package ferdi.networkbuilder.config;

import ferdi.networkbuilder.metadata.GeographicHouseholdMetaData;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
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
    private boolean friendshipsSkewedByAge;
    private int friendshipsMinAge;
    private int friendshipsSkewedByAgeAgeGroup;
    private int friendshipsBarabasiAlbertGraphM;

    private int livesWithParentsMaxAge;

    private int maxForHighestWorksiteSizeIsNTimesOfMin;
    private final Random random;
    private int seed;

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
}
