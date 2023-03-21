package ferdi.networkbuilder.model.collections;

import ferdi.networkbuilder.model.agents.Agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolMap {

    private final Map<Integer, Map<Short, List<Agent>>> schoolMap;

    public SchoolMap() {
        this.schoolMap = new HashMap<>();
    }

    public void putAll(Map<Integer, Agent> agents){
        for(Map.Entry<Integer, Agent> agent: agents.entrySet()){
            put(agent.getKey(),agent.getValue());
        }
    }

    private void put(Integer key, Agent value) {
        if(schoolMap.containsKey(value.getArea())){
            if(schoolMap.get(value.getArea()).containsKey(value.getAge())){
                schoolMap.get(value.getArea()).get(value.getAge()).add(value);
            }else {
                List<Agent> l = new ArrayList<>();
                l.add(value);
                schoolMap.get(value.getArea()).put(value.getAge(), l);
            }
        }else {
            Map<Short,List<Agent>> ages = new HashMap<>();
            List<Agent> l = new ArrayList<>();
            l.add(value);
            ages.put(value.getAge(),l);
            schoolMap.put(value.getArea(),ages);
        }
    }

    public void printOut(){
        System.out.println("AgeMap-Data");
        System.out.println("-------------------------------------------------------------------------------------------");
        for(Map.Entry<Integer,Map<Short,List<Agent>>> age: schoolMap.entrySet()){
            System.out.println("---Area: " + age.getKey());
            for(Map.Entry<Short,List<Agent>> list: age.getValue().entrySet()){
                System.out.println("------Age-List: Age:" + list.getKey() + ", list: " + list.getValue());
            }
        }
    }

    public Map<Integer, Map<Short, List<Agent>>> getMap() {
        return schoolMap;
    }
}
