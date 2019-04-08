function addProductsTocart(contextPath) {
    var checkboxes = document.getElementsByName("cartlist");
    var productsInCart = document.getElementById("productsInCart").value;
    var currentProducts = [];
    // loop over them all
    //alert(globalCartList+checkboxes.length);
    var count = 0;
    for (var i=0; i<checkboxes.length; i++) {
        // And stick the checked ones onto an array...
    	var item = checkboxes[i].value;
    	//alert(products.search(item));
        if(checkboxes[i].checked && productsInCart.search(item)>=0) {
        	//alert((checkboxes[i].value).search(products));
            alert(checkboxes[i].value+":this product available in cart");
            return false;
        }
        if (checkboxes[i].checked) {
        	count = count +1;
            currentProducts = currentProducts+checkboxes[i].value+",";
        }
    }
    if( count == 0 ) {
    	alert("Please select products before adding to the card");
    	return false;
    }
    if (currentProducts != "") {
    	document.getElementById("productsInCart").value = currentProducts + productsInCart;	
    }
    if (document.getElementById("productsInCart").value == ""){
    	alert("Please select products before adding to the card");
    	return false;
    }
    
    //alert(globalCartList);
}


function calculateTotal(qty,lineNo,cost) {
	//alert(qty+pcode+cost);
	var totalPrice = document.getElementById("totalPrice").value;
	document.getElementById("total"+lineNo).value = parseInt(qty) * parseInt(cost);
	//totalPrice = parseInt((parseInt(totalPrice)-parseInt(parseInt(qty-1) * parseInt(cost)))) + parseInt(parseInt(qty) * parseInt(cost));
	//document.getElementById("totalPrice").value = totalPrice;
	getTotal();
}

function getTotal() {
	var totalprice = 0;
	if (document.getElementById("sizeOfList") != null) {
		var sizeoflist = document.getElementById("sizeOfList").value;
		var lastIndex = parseInt(sizeoflist);
		for (var i = 1; i <=  lastIndex; i += 1) {
			var index = parseInt(i);
			var price = document.getElementById("total"+index).value;
			totalprice = parseFloat(totalprice) + parseFloat(price);
		}
		document.getElementById("totalPrice").value = totalprice;
	}

}