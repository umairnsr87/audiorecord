package com.pucho.audiorecord;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class getData {
    Connection con;
    String connectionresult = "";
    Boolean isSuccess = false;

    public List<String> getdata() {

        List<String> data = new ArrayList<String>();
        try {
            con = MyTask.getCon();
            Log.e("Connection tag", "This is the connection value" + con);
            Statement st = con.createStatement();
            String sql = "select * from registration where email=" + "'" + "umairnsr87@gmail.com" + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                data.add(rs.getString("name"));
                data.add(rs.getString("email"));
            }

        } catch (SQLException e) {
            Log.e("connection tag error1", e.getMessage());
            //     e.printStackTrace();
        } finally {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        }
        return data;
    }

    public void setData(String s, String p) {
        try {
            con = MyTask.getCon();
            Log.e("Connection tag", "This is the connection value" + con);
            String sql = "insert into registration values(?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, s);
            st.setString(2, p);
            st.executeUpdate();
        } catch (SQLException e) {
            Log.e("connection tag error1", e.getMessage());
            //     e.printStackTrace();
        } finally {
//        try {
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        }
    }
}