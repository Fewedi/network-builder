package ferdi.networkbuilder.controller;

import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
import ferdi.networkbuilder.services.metadatacreation.HousholdMetaDataCreationService;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

@Controller
public class MetaDataCreationHouseholdController {

    private final HousholdMetaDataCreationService housholdMetaDataCreationService;


    public MetaDataCreationHouseholdController(HousholdMetaDataCreationService housholdMetaDataCreationService) {
        this.housholdMetaDataCreationService = housholdMetaDataCreationService;
    }

    public void createMetaDataLower(List<List<String>> data, HashMap<Long,GeographicHouseholdMetaData> metaData){
        housholdMetaDataCreationService.buildMetaDataLower(data, metaData);
    }

    public void createMetaDataGlobal(List<List<String>> data, GeographicHouseholdMetaData metaData){
        housholdMetaDataCreationService.buildMetaDataGlobal(data, metaData);
    }


}
