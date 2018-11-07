# UNIM-4IT475-SoapWS

A Soap Web Service as a semestral work for the 4IT475 System Integration Testing course.

The goal of the semestal work is to design and implement a SOAP WS. This project represents a web service encrypting a plaintext using a cipher operation and decrypting it back. Some of the ciphers use keys or an additional configuration within the operation.

## Sample usage

Let's learn how to use the Caesar cipher encryption and decryption.

 1. Open SoapUI or any similar SOAP client and create a new SOAP WS entering the WSDL located on [https://4it475.azurewebsites.net/soap/ws/cipher.wsdl](https://4it475.azurewebsites.net/soap/ws/cipher.wsdl). 
 2. Open the **CaesarEncrypt** operation at the `http://localhost:8080/soap/ws` endpoint.
 3. Fill in the plaintext to be ecrypted inside the `<fis:encrypt>` tag. Optionally fill in `<fis:shift>` which stands for the shift in the international alphabet which is used by the Caesar cipher. The `<fis:shift>` is optional and the default value is `3`.
 4. The full request should look like:
 
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                          xmlns:fis="https://fis.vse.cz/">
            <soapenv:Header/>
            <soapenv:Body>
                <fis:CaesarEncryptRequest>
                    <fis:encrypt>Hello world</fis:encrypt>
                    <fis:shift>5</fis:shift>
                </fis:CaesarEncryptRequest>
            </soapenv:Body>
        </soapenv:Envelope>
       
 5. Submit the request to the endpoint.
 6. The response should look like:
 
        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
            <SOAP-ENV:Header/>
            <SOAP-ENV:Body>
                <ns2:CaesarEncryptResponse xmlns:ns2="https://fis.vse.cz/">
                    <ns2:encrypted>MJQQT BTWQI</ns2:encrypted>
                </ns2:CaesarEncryptResponse>
            </SOAP-ENV:Body>
        </SOAP-ENV:Envelope>

 7. The encrypted value is `MJQQT BTWQI`.
 8. Use the **CaesarDecrypt** operation at the very same endpoint to receive the original value back.
 9. The full request should look like:
 
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                          xmlns:fis="https://fis.vse.cz/">
            <soapenv:Header/>
            <soapenv:Body>
                <fis:CaesarDecryptRequest>
                    <fis:decrypt>MJQQT BTWQI</fis:decrypt>
                    <fis:shift>5</fis:shift>
                </fis:CaesarDecryptRequest>
            </soapenv:Body>
        </soapenv:Envelope>
 10. Upon submitting the request to the endpoint, the response should return back `HELLO WORLD` in the response:
 
         <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
             <SOAP-ENV:Header/>
             <SOAP-ENV:Body>
                 <ns2:CaesarDecryptResponse xmlns:ns2="https://fis.vse.cz/">
                     <ns2:decrypted>HELLO WORLD</ns2:decrypted>
                </ns2:CaesarDecryptResponse>
             </SOAP-ENV:Body>
         </SOAP-ENV:Envelope>

The full list of all the operations of the endpoint to be found at [Wiki](https://github.com/NicharNET/UNIM-4IT475-SoapWS/wiki).

## Localhost installation

Build and run the project using command:

`mvn clean install`

In case the generating of target classes from the schema files is required, run:

`mvn jaxb2:xjc`

The WSDL should be available on the address:

[http://localhost:8080/soap/ws/cipher.wsdl](http://localhost:8080/soap/ws/cipher.wsdl)
