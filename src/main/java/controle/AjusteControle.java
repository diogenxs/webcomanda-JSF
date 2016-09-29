/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Ajuste;
import entidades.Produto;
import facade.AjusteFacade;
import facade.ProdutoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jaimedias
 */
@ManagedBean
@SessionScoped
public class AjusteControle {

    private Ajuste ajuste;
    @EJB
    private AjusteFacade ajusteFacade;
    @EJB
    private ProdutoFacade produtoFacade;
    
    private ConverterGenerico produtoConverter;
    
    public ConverterGenerico getProdutoConverter() {
        if (produtoConverter == null) {
            produtoConverter = new ConverterGenerico(produtoFacade);
        }
        return produtoConverter;
    }

    public void setProdutoConverter(ConverterGenerico produtoConverter) {
        this.produtoConverter = produtoConverter;
    }    
    
    public void novo() {
        ajuste = new Ajuste();  
    }

    public void remover(Ajuste c) {
        ajuste = c;
        ajusteFacade.remover(ajuste);
    }

    public void salvar() {
        ajusteFacade.salvar(ajuste);
    }

    public void fechar(){
        
    }
    
    public List<Ajuste> listaTodos() {
        return ajusteFacade.listaTodos();
    }

    public Ajuste getAjuste() {
        return ajuste;
    }

    public void setAjuste(Ajuste ajuste) {
        this.ajuste = ajuste;
    }

    public List<Produto> listaProdutos(String parte) {
        return produtoFacade.listaFiltrando(parte, "nome");
    }
    
}
