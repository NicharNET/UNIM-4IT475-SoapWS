package cz.vse.fis.ws.polyalphabetic;

import cz.vse.fis.Application;
import cz.vse.fis.ws.generated.VigenereDecryptRequest;
import cz.vse.fis.ws.generated.VigenereDecryptResponse;
import cz.vse.fis.ws.generated.VigenereEncryptRequest;
import cz.vse.fis.ws.generated.VigenereEncryptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Vigenere cipher SOAP endpoint
 *
 * Created by Alexandra Kolpakova on 03.11.2018.
 */
@Endpoint
public class VigenereEndpoint {

    /**
     * The Vigenere cipher component
     */
    @Autowired
    private Vigenere vigenere;

    /**
     * The Vigenere cipher encryption operation
     * @param request The encryption request
     * @return The encryption response
     */
    @PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "VigenereEncryptRequest")
    @ResponsePayload
    public VigenereEncryptResponse encrypt(@RequestPayload VigenereEncryptRequest request) {

        VigenereEncryptResponse response = new VigenereEncryptResponse();
        String encrypted = this.vigenere.encrypt(request.getEncrypt(), request.getKey());
        response.setEncrypted(encrypted);

        return response;
    }

    /**
     * The Vigenere cipher decryption operation
     * @param request The decryption request
     * @return The decryption response
     */
    @PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "VigenereDecryptRequest")
    @ResponsePayload
    public VigenereDecryptResponse decrypt(@RequestPayload VigenereDecryptRequest request) {

        VigenereDecryptResponse response = new VigenereDecryptResponse();
        String decrypted = this.vigenere.decrypt(request.getDecrypt(), request.getKey());
        response.setDecrypted(decrypted);

        return response;
    }
}
