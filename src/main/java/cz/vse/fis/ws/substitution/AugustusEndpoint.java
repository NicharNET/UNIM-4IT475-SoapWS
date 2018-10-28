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
 * Augustus cipher SOAP endpoint
 * 
 * @author Alexandra Kolpakova
 */
@Endpoint
public class AugustusEndpoint {

	/**
	 * The Augustus cipher component
	 */
    @Autowired
    private Augustus augustus;

    /**
     * The Augustus cipher encryption operation
     * @param request The encryption request
     * @return The encryption response
     */
    @PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "AugustusEncryptRequest")
    @ResponsePayload
    public AugustusEncryptResponse encrypt(@RequestPayload AugustusEncryptRequest request) {

        AugustusEncryptResponse response = new AugustusEncryptResponse();
        String encrypted = this.augustus.encrypt(request.getEncrypt());
        response.setEncrypted(encrypted);

        return response;
    }

    /**
     * The Augustus cipher decryption operation
     * @param request The decryption request
     * @return The decryption response
     */
    @PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "AugustusDecryptRequest")
    @ResponsePayload
    public AugustusDecryptResponse decrypt(@RequestPayload AugustusDecryptRequest request) {

        AugustusDecryptResponse response = new AugustusDecryptResponse();
        String decrypted = this.augustus.decrypt(request.getDecrypt());
        response.setDecrypted(decrypted);

        return response;
    }
}
