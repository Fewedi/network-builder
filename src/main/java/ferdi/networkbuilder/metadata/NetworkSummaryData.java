package ferdi.networkbuilder.metadata;

import java.util.Map;

public class NetworkSummaryData {
    private int agentCount;
    private int minors;
    private int adults;
    private int livesInCouple;
    private int livesAsSingle;

    private int livesWithKids;
    private int livesWithoutKids;

    private int amountAreas;
    private SummaryTemplate area;


    private int friendsSum;
    private SummaryTemplate friends;

    private int relativesSum;
    private SummaryTemplate relatives;

    private int classesAmount;
    private int kidsInClasses;
    private SummaryTemplate schoolClass;

    private int closeColleaguesGroupsAmount;
    private SummaryTemplate closeColleagues;
    private int workingPop;
    private int worksitesAmount;
    private SummaryTemplate worksites;

    private int amountOfPeopleInHousehold;
    private SummaryTemplate household;

    private Map<Short,Integer> demographic;


    @Override
    public String toString() {
        return "NetworkSummaryData{" +
                "\n agentCount=" + agentCount +
                "\n minors=" + minors +
                "\n adults=" + adults +
                "\n livesInCouple=" + livesInCouple +
                "\n livesAsSingle=" + livesAsSingle +
                "\n livesWithKids=" + livesWithKids +
                "\n livesWithoutKids=" + livesWithoutKids +
                "\n amountAreas=" + amountAreas +
                "\n area=" + area +
                "\n friendsSum=" + friendsSum +
                "\n friends=" + friends +
                "\n relativesSum=" + relativesSum +
                "\n relatives=" + relatives +
                "\n classesAmount=" + classesAmount +
                "\n kidsInClasses=" + kidsInClasses +
                "\n schoolClass=" + schoolClass +
                "\n closeColleaguesGroupsAmount=" + closeColleaguesGroupsAmount +
                "\n closeColleagues=" + closeColleagues +
                "\n workingPop=" + workingPop +
                "\n worksitesAmount=" + worksitesAmount +
                "\n worksites=" + worksites +
                "\n amountOfPeopleInHousehold=" + amountOfPeopleInHousehold +
                "\n household=" + household +
                "\n demographic=" + demographic +
                '}';
    }

    public Map<Short, Integer> getDemographic() {
        return demographic;
    }

    public void setDemographic(Map<Short, Integer> demographic) {
        this.demographic = demographic;
    }

    public int getAmountOfPeopleInHousehold() {
        return amountOfPeopleInHousehold;
    }

    public void setAmountOfPeopleInHousehold(int amountOfPeopleInHousehold) {
        this.amountOfPeopleInHousehold = amountOfPeopleInHousehold;
    }

    public int getAgentCount() {
        return agentCount;
    }

    public void setAgentCount(int agentCount) {
        this.agentCount = agentCount;
    }

    public int getMinors() {
        return minors;
    }

    public void setMinors(int minors) {
        this.minors = minors;
    }

    public int getLivesInCouple() {
        return livesInCouple;
    }

    public void setLivesInCouple(int livesInCouple) {
        this.livesInCouple = livesInCouple;
    }

    public int getLivesAsSingle() {
        return livesAsSingle;
    }

    public void setLivesAsSingle(int livesAsSingle) {
        this.livesAsSingle = livesAsSingle;
    }

    public int getLivesWithKids() {
        return livesWithKids;
    }

    public void setLivesWithKids(int livesWithKids) {
        this.livesWithKids = livesWithKids;
    }

    public int getLivesWithoutKids() {
        return livesWithoutKids;
    }

    public void setLivesWithoutKids(int livesWithoutKids) {
        this.livesWithoutKids = livesWithoutKids;
    }

    public int getAmountAreas() {
        return amountAreas;
    }

    public void setAmountAreas(int amountAreas) {
        this.amountAreas = amountAreas;
    }

    public SummaryTemplate getArea() {
        return area;
    }

    public void setArea(SummaryTemplate area) {
        this.area = area;
    }

    public int getFriendsSum() {
        return friendsSum;
    }

    public void setFriendsSum(int friendsSum) {
        this.friendsSum = friendsSum;
    }

    public SummaryTemplate getFriends() {
        return friends;
    }

    public void setFriends(SummaryTemplate friends) {
        this.friends = friends;
    }

    public int getRelativesSum() {
        return relativesSum;
    }

    public void setRelativesSum(int relativesSum) {
        this.relativesSum = relativesSum;
    }

    public SummaryTemplate getRelatives() {
        return relatives;
    }

    public void setRelatives(SummaryTemplate relatives) {
        this.relatives = relatives;
    }

    public int getClassesAmount() {
        return classesAmount;
    }

    public void setClassesAmount(int classesAmount) {
        this.classesAmount = classesAmount;
    }

    public int getKidsInClasses() {
        return kidsInClasses;
    }

    public void setKidsInClasses(int kidsInClasses) {
        this.kidsInClasses = kidsInClasses;
    }

    public SummaryTemplate getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SummaryTemplate schoolClass) {
        this.schoolClass = schoolClass;
    }

    public int getCloseColleaguesGroupsAmount() {
        return closeColleaguesGroupsAmount;
    }

    public void setCloseColleaguesGroupsAmount(int closeColleaguesGroupsAmount) {
        this.closeColleaguesGroupsAmount = closeColleaguesGroupsAmount;
    }

    public SummaryTemplate getCloseColleagues() {
        return closeColleagues;
    }

    public void setCloseColleagues(SummaryTemplate closeColleagues) {
        this.closeColleagues = closeColleagues;
    }

    public SummaryTemplate getHousehold() {
        return household;
    }

    public void setHousehold(SummaryTemplate household) {
        this.household = household;
    }

    public int getWorkingPop() {
        return workingPop;
    }

    public void setWorkingPop(int workingPop) {
        this.workingPop = workingPop;
    }

    public int getWorksitesAmount() {
        return worksitesAmount;
    }

    public void setWorksitesAmount(int worksitesAmount) {
        this.worksitesAmount = worksitesAmount;
    }

    public SummaryTemplate getWorksites() {
        return worksites;
    }

    public void setWorksites(SummaryTemplate worksites) {
        this.worksites = worksites;
    }

    public void setAdults(int adults) {this.adults = adults;}

    public int getAdults() {return adults;}
}
