/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mokkivarausjarjestelma;

/**
 *
 * @author Yayneb
 */
public class Mokkimuokkaus {

    private Integer mokkiid;
    private Integer toimitntaauleid;
    private String postinumero;
    private String mokkinimi;
    private String katuosoite;
    private String kuvaus;
    private Integer henkilomaara;
    private Integer mokinomistajanid;
    private String hinta;

    public Mokkimuokkaus() {
    }

    public Mokkimuokkaus(Integer mokkiid, Integer toimitntaauleid, String postinumero, String mokkinimi, String katuosoite, String kuvaus, Integer henkilomaara, Integer mokinomistajanid, String hinta) {
        this.mokkiid = mokkiid;
        this.toimitntaauleid = toimitntaauleid;
        this.postinumero = postinumero;
        this.mokkinimi = mokkinimi;
        this.katuosoite = katuosoite;
        this.kuvaus = kuvaus;
        this.henkilomaara = henkilomaara;
        this.mokinomistajanid = mokinomistajanid;
        this.hinta = hinta;
    }

    public Integer getMokkiid() {
        return mokkiid;
    }

    public Integer getToimitntaauleid() {
        return toimitntaauleid;
    }

    public String getPostinumero() {
        return postinumero;
    }

    public String getMokkinimi() {
        return mokkinimi;
    }

    public String getKatuosoite() {
        return katuosoite;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public Integer getHenkilomaara() {
        return henkilomaara;
    }

    public Integer getMokinomistajanid() {
        return mokinomistajanid;
    }

    public String getHinta() {
        return hinta;
    }
}
