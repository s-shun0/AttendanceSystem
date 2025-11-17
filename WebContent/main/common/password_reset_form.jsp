<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>パスワード再設定</title>
</head>
<body>

<h2>新しいパスワードを入力してください</h2>

<form action="/attendsystem/Main/PasswordResetExecute.action" method="post">
    <input type="hidden" name="token" value="${token}">

    <label>新しいパスワード</label><br>
    <input type="password" name="password" required><br><br>

    <input type="submit" value="パスワードを変更する">
</form>

</body>
</html>
