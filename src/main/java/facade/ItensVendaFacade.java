/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.ItensVenda;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jaimedias
 */
@Stateless
public class ItensVendaFacade extends AbstractFacade<ItensVenda> {

    @PersistenceContext(unitName = "webmobilejsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItensVendaFacade() {
        super(ItensVenda.class);
    }
}
