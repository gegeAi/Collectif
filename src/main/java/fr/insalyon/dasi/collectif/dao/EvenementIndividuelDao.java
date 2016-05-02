/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.collectif.dao;

import fr.insalyon.dasi.collectif.metier.modele.EvenementIndividuel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author lboucaud
 */
public class EvenementIndividuelDao {
    
    public void create(EvenementIndividuel evenement) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            em.persist(evenement);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public EvenementIndividuel update(EvenementIndividuel evenement) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            evenement = em.merge(evenement);
        }
        catch(Exception e){
            throw e;
        }
        return evenement;
    }
    
    public EvenementIndividuel findById(long id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        EvenementIndividuel evenement = null;
        try {
            evenement = em.find(EvenementIndividuel.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return evenement;
    }
    
    public List<EvenementIndividuel> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<EvenementIndividuel> evenements = null;
        try {
            Query q = em.createQuery("SELECT a FROM EvenementIndividuel a");
            evenements = (List<EvenementIndividuel>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return evenements;
    }
    
}
