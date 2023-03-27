package ferdi.networkbuilder.model.agents;

import ferdi.networkbuilder.config.MetaConfig;

import java.util.List;
import java.util.Random;

public class Health {
    private HealthStatus healthStatus;
    private int daysInStatus;

    public Health(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus, MetaConfig config, int age) {

        this.healthStatus = healthStatus;
        generateDays(healthStatus,config,age);
    }

    private void generateDays(HealthStatus healthStatus, MetaConfig config, int age) {
        Random rng = config.getRandom();
        switch (healthStatus){
            case SUSCEPTIBLE, RECOVERED, DEAD -> {
                daysInStatus = Integer.MAX_VALUE;
            }
            case INCUBATION -> {
                if(config.getIncubationDist().equals("gamma")){
                    useGamma(config.getIncubationGammaK(),config.getIncubationGammaGamma(),rng);
                }else if(config.getIncubationDist().equals("normal")){
                    useNormal(config.getIncubationNormalDurationAges(),config.getIncubationNormalDurationMean(),config.getIncubationNormalSDOfMean(),age,rng);
                }
            }
            case ASYMPTOMATIC -> {
                if(config.getAsymptomaticDist().equals("gamma")){
                    useGamma(config.getAsymptomaticGammaK(),config.getAsymptomaticGammaGamma(),rng);
                }else if(config.getAsymptomaticDist().equals("normal")){
                    useNormal(config.getAsymptomaticNormalDurationAges(),config.getAsymptomaticNormalDurationMean(),config.getAsymptomaticNormalSDOfMean(),age,rng);
                }
            }
            case MILD -> {
                if(config.getMildDist().equals("gamma")){
                    useGamma(config.getMildGammaK(),config.getMildGammaGamma(),rng);
                }else if(config.getMildDist().equals("normal")){
                    useNormal(config.getMildNormalDurationAges(),config.getMildNormalDurationMean(),config.getMildNormalSDOfMean(),age,rng);
                }
            }
            case SEVERE -> {
                if(config.getSevereDist().equals("gamma")){
                    useGamma(config.getSevereGammaK(),config.getSevereGammaGamma(),rng);
                }else if(config.getSevereDist().equals("normal")){
                    useNormal(config.getSevereNormalDurationAges(),config.getSevereNormalDurationMean(),config.getSevereNormalSDOfMean(),age,rng);
                }
            }
            case SEVERE_HOS -> {
                if(config.getSeverehosDist().equals("gamma")){
                    useGamma(config.getSeverehosGammaK(),config.getSeverehosGammaGamma(),rng);
                }else if(config.getSeverehosDist().equals("normal")){
                    useNormal(config.getSeverehosNormalDurationAges(),config.getSeverehosNormalDurationMean(),config.getSeverehosNormalSDOfMean(),age,rng);
                }
            }
        }
    }

    private void useNormal(List<Integer> incubationNormalDurationAges, List<Integer> incubationNormalDurationMean, double incubationNormalSDOfMean, int age, Random rng) {
    }



    private void useGamma(double incubationGammaK, double incubationGammaGamma,Random rng) {

    }


}
