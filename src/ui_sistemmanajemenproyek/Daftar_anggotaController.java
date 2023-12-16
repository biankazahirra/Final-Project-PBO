
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
import javafx.beans.property.SimpleObjectProperty;
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

public class Daftar_anggotaController implements Initializable {
    
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
    private TableColumn<Anggota, Integer> klmId;

    @FXML
    private TableColumn<Anggota, String> klmNama;

    @FXML
    private TableColumn<Anggota, Tim> klmTim;
    
    @FXML
    private TableColumn<Anggota, Proyek> klmProyek;

    @FXML
    private Label lblUsername;

    @FXML
    private TableView<Anggota> tblAgt;

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
    void goToProyek(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("daftar_proyek.fxml"));

        Stage stage = (Stage) btnPyk.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    void goToTim(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("daftar_tim.fxml"));

        Stage stage = (Stage) btnTim.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    void goToTugas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("daftar_tugas.fxml"));

        Stage stage = (Stage) btnTgs.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    
    
    
//    private Akun_anggota getDataUsername(String username) {
//        Connection conn = DBHelper.getConnection();
//        String query = "SELECT * FROM `akun_anggota` WHERE id = " + username;
//        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)) {
//            if (rs.next()) {
//                return new Akun_anggota(
//                        rs.getString("username"),
//                        rs.getString("password")
//                );
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//    
//    public void initData(String username) {
//        Akun_anggota agt = getDataUsername(username);
//
//        lblUsername.setText(String.valueOf(agt.getUsername()));
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String username = InformasiSesi.getUsername();
        lblUsername.setText(username);
        
        showAnggota();

    }

    public ObservableList<Anggota> getDataAnggota(){
        ObservableList<Anggota> agt = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT * FROM daftar_anggota JOIN daftar_tim ON daftar_anggota.idTim = daftar_tim.idTim "
        + "JOIN daftar_proyek ON daftar_anggota.idProyek = daftar_proyek.id";
        
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Anggota anggota;
                anggota = new Anggota(
                        rs.getInt("idAnggota"),
                        rs.getString("namaAnggota"),
                        new Tim(rs.getString("namaTim")),
                        new Proyek(rs.getString("nama"))
                );
                agt.add(anggota);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return agt;
        
    }
    
    public void showAnggota() {
        ObservableList<Anggota> list = getDataAnggota();

        if (list != null) {
            klmId.setCellValueFactory(new PropertyValueFactory<>("idAnggota"));
            klmNama.setCellValueFactory(new PropertyValueFactory<>("namaAnggota"));
            klmTim.setCellValueFactory(new PropertyValueFactory<>("namaTim"));
            klmProyek.setCellValueFactory(new PropertyValueFactory<>("namaProyek"));
            tblAgt.setItems(list);
        } else {
            System.out.println("Data anggota null");
        }
   }

    
}
