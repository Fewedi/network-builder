package ferdi.networkbuilder.model.contacts;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.metadata.ContactType;
import ferdi.networkbuilder.metadata.DaySummary;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.agents.TestCenter;

import java.util.ArrayList;
import java.util.Collections;
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


    public void notifyClassmates(MetaConfig config, TestCenter testCenter) {
        for (Agent agent: students){
            agent.classmateIsPositive(config,testCenter);
        }
    }

    public List<Agent> meetAndInfect(Agent himSelf, MetaConfig config, boolean infectious, DaySummary daySummary) {
        int max = students.size();
        int amount = (int) Math.ceil((double) max * config.getEncountersInSchoolclass());
        List<Agent> l = new ArrayList<>();
        Collections.shuffle(students,config.getRandom());
        if(students.size() <= 1){
            return l;
        }
        if(infectious) {
            double prop = config.getBaselineTransmissionProp() * config.getTransmissionRateSchool();
            boolean me = false;
            for (int i = 0; i < amount; i++) {
                if (!students.get(i).equals(himSelf)) {

                    if (prop > config.getRandom().nextDouble()) {
                        students.get(i).infect(config, ContactType.SCHOOL);
                    }
                    l.add(students.get(i));
                    daySummary.addContact(ContactType.SCHOOL);
                } else {
                    me = true;
                }
                if (me) {
                    if (prop > config.getRandom().nextDouble()) {
                        students.get(amount).infect(config, ContactType.SCHOOL);
                    }
                    l.add(students.get(amount));
                    daySummary.addContact(ContactType.SCHOOL);
                }

            }
        }else {
            boolean me = false;
            for (int i = 0; i < amount; i++) {
                if (!students.get(i).equals(himSelf)) {
                    l.add(students.get(i));
                    daySummary.addContact(ContactType.SCHOOL);
                } else {
                    me = true;
                }
                if (me) {
                    l.add(students.get(amount));
                    daySummary.addContact(ContactType.SCHOOL);
                }
            }
        }
        return l;
    }

}
