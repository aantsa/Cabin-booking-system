/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mokkivarausjarjestelma;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import static mokkivarausjarjestelma.ValidointiLuokka.textFieldIsNull;

/**
 *
 * @author alisa
 */
public class FXMLDocumentController implements Initializable {

    // RAPORTOINTIVALILEHDEN NAPIT
    @FXML
    private DatePicker dpAlkuPalRaps;

    @FXML
    private DatePicker dpLoppuPalRaps;

    @FXML
    private TextField tbToimintaAluePalRaps;

    @FXML
    private Button btnGeneroiPalRaps;

    @FXML
    private DatePicker dpAlkuMajRaps;

    @FXML
    private DatePicker dpLoppuMajRaps;

    @FXML
    private TextField tbToimintaAlueMajRaps;

    @FXML
    private Button btnGeneroiMajRaps;

    @FXML
    private TextArea taRaps;

    @FXML
    private ChoiceBox<String> choicePalRaps;

    @FXML
    private ChoiceBox<String> choiceMajRaps;

    @FXML
    private Button btnLataaRaps;

    // PALVELUVALILEHDEN NAPIT
    @FXML
    private Button btnHaeKaikkiPalvelut;

    @FXML
    private TextField txt_toimintaalueid;

    @FXML
    private TextField txt_palvelunnimi;

    @FXML
    private Button btn_lisaa;

    @FXML
    private Button btnlisaaAsiakas;

    @FXML
    private Button btnpostaPalvelut;

    @FXML
    private Button btnpoistaAsiakas;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_nimi;

    @FXML
    private TextField txt_tyyppi;

    @FXML
    private TextField txt_kuvaus;

    @FXML
    private TextField txt_hinta;

    @FXML
    private TextField txt_haeID;

    @FXML
    private TextField txt_alv;

    @FXML
    private TableView<Palvelut> table_palvelut;

    @FXML
    private TableColumn<Palvelut, Integer> col_id;

    @FXML
    private TableColumn<Palvelut, Integer> col_toimintaAlue;

    @FXML
    private TableColumn<Palvelut, String> col_nimi;

    @FXML
    private TableColumn<Palvelut, Integer> col_tyyppi;

    @FXML
    private TableColumn<Palvelut, String> col_kuvaus;

    @FXML
    private TableColumn<Palvelut, Double> col_hinta;

    @FXML
    private TableColumn<Palvelut, Double> col_alv;

    // ASIAKASVALILEHDEN NAMIKAT
    @FXML
    private TableView<Asiakas> table_asiakas;

    @FXML
    private TableColumn<Asiakas, Integer> col_asiakasid;

    @FXML
    private TableColumn<Asiakas, String> col_etunimi;

    @FXML
    private TableColumn<Asiakas, String> col_sukunimi;

    @FXML
    private TableColumn<Asiakas, String> col_hetu;

    @FXML
    private TableColumn<Asiakas, String> col_email;

    @FXML
    private TableColumn<Asiakas, String> col_lahiosoite;

    @FXML
    private TableColumn<Asiakas, String> col_postinumero;

    @FXML
    private TableColumn<Asiakas, String> col_puhelinnro;

    @FXML
    private TextField txt_tallennaetunimi;

    @FXML
    private TextField txt_tallennasukunimi;

    @FXML
    private TextField txt_tallennaasiakasid;

    @FXML
    private TextField txt_tallennalahiosoite;

    @FXML
    private TextField txt_tallennapostinumero;

    @FXML
    private TextField txt_tallennaemail;

    @FXML
    private TextField txt_tallennahetu;

    @FXML
    private Button btntallennamuutokset;

    @FXML
    private TextField txt_tallennapuhelinnro;

    @FXML
    private TextField txt_asiakasid;

    @FXML
    private TextField txt_sukunimi;

    @FXML
    private TextField txt_etunimi;

    @FXML
    private TextField txt_hetu;

    @FXML
    private Button btnHaeAsiakkaat;

    // HAKU- JA VARAUSVÄLILEHTI
    @FXML
    private ChoiceBox<String> ComboBoxEkaPalvelut;

    @FXML
    private ChoiceBox<String> ComboBoxEkaMajoittujat;

    @FXML
    private TextField txt_hvta;

    @FXML
    private TextField txt_hvkid;

    @FXML
    private DatePicker dphvAlku;

    @FXML
    private DatePicker dphvLoppu;

    @FXML
    private TableView<Mokki> tableviewEkasivu;

    @FXML
    private TableColumn<Mokki, Integer> columnId;

    @FXML
    private TableColumn<Mokki, String> columnNimi;

    @FXML
    private TableColumn<Mokki, String> columnOsoite;

    @FXML
    private TableColumn<Mokki, String> columnPostinro;

    @FXML
    private TableColumn<Mokki, String> columnToiminta;

    @FXML
    private TableColumn<Mokki, Integer> columnHlo;

    @FXML
    private TableColumn<Mokki, Double> columnHinta;

    @FXML
    private Label lblMokinNimi;

    @FXML
    private Label lblMokkiToimipaikka;

    @FXML
    private Label lblMokinOsoite;

    @FXML
    private Label lblKohdeID;

    @FXML
    private Label lblPostinro;

    @FXML
    private Label lblMokinHinta;

    @FXML
    private TextArea taHakuJaVaraus;

    //VARAUKSENTEON NAPIT HAKU- JA VARAUSVÄLILEHDELLE
    @FXML
    private DatePicker dpVarauksenAlku;

    @FXML
    private DatePicker dpVarauksenLoppu;

    @FXML
    private DatePicker dpVarauksenTekopvm;

    @FXML
    private TextField tbEtunimiEtusivu;

    @FXML
    private TextField tbSukunimiEtusivu;

    @FXML
    private TextField tbEmailEtusivu;

    @FXML
    private TextField tbPuhnroEtusivu;

    @FXML
    private TextField tbHetuEtusivu;

    @FXML
    private TextField tbAsiakasIDEtusivu;

    @FXML
    private TextField tbPostinroEtuSivu;

    @FXML
    private TextField tbOsoiteEtusivu;

    @FXML
    private Button btnVaraaHAKU;

    @FXML
    private Button btnRekisteroiAsiakas;

    // VARAUKSIENHALLINTA JA LASKUTUS
    @FXML
    private TableView<Varaus> tableviewVarauksienH;

    @FXML
    private TableColumn<Varaus, Integer> columnMokkiIDvar;

    @FXML
    private TableColumn<Varaus, Integer> columnVarausnrovar;

    @FXML
    private TableColumn<Varaus, Integer> columnVaraajavar;

    @FXML
    private TableColumn<Varaus, Double> columnSumma;

    @FXML
    private Label lblkohdeIDVAR;

    @FXML
    private Label lblKohteenNimiVAR;

    @FXML
    private Label lblKohteenOsoiteVAR;

    @FXML
    private Label lblPostinumeroVAR;

    @FXML
    private Label lblHintaVAR;

    @FXML
    private Label lblVarausIDVAR;

    @FXML
    private DatePicker dpVuokrausAlkupvm;

    @FXML
    private DatePicker dpVuokrausLoppupvm;

    @FXML
    private Button btnPeruutaVaraus;

    @FXML
    private Button btnLahetaTulostaLasku;

    @FXML
    private TextField tbPostinumeroVarHallinta;

    @FXML
    private Label lblEtunimiVAR;

    @FXML
    private Label lblSukunimiVAR;

    @FXML
    private CheckBox cbUlkomaalainenVaraaja;

    @FXML
    private TextField tbPostitoimipaikkaVarHallinta;

    @FXML
    private CheckBox cbPaperilasku;

    @FXML
    private CheckBox cbSahkoinenLasku;

    @FXML
    private TextField tbasiakasIdVar;

    // MOKKIEN MUOKKAUS
    
    @FXML
    private Button tyhjennaomistajakentat;
    
    @FXML
    private Button tyhjennamokkikentat;
    
    @FXML
    private Button btnHaeMokki;
        
    @FXML
    private TextField txt_KohteenNimi;
    
    @FXML
    private TableView<Mokkimuokkaus> textviewMuokkaamokkeja;
    
    @FXML
    private TableColumn<Mokkimuokkaus, Integer> colummokki_id;
    
    @FXML
    private TableColumn<Mokkimuokkaus, String> columkohteennimi;
    
    @FXML
    private TableColumn<Mokkimuokkaus, Integer> columtoimipaikkaaa;
    
    @FXML
    private TableColumn<Mokkimuokkaus, Integer> columomistaja;
    
    @FXML
    private TextField Mtbkohdeid;
    
    @FXML
    private TextField Mtbmokinnimi;
    
    @FXML
    private TextField Mtbosoite;
    
    @FXML
    private TextField Mtbpostinumero;
    
    @FXML
    private TextField Mtbhinta;
    
    @FXML
    private TextField Mtbkapasiteetti;
    
    @FXML
    private TextField Mtbtoimintaalue;
    
    @FXML
    private TextField Mtbomistajaid;
    
    @FXML
    private TextArea Mkuvaus;
    
    @FXML
    private TextField tbetunimi;
    
    @FXML
    private TextField tbsukunimi;
    
    @FXML
    private TextField tbosoite;
    
    @FXML
    private TextField tbemail;
    
    @FXML
    private TextField tbpuhnro;
    
    @FXML
    private Button lisaaomistaja;
    
    @FXML
    private TableView<Mokinomistaja> tvomistaja;
    
    @FXML
    private TableColumn<Mokinomistaja, Integer> omistajacolumid;
    
    @FXML
    private TableColumn<Mokinomistaja, String> omistajacolumetunimi;
    
    @FXML
    private TableColumn<Mokinomistaja, String> omistajacolumsukunimi;
    
    @FXML
    private Button buttonlisaamokki;
    
    @FXML
    private Button mokkilistanpaivitys;

    
///  ZEINEBIN MOKIN LISAYS JA MUOKKAUS
    ObservableList<Mokkimuokkaus> listMokMuok;
    ObservableList<Mokkimuokkaus> dataListMokMuok = FXCollections.observableArrayList();

    //MÖKKI LISÄTÄÄN LISTAAN NÄKYVILLE (ZV)
    public void lisaamokkiMokkilistaan() {

        colummokki_id.setCellValueFactory(new PropertyValueFactory<Mokkimuokkaus, Integer>("mokkiid"));
        columkohteennimi.setCellValueFactory(new PropertyValueFactory<Mokkimuokkaus, String>("mokkinimi"));
        columtoimipaikkaaa.setCellValueFactory(new PropertyValueFactory<Mokkimuokkaus, Integer>("toimitntaauleid"));
        columomistaja.setCellValueFactory(new PropertyValueFactory<Mokkimuokkaus, Integer>("mokinomistajanid"));

        listMokMuok = database.getMokkimuokkaus();
        textviewMuokkaamokkeja.setItems(listMokMuok);

        dataListMokMuok.addAll(listMokMuok);
    }
    
    // TYHJENTAA MOKKIVALILEHDEN KENTAT (ZV)
    @FXML 
        void tyhjenna() throws SQLException {
        Mtbkohdeid.clear();
        Mtbmokinnimi.clear();
        Mtbosoite.clear();;
        Mtbpostinumero.clear();
        Mtbhinta.clear();
        Mtbkapasiteetti.clear();
        Mtbtoimintaalue.clear();
        Mtbomistajaid.clear();
        Mkuvaus.clear();
        txt_KohteenNimi.clear();
        lisaamokkiMokkilistaan();
   }
    // TYHJENTAA OMISTAJA VALILEHDEN KENTAT (ZV)
    @FXML
    void tyhjennaomistaja() throws SQLException{
       tbetunimi.clear();
       tbsukunimi.clear();
       tbosoite.clear();
       tbemail.clear();
       tbpuhnro.clear();
    } 
    
    
    // HAKUPAINIKE (AF)
    public void hae_mokki() throws SQLException {

        colummokki_id.setCellValueFactory(new PropertyValueFactory <Mokkimuokkaus, Integer>("mokkiid"));
        columkohteennimi.setCellValueFactory(new PropertyValueFactory<Mokkimuokkaus, String>("mokkinimi"));
        columtoimipaikkaaa.setCellValueFactory(new PropertyValueFactory<Mokkimuokkaus, Integer>("toimitntaauleid"));
        columomistaja.setCellValueFactory(new PropertyValueFactory <Mokkimuokkaus, Integer>("mokinomistajanid"));

        listMokMuok = database.haeDataMokit(txt_KohteenNimi.getText());
        textviewMuokkaamokkeja.setItems(listMokMuok);

        dataListMokMuok.addAll(listMokMuok);
    }
    
    //TUO VALITUN MÖKIN KAIKKI TIEDOT MUOKATTAVAKSI (ZV)
    @FXML
    public void valittusiirto() {
        try {
            Mokkimuokkaus valittuMokkimuokkaus = textviewMuokkaamokkeja.getSelectionModel().getSelectedItem();
            Mtbkohdeid.setText(Integer.toString(valittuMokkimuokkaus.getMokkiid()));
            Mtbmokinnimi.setText(valittuMokkimuokkaus.getMokkinimi());
            Mtbosoite.setText(valittuMokkimuokkaus.getKatuosoite());
            Mtbpostinumero.setText(valittuMokkimuokkaus.getPostinumero());
            Mtbhinta.setText(valittuMokkimuokkaus.getHinta());
            Mtbkapasiteetti.setText(Integer.toString(valittuMokkimuokkaus.getHenkilomaara()));
            Mtbtoimintaalue.setText(Integer.toString(valittuMokkimuokkaus.getToimitntaauleid()));
            Mtbomistajaid.setText(Integer.toString(valittuMokkimuokkaus.getMokinomistajanid()));
            Mkuvaus.setText(valittuMokkimuokkaus.getKuvaus());
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, "Jotain meni pieleen");
        }
    }

    @FXML
    public void palveluidenValinta() {
        try {
            Palvelut palvelutOnJees = table_palvelut.getSelectionModel().getSelectedItem();
            txt_id.setText(Integer.toString(palvelutOnJees.getId()));
            txt_nimi.setText(palvelutOnJees.getNimi());
            txt_kuvaus.setText(palvelutOnJees.getKuvaus());
            txt_alv.setText(Double.toString(palvelutOnJees.getAlv()));
            txt_toimintaalueid.setText(Integer.toString(palvelutOnJees.getToimintaAlueID()));
            txt_tyyppi.setText(Integer.toString(palvelutOnJees.getTyyppi()));
            txt_hinta.setText(Double.toString(palvelutOnJees.getHinta()));
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, "Jotain meni pieleen");
        }
    }
    //MÖKIN LISÄÄMINEN TIETOKANTAAN (ZV)
    @FXML
    void lisaamokki() throws SQLException {
        String sgl = "INSERT INTO `mokki` (`mokki_id`, `toimintaalue_id`, `postinro`, `mokkinimi`, `katuosoite`, `kuvaus`, `henkilomaara`, `mokinOmistaja_idmokinOmistaja`, `hinta`)VALUES(?,?,?,?,?,?,?,?,?)";
        Integer mokki_id = Integer.parseInt(Mtbkohdeid.getText());
        Integer toimintaalue_id = Integer.parseInt(Mtbtoimintaalue.getText());
        String postinro = Mtbpostinumero.getText();
        String mokkinimi = Mtbmokinnimi.getText();
        String katuosoite = Mtbosoite.getText();
        String kuvaus = Mkuvaus.getText();
        Integer henkilomaara = Integer.parseInt(Mtbkapasiteetti.getText());
        Integer mokinOmistaja_idmokinOmistaja = Integer.parseInt(Mtbomistajaid.getText());
        String hinta = Mtbhinta.getText();
        try {
            pst = con.prepareStatement(sgl);
            pst.setInt(1, mokki_id);
            pst.setInt(2, toimintaalue_id);
            pst.setString(3, postinro);
            pst.setString(4, mokkinimi);
            pst.setString(5, katuosoite);
            pst.setString(6, kuvaus);
            pst.setInt(7, henkilomaara);
            pst.setInt(8, mokinOmistaja_idmokinOmistaja);
            pst.setString(9, hinta);

            int i = pst.executeUpdate();
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Mökki lisätty onnistuneesti.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pst.close();
        }
        lisaamokkiMokkilistaan();
        Mtbkohdeid.clear();
        Mtbmokinnimi.clear();
        Mtbosoite.clear();;
        Mtbpostinumero.clear();
        Mtbhinta.clear();
        Mtbkapasiteetti.clear();
        Mtbtoimintaalue.clear();
        Mtbomistajaid.clear();
        Mkuvaus.clear();
    }
    //MÖKIN MUUTOSTEN MUOKKAAMISEN TALLENTAMINEN ELI PÄIVITTÄMINEN KANTAAN (ZV)
    @FXML
    private Button tallennamuutokset;

    public void tallennamuutoksia() throws SQLException {
        Mokkimuokkaus valittuMokkimuokkaus = textviewMuokkaamokkeja.getSelectionModel().getSelectedItem();
        String sql = "UPDATE mokki SET mokki_id = '" + Mtbkohdeid.getText() + "', toimintaalue_id = '" + Mtbtoimintaalue.getText() + "', postinro = '" + Mtbpostinumero.getText() + "', mokkinimi = '" + Mtbmokinnimi.getText()
                + "', katuosoite = '" + Mtbosoite.getText() + "', kuvaus = '" + Mkuvaus.getText() + "', henkilomaara = '" + Mtbkapasiteetti.getText() + "', mokinOmistaja_idmokinOmistaja = '" + Mtbomistajaid.getText()
                + "', hinta = '" + Mtbhinta.getText() + "' WHERE mokki_id = " + Mtbkohdeid.getText();
        try {
            database.dbMuokkaus(sql);
            JOptionPane.showMessageDialog(null, "Mökin tiedot päivitetty.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kentät ovat joko puutteelliset tai virheelliset");
        }
        lisaamokkiMokkilistaan();

        Mtbkohdeid.clear();
        Mtbmokinnimi.clear();
        Mtbosoite.clear();;
        Mtbpostinumero.clear();
        Mtbhinta.clear();
        Mtbkapasiteetti.clear();
        Mtbtoimintaalue.clear();
        Mtbomistajaid.clear();
        Mkuvaus.clear();
    }
    
    //MÖKIN LISTAN PÄIVITTÄMINEN JOS UUSI MÖKKI EI PÄIVITY AUTOMAATTISESTI (ZV)
    public void paivitamokkilista() throws SQLException {
        lisaamokkiMokkilistaan();
    }

//MÖKINOMISTAJAN LISÄÄMINEN TIETOKANTAAN (ZV)
    Connection con = null;
    PreparedStatement pst = null;

    @FXML
    void handleButtonAction() throws SQLException {
        String sql = "INSERT INTO `mokinomistaja` (`etunimi`, `sukunimi`, `email`, `lahiosoite`, `puhelinnumero`)VALUES(?,?,?,?,?)";
        String etunimi = tbetunimi.getText();
        String sukunimi = tbsukunimi.getText();
        String email = tbemail.getText();
        String lahiosoite = tbosoite.getText();
        String puhelinnumero = tbpuhnro.getText();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, etunimi);
            pst.setString(2, sukunimi);
            pst.setString(3, email);
            pst.setString(4, lahiosoite);
            pst.setString(5, puhelinnumero);

            int i = pst.executeUpdate();
            if (i == 1) {
                System.out.println("Uusi mökin omistaja lisätty onnistuneesti.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pst.close();
        }
        tbetunimi.clear();
        tbsukunimi.clear();
        tbosoite.clear();
        tbemail.clear();
        tbpuhnro.clear();
    }

    ObservableList<Mokinomistaja> listOm;
    ObservableList<Mokinomistaja> dataListOm = FXCollections.observableArrayList();

    //MÖKINOMISTAJIEN LISÄÄMINEN TAULUUN (ZV)
    public void lisaaMokinomistajattauluun() {
        omistajacolumid.setCellValueFactory(new PropertyValueFactory<Mokinomistaja, Integer>("idmokinOmistaja"));
        omistajacolumetunimi.setCellValueFactory(new PropertyValueFactory<Mokinomistaja, String>("etunimi"));
        omistajacolumsukunimi.setCellValueFactory(new PropertyValueFactory<Mokinomistaja, String>("sukunimi"));

        listOm = database.getMokinomistajat();
        tvomistaja.setItems(listOm);

        dataListOm.addAll(listOm);
    }

    //MÖKINOMISTAJAN VALITSEMINEN TAULUSTA JA TIETOJEN NÄYTTÄMINEN TEKSTIKENTISSÄ
    @FXML
    public void valitunrivinsiirto() {
        try {
            Mokinomistaja valittuOmistaja = tvomistaja.getSelectionModel().getSelectedItem();
            tbetunimi.setText(valittuOmistaja.getEtunimi());
            tbsukunimi.setText(valittuOmistaja.getSukunimi());
            tbosoite.setText(valittuOmistaja.getLahiosoite());
            tbemail.setText(valittuOmistaja.getEmail());
            tbpuhnro.setText(valittuOmistaja.getPuhelinnumero());
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, "Jotain meni pieleen");
        }
    }

    @FXML
    private Button buttontallennamuutokset;

    //OMISTAJAN TIETOJEN MUOKKAAMINEN
    public void tallennamuutokset() throws SQLException {
        Mokinomistaja valittuOmistaja = tvomistaja.getSelectionModel().getSelectedItem();
        String sql = "UPDATE mokinomistaja SET etunimi = '" + tbetunimi.getText() + "', sukunimi = '" + tbsukunimi.getText() + "', email = '" + tbemail.getText() + "', lahiosoite = '" + tbosoite.getText() + "', puhelinnumero = '" + tbpuhnro.getText() + "' WHERE idmokinOmistaja = " + valittuOmistaja.getIdmokinOmistaja();
        try {
            database.dbMuokkaus(sql);
            JOptionPane.showMessageDialog(null, "Omistajan tiedot päivitetty");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kentät ovat joko puutteelliset tai virheelliset");
        }
        lisaaMokinomistajattauluun();
        tbetunimi.clear();
        tbsukunimi.clear();
        tbosoite.clear();
        tbemail.clear();
        tbpuhnro.clear();
    }
    //KUN LISÄÄ UUDEN KÄYTTÄJÄN NIIN PÄIVITTÄÄ LISTAN

    public void paivitalista() throws SQLException {
        lisaaMokinomistajattauluun();
    }

    @FXML
    private Button buttonpoistaomistaja;

    @FXML
    void poista_omistaja() throws SQLException {

        con = database.ConnectDb();
        // Mokinomistaja valittuOmistaja = tvomistaja.getSelectionModel().getSelectedItem();
        String sql = "delete from mokki WHERE mokki_id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, Mtbkohdeid.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Omistaja poistettu onnistuneesti.");
            lisaaMokinomistajattauluun();
            lisaamokkiMokkilistaan();
            // search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Virhe!");

        }

        Mtbkohdeid.clear();
        Mtbmokinnimi.clear();
        Mtbosoite.clear();;
        Mtbpostinumero.clear();
        Mtbhinta.clear();
        Mtbkapasiteetti.clear();
        Mtbtoimintaalue.clear();
        Mtbomistajaid.clear();
        Mkuvaus.clear();
    }


// SIIRRETAAN KANNASTA MOKIT HAKU- JA VARAUSVALILEHDEN TABLEEN (AR)
    ObservableList<Mokki> listMo;
    ObservableList<Mokki> dataListMo = FXCollections.observableArrayList();

    public void lisaaMokitTableen() {

        columnId.setCellValueFactory(new PropertyValueFactory<Mokki, Integer>("id"));
        columnNimi.setCellValueFactory(new PropertyValueFactory<Mokki, String>("nimi"));
        columnOsoite.setCellValueFactory(new PropertyValueFactory<Mokki, String>("osoite"));
        columnPostinro.setCellValueFactory(new PropertyValueFactory<Mokki, String>("postinro"));
        columnToiminta.setCellValueFactory(new PropertyValueFactory<Mokki, String>("paikkakunta"));
        columnHlo.setCellValueFactory(new PropertyValueFactory<Mokki, Integer>("henkilomaara"));
        columnHinta.setCellValueFactory(new PropertyValueFactory<Mokki, Double>("hinta"));

        listMo = database.getMokit();
        tableviewEkasivu.setItems(listMo);

        dataListMo.addAll(listMo);
    }

    // TAMA OLI HATAISEEN LUOTU TOTEUTUS VARAAMISELLE, ENSIN REKISTEROIDAAN ASIAKAS JA JOS TIEDETAAN, ETTA ASIAKAS ON JO OLEMASSA,
    // NIIN TARVITTAVAT SYOTTEET OVAT HETU JA AJANKOHDAT
    // ELI ENSIN VALITAAN MOKKI LISTALTA
    // UUDEN ASIAKKAAN KOHDALLA JOUDUTAAN TAYTTAMAAN KAIKKI KENTAT JA REKISTEROITAVA ASIAKAS REKISTEROI NAPISTA JA SITTEN PAIVAT JA CLICK VARAA
    // JOS VANHA ASIAKAS NIIN: VALITSE MOKKI, SYOTA HETU JA PVM:T JA PAINA VARAA
    // ALISAN VARAUSVALILEHTI
    @FXML
    void rekisteroiAsiakas() {

        String sql = "INSERT INTO asiakas (etunimi, sukunimi, hetu_ytun, puhelinnro, email, lahiosoite, postinro) "
                + "VALUES ('" + tbEtunimiEtusivu.getText() + "', '" + tbSukunimiEtusivu.getText() + "', '" + tbHetuEtusivu.getText()
                + "', '" + tbPuhnroEtusivu.getText() + "', '" + tbEmailEtusivu.getText() + "', '" + tbOsoiteEtusivu.getText() + "', '" + tbPostinroEtuSivu.getText() + "')";

        try {

            database.dbMuokkaus(sql);

            JOptionPane.showMessageDialog(null, "Asiakas rekisteröity onnistuneesti.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Virhe!");
        }
        UpdateTableAsiakas();

    }

    @FXML
    void varaa_Clicked() {

        String varattu = dpVarauksenTekopvm.getValue().toString();
        String alku = dpVarauksenAlku.getValue().toString();
        String loppu = dpVarauksenLoppu.getValue().toString();

        String sql = "INSERT INTO varaus (asiakas_id, mokki_mokki_id, varattu_alkupvm, varattu_loppupvm, varattu_pvm)"
                + " VALUES ((SELECT asiakas_id FROM asiakas WHERE hetu_ytun = '" + tbHetuEtusivu.getText() + "'), "
                + "(SELECT mokki_id FROM mokki WHERE mokkinimi = '" + lblMokinNimi.getText() + "'), " + "'" + alku + "', '" + loppu
                + "', '" + varattu + "')";

        try {

            database.dbMuokkaus(sql);

            JOptionPane.showMessageDialog(null, "Varaus tehty onnistuneesti.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Virhe.");
        }
        UpdateTableVaraukset();
    }

// TASSA SIIRRETAAN HAKU JA VARAUS VALILEHDEN TABLEVIEW:N TIEDOT HAKU- JA VARAUSVALILEHDEN LABELEIHIN (AR)
    @FXML
    public void siirraValittuRivi() {
        try {

            String kuvaus = "";
            Mokki valittuMokki = tableviewEkasivu.getSelectionModel().getSelectedItem();
            kuvaus = database.getKuvaus(valittuMokki.getId());

            lblKohdeID.setText(Integer.toString(valittuMokki.getId()));
            lblMokinNimi.setText(valittuMokki.getNimi());
            lblMokinOsoite.setText(valittuMokki.getOsoite());
            lblPostinro.setText(valittuMokki.getPostinro());
            lblMokkiToimipaikka.setText(valittuMokki.getPaikkakunta());
            lblMokinHinta.setText(Double.toString(valittuMokki.getHinta()));
            taHakuJaVaraus.setText(kuvaus);

        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, "Jotain meni pieleen");
        }
    }

    // KUTSUTAAN YLLAOLEVAA (AR)
    @FXML
    void siirraValittuRiviClick() {
        siirraValittuRivi();
    }

    // TASTA ALKAA VARAUKSIENHALLINTA VALILEHDEN KOODIT (AR)
    
    ObservableList<Varaus> listV;
    private final ObservableList<Varaus> dataListV = FXCollections.observableArrayList();
    // TUODAAN VARAUKSET TABLEVIEWHN
    public void UpdateTableVaraukset() {

        columnMokkiIDvar.setCellValueFactory(new PropertyValueFactory<Varaus, Integer>("mokki_id"));
        columnVarausnrovar.setCellValueFactory(new PropertyValueFactory<Varaus, Integer>("varaus_id"));
        columnVaraajavar.setCellValueFactory(new PropertyValueFactory<Varaus, Integer>("asiakas_id"));
        columnSumma.setCellValueFactory(new PropertyValueFactory<Varaus, Double>("lasku_summa"));

        listV = database.getVaraukset();
        tableviewVarauksienH.setItems(listV);
        dataListV.addAll(listV);

    }

    // VARAUKSEN MUOKKAUS(AR)
    @FXML
    void siirraValittuVarausMuokkaukseen() {

        try {

            Varaus valittuVaraus = tableviewVarauksienH.getSelectionModel().getSelectedItem();

            lblkohdeIDVAR.setText(Integer.toString(valittuVaraus.getMokki_id()));
            tbasiakasIdVar.setText(Integer.toString(valittuVaraus.getAsiakas_id()));

        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, "Jotain meni pieleen");
        }

    }

    // LASKUTUS
    /*  @FXML
    void lahetaTulostaClicked(){
       
        // vahvistuspvm on se pvm kun laskutetaan 
        //Varattu se kun varaus on tehty
        
    }*/
    

    // SEURAAVAT NELJA METODIA OVAT RAPORTOINTIVALILEHTEEN (AR)
    // Generoi majoittumisten-/varauksien maara(kpl) ajanjaksolla -nappi
    // Siirretaan tiedot tietokannasta valilehden textAreaan (AR)
    @FXML
    void generoiMajoittumisRaportti() throws SQLException, ClassNotFoundException {

        String toimintaAlue;
        String alku;
        String loppu;

        // Otetaan ylos toiminta-alue, alkupvm ja loppupvm
        toimintaAlue = choiceMajRaps.getValue();
        alku = dpAlkuMajRaps.getValue().toString();
        loppu = dpLoppuMajRaps.getValue().toString();

        // Laitetaan yllamainitut parametrina databasefunktioon getMajoittumiset(); ja otetaan
        // palautuva COUNT ylos
        int haunTulos = database.getMajoittumiset(toimintaAlue, alku, loppu);

        // Asetetaan paluuarvot textAreaan "Raporttimuodossa"
        taRaps.setText("Toiminta-alueella " + toimintaAlue + " on ajanjaksolla " + alku + " -- " + loppu + "\n"
                + "tehty varauksia yhteensä " + Integer.toString(haunTulos) + " kappaletta.");
    }

    // Sama kuin ylla, mutta tulos on ostettujen palveluiden maara euroissa seka alvin maara (AR)
    @FXML
    void generoiPalveluRaportti() {

        // Luodaan muuttujat, johon asetetaan textboxien ja datepickereiden tiedot, 
        // seka tehdaan apumuutujat joilla saadaan hinnat kohdilleen
        String toimintaAlue = "";
        String alku = "";
        String loppu = "";
        double hintaTrack = 0.00;
        double alvTrack = 0.00;

        // Lista<Palvelut> johon database-funktion getPalvelutEuroina() paluuarvot tallentuvat
        ObservableList<Palvelut> palveluidenEurot = FXCollections.observableArrayList();

        // Otetaan arvot talteen
        toimintaAlue = choicePalRaps.getValue();
        alku = dpAlkuPalRaps.getValue().toString();
        loppu = dpLoppuPalRaps.getValue().toString();

        // Viedaan arvot parametrina databasfunktioon getPalvelutEuroina() ja otetaan ne listaan
        palveluidenEurot = database.getPalvelutEuroina(toimintaAlue, alku, loppu);

        // Kaydaan lista lapi
        for (Palvelut p : palveluidenEurot) {
            hintaTrack = hintaTrack + p.getHinta();
            alvTrack = alvTrack + p.getAlv();
        }

        // Asetetaan raportinomainen teksti TextAreaan
        taRaps.setText("Toiminta-alueella " + toimintaAlue + " on ajanjaksolla " + alku + " -- " + loppu + "\n"
                + "ostettu palveluita yhteensä " + Double.toString(hintaTrack) + " eurolla ja" + "\n"
                + "niiden arvonlisävero on yhteensä " + Double.toString(alvTrack) + " euroa.");
    }

    // Lataa-nappi Raportointi valilehdella, tasta saadaan tekstitiedosto, jossa
    // TextArean teksti.
    @FXML
    void lataaRaps() {

        // Jos olisi jaanyt enemman aikaa, olisin halunnut kehittaa tata
        //  siten, etta luodaan aina uusi raportti ja jarkevalla, paljon fiinimmalla pohjalla (AR)
        // Luotavan raportin nimi
        String filu = "raportti.txt";
        // Teksti joka sinne dumpataan
        String raportti = taRaps.getText();

        try {
            // Luodaan writer
            PrintWriter kirjoittaja = new PrintWriter(filu);
            kirjoittaja.println(raportti);
            // Suljetaan wrtiter, koska muuten raportti on blanco (RAM-issue)
            kirjoittaja.close();
            JOptionPane.showMessageDialog(null, "Raportti valmis.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ongelmia lataamisessa. Ota yhteys IT-tukeen.");
        }
    }

    // TASSA LISATAAN RAPORTOINTIVALILEHDEN CHOICEBOXEIHIN TOIMINTA-ALUEET 
    // KUTSUTAAN INITIALIZESSA (AR)
    @FXML
    public void lisaaChoiceBoxiin() {

        // Luodaan List<ToimintaAlue>
        ObservableList<ToimintaAlue> taLista = FXCollections.observableArrayList();

        //Kutsutaan funktiota databasesta, joka hakee kaikki kannan toiminta-alueet, ja lisaa ne listaan
        taLista = database.getChoiceBoxValues();

        // Kaydaan lista lapi ja addataan ne boxeihin
        for (ToimintaAlue ta : taLista) {
            choicePalRaps.getItems().add(ta.getNimi());
            choiceMajRaps.getItems().add(ta.getNimi());
        }

    }

    // AUTOFILL VARAUKSIEN HALLINTAAN. TAYTTAA POSTITOIMIPAIKAN POSTINUMERON PERUSTEELLA. TAMA TOIMII, MUU SIVU EI
    @FXML
    void autoFill() throws ClassNotFoundException, SQLException {

        // Otetaan postinumero ylos
        String nro = tbPostinumeroVarHallinta.getText();
        // Luodaan Lista<Posti>
        ObservableList<Posti> postit = FXCollections.observableArrayList();

        // JOS VARAAJA ON ULKOMAALAINEN NIIN KANNATTAA LAITTAA POSTINRO TOIMIPAIKKAAN, KOSKA POSTINRO CHAR(5)
        if (cbUlkomaalainenVaraaja.isSelected()) {
            JOptionPane j = new JOptionPane();
            JOptionPane.showMessageDialog(null, "Huomaathan, että varaaja on ulkomailta, postinumero kannattaa tallentaa postitoimipaikkaan."); // Koska database postinro on char(5)
        } // Jos Numero on liian lyhyt ja varaaja on suomalaisella osoitteella varustettu, niin ilmoitus
        else if (tbPostinumeroVarHallinta.getText().length() < 5 && cbUlkomaalainenVaraaja.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Postinro liian lyhyt!");
        } // Jos Numero on liian pitka ja varaaja on suomalaisella osoitteella varustettu, niin ilmoitus
        else if (tbPostinumeroVarHallinta.getText().length() > 5 && cbUlkomaalainenVaraaja.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Postinro liian pitkä!");
        } // Haetaan databasesta postinumeroa vastaava toimipaikka
        else {
            postit = database.getPostit(nro);
            for (Posti p : postit) {
                tbPostitoimipaikkaVarHallinta.setText(p.getPostinumero());
            }
        }
    }

    // TASSA TOTEUTETAAN HAKUA (AF)
    private final ObservableList<Palvelut> dataListP = FXCollections.observableArrayList();

    /// PALVELUN LISAAMINEN TIETOKANTAAN (AF)
    public void Lisaa_palvelu() throws SQLException {

        Connection conn = database.ConnectDb();
        String sql = "insert into palvelu (palvelu_id, toimintaalue_id, nimi, tyyppi, kuvaus, hinta, alv)values(?,?,?,?,?,?,?);";
        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, Integer.parseInt(txt_id.getText()));
            pst.setInt(2, Integer.parseInt(txt_toimintaalueid.getText()));
            pst.setString(3, txt_nimi.getText());
            pst.setInt(4, Integer.parseInt(txt_tyyppi.getText()));
            pst.setString(5, txt_kuvaus.getText());
            pst.setDouble(6, Double.parseDouble(txt_hinta.getText()));
            pst.setDouble(7, Double.parseDouble(txt_alv.getText()));
            pst.executeUpdate();
            pst.close();
            JOptionPane.showMessageDialog(null, "Palvelu lisätty");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kentät ovat joko puutteelliset tai virheelliset");
        }
        UpdateTablePalvelut();
    }

    public int index = 0;
    ObservableList<Palvelut> listM;

    // PALVELUIDEN PAIVITTAMINEN TABLEEN (AF)
    public void UpdateTablePalvelut() {

        col_id.setCellValueFactory(new PropertyValueFactory<Palvelut, Integer>("id"));
        col_toimintaAlue.setCellValueFactory(new PropertyValueFactory<Palvelut, Integer>("toimintaAlueID"));
        col_nimi.setCellValueFactory(new PropertyValueFactory<Palvelut, String>("nimi"));
        col_tyyppi.setCellValueFactory(new PropertyValueFactory<Palvelut, Integer>("tyyppi"));
        col_kuvaus.setCellValueFactory(new PropertyValueFactory<Palvelut, String>("kuvaus"));
        col_hinta.setCellValueFactory(new PropertyValueFactory<Palvelut, Double>("hinta"));
        col_alv.setCellValueFactory(new PropertyValueFactory<Palvelut, Double>("alv"));

        listM = database.getDatapalvelut();
        table_palvelut.setItems(listM);

        dataListP.addAll(listM);
        index++;
    }

    //Hakee valitun palvelun (AF) 
    public void hae_palvelu() throws SQLException {
        col_id.setCellValueFactory(new PropertyValueFactory<Palvelut, Integer>("id"));
        col_toimintaAlue.setCellValueFactory(new PropertyValueFactory<Palvelut, Integer>("toimintaAlueID"));
        col_nimi.setCellValueFactory(new PropertyValueFactory<Palvelut, String>("nimi"));
        col_tyyppi.setCellValueFactory(new PropertyValueFactory<Palvelut, Integer>("tyyppi"));
        col_kuvaus.setCellValueFactory(new PropertyValueFactory<Palvelut, String>("kuvaus"));
        col_hinta.setCellValueFactory(new PropertyValueFactory<Palvelut, Double>("hinta"));
        col_alv.setCellValueFactory(new PropertyValueFactory<Palvelut, Double>("alv"));

        listM = database.haeDatapalvelut(txt_palvelunnimi.getText());
        table_palvelut.setItems(listM);

        dataListP.addAll(listM);
        index++;

    }

    // PALVELUIDEN POISTO (AF)
    @FXML
    void poista_palvelu() throws SQLException {

        Connection conn = database.ConnectDb();
        String sql = "delete from palvelu where palvelu_id = ?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txt_id.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Valittu palvelu poistettu");
            UpdateTablePalvelut();

            // search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Valitse poistettava palvelu");
        }
        txt_id.clear();
        txt_toimintaalueid.clear();
        txt_nimi.clear();
        txt_tyyppi.clear();
        txt_kuvaus.clear();
        txt_hinta.clear();
        txt_alv.clear();

    }

    ObservableList<Asiakas> listA;
    private final ObservableList<Asiakas> dataListA = FXCollections.observableArrayList();

    //Päivittää asiakas taulun (AF)
    public void UpdateTableAsiakas() {
        col_asiakasid.setCellValueFactory(new PropertyValueFactory<Asiakas, Integer>("asiakasid"));
        col_etunimi.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("etunimi"));
        col_sukunimi.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("sukunimi"));
        col_hetu.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("heTu"));
        col_email.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("email"));
        col_lahiosoite.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("lahiosoite"));
        col_postinumero.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("postinumero"));
        col_puhelinnro.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("puhelinnro"));

        listA = database.getDataAsiakkaat();
        table_asiakas.setItems(listA);

        dataListA.addAll(listA);
    }

    //Poistaa valitun asiakkaan (AF)    
    @FXML
    void poista_asiakas() throws SQLException {
        Connection conn = database.ConnectDb();
        String sql = "delete from asiakas where asiakas_id = ?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txt_tallennaasiakasid.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Asiakkaan tiedot poistettu");
            UpdateTableAsiakas();

            // search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Valitse poistettava henkilö");
        }
        txt_tallennaasiakasid.clear();
        txt_tallennapostinumero.clear();
        txt_tallennaetunimi.clear();
        txt_tallennasukunimi.clear();
        txt_tallennalahiosoite.clear();
        txt_tallennaemail.clear();
        txt_tallennapuhelinnro.clear();
        txt_tallennahetu.clear();

    }

    //Hakee valitun asiakkaan (AF)
    @FXML
    void hae_Asiakkaat() {
        col_asiakasid.setCellValueFactory(new PropertyValueFactory<Asiakas, Integer>("asiakasid"));
        col_etunimi.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("etunimi"));
        col_sukunimi.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("sukunimi"));
        col_hetu.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("heTu"));
        col_email.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("email"));
        col_lahiosoite.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("lahiosoite"));
        col_postinumero.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("postinumero"));
        col_puhelinnro.setCellValueFactory(new PropertyValueFactory<Asiakas, String>("puhelinnro"));

        listA = database.haeDataAsiakkaat(txt_haeID.getText());
        table_asiakas.setItems(listA);
        dataListA.addAll(listA);
    }

    //Lisää uuden asiakkaan (AF)
    @FXML
    void lisaa_asiakas() throws SQLException {

        Connection conn = database.ConnectDb();
        String sql = "insert into asiakas (asiakas_id, postinro, etunimi, sukunimi, lahiosoite, email, puhelinnro, hetu_ytun)values(?,?,?,?,?,?,?,?);";
        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, Integer.parseInt(txt_tallennaasiakasid.getText()));
            pst.setString(2, txt_tallennapostinumero.getText());
            pst.setString(3, txt_tallennaetunimi.getText());
            pst.setString(4, txt_tallennasukunimi.getText());
            pst.setString(5, txt_tallennalahiosoite.getText());
            pst.setString(6, txt_tallennaemail.getText());
            pst.setString(7, txt_tallennapuhelinnro.getText());
            pst.setString(8, txt_tallennahetu.getText());
            pst.executeUpdate();
            pst.close();
            JOptionPane.showMessageDialog(null, "Uusi asiakas lisätty");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kentät ovat joko puutteelliset tai virheelliset");
        }
        UpdateTableAsiakas();
        txt_tallennaasiakasid.clear();
        txt_tallennapostinumero.clear();
        txt_tallennaetunimi.clear();
        txt_tallennasukunimi.clear();
        txt_tallennalahiosoite.clear();
        txt_tallennaemail.clear();
        txt_tallennapuhelinnro.clear();
        txt_tallennahetu.clear();
    }

    // TALLENNETAAN MUUTOKSET ASIAKASTIETOIHIN (AR)
    public void tallenna_muutokset() throws SQLException {

        String sql = "UPDATE asiakas SET asiakas_id = " + txt_tallennaasiakasid.getText() + ", etunimi = '" + txt_tallennaetunimi.getText()
                + "', sukunimi = '" + txt_tallennasukunimi.getText() + "', lahiosoite = '" + txt_tallennalahiosoite.getText() + "', "
                + "email = '" + txt_tallennaemail.getText() + "', puhelinnro = '" + txt_tallennapuhelinnro.getText() + "', hetu_ytun = '" + txt_tallennahetu.getText() + "' WHERE asiakas_id = " + txt_tallennaasiakasid.getText();

        try {

            database.dbMuokkaus(sql);

            JOptionPane.showMessageDialog(null, "Asiakkaan tiedot päivitetty");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kentät ovat joko puutteelliset tai virheelliset");
        }
        UpdateTableAsiakas();
    }

    // ASIAKKAIDEN HAKU TIETOKANNASTA (AF)
    // SIIRRETAAN ASIAKASTIEDOT TABLEVIEWSTA MUOKKAUSLAATIKOIHIN (AR)
    // KUN KLIKATAAN RIVIA
    public void siirraValittuAsiakasMuokkaukseen() {

        try {

            Asiakas valittuAsiakas = table_asiakas.getSelectionModel().getSelectedItem();

            txt_tallennaetunimi.setText(valittuAsiakas.getEtunimi());
            txt_tallennasukunimi.setText(valittuAsiakas.getSukunimi());
            txt_tallennaasiakasid.setText(Integer.toString(valittuAsiakas.getAsiakasid()));
            txt_tallennalahiosoite.setText(valittuAsiakas.getLahiosoite());
            txt_tallennapostinumero.setText(valittuAsiakas.getPostinumero());
            txt_tallennaemail.setText(valittuAsiakas.getEmail());
            txt_tallennahetu.setText(valittuAsiakas.getHeTu());
            txt_tallennapuhelinnro.setText(valittuAsiakas.getPuhelinnro());
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, "Jotain meni pieleen");
        }

    }
    
    // KUTSUTAAN YLLAOLEVAA KUN KLIKATAN TABLEN RIVIA (AR)
    @FXML
    void asiakastableClick() {
        siirraValittuAsiakasMuokkaukseen();
    }

    // ASETETAAN ARVOT EKASIVUN MAJOITTUJIEN MAARA CHOICEBOXIIN (AR) NAMA ON MUODON VUOKSI, KOSKA

    @FXML
    public void lisaaChoiceBoxiinMajoittujienlkm() {

        // Luodaan List<Mokki>
        ObservableList<Mokki> lista = FXCollections.observableArrayList();

        //Kutsutaan funktiota databasesta, joka hakee kaikki kannan mokkeihin ilmoitetut majoittujien max maarat, 
        //ja lisaa ne listaan
        lista = database.getChoiceBoxValuesMajlkm();

        // Kaydaan lista lapi ja addataan ne boxeihin
        for (Mokki m : lista) {
            ComboBoxEkaMajoittujat.getItems().add(Integer.toString(m.getHenkilomaara()));
        }

    }

    // ASETETAAN ARVOT EKASIVUN PALVELUT-CHOICEBOXIIN (AR)
    @FXML
    public void lisaaChoiceBoxiinPalvelut() {

        // Luodaan List<Mokki>
        ObservableList<Palvelut> lista = FXCollections.observableArrayList();

        //Kutsutaan funktiota databasesta, joka hakee kaikki palvelut, 
        //ja lisaa ne listaan
        lista = database.getChoiceBoxValuesPalvelut();

        // Kaydaan lista lapi ja addataan ne boxeihin
        for (Palvelut p : lista) {
            ComboBoxEkaPalvelut.getItems().add(p.getNimi());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //HATEAAN LISTVIEWHIN TIEDOT
        UpdateTablePalvelut();
        UpdateTableAsiakas();
        UpdateTableVaraukset();
        // LISATAAN MOKIT ETUSIVUN TABLEVIEWHN (AR)
        lisaaMokitTableen();
        // LISATAAN TOIMINTA-ALUEET CHOICEBOXEIHIN RAPORTOINTI-VALILEHDELLA
        lisaaChoiceBoxiin();
        //LISÄÄ MÖKIN TIETOJA KANTAAN(ZV)
        con = database.ConnectDb();
        //LISÄÄ HAKU JA VARAUSSIVULLE KAIKKI MÖKIT (ZV)
        lisaaMokitTableen();
        //LISÄÄ KAIKKI MOKINOMISTAJAT OMISTAJIEN TAULUUN (ZV)
        lisaaMokinomistajattauluun();
        //LISÄÄ KAIKKI MOKIT MOKKIENMUOKKAUSLISTAAN (ZV)
        lisaamokkiMokkilistaan();
        lisaaChoiceBoxiinPalvelut();
        lisaaChoiceBoxiinMajoittujienlkm();
    }

}
