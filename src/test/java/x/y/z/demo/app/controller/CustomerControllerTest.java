package x.y.z.demo.app.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import x.y.z.demo.app.service.CustomerService;
import x.y.z.demo.config.datasource.DataSourceConfig;
import x.y.z.demo.config.datasource.PersistenceLayerConfig;
import x.y.z.demo.config.web.WebConfig;

import java.util.Collection;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class,
        classes = {
                WebConfig.class,
                DataSourceConfig.class,
                PersistenceLayerConfig.class,
                CustomerController.class,
                CustomerService.class
        })
@WebAppConfiguration
public class CustomerControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("12345678", "12345678"));
    }

    @Test
    public void testCustomerDetails() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/customer/details")
        ).andExpect(
                status().isOk()
        ).andReturn();

        String responseAsString = result.getResponse().getContentAsString();


        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, ?> objectMap = objectMapper.readValue(responseAsString, Map.class);
        String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectMap);
        System.out.println(s);

    }
}