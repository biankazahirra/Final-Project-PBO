
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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Form_input_proyekController implements Initializable {

    @FXML
    private Button btnTambah;
    
    @FXML
    private Button btnKembali;

    @FXML
    private TextField fldAnggaran;

    @FXML
    private TextField fldDeadline;

    @FXML
    private TextField fldID;

    @FXML
    private TextField fldNama;

    @FXML
    private TextArea fldTujuan;

    @FXML
    void insertData(ActionEvent event) {
        if(event.getSource() == btnTambah){
            tambahData();
        }
    }
    
    @FXML
    void backDaftarProyek(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("daftar_proyek.fxml"));
        
        Stage stage = (Stage) btnKembali.getScene().getWindow();
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
        String query = "INSERT INTO `daftar_proyek` VALUES ('" + fldID.getText() + "','" + fldNama.getText() + "','" + fldTujuan.getText() + "','" + fldDeadline.getText() + "','" + fldAnggaran.getText() + "')";
        update(query);
        
        fldID.clear();
        fldNama.clear();
        fldTujuan.clear();
        fldDeadline.clear();
        fldAnggaran.clear();
    }
    
}
