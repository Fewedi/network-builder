package ferdi.networkbuilder.config;

import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Configuration
@ConfigurationProperties(prefix = "default")
public class MetaConfig {
    private int maximalPopulation;
    private String pathDataHousehold;
    private String pathDataAge;
    private String pathDataWorksites;
    private String fileNameHousehold;
    private String fileNameAge;
    private String fileNameWorksites;

    private String pathOutput;
    private boolean printNetworkData;
    private boolean createFileNetworkData;


    private int days;
    private double initialAmountRecovered;
    private double initialAmountInfected;


    private boolean friendshipsSkewedByAge;
    private int friendshipsMinAge;
    private int friendshipsSkewedByAgeAgeGroup;
    private int friendshipsBarabasiAlbertGraphM;

    private int livesWithParentsMaxAge;

    private int maxForHighestWorksiteSizeIsNTimesOfMin;
    private final Random random;
    private int seed;

    private int workingAge;

    private int classSize;

    private int minAgeForSchool;

    private double customerFacingRatio;

    private boolean classesOfDifferentAgesIfNecessary;

    private int closeColleaguesMin;
    private int closeColleaguesAverage;
    private int relativesMin;
    private int relativesAv;

    private int workingAgeMax;


    private String asymptomaticDist;
    private List<Integer> asymptomaticNormalDurationAges;
    private List<Integer> asymptomaticNormalDurationMean;
    private double asymptomaticNormalSDOfMean;
    private double asymptomaticGammaK;
    private double asymptomaticGammaGamma;



    private String mildDist;
    private List<Integer> mildNormalDurationAges;
    private List<Integer> mildNormalDurationMean;
    private double mildNormalSDOfMean;
    private double mildGammaK;
    private double mildGammaGamma;


    private String incubationDist;
    private List<Integer> incubationNormalDurationAges;
    private List<Integer> incubationNormalDurationMean;
    private double incubationNormalSDOfMean;
    private double incubationGammaK;
    private double incubationGammaGamma;


    private String severeDist;
    private List<Integer> severeNormalDurationAges;
    private List<Integer> severeNormalDurationMean;
    private double severeNormalSDOfMean;
    private double severeGammaK;
    private double severeGammaGamma;


    private String severehosDist;
    private List<Integer> severehosNormalDurationAges;
    private List<Integer> severehosNormalDurationMean;
    private double severehosNormalSDOfMean;
    private double severehosGammaK;
    private double severehosGammaGamma;








    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getInitialAmountRecovered() {
        return initialAmountRecovered;
    }

    public void setInitialAmountRecovered(double initialAmountRecovered) {
        this.initialAmountRecovered = initialAmountRecovered;
    }

    public double getInitialAmountInfected() {
        return initialAmountInfected;
    }

    public void setInitialAmountInfected(double initialAmountInfected) {
        this.initialAmountInfected = initialAmountInfected;
    }

    public int getWorkingAgeMax() {
        return workingAgeMax;
    }

    public void setWorkingAgeMax(int workingAgeMax) {
        this.workingAgeMax = workingAgeMax;
    }

    public int getRelativesMin() {
        return relativesMin;
    }

    public void setRelativesMin(int relativesMin) {
        this.relativesMin = relativesMin;
    }

    public int getRelativesAv() {
        return relativesAv;
    }

    public void setRelativesAv(int relativesAv) {
        this.relativesAv = relativesAv;
    }

    public int getCloseColleaguesMin() {
        return closeColleaguesMin;
    }

    public void setCloseColleaguesMin(int closeColleaguesMin) {
        this.closeColleaguesMin = closeColleaguesMin;
    }

    public int getCloseColleaguesAverage() {
        return closeColleaguesAverage;
    }

    public void setCloseColleaguesAverage(int closeColleaguesAverage) {
        this.closeColleaguesAverage = closeColleaguesAverage;
    }

    public boolean isClassesOfDifferentAgesIfNecessary() {
        return classesOfDifferentAgesIfNecessary;
    }

    public void setClassesOfDifferentAgesIfNecessary(boolean classesOfDifferentAgesIfNecessary) {
        this.classesOfDifferentAgesIfNecessary = classesOfDifferentAgesIfNecessary;
    }

    public int getClassSize() {
        return classSize;
    }

    public void setClassSize(int classSize) {
        this.classSize = classSize;
    }

    public int getMinAgeForSchool() {
        return minAgeForSchool;
    }

    public void setMinAgeForSchool(int minAgeForSchool) {
        this.minAgeForSchool = minAgeForSchool;
    }

    public int getWorkingAge() {
        return workingAge;
    }

    public void setWorkingAge(int workingAge) {
        this.workingAge = workingAge;
    }

    public boolean isFriendshipsSkewedByAge() {
        return friendshipsSkewedByAge;
    }

    public void setFriendshipsSkewedByAge(boolean friendshipsSkewedByAge) {
        this.friendshipsSkewedByAge = friendshipsSkewedByAge;
    }

    public Random getRandom() {
        return random;
    }

    public void setSeed(int seed) {
        this.seed = seed;
        random.setSeed(seed);
    }

    public int getFriendshipsBarabasiAlbertGraphM() {
        return friendshipsBarabasiAlbertGraphM;
    }

    public void setFriendshipsBarabasiAlbertGraphM(int friendshipsBarabasiAlbertGraphM) {
        this.friendshipsBarabasiAlbertGraphM = friendshipsBarabasiAlbertGraphM;
    }

    public int getFriendshipsMinAge() {
        return friendshipsMinAge;
    }

    public void setFriendshipsMinAge(int friendshipsMinAge) {
        this.friendshipsMinAge = friendshipsMinAge;
    }


    public boolean isDefaultFriendshipsSkewedByAge() {
        return friendshipsSkewedByAge;
    }

    public void setDefaultFriendshipsSkewedByAge(boolean friendshipsSkewedByAge) {
        this.friendshipsSkewedByAge = friendshipsSkewedByAge;
    }

    public int getFriendshipsSkewedByAgeAgeGroup() {
        return friendshipsSkewedByAgeAgeGroup;
    }

    public void setFriendshipsSkewedByAgeAgeGroup(int friendshipsSkewedByAgeAgeGroup) {
        this.friendshipsSkewedByAgeAgeGroup = friendshipsSkewedByAgeAgeGroup;
    }

    public int getMaxAgents() {
        return maximalPopulation;
    }

    public void setMaximalPopulation(int maxAgents) {
        this.maximalPopulation = maxAgents;
    }

    public String getPathDataHousehold() {
        return pathDataHousehold;
    }

    public void setPathDataHousehold(String pathDataHousehold) {
        this.pathDataHousehold = pathDataHousehold;
    }

    public String getPathDataAge() {
        return pathDataAge;
    }

    public void setPathDataAge(String pathDataAge) {
        this.pathDataAge = pathDataAge;
    }

    public String getFileNameHousehold() {
        return fileNameHousehold;
    }

    public void setFileNameHousehold(String fileNameHousehold) {
        this.fileNameHousehold = fileNameHousehold;
    }

    public String getFileNameAge() {
        return fileNameAge;
    }

    public void setFileNameAge(String fileNameAge) {
        this.fileNameAge = fileNameAge;
    }

    private final HashMap<Long,GeographicHouseholdMetaData> metaDataLocal;
    private final GeographicHouseholdMetaData metaDataGlobal;

    public MetaConfig() {
        this.metaDataLocal = new HashMap<>();
        this.metaDataGlobal = new GeographicHouseholdMetaData();
        this.random = new Random();
    }

    public HashMap<Long, GeographicHouseholdMetaData> getMetaDataLocal() {
        return metaDataLocal;
    }

    public GeographicHouseholdMetaData getMetaDataGlobal() {
        return metaDataGlobal;
    }

    public int getLivesWithParentsMaxAge() {
        return livesWithParentsMaxAge;
    }

    public void setLivesWithParentsMaxAge(int livesWithParentsMaxAge) {
        this.livesWithParentsMaxAge = livesWithParentsMaxAge;
    }

    public String getPathDataWorksites() {
        return pathDataWorksites;
    }

    public void setPathDataWorksites(String pathDataWorksites) {
        this.pathDataWorksites = pathDataWorksites;
    }

    public String getFileNameWorksites() {
        return fileNameWorksites;
    }

    public void setFileNameWorksites(String fileNameWorksites) {
        this.fileNameWorksites = fileNameWorksites;
    }

    public int getMaxForHighestWorksiteSizeIsNTimesOfMin() {
        return maxForHighestWorksiteSizeIsNTimesOfMin;
    }

    public void setMaxForHighestWorksiteSizeIsNTimesOfMin(int maxForHighestWorksiteSizeIsNTimesOfMin) {
        this.maxForHighestWorksiteSizeIsNTimesOfMin = maxForHighestWorksiteSizeIsNTimesOfMin;
    }

    public double getCustomerFacingRatio() {
        return customerFacingRatio;
    }

    public void setCustomerFacingRatio(double customerFacingRatio) {
        this.customerFacingRatio = customerFacingRatio;
    }

    public String getPathOutput() {
        return pathOutput;
    }

    public void setPathOutput(String pathOutput) {
        this.pathOutput = pathOutput;
    }

    public boolean isPrintNetworkData() {
        return printNetworkData;
    }

    public void setPrintNetworkData(boolean printNetworkData) {
        this.printNetworkData = printNetworkData;
    }

    public boolean isCreateFileNetworkData() {
        return createFileNetworkData;
    }

    public void setCreateFileNetworkData(boolean createFileNetworkData) {
        this.createFileNetworkData = createFileNetworkData;
    }

    public String getAsymptomaticDist() {
        return asymptomaticDist;
    }

    public void setAsymptomaticDist(String asymptomaticDist) {
        this.asymptomaticDist = asymptomaticDist;
    }

    public List<Integer> getAsymptomaticNormalDurationAges() {
        return asymptomaticNormalDurationAges;
    }

    public void setAsymptomaticNormalDurationAges(List<Integer> asymptomaticNormalDurationAges) {
        this.asymptomaticNormalDurationAges = asymptomaticNormalDurationAges;
    }

    public List<Integer> getAsymptomaticNormalDurationMean() {
        return asymptomaticNormalDurationMean;
    }

    public void setAsymptomaticNormalDurationMean(List<Integer> asymptomaticNormalDurationMean) {
        this.asymptomaticNormalDurationMean = asymptomaticNormalDurationMean;
    }

    public double getAsymptomaticNormalSDOfMean() {
        return asymptomaticNormalSDOfMean;
    }

    public void setAsymptomaticNormalSDOfMean(double asymptomaticNormalSDOfMean) {
        this.asymptomaticNormalSDOfMean = asymptomaticNormalSDOfMean;
    }

    public double getAsymptomaticGammaK() {
        return asymptomaticGammaK;
    }

    public void setAsymptomaticGammaK(double asymptomaticGammaK) {
        this.asymptomaticGammaK = asymptomaticGammaK;
    }

    public double getAsymptomaticGammaGamma() {
        return asymptomaticGammaGamma;
    }

    public void setAsymptomaticGammaGamma(double asymptomaticGammaGamma) {
        this.asymptomaticGammaGamma = asymptomaticGammaGamma;
    }

    public String getMildDist() {
        return mildDist;
    }

    public void setMildDist(String mildDist) {
        this.mildDist = mildDist;
    }

    public List<Integer> getMildNormalDurationAges() {
        return mildNormalDurationAges;
    }

    public void setMildNormalDurationAges(List<Integer> mildNormalDurationAges) {
        this.mildNormalDurationAges = mildNormalDurationAges;
    }

    public List<Integer> getMildNormalDurationMean() {
        return mildNormalDurationMean;
    }

    public void setMildNormalDurationMean(List<Integer> mildNormalDurationMean) {
        this.mildNormalDurationMean = mildNormalDurationMean;
    }

    public double getMildNormalSDOfMean() {
        return mildNormalSDOfMean;
    }

    public void setMildNormalSDOfMean(double mildNormalSDOfMean) {
        this.mildNormalSDOfMean = mildNormalSDOfMean;
    }

    public double getMildGammaK() {
        return mildGammaK;
    }

    public void setMildGammaK(double mildGammaK) {
        this.mildGammaK = mildGammaK;
    }

    public double getMildGammaGamma() {
        return mildGammaGamma;
    }

    public void setMildGammaGamma(double mildGammaGamma) {
        this.mildGammaGamma = mildGammaGamma;
    }

    public String getIncubationDist() {
        return incubationDist;
    }

    public void setIncubationDist(String incubationDist) {
        this.incubationDist = incubationDist;
    }

    public List<Integer> getIncubationNormalDurationAges() {
        return incubationNormalDurationAges;
    }

    public void setIncubationNormalDurationAges(List<Integer> incubationNormalDurationAges) {
        this.incubationNormalDurationAges = incubationNormalDurationAges;
    }

    public List<Integer> getIncubationNormalDurationMean() {
        return incubationNormalDurationMean;
    }

    public void setIncubationNormalDurationMean(List<Integer> incubationNormalDurationMean) {
        this.incubationNormalDurationMean = incubationNormalDurationMean;
    }

    public double getIncubationNormalSDOfMean() {
        return incubationNormalSDOfMean;
    }

    public void setIncubationNormalSDOfMean(double incubationNormalSDOfMean) {
        this.incubationNormalSDOfMean = incubationNormalSDOfMean;
    }

    public double getIncubationGammaK() {
        return incubationGammaK;
    }

    public void setIncubationGammaK(double incubationGammaK) {
        this.incubationGammaK = incubationGammaK;
    }

    public double getIncubationGammaGamma() {
        return incubationGammaGamma;
    }

    public void setIncubationGammaGamma(double incubationGammaGamma) {
        this.incubationGammaGamma = incubationGammaGamma;
    }

    public String getSevereDist() {
        return severeDist;
    }

    public void setSevereDist(String severeDist) {
        this.severeDist = severeDist;
    }

    public List<Integer> getSevereNormalDurationAges() {
        return severeNormalDurationAges;
    }

    public void setSevereNormalDurationAges(List<Integer> severeNormalDurationAges) {
        this.severeNormalDurationAges = severeNormalDurationAges;
    }

    public List<Integer> getSevereNormalDurationMean() {
        return severeNormalDurationMean;
    }

    public void setSevereNormalDurationMean(List<Integer> severeNormalDurationMean) {
        this.severeNormalDurationMean = severeNormalDurationMean;
    }

    public double getSevereNormalSDOfMean() {
        return severeNormalSDOfMean;
    }

    public void setSevereNormalSDOfMean(double severeNormalSDOfMean) {
        this.severeNormalSDOfMean = severeNormalSDOfMean;
    }

    public double getSevereGammaK() {
        return severeGammaK;
    }

    public void setSevereGammaK(double severeGammaK) {
        this.severeGammaK = severeGammaK;
    }

    public double getSevereGammaGamma() {
        return severeGammaGamma;
    }

    public void setSevereGammaGamma(double severeGammaGamma) {
        this.severeGammaGamma = severeGammaGamma;
    }

    public String getSeverehosDist() {
        return severehosDist;
    }

    public void setSeverehosDist(String severehosDist) {
        this.severehosDist = severehosDist;
    }

    public List<Integer> getSeverehosNormalDurationAges() {
        return severehosNormalDurationAges;
    }

    public void setSeverehosNormalDurationAges(List<Integer> severehosNormalDurationAges) {
        this.severehosNormalDurationAges = severehosNormalDurationAges;
    }

    public List<Integer> getSeverehosNormalDurationMean() {
        return severehosNormalDurationMean;
    }

    public void setSeverehosNormalDurationMean(List<Integer> severehosNormalDurationMean) {
        this.severehosNormalDurationMean = severehosNormalDurationMean;
    }

    public double getSeverehosNormalSDOfMean() {
        return severehosNormalSDOfMean;
    }

    public void setSeverehosNormalSDOfMean(double severehosNormalSDOfMean) {
        this.severehosNormalSDOfMean = severehosNormalSDOfMean;
    }

    public double getSeverehosGammaK() {
        return severehosGammaK;
    }

    public void setSeverehosGammaK(double severehosGammaK) {
        this.severehosGammaK = severehosGammaK;
    }

    public double getSeverehosGammaGamma() {
        return severehosGammaGamma;
    }

    public void setSeverehosGammaGamma(double severehosGammaGamma) {
        this.severehosGammaGamma = severehosGammaGamma;
    }
}
