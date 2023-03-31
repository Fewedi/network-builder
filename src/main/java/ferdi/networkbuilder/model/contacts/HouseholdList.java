package ferdi.networkbuilder.model.contacts;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.metadata.ContactType;
import ferdi.networkbuilder.metadata.DaySummary;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.agents.TestCenter;

import java.util.ArrayList;
import java.util.List;

public class HouseholdList extends ArrayList<Agent> {
    public void notifyThose(MetaConfig config, TestCenter testCenter) {
        for (Agent agent: this){
            agent.householdMemberIsPositive(config,testCenter);
        }
    }

    public List<Agent> meetAndInfect(MetaConfig config, boolean infectious, boolean isolated, DaySummary daySummary) {
        if(infectious){
            double prop = 0;
            if(isolated){
                prop = config.getBaselineTransmissionProp() * config.getTransmissionRateHousehold() * config.getTransmissionReductionHouseholdIsolated();
                //System.out.println("prop hh is: " + prop);
            }else {
                prop = config.getBaselineTransmissionProp() * config.getTransmissionRateHousehold();
                //System.out.println("prop hh not is: " + prop);
            }
            for(Agent agent: this){
                if(prop > config.getRandom().nextDouble()){
                    agent.infect(config,ContactType.HOUSEHOLD);
                    //System.out.println("infect") household");
                }
            }
        }
        for(int i = 0; i < this.size(); i++){
            daySummary.addContact(ContactType.HOUSEHOLD);
        }
        return this;
    }
}
