package org.stackbuilder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stackbuilder.model.PredictionRequest;
import org.stackbuilder.model.PredictionResponse;
import org.stackbuilder.service.PicoPlacaService;

@RestController
public class PicoPlacaController {

    private final PicoPlacaService picoPlacaService;

    public PicoPlacaController(PicoPlacaService picoPlacaService) {
        this.picoPlacaService = picoPlacaService;
    }

    @PostMapping("/predict")
    public ResponseEntity<PredictionResponse> predict(@RequestBody PredictionRequest request) {
        PredictionResponse response = picoPlacaService.canDrive(request);
        return ResponseEntity.ok(response);
    }
}
