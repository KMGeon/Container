<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>

<script type="text/javascript">
	function checkForm() {
		//���� ���δ� isNaN()  - �Լ��� Ȱ���Ͽ� �˻�
		//is NaN : if Not a Number�� ����
		//���ڸ� false , ���ڰ� �ƴϸ� true

		let val = "������";
		let val2 = 23;
		let val3 = document.frm.name.value;

		console.log(isNaN(val));//true
		console.log(isNaN(val2));//false
		console.log(isNaN(val3));//true

		//�̸��� ���ڷ� ������ �� ����. -> �̸��� ���ڿ��� �Ѵ�.
		if (!isNaN.val3.substr(0, 1)) {
			alert("�̸��� ���ڷ� ������ �� �����ϴ�.")
			document.frm.name.focus();
		}
		form.submit();
	}
</script>
</head>
<body>
	<form action="" name="frm">
		<p>
			�̸�: <input type="text" name="name">
		</p>
		<p>
			<input type="button" value="����" onclick="checkForm()">
		</p>
	</form>
</body>
</html>