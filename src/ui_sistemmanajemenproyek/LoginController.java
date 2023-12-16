package ui_sistemmanajemenproyek;

import db.DBHelper;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoginController implements Initializable {

    @FXML
    private Button btnMasuk;

    @FXML
    private Button btnDaftar;

    @FXML
    private PasswordField fldPassword;

    @FXML
    private TextField fldUsername;

    @FXML
    private Hyperlink hlLupaPW;
    
    @FXML
    private Hyperlink btnLoginAdm;
    
    @FXML
    void goToLoginAdm(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login_admin.fxml"));

        Stage stage = (Stage) btnLoginAdm.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    void daftarAkun(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("daftar_akun.fxml"));

        Stage stage = (Stage) btnDaftar.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void lupaPW(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("daftar_akun.fxml"));

        Stage stage = (Stage) btnDaftar.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void masukAkun(ActionEvent event) throws IOException {
        String username = fldUsername.getText();
        String password = fldPassword.getText();

    if (validasiLogin(username, password)) {
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
}
