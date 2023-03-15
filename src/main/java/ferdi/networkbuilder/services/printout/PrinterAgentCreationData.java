package ferdi.networkbuilder.services.printout;

import ferdi.networkbuilder.metadata.AgentCreationData;
import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PrinterAgentCreationData implements Printer{


    @Override
    public void printOut(Map<Long,List<List<List<AgentCreationData>>>> agentCreationData, HashMap<Long, GeographicHouseholdMetaData> metaLocal){
        for(Map.Entry<Long, List<List<List<AgentCreationData>>>> entry : agentCreationData.entrySet()) {
            System.out.println("---area: " + entry.getValue().get(0).get(0).get(0).getArea() + ", pop: " + metaLocal.get(entry.getValue().get(0).get(0).get(0).getArea()).getInhabitantsCount());
            for (List<List<AgentCreationData>> entry1 : entry.getValue()) {
                //System.out.println(entry1);
                System.out.println("------agegroup: " + entry1.get(0).get(0).getAge());
                for (List<AgentCreationData> entry2 : entry1) {
                    System.out.println("---------household: kids " + entry2.get(0).isKids() + ", couple " + entry2.get(0).isCouple());
                    for (AgentCreationData entry3 : entry2) {
                        System.out.println("------------age: " + entry3);
                    }
                }
            }
        }
    }
}
