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
      <div class="row tm-content-row">
        <div class="col-sm-12 col-md-12 col-lg-8 col-xl-8 tm-block-col">
          <div class="tm-bg-primary-dark tm-block tm-block-products">
            <a
                    href="add-product.html"
                    class="btn btn-primary btn-block text-uppercase mb-3">Add To Cart</a>
            <div class="tm-product-table-container">
              <table class="table table-hover tm-table-small tm-product-table">
                <thead>
                  <tr>
                    <th scope="col">PRODUCT CODE</th>
                    <th scope="col">PRODUCT NAME</th>
                    <th scope="col">DESCRIPTION</th>
                    <th scope="col">COST</th>
                    <th scope="col">MOQ</th>
                    <th scope="col">MOQ1</th>
                    <th scope="col">MOQ2</th>
                    <th scope="col">MOQ3</th>
                    <th scope="col">&nbsp;</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach items="${childProducts}" var="product">
                  <tr>
                    <td class="tm-product-name">${product.productCode }</td>
                    <td class="tm-product-name">${product.name }</td>
                    <td>${product.description }</td>
                    <td>${product.cost }</td>
                    <td>${product.moq }</td>
                    <td>${product.moq }1</td>
                    <td>${product.moq }2</td>
                    <td>${product.moq }3</td>
                    <th scope="row"><input type="checkbox" /></th>
                  </tr>
                </c:forEach>

                </tbody>
              </table>
            </div>
            <!-- table container -->
<!--            <button class="btn btn-primary btn-block text-uppercase">
              Delete selected products
            </button>
            -->
          </div>
        </div>
      </div>
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