/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mokkivarausjarjestelma;

import java.io.IOException;
import java.util.List;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alisa
 */
public class PostiDAOImpl implements PostiDAO {

    private List<Posti> postinrotJaToimipaikat;
    private static String filu = "C:\\Users\\alisa\\Documents\\NetBeansProjects\\Mokkivarausjarjestelma\\src\\Postitoimipaikat.txt";

    public PostiDAOImpl() {

        postinrotJaToimipaikat = new ArrayList<Posti>();
    }

    @Override
    public void vieNumerotJaToimipaikatKantaan() {
        try {
            for (Posti p : postinrotJaToimipaikat) {
                String paikka = p.getToimipaikka();
                String nro = p.getPostinumero();
                String sqlKasky = "INSERT IGNORE INTO posti " + "SET postinro = " + "'" + nro + "', " + "toimipaikka = '" + paikka + "'";
                database.dbMuokkaus(sqlKasky);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostiDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // JOS EI OIKEESTI RUPEE TOIMII NII HELKKARI
    public String kokkonenAutofill(String nro) {

        for (Posti P : postinrotJaToimipaikat) {

            if (nro.equals(P.getPostinumero())) {
                return P.getToimipaikka();
            }
        }

        return "Ei löydy";

    }

    @Override
    public void lueNumerotJaToimipaikat() { // Tassa 
        try {
            String nro = "";                                        // Numeron apumuuttuja
            String paikka = "";                                     // Toimipaikan apumuuttuja
            String luettuRivi = "";                                 // Tiedostosta luettu rivi
            String muokattavaRivi = "";
            File file = new File(filu);                             // Luodaan tiedoston ilmentyma
            //FileReader fr = new FileReader(file);                   // Avataan tiedosto
            BufferedReader br = new BufferedReader((new InputStreamReader(new FileInputStream(file), "ISO-8859-1")));             // Luodaan puskuri merkkien lukemiseen
            StringBuffer sb = new StringBuffer();                   // Luodaan string-puskuri, jossa ei merkkeja

            while ((luettuRivi = br.readLine()) != null) {           // Luetaan riveja niin kauan kun tiedostossa on tavaraa
                Posti p = new Posti();                               // Luodaan Posti-olio joka on yksittainen postinro ja toimipaikkakokonaisuus, esim 70500 Kuopio
                muokattavaRivi = sb.append(luettuRivi).toString();   // Liittaa rivin tekstin StringBufferiin
                sb.delete(0, sb.length());                            // Tyhjaa StringBufferin
                String patkat[] = muokattavaRivi.split(" ");         // Jaetaan Rivi kahteen osaan valilyonnin kohdalta eli [0] ja [1]
                nro = patkat[0];                                     // Postinumero on ensimmaisena eli paikalla [0]
                paikka = patkat[1];                                  // Toimipaikka on toisena eli paikalla [1]
                p.setToimipaikka(paikka);
                p.setPostinumero(nro);
                postinrotJaToimipaikat.add(p);                       // Lisataan Olio p listaan
                System.out.println(p.getToimipaikka());
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static ObservableList<Posti> taydennaToimipaikka(String postinumero) throws ClassNotFoundException, SQLException{
            
            String kysely = "SELECT '' as toimipaikka  FROM posti WHERE postinro = '" + postinumero + "'";
            
            try{
               ResultSet rs = database.dbSelect(kysely);
               ObservableList<Posti> postit = postitListaan(rs);
               return postit;
                }catch(SQLException e){
                System.out.println("Error" + e);
                e.printStackTrace();
                throw e;
            }
        
    }*/
    private static ObservableList<Posti> postitListaan(ResultSet rs) throws ClassNotFoundException, SQLException {
        try {

            ObservableList<Posti> postiLista = FXCollections.observableArrayList();

            while (rs.next()) {

                Posti pos = new Posti();
                pos.setPostinumero(rs.getString("postinro"));
                pos.setToimipaikka(rs.getString("toimipaikka"));
                postiLista.add(pos);
                rs.close();
            }
            return postiLista;
        } catch (SQLException e) {
            System.out.println("Errori postitListaan");
            e.printStackTrace();
            throw e;
        }
    }
}
