/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.rest.ejb;

import com.fcastillo.capitulo.rest.entity.Cuotas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fcastillo
 */
@Local
public interface CuotasFacadeLocal {

    void create(Cuotas cuotas);

    void edit(Cuotas cuotas);

    void remove(Cuotas cuotas);

    Cuotas find(Object id);

    List<Cuotas> findAll();

    List<Cuotas> findRange(int[] range);

    int count();
    
}
