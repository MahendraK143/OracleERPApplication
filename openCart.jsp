<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Product Page - Admin HTML Template</title>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Roboto:400,700"
    />
    <!-- https://fonts.google.com/specimen/Roboto -->
    <link rel="stylesheet" href="css/fontawesome.min.css" />
    <!-- https://fontawesome.com/ -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <!-- https://getbootstrap.com/ -->
    <link rel="stylesheet" href="css/templatemo-style.css">
    <!--
	Product Admin CSS Template
	https://templatemo.com/tm-524-product-admin
	-->
	<style type="text/css">
	#myform {
    text-align: center;
    padding: 5px;
    border: 1px dotted #ccc;
    margin: 2%;
}
.qty {
    width: 40px;
    height: 25px;
    text-align: center;
}
input.qtyplus { width:25px; height:25px;}
input.qtyminus { width:25px; height:25px;}
	</style>
  </head>

  <body id="reportsPage">
    <div class="container mt-5">
    <form id="myForm" name="myForm" action="createPO" method="post">
    
      <div class="row tm-content-row">
        <div class="col-sm-12 col-md-12 col-lg-8 col-xl-8 tm-block-col">
          <div class="tm-bg-primary-dark tm-block tm-block-products">
          <c:if test="${ViewPO ne true}">
   			<input type="hidden" id="productsInCart" name="productsInCart" value="${sessionScope.productsInCart}" >
          	<input type="submit" class="btn btn-primary btn-block text-uppercase mb-3" value="Create Purchase Order">
            <input type="hidden" name="sizeOfList" id="sizeOfList" value="${sizeOfList}">
          </c:if>
            <div class="tm-product-table-container">
              <table class="table table-hover tm-table-small tm-product-table" onmouseover="getTotal()">
                <thead>
                  <tr>
                  	<th scope="col">LINE NO</th>
                    <th scope="col">PRODUCT CODE</th>
                    <th scope="col">PRODUCT NAME</th>
                    <th scope="col">DESCRIPTION</th>
                    <th scope="col">MOQ</th>
                    <th scope="col">MOQ1</th>
                    <th scope="col">COST</th>
                    <th scope="col">QUANTITY</th>
                    <th scope="col">TOTAL</th>
                    <c:if test="${ViewPO ne true}">
                    	<th scope="col">&nbsp;</th>
                    </c:if>
                  </tr>
                </thead>
                <tbody>
                <c:if test="${cartList.size() > 0}">
                  <c:forEach items="${cartList}" var="product">
                    <tr>
                      <td class="tm-product-name">${product.lineNo }</td>
                      <td class="tm-product-name">${product.productCode }</td>
                      <td class="tm-product-name">${product.name }</td>
                      <td>${product.description }</td>
                      <td>${product.moq }</td>
                      <td>${product.moq }1</td>
                      <td>${product.cost }</td>
                      <c:if test="${ViewPO ne true}">
	                      <td>
							<input type="number" id="quantity${product.lineNo}" min="1" max="1000" name='quantity${product.lineNo}' onclick="return calculateTotal(this.value,'${product.lineNo }','${product.cost }');" onkeyup="return calculateTotal(this.value,'${product.productCode }','${product.cost }');" value='${product.quantity }' onmouseover="return calculateTotal(this.value,'${product.productCode }','${product.cost }');" value='${product.quantity }' class='qty' />
	                      </td>
	                      <td><input type="text" id="total${product.lineNo}" name="total${product.lineNo}" value="${product.cost }" width="100px;" readonly="readonly"> </td>
	                      <th scope="row"><input type="checkbox" value="${product.productCode}" name="cartlist" id="cartlist" /></th>
                      </c:if>
                      <c:if test="${ViewPO eq true}">
	                      <td>${product.quantity }</td>
	                      <td>${product.totalCostByProduct }</td>
                      </c:if>
                      
                    </tr>
                  </c:forEach>
                </c:if>
                </tbody>
              </table>
            </div>
	        <div align="right" style="color: white;">
	              <c:if test="${ViewPO ne true}">
	              	Total Price:<input type="text" id="totalPrice" name="totalPrice" value="${totalPrice }" readonly="readonly"> 
	              </c:if>
	              <c:if test="${ViewPO eq true}">
	              	Total Price:${purchaseOrder.totalPrice } 
	              </c:if>
	        </div>
	              
	              <c:if test="${products.size() == 0}">
	                There is no child products...
	              </c:if>
            
            <!-- table container -->
<!--            <button class="btn btn-primary btn-block text-uppercase">
              Delete selected products
            </button>
            -->
          </div>
      </div>    
    </form>

    </div>

    <script src="js/jquery-3.3.1.min.js"></script>
    <!-- https://jquery.com/download/ -->
    <script src="js/bootstrap.min.js"></script>
    <!-- https://getbootstrap.com/ -->
    <script>
      $(function() {
        $(".tm-product-name").on("click", function() {
          window.location.href = "edit-product.html";
        });
      });
    </script>
  </body>
</html>