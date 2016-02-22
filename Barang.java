package Model;

public class Barang {
    
    private String nama_barang;
    private String id_barang;
    private int jumlah_barang;
    
    public Barang(){
        
    }
    
    public Barang(String nama_barang, String id_barang){
        this.nama_barang = nama_barang;
        this.id_barang = id_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getId_barang() {
        return id_barang;
    }

    public void setId_barang(String id_barang) {
        this.id_barang = id_barang;
    }
    
    public int getJumlah_barang() {
        return jumlah_barang;
    }

    public void setJumlah_barang(int jumlah_barang) {
        this.jumlah_barang = jumlah_barang;
    }
}
