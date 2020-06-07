/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mokkivarausjarjestelma;

import java.util.logging.Logger;

/**
 *
 */
public class Mokki {

    private int id;
    private String nimi;
    private String osoite;
    private String postinro;
    private String paikkakunta;
    private int henkilomaara;
    private Double hinta;

    public Mokki() {
    }

    public Mokki(int id, String nimi, String osoite, String postinro, String paikkakunta, int henkilomaara, Double hinta) {
        this.id = id;
        this.nimi = nimi;
        this.osoite = osoite;
        this.postinro = postinro;
        this.paikkakunta = paikkakunta;
        this.henkilomaara = henkilomaara;
        this.hinta = hinta;
    }

    public Mokki(int henkilomaara) {
        this.henkilomaara = henkilomaara;
    }

    public String getNimi() {
        return nimi;
    }

    public int getId() {
        return id;
    }

    public String getPaikkakunta() {
        return paikkakunta;
    }

    public String getOsoite() {
        return osoite;
    }

    public String getPostinro() {
        return postinro;
    }

    public Double getHinta() {
        return hinta;
    }

    public int getHenkilomaara() {
        return henkilomaara;
    }

}
