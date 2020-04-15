/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.sac.beans;

import java.io.Serializable;

/**
 *
 * @author rhone
 */
public class PesquisaGerente implements Serializable {

   private String CampoS1;
   private String CampoS2;
   private int CampoI1;
   private int CampoI2;
   private float CampoF1;
   private float CampoF2;

    public PesquisaGerente() {

    }

    public String getCampoS1() {
        return CampoS1;
    }

    public void setCampoS1(String CampoS1) {
        this.CampoS1 = CampoS1;
    }

    public String getCampoS2() {
        return CampoS2;
    }

    public void setCampoS2(String CampoS2) {
        this.CampoS2 = CampoS2;
    }

    public int getCampoI1() {
        return CampoI1;
    }

    public void setCampoI1(int CampoI1) {
        this.CampoI1 = CampoI1;
    }

    public int getCampoI2() {
        return CampoI2;
    }

    public void setCampoI2(int CampoI2) {
        this.CampoI2 = CampoI2;
    }

    public float getCampoF1() {
        return CampoF1;
    }

    public void setCampoF1(float CampoF1) {
        this.CampoF1 = CampoF1;
    }

    public float getCampoF2() {
        return CampoF2;
    }

    public void setCampoF2(float CampoF2) {
        this.CampoF2 = CampoF2;
    }

}
