package ferdi.networkbuilder.model.groups;

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
}
