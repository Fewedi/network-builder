package ferdi.networkbuilder.controller;

import ferdi.networkbuilder.NetworkBuildingHub;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.AgentMap;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class HubController {

    private final NetworkBuildingHub networkBuildingHub;

    public HubController(NetworkBuildingHub networkBuildingHub) {
        this.networkBuildingHub = networkBuildingHub;
    }


    public AgentMap<Agent> buildNetwork(ApplicationContext ctx){
        return networkBuildingHub.buildNetwork(ctx);

    }
}
