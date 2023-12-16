
package ui_sistemmanajemenproyek;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


public class Jadwal {
   // private Proyek proyek;
    private Tugas idProy;
    private Tugas nmTugas;
    private Tugas tglMulai;
    private Tugas tglSelesai;
    private Status_Tugas statusTugas;

    public Jadwal(Tugas idProy, Tugas nmTugas, Tugas tglMulai, Tugas tglSelesai, Status_Tugas statusTugas) {
        this.idProy = idProy;
        this.nmTugas = nmTugas;
        this.tglMulai = tglMulai;
        this.tglSelesai = tglSelesai;
        this.statusTugas = statusTugas;
    }

    public Tugas getIdProy() {
        return idProy;
    }

    public void setIdProy(Tugas idProy) {
        this.idProy = idProy;
    }

    public Tugas getNmTugas() {
        return nmTugas;
    }

    public void setNmTugas(Tugas nmTugas) {
        this.nmTugas = nmTugas;
    }

    public Tugas getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(Tugas tglMulai) {
        this.tglMulai = tglMulai;
    }

    public Tugas getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(Tugas tglSelesai) {
        this.tglSelesai = tglSelesai;
    }

    public Status_Tugas getStatusTugas() {
        return statusTugas;
    }

    public void setStatusTugas(Status_Tugas statusTugas) {
        this.statusTugas = statusTugas;
    }
    
    public int getIdProyek() {
        return idProy.getIdProyek();
    }

    public String getNamaTugas() {
        return nmTugas.getNamaTugas();
    }
    
    public LocalDate getTanggalMulai() {
        return tglMulai.getTanggalMulai();
    }
    
    public Date getTanggalSelesai() {
        return tglSelesai.getTanggalSelesai();
    }


    
    
}
