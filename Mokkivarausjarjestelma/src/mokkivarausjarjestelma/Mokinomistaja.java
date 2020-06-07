/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mokkivarausjarjestelma;

/**
 *
 * @author alisa
 */
public class Mokinomistaja {

    Integer idmokinOmistaja;
    String etunimi;
    String sukunimi;
    String email;
    String lahiosoite;
    String puhelinnumero;

    public Mokinomistaja(Integer idmokinOmistaja, String etunimi, String sukunimi, String email, String lahiosoite, String puhelinnumero) {
        this.idmokinOmistaja = idmokinOmistaja;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.email = email;
        this.lahiosoite = lahiosoite;
        this.puhelinnumero = puhelinnumero;
    }

    public Integer getIdmokinOmistaja() {
        return idmokinOmistaja;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public String getEmail() {
        return email;
    }

    public String getLahiosoite() {
        return lahiosoite;
    }

    public String getPuhelinnumero() {
        return puhelinnumero;
    }

}
