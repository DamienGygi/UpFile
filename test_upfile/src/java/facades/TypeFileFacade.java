/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.TypeFile;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author damien.gygi
 */
@Stateless
public class TypeFileFacade extends AbstractFacade<TypeFile> {

    @PersistenceContext(unitName = "test_upfilePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeFileFacade() {
        super(TypeFile.class);
    }
    
}
