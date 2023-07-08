package ru.mtsbank.port.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mtsbank.port.model.JettyModel;
import ru.mtsbank.port.entity.Jetty;
import ru.mtsbank.port.request.ShipRequest;
import ru.mtsbank.port.service.JettyService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class JettyController {
    private final JettyService jettyService;

    @GetMapping(value = "/jetties")
    public ResponseEntity<List<JettyModel>> read() {
        final List<JettyModel> jettyModelList = jettyService.readAll();
        return jettyModelList != null
                ? new ResponseEntity<>(jettyModelList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/jetties")
    public ResponseEntity<Jetty> get(@RequestBody @NotNull ShipRequest shipRequest) {
        try {
            jettyService.addShip(shipRequest.name, shipRequest.capacity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
    }

    @RequestMapping(value = "/jetties/upload", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        File convertFile = new File("/var/tmp/"+file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return "File is upload successfully";
    }
}