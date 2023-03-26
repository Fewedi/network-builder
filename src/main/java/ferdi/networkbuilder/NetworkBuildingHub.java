package ferdi.networkbuilder;

import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.AgentMap;
import org.springframework.context.ApplicationContext;

public interface NetworkBuildingHub {

    AgentMap<Agent> buildNetwork(ApplicationContext ctx);
}
