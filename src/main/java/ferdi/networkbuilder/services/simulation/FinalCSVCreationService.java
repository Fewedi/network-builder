package ferdi.networkbuilder.services.simulation;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.metadata.DaySummary;

import java.util.List;

public interface FinalCSVCreationService {

    void create(List<DaySummary> daySummaryList, MetaConfig config, String fileName);
}
