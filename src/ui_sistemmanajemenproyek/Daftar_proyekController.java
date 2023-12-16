package ui_sistemmanajemenproyek;

import db.DBHelper;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Daftar_proyekController implements Initializable {
    
    @FXML
    private Button btnAgt;

    @FXML
    private Button btnJdwl;
    
    @FXML
    private Button btnTim;

    @FXML
    private Button btnMainMenu;

    @FXML
    private Button btnTambahAgt;

    @FXML
    private Button btnTgs;

    @FXML
    private TableColumn<Proyek, BigDecimal> klmAnggaran;

    @FXML
    private TableColumn<Proyek, LocalDate> klmDeadline;

    @FXML
    private TableColumn<Proyek, Integer> klmId;

    @FXML
    private TableColumn<Proyek, String> klmNama;

    @FXML
    private TableColumn<Proyek, String> klmTujuan;

    @FXML
    private Label lblUsername;

    @FXML
    private TableView<Proyek> tblDaftarProyek;
    
    @FXML
    private Button btnTambahProyek;

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
    void goToJadwal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("daftar_jadwal.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnJdwl.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    void goToTugas(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("daftar_tugas.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnTgs.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    
    @FXML
    void goToTim(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("daftar_tim.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnTim.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        String username = InformasiSesi.getUsername();
        lblUsername.setText(username);

        showProyek();
    }

    
    
    public ObservableList<Proyek> getDataProyek(){
        ObservableList<Proyek> pyk = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT * FROM `daftar_proyek`";
        
        Statement st;
        ResultSet rs;
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Proyek temp;
            while (rs.next()) {
                temp = new Proyek(rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("tujuan"),
                        LocalDate.parse(rs.getString("tenggat_waktu")),
                        rs.getBigDecimal("anggaran"));
                
                pyk.add(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pyk;
    }
    
    public void showProyek() {
        ObservableList<Proyek> list = getDataProyek();
        
        klmId.setCellValueFactory(new PropertyValueFactory<>("idProyek"));
        klmNama.setCellValueFactory(new PropertyValueFactory<>("namaProyek"));
        klmTujuan.setCellValueFactory(new PropertyValueFactory<>("tujuanProyek"));
        klmDeadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        klmAnggaran.setCellValueFactory(new PropertyValueFactory<>("anggaranProyek"));
        tblDaftarProyek.setItems(list);
    }
    
}
    

//    @FXML
//    void openForm_tambah(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("form_input_proyek.fxml"));
//        
//        Stage stage = (Stage) btnTambahProyek.getScene().getWindow();
//        stage.setScene(new Scene(root));
//    }
//    
//    @FXML
//    void openDetailProyek(ActionEvent event) throws IOException {
        // Mendapatkan ID proyek dari TextField
     //   String proyekIdText = fldCariProyek.getText();

        // Memeriksa apakah ID proyek tidak kosong
//        if (!proyekIdText.isEmpty()) {
//        try {
//            int proyekId = Integer.parseInt(proyekIdText);
//
//            // Memeriksa apakah proyek dengan ID yang dicari ada
//            if (isProyekExist(proyekId)) {
//                openDetailProyekById(proyekId);
//            } else {
//                showNotFoundAlert();
//            }
//        } catch (NumberFormatException e) {
//            // Tampilkan alert jika input bukan angka
//            showInvalidInputAlert();
//        }
//    }
//  }

//    private void openDetailProyekById(int proyekId) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("detail_proyek.fxml"));
//        Parent root = loader.load();
//        Detail_proyekController detailController = loader.getController();
//
//        detailController.initData(proyekId);
//
//        Stage stage = new Stage();
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.setScene(new Scene(root));
//
//        stage.showAndWait();
//    }
//    
//    private boolean isProyekExist(int proyekId) {
//        Connection conn = DBHelper.getConnection();
//        String query = "SELECT * FROM `daftar_proyek` WHERE id = '" + proyekId + "'";
//        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)) {
//            return rs.next();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            return getDataProyekById(proyekId) != null;
//        }
//    }
//    
//    private void showNotFoundAlert() {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Proyek Tidak Ditemukan");
//        alert.setHeaderText(null);
//        alert.setContentText("Proyek dengan ID yang dicari tidak ditemukan.");
//        alert.showAndWait();
//    }
//    
//    private void showInvalidInputAlert() {
//        Alert alert = new Alert(AlertType.ERROR);
//        alert.setTitle("Input Tidak Valid");
//        alert.setHeaderText(null);
//        alert.setContentText("ID proyek harus berupa angka.");
//        alert.showAndWait();
//    }
//    
//    private Proyek getDataProyekById(int proyekId) {
//        Connection conn = DBHelper.getConnection();
//        String query = "SELECT * FROM `daftar_proyek` WHERE id = " + proyekId;
//        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)) {
//            if (rs.next()) {
//                Tim tim = new Tim();
//                return new Proyek(
//                        rs.getInt("id"),
//                        rs.getString("nama"),
//                        rs.getString("tujuan"),
//                        LocalDate.parse(rs.getString("tenggat_waktu")),
//                        rs.getBigDecimal("anggaran"),
//                        tim
//                );
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//    
//    
    
    
    
    
    


