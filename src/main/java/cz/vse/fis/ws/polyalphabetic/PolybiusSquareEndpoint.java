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
 * Created by Alexandra Kolpakova on 27.10.2018.
 */

@Endpoint
public class PolybiusSquareEndpoint {

    @Autowired
    private PolybiusSquare polybiusSquare;


    @PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "PolybiusSquareEncryptRequest")
    @ResponsePayload
    public PolybiusSquareEncryptResponse encrypt(@RequestPayload PolybiusSquareEncryptRequest request) {

        PolybiusSquareEncryptResponse response = new PolybiusSquareEncryptResponse();
        String encrypted = this.polybiusSquare.encrypt(request.getEncrypt(), request.getKey());
        response.setEncrypted(encrypted);

        return response;
    }

    @PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "PolybiusSquareDecryptRequest")
    @ResponsePayload
    public PolybiusSquareDecryptResponse decrypt(@RequestPayload PolybiusSquareDecryptRequest request) {

        PolybiusSquareDecryptResponse response = new PolybiusSquareDecryptResponse();
        String decrypted = this.polybiusSquare.decrypt(request.getDecrypt(), request.getKey());
        response.setDecrypted(decrypted);

        return response;
    }
}
