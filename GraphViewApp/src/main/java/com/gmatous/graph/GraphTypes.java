package com.gmatous.graph;

/**
 * Created by gmatous on 1/7/14.
 */
public enum GraphTypes {
    GRAPH_VIEW1("Graph View Bar") {
        @Override
        public String data() {
            return "copper";
        }
    }, GRAPH_VIEW2("Graph View Line") {
        @Override
        public String data() {
            return "bronze";
        }
    }, A_CHART_ENGINE1("Chart Engine 1") {
        @Override
        public String data() {
            return "silver";
        }
    }, A_CHART_ENGINE2("Chart Engine 2") {
        @Override
        public String data() {
            return "silver";
        }
    }, A_CHART_ENGINE3("Chart Engine 3") {
        @Override
        public String data() {
            return "silver";
        }
    }, A_CHART_ENGINE4("Chart Engine 4") {
        @Override
        public String data() {
            return "silver";
        }
    }, DEFAULT("Chart Engine 1") {
        @Override
        public String data() {
            return "silver";
        }
    };
    private String graphName;

    public String getGraphName() {
        return graphName;
    }

    public abstract String data();

    private GraphTypes(String name) {
        this.graphName = name;
    }
}