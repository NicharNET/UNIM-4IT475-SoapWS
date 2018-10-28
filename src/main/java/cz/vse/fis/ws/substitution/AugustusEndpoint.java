package cz.vse.fis.ws.substitution;

import cz.vse.fis.Application;
import cz.vse.fis.ws.generated.AugustusDecryptRequest;
import cz.vse.fis.ws.generated.AugustusDecryptResponse;
import cz.vse.fis.ws.generated.AugustusEncryptRequest;
import cz.vse.fis.ws.generated.AugustusEncryptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by Alexandra Kolpakova on 27.10.2018.
 */

@Endpoint
public class AugustusEndpoint {

    @Autowired
    private Augustus augustus;

    @PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "AugustusEncryptRequest")
    @ResponsePayload
    public AugustusEncryptResponse encrypt(@RequestPayload AugustusEncryptRequest request) {

        AugustusEncryptResponse response = new AugustusEncryptResponse();
        String encrypted = this.augustus.encrypt(request.getEncrypt());
        response.setEncrypted(encrypted);

        return response;
    }

    @PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "AugustusDecryptRequest")
    @ResponsePayload
    public AugustusDecryptResponse decrypt(@RequestPayload AugustusDecryptRequest request) {

        AugustusDecryptResponse response = new AugustusDecryptResponse();
        String decrypted = this.augustus.decrypt(request.getDecrypt());
        response.setDecrypted(decrypted);

        return response;
    }
}
