package ferdi.networkbuilder.model.agents;

import ferdi.networkbuilder.config.MetaConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CTA implements Serializable {
    private final List<List<Agent>> savedAgents;

    public CTA(){
        this.savedAgents = new ArrayList<>();
    }

    public void notifyThose(MetaConfig config, TestCenter testCenter) {
        for (List<Agent> days: savedAgents) {
            for(Agent agent: days){
                agent.cTAContactIsPositive(config,testCenter);
            }
        }
    }

    public void addAll(List<Agent> todaysContacts, MetaConfig config) {
        removeOld(config.getcTADaysToSave());
        savedAgents.add(0,todaysContacts);
    }

    private void removeOld(int getcTADaysToSave) {
        if(savedAgents.size() >= getcTADaysToSave){
            savedAgents.remove(savedAgents.size()-1);
        }
    }

    public void reset() {
        savedAgents.clear();
    }
}
