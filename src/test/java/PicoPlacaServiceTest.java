import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.stackbuilder.exception.InvalidInputException;
import org.stackbuilder.model.PredictionRequest;
import org.stackbuilder.model.PredictionResponse;
import org.stackbuilder.service.PicoPlacaService;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PicoPlacaService.class)
public class PicoPlacaServiceTest {
    @Autowired
    private PicoPlacaService picoPlacaService;
    /* Tests:
    * Vehicle restricted during prohibited hours and days.
    * Vehicle not restricted outside of hours.
    * Vehicle not restricted on weekends.
    * Handling of invalid entries.
    * Handling blank entries
    *   - Blank plate number
    *   - Blank time
    *   - Blank Date
    * */
    @Test
    public void testRestrictedDuringProhibitedHoursAndDays() {
        PredictionRequest request = new PredictionRequest("ABC-1234","17-09-2024","07:00");
        PredictionResponse response  = picoPlacaService.canDrive(request);
        assertFalse(response.isCanDrive());
    }

    @Test
    public void testNotRestrictedOutsideHours() {
        PredictionRequest request = new PredictionRequest("ABC-1234","17-09-2024","10:00");
        PredictionResponse response  = picoPlacaService.canDrive(request);
        assertTrue(response.isCanDrive());
    }

    @Test
    public void testNotRestrictedWeekends(){
        PredictionRequest request = new PredictionRequest("ABC-1234","22-09-2024","08:00");
        PredictionResponse response  = picoPlacaService.canDrive(request);
        assertTrue(response.isCanDrive());
    }

    @Test
    public void testInvalidIputs(){
        PredictionRequest request = new PredictionRequest("AA-1234","17-09-2024","10:00");
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> picoPlacaService.canDrive(request));
        assertEquals("Invalid Format", exception.getMessage());
    }

    @Test
    public void testBlankPlateNumber(){
        PredictionRequest request = new PredictionRequest("","17-09-2024","10:00");
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> picoPlacaService.canDrive(request));
        assertEquals("Invalid Format", exception.getMessage());
    }

    @Test
    public void testBlankDate(){
        PredictionRequest request = new PredictionRequest("ABC-1234","","08:00");
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> picoPlacaService.canDrive(request));
        assertEquals("Invalid Format", exception.getMessage());
    }

    @Test
    public void testBlankTime(){
        PredictionRequest request = new PredictionRequest("ABC-1234","22-09-2024","");
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> picoPlacaService.canDrive(request));
        assertEquals("Invalid Format", exception.getMessage());
    }
}
