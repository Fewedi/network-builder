package ferdi.networkbuilder.model.collections;

import ferdi.networkbuilder.model.agents.Agent;

import java.util.HashMap;
import java.util.Map;

public class AreaMap{

    private final Map<Integer,Map<Integer, Agent>> areaMap;
    public AreaMap(){
        areaMap = new HashMap<>();
    }
    public void putAll(Map<Integer, Agent> map){
        for (Map.Entry<Integer,Agent> entry: map.entrySet()){
            put(entry.getValue().getArea(),entry.getKey().intValue(),entry.getValue());
        }
    }

    public void put(Integer area, Integer id, Agent agent ){
        if(areaMap.containsKey(area)){
            areaMap.get(area).put(id, agent);
        }else {
            areaMap.put(area,new HashMap<>());
            areaMap.get(area).put(id, agent);
        }
    }

    public Map<Integer, Map<Integer, Agent>> getAreaMap() {
        return areaMap;
    }
}
