package ferdi.networkbuilder.services.agentcreation;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import ferdi.networkbuilder.model.contacts.Worksite;
import ferdi.networkbuilder.model.contacts.WorksiteCloseColleagueGroup;
import ferdi.networkbuilder.model.contacts.WorksiteType;
import org.apache.commons.math3.distribution.BinomialDistribution;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorksiteCreationService implements RelationCreationService{
    @Override
    public void buildRelationships(ModelFoundation modelFoundation, MetaConfig config) {
        Map<Integer, Agent> workingPop = modelFoundation.getAgeMapRange(config.getWorkingAge(), config.getWorkingAgeMax());
        ArrayList<Agent> workingPopList = new ArrayList<>();
        for(Map.Entry<Integer,Agent> entry: workingPop.entrySet()){
            workingPopList.add(entry.getValue());
        }
        Collections.shuffle(workingPopList,config.getRandom());

        int worksiteCounter = 0;
        int worksiteSizeCounter = 0;
        int workingPeople = 0;

        while(!workingPopList.isEmpty()){
            WorksiteType nextWorkssite = getNextWorksiteTypes(modelFoundation.getWorksiteTypes(),config.getRandom());

            Worksite worksite = getNextWorksite(nextWorkssite,config.getRandom());
            worksiteCounter ++;

            for(int i = 0; i<worksite.getSize(); i++){
                workingPeople++;
                Agent worker = workingPopList.get(0);
                worker.addWorksite(worksite);
                worker.setWorks(true);
                if(config.getRandom().nextDouble() <= config.getCustomerFacingRatio()){
                    worker.setWorkCustomerFacing(true);
                }
                worksite.add(workingPopList.get(0));
                workingPopList.remove(0);
                if(workingPopList.isEmpty()){
                    break;
                }
            }
            worksiteSizeCounter += worksite.getEmployees().size();
            modelFoundation.getWorksites().add(worksite);
        }
        System.out.println("Worksites crated: " + worksiteCounter + ", with working people: " + workingPeople + ", with people in worksites: " + worksiteSizeCounter + ", mean worksites size: " + ((double) worksiteSizeCounter / (double) worksiteCounter));
        setCloseColegueGroups(modelFoundation.getWorksites(),config);
    }

    private void setCloseColegueGroups(List<Worksite> worksites, MetaConfig config) {
        int groupCounter = 0;
        int agentCounter = 0;
        for(Worksite worksite: worksites){
            List<Agent> employees = new ArrayList<>(worksite.getEmployees());
            Collections.shuffle(employees,config.getRandom());
            while(!employees.isEmpty()){
                int size = getBinomialDistributedNr(config.getRandom(),config.getCloseColleaguesMin(),config.getCloseColleaguesAverage());
                WorksiteCloseColleagueGroup group = new WorksiteCloseColleagueGroup(size);
                for (int i = 0; i < size; i++){
                    Agent agent = employees.get(0);
                    group.addColleague(agent);
                    agentCounter++;
                    agent.setWorksiteCloseColleagueGroup(group);
                    employees.remove(0);
                    if(employees.isEmpty()){
                        break;
                    }
                }
                worksite.addGroup(group);
                groupCounter++;
            }
        }
        System.out.println("Groups in Worksites crated: " + groupCounter + ", with working people: " + agentCounter + ", mean group size: " + ((double) agentCounter / (double) groupCounter));

    }

    private int getBinomialDistributedNr(Random rng, int min, int av){
        int n = (av - min) * 2;  // number of trials
        double p = 0.5;  // probability of success
        BinomialDistribution binomial = new BinomialDistribution(n, p);
        return binomial.inverseCumulativeProbability(rng.nextDouble()) + min;
    }

    private Worksite getNextWorksite(WorksiteType nextWorksite, Random rng) {
        int min = nextWorksite.getMinSize();
        int max = nextWorksite.getMaxSize();
        int workers = min + rng.nextInt(max - min);
        return new Worksite(workers);
    }

    private WorksiteType getNextWorksiteTypes(List<WorksiteType> worksiteTypes, Random rng) {
        double cumulativeProbability = 0.0;
        for (int i = 0; i < worksiteTypes.size(); i++) {
            cumulativeProbability += worksiteTypes.get(i).getRelativeAmount();
            if (rng.nextDouble() <= cumulativeProbability) {
                return worksiteTypes.get(i);
            }
        }
        return null;
    }
}
