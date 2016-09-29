/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Ajuste;
import entidades.Cidade;
import entidades.ItensVenda;
import entidades.Produto;
import entidades.Venda;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jaimedias
 */
@Stateless
public class AjusteFacade extends AbstractFacade<Ajuste>{

    @PersistenceContext(unitName = "webmobilejsfPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AjusteFacade() {
        super(Ajuste.class);
    }
}
