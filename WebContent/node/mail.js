const nodemailer = require('nodemailer');

exports.sendResetMail = function(email, callback) {

    const transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
            user: 'あなたのGmail@gmail.com',
            pass: 'アプリパスワード' // 16桁
        }
    });

    const mailOptions = {
        from: 'あなたのGmail@gmail.com',
        to: email,
        subject: 'パスワードリセット',
        text: '以下のURLをクリックしてパスワードをリセットしてください。\n\nhttp://localhost:8080/reset/reset_complete.jsp'
    };

    transporter.sendMail(mailOptions, function(err, info) {
        if (err) {
            return callback(err);
        }
        callback(null);
    });
};
