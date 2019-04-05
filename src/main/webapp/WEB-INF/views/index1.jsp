<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Reading List</title>
<style type="text/css">
body {
    background-color: #cccccc;
    font-family: arial,helvetica,sans-serif;
}
.bookHeadline {
    font-size: 12pt;
    font-weight: bold;
}
.bookDescription {
    font-size: 10pt;
}
label {
    font-weight: bold;
}
</style>
</head>
<body>
	<h2>Your Reading List</h2>
	<c:forEach items="${books}" var="book">
		${book.title}-${book.author}-${book.description}<br>
	</c:forEach>
	<div>
		<p>You have no books in your book list</p>
	</div>
	<hr />
	<h3>Add a book</h3>
	<form method="POST" action="addBook">
		<label for="title">Title:</label> 
		<input type="text" name="title"
			size="50"></input><br /> 
		<label for="author">Author:</label> 
		<input
			type="text" name="author" size="50">
		</input>
		<br /> 
		<label for="isbn">ISBN:</label>
		<input type="text" name="isbn" size="15"></input><br /> 
		<label
			for="description">Description:</label><br />
		<textarea name="description" cols="80" rows="5">
        </textarea>
		<br /> <input type="submit"></input>
	</form>
</body>
</html>