package ferdi.networkbuilder.services.printout;

import ferdi.networkbuilder.metadata.AgentCreationData;
import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface Printer {

    void printOut(Map<Long,List<List<List<AgentCreationData>>>> agentCreationData, HashMap<Long, GeographicHouseholdMetaData> metaLocal);
}
