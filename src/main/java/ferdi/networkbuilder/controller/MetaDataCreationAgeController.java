package ferdi.networkbuilder.controller;


import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
import ferdi.networkbuilder.services.AgeMetaDataCreationService;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

@Controller
public class MetaDataCreationAgeController {
    private final AgeMetaDataCreationService ageMetaDataCreationService;

    public MetaDataCreationAgeController(AgeMetaDataCreationService ageMetaDataCreationService) {
        this.ageMetaDataCreationService = ageMetaDataCreationService;
    }


    public void createMetaDataLower(List<List<String>> data, HashMap<Long, GeographicHouseholdMetaData> metaData){
        ageMetaDataCreationService.buildMetaDataLower(data, metaData);
    }

    public void createMetaDataGlobal(List<List<String>> data, GeographicHouseholdMetaData metaData){
        ageMetaDataCreationService.buildMetaDataGlobal(data, metaData);
    }
}
