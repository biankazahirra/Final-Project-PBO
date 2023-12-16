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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Form_anggotaController implements Initializable {

    @FXML
    private Button btnJdwl;
    
    @FXML
    private Button btnHapusPyk;
    
    @FXML
    private Button btnHapusTim;
    
    @FXML
    private Button btnUpdateStatus;

    @FXML
    private Button btnMainMenu;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnTgs;
    
    @FXML
    private Button btnPyk;

    @FXML
    private Button btnTim;

    @FXML
    private ComboBox<Proyek> cboxProyek;

    @FXML
    private ComboBox<Tim> cboxTim;

    @FXML
    private TextField fldId;

    @FXML
    private TextField fldNama;

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
    void goToFormTugas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("form_tugas.fxml"));

        Stage stage = (Stage) btnTgs.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void insertData(ActionEvent event) {
        if(event.getSource() == btnTambah){
                tambahData();
                showInfoDialog("Berhasil", "Berhasil menambah data");

        }
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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String username = InformasiSesi.getUsername();
        lblUsername.setText(username);
        showCbox();
    }

    public ObservableList<Tim> getDataTim(){
        ObservableList<Tim> timList = FXCollections.observableArrayList();

        Connection conn = DBHelper.getConnection();
        String query = "SELECT idTim, namaTim FROM daftar_tim";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Tim tim = new Tim(rs.getInt("idTim"), rs.getString("namaTim"));
                timList.add(tim);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return timList;
    }

    public ObservableList<Proyek> getDataProyek(){
        ObservableList<Proyek> proyekList = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT id, nama FROM daftar_proyek"; 

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
        ObservableList<Tim> timList = getDataTim();
        cboxTim.setItems(timList);

        ObservableList<Proyek> proyekList = getDataProyek();
        cboxProyek.setItems(proyekList);
    }
    
    private void tambahData(){
        String query = "INSERT INTO `daftar_anggota` VALUES ('" + fldId.getText() + "','" + fldNama.getText() + "','" +
                cboxTim.getValue().getIdTim() + "','" + cboxProyek.getValue().getIdProyek() + "')";

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
