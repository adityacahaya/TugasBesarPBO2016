package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Penyedia extends Orang{
    
    private String id_penyedia;
    private Barang[] daftarBarang = new Barang[1000];
    private int index = 0;

    File log = new File("Data Barang.txt");
    
    public Penyedia(){
        
    }
    
    public Penyedia(String nama, String id_penyedia) {
        super(nama);
        this.id_penyedia = id_penyedia;
        bacaFile();
    }
    
    public void bacaFile(){
        try {
            FileReader fr = new FileReader("Data Barang.txt");
            BufferedReader bf = new BufferedReader(fr);
            String x = "";
            String nama_barang = "";
            String id_barang = "";
            int jumlah = 0;
            int i = 0;
            try {
                while((x = bf.readLine()) != null){
                    if(i == 0){
                        nama_barang = x;
                        i++;
                    }else if(i == 1){
                        id_barang = x;
                        i++;
                    }else if(i == 2){
                        jumlah = Integer.parseInt(x);
                        i = 0;
                        Barang brg = new Barang(nama_barang,id_barang);
                        brg.setJumlah_barang(jumlah);
                        this.daftarBarang[index] = brg;
                        System.out.println(this.daftarBarang[index].getNama_barang());
                        System.out.println(this.daftarBarang[index].getId_barang());
                        System.out.println(this.daftarBarang[index].getJumlah_barang());
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
    
    public void setId_penyedia(String id_penyedia){
        this.id_penyedia = id_penyedia;
    }
    
    public String getId_penyedia(){
        return this.id_penyedia;
    }
    
    public void createBarang(String nama_barang, String id_barang, int jumlah){
        try {
            if(log.exists() == false){
                log.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileWriter(log, true));
            out.println(nama_barang);
            out.println(id_barang);
            out.println(String.valueOf(jumlah));
            out.close();
            out.flush();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        Barang brg = new Barang(nama_barang,id_barang);
        brg.setJumlah_barang(jumlah);
        this.daftarBarang[index] = brg;
        index++;
    }

    public Barang[] getDaftarBarang() {
        return daftarBarang;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
