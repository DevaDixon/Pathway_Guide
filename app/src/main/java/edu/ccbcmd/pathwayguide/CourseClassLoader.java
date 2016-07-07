package edu.ccbcmd.pathwayguide;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import edu.ccbcmd.pathwayguide.CourseClass;

import java.util.ArrayList;
import java.util.List;


//This class will load the courses into memory.
// The operation of loading the courses into memory is accomplished by using sharedpreferences to direct
//which vectors of classes to load courseclass objects with.
//This class ASSUMES that the setup has completed properly.
public class CourseClassLoader {


    //This list allows the courses to be sorted according to their status as done, inprogress, available, and not able to take
    private List<CourseClass> sortedObject;

    //This is the access to the database
    PathwaysDBHelper dataBase;

    //This variable will loaded from the vector of course labels the appropriate labels for the pathway
    public  static String[] courseLabels;
    //This variable will load in the vector of course full labels.
    String[] courseFullTitles;
    //This variable will load in the prerequisites for the courses. Each position corresponds to the courseLabels position.
    String[] coursePrereqs;
    //This variable will load in the urls for the courses. Each position corresponds to the courseLabels position
    String[] courseURLs;

    //This will store the pathway name
    String pathwayText;



    CourseClassLoader(Context context){
        super();

        //We need to load in three separate instances of the sharedpreferences as each of the first two instances only contains one vector
        //Each vector of data stores booleans.  These booleans indicate whether a course is done or inprogress.
        SharedPreferences sharedPrefDone = context.getSharedPreferences("courses", Context.MODE_PRIVATE);
        SharedPreferences sharedPrefInProgress = context.getSharedPreferences("coursesInProgress", Context.MODE_PRIVATE);
        //The third instance of sharedpreferences is the particular pathway chosen.
        SharedPreferences pathwayPref = context.getSharedPreferences("pathway", Context.MODE_PRIVATE);

        //The fourth instance of sharedpreferences is to get the permission of a course
        SharedPreferences pathwayPermission = context.getSharedPreferences("permission",Context.MODE_PRIVATE);

        //Initializing the database
        dataBase = new PathwaysDBHelper(context);
        DatabaseWrapper wrapper = new DatabaseWrapper();


        int pathway = -1;
        if (pathwayPref.contains("PathwayChoice"))
        {
            pathway = pathwayPref.getInt("PathwayChoice", 100);
        } else { pathway = 100;}

        //Once the pathway choice is memorialized as an integer, the switch case statement here will load in the appropriate
        // vectors into the courseLabels and coursePrereqs and courseURLs variables.
        //TODO: FIX THIS SWITCH STATEMENT TO ENCOMPASS ALL OF THE PATHWAYS.
        //TODO: INCLUDE THE SUBPATHWAY STATMENTS AS WELL!
        switch (pathway){
            case CourseContract.PRE_ALLIED_HEALTH._PRE_ALLIED_HEALTH:
            {
                //Database way
                courseLabels = wrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME;
                coursePrereqs = loadInPreReqs(courseLabels);
                courseFullTitles = loadInTitles(courseLabels);

                //Old Way
                //courseLabels = context.getResources().getStringArray(R.array.AlliedHealthPathway);
                //courseFullTitles = new String[courseLabels.length]; //context.getResources().getStringArray(R.array.AlliedHealthPathwayFullTitles);
                //coursePrereqs = context.getResources().getStringArray(R.array.AlliedHealthPrereqs);
                courseURLs = new String[courseLabels.length]; //context.getResources().getStringArray(R.array.AlliedHealthURLS);
                break;
            }
            case CourseContract.TSM.TSM:
            {
                //DataBase Way
                courseLabels = wrapper.getSubPathwayClasses(CourseContract.TSM.TSM_COMPUTER_SCIENCE_IT_NAME);
                pathwayText = CourseContract.TSM.TSM_COMPUTER_SCIENCE_IT_NAME;
                coursePrereqs = loadInPreReqs(courseLabels);
                courseFullTitles = loadInTitles(courseLabels);
                //OldWay
                //courseLabels = context.getResources().getStringArray(R.array.TSMPathway);
                //courseFullTitles = new String[courseLabels.length]; //context.getResources().getStringArray(R.array.TSMPathwayFullTitles);
                //coursePrereqs =  loadInPreReqs(courseLabels); //context.getResources().getStringArray(R.array.TSMPrereqs);
                courseURLs = new String[courseLabels.length]; //context.getResources().getStringArray(R.array.TSMURLS);
                break;
            }
            default:
            {
                //Database way
                courseLabels = wrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME;
                coursePrereqs = loadInPreReqs(courseLabels);
                courseFullTitles = loadInTitles(courseLabels);


                //Old Way
                //courseLabels = context.getResources().getStringArray(R.array.AlliedHealthPathway);
                //courseFullTitles = new String[courseLabels.length]; //context.getResources().getStringArray(R.array.AlliedHealthPathwayFullTitles);
                //coursePrereqs = context.getResources().getStringArray(R.array.AlliedHealthPrereqs);
                courseURLs = new String[courseLabels.length]; //context.getResources().getStringArray(R.array.AlliedHealthURLS);
                break;
            }
        }


        //This is the assignment of courseObjects and sortedObjects
        //coursesObject = new ArrayList<CourseClass>();
        sortedObject = new ArrayList<CourseClass>();

        //These Objects are instantiated to hold the categories of courseobjects
        List<CourseClass> courseDone = new ArrayList<CourseClass>();
        List<CourseClass> courseInProgress = new ArrayList<CourseClass>();
        List<CourseClass> courseTop = new ArrayList<CourseClass>();
        List<CourseClass> courseAvailable = new ArrayList<CourseClass>();
        boolean canJump = false;
        //This Loop determines what category each of the courses is in.
        for (int i = courseLabels.length-1; i>=0; i--)
        {
            //This section of code initializes from the shared preferences whether a course is done, inprogress or has prerequisites
            //TODO: PROTECT THIS SECTION FROM BAD INITIALIZations  AS IN: MAKE SURE NONE OF THESE ARE NULL.
            boolean isCourseAvailableForRegistration = false;
            String iCoursePrereq = coursePrereqs[i];
            boolean done = sharedPrefDone.getBoolean(courseLabels[i], false);
            boolean inProgress = sharedPrefInProgress.getBoolean(courseLabels[i], false);
            boolean preReq = false;

            //These lines check if the course has a listed prerequisite, and sets the corresponding flag.
            if (!iCoursePrereq.equals("NONE")){preReq = true;}

            //this complicated bit of logic asks if prerequisites have been done for a course that is not done nor in progress.
            //I must mention, I don't follow the logic today, but I'm sure that it works... somehow.
            if (!done&&!inProgress){

                for (int j =0; j<courseLabels.length-1; j++)
                {
                    String courseString = courseLabels[j];
                    boolean prereqDone = sharedPrefDone.getBoolean(courseLabels[j],false);
                    if (courseString.equals(iCoursePrereq)&&prereqDone){
                        isCourseAvailableForRegistration = true;
                    }
                }
                canJump = pathwayPermission.getBoolean("permission"+courseLabels[i],false);
                if (!isCourseAvailableForRegistration && !done && !inProgress && preReq && canJump){ isCourseAvailableForRegistration = true;}
                if (!isCourseAvailableForRegistration && !done && !inProgress &&!preReq){isCourseAvailableForRegistration = true;}
            }

            boolean meet = false;
            if (courseLabels[i].equals("GEMATH")){meet = true;}

            //After setting all of the appropriate flags,  The course object itself is instantiated.
            CourseClass course = new CourseClass(courseLabels[i],
                    courseFullTitles[i],
                    courseURLs[i],
                    done,
                    inProgress,
                    preReq,
                    coursePrereqs[i],
                    isCourseAvailableForRegistration,
                    i,
                    meet,
                    canJump);


            //This section of code adds the course to the particular container, that is, done, inprogress, etc. container
            boolean added = false;
            if (done){
                courseDone.add(course);
                added = true;
            }
            if (inProgress && !added){
                courseInProgress.add(course);
                added = true;
            }
            if (isCourseAvailableForRegistration && !added){
                courseAvailable.add(course);
                added = true;
            }
            if (!added){
                courseTop.add(course);
            }
            //unnecessary anymore, we can simply remove this  I think.
            //coursesObject.add(course);
        }

        //This section of loops adds the courses object into the sortedobject correctly.
        for (CourseClass course : courseTop) {
            sortedObject.add(course);

        }
        for (CourseClass course : courseAvailable){
            sortedObject.add(course);
        }

        for (CourseClass course : courseInProgress){
            sortedObject.add(course);

        }
        for (CourseClass course : courseDone){
            sortedObject.add(course);

        }

    }


    public List<CourseClass> loadClassObjects(){
        return sortedObject;
    }

    public int howManyCourses(){return sortedObject.size();}

    public String[] getCourseLabels(){return courseLabels;}

    public CourseClass getXMLOrder(int order){
        for (CourseClass course : sortedObject){
            if (course.getPosition()==order)
            {
                return course;
            }
        }
        return null;
    }

    public String getPathway() {return pathwayText;}

    private String[] loadInPreReqs(String[] courses){
        String[] preReq = new String[courses.length];
        DatabaseWrapper wrapper = new DatabaseWrapper();
        for ( int i = 0; i < courses.length; i++) {
            if (wrapper.getClassPrereqs(courses[i]).length!=0)
                preReq[i] = wrapper.getClassPrereqs(courses[i])[0];
            else
                preReq[i] = "NONE";
        }
        return preReq;
    }

    private String[] loadInTitles(String[] courses){
        String[] titles = new String[courses.length];
        DatabaseWrapper wrapper = new DatabaseWrapper();
        for (int i = 0; i<courses.length; i++){
            String[] cur = wrapper.getClassInfo(courses[i]);


            if (cur.length !=0)
                titles[i] = cur[1];
            else
                titles[i] = "Null";
        }
        return titles;
    }
}