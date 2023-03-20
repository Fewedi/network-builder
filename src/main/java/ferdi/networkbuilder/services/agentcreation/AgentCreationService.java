package ferdi.networkbuilder.services.agentcreation;

import ferdi.networkbuilder.metadata.AgentCreationData;
import ferdi.networkbuilder.model.collections.ModelFoundation;

import java.util.List;
import java.util.Map;

public interface AgentCreationService {
    ModelFoundation createAgents(Map<Integer,List<List<List<AgentCreationData>>>> data);
}
