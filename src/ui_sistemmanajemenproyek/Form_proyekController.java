package ui_sistemmanajemenproyek;

import db.DBHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Form_proyekController implements Initializable {

    @FXML
    private Button btnAgt;

    @FXML
    private Button btnJdwl;

    @FXML
    private Button btnMainMenu;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnTgs;

    @FXML
    private Button btnTim;

    @FXML
    private TextField fldAnggaran;

    @FXML
    private DatePicker fldDeadline;

    @FXML
    private TextField fldId;

    @FXML
    private TextField fldNama;

    @FXML
    private TextArea fldTujuan;

    @FXML
    private Label lblUsername;
    
    @FXML
    private Button btnHapusPyk;
    
    @FXML
    private Button btnHapusTim;
    
    @FXML
    private Button btnUpdateStatus;

    @FXML
    void goDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu_utama.fxml"));

        Stage stage = (Stage) btnMainMenu.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToFormAnggota(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("form_anggota.fxml"));

        Stage stage = (Stage) btnAgt.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToFormJadwal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("form_jadwal.fxml"));

        Stage stage = (Stage) btnJdwl.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToFormTim(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("form_tim.fxml"));

        Stage stage = (Stage) btnTim.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goToFormTugas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("form_tugas.fxml"));

        Stage stage = (Stage) btnTgs.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void insertData(ActionEvent event) {
        if (event.getSource().equals(btnTambah)) {
            tambahData();
            showInfoDialog("Berhasil", "Berhasil menambah data");
        }
    }
    
    @FXML
    void goToHapusProyek(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hapus_proyek.fxml"));

        Stage stage = (Stage) btnHapusPyk.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @FXML
    void goToHapusTim(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hapus_tim.fxml"));

        Stage stage = (Stage) btnHapusTim.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    
    @FXML
    void goToUpdateStatus(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("update_status.fxml"));

        Stage stage = (Stage) btnUpdateStatus.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String username = InformasiSesi.getUsername();
        lblUsername.setText(username);
    }

    private void tambahData() {
        try {
            String query = "INSERT INTO daftar_proyek (id, nama, tujuan, tenggat_waktu, anggaran) VALUES (?, ?, ?, ?, ?)";
            
            Connection conn = DBHelper.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, fldId.getText());
            preparedStatement.setString(2, fldNama.getText());
            preparedStatement.setString(3, fldTujuan.getText());
            preparedStatement.setString(4, fldDeadline.getValue().toString());
            preparedStatement.setString(5, fldAnggaran.getText());

            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            conn.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showInfoDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
