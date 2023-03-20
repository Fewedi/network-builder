package ferdi.networkbuilder.model.collections;

import ferdi.networkbuilder.model.agents.Agent;

import java.util.ArrayList;

public class FriendList extends ArrayList<Agent> {

    public String toString(){
        StringBuilder s = new StringBuilder("{ - ");
        for (Agent agent: this){
            s.append("[id=").append(agent.getId()).append(", age=").append(agent.getAge()).append("] - ");
        }
        return s + "]";
    }


}
