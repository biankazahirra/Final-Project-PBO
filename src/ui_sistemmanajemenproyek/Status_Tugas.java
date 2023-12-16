
package ui_sistemmanajemenproyek;

public enum Status_Tugas {
    BELUM_DIMULAI("BELUM_DIMULAI"),
    SEDANG_DIPROSES("SEDANG_DIPROSES"),
    SELESAI("SELESAI"),
    TERTUNDA("TERTUNDA");
    
    private final String label;

    Status_Tugas(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
