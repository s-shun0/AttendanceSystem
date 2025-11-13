package tool;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QRcodeServlet extends HttpServlet {
    // メモリ上に一時的に保存（実際はDBでもOK）
    private static String latestToken = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // 新しいトークンを生成（毎回URLが変わる）
        String token = UUID.randomUUID().toString();
        latestToken = token;

        String qrUrl = "http://localhost:8080/YourApp/verify?token=" + token;

        // QRコード生成
        int size = 200;
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(qrUrl, BarcodeFormat.QR_CODE, size, size);
            response.setContentType("image/png");
            MatrixToImageWriter.writeToStream(matrix, "png", response.getOutputStream());
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public static String getLatestToken() {
        return latestToken;
    }
}
