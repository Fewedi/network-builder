package ferdi.networkbuilder.metadata;

public record AgentCreationData(int age, boolean kids, boolean couple, long area, int amount) {

    @Override
    public String toString() {
        return "AgentCreationFile{" +
                "Age=" + age +
                ", kids=" + kids +
                ", couple=" + couple +
                ", area=" + area +
                ", amount=" + amount +
                '}';
    }
}
