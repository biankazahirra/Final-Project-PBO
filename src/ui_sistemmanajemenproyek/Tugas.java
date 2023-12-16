package ui_sistemmanajemenproyek;

import java.sql.Date;
import java.time.LocalDate;
import javafx.beans.property.SimpleObjectProperty;

public class Tugas {
    private int idTugas;
    private Proyek proyek;
    private Anggota anggota;
    private String namaTugas;
    private LocalDate tanggalMulai;
    private Date tanggalSelesai;

    public Tugas(Proyek proyek, Anggota anggota, String namaTugas, LocalDate tanggalMulai, Date tanggalSelesai) {
        this.proyek = proyek;
        this.anggota = anggota;
        this.namaTugas = namaTugas;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
    }

    public Tugas(int idTugas, String namaTugas) {
        this.idTugas = idTugas;
        this.namaTugas = namaTugas;
    }

    public Tugas(Proyek proyek) {
        this.proyek = proyek;
    }

    public Tugas(String namaTugas) {
        this.namaTugas = namaTugas;
    }
    
    public Tugas(LocalDate tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    } 

    public Tugas(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }
    
    public int getIdTugas() {
        return idTugas;
    }

    public void setIdTugas(int idTugas) {
        this.idTugas = idTugas;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setNamaAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public String getNamaTugas() {
        return namaTugas;
    }

    public void setNamaTugas(String namaTugas) {
        this.namaTugas = namaTugas;
    }

    public LocalDate getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(LocalDate tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }
    
    public int getIdProyek() {
        return proyek.getIdProyek();
    }

    public int getIdAnggota() {
        return anggota.getIdAnggota();
    }
    
    @Override
    public String toString() {
        return idTugas + " - " + namaTugas;
    }



}
