/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Produto;
import entidades.TipoProduto;
import facade.ProdutoFacade;
import facade.TipoProdutoFacade;
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
public class ProdutoControle {

    private Produto produto;
    @EJB
    private ProdutoFacade produtoFacade;
    @EJB
    private TipoProdutoFacade tipoprodutoFacade;
    private ConverterGenerico tipoprodutoConverter;

    public ProdutoFacade getProdutoFacade() {
        return produtoFacade;
    }

    public void setProdutoFacade(ProdutoFacade produtoFacade) {
        this.produtoFacade = produtoFacade;
    }

    public TipoProdutoFacade getTipoprodutoFacade() {
        return tipoprodutoFacade;
    }

    public void setTipoprodutoFacade(TipoProdutoFacade tipoprodutoFacade) {
        this.tipoprodutoFacade = tipoprodutoFacade;
    }

    public ConverterGenerico getTipoprodutoConverter() {
        if (tipoprodutoConverter == null) {
            tipoprodutoConverter = new ConverterGenerico(tipoprodutoFacade);
        }
        return tipoprodutoConverter;
    }

    public void setTipoprodutoConverter(ConverterGenerico tipoprodutoConverter) {
        this.tipoprodutoConverter = tipoprodutoConverter;
    }

    public void novo() {
        produto = new Produto();
    }

    public void editar(Produto e) {
        produto = e;
    }

    public void remover(Produto e) {
        produto = e;
        produtoFacade.remover(produto);
    }

    public void salvar() {
        produtoFacade.salvar(produto);
    }

    public List<Produto> listaTodos() {
        return produtoFacade.listaTodos();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<TipoProduto> listaTodostp() {
        return tipoprodutoFacade.listaTodos();
    }

    public List<TipoProduto> listaFiltrando(String parte) {
        return tipoprodutoFacade.listaFiltrando(parte, "descricao");
    }

}
