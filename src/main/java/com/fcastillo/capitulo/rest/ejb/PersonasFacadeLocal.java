/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.rest.ejb;

import com.fcastillo.capitulo.rest.entity.Personas;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author fcastillo
 */
@Local
public interface PersonasFacadeLocal {

    void create(Personas personas);

    void edit(Personas personas);

    void remove(Personas personas);

    Personas find(Object id);

    List<Personas> findAll();

    List<Personas> findRange(int[] range);

    int count();

    List<Personas> findByParams(int start, int size, String sortField, String sortOrder, Map<String, Object> filters);

}
