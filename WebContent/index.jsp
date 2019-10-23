<jsp:include page="header.jsp"></jsp:include>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.database.DB_Conn"%>


    <!-- Page Content -->
    
  <section id="header" class="conatiner">
      <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
          <li data-target="#myCarousel" data-slide-to="1"></li>
          <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img class="first-slide" src="images/banner/banner-1.jpg" alt="First slide">
            <div class="container">
              <div class="carousel-caption text-left">
              </div>
            </div>
          </div>
          <div class="carousel-item">
            <img class="second-slide" src="images/banner/banner-2.jpg" alt="Second slide">
            <div class="container">
              <div class="carousel-caption">
                <h1 class="ban">Mid Season Sale.</h1>
                <p class="under">Upto 50% Off <br> On Selected items online and in stores</p>
              </div>
            </div>
          </div>
          <div class="carousel-item">
            <img class="third-slide" src="images/banner/b5.png" alt="Third slide">
            <div class="container">
              <div class="carousel-caption text-right">
              </div>
            </div>
          </div>
        </div>
        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>
  </section>

<%
    Connection c = new DB_Conn().getConnection();
    Statement st = c.createStatement();
    String getCategory = "SELECT * FROM  `category`  ";
    
    ResultSet rs = st.executeQuery(getCategory);
    
%>

<section id="main">    
    <div class="container-fluid">
      <div class="row">
        <nav class="col-sm-3 col-md-2 d-none d-sm-block bg-light sidebar">
          <ul class="nav nav-pills flex-column">
          	<li><b>Categories</b></li>
          	 <%
                while (rs.next()){
                   	int cat_id = rs.getInt("cat_id");
                    String cat = rs.getString ("category");
              %>
                    <li><a href="viewProducts_.jsp"><%= cat %></a>
                      <ol>
                      <%
                       Connection con = new DB_Conn().getConnection();
                       Statement stst = con.createStatement();
 				       String getSubCategory = "SELECT * FROM  `subcategory` where cat_id="+cat_id;
 					   ResultSet sub = stst.executeQuery(getSubCategory);

                           while(sub.next())
                           {
                        	   String subcategory = sub.getString("subcategory");
                       
                      %> 
      				    <li><a href="displayItems.jsp?cat_id=<%=cat_id%>&subcat=<%=subcategory%>"><%= subcategory %></a></li>
      				    <%
                           }
      				    %>
     				</ol>
                    </li>
               <%
                 }
               %>
          </ul>
               
               
   <!-- =================================Middle section==================================== -->      
<%
Connection con1 = new DB_Conn().getConnection();
Statement str = con1.createStatement();
String query = "select * from items where subcatg='Mobiles'";
ResultSet result = str.executeQuery(query);

%>   
   
              
          </nav>
          <div class="col-sm-9 col-md-10 main">
          <h2>Feature <strong>Products</strong></h2>
          <div class="row">
          <% while(result.next()){ %>
          <div class="col-md-4">												
			<div class="span3 thumbnail">
				<div class="product-box">
					<span class="sale_tag"></span>
					  <p><a href="view_product.jsp?itemid=<%=result.getInt("item_id")%>"><img src="<%=result.getString("img") %>" alt="" width="400" height="350" /></a></p>
						 <a href="view_product.jsp?itemid=<%=result.getInt("item_id")%>" class="title"><%=result.getString("desc") %></a><br/>
						 <a href="products.html" class="category"></a>
						 <p class="price">Rs.<%=result.getString("price") %></p>
				</div>
			</div>
			</div>
			<%
             }
			%>
			
			</div><!-- end of inner row -->
          </div><!-- end of outer column -->
     </div><!-- end of outer row -->
     </div><!-- end of container -->
</section>  


<section id="feature">
	<div class="container">
		<div class="row feature_box">
			<div class="col-sm-12 col-md-4">				
				<div class="span4">
					<div class="service">
						<div class="responsive">	
							<img src="images/features/feature_img_2.png" alt="" />
								<h4>MODERN <strong>DESIGN</strong></h4>
								<p>We are improvising the experience of shopping.</p>									
						</div>
					</div>
				</div>
			</div>				
			<div class="col-sm-12 col-md-4">
				<div class="span4">	
					<div class="service">
						<div class="customize">			
							<img src="images/features/feature_img_1.png" alt="" />
							<h4>FREE <strong>SHIPPING</strong></h4>
							<p>No need to pay for shipping. Free goods delivery from shopperStop</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 col-md-4">	
				<div class="span4">
					<div class="service">
						<div class="support">	
							<img src="images/features/feature_img_3.png" alt="" />
							<h4>24/7 LIVE <strong>SUPPORT</strong></h4>
							<p>Contact us anytime our executives will solve your queries</p>
						</div>
					</div>
				</div>	
			</div>	
		</div>	
	</div>				
</section>
<section class="clients">
				<h4 class="title"><span class="text">Manufactures</span></h4>
				<div class="row">					
					<div class="col-sm-4 col-md-2">
						<a href="#"><img alt="" src="images/clients/14.png"></a>
					</div>
					<div class="col-sm-4 col-md-2">
						<a href="#"><img alt="" src="images/clients/35.png"></a>
					</div>
					<div class="col-sm-4 col-md-2">
						<a href="#"><img alt="" src="images/clients/1.png"></a>
					</div>
					<div class="col-sm-4 col-md-2">
						<a href="#"><img alt="" src="images/clients/2.png"></a>
					</div>
					<div class="col-sm-4 col-md-2">
						<a href="#"><img alt="" src="images/clients/3.png"></a>
					</div>
					<div class="col-sm-4 col-md-2">
						<a href="#"><img alt="" src="images/clients/4.png"></a>
					</div>
				</div>
			</section>
     <!-- Including footer content -->

<jsp:include page="footer.jsp"></jsp:include>
