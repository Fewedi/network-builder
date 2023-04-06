package ferdi.networkbuilder.model.contacts;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.metadata.ContactType;
import ferdi.networkbuilder.metadata.DaySummary;
import ferdi.networkbuilder.model.agents.Agent;

import java.util.ArrayList;
import java.util.List;

public class WorksiteCloseColleagueGroup {
    private final List<Agent> colleagues;
    private int size;


    public WorksiteCloseColleagueGroup(int size) {
        this.colleagues = new ArrayList<>();
        this.size = size;
    }

    public void addColleague(Agent colleague){
        colleagues.add(colleague);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Agent> meetAndInfect(Agent himself, MetaConfig config, boolean infectious, DaySummary daySummary) {
        List<Agent> colOfAgent = new ArrayList<>(colleagues);
        colOfAgent.remove(himself);
        if(infectious){
            double prop = config.getBaselineTransmissionProp() * config.getTransmissionRateWork();
            for(Agent agent: colOfAgent){
                if(prop > config.getRandom().nextDouble()){
                    agent.infect(config, ContactType.WORKPLACEGROUP);
                }
                daySummary.addContact(ContactType.WORKPLACEGROUP);
            }
        }

        return colOfAgent;
    }
}
