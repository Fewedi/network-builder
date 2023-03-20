package ferdi.networkbuilder.model.collections;

import ferdi.networkbuilder.model.agents.Agent;

import java.util.Map;

public class ModelFoundation {

    private final AgentMap<Agent> fullMap;
    private final AgeMap ageMap;

    public  ModelFoundation(){
        fullMap = new AgentMap<>();
        ageMap = new AgeMap();
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
}
