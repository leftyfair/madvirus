<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<ul>
<c:forEach items="${anseweredData.responses}" var="r" varStatus="s">
	<li>${s.index + 1}번 문항 : ${r}</li>
</c:forEach>
</ul>

<div>
	응답자의 위치 : ${anseweredData.res.location}<br> 
	응답자의 나이 : ${anseweredData.res.age}<br> 
</div>

</body>
</html>