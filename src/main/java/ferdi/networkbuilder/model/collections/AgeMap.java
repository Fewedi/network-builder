package ferdi.networkbuilder.model.collections;

import ferdi.networkbuilder.model.agents.Agent;

import java.util.HashMap;
import java.util.Map;

public class AgeMap {

    private final Map<Short,Map<Integer, Agent>> ageMap;

    public AgeMap() {
        ageMap = new HashMap<>();
    }

    public void putAll(Map<Integer, Agent> agents) {
        for(Map.Entry<Integer, Agent> agent: agents.entrySet()){
            put(agent.getKey(),agent.getValue());
        }
    }

    private void put(Integer id, Agent agent){
        if(ageMap.containsKey(agent.getAge())){
            ageMap.get(agent.getAge()).put(id, agent);
        }else {
            Map<Integer, Agent> map = new HashMap<>();
            map.put(id,agent);
            ageMap.put(agent.getAge(),map);
        }
    }


    public void printOut(){
        System.out.println("AgeMap-Data");
        System.out.println("-------------------------------------------------------------------------------------------");
        for(Map.Entry<Short,Map<Integer,Agent>> age: ageMap.entrySet()){
            System.out.println("---Age: " + age.getKey());
            for(Map.Entry<Integer,Agent> agent: age.getValue().entrySet()){
                System.out.println("------agent: id:" + agent.getKey() + ", agent: " + agent.getValue());
            }
        }
    }

    public Map<Integer,Agent> getRange(int minAge, int maxAge) {
        Map<Integer,Agent> map = new AgentMap<>();
        for (int i = minAge; i <= maxAge; i++){

            if(ageMap.containsKey((short)i)){
                //System.out.println(i);
                map.putAll(ageMap.get((short) i));
            }
        }
        return map;
    }
}
