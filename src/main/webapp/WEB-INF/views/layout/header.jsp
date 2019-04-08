<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-xl">
    <div class="container h-100">
        <a class="navbar-brand" href="index.html">
            <h1 class="tm-site-title mb-0">ORACLE ERP APPLICATION</h1>
        </a>
        <button class="navbar-toggler ml-auto mr-0" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <i class="fas fa-bars tm-nav-icon"></i>
        </button>
	
		<c:if test="${navStatus eq true}">
		 <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mx-auto h-100">
                <li class="nav-item">
                	<c:if test="${TabActive eq 'DashBoard' }">
                    <a class="nav-link active" href="home">
                        <i class="fas fa-tachometer-alt"></i>
                        Dashboard
                        <span class="sr-only">(current)</span>
                    </a>                	
                	</c:if>
                	<c:if test="${TabActive ne 'DashBoard' }">
                    <a class="nav-link" href="home">
                        <i class="fas fa-tachometer-alt"></i>
                        Dashboard
                        <span class="sr-only">(current)</span>
                    </a>                	
                	</c:if>
                	
                </li>
                <li class="nav-item">
                <c:if test="${TabActive eq 'Products'}">
                    <a class="nav-link active" href="products">
                        <i class="fas fa-shopping-cart"></i>
                        Products
                    </a>
                 </c:if>   
                <c:if test="${TabActive ne 'Products' }">
                    <a class="nav-link" href="products">
                        <i class="fas fa-shopping-cart"></i>
                        Products
                    </a>
                 </c:if>   
                </li>
<!--                 <li class="nav-item dropdown">

                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                        <i class="far fa-file-alt"></i>
                        <span>
                            Reports <i class="fas fa-angle-down"></i>
                        </span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Daily Report</a>
                        <a class="dropdown-item" href="#">Weekly Report</a>
                        <a class="dropdown-item" href="#">Yearly Report</a>
                    </div>
                </li>
 -->
                <li class="nav-item">
                <c:if test="${TabActive eq 'OpenCart' }">
                    <a class="nav-link active" href="openCart">
                        <i class="fas fa-shopping-cart"></i>
                        Cart(<c:if test="${sessionScope.productList != null && sessionScope.productList.size()>0}">${sessionScope.productList.size()}</c:if>
                        	<c:if test="${sessionScope.productList == null || sessionScope.productList.size()==0}">0</c:if>)
                    </a>
                 </c:if>   
                <c:if test="${TabActive ne 'OpenCart' }">
                    <a class="nav-link" href="openCart">
                        <i class="fas fa-shopping-cart"></i>
                        Cart(<c:if test="${sessionScope.productList != null && sessionScope.productList.size()>0}">${sessionScope.productList.size()}</c:if>
                        	<c:if test="${sessionScope.productList == null || sessionScope.productList.size()==0}">0</c:if>)
                    </a>
                 </c:if>   
                </li>
                
                <li class="nav-item">
                <c:if test="${TabActive eq 'OrderHistory' }">
                    <a class="nav-link active" href="orderHistory">
                        <i class="fas fa-shopping-cart"></i>
                        Order History
                    </a>
                </c:if>    
                <c:if test="${TabActive ne 'OrderHistory' }">
                    <a class="nav-link" href="orderHistory">
                        <i class="fas fa-shopping-cart"></i>
                        Order History
                    </a>
                </c:if>    
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-cog"></i>
                        <span>
                            Settings <i class="fas fa-angle-down"></i>
                        </span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Profile</a>
                        <a class="dropdown-item" href="#">Billing</a>
                        <a class="dropdown-item" href="#">Customize</a>
                    </div>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link d-block" href="login.html">
                        Admin, <b>Logout</b>
                    </a>
                </li>
            </ul>
        </div>
		</c:if>
       
    </div>

</nav>