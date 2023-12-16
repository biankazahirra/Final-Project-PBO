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
import java.util.Date;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author Bianka Zahirra Putri
 */
public class Daftar_jadwalController implements Initializable {

    @FXML
    private Button btnAgt;

    @FXML
    private Button btnTim;

    @FXML
    private Button btnMainMenu;

    @FXML
    private Button btnPyk;
    
    @FXML
    private Button btnTgs;

    @FXML
    private TableColumn<Jadwal, Proyek> klmIdPyk;

    @FXML
    private TableColumn<Jadwal, Tugas> klmNama;

    @FXML
    private TableColumn<Jadwal, Status_Tugas> klmStatus;

    @FXML
    private TableColumn<Jadwal, Tugas> klmTglMulai;

    @FXML
    private TableColumn<Jadwal, Tugas> klmTglSelesai;

    @FXML
    private Label lblUsername;

    @FXML
    private TableView<Jadwal> tblDaftarJadwal;
    
    @FXML
    void goToTugas(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("daftar_tugas.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnTgs.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    void goDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_utama.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnMainMenu.getScene().getWindow();
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
    void goToTim(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("daftar_tim.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnTim.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    void goToProyek(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("daftar_proyek.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnPyk.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    
   public ObservableList<Jadwal> getDataJadwal() {
    ObservableList<Jadwal> jadwals = FXCollections.observableArrayList();
    Connection conn = DBHelper.getConnection();
    String query = "SELECT daftar_anggota.idProyek, daftar_tugas.namaTugas, daftar_tugas.tanggalMulai, daftar_tugas.tanggalSelesai, daftar_jadwal.statusTugas " +
               "FROM daftar_jadwal " +
               "JOIN daftar_tugas ON daftar_jadwal.idTugas = daftar_tugas.idTugas " +
               "JOIN daftar_anggota ON daftar_tugas.idAnggota = daftar_anggota.idAnggota";


    Statement st;
    ResultSet rs;

   try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                
                Status_Tugas statusTugas = Status_Tugas.valueOf(rs.getString("statusTugas"));
                Jadwal jadwal = new Jadwal(
                        new Tugas(new Proyek(rs.getInt("idProyek"))),
                        new Tugas(rs.getString("namaTugas")),
                        new Tugas(rs.getDate("tanggalMulai").toLocalDate()),
                        new Tugas(rs.getDate("tanggalSelesai")),
                        statusTugas);
                jadwals.add(jadwal);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return jadwals;
    }

   public void showJadwal() {
    ObservableList<Jadwal> list = getDataJadwal();

    if (list != null) {
        klmIdPyk.setCellValueFactory(new PropertyValueFactory<>("idProyek"));
        klmNama.setCellValueFactory(new PropertyValueFactory<>("namaTugas"));
        klmStatus.setCellValueFactory(new PropertyValueFactory<>("statusTugas"));
        klmTglMulai.setCellValueFactory(new PropertyValueFactory<>("tanggalMulai"));
        klmTglSelesai.setCellValueFactory(new PropertyValueFactory<>("tanggalSelesai"));

        tblDaftarJadwal.setItems(list);
    } else {
        System.out.println("Data jadwal null");
    }
}



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String username = InformasiSesi.getUsername();
        lblUsername.setText(username);
        
        showJadwal();
        
    }    
    
}
