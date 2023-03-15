package ferdi.networkbuilder.controller;

import ferdi.networkbuilder.NetworkBuildingHub;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class HubController {

    private final NetworkBuildingHub networkBuildingHub;

    public HubController(NetworkBuildingHub networkBuildingHub) {
        this.networkBuildingHub = networkBuildingHub;
    }


    public void buildNetwork(ApplicationContext ctx){
        networkBuildingHub.buildNetwork(ctx);
    }
}
