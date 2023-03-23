package ferdi.networkbuilder.metadata;

public record SummaryTemplate(double average,int min, int max) {
    @Override
    public String toString() {
        return "ST{" +
                "average=" + average +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
