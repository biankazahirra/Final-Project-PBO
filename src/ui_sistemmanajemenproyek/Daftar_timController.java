
package ui_sistemmanajemenproyek;

import db.DBHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class Daftar_timController implements Initializable {
    
    @FXML
    private Button btnJdwl;

    @FXML
    private Button btnMainMenu;

    @FXML
    private Button btnPyk;

    @FXML
    private Button btnTgs;
    
    @FXML
    private Button btnAgt;

    @FXML
    private TableColumn<Tim, Integer> klmId;

    @FXML
    private TableColumn<Tim, String> klmNama;

    @FXML
    private TableColumn<Tim, String> klmProyek;

    @FXML
    private Label lblUsername;
    
    @FXML
    private TableView<Tim> tblDaftarTim;

    @FXML
    void goDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_utama.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnMainMenu.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    void goToJadwal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("daftar_jadwal.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnJdwl.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    
    @FXML
    void goToAnggota(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("daftar_anggota.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnAgt.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    void goToProyek(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("daftar_proyek.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnPyk.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    void goToTugas(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("daftar_tugas.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnTgs.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    
    public ObservableList<Tim> getDataTim() {
        ObservableList<Tim> tm = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT * FROM daftar_tim";
        
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Tim tim = new Tim(rs.getInt("idtim"), rs.getString("namatim"));
                tm.add(tim);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tm;
        
       }
    
    public void showTim() {
        ObservableList<Tim> list = getDataTim();

        if (list != null) {
            klmId.setCellValueFactory(new PropertyValueFactory<>("idTim"));
            klmNama.setCellValueFactory(new PropertyValueFactory<>("namaTim"));
            tblDaftarTim.setItems(list);
        } else {
            System.out.println("Data tim null");
        }
}

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String username = InformasiSesi.getUsername();
        lblUsername.setText(username);
        
        showTim();
    } 
    
}
