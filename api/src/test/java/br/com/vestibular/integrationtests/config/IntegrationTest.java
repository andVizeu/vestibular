package br.com.vestibular.integrationtests.config;

import br.com.vestibular.ApiApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RunWith(SpringRunner.class)
@ActiveProfiles("integrationtest")
@SpringBootTest(
        webEnvironment = WebEnvironment.MOCK,
        classes = ApiApplication.class)
@AutoConfigureMockMvc
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IntegrationTest {
}
