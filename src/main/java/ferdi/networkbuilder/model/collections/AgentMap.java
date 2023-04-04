package ferdi.networkbuilder.model.collections;

import ferdi.networkbuilder.model.agents.Agent;

import java.io.Serializable;
import java.util.HashMap;

public class AgentMap<T extends Agent> extends HashMap<Integer,T> {
    public void printOut(){
        System.out.println("AgentMap-Data");
        System.out.println("-------------------------------------------------------------------------------------------");
        for(Entry<Integer, T> agent: this.entrySet()){
            System.out.println("---agent: id:" + agent.getKey() + ", agent: " + agent.getValue());
        }
    }
}
