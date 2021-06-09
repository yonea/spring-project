package com.fr.yoni.registrant;

import com.fr.yoni.registrant.controller.RegistrantController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * RegistrantApplicationTests class to test if application have controller
 * @author Yoni Baroukh
 */
@SpringBootTest
class RegistrantApplicationTests {

    @Autowired
    private RegistrantController registrantController;

    @Test
    void contextLoads() {
        assertThat(registrantController).isNotNull();
    }

}
