package ferdi.networkbuilder.model.collections;

import ferdi.networkbuilder.model.agents.Agent;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.MAX_VALUE;

public class AreaMap{

    private final Map<Integer,Map<Integer, Agent>> areaMap;
    public AreaMap(){
        areaMap = new HashMap<>();
    }
    public void putAll(Map<Integer, Agent> map){
        for (Map.Entry<Integer,Agent> entry: map.entrySet()){
            put(entry.getValue().getArea(), entry.getKey(),entry.getValue());
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

    public int getCount(){
        return areaMap.size();
    }

    public double getAverageSize(){
        int sum = 0;
        for(Map.Entry<Integer, Map<Integer,Agent>> entry: areaMap.entrySet()){
            sum += entry.getValue().size();
        }
        return (double) sum / (double) areaMap.size();
    }

    public int getMaxSize(){
        int max = 0;
        for(Map.Entry<Integer, Map<Integer,Agent>> entry: areaMap.entrySet()){
            if(entry.getValue().size() > max){
                max = entry.getValue().size();
            }
        }
        return max;
    }

    public int getMinSize(){
        int min = MAX_VALUE;
        for(Map.Entry<Integer, Map<Integer,Agent>> entry: areaMap.entrySet()){
            if(entry.getValue().size() < min){
                min = entry.getValue().size();
            }
        }
        return min;
    }


    public Map<Integer, Map<Integer, Agent>> getAreaMap() {
        return areaMap;
    }
}
