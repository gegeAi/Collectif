/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.collectif.metier.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author lboucaud
 */
@Entity
public class Responsable {
   
   @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    private String mail;
    private String motDePasse;
  

    public Responsable() {
    }

    public Responsable(String mail,String motDePasse) {
        
        this.mail = mail;
        this.motDePasse=motDePasse;
      
    }

    

     public Long getId() {
        return id;
    }
    public String getMail() {
        return mail;
    }
    
    public String getMotDePasse() {
        return motDePasse;
    }
    

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    

    @Override
    public String toString() {
        return "Responsable{" +"mail=" + mail + ", mot de passe:"+motDePasse + '}';
    }
}
