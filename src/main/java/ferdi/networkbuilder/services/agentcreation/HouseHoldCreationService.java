package ferdi.networkbuilder.services.agentcreation;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.AreaMap;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HouseHoldCreationService implements RelationCreationService{
    @Override
    public void buildRelationships(ModelFoundation modelFoundation, MetaConfig config) {
        int maxAgeChild = config.getLivesWithParentsMaxAge();
        AreaMap areaMap = modelFoundation.getAreaMap();

        int hhcount = 0;
        int hhcountNoKidsSingle = 0;
        int hhcountKidsSingle = 0;
        int hhcountNoKidsCouple = 0;
        int hhcountKidsCouple = 0;
        int sumCoupleWithKids = 0;

        int sumSingleWithKids = 0;
        for(Map.Entry<Integer, Map<Integer, Agent>> areaEntry: areaMap.getAreaMap().entrySet()){
            Map<Integer, Agent> fullmap = areaEntry.getValue();
            Map<Integer, Agent> kids = new HashMap<>();
            Map<Integer, Agent> adult = new HashMap<>();

            Map<Integer, Agent> adultKidsCouple = new HashMap<>();
            Map<Integer, Agent> adultNoKidsCouple = new HashMap<>();
            Map<Integer, Agent> adultKidsSingle = new HashMap<>();
            Map<Integer, Agent> adultNoKidsSingle = new HashMap<>();
            for (Map.Entry<Integer,Agent> entry: fullmap.entrySet()){
                if(entry.getValue().getAge() <= maxAgeChild && !entry.getValue().isKids() && !entry.getValue().isCouple()){
                    kids.put(entry.getKey(), entry.getValue());
                }else {
                    adult.put(entry.getKey(), entry.getValue());
                    if(entry.getValue().isCouple() && entry.getValue().isKids()){
                        adultKidsCouple.put(entry.getKey(), entry.getValue());
                    }else if (!entry.getValue().isCouple() && entry.getValue().isKids()){
                        adultKidsSingle.put(entry.getKey(), entry.getValue());
                    }else if (entry.getValue().isCouple() && !entry.getValue().isKids()){
                        adultNoKidsCouple.put(entry.getKey(), entry.getValue());
                    }else if (!entry.getValue().isCouple() && !entry.getValue().isKids()){
                        adultNoKidsSingle.put(entry.getKey(), entry.getValue());
                    }
                }
            }

            List<List<Agent>> singleKidsFam = singleing(adultKidsSingle,config.getRandom());
            List<List<Agent>> coupleKidsFam = coupling(adultKidsCouple,config.getRandom());
            List<List<Agent>> coupleNoKidsFam = coupling(adultNoKidsCouple,config.getRandom());
            List<List<Agent>> mergedList = new ArrayList<>();
            mergedList.addAll(singleKidsFam);
            mergedList.addAll(coupleKidsFam);
            addKids(mergedList,kids,config.getRandom());

            hhcount+= singleKidsFam.size() + adultNoKidsSingle.size() + coupleNoKidsFam.size() + coupleKidsFam.size();
            hhcountNoKidsSingle += adultNoKidsSingle.size();
            hhcountKidsSingle += singleKidsFam.size();
            hhcountNoKidsCouple += coupleNoKidsFam.size();
            hhcountKidsCouple += coupleKidsFam.size();
            for(List<Agent> fam : singleKidsFam){
                sumSingleWithKids += fam.size();
            }
            for(List<Agent> fam : coupleKidsFam){
                sumCoupleWithKids += fam.size();
            }
        }

        double meanSingle = (double) sumSingleWithKids / (double) hhcountKidsSingle;
        double meanCouple = (double) sumCoupleWithKids / (double) hhcountKidsCouple;
        System.out.println("Households created: " + hhcount +
                ", living allone: " + hhcountNoKidsSingle  +
                ", living as couple: " +  hhcountNoKidsCouple +
                ", living alone with Kids: " + hhcountKidsSingle + " with size ~" + meanSingle +
                ", living as two with Kids: " + hhcountKidsCouple + " with size ~" + meanCouple);
    }

    private void addKids(List<List<Agent>> fams,Map<Integer, Agent> kids, Random rng ){
        List<Agent> kidsList = new ArrayList<>();
        for(Map.Entry<Integer,Agent> entry: kids.entrySet()){
            kidsList.add(entry.getValue());
        }
        Collections.shuffle(kidsList,rng);
        Collections.shuffle(fams,rng);
        List<Agent> notassigndkids = new ArrayList<>();

        for(int i = 0; i < kidsList.size(); i++){
            Agent kid = kidsList.get(i);
            if(i < fams.size()){
                for (Agent member: fams.get(i)){
                    kid.setFemale(rng.nextBoolean());
                    member.addHousehold(kid);
                    kid.addHousehold(member);
                }
                fams.get(i).add(kid);
            }else{
                notassigndkids.add(kid);
            }
        }
        if(!notassigndkids.isEmpty()){
            assingNotAssigndKidsToFamilies(fams,notassigndkids,rng);
        }
    }

    private void assingNotAssigndKidsToFamilies(List<List<Agent>> fams, List<Agent> notassigndkids, Random rng){
        while (!notassigndkids.isEmpty()){
            Agent kid = notassigndkids.get(0);
            notassigndkids.remove(0);
            kid.setFemale(rng.nextBoolean());
            List<Agent> fam = fams.get(rng.nextInt(fams.size()));
            fam.add(kid);
            for(Agent member: fam){
                member.addHousehold(kid);
                kid.addHousehold(member);
            }
        }
    }

    private List<List<Agent>> singleing(Map<Integer, Agent> map, Random rng) {
        List<Agent> list = new ArrayList<>();
        for(Map.Entry<Integer,Agent> entry: map.entrySet()){
            list.add(entry.getValue());
        }
        Collections.shuffle(list,rng);
        List<List<Agent>> famList = new ArrayList<>();
        for (Agent f : list) {
            f.setFemale(rng.nextBoolean());
            List<Agent> fam = new ArrayList<>();
            fam.add(f);
            famList.add(fam);
        }
        return famList;
    }

    private List<List<Agent>> coupling(Map<Integer,Agent> map, Random rng){
        List<Agent> list = new ArrayList<>();
        for(Map.Entry<Integer,Agent> entry: map.entrySet()){
            list.add(entry.getValue());
        }
        Collections.shuffle(list,rng);
        List<List<Agent>> famList = new ArrayList<>();
        for(int i = 0; i < list.size()-1; i = i+2){
            Agent f = list.get(i);
            Agent m = list.get(i+1);
            f.addHousehold(m);
            m.addHousehold(f);
            f.setFemale(true);
            m.setFemale(false);
            List<Agent> fam = new ArrayList<>();
            fam.add(m);
            fam.add(f);
            famList.add(fam);
        }
        if(list.size() % 2 == 1){
            Agent ag = list.get(list.size()-1);
            ag.setCouple(false);
            ag.setFemale(rng.nextBoolean());
            List<Agent> fam = new ArrayList<>();
            fam.add(ag);
            famList.add(fam);
        }
        return famList;
    }
}
