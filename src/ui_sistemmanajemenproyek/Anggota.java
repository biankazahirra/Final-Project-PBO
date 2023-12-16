
package ui_sistemmanajemenproyek;

public class Anggota {
    private int idAnggota;
    private String namaAnggota;
    private Tim tim;
    private Proyek proyek;

    public Anggota(int idAnggota, String namaAnggota, Tim tim, Proyek proyek) {
        this.idAnggota = idAnggota;
        this.namaAnggota = namaAnggota;
        this.tim = tim;
        this.proyek = proyek;
    }

    public Anggota(int idAnggota, String namaAnggota) {
        this.idAnggota = idAnggota;
        this.namaAnggota = namaAnggota;
    }

    public Anggota(Tim tim) {
        this.tim = tim;
    }

    public Anggota(Proyek proyek) {
        this.proyek = proyek;
    }
    
    public Anggota(int idAnggota) {
        this.idAnggota = idAnggota;
    }

    public int getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(int idAnggota) {
        this.idAnggota = idAnggota;
    }

    public String getNamaAnggota() {
        return namaAnggota;
    }

    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }

    public Tim getTim() {
        return tim;
    }

    public void setTim(Tim tim) {
        this.tim = tim;
    }

    public Proyek getProyek() {
        return proyek;
    }

    public void setProyek(Proyek proyek) {
        this.proyek = proyek;
    }
    
    public String getNamaTim() {
        return tim.getNamaTim();
    }

    public String getNamaProyek() {
        return proyek.getNamaProyek();
    }
    
    @Override
    public String toString(){
        return idAnggota + " - "+ namaAnggota;
    }

    
}
