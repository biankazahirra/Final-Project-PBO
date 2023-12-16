/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Update_statusController implements Initializable {

    @FXML
    private Button btnAgt;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnHapusProyek;

    @FXML
    private Button btnHapusTim;

    @FXML
    private Button btnJdwl;

    @FXML
    private Button btnMainMenu;

    @FXML
    private Button btnPyk;

    @FXML
    private Button btnTgs;

    @FXML
    private Button btnTim;

    @FXML
    private ComboBox<Tugas> cboxId;

    @FXML
    private ComboBox<Status_Tugas> cboxStatus;

    @FXML
    private Label lblUsername;

    @FXML
    void updateData(ActionEvent event) {
        if (event.getSource() == btnUpdate) {
            if (konfirmasiHapus()) {
                updateData();
                showInfoDialog("Berhasil", "Berhasil memperbarui data");
                }
            }
    }

    @FXML
    void goDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu_utama.fxml"));

        Stage stage = (Stage) btnMainMenu.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToFormAnggota(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("form_anggota.fxml"));

        Stage stage = (Stage) btnAgt.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToFormProyek(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("form_proyek.fxml"));

        Stage stage = (Stage) btnPyk.getScene().getWindow();
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
    void goToFormJadwal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("form_jadwal.fxml"));

        Stage stage = (Stage) btnJdwl.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToHapusTim(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hapus_tim.fxml"));

        Stage stage = (Stage) btnHapusTim.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    
    @FXML
    void goToHapusProyek(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hapus_proyek.fxml"));

        Stage stage = (Stage) btnHapusProyek.getScene().getWindow();
        stage.setScene(new Scene(root));
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
        cboxId.setItems(tugasList);
        
        ObservableList<Status_Tugas> status = getDataStatus();
        cboxStatus.setItems(status);
    }
    
    private void updateData(){
        String query = "UPDATE `daftar_jadwal` SET `statusTugas` = '" + cboxStatus.getValue().getLabel()  + "' WHERE `idTugas` = '" + cboxId.getValue().getIdTugas() + "'";
        
        update(query);
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
    
    private boolean konfirmasiHapus() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Update");
        alert.setHeaderText(null);
        alert.setContentText("Apakah Anda yakin ingin memperbarui status?");

        ButtonType buttonTypeYa = new ButtonType("Ya", ButtonData.OK_DONE);
        ButtonType buttonTypeBatal = new ButtonType("Batal", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYa, buttonTypeBatal);

        alert.initModality(Modality.APPLICATION_MODAL);

        alert.showAndWait();

        return alert.getResult() == buttonTypeYa;
    }
    
}
