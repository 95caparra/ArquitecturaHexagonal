package com.movii.hexagonal.commons.helper;

public class ConstantsHelper {

    /**
     * method: ConstantsHelper
     *
     */
    public ConstantsHelper() {
        //do anything
    }

    // Yml properties
    public static final String PROJECT_PATH = "${spring.application.root}";
    public static final String PING_YML_ROUTE = "${spring.application.services.rest.ping.path}";
    public static final String SPRING_CONFIG_PREFIX = "spring.application";

    //Util
    public static final String LONG_LINE = "------------------------------------------------";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    // Logs on start application
    public static final String LOG_START_PROJECT = "{} Aplicación iniciada";
    public static final String LOG_PORT_OF_PROJECT = "Puerto: {}";
    public static final String LOG_PROJECT_VERSION = "Versión: {}";
    public static final String LOG_RUN_OK = "Lanzamiento [OK]";

    //Correlative
    static final String CORRELATIVE_ID = "correlation-id";
    static final String COMPONENT_CORRELATIVE = "component";


    //Validator fields Regex
    public static final String REGEXP_NUMERIC = "[0-9]+";

}
