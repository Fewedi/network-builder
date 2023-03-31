package ferdi.networkbuilder.controller;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.metadata.DaySummary;
import ferdi.networkbuilder.services.simulation.FinalCSVCreationService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FinalCSVCreationController {
    private final FinalCSVCreationService finalCSVCreationService;

    public FinalCSVCreationController(FinalCSVCreationService finalCSVCreationService) {
        this.finalCSVCreationService = finalCSVCreationService;
    }

    public void createNetworkSummary (List<DaySummary> daySummaryList, MetaConfig config){
        finalCSVCreationService.create(daySummaryList,config);
    }
}

