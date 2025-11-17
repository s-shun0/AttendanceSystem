const express = require('express');
const bodyParser = require('body-parser');
const mail = require('./mail');

const app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// パスワードリセット用API
app.post('/send-reset', function(req, res) {
    const email = req.body.email;

    mail.sendResetMail(email, function(err) {
        if (err) {
            console.error(err);
            return res.status(500).json({ message: '送信エラー' });
        }
        res.json({ message: 'メール送信成功' });
    });
});

// 3000番ポートで起動
app.listen(3000, function() {
    console.log('Server started on http://localhost:3000');
});
