package ferdi.networkbuilder;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.controller.InitializationController;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.AgentMap;
import ferdi.networkbuilder.services.simulation.InitializationService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SimulatorImpl implements Simulator{
    @Override
    public void simulate(AgentMap<Agent> agentMap, ApplicationContext ctx) {
        InitializationController initializationController = (InitializationController) ctx.getBean("initializationController");
        MetaConfig config = (MetaConfig) ctx.getBean("metaConfig");
        initializationController.initialize(agentMap,config);
        for( int day = 0; day <= config.getDays() ; day++){
            // Statusänderung
            // Reaktion, wenn symptome
            // treffen Kontakte
            // infektion Kontakte
            // output generierung
        }
    }
}
