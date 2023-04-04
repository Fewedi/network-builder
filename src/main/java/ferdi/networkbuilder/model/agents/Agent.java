package ferdi.networkbuilder.model.agents;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.metadata.ContactType;
import ferdi.networkbuilder.metadata.DaySummary;
import ferdi.networkbuilder.model.contacts.FriendList;
import ferdi.networkbuilder.model.contacts.HouseholdList;
import ferdi.networkbuilder.model.contacts.RelativesList;
import ferdi.networkbuilder.model.contacts.SchoolClass;
import ferdi.networkbuilder.model.contacts.Worksite;
import ferdi.networkbuilder.model.contacts.WorksiteCloseColleagueGroup;
import org.apache.commons.math3.distribution.PoissonDistribution;

import java.io.Serializable;
import java.util.*;

public abstract class Agent implements Serializable {

    private Health health;
    private final int id;
    private final short age;
    private boolean couple;
    private final boolean kids;
    private final int area;

    private SchoolClass schoolClass;

    private boolean female;

    private final RelativesList relativesList;
    private final FriendList friendList;
    private final HouseholdList householdList;
    private Worksite worksite;

    private WorksiteCloseColleagueGroup worksiteCloseColleagueGroup;

    private boolean workCustomerFacing = false;

    private final CTA app;
    private boolean works = false;
    private boolean student = false;

    private boolean symptoms = false;
    private boolean isolated = false;
    private boolean usesCTA = false;
    private boolean didITestPositiveInMyLife = false;
    private boolean waitUntilSymptomsGone=false;
    private int timeToIsolate = -1;

    private boolean gotInfectedYesterday = false;
    private ContactType gotInfectedYesterdayBy = ContactType.NULL;
    private int daysUntilTestArrives = -1;

    public void reset(MetaConfig config){
        //health.reset(config,age);
        app.reset();
        daysUntilTestArrives = -1;
        gotInfectedYesterdayBy = ContactType.NULL;
        gotInfectedYesterday = false;
        timeToIsolate = -1;
        usesCTA = false;
        isolated = false;
        symptoms = false;
    }

    public Agent(int id, short age, boolean couple, boolean kids, int area) {
        this.id = id;
        this.age = age;
        this.couple = couple;
        this.kids = kids;
        this.area = area;
        friendList = new FriendList();
        householdList = new HouseholdList();
        relativesList = new RelativesList();
        app = new CTA();
    }

    public void meetAndInfect(List<Agent> agentsInArea, MetaConfig config, int day, DaySummary daySummary) {
        //System.out.println(" -------------------- id: " + id + " infected " + health.isInfectedCurrently());
        if(!health.getHealthStatus().equals(HealthStatus.SEVERE_HOS)){
            if(usesCTA){
                List<Agent> todaysContacts = new ArrayList<>();
                if(config.isSpread_via_household() && day<=config.getEncountersPerWeekHousehold()){
                    todaysContacts.addAll(householdList.meetAndInfect(config,health.isInfectious(),isolated,daySummary));
                }
                if(config.isSpread_via_friends() && day<=config.getEncountersPerWeekFriends() && !isolated){
                    todaysContacts.addAll(friendList.meetAndInfect(config,health.isInfectious(),daySummary));
                }
                if(config.isSpread_via_relatives() && !isolated && config.getEncountersPerWeekRelations() < config.getRandom().nextDouble()){
                    todaysContacts.addAll(relativesList.meetAndInfect(config,health.isInfectious(),daySummary));
                }
                if(!isolated && works && day<=config.getEncountersPerWeekWork()){
                    if(config.isSpread_via_work_group()) todaysContacts.addAll(worksiteCloseColleagueGroup.meetAndInfect(this,config,health.isInfectious(),daySummary));
                    if(config.isSpread_via_work_not_group()) todaysContacts.addAll(worksite.meetAndInfect(this,config,health.isInfectious(),daySummary));
                    if(config.isSpread_via_work_public_facing() && workCustomerFacing){
                        todaysContacts.addAll(meetAndInfectRandom(agentsInArea,config,config.getTransmissionWorkCustomersFacingP(),config.getTransmissionRateWorkCustomersFacing(),daySummary,ContactType.WORKPLACEPUBLICFACING));
                    }
                }
                if(config.isSpread_via_school() && !isolated && student && day<=config.getEncountersPerWeekSchool()){
                    todaysContacts.addAll(schoolClass.meetAndInfect(this,config,health.isInfectious(),daySummary));
                }
                if(!isolated && age >= config.getEncountersPerWeekOldAge()) {
                    if (config.isSpread_via_random() &&config.getEncountersPerWeekRandomOld() < config.getRandom().nextDouble()) {
                        todaysContacts.addAll(meetAndInfectRandom(agentsInArea, config,config.getTransmissionRandomP(),config.getTransmissionRateRandom(),daySummary,ContactType.RANDOM));
                    }
                }else {
                    if (config.isSpread_via_random() && !isolated && day<= config.getEncountersPerWeekRandomYoung()) {
                        todaysContacts.addAll(meetAndInfectRandom(agentsInArea, config, config.getTransmissionRandomP(),config.getTransmissionRateRandom(),daySummary,ContactType.RANDOM));
                    }
                }
                app.addAll(todaysContacts,config);
            }else {
                if(config.isSpread_via_household() && day<=config.getEncountersPerWeekHousehold()){
                    householdList.meetAndInfect(config,health.isInfectious(),isolated,daySummary);
                }
                if(config.isSpread_via_friends() && !isolated && day<=config.getEncountersPerWeekFriends()){
                    friendList.meetAndInfect(config,health.isInfectious(),daySummary);
                }
                if(config.isSpread_via_relatives() && !isolated && config.getEncountersPerWeekRelations() < config.getRandom().nextDouble()){
                    relativesList.meetAndInfect(config,health.isInfectious(),daySummary);
                }
                if(!isolated && works && day<=config.getEncountersPerWeekWork()){
                    if(config.isSpread_via_work_group()) worksiteCloseColleagueGroup.meetAndInfect(this,config,health.isInfectious(),daySummary);
                    if(config.isSpread_via_work_not_group())worksite.meetAndInfect(this,config,health.isInfectious(),daySummary);
                    if(config.isSpread_via_work_public_facing() &&workCustomerFacing){
                        meetAndInfectRandom(agentsInArea,config,config.getTransmissionWorkCustomersFacingP(),config.getTransmissionRateWorkCustomersFacing(),daySummary,ContactType.WORKPLACEPUBLICFACING);
                    }
                }
                if(config.isSpread_via_school() &&!isolated && student && day<=config.getEncountersPerWeekSchool()){
                    schoolClass.meetAndInfect(this,config,health.isInfectious(),daySummary);
                }
                if(!isolated && age >= config.getEncountersPerWeekOldAge()) {
                    if (config.isSpread_via_random() &&config.getEncountersPerWeekRandomOld() < config.getRandom().nextDouble()) {
                        meetAndInfectRandom(agentsInArea, config,config.getTransmissionRandomP(),config.getTransmissionRateRandom(),daySummary,ContactType.RANDOM);
                    }
                }else {
                    if (config.isSpread_via_random() &&!isolated && day<= config.getEncountersPerWeekRandomYoung()) {
                        meetAndInfectRandom(agentsInArea, config, config.getTransmissionRandomP(),config.getTransmissionRateRandom(),daySummary,ContactType.RANDOM);
                    }
                }
            }
        }
    }

    private List<Agent> meetAndInfectRandom(List<Agent> agentsInArea, MetaConfig config, double k,double factor  ,DaySummary daySummary,ContactType contactType) {
        List<Agent> l = new ArrayList<>();
        int max = agentsInArea.size();
        double lambda = (double) max * k;
        PoissonDistribution poissonDistribution = new PoissonDistribution(lambda);
        int amount = poissonDistribution.sample();
        Collections.shuffle(agentsInArea,config.getRandom());
        //System.out.println(lambda + " "+ amount);
        if(health.isInfectious()) {
            double prop = config.getBaselineTransmissionProp() * factor;
            for(int i = 0; i < amount && i < agentsInArea.size(); i++){
                if(prop > config.getRandom().nextDouble()){
                    agentsInArea.get(i).infect(config, contactType);
                    //System.out.println(" infect random");
                }
                l.add(agentsInArea.get(i));
                daySummary.addContact(contactType);
            }
        }else {
            for(int i = 0; i < amount && i < agentsInArea.size(); i++){
                l.add(agentsInArea.get(i));
                daySummary.addContact(contactType);
            }
        }
        return l;
    }


    public void test(MetaConfig config) {
        daysUntilTestArrives = config.getDaysUntilTestsArrive();
    }

    private void reactToTest(TestCenter testCenter, MetaConfig config){
        daysUntilTestArrives = -1;
        if(health.isInfectedCurrently()){
            testCenter.testedPos();
            //react to positive test result
            didITestPositiveInMyLife = true;
            //self-isolate
            selfIsolateTest(config);
            //notify household
            householdList.notifyThose(config,testCenter);
            //notify relatives
            relativesList.notifyThose(config,testCenter);
            // notify via App
            if(usesCTA){
                app.notifyThose(config,testCenter);
            }
            // notify class
            if(student){
                schoolClass.notifyClassmates(config,testCenter);
            }
        }else {
            testCenter.testedNeg();
           // System.out.println(id +" tested neg");
        }
    }

    public void dontTest(boolean symptoms, MetaConfig config) {
        double prob;
        if(symptoms){
            prob = config.getIsolationProbabilityNoTest();
            if (withCertainProb(config.getRandom(), prob)) {
                isolated = true;
                waitUntilSymptomsGone = true;
            }
        }else {
            prob = config.getIsolationProbabilityCTAAndRelativesNoTest();
            if (withCertainProb(config.getRandom(), prob)) {
                isolated = true;
                waitUntilSymptomsGone = false;
                timeToIsolate = config.getIsolationTimeIfContact();
            }
        }
    }

    public void applyBeeingInfected(DaySummary daySummary,MetaConfig config){
        if(gotInfectedYesterday){
            gotInfectedYesterday = false;

            boolean inf = health.infect(age,config);
            if (inf){
                daySummary.addInfection(gotInfectedYesterdayBy);
            }
            gotInfectedYesterdayBy = ContactType.NULL;
        }
    }
    public void nextDay(MetaConfig config, TestCenter testCenter, DaySummary daySummary){
        if(daysUntilTestArrives > 0){
         //   System.out.println(id + ", daysUntilTestArrives 1: " + daysUntilTestArrives);
            daysUntilTestArrives--;
            //System.out.println(id + " "+daysUntilTestArrives);
        }
        if (daysUntilTestArrives == 0){
        //    System.out.println(id + ", daysUntilTestArrives 2: " + daysUntilTestArrives);
            reactToTest(testCenter,config);
        }
        if(waitUntilSymptomsGone){
            if(!health.doIHaveSymptoms()){
                waitUntilSymptomsGone=false;
                isolated = false;
                symptoms = false;
            }
        }else {
            if(timeToIsolate>0){
                timeToIsolate--;
            }else if (timeToIsolate == 0){
                timeToIsolate = -1;
                isolated=false;
            }
        }
    }

    public void cTAContactIsPositive(MetaConfig config, TestCenter testCenter){
        if(usesCTA){
            testBecauseContact(config,testCenter);
        }//System.out.println(id + " via CTA: " + toString() +" --- "+ getHealth() + " --- " + toStringIsolation()+ " --- " +toStringHousehold());
    }

    public void relativeIsPositive(MetaConfig config, TestCenter testCenter){
        testBecauseContact(config,testCenter);
        //System.out.println(id + " via relative: " + toString() +" --- "+ getHealth() + " --- " + toStringIsolation()+ " --- " +toStringHousehold());
    }

    public void householdMemberIsPositive(MetaConfig config, TestCenter testCenter){
        selfIsolateContact(config);
        testBecauseContact(config,testCenter);
        //System.out.println(id + " via household: " + toString() +" --- "+ getHealth() + " --- " + toStringIsolation()+ " --- " +toStringHousehold());
    }


    public void classmateIsPositive(MetaConfig config, TestCenter testCenter){
        selfIsolateSchool(config);
        testBecauseContact(config,testCenter);
    }

    private void testBecauseSymptoms(MetaConfig config, TestCenter testCenter){
        if((!health.getHealthStatus().equals(HealthStatus.RECOVERED) || config.isDoITestIfIRecovered())
                && daysUntilTestArrives == -1
                && (config.isDoITestIfIAlreadyTestedPositive() || !didITestPositiveInMyLife)){

            //System.out.println(id+ ": test caused symptoms");
            //get tested
            testCenter.testBecauseSymptoms(this);
        }
    }
    private void testBecauseContact(MetaConfig config, TestCenter testCenter){
        if((!health.getHealthStatus().equals(HealthStatus.RECOVERED) || config.isDoITestIfIRecovered())
                && daysUntilTestArrives == -1
                && (config.isDoITestIfIAlreadyTestedPositive() || !didITestPositiveInMyLife)){
            //get tested
            //System.out.println(id+ ": test caused contact");
            testCenter.testBecauseContact(this);
        }
    }

    private void selfIsolateContact(MetaConfig config) {
        double prob = config.getIsolationProbabilityCTAAndRelativesNoTest();
        if(withCertainProb(config.getRandom(), prob)){
            //System.out.println(id + " self-isolated because of contact " + config.getIsolationTimeIfContact());
            isolated = true;
            waitUntilSymptomsGone = false;
            timeToIsolate = config.getIsolationTimeIfContact();
        }else {
            //System.out.println(id + " not self-isolated because of contact");
        }
    }

    private void selfIsolateTest(MetaConfig config) {
        double prob = config.getIsolationProbabilityTest();
        if(withCertainProb(config.getRandom(), prob)){
            //System.out.println(id + " self-isolated because of test");
            isolated = true;
            waitUntilSymptomsGone = true;
        }else {
            //System.out.println(id + " not self-isolated because of test");
        }
    }
    private void selfIsolateSchool(MetaConfig config) {
        //System.out.println(id + " self-isolated because of school");
        isolated = true;
        waitUntilSymptomsGone = false;
        timeToIsolate = config.getIsolationTimeIfContact();
    }


    public void dealWithSymptomsFromCovid(MetaConfig config, TestCenter testCenter) {
        if(health.doIHaveSymptoms() && !symptoms){
            symptoms = true;
            //react to Symptoms from Covid
            testBecauseSymptoms(config,testCenter);
        }else if (!health.doIHaveSymptoms() && symptoms){
            symptoms = false;
            isolated = false;
        }
        if(daysUntilTestArrives != -1){
            //System.out.println(id + " 2 -------------------------------------------------------------------------DaysUntilTestsArrive: " + daysUntilTestArrives);
        }
    }

    public void dealWithSymptomsFromILI(MetaConfig config, TestCenter testCenter) {
        if(config.getRandom().nextDouble() < config.getProportionOfPopWithILIPerDay() && config.getRandom().nextDouble() < config.getProportionOfTestingBecauseILI()){
            testBecauseSymptoms(config,testCenter);
        }
    }
    private boolean withCertainProb(Random rng, double prob){
        return rng.nextDouble() < prob;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public boolean isWorks() {
        return works;
    }

    public void setWorks(boolean works) {
        this.works = works;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }


    public int getId() {
        return id;
    }

    public short getAge() {
        return age;
    }

    public boolean isCouple() {
        return couple;
    }

    public boolean isKids() {
        return kids;
    }

    public int getArea() {
        return area;
    }



    public String toStringIsolation() {
        return "symptoms: " + health.doIHaveSymptoms()
                + ", infected: " + health.isInfectedCurrently()
                + ", infectious: " + health.isInfectious()
                + ", days until test arrives: " + daysUntilTestArrives
                + ", isolated: " + isolated + " for " + timeToIsolate + " days bzw until symptoms gone: " + waitUntilSymptomsGone;
    }
    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id+
                ", age=" + age +
                ", area=" + area +
                "}";
    }

    public void addFriend(Agent friend) {
        if(!friendList.contains(friend)){
            friendList.add(friend);
        }
    }

    public String toStingFriends(){
        StringBuilder s = new StringBuilder(id + ": |");
        for (Agent friend : friendList){
            s.append(friend.getId()).append("|");
        }
        return s.toString();
    }

    public String toStringHousehold(){
        //StringBuilder s = new StringBuilder(id + ", age: " + age + ", area: " + area + ", kids " + kids + ", couple: " + couple + ": ----- |");
        StringBuilder s = new StringBuilder("household |");

        for (Agent friend : householdList){
            s.append(friend.getId()).append(", age: ").append(friend.age).append(", area: ").append(friend.area).append("|");
        }
        return s.toString();
    }

    public String toStringRelatives(){
        //StringBuilder s = new StringBuilder(id + ", age: " + age + ", area: " + area + ", kids " + kids + ", couple: " + couple + ": ----- |");
        StringBuilder s = new StringBuilder("relatives |");

        for (Agent friend : relativesList){
            s.append(friend.getId()).append(", age: ").append(friend.age).append(", area: ").append(friend.area).append("|");
        }
        return s.toString();
    }

    public FriendList getFriends() {
        return friendList;
    }

    public void addHousehold(Agent agent) {
        householdList.add(agent);
    }

    public void setFemale(boolean b) {
        female = b;
    }

    public void setCouple(boolean b) {
        couple = b;
    }

    public void addWorksite(Worksite worksite) {
        this.worksite = worksite;
    }

    public boolean isFemale() {
        return female;
    }

    public Worksite getWorksite() {
        return worksite;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public boolean isWorkCustomerFacing() {
        return workCustomerFacing;
    }

    public void setWorkCustomerFacing(boolean workCustomerFacing) {
        this.workCustomerFacing = workCustomerFacing;
    }

    public void setWorksiteCloseColleagueGroup(WorksiteCloseColleagueGroup worksiteCloseColleagueGroup) {
        this.worksiteCloseColleagueGroup = worksiteCloseColleagueGroup;
    }

    public HouseholdList getHousehold() {
        return householdList;
    }

    public RelativesList getRealtives() {
        return relativesList;
    }

    public boolean getFemale() {
        return female;
    }


    public void setUsesCTA(boolean usesCTA) {
        this.usesCTA = usesCTA;
    }

    public boolean isDidITestPositiveInMyLife() {
        return didITestPositiveInMyLife;
    }

    public boolean isIsolated() {
        return isolated;
    }


    public void infect(MetaConfig config, ContactType contactType) {
        gotInfectedYesterday = true;
        gotInfectedYesterdayBy = contactType;
    }


}
