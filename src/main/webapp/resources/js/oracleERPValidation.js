var globalCartList = "";
function addProductsTocart(contextPath) {
    var checkboxes = document.getElementsByName("cartlist");
    var checkboxesChecked = [];
    // loop over them all
    alert(globalCartList);
    for (var i=0; i<checkboxes.length; i++) {
        // And stick the checked ones onto an array...
        if(checkboxes[i].checked && (checkboxes[i].value).search(globalCartList)) {
            alert("this product available in cart");
            return false;
        }
        if (checkboxes[i].checked) {
            checkboxesChecked.push(checkboxes[i].value);
            globalCartList=checkboxes[i].value+",";
        }
    }
    alert(globalCartList);
}