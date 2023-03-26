package ferdi.networkbuilder.services.simulation;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.AgentMap;

public interface InitializationService {
    void initialise(AgentMap<Agent> agentMap, MetaConfig config);
}
