<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ShopperStop.com</title>
</head>
<body>


        <%
        if (session.getAttribute("user") == null ){// THen new user, show join now
            %>
            <jsp:include page="header.jsp"></jsp:include>
        <%
        }else {
            %>
           <!--   <jsp:include page="logout.jsp"></jsp:include> -->
           
        <%
        }
        %>
<%

        if ((request.getAttribute("message") == null) || (request.getAttribute("messageDetails") == null)){
        // if email session is set, Dont show the message redirect to index
            //response.sendRedirect("/index.jsp");
        }
%>

        <div class="container">
            <div>

                <div style="padding: 10px;">
                    <h1>
                        <% out.print(request.getAttribute("message"));%></h1>

                    
                    <h5 style="padding: 10px;">
                        <%                                                                
                            out.print(request.getAttribute("messageDetail"));
                        %>
                    </h5>
                </div>
            </div>
        </div>


</body>
</html>