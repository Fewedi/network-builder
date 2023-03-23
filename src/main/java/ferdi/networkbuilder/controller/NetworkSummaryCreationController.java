package ferdi.networkbuilder.controller;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.metadata.NetworkSummaryData;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import ferdi.networkbuilder.services.output.OutputService;
import org.springframework.stereotype.Controller;

@Controller
public class NetworkSummaryCreationController {
    private final OutputService outputService;

    public NetworkSummaryCreationController(OutputService outputService) {
        this.outputService = outputService;
    }

    public NetworkSummaryData createNetworkSummary (ModelFoundation modelFoundation,MetaConfig config){
        return outputService.createNetworkSummary(modelFoundation,config);
    }
}
