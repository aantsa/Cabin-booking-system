/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mokkivarausjarjestelma;

public class Palvelut {

    int id, toimintaAlueID, tyyppi;
    Double hinta, alv;
    String nimi, kuvaus;

    public Palvelut() {

    }

    public Palvelut(int id, int toimintaAlueID, String nimi, int tyyppi, String kuvaus, Double hinta, Double alv) {

        this.id = id;
        this.toimintaAlueID = toimintaAlueID;
        this.nimi = nimi;
        this.tyyppi = tyyppi;
        this.kuvaus = kuvaus;
        this.hinta = hinta;
        this.alv = alv;
    }

    public Palvelut(double hinta, double alv) {
        this.hinta = hinta;
        this.alv = alv;
    }

    public Palvelut(String nimi) {
        this.nimi = nimi;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setToimintaAlueID(int toimintaAlueID) {
        this.toimintaAlueID = toimintaAlueID;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setTyyppi(int tyyppi) {
        this.tyyppi = tyyppi;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public void setHinta(Double hinta) {
        this.hinta = hinta;
    }

    public void setAlv(Double alv) {
        this.alv = alv;
    }

    public int getId() {
        return id;
    }

    public int getToimintaAlueID() {
        return toimintaAlueID;
    }

    public String getNimi() {
        return nimi;
    }

    public int getTyyppi() {
        return tyyppi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public Double getHinta() {
        return hinta;
    }

    public Double getAlv() {
        return alv;
    }
}
