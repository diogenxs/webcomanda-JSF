/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Comanda;
import entidades.ItensVenda;
import entidades.Venda;
import entidades.Pessoa;
import entidades.Produto;
import facade.ComandaFacade;
import facade.ItensVendaFacade;
import facade.VendaFacade;
import facade.PessoaFacade;
import facade.ProdutoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import relatorio.Relatorio;

/**
 *
 * @author jaimedias
 */
@ManagedBean
@SessionScoped
public class VendaControle {

    private Venda venda;
    private ItensVenda itensVenda;
    @EJB
    private ItensVendaFacade itensVendaFacade;
    @EJB
    private VendaFacade vendaFacade;
    @EJB
    private PessoaFacade pessoaFacade;
    @EJB
    private ProdutoFacade produtoFacade;
    @EJB
    private ComandaFacade comandaFacade;
    private ConverterGenerico pessoaConverter;
    private ConverterGenerico produtoConverter;
    private ConverterGenerico comandaConverter;

    private Double troco;

    public Double getTroco() {
        return troco;
    }

    public void setTroco(Double troco) {
        this.troco = troco;
    }
    
    public ConverterGenerico getPessoaConverter() {
        if (pessoaConverter == null) {
            pessoaConverter = new ConverterGenerico(pessoaFacade);
        }
        return pessoaConverter;
    }

    public void setPessoaConverter(ConverterGenerico pessoaConverter) {
        this.pessoaConverter = pessoaConverter;
    }

    public ConverterGenerico getProdutoConverter() {
        if (produtoConverter == null) {
            produtoConverter = new ConverterGenerico(produtoFacade);
        }
        return produtoConverter;
    }

    public void setProdutoConverter(ConverterGenerico produtoConverter) {
        this.produtoConverter = produtoConverter;
    }

    public ConverterGenerico getComandaConverter() {
        if (comandaConverter == null) {
            comandaConverter = new ConverterGenerico(comandaFacade);
        }
        return comandaConverter;
    }

    public void setComandaConverter(ConverterGenerico comandaConverter) {
        this.comandaConverter = comandaConverter;
    }

    public void novo() {
        venda = new Venda();
        itensVenda = new ItensVenda();
    }

    public void editar(Venda c) {
        itensVenda = new ItensVenda();
        venda = c;
    }

    public void remover(Venda c) {
        // addMessage("Exclusão realizada", "Venda de Id: " + venda.getId() + " excluída.");
        vendaFacade.remover(c);

    }

    public void salvar() {
        vendaFacade.salvar(venda);
    }

    public void fechar() {
        vendaFacade.fechar(venda);
    }

    public List<Venda> listaTodos() {
        return vendaFacade.listaTodos();
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public List<Pessoa> listaPessoas(String parte) {
        return pessoaFacade.listaFiltrando(parte, "nome");
    }

    public List<Produto> listaProdutos(String parte) {
        return produtoFacade.listaFiltrando(parte, "nome");
    }

    public ItensVenda getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(ItensVenda itensVenda) {
        this.itensVenda = itensVenda;
    }

    public void addItem() {
        Double estoque = itensVenda.getProduto().getEstoque();
        if (itensVenda.getQuantidade() > estoque) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Estoque insuficiente!",
                            "Restam apenas " + estoque + " unidades!"
                    ));
        } else {
            itensVenda.setPreco(itensVenda.getProduto().getPreco());
            itensVenda.setVenda(venda);
            venda.getItensVendas().add(itensVenda);

            itensVenda = new ItensVenda();
        }

    }

    public void removerItem(ItensVenda it) {
        venda.getItensVendas().remove(it);
        itensVendaFacade.remover(it);
        itensVenda = new ItensVenda();
        
    }

    public List<Comanda> getListaComandasIndisponiveis(String parte) {
        return comandaFacade.listaIndisponivel(parte);
    }

    public List<Comanda> listaComandasDisponiveis(String parte) {
        return comandaFacade.listaDisponivel(parte);
    }

    public List<Venda> getListaVendasAbertas() {
        return vendaFacade.listaVendasAbertas();
    }

    public void gerarRelatorio() {
        Relatorio r = new Relatorio();
        r.getRelatorio();
        ///r.getRelatorio(this.listaTodos());

    }
}
