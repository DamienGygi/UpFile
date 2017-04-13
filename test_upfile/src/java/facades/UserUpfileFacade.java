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
import javax.persistence.Query;

/**
 *
 * @author damien.gygi
 */
@Stateless
public class UserUpfileFacade extends AbstractFacade<UserUpfile> {

    @PersistenceContext(unitName = "test_upfilePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserUpfileFacade() {
        super(UserUpfile.class);
    }
    
    public UserUpfile findUserByName(String username) {
        Query users = em.createNamedQuery("User.findByUsername").setParameter("username", username);
        
        return (UserUpfile) users.getSingleResult();
    }
    
}
