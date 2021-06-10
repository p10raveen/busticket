/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.configure;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Praveen
 */
public class Record {
    
    public static Connection takeConnection()
    {
        Connection con=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String path="jdbc:mysql://localhost:3306/bus";
            con = DriverManager.getConnection(path,"root","bhopal");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
    
    public static int loginCheck(String email,String pass)
    {
        int i=0;
        try
        {
            Connection con = Record.takeConnection();
            String query="select v_id from vendor where email=? and password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
               i = rs.getInt(1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return i;
    }
    public static int getBusId(String dest)
    {   int id=0;
        try
        {
            Connection con = Record.takeConnection();
            String query="select bus_id from bus_details where destination=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, dest);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                id=rs.getInt(1);
            }
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return id;
    }
    
    public static String getBusAmount(String dest)
    {   String amount="";
        try
        {
            Connection con = Record.takeConnection();
            String query="select amount from bus_details where destination=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, dest);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                amount=rs.getString(1);
            }
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return amount;
    }
    
    public static int setAllDetails(String p_name,String spinner_value,String dest,String p_mobile,String p_id_cat, String p_id_num,double total,int vendor_id,int bus_id)
    {  int i=0;
        try
        {
            Connection con = Record.takeConnection();
            String query= "insert into passenger(name,nopassenger,destination,mobile,Id_category,Id_number,date,Amount,vendor_id,bus_id) values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, p_name);
            ps.setString(2, spinner_value);
            ps.setString(3, dest);
            ps.setString(4,p_mobile);
            ps.setString(5,p_id_cat);
            ps.setString(6,p_id_num);
            ps.setString(7,getCurrentDate());
            ps.setString(8,String.valueOf(total));
            ps.setInt(9, vendor_id);
            ps.setInt(10,bus_id);
            
            i = ps.executeUpdate();
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return i;
    }
    
    public static String getCurrentDate()
    {
        Date d = new Date(); // current Date and time
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String cdate = f.format(d);
        return cdate;
    }
}
