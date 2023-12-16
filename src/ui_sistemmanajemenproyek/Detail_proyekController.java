package ui_sistemmanajemenproyek;

import db.DBHelper;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Detail_proyekController implements Initializable {

    @FXML
    private Label lblAnggaran;

    @FXML
    private Label lblDeadline;

    @FXML
    private Label lblId;

    @FXML
    private Label lblNama;

    @FXML
    private Label lblTujuan;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initData(int proyekId) {
        Proyek proyek = getDataProyekById(proyekId);

        lblId.setText(String.valueOf(proyek.getIdProyek()));
        lblNama.setText(proyek.getNamaProyek());
        lblTujuan.setText(proyek.getTujuanProyek());
        lblDeadline.setText(proyek.getDeadline().toString());
        lblAnggaran.setText(proyek.getAnggaranProyek().toString());
    }

    private Proyek getDataProyekById(int proyekId) {
        Connection conn = DBHelper.getConnection();
        String query = "SELECT * FROM `daftar_proyek` WHERE id = " + proyekId;
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                
//                return new Proyek(
//                        rs.getInt("id"),
//                        rs.getString("nama"),
//                        rs.getString("tujuan"),
//                        LocalDate.parse(rs.getString("tenggat_waktu")),
//                        rs.getBigDecimal("anggaran"),
//                        rs.getString("nama")
//                       
//                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
