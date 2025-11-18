package Main;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class EmailUtil {

    private static String clientId = "YOUR_CLIENT_ID";
    private static String clientSecret = "YOUR_CLIENT_SECRET";
    private static String tenantId = "YOUR_TENANT_ID";
    private static String senderEmail = "youraccount@domain.com";

    public static void sendEmail(String to, String subject, String body) throws Exception {
        String token = getAccessToken();

        String json = new JSONObject()
            .put("message", new JSONObject()
                .put("subject", subject)
                .put("body", new JSONObject()
                    .put("contentType", "Text")
                    .put("content", body))
                .put("toRecipients", new org.json.JSONArray()
                    .put(new JSONObject().put("emailAddress", new JSONObject().put("address", to)))))
            .toString();

        URL url = new URL("https://graph.microsoft.com/v1.0/users/" + senderEmail + "/sendMail");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + token);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        os.close();

        int status = conn.getResponseCode();
        if (status != 202) {
            InputStream is = conn.getErrorStream();
            byte[] errorBytes = is.readAllBytes();
            throw new RuntimeException("メール送信失敗: " + new String(errorBytes));
        }
    }

    private static String getAccessToken() throws Exception {
        String url = "https://login.microsoftonline.com/" + tenantId + "/oauth2/v2.0/token";
        String data = "client_id=" + clientId +
                      "&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default" +
                      "&client_secret=" + clientSecret +
                      "&grant_type=client_credentials";

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        OutputStream os = conn.getOutputStream();
        os.write(data.getBytes());
        os.flush();
        os.close();

        InputStream is = conn.getInputStream();
        String response = new String(is.readAllBytes());
        JSONObject json = new JSONObject(response);
        return json.getString("access_token");
    }
}
