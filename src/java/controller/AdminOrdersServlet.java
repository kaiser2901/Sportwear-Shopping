/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Orders;
import entity.OrdersDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.OrdersDetailFacadeLocal;
import model.OrdersFacadeLocal;

/**
 *
 * @author Tran Thien
 */
@WebServlet(name = "AdminOrdersServlet", urlPatterns = {"/Admin/Orders/*"})
public class AdminOrdersServlet extends HttpServlet {

    @EJB
    private OrdersFacadeLocal orderFacade;
    @EJB
    private OrdersDetailFacadeLocal ordersDetailFacade;
    RequestDispatcher dispatcher;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String action = request.getPathInfo();

        switch (action) {
            case "/List":
                getViewList(request, response);
                break;
            case "/Edit":
                getViewEdit(request, response);
                break;
            case "/Update":
                update(request, response);
                break;
            default:
                out.print("ok");
                break;
        }
    }

    private void getViewList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List<Orders> list = orderFacade.findAll();
        request.setAttribute("list", list);
        dispatcher = request.getRequestDispatcher("/admin/views/order/list.jsp");
        dispatcher.forward(request, response);
    }

    private void getViewEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("id") == null || request.getParameter("id").trim().isEmpty()) {
            response.sendRedirect("List");
        } else {

            int id = Integer.parseInt(request.getParameter("id"));

            Orders orders = orderFacade.find(id);

            request.setAttribute("orders", orders);

            dispatcher = request.getRequestDispatcher("/admin/views/order/edit.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("statusOrder");

        Orders order = new Orders(id, status);

        boolean check = orderFacade.update(order);

        if (check) {
            request.getSession().setAttribute("NotyMessage", "Edit brand successfully");
            response.sendRedirect("List");
        }
      
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
