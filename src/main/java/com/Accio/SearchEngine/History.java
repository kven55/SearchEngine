package com.Accio.SearchEngine;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet("/History")
public class History extends HttpServlet {
    protected void doGet(HttpServletRequest req , HttpServletResponse res)
    {
        Connection connection = DatabaseConnection.getConnection();
        ArrayList<SearchHistoryData> shData = new ArrayList<>();

        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from history");
            while(rs.next())
            {
                SearchHistoryData currData = new SearchHistoryData();
                currData.setKeyword(rs.getString("keyword"));
                currData.setUrl(rs.getString("url"));

                shData.add(currData);
            }

            req.setAttribute("historyResults" , shData);
            RequestDispatcher rd = req.getRequestDispatcher("history.jsp");
            rd.forward(req , res);
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }
}
