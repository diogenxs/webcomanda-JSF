/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidades.Estado;
import facade.EstadoFacade;
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
public class EstadoControle {

    private Estado estado;
    @EJB
    private EstadoFacade estadoFacade;

    public void novo(){
        estado = new Estado();
    }
    
    public void editar(Estado e){
        estado = e;
    }
    
    public void remover(Estado e){
        estado = e;
        estadoFacade.remover(estado);
    }
    
    public void salvar() {
        estadoFacade.salvar(estado);
    }
    
    public List<Estado> listaTodos(){
        return estadoFacade.listaTodos();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
