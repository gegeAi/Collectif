/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasUsage.Actions;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Samuel Toko
 * Classe abstraite qui va permettre de définir toutes les actions
 */
public abstract class Action {
    
    public abstract void execute(HttpServletRequest request);
    
}
