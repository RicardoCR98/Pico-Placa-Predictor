import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.stackbuilder.controller.PicoPlacaController;
import org.stackbuilder.model.PredictionRequest;
import org.stackbuilder.model.PredictionResponse;
import org.stackbuilder.service.PicoPlacaService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PicoPlacaController.class)
@ContextConfiguration(classes = PicoPlacaController.class)
public class PicoPlacaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PicoPlacaService picoPlacaService;

    /* Tests:
     * When the response is false
     * When the response is true
     * */
    @Test
    public void tesPredictEndpointFalse() throws Exception {
        PredictionRequest request = new PredictionRequest("ABC-1234","17-09-2024","07:00");
        PredictionResponse response = new PredictionResponse(false, "The car can NOT drive at ... on ....");

        Mockito.when(picoPlacaService.canDrive(Mockito.any())).thenReturn(response);
        mockMvc.perform(post("/predict")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.canDrive").value(false))
                .andExpect(jsonPath("$.message").value("The car can NOT drive at ... on ...."));
    }

    @Test
    public void tesPredictEndpointTrue() throws Exception {
        PredictionRequest request = new PredictionRequest("ABC-1234","18-09-2024","07:00");
        PredictionResponse response = new PredictionResponse(true, "The car can drive normally.");

        Mockito.when(picoPlacaService.canDrive(Mockito.any())).thenReturn(response);
        mockMvc.perform(post("/predict")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.canDrive").value(true))
                .andExpect(jsonPath("$.message").value("The car can drive normally."));
    }
}
