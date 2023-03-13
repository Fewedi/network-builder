package ferdi.networkbuilder.metadata;

public abstract class GeographicMetaData {

    private Long cduId;
    private String geoCode;

    private String geoLabel;

    public void setGeographicMetaData(Long cduId, String geoCode, String geoLabel) {
        this.cduId = cduId;
        this.geoCode = geoCode;
        this.geoLabel = geoLabel;
    }

    public Long getCduId() {
        return cduId;
    }

    public String getGeoCode() {
        return geoCode;
    }

    public String getGeoLabel() {
        return geoLabel;
    }

    @Override
    public String toString() {
        return
                "cduId=" + cduId +
                ", geoCode='" + geoCode + '\'' +
                ", geoLabel='" + geoLabel + '\''
                ;
    }
}
