package ferdi.networkbuilder.metadata;

import ferdi.networkbuilder.model.agents.HealthStatus;
import ferdi.networkbuilder.model.agents.TestCenter;

public class DaySummary {

    final private int day;
    private int susceptible;
    private int incubation;
    private int asymptomatic;
    private int mild;
    private int severe;
    private int severeHos;
    private int dead;
    private int recovered;

    private int infectious;
    private int notInfectious;

    private int daysUntilNextTest;
    private int testsUsedToday;
    private int unusedTests;
    private int testsUsedTodayBySymptomatic;
    private int testsUsedTodayByContact;
    private int testsNeededTodayBySymptomatic;
    private int testsNeededTodayByContact;
    private int positiveTestsArrivedToday;
    private int negativeTestsArrivedToday;


    private int contactsHousehold;
    private int contactsFriends;
    private int contactsRelations;
    private int contactsSchool;
    private int contactsWorkGroup;
    private int contactsWorksite;
    private int contactsWorkPublicFacing;
    private int contactsRandom;
    private int contactsSum;


    private int infectionsHousehold;
    private int infectionsFriends;
    private int infectionsRelations;
    private int infectionsSchool;
    private int infectionsWorkGroup;
    private int infectionsWorksite;
    private int infectionsWorkPublicFacing;
    private int infectionsRandom;
    private int infectionsSum;


    public DaySummary(int day) {
        this.day = day;
        susceptible=0;
        incubation=0;
        asymptomatic=0;
        mild=0;
        severe=0;
        severeHos=0;
        dead=0;
        recovered=0;
        infectious=0;
        notInfectious=0;

        contactsHousehold=0;
        contactsFriends=0;
        contactsRelations=0;
        contactsSchool=0;
        contactsWorkGroup=0;
        contactsWorksite=0;
        contactsWorkPublicFacing=0;
        contactsRandom=0;
        contactsSum=0;

        infectionsHousehold=0;
        infectionsFriends=0;
        infectionsRelations=0;
        infectionsSchool=0;
        infectionsWorkGroup=0;
        infectionsWorksite=0;
        infectionsWorkPublicFacing=0;
        infectionsRandom=0;
        infectionsSum=0;
    }

    public void addInfection(ContactType contactType){
        infectionsSum++;
        switch (contactType){
            case SCHOOL -> {infectionsSchool++;}
            case FRIEND -> {infectionsFriends++;}
            case HOUSEHOLD -> {infectionsHousehold++;}
            case RELATIONS -> {infectionsRelations++;}
            case WORKPLACEGROUP -> {infectionsWorkGroup++;}
            case WORKPLACE -> {infectionsWorksite++;}
            case WORKPLACEPUBLICFACING -> {infectionsWorkPublicFacing++;}
            case RANDOM -> {infectionsRandom++;}
        }
    }

    public void addContact(ContactType contactType){
        contactsSum++;
        switch (contactType){
            case SCHOOL -> {contactsSchool++;}
            case FRIEND -> {contactsFriends++;}
            case HOUSEHOLD -> {contactsHousehold++;}
            case RELATIONS -> {contactsRelations++;}
            case WORKPLACEGROUP -> {contactsWorkGroup++;}
            case WORKPLACE -> {contactsWorksite++;}
            case WORKPLACEPUBLICFACING -> {contactsWorkPublicFacing++;}
            case RANDOM -> {contactsRandom++;}
        }

    }
    public void addOne(HealthStatus healthStatus, boolean infectious, TestCenter testCenter){
        switch (healthStatus){
            case SUSCEPTIBLE -> {susceptible++;}
            case INCUBATION -> {incubation++;}
            case ASYMPTOMATIC -> {asymptomatic++;}
            case MILD -> {mild++;}
            case SEVERE -> {severe++;}
            case SEVERE_HOS-> {severeHos++;}
            case DEAD -> {dead++;}
            case RECOVERED -> {recovered++;}
        }
        if(infectious)this.infectious++;
        else this.notInfectious++;
        daysUntilNextTest = testCenter.getDaysTillRefresh();
        testsUsedToday = testCenter.getTestsUsedToday();
        unusedTests = testCenter.getTests();
        testsNeededTodayByContact = testCenter.getTestsNeededTodayByContact();
        testsNeededTodayBySymptomatic = testCenter.getTestsNeededTodayBySymptomatic();
        testsUsedTodayByContact = testCenter.getTestsUsedTodayByContact();
        testsUsedTodayBySymptomatic = testCenter.getTestsUsedTodayBySymptomatic();
        positiveTestsArrivedToday = testCenter.getPositiveTestsArrivedToday();
        negativeTestsArrivedToday =testCenter.getNegativeTestsArrivedToday();
    }

    public String headerString(){
        return "day"+";"+
                "susceptible"+";"+
                "incubation"+";"+
                "asymptomatic"+";"+
                "mild"+";"+
                "severe"+";"+
                "severeHos"+";"+
                "dead"+";"+
                "recovered"+";"+
                "infectious"+";"+
                "notInfectious"+";"+
                "daysUntilNextTest"+";"+
                "testsUsedToday"+";"+
                "unusedTests"+";"+
                "testsUsedTodayBySymptomatic"+";"+
                "testsNeededTodayBySymptomatic"+";"+
                "testsUsedTodayByContact"+";"+
                "testsNeededTodayByContact"+";"+
                "positiveTestsArrivedToday"+";"+
                "negativeTestsArrivedToday"+";"+
                "infectionsHousehold"+";"+
                "contactsHousehold"+";"+
                "infectionsSchool"+";"+
                "contactsSchool"+";"+
                "infectionsFriends"+";"+
                "contactsFriends"+";"+
                "infectionsRelations"+";"+
                "contactsRelations"+";"+
                "infectionsWorkGroup"+";"+
                "contactsWorkGroup"+";"+
                "infectionsWorksite"+";"+
                "contactsWorksite"+";"+
                "infectionsWorkPublicFacing"+";"+
                "contactsWorkPublicFacing"+";"+
                "infectionsRandom"+";"+
                "contactsRandom";
    }
    public String csvString() {
        return day+";"+
                susceptible+";"+
                incubation+";"+
                asymptomatic+";"+
                mild+";"+
                severe+";"+
                severeHos+";"+
                dead+";"+
                recovered+";"+
                infectious+";"+
                notInfectious+";"+
                daysUntilNextTest+";"+
                testsUsedToday+";"+
                unusedTests+";"+
                testsUsedTodayBySymptomatic+";"+
                testsNeededTodayBySymptomatic+";"+
                testsUsedTodayByContact+";"+
                testsNeededTodayByContact+";"+
                positiveTestsArrivedToday+";"+
                negativeTestsArrivedToday+";"+
                infectionsHousehold+";"+
                contactsHousehold+";"+
                infectionsSchool+";"+
                contactsSchool+";"+
                infectionsFriends+";"+
                contactsFriends+";"+
                infectionsRelations+";"+
                contactsRelations+";"+
                infectionsWorkGroup+";"+
                contactsWorkGroup+";"+
                infectionsWorksite+";"+
                contactsWorksite+";"+
                infectionsWorkPublicFacing+";"+
                contactsWorkPublicFacing+";"+
                infectionsRandom+";"+
                contactsRandom;

    }
    @Override
    public String toString() {
        return "------------------- Day " + day +"\n" +
                ", susceptible                     =" + susceptible + "\n" +
                ", incubation time                 =" + incubation +"\n" +
                ", asymptomatic                    =" + asymptomatic +"\n" +
                ", mild symptoms                   =" + mild +"\n" +
                ", severe symptoms                 =" + severe +"\n" +
                ", in hospital                     =" + severeHos +"\n" +
                ", dead                            =" + dead +"\n" +
                ", recovered                       =" + recovered + "\n" +
                "    -  -"+ "\n" +
                ", infectious                      =" + infectious +"\n" +
                ", not infectious                  =" + notInfectious +"\n" +
                "    -  -"+ "\n" +
                ", days till new tests             =" + daysUntilNextTest +"\n" +
                ", tests used today                =" + testsUsedToday +"\n" +
                ", unused tests                    =" + unusedTests +"\n" +
                ", tests used/needed - symptoms    =" + testsUsedTodayBySymptomatic+"/"+testsNeededTodayBySymptomatic +"\n"+
                ", tests used/needed - contacts    =" + testsUsedTodayByContact+"/"+testsNeededTodayByContact +"\n"+
                ", today's arrived tests +/-       =" + positiveTestsArrivedToday +"/"+negativeTestsArrivedToday + "\n" +
                "    -  -"+ "\n" +
                ", infections/contacts             =" + infectionsSum+"/"+contactsSum +"\n"+
                ", household                       =" + infectionsHousehold+"/"+contactsHousehold +"\n"+
                ", school                          =" + infectionsSchool+"/"+contactsSchool +"\n"+
                ", friends                         =" + infectionsFriends+"/"+contactsFriends +"\n"+
                ", relations                       =" + infectionsRelations+"/"+contactsRelations +"\n"+
                ", workplace close colleagues      =" + infectionsWorkGroup+"/"+contactsWorkGroup +"\n"+
                ", workplace other colleagues      =" + infectionsWorksite+"/"+contactsWorksite +"\n"+
                ", workplace with public facing    =" + infectionsWorkPublicFacing+"/"+contactsWorkPublicFacing +"\n"+
                ", random                          =" + infectionsRandom+"/"+contactsRandom +"\n";
    }
}
