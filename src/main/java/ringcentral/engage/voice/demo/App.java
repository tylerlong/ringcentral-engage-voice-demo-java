package ringcentral.engage.voice.demo;

import com.ringcentral.RestClient;
import com.ringcentral.RestException;
import com.ringcentral.engagevoice.EngageVoice;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.ResponseBody;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, RestException, com.ringcentral.engagevoice.RestException {
        Dotenv dotenv = Dotenv.load();

        RestClient rc = new RestClient(
                dotenv.get("ENGAGE_VOICE_CLIENT_ID"),
                dotenv.get("ENGAGE_VOICE_CLIENT_SECRET"),
                dotenv.get("ENGAGE_VOICE_RC_SERVER_URL")
        );

        rc.authorize(
                dotenv.get("ENGAGE_VOICE_USERNAME"),
                dotenv.get("ENGAGE_VOICE_EXTENSION"),
                dotenv.get("ENGAGE_VOICE_PASSWORD")
        );

        EngageVoice engageVoice = new EngageVoice(dotenv.get("ENGAGE_VOICE_SERVER_URL"));
        engageVoice.authorize(rc.token.access_token);
        ResponseBody responseBody = engageVoice.get("/voice/api/v1/admin/accounts");
        String s = responseBody.string();
        System.out.println(s);
    }
}
