/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.rest.ejb;

import com.fcastillo.capitulo.rest.entity.Personas;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author fcastillo
 */
@Stateless
public class PersonasFacade extends AbstractFacade<Personas> implements PersonasFacadeLocal {

    @PersistenceContext(unitName = "com.fcastillo_capitulo-rest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonasFacade() {
        super(Personas.class);
    }

    @Override
    public List<Personas> findByParams(int start, int size, String sortField, String sortOrder, Map<String, Object> filters) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Personas> criteriaQuery = criteriaBuilder.createQuery(Personas.class);
        Root<Personas> raiz = criteriaQuery.from(Personas.class);

        List<Predicate> listaPredicados = new ArrayList<>();
        if (sortField != null) {
            criteriaQuery.orderBy(sortOrder.equals("asc") ? criteriaBuilder.asc(raiz.get(sortField)) : criteriaBuilder.desc(raiz.get(sortField)));
        }

        if (filters.size() > 0 && filters != null) {
            if (filters.get("search") != null) {
                Predicate predicadoSearch = criteriaBuilder.like(criteriaBuilder.lower(raiz.get("apellido")), "%" + filters.get("search").toString().toLowerCase() + "%");
                listaPredicados.add(predicadoSearch);
            }

            if (listaPredicados.size() > 0) {
                listaPredicados.forEach((p) -> {
                    criteriaQuery.where(listaPredicados.toArray(new Predicate[listaPredicados.size()]));
                });
            }
        }
        TypedQuery<Personas> query = em.createQuery(criteriaQuery);
        query.setFirstResult(start);
        query.setMaxResults(size);
        return query.getResultList();

    }

    @Override
    public Personas findByDocumento(String numeroDocumento) {
        String consulta = "FROM Personas p WHERE p.ndocumento=?1";
        Personas persona = null;
        TypedQuery<Personas> query = em.createQuery(consulta, Personas.class);
        query.setParameter(1, numeroDocumento);
        if (!query.getResultList().isEmpty()) {
            persona = query.getResultList().get(0);
        }
        return persona;
    }

}
