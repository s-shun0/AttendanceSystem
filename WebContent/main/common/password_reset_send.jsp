<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="../../css/style.css">
<link rel="stylesheet" href="../../css/menu.css">

<title>出席管理システム</title>

</head>
<body>

	<div class="wrapper">
		<!-- ヘッダー（JSで読み込み） -->
		<div id="header"></div>
		<h2 class="text-password_reset fs-4 mt-5">パスワードリセットURL送信</h2>
		<main class="content">
			<label>メールアドレス</label>
			<input class="form-control px-5 fs-5" autocomplete="off"
				id="password-input" maxlength="20" name="email"
				placeholder="内容未定のため保留中" style="ime-mode: disabled"
				type="email" required />
		</main>
		<input class="send-btn btn btn-lg btn-primary" type="submit" name="login" value="メール送信"/>
		<!-- フッター（JSで読み込み） -->
	    <div id="footer"></div>
    </div>

    <!-- JavaScriptファイルの読み込み -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- jQuery本体 -->
	<script src="../../js/header.js"></script>
	<script src="../../js/footer.js"></script>
</body>
</html>
