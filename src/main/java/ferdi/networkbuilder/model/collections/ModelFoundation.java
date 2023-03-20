package ferdi.networkbuilder.model.collections;

import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.groups.WorksiteType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModelFoundation {

    private final AgentMap<Agent> fullMap;
    private final AgeMap ageMap;

    private final AreaMap areaMap;

    private final List<WorksiteType> worksiteTypes;


    public  ModelFoundation(){
        areaMap = new AreaMap();
        fullMap = new AgentMap<>();
        ageMap = new AgeMap();
        worksiteTypes = new ArrayList<>();
    }

    public AgentMap<Agent> getFullMap() {
        return fullMap;
    }

    public AgeMap getAgeMap() {
        return ageMap;
    }

    public Map<Integer, Agent> getAgeMapRange(int minAge, int maxAge) {
        return ageMap.getRange(minAge,maxAge);
    }

    public int getFriendShipCount() {
        int count = 0;
        for (Map.Entry<Integer, Agent> agent : fullMap.entrySet()){
            count += agent.getValue().getFriends().size();
        }
        return count;
    }


    public AreaMap getAreaMap() {
        return areaMap;
    }

    public List<WorksiteType> getWorksiteTypes() {
        return worksiteTypes;
    }
}

