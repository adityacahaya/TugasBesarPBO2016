package Model;

import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Petugas extends Orang{
    
    private String id_petugas;
    private Gudang[] gudang = new Gudang[1000];
    int index = 0;
    
    File log = new File("Data Gudang.txt");
        
    public Petugas(){
        
    }
    
    public Petugas(String nama_petugas, String id_petugas){
        super(nama_petugas);
        this.id_petugas = id_petugas;
        bacaFile();
    }
    
    public void bacaFile(){
        try {
            FileReader fr = new FileReader("Data Gudang.txt");
            BufferedReader bf = new BufferedReader(fr);
            String x = "";
            String nama_gudang = "";
            String id_gudang = "";
            int kapasitas = 0;
            int kapasitas_sisa;
            int i = 0;
            try {
                while((x = bf.readLine()) != null){
                    if(i == 0){
                        nama_gudang = x;
                        i++;
                    }else if(i == 1){
                        id_gudang = x;
                        i++;
                    }else if(i == 2){
                        kapasitas = Integer.parseInt(x);
                        i++;
                    }else if(i == 3){
                        kapasitas_sisa = Integer.parseInt(x);
                        i = 0;
                        Gudang gdg = new Gudang(nama_gudang,id_gudang,kapasitas);
                        gdg.setKapasitas_sisa(kapasitas_sisa);
                        this.gudang[index] = gdg;
                        index++;
                    }
                }
                System.out.println(index);
            } catch (IOException ex) {
                System.out.println("File Gagal Di Baca");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File Gagal Di Baca");
        }
    }

    public String getId_petugas() {
        return id_petugas;
    }

    public void setId_petugas(String id_petugas) {
        this.id_petugas = id_petugas;
    }
    
    public void tambahBarangGudang(Gudang gdg, Barang brg){
        gdg.addBarang(brg);
    }

    public Gudang[] getGudang() {
        return gudang;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    public void addGudang(String nama_gudang, String id_gudang, int kapasitas
            ,int kapasitas_sisa){
        try {
            if(log.exists() == false){
                log.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileWriter(log, true));
            out.println(nama_gudang);
            out.println(id_gudang);
            out.println(String.valueOf(kapasitas));
            out.println(String.valueOf(kapasitas_sisa));
            out.close();
            out.flush();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        Gudang gdg = new Gudang(nama_gudang,id_gudang,kapasitas);
        gdg.setKapasitas_sisa(kapasitas);
        this.gudang[index] = gdg;
        index++;
    }
    
    
    public void hapusGudang(String id_gudang){
        System.out.println("Masuk Hapus");
        int ketahuan = -1;
        for(int i=0; i<index; i++){
            if(this.gudang[i].getId_gudang().equals(id_gudang)){
                ketahuan = i;
                System.out.println("Ketahuan");
                break;
            }
        }
        
        try {
            String nama_file = this.gudang[ketahuan].getNama_gudang()+".txt";
            File fl = new File(nama_file);
            if(fl.exists()){
                PrintWriter in = new PrintWriter(new FileWriter(fl, false));
                in.close();
                in.flush();
            }       
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        if(ketahuan != -1){
            System.out.println("Pindah2 Array");
            for(int i=ketahuan; i<index-1; i++){
                this.gudang[i].setNama_gudang(this.gudang[i+1].getNama_gudang());
                this.gudang[i].setId_gudang(this.gudang[i+1].getId_gudang());
                this.gudang[i].setKapasitas(this.gudang[i+1].getKapasitas());
                this.gudang[i].setKapasitas_sisa(this.gudang[i+1].getKapasitas_sisa());
                System.out.println(this.gudang[i].getKapasitas_sisa()+"<-- Data");
                System.out.println("Oke");
            }
            index--;
        }
        
        try {
            if(log.exists()){
                PrintWriter in = new PrintWriter(new FileWriter(log, false));
                in.close();
                in.flush();
            }
            
            for(int i=0; i<index; i++){
                PrintWriter in = new PrintWriter(new FileWriter(log, true));
                in.println(this.gudang[i].getNama_gudang());
                in.println(this.gudang[i].getId_gudang());
                in.println(String.valueOf(this.gudang[i].getKapasitas()));
                in.println(String.valueOf(this.gudang[i].getKapasitas_sisa()));
                in.close();
                in.flush();
            }
                    
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }
    
    public void editGudang(String id_gudang, int kapa_baru){
        
        int ketahuan = -1;
        for(int i=0; i<index; i++){
            if(this.gudang[i].getId_gudang().equals(id_gudang)){
                ketahuan = i;
                break;
            }
        }
        
        int hitungKapasitasSisa =  (kapa_baru
                - this.gudang[ketahuan].getKapasitas())
                + this.gudang[ketahuan].getKapasitas_sisa();
        System.out.println(hitungKapasitasSisa);
        
        if(ketahuan != -1){
            this.gudang[ketahuan].setKapasitas_sisa(hitungKapasitasSisa);
            this.gudang[ketahuan].setKapasitas(kapa_baru);
        }
        
        try {
            if(log.exists()){
                log.delete();
            }
            PrintWriter out = new PrintWriter(new FileWriter(log, false));
            out.close();
            out.flush();
            for(int i=0; i<index; i++){
                PrintWriter in = new PrintWriter(new FileWriter(log, true));
                in.println(this.gudang[i].getNama_gudang());
                in.println(this.gudang[i].getId_gudang());
                in.println(String.valueOf(this.gudang[i].getKapasitas()));
                in.println(String.valueOf(this.gudang[i].getKapasitas_sisa()));
                in.close();
                in.flush();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void tambahBarangkeGudang(String nama_gudang, String nama_barang,Penyedia p){
        System.out.println("");
        
        int index_gudang = 0;
        for(int i=0; i<this.getIndex(); i++){
            if(this.getGudang()[i].getNama_gudang().equals(nama_gudang)){
                index_gudang = i;
                break;
            }
        }
        
        int index_barang = 0;
        for(int i=0; i<p.getIndex(); i++){
            if(p.getDaftarBarang()[i].getNama_barang().equals(nama_barang)){
                index_barang = i;
                break;
            }
        }
        
        if(this.getGudang()[index_gudang].getKapasitas_sisa() 
                < p.getDaftarBarang()[index_barang].getJumlah_barang()){
            JOptionPane.showMessageDialog(null, "Kapasitas Gudang Kurang !", "Error !", 0);
        }else{
            String nama_file = nama_gudang+".txt";
            File fl = new File(nama_file);
            if(fl.exists()){
                try {
                    for(int i=0; i<p.getIndex(); i++){
                        if(p.getDaftarBarang()[i].getNama_barang().equals(nama_barang)){
                            PrintWriter out = new PrintWriter(new FileWriter(fl, true));
                            out.println(p.getDaftarBarang()[i].getNama_barang());
                            out.println(p.getDaftarBarang()[i].getId_barang());
                            out.println(p.getDaftarBarang()[i].getJumlah_barang());
                            out.close();
                            out.flush();
                            this.getGudang()[index_gudang].addBarang(p.getDaftarBarang()[i]);

                            try {
                                if(log.exists()){
                                    PrintWriter in = new PrintWriter(new FileWriter(log, false));
                                    in.close();
                                    in.flush();
                                }

                                for(int j=0; j<index; j++){
                                    PrintWriter in = new PrintWriter(new FileWriter(log, true));
                                    in.println(this.gudang[j].getNama_gudang());
                                    in.println(this.gudang[j].getId_gudang());
                                    in.println(String.valueOf(this.gudang[j].getKapasitas()));
                                    in.println(String.valueOf(this.gudang[j].getKapasitas_sisa()));
                                    in.close();
                                    in.flush();
                                }

                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                            JOptionPane.showMessageDialog(null, "Barang Berhasil Ditambahkan ke Gudang !", "Berhasil !", 1);
                            break;
                        }
                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }else{
                try {
                    fl.createNewFile();
                    for(int i=0; i<p.getIndex(); i++){
                        if(p.getDaftarBarang()[i].getNama_barang().equals(nama_barang)){
                            PrintWriter out = new PrintWriter(new FileWriter(fl, true));
                            out.println(p.getDaftarBarang()[i].getNama_barang());
                            out.println(p.getDaftarBarang()[i].getId_barang());
                            out.println(p.getDaftarBarang()[i].getJumlah_barang());
                            out.close();
                            out.flush();
                            this.getGudang()[index_gudang].addBarang(p.getDaftarBarang()[i]);
                            System.out.println(p.getDaftarBarang()[i].getId_barang());

                            try {
                                if(log.exists()){
                                    PrintWriter in = new PrintWriter(new FileWriter(log, false));
                                    in.close();
                                    in.flush();
                                }

                                for(int j=0; j<index; j++){
                                    PrintWriter in = new PrintWriter(new FileWriter(log, true));
                                    in.println(this.gudang[j].getNama_gudang());
                                    in.println(this.gudang[j].getId_gudang());
                                    in.println(String.valueOf(this.gudang[j].getKapasitas()));
                                    in.println(String.valueOf(this.gudang[j].getKapasitas_sisa()));
                                    in.close();
                                    in.flush();
                                }

                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                            JOptionPane.showMessageDialog(null, "Barang Berhasil Ditambahkan ke Gudang !", "Berhasil !", 1);
                            break;
                        }
                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
