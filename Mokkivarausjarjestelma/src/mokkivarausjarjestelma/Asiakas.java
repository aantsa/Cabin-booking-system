/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mokkivarausjarjestelma;

/**
 *
 * @author Anton F.
 */
public class Asiakas {

    int asiakasid;
    String etunimi;
    String sukunimi;
    String heTu;
    String email;
    String lahiosoite;
    String postinumero;
    String puhelinnro;

    public String getPuhelinnro() {
        return puhelinnro;
    }

    public void setPuhelinnro(String puhelinnro) {
        this.puhelinnro = puhelinnro;
    }

    public int getAsiakasid() {
        return asiakasid;
    }

    public void setAsiakasid(int asiakasid) {
        this.asiakasid = asiakasid;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public String getHeTu() {
        return heTu;
    }

    public void setHeTu(String heTu) {
        this.heTu = heTu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLahiosoite() {
        return lahiosoite;
    }

    public void setLahiosoite(String lahiosoite) {
        this.lahiosoite = lahiosoite;
    }

    public String getPostinumero() {
        return postinumero;
    }

    public void setPostinumero(String postinumero) {
        this.postinumero = postinumero;
    }

    public Asiakas(int asiakasid, String postinumero, String etunimi, String sukunimi, String lahiosoite, String email, String puhelinnro, String heTu) {
        this.asiakasid = asiakasid;
        this.postinumero = postinumero;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.lahiosoite = lahiosoite;
        this.email = email;
        this.puhelinnro = puhelinnro;
        this.heTu = heTu;
    }

}
