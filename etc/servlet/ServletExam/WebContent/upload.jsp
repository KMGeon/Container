<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� ���ε� ����</title>
</head>
<body>
	<h3>����ġ ��ī��Ÿ ������Ʈ�� fileupload����� �̿��� ���� ���ε�</h3>
	<!-- ���� �ø���� post -->
	<form action="upload.do" method="post" enctype="multipart/form-data">
		���� ����: <input type="file" name="uploadFile"> ������: <input
			type="text" name="sender">

		<button>���Ͼ��ε�</button>
	</form>
	<!-- form������ �ݵ�� post�� ������. -->
	<hr>
		<h3>������3 ���� �����ϴ� part �������̽� �̿��� ���� ���ε�</h3>
	<!-- ���� �ø���� post -->
	<form action="upload2.do" method="post" enctype="multipart/form-data">
		���� ����: <input type="file" name="uploadFile"> ������: <input
			type="text" name="sender">

		<button>���Ͼ��ε�</button>
	</form>



</body>
</html>