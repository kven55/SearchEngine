<%@page import = "java.util.ArrayList"%>
<%@page import = "com.Accio.SearchEngine.SearchResultData"%>

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
                <th>Page Title</th>
                <th>Page Url</th>
            </tr>
            <%
                ArrayList<SearchResultData> list = (ArrayList<SearchResultData>)request.getAttribute("srData");
                for(SearchResultData res : list){
            %>
            <tr>
                <td><% out.print(res.getTitle()); %></td>
                <td><a href = "<% out.print(res.getUrl()); %>"> <% out.print(res.getUrl()); %></a></td>
            </tr>

            <%
                }
            %>
        </table>
    </body>
</html>