package ferdi.networkbuilder.model.agents;

import ferdi.networkbuilder.config.MetaConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TestCenter {


    final private int maxDays;
    final private int maxTests;
    private int positiveTestsArrived;
    private int negativeTestsArrived;


    private int tests;
    private int daysTillRefresh;
    private int testsUsedToday;
    private int positiveTestsArrivedToday;
    private int negativeTestsArrivedToday;
    private int testsUsedTodayBySymptomatic;
    private int testsUsedTodayByContact;
    private int testsNeededTodayBySymptomatic;
    private int testsNeededTodayByContact;

    private final List<TestWithReason> listPrio1;

    private final List<TestWithReason> listPrio2;

    public TestCenter(MetaConfig config, Map<Integer,Agent> agentMap){
        maxTests = (int) (config.getTestsPerNDays() * (double) agentMap.size());
        tests = maxTests;
        maxDays = config.getTestRefreshingDays();
        daysTillRefresh = maxDays;
        testsUsedToday = 0;
        testsUsedTodayBySymptomatic = 0;
        testsUsedTodayByContact = 0;
        testsNeededTodayByContact = 0;
        testsNeededTodayBySymptomatic = 0;
        positiveTestsArrived = 0;
        negativeTestsArrived = 0;
        positiveTestsArrivedToday = 0;
        negativeTestsArrivedToday = 0;

        this.listPrio1 = new ArrayList<>();
        this.listPrio2 = new ArrayList<>();
    }

    public void testAll(MetaConfig config){
        if(config.isTestPrio()){
            Collections.shuffle(listPrio1,config.getRandom());
            Collections.shuffle(listPrio2,config.getRandom());
            listPrio1.addAll(listPrio2);
        }else {
            listPrio1.addAll(listPrio2);
            Collections.shuffle(listPrio1,config.getRandom());
        }
        for(int i = 0; i < listPrio1.size(); i++){
            if(tests > 0){
                listPrio1.get(i).agent().test(config);
                tests--;
                if(listPrio1.get(i).symptoms()){testsUsedTodayBySymptomatic++;}
                else {testsUsedTodayByContact++;}
                testsUsedToday++;
                //System.out.println("ooooooooooooooooooooooooooooooooooooooooooooo "+ testsUsedToday);
            }else{
                listPrio1.get(i).agent().dontTest(listPrio1.get(i).symptoms(),config);
            }
        }
    }
    public void nextDay(){
        listPrio1.clear();
        listPrio2.clear();
        if(daysTillRefresh > 0){
            daysTillRefresh--;
        }else {
            daysTillRefresh = maxDays;
            tests = maxTests;
        }
        testsUsedToday = 0;
        testsUsedTodayBySymptomatic = 0;
        testsUsedTodayByContact = 0;
        testsNeededTodayByContact = 0;
        testsNeededTodayBySymptomatic = 0;
        positiveTestsArrivedToday = 0;
        negativeTestsArrivedToday = 0;
    }

    public void testBecauseSymptoms(Agent agent){
        testsNeededTodayBySymptomatic++;
        listPrio1.add(new TestWithReason(agent,true));
    }

    public void testBecauseContact(Agent agent){
        testsNeededTodayByContact++;
        //System.out.println("testsNeededTodayByContact: " + testsNeededTodayByContact);
        listPrio2.add(new TestWithReason(agent, false));
    }

    public void testedPos() {
        positiveTestsArrivedToday++;
        positiveTestsArrived++;
    }

    public void testedNeg(){
        negativeTestsArrivedToday++;
        negativeTestsArrived++;
    }

    public int getTests() {
        return tests;
    }

    public int getDaysTillRefresh() {
        return daysTillRefresh;
    }

    public int getTestsUsedToday() {
        return testsUsedToday;
    }

    public int getTestsUsedTodayBySymptomatic() {
        return testsUsedTodayBySymptomatic;
    }

    public int getTestsUsedTodayByContact() {
        return testsUsedTodayByContact;
    }

    public int getTestsNeededTodayBySymptomatic() {
        return testsNeededTodayBySymptomatic;
    }

    public int getTestsNeededTodayByContact() {
        return testsNeededTodayByContact;
    }

    public int getPositiveTestsArrived() {
        return positiveTestsArrived;
    }

    public int getNegativeTestsArrived() {
        return negativeTestsArrived;
    }

    public int getPositiveTestsArrivedToday() {
        return positiveTestsArrivedToday;
    }

    public int getNegativeTestsArrivedToday() {
        return negativeTestsArrivedToday;
    }
}
