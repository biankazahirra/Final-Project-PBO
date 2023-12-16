package ui_sistemmanajemenproyek;

import db.DBHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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

public class Daftar_tugasController implements Initializable {

    @FXML
    private Button btnAgt;

    @FXML
    private Button btnJdwl;

    @FXML
    private Button btnMainMenu;

    @FXML
    private Button btnPyk;

    @FXML
    private Button btnTim;

    @FXML
    private TableColumn<Tugas, Integer> klmIdPyk;

    @FXML
    private TableColumn<Tugas, Integer> klmAgt;

    @FXML
    private TableColumn<Tugas, String> klmTgs;

    @FXML
    private TableColumn<Tugas, LocalDate> klmTglMulai;

    @FXML
    private TableColumn<Tugas, Date> klmTglSelesai;

    @FXML
    private Label lblUsername;

    @FXML
    private TableView<Tugas> tblDaftarTugas;

    @FXML
    void goToJadwal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("daftar_jadwal.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnJdwl.getScene().getWindow();
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
    void goDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu_utama.fxml"));

        Stage stage = (Stage) btnMainMenu.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToProyek(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("daftar_proyek.fxml"));

        Stage stage = (Stage) btnPyk.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToAnggota(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("daftar_anggota.fxml"));

        Stage stage = (Stage) btnAgt.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public ObservableList<Tugas> getDataTugas() {
        ObservableList<Tugas> tgs = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT daftar_anggota.idProyek, daftar_anggota.idAnggota, daftar_tugas.namaTugas, daftar_tugas.tanggalMulai, daftar_tugas.tanggalSelesai " +
               "FROM daftar_tugas " +
               "JOIN daftar_anggota ON daftar_tugas.idAnggota = daftar_anggota.idAnggota " +
               "JOIN daftar_proyek ON daftar_anggota.idProyek = daftar_proyek.id";

        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Tugas temp;
            while (rs.next()) {
                Tugas tugas = new Tugas(
                        new Proyek(rs.getInt("idProyek")),
                        new Anggota(rs.getInt("idAnggota")),
                        rs.getString("namaTugas"),
                        LocalDate.parse(rs.getString("tanggalMulai")),
                        rs.getDate("tanggalSelesai")
                        
                );
                tgs.add(tugas);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tgs;
    }

        @Override
        public void initialize(URL url, ResourceBundle rb) {
            String username = InformasiSesi.getUsername();
            lblUsername.setText(username);

            showTugas();
        }

        public void showTugas() {
        ObservableList<Tugas> list = getDataTugas();

        if (list != null) {
            klmIdPyk.setCellValueFactory(new PropertyValueFactory<>("idProyek"));
            klmAgt.setCellValueFactory(new PropertyValueFactory<>("idAnggota"));
            klmTgs.setCellValueFactory(new PropertyValueFactory<>("namaTugas"));
            klmTglMulai.setCellValueFactory(new PropertyValueFactory<>("tanggalMulai"));
            klmTglSelesai.setCellValueFactory(new PropertyValueFactory<>("tanggalSelesai"));
            tblDaftarTugas.setItems(list);
        } else {
            System.out.println("Data tugas null");
        }
    }

}
