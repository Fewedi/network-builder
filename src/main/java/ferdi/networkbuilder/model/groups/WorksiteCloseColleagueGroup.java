package ferdi.networkbuilder.model.groups;

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
}
