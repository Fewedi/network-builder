package ferdi.networkbuilder.model.contacts;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.metadata.ContactType;
import ferdi.networkbuilder.metadata.DaySummary;
import ferdi.networkbuilder.model.agents.Agent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FriendList extends ArrayList<Agent> {

    public String toString(){
        StringBuilder s = new StringBuilder("{ - ");
        for (Agent agent: this){
            s.append("[id=").append(agent.getId()).append(", age=").append(agent.getAge()).append("] - ");
        }
        return s + "]";
    }


    public List<Agent> meetAndInfect(MetaConfig config, boolean infectious, DaySummary daySummary) {
        int max = this.size();
        int amount = (int) Math.ceil((double) max * config.getEncountersInFriendsMax());
        List<Agent> l = new ArrayList<>();
        Collections.shuffle(this,config.getRandom());
        if(infectious) {
            double prop = config.getBaselineTransmissionProp() * config.getTransmissionRateFriends();
            for(int i = 0; i < amount; i++){
                if(prop > config.getRandom().nextDouble()){
                    this.get(i).infect(config, ContactType.FRIEND);
                    //System.out.println(" infect friend ");
                }
                l.add(this.get(i));
            }
        }else {
            for(int i = 0; i < amount; i++){
                l.add(this.get(i));
                daySummary.addContact(ContactType.FRIEND);
            }
        }
        return l;
    }
}
