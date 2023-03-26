package ferdi.networkbuilder.model.agents;

import ferdi.networkbuilder.model.contacts.FriendList;
import ferdi.networkbuilder.model.contacts.HouseholdList;
import ferdi.networkbuilder.model.contacts.RelativesList;
import ferdi.networkbuilder.model.groups.SchoolClass;
import ferdi.networkbuilder.model.groups.Worksite;
import ferdi.networkbuilder.model.groups.WorksiteCloseColleagueGroup;

public abstract class Agent {

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


    private boolean works = false;
    private boolean student = false;


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

    public Agent(int id, short age, boolean couple, boolean kids, int area) {
        this.id = id;
        this.age = age;
        this.couple = couple;
        this.kids = kids;
        this.area = area;
        friendList = new FriendList();
        householdList = new HouseholdList();
        relativesList = new RelativesList();
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

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id+
                ", age=" + age +
                //", couple=" + couple +
                //", kids=" + kids +
                ", area=" + area +
                //", friends=" + friendList +
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
        StringBuilder s = new StringBuilder(id + ", age: " + age + ", area: " + area + ", kids " + kids + ", couple: " + couple + ": ----- |");
        for (Agent friend : householdList){
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
}
