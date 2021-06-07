package com.fr.yoni.registrant;

import com.fr.yoni.registrant.domain.Registrant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Abstract class to factorize http tests and send back result from request GET and POST
 * @see RegistrantControllerTest
 * @author Yoni Baroukh
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class GetResultRequestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Return value of status code following a post request
     * @param baseUrl
     * @param registrant
     * @return int
     * @throws URISyntaxException
     */
    public int getPostStatusCode(String baseUrl, Registrant registrant) throws URISyntaxException {
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<Registrant> request = new HttpEntity<>(registrant, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        return result.getStatusCodeValue();
    }

    /**
     * Return value of status code following a get request
     * @param baseUrl
     * @return int
     * @throws URISyntaxException
     */
    public ResponseEntity<String> getGetResult(String baseUrl) throws URISyntaxException {
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);

        return result;
    }
}
