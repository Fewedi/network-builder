package ferdi.networkbuilder.config;

import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Random;


@Configuration
@ConfigurationProperties(prefix = "default")
public class MetaConfig {

    private int runCounter = 0;
    private int runs;
    private boolean test_multiple;
    private boolean toTestCTAAppliance;
    private List<Double> cTAApplienceList;
    private boolean toTestTestCapacity;
    private List<Double> testCapacityList;

    private boolean toTestTestPriority;


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

    private double cTAUsers;
    private int cTAMinAge;

    private int days;
    private double initialAmountRecovered;
    private double initialAmountInfected;

    private double proportionOfPopWithILIPerDay;
    private double proportionOfTestingBecauseILI;

    private boolean friendshipsSkewedByAge;
    private int friendshipsMinAge;
    private int friendshipsSkewedByAgeAgeGroup;
    private int friendshipsBarabasiAlbertGraphM;

    private int livesWithParentsMaxAge;

    private int maxForHighestWorksiteSizeIsNTimesOfMin;
    private final Random random;
    private final RandomGenerator randomGenerator;
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


    private int testRefreshingDays;
    private double testsPerNDays;
    private int daysUntilTestsArrive;

    private boolean infectiousWhileIncubation;
    private int infectiousWhileIncubationDays;
    private double infectiousWhileIncubationProb;

    private List<Integer> alphaAges;
    private List<Double> alphaValue;

    private List<Integer> deltaAges;
    private List<Double> deltaValue;

    private List<Integer> gammaAges;
    private List<Double> gammaValue;
    private double gammaFemaleDif;
    private int isolationTimeIfContact;

    private String asymptomaticDist;
    private List<Integer> asymptomaticNormalDurationAges;
    private List<Integer> asymptomaticNormalDurationMean;
    private double asymptomaticNormalSDOfMean;
    private double asymptomaticGammaK;
    private double asymptomaticGammaGamma;
    private boolean doITestIfIRecovered;
    private boolean doITestIfIAlreadyTestedPositive;



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


    private double isolationProbabilityTest;

    private double isolationProbabilityNoTest;
    private double isolationProbabilityCTAAndRelativesTest;
    private double isolationProbabilityCTAAndRelativesNoTest;
    private double testProbabilityILI;
    private double ILIFracPerWeek;
    private boolean testPrio;


    private int encountersPerWeekRandomYoung;
    private double encountersPerWeekRandomOld;
    private int encountersPerWeekOldAge;
    private int encountersPerWeekSchool;
    private double encountersPerWeekRelations;
    private int encountersPerWeekWork;
    private int encountersPerWeekHousehold;
    private int encountersPerWeekFriends;
    private double encountersInSchoolclass;

    private double baselineTransmissionProp;
    private double transmissionRateHousehold;
    private double transmissionRateRelatives;
    private double transmissionRateFriends;
    private double transmissionRateWork;
    private double transmissionRateWorkCustomersFacing;
    private double transmissionRateRandom;
    private double transmissionRateSchool;

    private double transmissionRandomP;
    private double transmissionWorkCustomersFacingP;

    private double encountersInFriendsMax;

    private double transmissionReductionHouseholdIsolated;


    private boolean spread_via_relatives;
    private boolean spread_via_household;
    private boolean spread_via_school;
    private boolean spread_via_friends;
    private boolean spread_via_random;
    private boolean spread_via_work_group;
    private boolean spread_via_work_not_group;
    private boolean spread_via_work_public_facing;

    private int cTADaysToSave;

    public MetaConfig() {
        this.metaDataLocal = new HashMap<>();
        this.metaDataGlobal = new GeographicHouseholdMetaData();
        this.random = new Random();
        this.randomGenerator = new MersenneTwister();
    }

    public void setSeed(int seed) {
        this.seed = seed;
        random.setSeed(seed);
        randomGenerator.setSeed(seed);
    }


    public int getRuns() {
        return runs;
    }

    public void addRun(){
        runCounter++;
    }

    public int getRunCounter(){
        return runCounter;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public boolean isToTestTestCapacity() {
        return toTestTestCapacity;
    }

    public void setToTestTestCapacity(boolean toTestTestCapacity) {
        this.toTestTestCapacity = toTestTestCapacity;
    }

    public List<Double> getTestCapacityList() {
        return testCapacityList;
    }

    public void setTestCapacityList(List<Double> testCapacityList) {
        this.testCapacityList = testCapacityList;
    }

    public boolean isToTestTestPriority() {
        return toTestTestPriority;
    }

    public void setToTestTestPriority(boolean toTestTestPriority) {
        this.toTestTestPriority = toTestTestPriority;
    }

    public boolean isTest_multiple() {
        return test_multiple;
    }

    public void setTest_multiple(boolean test_multiple) {
        this.test_multiple = test_multiple;
    }

    public boolean isToTestCTAAppliance() {
        return toTestCTAAppliance;
    }

    public void setToTestCTAAppliance(boolean toTestCTAAppliance) {
        this.toTestCTAAppliance = toTestCTAAppliance;
    }

    public List<Double> getcTAApplienceList() {
        return cTAApplienceList;
    }

    public void setcTAApplienceList(List<Double> cTAApplienceList) {
        this.cTAApplienceList = cTAApplienceList;
    }

    public double getProportionOfPopWithILIPerDay() {
        return proportionOfPopWithILIPerDay;
    }

    public void setProportionOfPopWithILIPerDay(double proportionOfPopWithILIPerDay) {
        this.proportionOfPopWithILIPerDay = proportionOfPopWithILIPerDay;
    }

    public double getProportionOfTestingBecauseILI() {
        return proportionOfTestingBecauseILI;
    }

    public void setProportionOfTestingBecauseILI(double proportionOfTestingBecauseILI) {
        this.proportionOfTestingBecauseILI = proportionOfTestingBecauseILI;
    }

    public int getcTADaysToSave() {
        return cTADaysToSave;
    }

    public void setcTADaysToSave(int cTADaysToSave) {
        this.cTADaysToSave = cTADaysToSave;
    }

    public boolean isSpread_via_relatives() {
        return spread_via_relatives;
    }

    public void setSpread_via_relatives(boolean spread_via_relatives) {
        this.spread_via_relatives = spread_via_relatives;
    }

    public boolean isSpread_via_household() {
        return spread_via_household;
    }

    public void setSpread_via_household(boolean spread_via_household) {
        this.spread_via_household = spread_via_household;
    }

    public boolean isSpread_via_school() {
        return spread_via_school;
    }

    public void setSpread_via_school(boolean spread_via_school) {
        this.spread_via_school = spread_via_school;
    }

    public boolean isSpread_via_friends() {
        return spread_via_friends;
    }

    public void setSpread_via_friends(boolean spread_via_friends) {
        this.spread_via_friends = spread_via_friends;
    }

    public boolean isSpread_via_random() {
        return spread_via_random;
    }

    public void setSpread_via_random(boolean spread_via_random) {
        this.spread_via_random = spread_via_random;
    }

    public boolean isSpread_via_work_group() {
        return spread_via_work_group;
    }

    public void setSpread_via_work_group(boolean spread_via_work_group) {
        this.spread_via_work_group = spread_via_work_group;
    }

    public boolean isSpread_via_work_not_group() {
        return spread_via_work_not_group;
    }

    public void setSpread_via_work_not_group(boolean spread_via_work_not_group) {
        this.spread_via_work_not_group = spread_via_work_not_group;
    }

    public boolean isSpread_via_work_public_facing() {
        return spread_via_work_public_facing;
    }

    public void setSpread_via_work_public_facing(boolean spread_via_work_public_facing) {
        this.spread_via_work_public_facing = spread_via_work_public_facing;
    }

    public double getEncountersInFriendsMax() {
        return encountersInFriendsMax;
    }

    public void setEncountersInFriendsMax(double encountersInFriendsMax) {
        this.encountersInFriendsMax = encountersInFriendsMax;
    }

    public boolean isTestPrio() {
        return testPrio;
    }

    public void setTestPrio(boolean testPrio) {
        this.testPrio = testPrio;
    }

    public int getIsolationTimeIfContact() {
        return isolationTimeIfContact;
    }

    public void setIsolationTimeIfContact(int isolationTimeIfContact) {
        this.isolationTimeIfContact = isolationTimeIfContact;
    }

    public boolean isDoITestIfIAlreadyTestedPositive() {
        return doITestIfIAlreadyTestedPositive;
    }

    public void setDoITestIfIAlreadyTestedPositive(boolean doITestIfIAlreadyTestedPositive) {
        this.doITestIfIAlreadyTestedPositive = doITestIfIAlreadyTestedPositive;
    }

    public boolean isDoITestIfIRecovered() {
        return doITestIfIRecovered;
    }

    public void setDoITestIfIRecovered(boolean doITestIfIRecovered) {
        this.doITestIfIRecovered = doITestIfIRecovered;
    }

    public int getDaysUntilTestsArrive() {
        return daysUntilTestsArrive;
    }

    public void setDaysUntilTestsArrive(int daysUntilTestsArrive) {
        this.daysUntilTestsArrive = daysUntilTestsArrive;
    }

    public double getIsolationProbabilityTest() {
        return isolationProbabilityTest;
    }

    public void setIsolationProbabilityTest(double isolationProbabilityTest) {
        this.isolationProbabilityTest = isolationProbabilityTest;
    }

    public double getIsolationProbabilityNoTest() {
        return isolationProbabilityNoTest;
    }

    public void setIsolationProbabilityNoTest(double isolationProbabilityNoTest) {
        this.isolationProbabilityNoTest = isolationProbabilityNoTest;
    }

    public double getIsolationProbabilityCTAAndRelativesTest() {
        return isolationProbabilityCTAAndRelativesTest;
    }

    public void setIsolationProbabilityCTAAndRelativesTest(double isolationProbabilityCTAAndRelativesTest) {
        this.isolationProbabilityCTAAndRelativesTest = isolationProbabilityCTAAndRelativesTest;
    }

    public double getIsolationProbabilityCTAAndRelativesNoTest() {
        return isolationProbabilityCTAAndRelativesNoTest;
    }

    public void setIsolationProbabilityCTAAndRelativesNoTest(double isolationProbabilityCTAAndRelativesNoTest) {
        this.isolationProbabilityCTAAndRelativesNoTest = isolationProbabilityCTAAndRelativesNoTest;
    }

    public double getTestProbabilityILI() {
        return testProbabilityILI;
    }

    public void setTestProbabilityILI(double testProbabilityILI) {
        this.testProbabilityILI = testProbabilityILI;
    }

    public double getILIFracPerWeek() {
        return ILIFracPerWeek;
    }

    public void setILIFracPerWeek(double ILIFracPerWeek) {
        this.ILIFracPerWeek = ILIFracPerWeek;
    }

    public int getTestRefreshingDays() {
        return testRefreshingDays;
    }

    public void setTestRefreshingDays(int testRefreshingDays) {
        this.testRefreshingDays = testRefreshingDays;
    }

    public double getTestsPerNDays() {
        return testsPerNDays;
    }

    public void setTestsPerNDays(double testsPerNDays) {
        this.testsPerNDays = testsPerNDays;
    }

    public double getcTAUsers() {
        return cTAUsers;
    }

    public void setcTAUsers(double cTAUsers) {
        this.cTAUsers = cTAUsers;
    }

    public int getcTAMinAge() {
        return cTAMinAge;
    }

    public void setcTAMinAge(int cTAMinAge) {
        this.cTAMinAge = cTAMinAge;
    }

    public boolean isInfectiousWhileIncubation() {
        return infectiousWhileIncubation;
    }

    public void setInfectiousWhileIncubation(boolean infectiousWhileIncubation) {
        this.infectiousWhileIncubation = infectiousWhileIncubation;
    }

    public int getInfectiousWhileIncubationDays() {
        return infectiousWhileIncubationDays;
    }

    public void setInfectiousWhileIncubationDays(int infectiousWhileIncubationDays) {
        this.infectiousWhileIncubationDays = infectiousWhileIncubationDays;
    }

    public double getInfectiousWhileIncubationProb() {
        return infectiousWhileIncubationProb;
    }

    public void setInfectiousWhileIncubationProb(double infectiousWhileIncubationProb) {
        this.infectiousWhileIncubationProb = infectiousWhileIncubationProb;
    }

    public List<Integer> getAlphaAges() {
        return alphaAges;
    }

    public void setAlphaAges(List<Integer> alphaAges) {
        this.alphaAges = alphaAges;
    }

    public List<Double> getAlphaValue() {
        return alphaValue;
    }

    public void setAlphaValue(List<Double> alphaValue) {
        this.alphaValue = alphaValue;
    }

    public List<Integer> getDeltaAges() {
        return deltaAges;
    }

    public void setDeltaAges(List<Integer> deltaAges) {
        this.deltaAges = deltaAges;
    }

    public List<Double> getDeltaValue() {
        return deltaValue;
    }

    public void setDeltaValue(List<Double> deltaValue) {
        this.deltaValue = deltaValue;
    }

    public List<Integer> getGammaAges() {
        return gammaAges;
    }

    public void setGammaAges(List<Integer> gammaAges) {
        this.gammaAges = gammaAges;
    }

    public List<Double> getGammaValue() {
        return gammaValue;
    }

    public void setGammaValue(List<Double> gammaValue) {
        this.gammaValue = gammaValue;
    }

    public double getGammaFemaleDif() {
        return gammaFemaleDif;
    }

    public void setGammaFemaleDif(double gammaFemaleDif) {
        this.gammaFemaleDif = gammaFemaleDif;
    }

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

    public RandomGenerator getRandomGenerator(){ return randomGenerator;}



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

    public int getEncountersPerWeekRandomYoung() {
        return encountersPerWeekRandomYoung;
    }

    public void setEncountersPerWeekRandomYoung(int encountersPerWeekRandomYoung) {
        this.encountersPerWeekRandomYoung = encountersPerWeekRandomYoung;
    }

    public double getEncountersPerWeekRandomOld() {
        return encountersPerWeekRandomOld/7.0;
    }

    public void setEncountersPerWeekRandomOld(double encountersPerWeekRandomOld) {
        this.encountersPerWeekRandomOld = encountersPerWeekRandomOld;
    }

    public int getEncountersPerWeekOldAge() {
        return encountersPerWeekOldAge;
    }

    public void setEncountersPerWeekOldAge(int encountersPerWeekOldAge) {
        this.encountersPerWeekOldAge = encountersPerWeekOldAge;
    }

    public int getEncountersPerWeekSchool() {
        return encountersPerWeekSchool;
    }

    public void setEncountersPerWeekSchool(int encountersPerWeekSchool) {
        this.encountersPerWeekSchool = encountersPerWeekSchool;
    }

    public double getEncountersPerWeekRelations() {
        return encountersPerWeekRelations/7.0;
    }

    public void setEncountersPerWeekRelations(double encountersPerWeekRelations) {
        this.encountersPerWeekRelations = encountersPerWeekRelations;
    }

    public int getEncountersPerWeekWork() {
        return encountersPerWeekWork;
    }

    public void setEncountersPerWeekWork(int encountersPerWeekWork) {
        this.encountersPerWeekWork = encountersPerWeekWork;
    }

    public int getEncountersPerWeekHousehold() {
        return encountersPerWeekHousehold;
    }

    public void setEncountersPerWeekHousehold(int encountersPerWeekHousehold) {
        this.encountersPerWeekHousehold = encountersPerWeekHousehold;
    }

    public int getEncountersPerWeekFriends() {
        return encountersPerWeekFriends;
    }

    public void setEncountersPerWeekFriends(int encountersPerWeekFriends) {
        this.encountersPerWeekFriends = encountersPerWeekFriends;
    }

    public double getEncountersInSchoolclass() {
        return encountersInSchoolclass;
    }

    public void setEncountersInSchoolclass(double encountersInSchoolclass) {
        this.encountersInSchoolclass = encountersInSchoolclass;
    }

    public double getBaselineTransmissionProp() {
        return baselineTransmissionProp;
    }

    public void setBaselineTransmissionProp(double baselineTransmissionProp) {
        this.baselineTransmissionProp = baselineTransmissionProp;
    }

    public double getTransmissionRateHousehold() {
        return transmissionRateHousehold;
    }

    public void setTransmissionRateHousehold(double transmissionRateHousehold) {
        this.transmissionRateHousehold = transmissionRateHousehold;
    }

    public double getTransmissionRateRelatives() {
        return transmissionRateRelatives;
    }

    public void setTransmissionRateRelatives(double transmissionRateRelatives) {
        this.transmissionRateRelatives = transmissionRateRelatives;
    }

    public double getTransmissionRateFriends() {
        return transmissionRateFriends;
    }

    public void setTransmissionRateFriends(double transmissionRateFriends) {
        this.transmissionRateFriends = transmissionRateFriends;
    }

    public double getTransmissionRateWork() {
        return transmissionRateWork;
    }

    public void setTransmissionRateWork(double transmissionRateWork) {
        this.transmissionRateWork = transmissionRateWork;
    }

    public double getTransmissionRateWorkCustomersFacing() {
        return transmissionRateWorkCustomersFacing;
    }

    public void setTransmissionRateWorkCustomersFacing(double transmissionRateWorkCustomersFacing) {
        this.transmissionRateWorkCustomersFacing = transmissionRateWorkCustomersFacing;
    }

    public double getTransmissionRateRandom() {
        return transmissionRateRandom;
    }

    public void setTransmissionRateRandom(double transmissionRateRandom) {
        this.transmissionRateRandom = transmissionRateRandom;
    }

    public double getTransmissionRateSchool() {
        return transmissionRateSchool;
    }

    public void setTransmissionRateSchool(double transmissionRateSchool) {
        this.transmissionRateSchool = transmissionRateSchool;
    }

    public double getTransmissionRandomP() {
        return transmissionRandomP;
    }

    public void setTransmissionRandomP(double transmissionRandomP) {
        this.transmissionRandomP = transmissionRandomP;
    }

    public double getTransmissionWorkCustomersFacingP() {
        return transmissionWorkCustomersFacingP;
    }

    public void setTransmissionWorkCustomersFacingP(double transmissionWorkCustomersFacingP) {
        this.transmissionWorkCustomersFacingP = transmissionWorkCustomersFacingP;
    }

    public double getTransmissionReductionHouseholdIsolated() {
        return transmissionReductionHouseholdIsolated;
    }

    public void setTransmissionReductionHouseholdIsolated(double transmissionReductionHouseholdIsolated) {
        this.transmissionReductionHouseholdIsolated = transmissionReductionHouseholdIsolated;
    }
}
