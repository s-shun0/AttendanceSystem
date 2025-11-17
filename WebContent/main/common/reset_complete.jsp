<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>パスワードリセット完了</title>

<link rel="stylesheet" href="../../css/style.css">
<link rel="stylesheet" href="../../css/menu.css">
</head>

<body>
<div class="wrapper">
    <!-- 共通ヘッダー -->
    <div id="header"></div>

    <main class="content" style="text-align:center; margin-top: 60px;">
        <h2 class="fs-3">パスワードの変更が完了しました</h2>

        <p class="fs-5 mt-4">
            パスワードが正常に更新されました。<br>
            ログイン画面から新しいパスワードでログインしてください。
        </p>

        <a href="/attendsystem/login.jsp"
           class="btn btn-primary btn-lg mt-4">
           ログイン画面へ戻る
        </a>
    </main>

    <!-- 共通フッター -->
    <div id="footer"></div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../../js/header.js"></script>
<script src="../../js/footer.js"></script>

</body>
</html>
