package ferdi.networkbuilder.controller;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.AgentMap;
import ferdi.networkbuilder.services.simulation.InitializationService;
import org.springframework.stereotype.Controller;

@Controller
public class InitializationController {
    private final InitializationService initializationService;

    public InitializationController(InitializationService initializationService) {
        this.initializationService = initializationService;
    }

    public void initialize(AgentMap<Agent> agentMap, MetaConfig config){
        initializationService.initialise(agentMap,config);
    }

}
