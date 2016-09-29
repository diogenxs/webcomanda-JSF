/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.TipoProduto;
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
public class TipoProdutoControle {

    private TipoProduto tipoproduto;
    @EJB
    private TipoProdutoFacade tipoprodutoFacade;
    private ConverterGenerico tipoprodutoConverter;

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
    
    public TipoProduto getTipoproduto() {
        return tipoproduto;
    }

    public void setTipoproduto(TipoProduto tipoproduto) {
        this.tipoproduto = tipoproduto;
    }

    public TipoProdutoFacade getTipoprodutoFacade() {
        return tipoprodutoFacade;
    }

    public void novo(){
        tipoproduto = new TipoProduto();
    }
    
    public void editar(TipoProduto e){
        tipoproduto = e;
    }
    
    public void remover(TipoProduto e){
        tipoproduto = e;
        tipoprodutoFacade.remover(tipoproduto);
    }
    
    public void salvar() {
        tipoprodutoFacade.salvar(tipoproduto);
    }
    
    public List<TipoProduto> listaTodos(){
        return tipoprodutoFacade.listaTodos();
    }
    public List<TipoProduto> listaFiltrando(String parte) {
        return tipoprodutoFacade.listaFiltrando(parte, "descricao");
    }

    public TipoProduto getTipoProduto() {
        return tipoproduto;
    }

    public void setTipoProduto(TipoProduto tipoproduto) {
        this.tipoproduto = tipoproduto;
    }

}
