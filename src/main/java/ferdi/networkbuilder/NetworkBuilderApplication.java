package ferdi.networkbuilder;

import ferdi.networkbuilder.controller.HubController;
import ferdi.networkbuilder.controller.SimulatorController;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.AgentMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NetworkBuilderApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(NetworkBuilderApplication.class, args);
        HubController hubController = (HubController) ctx.getBean("hubController");
        SimulatorController simulatorController = (SimulatorController) ctx.getBean("simulatorController");

        AgentMap<Agent> agents = hubController.buildNetwork(ctx);
        simulatorController.simulate(agents,ctx);
    }

}
