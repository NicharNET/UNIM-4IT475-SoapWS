package cz.vse.fis.ws.polyalphabetic;

import cz.vse.fis.Application;
import cz.vse.fis.ws.generated.PolybiusSquareDecryptRequest;
import cz.vse.fis.ws.generated.PolybiusSquareDecryptResponse;
import cz.vse.fis.ws.generated.PolybiusSquareEncryptRequest;
import cz.vse.fis.ws.generated.PolybiusSquareEncryptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Polybius Square cipher SOAP endpoint
 * 
 * @author Alexandra Kolpakova
 */
@Endpoint
public class PolybiusSquareEndpoint {

	/**
	 * The Polybius Square cipher component
	 */
    @Autowired
    private PolybiusSquare polybiusSquare;

    /**
     * The Polybius Square cipher encryption operation
     * @param request The encryption request
     * @return The encryption response
     */
    @PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "PolybiusSquareEncryptRequest")
    @ResponsePayload
    public PolybiusSquareEncryptResponse encrypt(@RequestPayload PolybiusSquareEncryptRequest request) {

        PolybiusSquareEncryptResponse response = new PolybiusSquareEncryptResponse();
        String encrypted = this.polybiusSquare.encrypt(request.getEncrypt(), request.getKey());
        response.setEncrypted(encrypted);

        return response;
    }
    
    /**
     * The Polybius Square cipher decryption operation
     * @param request The decryption request
     * @return The decryption response
     */
    @PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "PolybiusSquareDecryptRequest")
    @ResponsePayload
    public PolybiusSquareDecryptResponse decrypt(@RequestPayload PolybiusSquareDecryptRequest request) {

        PolybiusSquareDecryptResponse response = new PolybiusSquareDecryptResponse();
        String decrypted = this.polybiusSquare.decrypt(request.getDecrypt(), request.getKey());
        response.setDecrypted(decrypted);

        return response;
    }
}
