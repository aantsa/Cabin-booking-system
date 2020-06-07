/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mokkivarausjarjestelma;

/**
 *
 * @author alisa Luokka taululle 'varaus'
 *
 */
public class Varaus {

    private int varaus_id;
    private int asiakas_id;
    private String asiakas_snimi;
    private String asiakas_enimi;
    private int mokki_id;
    private String mokki_nimi;
    private String varattu_pvm;
    private String vahvistus_pvm;
    private String varattu_alkupvm;
    private String varattu_loppupvm;
    private int lasku_id;
    private double lasku_summa;

    public Varaus() {
    }

    public Varaus(int mokki_id, int varaus_id, int asiakas_id) {
        this.mokki_id = mokki_id;
        this.varaus_id = varaus_id;
        this.asiakas_id = asiakas_id;

    }

    public Varaus(int varaus_id, int asiakas_id, String asiakas_snimi, String asiakas_enimi, int mokki_id, String mokki_nimi, String varattu_pvm, String vahvistus_pvm, String varattu_alkupvm, String varattu_loppupvm, int lasku_id, double lasku_summa) {
        this.varaus_id = varaus_id;
        this.asiakas_id = asiakas_id;
        this.asiakas_snimi = asiakas_snimi;
        this.asiakas_enimi = asiakas_enimi;
        this.mokki_id = mokki_id;
        this.mokki_nimi = mokki_nimi;
        this.varattu_pvm = varattu_pvm;
        this.vahvistus_pvm = vahvistus_pvm;
        this.varattu_alkupvm = varattu_alkupvm;
        this.varattu_loppupvm = varattu_loppupvm;
        this.lasku_id = lasku_id;
        this.lasku_summa = lasku_summa;
    }

    public int getVaraus_id() {
        return varaus_id;
    }

    public int getAsiakas_id() {
        return asiakas_id;
    }

    public String getAsiakas_snimi() {
        return asiakas_snimi;
    }

    public String getAsiakas_enimi() {
        return asiakas_enimi;
    }

    public int getMokki_id() {
        return mokki_id;
    }

    public String getMokki_nimi() {
        return mokki_nimi;
    }

    public String getVarattu_pvm() {
        return varattu_pvm;
    }

    public String getVahvistus_pvm() {
        return vahvistus_pvm;
    }

    public String getVarattu_alkupvm() {
        return varattu_alkupvm;
    }

    public String getVarattu_loppupvm() {
        return varattu_loppupvm;
    }

    public int getLasku_id() {
        return lasku_id;
    }

    public double getLasku_summa() {
        return lasku_summa;
    }

}
