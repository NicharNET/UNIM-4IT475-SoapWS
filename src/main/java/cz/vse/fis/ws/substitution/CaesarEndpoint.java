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

/**
 * Caesar cipher SOAP endpoint
 * 
 * @author Nikolas Charalambidis
 */
@Endpoint
public class CaesarEndpoint {
	
	/**
	 * The Caesar cipher component
	 */
	@Autowired
	private Caesar caesar;

	/**
     * The Caesar cipher encryption operation
     * @param request The encryption request
     * @return The encryption response
     */
	@PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "CaesarEncryptRequest")
	@ResponsePayload
	public CaesarEncryptResponse encrypt(@RequestPayload CaesarEncryptRequest request) {
		
		CaesarEncryptResponse response = new CaesarEncryptResponse();
		String encrypted = this.caesar.encrypt(request.getEncrypt(), request.getShift());
		response.setEncrypted(encrypted);

		return response;
	}
	
	/**
     * The Caesar cipher decryption operation
     * @param request The decryption request
     * @return The decryption response
     */
	@PayloadRoot(namespace = Application.NAMESPACE_URI, localPart = "CaesarDecryptRequest")
	@ResponsePayload
	public CaesarDecryptResponse decrypt(@RequestPayload CaesarDecryptRequest request) {
		
		CaesarDecryptResponse response = new CaesarDecryptResponse();
		String decrypted = this.caesar.decrypt(request.getDecrypt(), request.getShift());
		response.setDecrypted(decrypted);

		return response;
	}
}
