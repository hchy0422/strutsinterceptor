package com.chenying.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        dao = new UserDAO();
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String action = request.getServletPath();
	    
		String action = request.getParameter("operation");
		if(action != null) {
			if (action.equals("insert")) {
				insertUser(request, response);
			} else if (action.equals("new")) {
				showNewForm(request, response);
			} else if (action.equals("delete")) {
				deleteUser(request, response);
			}  else if (action.equals("edit")) {
				editUser(request, response);
			} else if (action.equals("update")) {
				updateUser(request, response);
			}
			else {
				listUser(request, response);
			}
		} else {
			listUser(request, response);
		}
		
		
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("first");
		String lastName = request.getParameter("last");
		String email = request.getParameter("email");
		User user = new User(id, firstName, lastName, email);
		dao.update(user);
		listUser(request, response);
	}

	private void editUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		int id = Integer.parseInt(request.getParameter("id"));
		User user = dao.getUserById(id);
		request.setAttribute("user", user);
		showUpdateForm(request, response);
		
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		int id = Integer.parseInt(request.getParameter("id"));
		User user = new User(id);
        dao.delete(user);
        listUser(request, response);
		
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateForm.jsp");
		dispatcher.forward(request, response);
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		// TODO Auto-generated method stub
		List<User> list = dao.listAllUser();
		request.setAttribute("listUser", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListUser.jsp");
		dispatcher.forward(request, response);
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		// TODO Auto-generated method stub
		String firstName = request.getParameter("first");
		String lastName = request.getParameter("last");
		String email = request.getParameter("email");
		
		User user = new User(firstName, lastName, email);
		dao.insert(user);
		listUser(request, response);
	}

	

}
