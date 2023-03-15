package ferdi.networkbuilder.controller;

import ferdi.networkbuilder.metadata.AgentCreationData;
import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
import ferdi.networkbuilder.services.agentcreationdata.AgentCreationDataService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class AgentCreationDataController {

    private final AgentCreationDataService agentCreationDataService;

    public AgentCreationDataController(AgentCreationDataService agentCreationDataService) {
        this.agentCreationDataService = agentCreationDataService;
    }

    public Map<Long, List<List<List<AgentCreationData>>>> buildAgentCreationData (Map<Long, GeographicHouseholdMetaData> metaData, int maxAgents){
        return agentCreationDataService.buildAgentCreationData(metaData,maxAgents);
    }
}
