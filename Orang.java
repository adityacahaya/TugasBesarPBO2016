package Model;

abstract class Orang {
    
    private String nama;
    
    public Orang(){
        
    }
    
    public Orang(String nama){
        this.nama = nama;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
    
    public String getNama(){
        return this.nama;
    }
}
