    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package facade;

    import entidades.Comanda;
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
    public class ComandaFacade extends AbstractFacade<Comanda> {

        @PersistenceContext(unitName = "webmobilejsfPU")
        private EntityManager em;

        @Override
        protected EntityManager getEntityManager() {
            return em;
        }

        public ComandaFacade() {
            super(Comanda.class);
        }

            public List<Comanda> listaDisponivel(String parte) {
            String hql = "from Comanda where status = 'L' and numero like '%" + parte + "%' ";
            Query q = getEntityManager().createQuery(hql);
            return q.getResultList();
        }

        public List<Comanda> listaIndisponivel(String parte) {
            String hql = "from Comanda where status = 'R' and numero like '%" + parte + "%' ";
            Query q = getEntityManager().createQuery(hql);
            return q.getResultList();
        }

    }
