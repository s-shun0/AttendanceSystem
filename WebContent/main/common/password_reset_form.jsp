<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>パスワードリセット</title>
</head>
<body>
<h2>パスワードリセット</h2>

<%
    String token = request.getParameter("token");
    if(token == null || token.isEmpty()) {
%>
    <p>無効なURLです。</p>
<%
    } else {
%>
    <form action="/attendsystem/Main/PasswordResetExecute.action" method="post">
        <input type="hidden" name="token" value="<%= token %>">
        <label>新しいパスワード：</label>
        <input type="password" name="newPassword" required>
        <br><br>
        <input type="submit" value="パスワードを変更">
    </form>
<%
    }
%>

</body>
</html>
