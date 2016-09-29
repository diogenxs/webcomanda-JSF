/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Cidade;
import entidades.PessoaJuridica;
import facade.CidadeFacade;
import facade.PessoaJuridicaFacade;
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
public class PessoaJuridicaControle {

    private PessoaJuridica pessoaJuridica;
    @EJB
    private PessoaJuridicaFacade pessoaJuridicaFacade;
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
        pessoaJuridica = new PessoaJuridica();
    }
    
    public void editar(PessoaJuridica c){
        pessoaJuridica = c;
    }
    
    public void remover(PessoaJuridica c){
        pessoaJuridica = c;
        pessoaJuridicaFacade.remover(pessoaJuridica);
    }
    
    public void salvar() {
        pessoaJuridicaFacade.salvar(pessoaJuridica);
    }
    
    public List<PessoaJuridica> listaTodos(){
        return pessoaJuridicaFacade.listaTodos();
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }
    
    public List<Cidade> listaCidades(String parte){
        return cidadeFacade.listaFiltrando(parte, "nome");
    }

}
