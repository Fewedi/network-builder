package ferdi.networkbuilder.controller;

import ferdi.networkbuilder.services.extraction.ExtractionService;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
public class ExtractionController {

    private final ExtractionService extractionService;

    public ExtractionController (ExtractionService extractionService){
        this.extractionService = extractionService;
    }

    public List<List<String>> extractDataFromFile(String path, String fileName){
        try {
            return extractionService.dataToList(path, fileName);
        } catch (FileNotFoundException e) {
            System.out.println("no file found");
            throw new RuntimeException(e);
        }
    }
}
