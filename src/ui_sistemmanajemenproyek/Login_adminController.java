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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login_adminController implements Initializable {

    @FXML
    private Button btnMasuk;
    
    @FXML
    private Button btnBatal;

    @FXML
    private PasswordField fldPassword;

    @FXML
    private TextField fldUsername;

    @FXML
    void masukAkun(ActionEvent event) throws IOException {
    String username = fldUsername.getText();
    String password = fldPassword.getText();

    if (validasiLogin(username, password) && username.toLowerCase().contains("adm")) {
        InformasiSesi.setUsername(username);
        showInfoDialog("Login Berhasil", "Selamat datang, " + username + "!");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_utama.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnMasuk.getScene().getWindow();
        stage.setScene(new Scene(root));
    } else {
        showErrorDialog("Login Gagal", "Username atau password salah. Silakan coba lagi.");
    }
}
    
    @FXML
    void masukLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Stage stage = (Stage) btnBatal.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
}
