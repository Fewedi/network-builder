package ferdi.networkbuilder.services.agentcreation;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import ferdi.networkbuilder.model.collections.SchoolMap;
import ferdi.networkbuilder.model.groups.SchoolClass;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SchoolClassCreationService implements RelationCreationService{
    @Override
    public void buildRelationships(ModelFoundation modelFoundation, MetaConfig config) {
        if(config.isClassesOfDifferentAgesIfNecessary()){
            buildRelationshipsOverlapping(modelFoundation,config);
        }else {
            buildRelationshipsNonOverlapping(modelFoundation,config);
        }
    }


    public void buildRelationshipsOverlapping(ModelFoundation modelFoundation, MetaConfig config) {
        SchoolMap schoolMap = modelFoundation.getSchoolMap();
        Map<Integer, Map<Short, List<Agent>>> schoolMapRaw = schoolMap.getMap();
        Map<Integer,  List<SchoolClass>> schoolClasses = new HashMap<>();
        int classCounter = 0;
        int kidsCounter = 0;
        for(Map.Entry<Integer, Map<Short, List<Agent>>> areas: schoolMapRaw.entrySet()){
            List<SchoolClass> classesInArea = new ArrayList<>();
            SchoolClass schoolClass = new SchoolClass(config.getClassSize());
            classCounter++;
            for (Map.Entry<Short, List<Agent>> kids : areas.getValue().entrySet()){
                for(Agent kid: kids.getValue()){
                    if(schoolClass.getStudents().size() >= config.getClassSize() ){
                        classesInArea.add(schoolClass);
                        classCounter++;
                        schoolClass = new SchoolClass(config.getClassSize());
                        addKidToClass(schoolClass,kid);
                        kidsCounter++;
                    }else if(schoolClass.getStudents().size() < config.getClassSize()){
                        addKidToClass(schoolClass,kid);
                        kidsCounter++;
                    }
                }
            }
            classesInArea.add(schoolClass);
            schoolClasses.put(areas.getKey(),classesInArea);
        }
        System.out.println("School-classes created: " + classCounter + " for " + kidsCounter + " kids");
    }

    private void addKidToClass(SchoolClass schoolClass, Agent kid) {
        schoolClass.getStudents().add(kid);
        kid.setSchoolClass(schoolClass);
        kid.setStudent(true);
    }

    public void buildRelationshipsNonOverlapping(ModelFoundation modelFoundation, MetaConfig config) {
        SchoolMap schoolMap = modelFoundation.getSchoolMap();
        Map<Integer, Map<Short, List<Agent>>> schoolMapRaw = schoolMap.getMap();
        schoolMap.printOut();
        Map<Integer,  List<SchoolClass>> schoolClasses = new HashMap<>();
        int classCounter = 0;
        int kidsCounter = 0;
        for(Map.Entry<Integer, Map<Short, List<Agent>>> areas: schoolMapRaw.entrySet()){
            List<SchoolClass> classesInArea = new ArrayList<>();
            for (Map.Entry<Short, List<Agent>> kids : areas.getValue().entrySet()){
                kidsCounter += kids.getValue().size();
                List<SchoolClass> classes = kidsToClasses(kids.getValue(),config);
                classesInArea.addAll(classes);
                classCounter += classes.size();
            }
            schoolClasses.put(areas.getKey(),classesInArea);
        }
        modelFoundation.setSchoolClasses(schoolClasses);
        System.out.println("School-classes created: " + classCounter + " for " + kidsCounter + " kids");
    }

    private List<SchoolClass> kidsToClasses(List<Agent> kids, MetaConfig config) {
        List<SchoolClass> classes = new ArrayList<>();
        List<Agent> kids2 = new ArrayList<>(kids);
        Collections.shuffle(kids2);
        while (!kids2.isEmpty()){
            SchoolClass schoolClass = new SchoolClass(config.getClassSize());
            for(int i = 0; i <= config.getClassSize(); i++){
                Agent kid = kids2.get(0);
                schoolClass.getStudents().add(kid);
                kid.setSchoolClass(schoolClass);
                kid.setStudent(true);
                kids2.remove(0);
                if(kids2.isEmpty()){
                    break;
                }
            }
            classes.add(schoolClass);
        }
        return classes;
    }
}

