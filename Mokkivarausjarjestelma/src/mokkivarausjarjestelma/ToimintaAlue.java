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
public class ToimintaAlue {

    private int toimintaalue_id;
    private String nimi;

    public ToimintaAlue() {
    }

    public ToimintaAlue(int toimintaalue_id, String nimi) {
        this.toimintaalue_id = toimintaalue_id;
        this.nimi = nimi;
    }

    public int getToimintaalue_id() {
        return toimintaalue_id;
    }

    public void setToimintaalue_id(int toimintaalue_id) {
        this.toimintaalue_id = toimintaalue_id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

}
