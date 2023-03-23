package ferdi.networkbuilder.services.output;

import ferdi.networkbuilder.config.MetaConfig;
import ferdi.networkbuilder.metadata.NetworkSummaryData;
import ferdi.networkbuilder.model.collections.ModelFoundation;

public interface OutputService {

    NetworkSummaryData createNetworkSummary(ModelFoundation modelFoundation, MetaConfig config);
}
