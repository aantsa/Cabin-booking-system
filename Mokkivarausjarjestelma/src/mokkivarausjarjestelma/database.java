/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mokkivarausjarjestelma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author alisa
 */
/**
 * - Luokasta voidaan kutsua connect(), dicconnect(), dbMuokkaus() ja dbDelect()
 * metodeja metodeja
 */
public class database {

    // KANNAN YHDISTAMISTA VARTEN
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static Connection connection = null;
    private static final String url = "jdbc:mysql://localhost:3306/vn?zeroDateTimeBehavior=convertToNull";
    private static final String username = "root";
    private static final String password = "Kolme";

    // TATA TASTA SEURAAVAA KAHTA METODIA OLEN KAYTTANYT POSTINUMEROIDEN JA TOIMIPAIKKOJEN KANTAAN LAITTAMISESSA
    // OSOITTATUI MYOHEMMIN KUITENKIN EPAVARMEMMAKSI KUIN ConnectDb() METODI
    // CONNECT DB(LOYTYY ALEMPAA) ON SE MITA KAYTETAAN SITTEN MUUALLA (AR)
    public static void connect() throws SQLException, ClassNotFoundException { //Yhteyden luominen
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Driveria ei loydy.");
            throw e;
        }

        System.out.println("Driver on rekisteroity");

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Yhteys paalla.");
        } catch (SQLException e) {
            System.out.println("Yhteys petti." + e);
        }
    }

    //SULJETAN YHTEYS (AR)
    public static void disconnect() throws SQLException { // Yhteyden sulkeminen
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Yhteys onnistuneesti kiinni");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    // TAMA ON INSERT/DELETE/UPDATE TOIMINNOILLE VAIHTOEHTOINEN TOTEUTUS, KAYTETTY ESIM POSTINROIDEN KANTAAN LAITTAMISESSA (AR)
    public static void dbMuokkaus(String sqlKysely) throws SQLException, ClassNotFoundException {    // Parametrina haluttu tietokantakysely
        Statement stmt = null;
        try {
            connect();  // Avataan yhteys
            stmt = connection.createStatement();
            stmt.executeUpdate(sqlKysely); // suoritetaan kysely
        } catch (SQLException e) {
            System.out.println("Ongelma kyselyssa");
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close(); //Statement pitaa sulkea ennen kuin suljetaan yhteys
            }
            disconnect();
        }
    }

    // PAA_ASIALLINEN METODI TIETOKANTAYHTEYDEN LUOMISEEN (AF)
    Connection conn = null;

    public static Connection ConnectDb() {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            //JOptionPane.showMessageDialog(null, "ConnectionEstablished"); //Ilmoitus kun yhteys päällä
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    // TALLA SAADAAN TIEDOT MAJOITTUMISRAPORTTIIN (AR)
    // PARAMETRINA toiminta-alue, alkupvm ja loppupvm
    public static int getMajoittumiset(String toimintaAlue, String apvm, String lpvm) {

        // ALIKYSELY
        String statement = "SELECT COUNT(*) FROM varaus"
                + " WHERE varattu_alkupvm >= '" + apvm + "' AND varattu_loppupvm <= '" + lpvm
                + "' AND mokki_mokki_id IN"
                + " (SELECT mokki_id from mokki WHERE toimintaalue_id IN "
                + "(SELECT toimintaalue_id FROM toimintaalue WHERE nimi = '" + toimintaAlue + "'))";

        Connection conn = ConnectDb();

        //PALAUTETAAN KYSELYSTA SAATU COUNT
        try {
            PreparedStatement ps = conn.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return 0;
    }

    // TALLA SAADAAN TIEDOT PALVELURAPORTTIIN (AR)
    public static ObservableList<Palvelut> getPalvelutEuroina(String toimintaAlue, String apvm, String lpvm) {

        // MIETO ALIKYSELY
        String statement = "SELECT hinta, alv FROM palvelu WHERE palvelu_id IN "
                + "(SELECT palvelu_id FROM varauksen_palvelut WHERE varaus_id IN "
                + "(SELECT varaus_id FROM varaus WHERE varattu_alkupvm >= '" + apvm + "' AND varattu_loppupvm <= '" + lpvm + "' AND mokki_mokki_id IN "
                + "(SELECT mokki_id FROM mokki WHERE toimintaalue_id IN"
                + "(SELECT toimintaalue_id FROM toimintaalue WHERE nimi = '" + toimintaAlue + "'))))";

        // LUODAAN LISTA JOHON LISATAAN PALVELU-OLIOT
        ObservableList<Palvelut> list = FXCollections.observableArrayList();

        // YHTEYS TIETOKANTAAN
        Connection conn = ConnectDb();

        try {
            PreparedStatement ps = conn.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            // NIIN KAUAN KUIN RESULTSETISSA TAVARAA NIIN LISATAAN LISTAAN OLIO JOSSA PALVELUN HINTA JA ALV
            while (rs.next()) {
                list.add(new Palvelut(Double.parseDouble(rs.getString("hinta")), Double.parseDouble(rs.getString("alv"))));
            }
        } catch (Exception e) {
        }
        // PALAUTETAAN LISTA
        return list;
    }

    // TAALLA SAAMME TIETOKANNASTA TOIMINTA-ALUEET RAPORTOINTI-VALILEHDEN CHOICEBOXEIHIN (AR)
    public static ObservableList<ToimintaAlue> getChoiceBoxValues() {

        String statement = "SELECT * FROM toimintaalue";
        ObservableList<ToimintaAlue> list = FXCollections.observableArrayList();

        Connection conn = ConnectDb();

        try {
            PreparedStatement ps = conn.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new ToimintaAlue(Integer.parseInt(rs.getString("toimintaalue_id")), rs.getString("nimi")));
            }
        } catch (Exception e) {
        }

        return list;
    }
    // HAETAAN VARAUKSET TABLEEN TIETOKANNASTA (AR)

    public static ObservableList<Varaus> getVaraukset() {

        Connection conn = ConnectDb();
        ObservableList<Varaus> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from varaus");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Varaus(Integer.parseInt(rs.getString("mokki_mokki_id")), Integer.parseInt(rs.getString("varaus_id")), Integer.parseInt(rs.getString("asiakas_id"))));
            }
        } catch (Exception e) {
        }
        return list;
       
    }

    // TALLA SAADAAN AUTOFILL POSTINUMEROIDEN JA TOIMIPAIKKOJEN KANSSA
    // PARAMETRINA POSTINRO (AR)
    public static ObservableList<Posti> getPostit(String postinro) {

        String statement = "select * from posti where postinro = '" + postinro + "'";
        Connection conn = ConnectDb();
        ObservableList<Posti> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Posti(rs.getString("postinro"), rs.getString("toimipaikka")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    // LATAA PALVELUT TIETOKANNASTA (AF)
    public static ObservableList<Palvelut> getDatapalvelut() {

        Connection conn = ConnectDb();
        ObservableList<Palvelut> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from palvelu");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Palvelut(Integer.parseInt(rs.getString("palvelu_id")), Integer.parseInt(rs.getString("toimintaalue_ID")), rs.getString("nimi"), Integer.parseInt(rs.getString("tyyppi")), rs.getString("kuvaus"), Double.parseDouble(rs.getString("hinta")), Double.parseDouble(rs.getString("alv"))));
            }
        } catch (Exception e) {
        }
        return list;
    }

    //HAKEE VALITUN PALVELUN (AF)
    public static ObservableList<Palvelut> haeDatapalvelut(String palvelunnimi) {

        Connection conn = ConnectDb();
        ObservableList<Palvelut> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("Select * From palvelu WHERE nimi like '" + palvelunnimi + "%';");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Palvelut(Integer.parseInt(rs.getString("palvelu_id")), Integer.parseInt(rs.getString("toimintaalue_ID")), rs.getString("nimi"), Integer.parseInt(rs.getString("tyyppi")), rs.getString("kuvaus"), Double.parseDouble(rs.getString("hinta")), Double.parseDouble(rs.getString("alv"))));
            }
        } catch (Exception e) {
            System.out.println("ei nappaa");
        }
        return list;
    }

    // HAETAAN MOKIT TIETOKANNASTA (AR)
    public static ObservableList<Mokki> getMokit() {

        Connection conn = ConnectDb();
        ObservableList<Mokki> list = FXCollections.observableArrayList();
        try {
            // Liitoskysely, koska tarvitsen toimipaikan nimen eri taulusta. Linkitetty luonnollisesti toimipaikka_id:lla.
            PreparedStatement ps = conn.prepareStatement("select mokki_id, mokkinimi, katuosoite, postinro, nimi, henkilomaara, hinta "
                    + "FROM mokki JOIN toimintaalue ON mokki.toimintaalue_id = toimintaalue.toimintaalue_id");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Mokki(Integer.parseInt(rs.getString("mokki_id")), rs.getString("mokkinimi"), rs.getString("katuosoite"), rs.getString("postinro"), rs.getString("nimi"), Integer.parseInt(rs.getString("henkilomaara")), Double.parseDouble(rs.getString("hinta"))));
            }
        } catch (Exception e) {
        }
        return list;
    }

    //LADATAAN ASIAKKAAT TIETOKANNASTA (AF)
    public static ObservableList<Asiakas> getDataAsiakkaat() {
        Connection conn = ConnectDb();
        ObservableList<Asiakas> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from asiakas");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Asiakas(Integer.parseInt(rs.getString("asiakas_id")), rs.getString("postinro"), rs.getString("etunimi"), rs.getString("sukunimi"), rs.getString("lahiosoite"), rs.getString("email"), rs.getString("puhelinnro"), rs.getString("hetu_ytun")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    // HAETAAN ASIAKKAAT TIETOKANNASTA (AF)
    public static ObservableList<Asiakas> haeDataAsiakkaat(String id) {
        Connection conn = ConnectDb();
        ObservableList<Asiakas> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("Select * From asiakas WHERE asiakas_id like '" + id + "%';");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Asiakas(Integer.parseInt(rs.getString("asiakas_id")), rs.getString("postinro"), rs.getString("etunimi"), rs.getString("sukunimi"), rs.getString("lahiosoite"), rs.getString("email"), rs.getString("puhelinnro"), rs.getString("hetu_ytun")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    // HAETAAN MOKIN KUVAUS TIETOKANNASTA(AR)
    public static String getKuvaus(int id) {

        Connection conn = ConnectDb();
        String kuvaus = "";

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT kuvaus FROM mokki WHERE mokki_id = " + id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                kuvaus = rs.getString("kuvaus");
            }
        } catch (Exception e) {
        }
        return kuvaus;
    }

    
    // ZEINEBIN TIETOKANTAMETODIT/FUNKTIOT
    public static ObservableList<Mokinomistaja> getMokinomistajat() {

        Connection conn = ConnectDb();
        ObservableList<Mokinomistaja> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM mokinomistaja");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Mokinomistaja(Integer.parseInt(rs.getString("idmokinOmistaja")), rs.getString("etunimi"), rs.getString("sukunimi"), rs.getString("email"),
                        rs.getString("lahiosoite"), rs.getString("puhelinnumero")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    // ZV
    public static ObservableList<Mokkimuokkaus> getMokkimuokkaus() {
        Connection conn = ConnectDb();
        ObservableList<Mokkimuokkaus> list = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM mokki");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Mokkimuokkaus(Integer.parseInt(rs.getString("mokki_id")), Integer.parseInt(rs.getString("toimintaalue_id")), rs.getString("postinro"),
                        rs.getString("mokkinimi"), rs.getString("katuosoite"), rs.getString("kuvaus"), Integer.parseInt(rs.getString("henkilomaara")), Integer.parseInt(rs.getString("mokinOmistaja_idmokinOmistaja")),
                        rs.getString("hinta")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    // TAALLA SAAMME TIETOKANNASTA MAJOITTUJIEN LUKUMAARAT EKAN SIVUN CHOICEBOXIIN MAJOITTUJIEN MAARA(AR)
    public static ObservableList<Mokki> getChoiceBoxValuesMajlkm() {

        String statement = "SELECT henkilomaara FROM mokki";
        ObservableList<Mokki> list = FXCollections.observableArrayList();

        Connection conn = ConnectDb();

        try {
            PreparedStatement ps = conn.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Mokki(Integer.parseInt(rs.getString("henkilomaara"))));
            }
        } catch (Exception e) {
        }

        return list;
    }
   
    // TAALLA SAAMME TIETOKANNASTA PALVELUT EKAN SIVUN CHOICEBOXIIN PALVELUT(AR)
        public static ObservableList<Palvelut> getChoiceBoxValuesPalvelut() {

        String statement = "SELECT nimi FROM palvelu";
        ObservableList<Palvelut> list = FXCollections.observableArrayList();

        Connection conn = ConnectDb();

        try {
            PreparedStatement ps = conn.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Palvelut(rs.getString("nimi")));
            }
        } catch (Exception e) {
        }

        return list;
    }
        
     // HAKUTOIMINTO MOKKIEN HAKUA VARTEN(AF)
public static ObservableList<Mokkimuokkaus> haeDataMokit(String nimi) {
        Connection conn = ConnectDb();
        ObservableList<Mokkimuokkaus> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("Select * From mokki WHERE mokkinimi like '" + nimi + "%';");
            ResultSet rs = ps.executeQuery();

 

            while (rs.next()) {
                list.add(new Mokkimuokkaus(Integer.parseInt(rs.getString("mokki_id")), Integer.parseInt(rs.getString("toimintaalue_id")), rs.getString("postinro"), rs.getString("mokkinimi"), rs.getString("katuosoite"), rs.getString("kuvaus"), Integer.parseInt(rs.getString("henkilomaara")), Integer.parseInt(rs.getString("mokinOmistaja_idmokinOmistaja")), rs.getString("hinta")));
            }
        } catch (Exception e) {
        }
        return list;
    }

}
