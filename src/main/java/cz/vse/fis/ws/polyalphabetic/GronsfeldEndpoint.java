package cz.vse.fis.ws.polyalphabetic;

import cz.vse.fis.Application;
import cz.vse.fis.ws.generated.GronsfeldDecryptRequest;
import cz.vse.fis.ws.generated.GronsfeldDecryptResponse;
import cz.vse.fis.ws.generated.GronsfeldEncryptRequest;
import cz.vse.fis.ws.generated.GronsfeldEncryptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Gronsfeld cipher SOAP endpoint
 * 
 * @author Nikolas Charalambidis
 */
@Endpoint
public class GronsfeldEndpoint {

    /**
     * The Gronsfeld cipher component
     */
    @Autowired
    private Gronsfeld gronsfeld;

    /**
     * The Gronsfeld cipher encryption operation
     * @param request The encryption request
     * @return The encryption response
     */
    @PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "GronsfeldEncryptRequest")
    @ResponsePayload
    public GronsfeldEncryptResponse encrypt(@RequestPayload GronsfeldEncryptRequest request) {

        GronsfeldEncryptResponse response = new GronsfeldEncryptResponse();
        String encrypted = this.gronsfeld.encrypt(request.getEncrypt(), request.getKey());
        response.setEncrypted(encrypted);

        return response;
    }

    /**
     * The Gronsfeld cipher decryption operation
     * @param request The decryption request
     * @return The decryption response
     */
    @PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "GronsfeldDecryptRequest")
    @ResponsePayload
    public GronsfeldDecryptResponse decrypt(@RequestPayload GronsfeldDecryptRequest request) {

        GronsfeldDecryptResponse response = new GronsfeldDecryptResponse();
        String decrypted = this.gronsfeld.decrypt(request.getDecrypt(), request.getKey());
        response.setDecrypted(decrypted);

        return response;
    }
}
