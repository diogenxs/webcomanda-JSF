/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Cidade;
import entidades.PessoaFisica;
import facade.CidadeFacade;
import facade.PessoaFisicaFacade;
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
public class PessoaFisicaControle {

    private PessoaFisica pessoaFisica;
    @EJB
    private PessoaFisicaFacade pessoaFisicaFacade;
    @EJB
    private CidadeFacade cidadeFacade;
    
    private ConverterGenerico cidadeConverter;

    public ConverterGenerico getCidadeConverter() {
        if(cidadeConverter == null){
            cidadeConverter = new ConverterGenerico(cidadeFacade);
        }
        return cidadeConverter;
    }

    public void setCidadeConverter(ConverterGenerico cidadeConverter) {
        this.cidadeConverter = cidadeConverter;
    }
    
    public void novo(){
        pessoaFisica = new PessoaFisica();
    }
    
    public void editar(PessoaFisica c){
        pessoaFisica = c;
    }
    
    public void remover(PessoaFisica c){
        pessoaFisica = c;
        pessoaFisicaFacade.remover(pessoaFisica);
    }
    
    public void salvar() {
        pessoaFisicaFacade.salvar(pessoaFisica);
    }
    
    public List<PessoaFisica> listaTodos(){
        return pessoaFisicaFacade.listaTodos();
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }
    
    public List<Cidade> listaCidades(String parte){
        return cidadeFacade.listaFiltrando(parte.trim(), "nome");
    }

}
