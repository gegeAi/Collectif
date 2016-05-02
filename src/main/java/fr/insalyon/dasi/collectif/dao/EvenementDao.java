/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.collectif.dao;

import fr.insalyon.dasi.collectif.metier.modele.Demande;
import fr.insalyon.dasi.collectif.metier.modele.Evenement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author lboucaud
 */
public class EvenementDao {
    
    public void create(Evenement evenement) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            em.persist(evenement);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public Evenement update(Evenement evenement) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            evenement = em.merge(evenement);
        }
        catch(Exception e){
            throw e;
        }
        return evenement;
    }
    
    public Evenement findById(long id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Evenement evenement = null;
        try {
            evenement = em.find(Evenement.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return evenement;
    }
    
    public List<Evenement> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Evenement> evenements = null;
        try {
            Query q = em.createQuery("SELECT a FROM Evenement a");
            evenements = (List<Evenement>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return evenements;
    }
    
    
    
}
