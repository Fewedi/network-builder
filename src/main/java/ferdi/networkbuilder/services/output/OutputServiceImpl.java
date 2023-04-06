package ferdi.networkbuilder.services.output;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.metadata.NetworkSummaryData;
import ferdi.networkbuilder.metadata.SummaryTemplate;
import ferdi.networkbuilder.model.agents.Agent;
import ferdi.networkbuilder.model.collections.AgeMap;
import ferdi.networkbuilder.model.collections.ModelFoundation;
import ferdi.networkbuilder.model.contacts.Worksite;
import ferdi.networkbuilder.model.contacts.WorksiteCloseColleagueGroup;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.MAX_VALUE;

@Service
public class OutputServiceImpl implements OutputService{

    @Override
    public NetworkSummaryData createNetworkSummary(ModelFoundation modelFoundation, MetaConfig config) {
        NetworkSummaryData summary = new NetworkSummaryData();
        setAgentCount(modelFoundation,summary);
        setMinors(modelFoundation,summary,config);
        setAdults(modelFoundation,summary,config);
        setFam(modelFoundation,summary,config);
        setAreas(modelFoundation,summary,config);
        setFriends(modelFoundation,summary,config);
        setRelatives(modelFoundation,summary,config);
        setSchoolClasses(modelFoundation,summary,config);
        setWorksites(modelFoundation,summary,config);
        setHousehold(modelFoundation,summary,config);
        setDemograpic(modelFoundation,summary,config);
        if(config.isPrintNetworkData()){
            System.out.println(summary);
        }
        if(config.isCreateFileNetworkData()){
            try {
                createFile(summary,config);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return summary;
    }

    private void createFile(NetworkSummaryData summary, MetaConfig config) throws IOException {
        String path = config.getPathOutput();
        String dir = "/network_metadata";
        File file = new File(path + dir);
        if (!file.exists()) { // check if directory does not exist
            file.mkdirs(); // create directory and its parent directories if they do not exist
        }
        boolean created = false;
        int c = 1;
        while (!created) {
            File fileToPrint = new File(file.getAbsolutePath() + "/Network_Summary_"+c+".txt");
            if (fileToPrint.exists()) {
                c++;
            } else {
                created = true;
                String input = summary.toString();
                createFile(input,fileToPrint);
            }
        }
    }

    private void createFile(String string, File file) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] bytes = string.getBytes();
            outputStream.write(bytes);
            outputStream.close();
            System.out.println("created and successfully written to: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("An error occurred while creating or writing to " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }

    private void setDemograpic(ModelFoundation modelFoundation, NetworkSummaryData summary, MetaConfig config) {
        Map<Short,Integer> demographic = new HashMap<>();
        AgeMap ageMap = modelFoundation.getAgeMap();
        for(short i = 0; i<= 100; i++){
            if(ageMap.getAgeMap().containsKey(i)){
                demographic.put(i,ageMap.getAgeMap().get(i).size());
            }else {
                demographic.put(i,0);
            }
        }
        summary.setDemographic(demographic);
    }

    private void setHousehold(ModelFoundation modelFoundation, NetworkSummaryData summary, MetaConfig config) {
        Map<Integer,Agent> map = modelFoundation.getFullMap();
        int min = MAX_VALUE;
        int max = 0;
        int amountOfPeopleInHousehold = 0;
        for (Map.Entry<Integer,Agent> entry: map.entrySet()){
            int amount = entry.getValue().getHousehold().size();
            amountOfPeopleInHousehold += amount;
            if(amount <= min){
                min = amount;
            }
            if(amount >= max){
                max = amount;
            }
        }
        double average = (double) amountOfPeopleInHousehold / (double) map.size();
        summary.setAmountOfPeopleInHousehold(amountOfPeopleInHousehold);
        summary.setHousehold(new SummaryTemplate(average,min,max));
    }

    private void setWorksites(ModelFoundation modelFoundation, NetworkSummaryData summary, MetaConfig config) {
        int workingPop = modelFoundation.getAgeMapRange(config.getWorkingAge(), config.getWorkingAgeMax()).size();
        int amountOfGroups = 0;
        List<Worksite> worksites = modelFoundation.getWorksites();
        int amountOfWorksites = worksites.size();
        int minWorksite = MAX_VALUE;
        int maxWorksite = 0;
        int minGroup = MAX_VALUE;
        int maxGroup = 0;
        for (Worksite worksite: worksites){
            amountOfGroups += worksite.getGroups().size();
            for (WorksiteCloseColleagueGroup group: worksite.getGroups()){
                int amountInGroup = group.getSize();
                if(amountInGroup <= minGroup){
                    minGroup = amountInGroup;
                }
                if(amountInGroup >= maxGroup){
                    maxGroup = amountInGroup;
                }
            }
            int amount = worksite.getEmployees().size();
            if(amount <= minWorksite){
                minWorksite = amount;
            }
            if(amount >= maxWorksite){
                maxWorksite = amount;
            }
        }
        double averageGroup = (double) workingPop / (double) amountOfGroups;
        double averageWorksite = (double) workingPop / (double) amountOfWorksites;
        summary.setCloseColleaguesGroupsAmount(amountOfGroups);
        summary.setCloseColleagues(new SummaryTemplate(averageGroup,minGroup,maxGroup));
        summary.setWorkingPop(workingPop);
        summary.setWorksitesAmount(amountOfWorksites);
        summary.setWorksites(new SummaryTemplate(averageWorksite,minWorksite,maxWorksite));
    }

    private void setSchoolClasses(ModelFoundation modelFoundation, NetworkSummaryData summary, MetaConfig config) {
        Map<Integer, Agent> map =  modelFoundation.getAgeMapRange(config.getMinAgeForSchool(), config.getWorkingAge()-1);
        int classesAmount = 0;
        int kidsInClasses = 0;
        int min = MAX_VALUE;
        int max = 0;
        for(Map.Entry<Integer, Agent> entry: map.entrySet() ){

            int amount = entry.getValue().getSchoolClass().getStudents().size();
            classesAmount++;
            kidsInClasses += amount;
            if(amount <= min){
                min = amount;
            }
            if(amount >= max){
                max = amount;
            }

        }

        double average = (double) kidsInClasses / (double) classesAmount;
        summary.setClassesAmount(classesAmount);
        summary.setKidsInClasses(kidsInClasses);
        summary.setSchoolClass(new SummaryTemplate(average,min,max));
    }

    private void setRelatives(ModelFoundation modelFoundation, NetworkSummaryData summary, MetaConfig config) {
        Map<Integer, Agent> map =  modelFoundation.getFullMap();
        int relativesSum = 0;
        int min = MAX_VALUE;
        int max = 0;
        for(Map.Entry<Integer, Agent> entry: map.entrySet() ){
            int amount = entry.getValue().getRealtives().size();
            relativesSum += amount;
            if(amount <= min){
                min = amount;
            }
            if(amount >= max){
                max = amount;
            }
        }
        double average = (double) relativesSum / (double) map.size();
        summary.setRelativesSum(relativesSum);
        summary.setRelatives(new SummaryTemplate(average,min,max));
    }

    private void setFriends(ModelFoundation modelFoundation, NetworkSummaryData summary, MetaConfig config) {
        Map<Integer, Agent> map =  modelFoundation.getAgeMapRange(config.getFriendshipsMinAge(), 100);
        int friendsSum = 0;
        int min = MAX_VALUE;
        int max = 0;
        for(Map.Entry<Integer, Agent> entry: map.entrySet() ){
            int amount = entry.getValue().getFriends().size();
            friendsSum += amount;
            if(amount <= min){
                min = amount;
            }
            if(amount >= max){
                max = amount;
            }
        }
        double average = (double) friendsSum / (double) map.size();
        summary.setFriendsSum(friendsSum);
        summary.setFriends(new SummaryTemplate(average,min,max));
    }

    private void setAreas(ModelFoundation modelFoundation, NetworkSummaryData summary, MetaConfig config) {
        int amountArea = modelFoundation.getAreaMap().getCount();
        double average = modelFoundation.getAreaMap().getAverageSize();
        int min = modelFoundation.getAreaMap().getMinSize();
        int max = modelFoundation.getAreaMap().getMaxSize();
        summary.setAmountAreas(amountArea);
        summary.setArea(new SummaryTemplate(average,min,max));
    }

    private void setFam(ModelFoundation modelFoundation, NetworkSummaryData summary, MetaConfig config) {
        int counterCouple = 0;
        int counterSingle = 0;
        int counterKids = 0;
        int counterNoKids = 0;
        Map<Integer, Agent> map = modelFoundation.getAgeMapRange(config.getWorkingAge(), 100);
        for(Map.Entry<Integer, Agent> entry: map.entrySet() ){
            if(entry.getValue().isKids()){
                counterKids++;
            }else {
                counterNoKids++;
            }
            if(entry.getValue().isCouple()){
                counterCouple++;
            }else {
                counterSingle++;
            }
        }
        summary.setLivesInCouple(counterCouple);
        summary.setLivesInCouple(counterSingle);
        summary.setLivesWithKids(counterKids);
        summary.setLivesWithoutKids(counterNoKids);
    }



    private void setAdults(ModelFoundation modelFoundation, NetworkSummaryData summary, MetaConfig config) {
        summary.setAdults(modelFoundation.getAgeMapRange(config.getWorkingAge(), 100).size());

    }

    private void setMinors(ModelFoundation modelFoundation, NetworkSummaryData summary, MetaConfig config) {
        summary.setMinors(modelFoundation.getAgeMapRange(0,config.getWorkingAge()-1).size());
    }

    private void setAgentCount(ModelFoundation modelFoundation, NetworkSummaryData summary) {
        summary.setAgentCount(modelFoundation.getFullMap().size());
    }
}
