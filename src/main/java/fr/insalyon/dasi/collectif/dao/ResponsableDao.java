/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.collectif.dao;

import fr.insalyon.dasi.collectif.metier.modele.Responsable;
import fr.insalyon.dasi.collectif.metier.modele.Responsable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author lboucaud
 */
public class ResponsableDao {
    
     public void create(Responsable responsable) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            em.persist(responsable);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public Responsable update(Responsable responsable) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            responsable = em.merge(responsable);
        }
        catch(Exception e){
            throw e;
        }
        return responsable;
    }
    
    public Responsable find(long id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Responsable responsable = null;
        try {
            responsable = em.find(Responsable.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return responsable;
    }
    
       public List<Responsable> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Responsable> responsables = null;
        try {
            Query q = em.createQuery("SELECT a FROM Responsable a");
            responsables = (List<Responsable>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return responsables;
    }
    
}
