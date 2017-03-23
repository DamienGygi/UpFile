/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author damien.gygi
 */
@Stateless
public class TypeFileFacade extends AbstractFacade<TypeFile> {

    @PersistenceContext(unitName = "upfilePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeFileFacade() {
        super(TypeFile.class);
    }
    
}
