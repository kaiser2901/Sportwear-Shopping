/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Orders;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author longv
 */
@Stateless
public class OrdersFacade extends AbstractFacade<Orders> implements OrdersFacadeLocal {

    @PersistenceContext(unitName = "NHLSportPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }

    @Override
    public List<Orders> findByCusId(Object cusId) {
        Query q = em.createQuery("SELECT a FROM Orders a WHERE a.customerId = :cusId");
        q.setParameter("cusId", cusId);

        return q.getResultList();
    }

    @Override
    public boolean update(Orders orders) {
        Query q = em.createQuery("UPDATE Orders c SET c.status =:status WHERE c.orderId =:orderId");
        q.setParameter("orderId", orders.getOrderId());
        q.setParameter("status", orders.getStatus());
        return q.executeUpdate() > 0;
    }

}
