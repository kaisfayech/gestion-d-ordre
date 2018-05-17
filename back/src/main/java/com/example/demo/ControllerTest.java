package com.example.demo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackApplication.class)
@WebAppConfiguration
public class ControllerTest {


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;


    private HttpMessageConverter mappingJackson2HttpMessageConverter;




    @Autowired
    private WebApplicationContext webApplicationContext;



    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }
    @Test
    public void responseSecurities() throws Exception {
        mockMvc.perform(get("/soliam-orders/securitesresponse"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }
    @Test
    public void responseContract() throws Exception {
        mockMvc.perform(get("/soliam-orders/contracttyperesponse/CONTRACT_TYPE"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }
    @Test
    public void responsePortfolio() throws Exception {
        mockMvc.perform(get("/soliam-orders/portfolioresponse/003"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }
    @Test
    public void responseRv() throws Exception {
        mockMvc.perform(get("/soliam-orders/rv/8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }
    @Test
    public void responsePaymentschedule() throws Exception {
        mockMvc.perform(get("/soliam-orders/paymentschedule"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }
    @Test
    public void responsethird() throws Exception {
        mockMvc.perform(get("/soliam-orders/third"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }
    @Test
    public void responseNote() throws Exception {
        mockMvc.perform(get("/soliam-orders/note"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }



    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}