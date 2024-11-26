package com.Accio.SearchEngine;

import com.Accio.SearchEngine.DatabaseConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException
    {
        String keyword = req.getParameter("searchKeyword");

        if(keyword == null || keyword.isEmpty())
            res.sendRedirect("index.jsp");

        else
        {
            ArrayList<SearchResultData> list = new ArrayList<>(); //used for packing the data to be used by frontend

            try {
                Connection conn = DatabaseConnection.getConnection();
                Statement st = conn.createStatement();

                //inserting the searched keyword in history table for future use in fetching history
                PreparedStatement ps = conn.prepareStatement("insert into history(keyword , url) values(? , ?)");
                ps.setString(1 , keyword);
                ps.setString(2 , "http://localhost:8080/SearchEngine/Search?searchKeyword="+keyword);
                ps.executeUpdate();



                //APPLYING RANKING ALGORITHM DURING SEARCHING IN DB(BASED ON PRIORITY OF PAGES WITH MORE KEYWORD COUNT)
                String query = " select PageTitle , PageUrl , ( (length(lower(PageText)) - length(replace(lower(PageText) , '"+keyword.toLowerCase()+"' , ''))) / length('"+keyword+"'))  as countOcc from pageData order by countOcc desc limit 30 ;";
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    SearchResultData srData = new SearchResultData();
                    srData.setTitle(rs.getString("pagetitle"));
                    srData.setUrl(rs.getString("pageurl"));

                    list.add(srData);
                }

                req.setAttribute("srData" , list);
                RequestDispatcher rd = req.getRequestDispatcher("search.jsp");
                rd.forward(req , res);

            }

            catch(Exception e){
                e.printStackTrace();
            }
        }


    }
}
