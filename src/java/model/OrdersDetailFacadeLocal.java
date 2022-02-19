/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import entity.OrdersDetail;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author longv
 */
@Local
public interface OrdersDetailFacadeLocal {

    void create(OrdersDetail ordersDetail);

    void edit(OrdersDetail ordersDetail);

    void remove(OrdersDetail ordersDetail);

    OrdersDetail find(Object id);

    List<OrdersDetail> findAll();

    List<OrdersDetail> findRange(int[] range);

    int count();

    List<OrdersDetail> getListTop10SellProduct();
    
}
