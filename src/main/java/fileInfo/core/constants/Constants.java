package fileInfo.core.constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Constants used in the project
 */
public class Constants {
    public static final String FILE_EXTENSION_URL = "http://filext.com/alphalist.php?extstart=^{0}";
    public static final String MIME_TYPE_URL = "http://svn.apache.org/repos/asf/httpd/httpd/trunk/docs/conf/mime.types";
    public static final String NOT_FOUND = "NOT_FOUND";
    public static final String FILE_NAME = "{0}.{1}";

    /**
     * This methods makes a get call on the passed url
     * and returns the recieved string in response.
     * Incase the call fails null is returned.
     *
     * @param url to which Get call needs to be made
     * @return String from the GET call
     * @throws IOException
     */
    public static String getCall(String url) throws IOException{
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        StringBuffer response = null;
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine).append("\n");
            }
            in.close();
        }
        return response == null ? null : response.toString();
    }
}
