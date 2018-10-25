package cz.vse.fis.ws.substitution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import cz.vse.fis.Application;
import cz.vse.fis.ws.generated.CaesarDecryptRequest;
import cz.vse.fis.ws.generated.CaesarDecryptResponse;
import cz.vse.fis.ws.generated.CaesarEncryptRequest;
import cz.vse.fis.ws.generated.CaesarEncryptResponse;

@Endpoint
public class CaesarEndpoint {
	
	@Autowired
	private Caesar caesar;

	@PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "CaesarEncryptRequest")
	@ResponsePayload
	public CaesarEncryptResponse encrypt(@RequestPayload CaesarEncryptRequest request) {
		
		CaesarEncryptResponse response = new CaesarEncryptResponse();
		String encrypted = this.caesar.encrypt(request.getEncrypt(), request.getShift());
		response.setEncrypted(encrypted);

		return response;
	}
	
	@PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "CaesarDecryptRequest")
	@ResponsePayload
	public CaesarDecryptResponse decrypt(@RequestPayload CaesarDecryptRequest request) {
		
		CaesarDecryptResponse response = new CaesarDecryptResponse();
		String decrypted = this.caesar.decrypt(request.getDecrypt(), request.getShift());
		response.setDecrypted(decrypted);

		return response;
	}
}
