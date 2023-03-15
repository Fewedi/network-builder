package ferdi.networkbuilder.metadata;

public class AgentCreationData {
    final int Age;
    final boolean kids;
    final boolean couple;
    final long area;
    final int amount;

    public AgentCreationData(int age, boolean kids, boolean couple, long area, int amount) {
        Age = age;
        this.kids = kids;
        this.couple = couple;
        this.area = area;
        this.amount = amount;
    }
    public int getAge() {
        return Age;
    }

    public boolean isKids() {
        return kids;
    }

    public boolean isCouple() {
        return couple;
    }

    public long getArea() {
        return area;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "AgentCreationFile{" +
                "Age=" + Age +
                ", kids=" + kids +
                ", couple=" + couple +
                ", area=" + area +
                ", amount=" + amount +
                '}';
    }
}
