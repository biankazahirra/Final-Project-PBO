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
import java.time.LocalDate;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Hapus_proyekController implements Initializable{

    @FXML
    private Button btnAgt;

    @FXML
    private Button btnHapus;

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
    private Button btnUpdateStatus;

    @FXML
    private ComboBox<Proyek> cboxId;

    @FXML
    private Label lblUsername;

    @FXML
    void deleteData(ActionEvent event) {
        if (event.getSource() == btnHapus) {
            if (konfirmasiHapus()) {
                hapusData();
                showInfoDialog("Berhasil", "Berhasil menghapus data");
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
    void goToUpdateStatus(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("update_status.fxml"));

        Stage stage = (Stage) btnUpdateStatus.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String username = InformasiSesi.getUsername();
        lblUsername.setText(username);
        showCbox();
    }
    
    public ObservableList<Proyek> getDataProyek(){
        ObservableList<Proyek> proyekList = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT id, nama FROM daftar_proyek"; // Assuming the column name is idProyek

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Proyek proyek = new Proyek(rs.getInt("id"), rs.getString("nama"));
                proyekList.add(proyek);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return proyekList;
    }
    
    public void showCbox(){
        ObservableList<Proyek> proyekList = getDataProyek();
        cboxId.setItems(proyekList);
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

    private void hapusData(){
        if (cboxId.getValue() != null) {
            String query = "DELETE FROM `daftar_proyek` WHERE id = " + cboxId.getValue().getIdProyek();
            update(query);
        } else {
            showErrorDialog("Menghapus Gagal", "Pilih proyek terlebih dahulu sebelum menghapus.");
            
        }

    }
    
    private void showErrorDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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
        alert.setTitle("Konfirmasi Hapus");
        alert.setHeaderText(null);
        alert.setContentText("Apakah Anda yakin ingin menghapus?");

        ButtonType buttonTypeYa = new ButtonType("Ya", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeBatal = new ButtonType("Batal", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYa, buttonTypeBatal);

        alert.initModality(Modality.APPLICATION_MODAL);

        alert.showAndWait();

        return alert.getResult() == buttonTypeYa;
    }
    
}
