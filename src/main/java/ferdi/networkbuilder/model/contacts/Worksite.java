package ferdi.networkbuilder.model.contacts;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.metadata.ContactType;
import ferdi.networkbuilder.metadata.DaySummary;
import ferdi.networkbuilder.model.agents.Agent;

import java.util.ArrayList;
import java.util.List;

public class Worksite {

    private final int workers;

    private final List<Agent> employees;
    private final List<WorksiteCloseColleagueGroup> groups;

    public Worksite(int workers) {
        this.workers=workers;
        this.employees = new ArrayList<>();
        this.groups = new ArrayList<>();
    }

    public int getSize() {
        return workers;
    }


    public List<WorksiteCloseColleagueGroup> getGroups() {
        return groups;
    }

    public void add(Agent agent) {
        employees.add(agent);
    }

    public List<Agent> getEmployees() {
        return employees;
    }

    public void addGroup(WorksiteCloseColleagueGroup group) {
        groups.add(group);
    }

    public List<Agent> meetAndInfect(Agent himself, MetaConfig config, boolean infectious, DaySummary daySummary) {

        int ran = config.getRandom().nextInt(employees.size());
        Agent employee = employees.get(ran);
        if(employee.equals(himself)){
            employee = employees.get((ran +1) % employees.size());
        }
        if(infectious){
            double prop = config.getBaselineTransmissionProp() * config.getTransmissionRateWork();
            if(prop > config.getRandom().nextDouble()){
                //System.out.println(" infect Worksite ");
                employee.infect(config, ContactType.WORKPLACE);
            }
            daySummary.addContact(ContactType.WORKPLACE);
        }
        List<Agent> l =  new ArrayList<>();
        l.add(employee);
        return l;
    }
}
