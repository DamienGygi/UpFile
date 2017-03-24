/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.UserUpfile;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author damien.gygi
 */
@Stateless
public class UserUpfileFacade extends AbstractFacade<UserUpfile> {

    @PersistenceContext(unitName = "projetTestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserUpfileFacade() {
        super(UserUpfile.class);
    }
    
}