package ferdi.networkbuilder.services.agentcreation;

import ferdi.networkbuilder.metadata.AgentCreationData;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import ferdi.networkbuilder.model.agents.Adult;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.agents.Child;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AgentCreationServiceImpl implements AgentCreationService {

    @Override
    public ModelFoundation createAgents(Map<Integer,List<List<List<AgentCreationData>>>> data) {
        AtomicInteger nextId = new AtomicInteger(0);
        ModelFoundation modelFoundation = new ModelFoundation();
        for(Map.Entry<Integer,List<List<List<AgentCreationData>>>> areaEntry: data.entrySet()){
            List<List<List<AgentCreationData>>> areaList = areaEntry.getValue();
            for (List<List<AgentCreationData>> ageGroup: areaList){
                for (List<AgentCreationData> householdType: ageGroup){
                    for (AgentCreationData age: householdType){
                        Map<Integer, Agent> agents = createAgentsForOneCreationData(nextId, age);
                        modelFoundation.getFullMap().putAll(agents);
                        modelFoundation.getAgeMap().putAll(agents);
                    }
                }
            }
        }
        System.out.println("Agents created: - " + modelFoundation.getFullMap().size());
        return modelFoundation;
        //modelFoundation.getAgeMap().printOut();
    }

    private Map<Integer, Agent> createAgentsForOneCreationData(AtomicInteger nextId, AgentCreationData data){
        Map<Integer, Agent> map = new HashMap<>();
        for(int i = 0; i < data.amount();i++){
            if(isAdult(data)){
                map.put(nextId.get(),new Adult(nextId.get(),(short) data.age(), data.couple(), data.kids(),(int) data.area()));
            }   else {
                map.put(nextId.get(),new Child(nextId.get(),(short) data.age(), data.couple(), data.kids(),(int) data.area()));
            }
            nextId.incrementAndGet();
        }
        return map;
    }

    private boolean isAdult(AgentCreationData data){
        return data.age() >= 18;
    }
}
