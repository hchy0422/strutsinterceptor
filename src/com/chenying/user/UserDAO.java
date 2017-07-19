package com.chenying.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class UserDAO {
	private Connection con;
	
	protected void connect(){
		String url = "jdbc:mysql://localhost:3306/User";
		String username = "root";
		String password = "adsfdg1001YY";
		try {
			if (con == null || con.isClosed()) {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			}
				
				
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void disconnect(){
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insert(User user) {
		String sql = "INSERT INTO student (firstname, lastname, email) VALUES (?, ?, ?)";
		connect();
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getFirstName());
			st.setString(2, user.getLastName());
			st.setString(3, user.getEmail());
			
			st.executeUpdate();
			st.close();
			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<User> listAllUser(){
		List<User> list = new ArrayList<>();
		String sql = "SELECT * FROM student";
		connect();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int id = rs.getInt("studentID");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String email = rs.getString("email");
				
				User user = new User(id, firstName, lastName, email);
				list.add(user);
			}
			rs.close();
			st.close();
			disconnect();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void delete(User user) {
		String sql = "DELETE FROM student where studentID = ?";
		connect();
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, user.getId());
			
			st.executeUpdate();
			st.close();
			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public User getUserById(int id) {
		User user = null;
		String sql = "SELECT * FROM student WHERE studentID =" + id;
		connect();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String email = rs.getString("email");
				user = new User(id, firstName, lastName, email);
			} else {
				throw new Exception("Could not find the user ID" + id);
			}
			
			
			rs.close();
			st.close();
			disconnect();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public void update(User user) {
		String sql = "UPDATE student SET firstname = ?, lastname = ?, email = ? WHERE studentID = ?";
		connect();
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getFirstName());
			st.setString(2, user.getLastName());
			st.setString(3, user.getEmail());
			st.setInt(4, user.getId());
			
			st.executeUpdate();
			st.close();
			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
