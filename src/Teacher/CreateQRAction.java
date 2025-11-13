package Teacher;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateQRAction extends HttpServlet{

	private static String latestToken = "";

	@Override
	protected  void doGet(HttpServletRequest req, HttpServletResponse res
			) throws ServletException, IOException {
		HttpSession session = req.getSession();


		// 新しいトークンを生成（毎回URLが変わる）
        String token = UUID.randomUUID().toString();
        latestToken = token;

        //決まり次第変更
        String qrUrl = "http://localhost:8080/YourApp/verify?token=" + token;

        //qrsize
        int size=200;
        
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(qrUrl, BarcodeFormat.QR_CODE, size, size);
            response.setContentType("image/png");
            MatrixToImageWriter.writeToStream(matrix, "png", response.getOutputStream());
        } catch (WriterException e) {
            e.printStackTrace();
        }
	}

}
