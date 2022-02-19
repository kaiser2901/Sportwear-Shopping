/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.Products;
import entity.Storages;
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
import model.ProductsFacadeLocal;
import model.StoragesFacadeLocal;

/**
 *
 * @author longv
 */
@WebServlet(name = "HomeServlet", urlPatterns = "/Home/*")
public class HomeServlet extends HttpServlet {
    @EJB
    private StoragesFacadeLocal storagesFacade;
    
    @EJB
    private ProductsFacadeLocal productFacade;
    RequestDispatcher dispatcher;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String action = request.getPathInfo();
        
        switch(action){
            case "/":
                getViewHome(request, response);
                break;
            case "/About":
                getViewAbout(request, response);
                break;
            default:
                out.print("ok");
                break;
        }
        
    }
    
    private void getViewAbout(HttpServletRequest request, HttpServletResponse response) 
           throws ServletException, IOException{
        dispatcher = request.getRequestDispatcher("/views/page/about.jsp");
        dispatcher.forward(request, response);
    }
    
    private void getViewHome(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        PrintWriter out = response.getWriter();
        List<Storages> listStorage = storagesFacade.getListStorageStatusQuantity();
        request.setAttribute("listProd", listStorage);
//        List<Products> listProd =  productFacade.findByStatus(true);
//        request.setAttribute("listProd", listProd);
        
        dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void getView404(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        dispatcher = request.getRequestDispatcher("/views/error/404.jsp");
        dispatcher.forward(request, response);
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
