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
  </head>

  <body id="reportsPage">
    <div class="container mt-5">
    <form id="myForm" name="myForm" action="createPO" method="post">
      <div class="row tm-content-row">
        <div class="col-sm-12 col-md-12 col-lg-8 col-xl-8 tm-block-col">
          <div class="tm-bg-primary-dark tm-block tm-block-products">
            <div class="tm-product-table-container">
              <table class="table table-hover tm-table-small tm-product-table" onmouseover="getTotal()">
                <thead>
                  <tr>
                  	<th scope="col">LINE NO</th>
                    <th scope="col">PURCHASE ORDER NO</th>
                    <th scope="col">CREATION DATE</th>
                    <th scope="col">TOTAL PRICE</th>
                    <th scope="col">&nbsp;</th>
                  </tr>
                </thead>
                <tbody>
                <c:if test="${PurchaseOrderList.size() > 0}">
                  <c:forEach items="${PurchaseOrderList}" var="purchaseOrder">
                    <tr>
                      <td class="tm-product-name">${purchaseOrder.poId }</td>
                      <td class="tm-product-name"><a href="viewPO?purchaseOrderNumber=${purchaseOrder.purchaseOrderNumber }">${purchaseOrder.purchaseOrderNumber }</a></td>
                      <td class="tm-product-name">${purchaseOrder.createdDate }</td>
                      <td class="tm-product-name">${purchaseOrder.totalPrice }</td>
                      <th scope="row"><input type="checkbox" value="${purchaseOrder.poId}" name="cartlist" id="cartlist" /></th>
                    </tr>
                  </c:forEach>
                </c:if>
                </tbody>
              </table>
              <c:if test="${products.size() == 0}">
                There is no child products...
              </c:if>
            </div>
            <!-- table container -->
<!--            <button class="btn btn-primary btn-block text-uppercase">
              Delete selected products
            </button>
            -->
          </div>
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