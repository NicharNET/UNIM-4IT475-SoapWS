package cz.vse.fis.ws.substitution;

import cz.vse.fis.Application;
import cz.vse.fis.ws.generated.AugustDecryptRequest;
import cz.vse.fis.ws.generated.AugustDecryptResponse;
import cz.vse.fis.ws.generated.AugustEncryptRequest;
import cz.vse.fis.ws.generated.AugustEncryptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by Alexandra Kolpakova on 27.10.2018.
 */

@Endpoint
public class AugustEndpoint {

    @Autowired
    private August august;

    @PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "AugustEncryptRequest")
    @ResponsePayload
    public AugustEncryptResponse encrypt(@RequestPayload AugustEncryptRequest request) {

        AugustEncryptResponse response = new AugustEncryptResponse();
        String encrypted = this.august.encrypt(request.getEncrypt());
        response.setEncrypted(encrypted);

        return response;
    }

    @PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "AugustDecryptRequest")
    @ResponsePayload
    public AugustDecryptResponse decrypt(@RequestPayload AugustDecryptRequest request) {

        AugustDecryptResponse response = new AugustDecryptResponse();
        String decrypted = this.august.decrypt(request.getDecrypt());
        response.setDecrypted(decrypted);

        return response;
    }
}
