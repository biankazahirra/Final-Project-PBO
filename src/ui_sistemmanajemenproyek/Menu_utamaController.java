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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Menu_utamaController implements Initializable {
    
    @FXML
    private Button btnForm;

    @FXML
    private Button btnAgt;

    @FXML
    private Button btnJdwl;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnPyk;

    @FXML
    private Button btnTgs;

    @FXML
    private Button btnTim;

    @FXML
    private Label lblUsername;
    
    @FXML
    void goToForm(ActionEvent event) throws IOException {
        String username = InformasiSesi.getUsername();
        if(username.toLowerCase().contains("adm")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("form_proyek.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnForm.getScene().getWindow();
        stage.setScene(new Scene(root));
        } else{
            showErrorDialog("Akses Ditolak", "Anda bukan admin");
        }
        
    }

    @FXML
    void goToDafproy(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("daftar_proyek.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnPyk.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToDafta(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("daftar_anggota.fxml"));
        
        Stage stage = (Stage) btnAgt.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    void goToDaftim(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("daftar_tim.fxml"));
        
        Stage stage = (Stage) btnAgt.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    void goToDaftug(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("daftar_tugas.fxml"));
        
        Stage stage = (Stage) btnTgs.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    void goToDafwal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("daftar_jadwal.fxml"));
        
        Stage stage = (Stage) btnTgs.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    void goToLogin(ActionEvent event) throws IOException {
        if (event.getSource() == btnLogout) {
            if (konfirmasiLogout()) {
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        
                Stage stage = (Stage) btnLogout.getScene().getWindow();
                stage.setScene(new Scene(root));
                }
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String username = InformasiSesi.getUsername();
        lblUsername.setText(username);
       
    }

    private Akun_anggota getDataUsername(String username) {
        Connection conn = DBHelper.getConnection();
        String query = "SELECT * FROM `akun_anggota` WHERE id = " + username;
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                return new Akun_anggota(
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }   
    
    private boolean validasiLogin(String username, String password) {
        Connection conn = DBHelper.getConnection();
        String query = "SELECT * FROM `akun_anggota` WHERE username = '" + username
                + "' AND password = '" + password + "'";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)) {
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void showInfoDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showErrorDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    private boolean konfirmasiLogout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Logout");
        alert.setHeaderText(null);
        alert.setContentText("Apakah Anda yakin ingin keluar?");

        ButtonType buttonTypeYa = new ButtonType("Ya", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeBatal = new ButtonType("Batal", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYa, buttonTypeBatal);

        alert.initModality(Modality.APPLICATION_MODAL);

        alert.showAndWait();

        return alert.getResult() == buttonTypeYa;
    }
    
}
