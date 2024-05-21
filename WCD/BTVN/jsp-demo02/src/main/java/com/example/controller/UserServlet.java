package com.example.controller;

import com.example.da.UserDAO;
import com.example.entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;
    public void init() {
        userDAO = new UserDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request,response);
                    break;
                case "/insert":
                    insertUser(request,response);
                    break;
                case "/delete":
                    deleteUser(request,response);
                    break;
                case "/edit":
                    showEditUser(request,response);
                    break;
                case "/update":
                    updateUser(request,response);
                    break;
                default:
                    listUser(request,response);
                    break;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException , ServletException, IOException , ClassNotFoundException {
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException ,ClassNotFoundException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }
    private void showEditUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User(name, email, country);
        userDAO.insertUser(newUser);
        request.setAttribute("user", newUser);
    }
    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User(name, email, country);
        userDAO.insertUser(newUser);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User book = new User(id, name, email, country);
        userDAO.updateUser(book);
        response.sendRedirect("list");
    }
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException , ClassNotFoundException , SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("list");
    }

}
