package edu.ccbcmd.pathwayguide;

/**
 * Created by jones on 7/4/2016.
 */
public class CourseContract {

    public CourseContract()
    {
        super();
    }

    final public static class PRE_ALLIED_HEALTH {
        public static final String PRE_ALLIED_HEALTH_NAME = "Pre-Allied Health";



        final public static int _PRE_ALLIED_HEALTH = 100;

        final public static int ALLIED_HEALTH_NURSING_ASN = 101;
        final public static String ALLIED_HEALTH_NURSING_ASN_NAME = "Nursing";


        final public static int THE_REST = 102;
        final public static String THE_REST_NAME = "Not Implemented";
    }


    final public static class TSM {
        final public static String TSM_NAME = "Technology, Science, & Math";

        final public static int TSM = 200;

        final public static int TSM_COMPUTER_SCIENCE = 210;


        final public static int TSM_COMPUTER_SCIENCE_IT = 211;
        final public static String TSM_COMPUTER_SCIENCE_IT_NAME = "Information Technology";

    }

    final public static class BUSINESS_LAW_CRIMINAL_JUSTICE {
        final public static String BUSINESS_LAW_CRIMINAL_JUSTICE_NAME = "Business, Law, and Criminal Justice";

        final public static int BUSINESS_LAW_CRIMINAL_JUSTICE = 300;
    }

    final public static class ARTS_HUMANITIES {
        final public static String ARTS_HUMANITIES_NAME = "Arts and Humanities";

        final public static int ARTS_HUMANITIES = 400;
    }

    final public static class  BEHAVIOR_SOCIAL_SCIENCES {
        final public static String BEHAVIOR_SOCIAL_SCIENCES_NAME = " Behavior and Social Sciences";

        final public static int BEHAVIOR_SOCIAL_SCIENCES = 500;
    }




}
