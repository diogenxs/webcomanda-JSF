/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.ItensVenda;
import entidades.Produto;
import entidades.Venda;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jaimedias
 */
@Stateless
public class VendaFacade extends AbstractFacade<Venda> {

    @PersistenceContext(unitName = "webmobilejsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VendaFacade() {
        super(Venda.class);
    }

    @Override
    public void salvar(Venda entity) {
        if(entity.getDataVenda() == null){
           entity.setDataVenda(new Date(System.currentTimeMillis()));
        }
        entity.setComandaFechada(false);
        entity.getComanda().setStatus("R");
        atualizaEstoque(entity);
        em.merge(entity);
    }

    private void atualizaEstoque(Venda entity) {
        for (ItensVenda iv : entity.getItensVendas()) {
            Produto p = iv.getProduto();
            p.setEstoque(p.getEstoque() - iv.getQuantidade());
            em.merge(p);
        }
    }

    public List<Venda> listaVendasAbertas() {
        Query q = getEntityManager().createQuery("from Venda where comandaFechada = false");
        return q.getResultList();
    }
    
    public void fechar(Venda entity) {
      
        entity.setComandaFechada(true);
        entity.getComanda().setStatus("L");
        em.merge(entity);
    }
}
