package ferdi.networkbuilder.controller;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.metadata.AgentCreationData;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import ferdi.networkbuilder.services.agentcreation.AgentCreationService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class AgentCreationController {
    private final AgentCreationService agentCreationService;

    public AgentCreationController(AgentCreationService agentCreationService) {
        this.agentCreationService = agentCreationService;
    }

    public ModelFoundation createAgents(Map<Integer,List<List<List<AgentCreationData>>>> data, MetaConfig config){
        return agentCreationService.createAgents(data, config);
    }
}
