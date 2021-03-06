package net.hogedriven.backpaper0.javaeeadventcalendar;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CalcTest {

    @Inject
    private Calc calc;

    @Test
    public void add_1_2_is_3() throws Exception {
        String result = calc.add(1, 2);
        assertThat(result, is("1 + 2 = 3"));
    }

    @Test
    @RunAsClient
    public void add_2_3_is_5() throws Exception {
        String response =
            ClientBuilder
                .newClient()
                .target("http://localhost:8181/calc-test/rest/calc/add")
                .queryParam("x", "2")
                .queryParam("y", "3")
                .request()
                .get(String.class);
        assertThat(response, is("2 + 3 = 5"));
    }

    @Deployment
    public static WebArchive createDeployment() {
        Path webinf = Paths.get("src", "main", "webapp", "WEB-INF");
        WebArchive war = ShrinkWrap
            .create(WebArchive.class, "calc-test.war")
            .addClasses(Calc.class, CalcBean.class, CalcApplication.class)
            .addAsWebInfResource(webinf.resolve("beans.xml").toFile());
        Logger.getGlobal().info(war.toString(true));
        return war;
    }
}
