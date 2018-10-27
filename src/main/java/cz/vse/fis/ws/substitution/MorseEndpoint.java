package cz.vse.fis.ws.substitution;

import cz.vse.fis.Application;
import cz.vse.fis.ws.generated.MorseDecryptRequest;
import cz.vse.fis.ws.generated.MorseDecryptResponse;
import cz.vse.fis.ws.generated.MorseEncryptRequest;
import cz.vse.fis.ws.generated.MorseEncryptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by Alexandra Kolpakova on 27.10.2018.
 */

@Endpoint
public class MorseEndpoint {

    @Autowired
    private Morse morse;

    @PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "MorseEncryptRequest")
    @ResponsePayload
    public MorseEncryptResponse encrypt(@RequestPayload MorseEncryptRequest request) {

        MorseEncryptResponse response = new MorseEncryptResponse();
        String encrypted = this.morse.encrypt(request.getEncrypt());
        response.setEncrypted(encrypted);

        return response;
    }

    @PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "MorseDecryptRequest")
    @ResponsePayload
    public MorseDecryptResponse decrypt(@RequestPayload MorseDecryptRequest request) {

        MorseDecryptResponse response = new MorseDecryptResponse();
        String decrypted = this.morse.decrypt(request.getDecrypt());
        response.setDecrypted(decrypted);

        return response;
    }
}
