package fr.insalyon.dasi.collectif.dao;

import fr.insalyon.dasi.collectif.metier.modele.Lieu;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class LieuDao {
    
    public void create(Lieu lieu) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            em.persist(lieu);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public Lieu update(Lieu lieu) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            lieu = em.merge(lieu);
        }
        catch(Exception e){
            throw e;
        }
        return lieu;
    }
    
    public Lieu findById(long id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Lieu lieu = null;
        try {
            lieu = em.find(Lieu.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return lieu;
    }
    
    public List<Lieu> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Lieu> lieux = null;
        try {
            Query q = em.createQuery("SELECT l FROM Lieu l");
            lieux = (List<Lieu>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return lieux;
    }
}
