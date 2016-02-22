package Model;

public class Gudang {
    
    private String nama_gudang;
    private String id_gudang;
    private int kapasitas;
    private Barang[] daftarBarang = new Barang[1000];
    private int index = 0;
    private int kapasitas_sisa;
    
    public Gudang(){
        
    }
    
    public Gudang(String nama_gudang, String id_gudang, int kapasitas){
        this.nama_gudang = nama_gudang;
        this.id_gudang = id_gudang;
        this.kapasitas = kapasitas;
    }

    public String getNama_gudang() {
        return nama_gudang;
    }

    public void setNama_gudang(String nama_gudang) {
        this.nama_gudang = nama_gudang;
    }

    public String getId_gudang() {
        return id_gudang;
    }

    public void setId_gudang(String id_gudang) {
        this.id_gudang = id_gudang;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
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
    
    public void addBarang(Barang brg){
        this.daftarBarang[index] = brg;
        this.setKapasitas_sisa(this.kapasitas_sisa - brg.getJumlah_barang());
        index++;
    }

    public int getKapasitas_sisa() {
        return kapasitas_sisa;
    }

    public void setKapasitas_sisa(int kapasitas_sisa) {
        this.kapasitas_sisa = kapasitas_sisa;
    }
}
