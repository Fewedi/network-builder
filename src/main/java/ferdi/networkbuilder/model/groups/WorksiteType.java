package ferdi.networkbuilder.model.groups;

public class WorksiteType {

    final int minSize;
    final int maxSize;
    final double relativeAmount;

    public WorksiteType(int minSize, int maxSize, double relativeAmount) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.relativeAmount = relativeAmount;
    }

    public int getMinSize() {
        return minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public double getRelativeAmount() {
        return relativeAmount;
    }

    @Override
    public String toString() {
        return "WorksiteType --- " +
                "minSize=" + minSize +
                ", maxSize=" + maxSize +
                ", relativeAmount=" + relativeAmount + "|";
    }
}
