package ferdi.networkbuilder.model.groups;

import ferdi.networkbuilder.model.agents.Agent;

import java.util.ArrayList;
import java.util.List;

public class SchoolClass {

    private final int size;
    private final List<Agent> students;

    public SchoolClass(int size) {
        this.size = size;
        this.students = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public List<Agent> getStudents() {
        return students;
    }
}
