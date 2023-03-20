package ferdi.networkbuilder.model.agents;

import ferdi.networkbuilder.model.contacts.FriendList;
import ferdi.networkbuilder.model.contacts.HouseholdList;

public abstract class Agent {


    private final int id;
    private final short age;
    private boolean couple;
    private final boolean kids;
    private final int area;

    private boolean female;

    private final FriendList friendList;
    private final HouseholdList householdList;

    public Agent(int id, short age, boolean couple, boolean kids, int area) {
        this.id = id;
        this.age = age;
        this.couple = couple;
        this.kids = kids;
        this.area = area;
        friendList = new FriendList();
        householdList = new HouseholdList();
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
                ", couple=" + couple +
                ", kids=" + kids +
                ", area=" + area +
                ", friends=" + friendList + "}";
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
}
