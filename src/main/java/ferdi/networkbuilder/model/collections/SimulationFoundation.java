package ferdi.networkbuilder.model.collections;

import ferdi.networkbuilder.model.agents.Agent;

public class SimulationFoundation {
    private final AgentMap<Agent> susceptibleAgents;
    private final AgentMap<Agent> incubationAgents;
    private final AgentMap<Agent> asymptomaticAgents;
    private final AgentMap<Agent> mildAgents;
    private final AgentMap<Agent> severeAgents;
    private final AgentMap<Agent> severeHosAgents;
    private final AgentMap<Agent> deadAgents;
    private final AgentMap<Agent> recoveredAgents;


    public SimulationFoundation(AgentMap<Agent> susceptibleAgents, AgentMap<Agent> incubationAgents, AgentMap<Agent> recoveredAgents) {
        this.susceptibleAgents = susceptibleAgents;
        this.incubationAgents = incubationAgents;
        this.recoveredAgents = recoveredAgents;
        asymptomaticAgents = new AgentMap<>();
        mildAgents = new AgentMap<>();
        severeAgents = new AgentMap<>();
        severeHosAgents = new AgentMap<>();
        deadAgents = new AgentMap<>();
    }

    public AgentMap<Agent> getSusceptibleAgents() {
        return susceptibleAgents;
    }

    public AgentMap<Agent> getIncubationAgents() {
        return incubationAgents;
    }

    public AgentMap<Agent> getAsymptomaticAgents() {
        return asymptomaticAgents;
    }

    public AgentMap<Agent> getMildAgents() {
        return mildAgents;
    }

    public AgentMap<Agent> getSevereAgents() {
        return severeAgents;
    }

    public AgentMap<Agent> getSevereHosAgents() {
        return severeHosAgents;
    }

    public AgentMap<Agent> getDeadAgents() {
        return deadAgents;
    }

    public AgentMap<Agent> getRecoveredAgents() {
        return recoveredAgents;
    }
}
