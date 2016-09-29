/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidades.Estado;
import entidades.Pessoa;
import entidades.PessoaFisica;
import entidades.PessoaJuridica;
import facade.EstadoFacade;
import facade.PessoaFacade;
import facade.PessoaFisicaFacade;
import facade.PessoaJuridicaFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jaimedias
 */
@ManagedBean
@SessionScoped
public class PessoaControle {
    
    @EJB
    private PessoaFisicaFacade pessoaFisicaFacade;
    @EJB
    private PessoaJuridicaFacade pessoaJuridicaFacade;
    
    @ManagedProperty(value = "#{pessoaFisicaControle}")
    private PessoaFisicaControle pessoaFisicaControle;
    @ManagedProperty(value = "#{pessoaJuridicaControle}")
    private PessoaJuridicaControle pessoaJuridicaControle;

    public List<Pessoa> listaTodos(){
        List<Pessoa> lista = new ArrayList<Pessoa>();
        lista.addAll(pessoaFisicaFacade.listaTodos());
        lista.addAll(pessoaJuridicaFacade.listaTodos());
        return lista;
    }
    
    public String editar(Pessoa p){
        if(p instanceof PessoaFisica){
            pessoaFisicaControle.editar((PessoaFisica)p);
            return "editapessoafisica";
        }else{
            pessoaJuridicaControle.editar((PessoaJuridica)p);
            return "editapessoajuridica";
        }
    }
    
    public void remover(Pessoa p){
        if(p instanceof PessoaFisica){
            pessoaFisicaControle.remover((PessoaFisica)p);
        }else{
            pessoaJuridicaControle.remover((PessoaJuridica)p);
        }
    }

    public PessoaFisicaControle getPessoaFisicaControle() {
        return pessoaFisicaControle;
    }

    public void setPessoaFisicaControle(PessoaFisicaControle pessoaFisicaControle) {
        this.pessoaFisicaControle = pessoaFisicaControle;
    }

    public PessoaJuridicaControle getPessoaJuridicaControle() {
        return pessoaJuridicaControle;
    }

    public void setPessoaJuridicaControle(PessoaJuridicaControle pessoaJuridicaControle) {
        this.pessoaJuridicaControle = pessoaJuridicaControle;
    }
    
    

}
