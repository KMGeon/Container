<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3>�Է� ����!</h3>
	<%
	//��ũ��Ʋ��
	//request : jsp�� ���ϰ� �������� �������ִ� ���� ��ü
	request.setCharacterEncoding("UTF-8");
	String id=request.getParameter("id");
	String passwd = request.getParameter("passwd");
	%>
	
	<p>���̵�:<%=id %> </p>
	<p>��й�ȣ:<%=passwd %> </p>
</body>
</html>