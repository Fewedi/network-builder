package ferdi.networkbuilder.services.agentcreationdata;

import ferdi.networkbuilder.metadata.AgentCreationData;
import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;

import java.util.List;
import java.util.Map;

public interface AgentCreationDataService {
    Map<Long, List<List<List<AgentCreationData>>>> buildAgentCreationData(Map<Long, GeographicHouseholdMetaData> metaData, int maxAgents);
}
