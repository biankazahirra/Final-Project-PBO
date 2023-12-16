package ui_sistemmanajemenproyek;

import db.DBHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Form_tugasController implements Initializable {

    @FXML
    private Button btnAgt;

    @FXML
    private Button btnJdwl;

    @FXML
    private Button btnMainMenu;

    @FXML
    private Button btnPyk;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnTim;

    @FXML
    private ComboBox<Anggota> cboxAgt;

    @FXML
    private DatePicker dpTglMulai;

    @FXML
    private DatePicker dpTglSelesai;

    @FXML
    private TextField fldId;

    @FXML
    private TextField fldNama;

    @FXML
    private Label lblUsername;
    
    @FXML
    private Button btnHapusPyk;
    
    @FXML
    private Button btnHapusTim;
    
    @FXML
    private Button btnUpdateStatus;
    
    @FXML
    void goToHapusProyek(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hapus_proyek.fxml"));

        Stage stage = (Stage) btnHapusPyk.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @FXML
    void goToHapusTim(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hapus_tim.fxml"));

        Stage stage = (Stage) btnHapusTim.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    
    @FXML
    void goToUpdateStatus(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("update_status.fxml"));

        Stage stage = (Stage) btnUpdateStatus.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu_utama.fxml"));

        Stage stage = (Stage) btnMainMenu.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToFormProyek(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("form_proyek.fxml"));

        Stage stage = (Stage) btnPyk.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToFormJadwal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("form_jadwal.fxml"));

        Stage stage = (Stage) btnJdwl.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToFormTim(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("form_tim.fxml"));

        Stage stage = (Stage) btnTim.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToFormAnggota(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("form_anggota.fxml"));

        Stage stage = (Stage) btnAgt.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void insertData(ActionEvent event) {
         if(event.getSource() == btnTambah){
                tambahData();
                showInfoDialog("Berhasil", "Berhasil menambah data");

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String username = InformasiSesi.getUsername();
        lblUsername.setText(username);
        showCbox();
    }    
    
    public ObservableList<Anggota> getDataAnggota(){
        ObservableList<Anggota> AnggotaList = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT idAnggota, namaAnggota FROM daftar_anggota"; 

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Anggota anggota = new Anggota(rs.getInt("idAnggota"), rs.getString("namaAnggota"));
                AnggotaList.add(anggota);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return AnggotaList;
    }
    
    public void showCbox(){
        ObservableList<Anggota> anggotaList = getDataAnggota();
        cboxAgt.setItems(anggotaList);
    }
    
    private void tambahData(){
        String query = "INSERT INTO `daftar_tugas` (idTugas, idAnggota, namaTugas, tanggalMulai, tanggalSelesai) VALUES ('" + fldId.getText() + "','" +
                    cboxAgt.getValue().getIdAnggota() + "','" + fldNama.getText() + "','" +
                    dpTglMulai.getValue() + "','" + dpTglSelesai.getValue() + "')";

        
        update(query);

        fldId.clear();
        fldNama.clear();
        
    }
    
    private void update(String query){
        Connection conn = DBHelper.getConnection();
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    private void showInfoDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
}


