package ferdi.networkbuilder.model.agents;

public class Health {
    private HealthStatus healthStatus;

    public Health(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }
}
