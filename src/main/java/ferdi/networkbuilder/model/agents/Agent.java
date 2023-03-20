package ferdi.networkbuilder.model.agents;

import ferdi.networkbuilder.model.collections.FriendList;

public abstract class Agent {


    private final int id;
    private final short age;
    private final boolean couple;
    private final boolean kids;
    private final int area;

    private final FriendList friendList;

    public Agent(int id, short age, boolean couple, boolean kids, int area) {
        this.id = id;
        this.age = age;
        this.couple = couple;
        this.kids = kids;
        this.area = area;
        friendList = new FriendList();
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
        friendList.add(friend);
    }

    public String toStingFriends(){
        StringBuilder s = new StringBuilder(id + ": |");
        for (Agent friend : friendList){
            s.append(friend.getId()).append("|");
        }
        return s.toString();

    }

    public FriendList getFriends() {
        return friendList;
    }
}
