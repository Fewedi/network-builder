package ferdi.networkbuilder.controller;

import ferdi.networkbuilder.Simulator;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.AgentMap;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class SimulatorController {
    private final Simulator simulator;

    public SimulatorController(Simulator simulator) {
        this.simulator = simulator;
    }

    public void simulate (AgentMap<Agent> agentMap, ApplicationContext ctx){
        simulator.simulate(agentMap,ctx);
    }
}
