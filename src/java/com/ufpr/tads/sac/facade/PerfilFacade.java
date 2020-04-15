/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.sac.facade;

import com.ufpr.tads.sac.beans.Perfil;
import com.ufpr.tads.sac.dao.PerfilDAO;
import java.util.List;

/**
 *
 * @author rhone
 */
public class PerfilFacade {
    
    public static List<Perfil> getAllPerf(){
        return PerfilDAO.getAllPerf();
    }
    
}
