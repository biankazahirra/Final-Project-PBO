
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Form_jadwalController implements Initializable {

    @FXML
    private Button btnAgt;
    
    @FXML
    private Button btnPyk;
    
    @FXML
    private TextField fldId;

    @FXML
    private Button btnMainMenu;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnTgs;

    @FXML
    private Button btnTim;
    
    @FXML
    private Button btnHapusPyk;
    
    @FXML
    private Button btnHapusTim;
    
    @FXML
    private Button btnUpdateStatus;

    @FXML
    private ComboBox<Tugas> cboxTgs;

    @FXML
    private ComboBox<Jadwal> cboxJadwal;

    @FXML
    private ComboBox<Status_Tugas> cboxStatus;

    @FXML
    private Label lblUsername;

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
    void goToFormAnggota(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("form_anggota.fxml"));

        Stage stage = (Stage) btnAgt.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToFormTim(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("form_tim.fxml"));

        Stage stage = (Stage) btnTim.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToFormTugas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("form_tugas.fxml"));

        Stage stage = (Stage) btnTgs.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
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
    void insertData(ActionEvent event) {
        if (event.getSource().equals(btnTambah)) {
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
    
    public ObservableList<Tugas> getDataTugas(){
        ObservableList<Tugas> TugasList = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT idTugas, namaTugas FROM daftar_tugas"; 

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Tugas tugas =  new Tugas(rs.getInt("idTugas"), rs.getString("namaTugas"));
                TugasList.add(tugas);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return TugasList;
    }

    private ObservableList<Status_Tugas> getDataStatus() {
        ObservableList<Status_Tugas> status = FXCollections.observableArrayList(Status_Tugas.values());
        
        return status;
    }  
    
    public void showCbox(){
        ObservableList<Tugas> tugasList = getDataTugas();
        cboxTgs.setItems(tugasList);
        
        ObservableList<Status_Tugas> status = getDataStatus();
        cboxStatus.setItems(status);
    }
    
    private void tambahData(){
        String query = "INSERT INTO `daftar_jadwal` (idJadwal, idTugas, statusTugas) VALUES ('" + fldId.getText() + "','" +
                    cboxTgs.getValue().getIdTugas() + "','" + cboxStatus.getValue().getLabel() + "')";
        
        update(query);

        fldId.clear();
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
