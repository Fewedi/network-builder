package ferdi.networkbuilder.model.agents;

import ferdi.networkbuilder.config.MetaConfig;
import org.apache.commons.math3.distribution.GammaDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Health {
    private HealthStatus healthStatus;
    private int daysInStatus;
    private boolean infectious;

    public boolean isInfectedCurrently() {
        switch (healthStatus) {
            case ASYMPTOMATIC, MILD, SEVERE, SEVERE_HOS, INCUBATION:{
                return true;
            } default :
                return false;
        }
    }

    public Health(HealthStatus healthStatus, MetaConfig config, int age) {
        this.healthStatus = healthStatus;
        generateDays(healthStatus,config,age);
    }

    public boolean doIHaveSymptoms(){
        switch (healthStatus) {
            case MILD, SEVERE, SEVERE_HOS:{
                return true;
            } default :
                return false;
        }
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void nextDay(MetaConfig config, int age, boolean female){
        if(daysInStatus != 0){
            daysInStatus--;
            if(healthStatus.equals(HealthStatus.INCUBATION)){
                getInfectiousEventually(config);
            }
        }else {
            switch (healthStatus){
                case ASYMPTOMATIC, MILD -> {
                    healthStatus = HealthStatus.RECOVERED;
                }
                case INCUBATION -> {
                    healthStatus = getStatusAfterIncubation(config, age);

                }
                case SEVERE_HOS -> {
                    healthStatus = getStatusAfterSevereHos(config, age,female);
                }
                case SEVERE -> {
                    healthStatus = HealthStatus.SEVERE_HOS;
                }
            }
            generateDays(healthStatus,config,age);
        }
    }

    private HealthStatus getStatusAfterSevereHos(MetaConfig config, int age, boolean female) {
        double gamma = getGamma(config,age,female);
        List<HealthStatus> poss = new ArrayList<>();
        poss.add(HealthStatus.DEAD);
        poss.add(HealthStatus.RECOVERED);
        List<Double> prob = new ArrayList<>();
        prob.add(gamma);
        prob.add(1.0 - gamma);
        return getByProb(poss,prob,config.getRandom());
    }

    private HealthStatus getStatusAfterIncubation(MetaConfig config, int age) {
        double alpha = getAlpha(config,age);
        double delta = getDelta(config,age);
        List<HealthStatus> poss = new ArrayList<>();
        poss.add(HealthStatus.SEVERE);
        poss.add(HealthStatus.MILD);
        poss.add(HealthStatus.ASYMPTOMATIC);

        List<Double> prob = new ArrayList<>();
        prob.add(alpha * delta);
        prob.add(alpha * (1.0 - delta));
        prob.add(1.0 - alpha);
        return getByProb(poss,prob,config.getRandom());
    }

    private HealthStatus getByProb(List<HealthStatus> poss, List<Double> prob, Random rng) {
        double random = rng.nextDouble();
        double cumulativeProbability = 0.0;
        int index = 0;
        for (double probability : prob) {
            cumulativeProbability += probability;
            if (random <= cumulativeProbability) {
                return poss.get(index);
            }
            index++;
        }
        return null;
    }

    private double getAlpha(MetaConfig config, int age){
        List<Double> values = config.getAlphaValue();
        List<Integer> ages = config.getAlphaAges();
        return getDoubleByAge(values,ages,age);
    }

    private double getDelta(MetaConfig config, int age){
        List<Double> values = config.getDeltaValue();
        List<Integer> ages = config.getDeltaAges();
        return getDoubleByAge(values,ages,age);
    }


    private double getGamma(MetaConfig config, int age, boolean female){
        List<Double> values = config.getGammaValue();
        List<Integer> ages = config.getGammaAges();
        double male = getDoubleByAge(values,ages,age);
        if(female){
            return male * config.getGammaFemaleDif();
        }else {
            return male;
        }
    }

    private double getDoubleByAge(List<Double> values, List<Integer> ages,int age){
        int j = 0;

        int b = ages.size();
        int a = values.size();
        for(int i = 0; i < Math.min(a,b); i++){
            if(age > ages.get(i)){
                j = i;
            }
        }
        return values.get(j);
    }


    private void generateDays(HealthStatus healthStatus, MetaConfig config, int age) {
        RandomGenerator rng = config.getRandomGenerator();
        switch (healthStatus){
            case SUSCEPTIBLE, RECOVERED, DEAD -> {
                daysInStatus = Integer.MAX_VALUE;
                infectious = false;
            }
            case INCUBATION -> {
                if(config.getIncubationDist().equals("gamma")){
                    useGamma(config.getIncubationGammaK(),config.getIncubationGammaGamma(),rng);
                }else if(config.getIncubationDist().equals("normal")){
                    useNormal(config.getIncubationNormalDurationAges(),config.getIncubationNormalDurationMean(),config.getIncubationNormalSDOfMean(),age,rng);
                }
                infectious = false;
                if(config.isInfectiousWhileIncubation()){
                    getInfectiousEventually(config);
                }
            }
            case ASYMPTOMATIC -> {
                if(config.getAsymptomaticDist().equals("gamma")){
                    useGamma(config.getAsymptomaticGammaK(),config.getAsymptomaticGammaGamma(),rng);
                }else if(config.getAsymptomaticDist().equals("normal")){
                    useNormal(config.getAsymptomaticNormalDurationAges(),config.getAsymptomaticNormalDurationMean(),config.getAsymptomaticNormalSDOfMean(),age,rng);
                }
                infectious = true;
            }
            case MILD -> {
                if(config.getMildDist().equals("gamma")){
                    useGamma(config.getMildGammaK(),config.getMildGammaGamma(),rng);
                }else if(config.getMildDist().equals("normal")){
                    useNormal(config.getMildNormalDurationAges(),config.getMildNormalDurationMean(),config.getMildNormalSDOfMean(),age,rng);
                }
                infectious = true;
            }
            case SEVERE -> {
                if(config.getSevereDist().equals("gamma")){
                    useGamma(config.getSevereGammaK(),config.getSevereGammaGamma(),rng);
                }else if(config.getSevereDist().equals("normal")){
                    useNormal(config.getSevereNormalDurationAges(),config.getSevereNormalDurationMean(),config.getSevereNormalSDOfMean(),age,rng);
                }
                infectious = true;
            }
            case SEVERE_HOS -> {
                if(config.getSeverehosDist().equals("gamma")){
                    useGamma(config.getSeverehosGammaK(),config.getSeverehosGammaGamma(),rng);
                }else if(config.getSeverehosDist().equals("normal")){
                    useNormal(config.getSeverehosNormalDurationAges(),config.getSeverehosNormalDurationMean(),config.getSeverehosNormalSDOfMean(),age,rng);
                }
                infectious = true;
            }
        }
    }

    private void getInfectiousEventually(MetaConfig config) {
        if(!infectious && daysInStatus <= config.getInfectiousWhileIncubationDays() && config.getInfectiousWhileIncubationProb() < config.getRandom().nextDouble()){
            infectious = true;
        }
    }

    private void useNormal(List<Integer> normalDurationAges, List<Integer> normalDurationMean, double normalSDOfMean, int age, RandomGenerator rng){
        int mean = getMeanViaAge(normalDurationAges,normalDurationMean,age);
        NormalDistribution normalDistribution = new NormalDistribution(rng, mean,normalSDOfMean * (double) mean);
        daysInStatus = (int) Math.round(normalDistribution.sample());
    }

    private int getMeanViaAge(List<Integer> normalDurationAges, List<Integer> normalDurationMean, int age) {
        int j = 0;
        for(int i = 0; i < Math.min(normalDurationAges.size(),normalDurationMean.size()); i++){
            if(age > normalDurationAges.get(i)){
                j = i;
            }
        }
        return normalDurationMean.get(j);
    }


    private void useGamma(double k, double gamma, RandomGenerator rng) {
        GammaDistribution gammaDistribution = new GammaDistribution(rng, k,gamma);
        daysInStatus = (int) Math.round( gammaDistribution.sample());
    }

    public boolean isInfectious() {
        return infectious;
    }

    @Override
    public String toString() {
        return "Health: " +
                "status = " + healthStatus +
                ", days until change = " + daysInStatus;
    }

    public boolean infect(int age, MetaConfig config) {
        //System.out.print(" try to infect ");
        if(healthStatus.equals(HealthStatus.SUSCEPTIBLE)){
            //System.out.println(" infect ");

            healthStatus = HealthStatus.INCUBATION;
            generateDays(healthStatus,config,age);
            return true;
        }
        return false;
    }
}
