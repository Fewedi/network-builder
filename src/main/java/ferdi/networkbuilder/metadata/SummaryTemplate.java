package ferdi.networkbuilder.metadata;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record SummaryTemplate(double average, int min, int max) {
    @Override
    public String toString() {
        return "average:" + round(average,2) +", minimum:" + min +", maximum:" + max;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
