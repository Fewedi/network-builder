package ferdi.networkbuilder;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.controller.HubController;
import ferdi.networkbuilder.controller.SimulatorController;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.AgentMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Random;

@SpringBootApplication
public class NetworkBuilderApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(NetworkBuilderApplication.class, args);

        MetaConfig metaConfig = (MetaConfig) ctx.getBean("metaConfig");
        if(metaConfig.getRuns() == 1){
            forOneSeed(ctx);
        }else {
            for(int i = 1; i <= metaConfig.getRuns(); i++){
                Random rng = new Random();
                metaConfig.setSeed(rng.nextInt());
                forOneSeed(ctx);
            }
        }
    }

    private static void forOneSeed(ApplicationContext ctx){
        HubController hubController = (HubController) ctx.getBean("hubController");
        SimulatorController simulatorController = (SimulatorController) ctx.getBean("simulatorController");

        AgentMap<Agent> agents = hubController.buildNetwork(ctx);
        simulatorController.simulate(agents,ctx);
    }

}
