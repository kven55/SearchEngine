<%@page import = "java.util.ArrayList"%>
<%@page import = "com.Accio.SearchEngine.SearchHistoryData"%>

<html>
    <head>
        <link rel = "stylesheet" type = "text/css" href = "Style.css">
    </head>

    <body>

       <h1> Basic Search Engine </h1>

       <form action = "Search">
               <input type = "text" name = "searchKeyword" class = "TextBox">
               <input type = "submit" value = "Search" class = "button">
       </form>

       <form action = "History">
                   <input type = "submit" value = "History" class = "button">
       </form>

        <table>
            <tr>

                <th> Page Keyword </th>
                <th> Page Url </th>

            </tr>

            <%
                ArrayList<SearchHistoryData> list = (ArrayList<SearchHistoryData>)request.getAttribute("historyResults");
                for(SearchHistoryData hd : list)
                {
            %>
            <tr>
                <td> <% out.print(hd.getKeyword()); %> </td>
                <td> <a href = "<% out.print(hd.getUrl()); %>"> <% out.print(hd.getUrl()); %> </a> </td>
            </tr>

            <%
                }
            %>


        </table>
    </body>
</html>