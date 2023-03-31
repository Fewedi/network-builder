package ferdi.networkbuilder.model.contacts;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.metadata.ContactType;
import ferdi.networkbuilder.metadata.DaySummary;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.agents.TestCenter;

import java.util.ArrayList;
import java.util.List;

public class RelativesList extends ArrayList<Agent> {
    public void notifyThose(MetaConfig config, TestCenter testCenter) {
        for (Agent agent: this){
            agent.relativeIsPositive(config,testCenter);
        }
    }

    public List<Agent> meetAndInfect(MetaConfig config, boolean infectious, DaySummary daySummary) {
        Agent relative = this.get(config.getRandom().nextInt(this.size()));
        if(infectious){
            double prop = config.getBaselineTransmissionProp() * config.getTransmissionRateRelatives();
            if(prop > config.getRandom().nextDouble()){
                relative.infect(config, ContactType.RELATIONS);
                //System.out.println(" infect relative");
            }
        }
        List<Agent> l =  new ArrayList<>();
        l.add(relative);
        daySummary.addContact(ContactType.RELATIONS);

        //System.out.println("  relative" + this);
        return l;
    }
}
