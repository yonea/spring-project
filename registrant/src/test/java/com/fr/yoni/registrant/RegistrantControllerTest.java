package com.fr.yoni.registrant;

import com.fr.yoni.registrant.domain.Registrant;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * RegistrantControllerTest class, using GetResultRequestTest to test the routes GET and POST.
 * @see GetResultRequestTest
 * @author Yoni Baroukh
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrantControllerTest extends GetResultRequestTest {

    @LocalServerPort
    private int port;

    private final String path = "http://localhost:";

    private List<Registrant> registrantArrayList;

    /**
     * Init list of Registrant with wrongs fields
     */
    @BeforeEach
    public void setUp() {
        /* null field */
        Registrant registrant1 = new Registrant(null, null, "Eric", "p-e@hotmail.fr", 18, true, "France");

        /* age < 18 */
        Registrant registrant2 = new Registrant(null, "Jean", "Charles", "j-e@hotmail.fr", 17, false, "France");

        /* country != France */
        Registrant registrant3 = new Registrant(null, "Yves", "Plein", "yves@hotmail.fr", 23, false, "Canada");

        registrantArrayList = new ArrayList<>(Arrays.asList(registrant1, registrant2, registrant3));
    }

    @Test
    public void testAddRegistrantUriSuccess() throws URISyntaxException
    {
        final String baseUrl = path + port + "/registrants/";
        Registrant registrant = new Registrant(null, "Pierre", "Eric", "p-e@hotmail.fr", 18, true, "France");

        Assert.assertEquals(201, getPostStatusCode(baseUrl, registrant));
    }

    @Test
    public void testAddRegistrantUriFail() throws URISyntaxException
    {
        final String baseUrl = path + port + "/registran/";
        Registrant registrant = new Registrant(null, "Pierre", "Eric", "p-e@hotmail.fr", 18, true, "France");

        Assert.assertEquals(404, getPostStatusCode(baseUrl, registrant));
    }

    @Test
    public void testAddRegistrantWrongFields() throws URISyntaxException
    {
        final String baseUrl = path + port + "/registrants/";

        for (Registrant registrant: registrantArrayList) {
            Assert.assertEquals(400, getPostStatusCode(baseUrl, registrant));
        }
    }

    @Test
    public void testGetOneRegistrantSuccess() throws URISyntaxException, JSONException {
        final String baseUrl = path + port + "/registrants/1";

        Assert.assertEquals(200, getGetResult(baseUrl).getStatusCodeValue());

        String bodyResult = getGetResult(baseUrl).getBody();
        JSONObject jsonObject = new JSONObject(bodyResult);

        /* Assert numbers of keys in the return JSON */
        Assert.assertEquals(7, jsonObject.length());

        JSONAssert.assertEquals("{" +
                "\"id\":1," +
                "\"lastname\":\"lastname1\"," +
                "\"firstname\":\"firstname1\"," +
                "\"email\":\"firstname1.lastname1@hotmail.fr\"," +
                "\"age\":18," +
                "\"student\":true," +
                "\"country\":\"FRANCE\"" +
                "}", bodyResult, JSONCompareMode.STRICT);
    }

    @Test
    public void testGetOneRegistrantFail() throws URISyntaxException, JSONException {
        final String baseUrl = path + port + "/registrants/9999";

        Assert.assertEquals(404, getGetResult(baseUrl).getStatusCodeValue());
    }

    @Test
    public void testGetAllRegistrantsUriSuccess() throws URISyntaxException, JSONException
    {
        final String baseUrl = path + port + "/registrants";
        String bodyResult = getGetResult(baseUrl).getBody();
        JSONArray jsonArray = new JSONArray(bodyResult);
        Assert.assertEquals(200, getGetResult(baseUrl).getStatusCodeValue());

        /* Assert numbers of registrants > 3 (4 registrants created in data.sql) */
        Assert.assertTrue(jsonArray.length() > 3);
    }

    @Test
    public void testGetAllRegistrantsUriFail() throws URISyntaxException
    {
        final String baseUrl = path + port + "/registrant";

        Assert.assertEquals(404, getGetResult(baseUrl).getStatusCodeValue());
    }

}
