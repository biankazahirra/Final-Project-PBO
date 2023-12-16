
package ui_sistemmanajemenproyek;


public class Tim {
    private int idTim;
    private String namaTim;

    public Tim(int idTim, String namaTim) {
        this.idTim = idTim;
        this.namaTim = namaTim;
    }

    public Tim(int idTim) {
        this.idTim = idTim;
    }
   

    public Tim(String namaTim) {
        this.namaTim = namaTim;
    }
    

    public int getIdTim() {
        return idTim;
    }

    public void setIdTim(int idTim) {
        this.idTim = idTim;
    }

    public String getNamaTim() {
        return namaTim;
    }

    public void setNamaTim(String namaTim) {
        this.namaTim = namaTim;
    }

    @Override
    public String toString() {
        return namaTim + " - " + idTim;
    }
    
    

}


