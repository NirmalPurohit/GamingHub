/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaming_hub;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nirmal
 */
public class ShowDataServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        try  {
            String ID=request.getParameter("gameid");
             Class.forName("com.mysql.jdbc.Driver"); 
           
            Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/gaming_hub_db","root","");
            String sql="SELECT * from games where gm_id=?";
           // String ID=request.getParameter("gameid");
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,ID);
            ResultSet rs=ps.executeQuery();
            //response.setContentType("image/jpg");
           // DataOutputStream dout=new DataOutputStream();
           
            
            while(rs.next())
            {
               Blob b=rs.getBlob(3);
             //out.printf("here");
            byte buffer[]=b.getBytes(1,(int) b.length());
            File f=new File("C:\\Users\\Nirmal\\AndroidStudioProjects\\GamingHub1\\app\\src\\main\\res\\drawable\\image.jpg");
            FileOutputStream fos=new FileOutputStream(f);
            fos.write(buffer);
                out.println(rs.getString(2)+"\n");
                out.println(rs.getString(4));
            }
            
        }catch(Exception e)
        {
            out.print("Exception");
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShowDataServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShowDataServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShowDataServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShowDataServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
