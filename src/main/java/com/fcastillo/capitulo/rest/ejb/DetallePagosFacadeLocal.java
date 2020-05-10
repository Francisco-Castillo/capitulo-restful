/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.rest.ejb;

import com.fcastillo.capitulo.rest.entity.DetallePagos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fcastillo
 */
@Local
public interface DetallePagosFacadeLocal {

    void create(DetallePagos detallePagos);

    void edit(DetallePagos detallePagos);

    void remove(DetallePagos detallePagos);

    DetallePagos find(Object id);

    List<DetallePagos> findAll();

    List<DetallePagos> findRange(int[] range);

    int count();
    
}
