
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

public class Daftar_akunController implements Initializable {

    @FXML
    private Button btnInsertDaftar;

    @FXML
    private PasswordField fldPW;

    @FXML
    private TextField fldUname;
    
    @FXML
    private Button btnKembali;
    
    @FXML
    void backToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Stage stage = (Stage) btnKembali.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    void insertDaftarAkun(ActionEvent event) throws IOException {
        tambahData();
        showInfoDialog("Berhasil daftar", "Berhasil daftar akun");
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Stage stage = (Stage) btnInsertDaftar.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
    private void tambahData(){
        String query = "INSERT INTO `akun_anggota` VALUES ('" + fldUname.getText() + "','" + fldPW.getText() + "')";
        update(query);
    }
    
    private void showInfoDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    
}
