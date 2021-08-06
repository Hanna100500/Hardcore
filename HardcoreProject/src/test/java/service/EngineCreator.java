package service;

import model.Engine;

public class EngineCreator {

    public static final String TESTDATA_NUMBER_OF_INSTANCES = "testdata.engine.numberOfInstances";

    public static Engine createSomeDefaultEngine() {
        return new Engine(TestDataReader.getTestData(TESTDATA_NUMBER_OF_INSTANCES));
    }
}
