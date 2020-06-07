/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mokkivarausjarjestelma;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alisa
 */
public class Posti {

    private String toimipaikka = "";
    private String postinumero = "";

    // Constructors
    public Posti() {
    }

    public Posti(String toimipaikka, String postinumero) {
        this.toimipaikka = toimipaikka;
        this.postinumero = postinumero;
    }

    Posti(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Getters and Setters
    public String getToimipaikka() {
        return toimipaikka;
    }

    public void setToimipaikka(String toimipaikka) {
        this.toimipaikka = toimipaikka;
    }

    public String getPostinumero() {
        return postinumero;
    }

    public void setPostinumero(String postinumero) {
        this.postinumero = postinumero;
    }

}
