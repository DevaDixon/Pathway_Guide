package edu.ccbcmd.pathwayguide;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import edu.ccbcmd.pathwayguide.CourseClass;

import java.util.ArrayList;
import java.util.List;

//Checked and pasted

//This class will load the courses into memory.
// The operation of loading the courses into memory is accomplished by using sharedpreferences to direct
//which vectors of classes to load courseclass objects with.
//This class ASSUMES that the setup has completed properly.
public class CourseClassLoader {


    //This list allows the courses to be sorted according to their status as done, inprogress, available, and not able to take
    private List<CourseClass> sortedObject;

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

        //The fourth instance of sharedpreferences is to get the permission of a course
        SharedPreferences pathwayPermission = context.getSharedPreferences("permission",Context.MODE_PRIVATE);

        //The fifth instance of sharedpreferences is to get the double class status
        SharedPreferences pathwayDoubleCourse = context.getSharedPreferences("DoubleCourse",Context.MODE_PRIVATE);

        String subpathway = DatabaseWrapper.getSettingsSubPathway();
        if (subpathway == "null") { subpathway = CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME;}

        // This switch statement will load in the proper subpathways and the associated data.
        switch (subpathway) {
            case CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME: {
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME;

                break;
            }
            case CourseContract.PRE_ALLIED_HEALTH.OCCUPATIONAL_THERAPY_ASSISTANT_NAME: {
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.OCCUPATIONAL_THERAPY_ASSISTANT_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.OCCUPATIONAL_THERAPY_ASSISTANT_NAME;
                break;
            }
            case CourseContract.PRE_ALLIED_HEALTH.DENTAL_HYGIENE_NAME: {
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.DENTAL_HYGIENE_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.DENTAL_HYGIENE_NAME;
                break;
            }
            case CourseContract.PRE_ALLIED_HEALTH.MEDICAL_LAB_TECHNOLOGY_NAME: {
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.MEDICAL_LAB_TECHNOLOGY_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.MEDICAL_LAB_TECHNOLOGY_NAME;
                break;
            }
            case CourseContract.PRE_ALLIED_HEALTH.VETERINARY_TECHNOLOGY_NAME: {
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.VETERINARY_TECHNOLOGY_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.VETERINARY_TECHNOLOGY_NAME;
                break;
            }
            case CourseContract.PRE_ALLIED_HEALTH.EMT_NAME: {
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.EMT_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.EMT_NAME;
                break;
            }
            case CourseContract.PRE_ALLIED_HEALTH.RCT_NAME: {
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.RCT_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.RCT_NAME;
                break;
            }
            case CourseContract.PRE_ALLIED_HEALTH.RADIOGRAPHY_NAME: {
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.RADIOGRAPHY_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.RADIOGRAPHY_NAME;
                break;
            }
            case CourseContract.PRE_ALLIED_HEALTH.RADIATION_THERAPY_NAME: {
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.RADIATION_THERAPY_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.RADIATION_THERAPY_NAME;
                break;
            }
            case CourseContract.PRE_ALLIED_HEALTH.MASSAGE_THERAPY_NAME: {
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.MASSAGE_THERAPY_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.MASSAGE_THERAPY_NAME;
                break;
            }
            case CourseContract.PRE_ALLIED_HEALTH.MORTUARY_SCIENCE_NAME: {
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.MORTUARY_SCIENCE_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.MORTUARY_SCIENCE_NAME;
                break;
            }
            case CourseContract.PRE_ALLIED_HEALTH.MENTAL_HEALTH_NAME: {
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.MENTAL_HEALTH_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.MENTAL_HEALTH_NAME;
                break;
            }
            case CourseContract.TSM.TSM_COMPUTER_SCIENCE_IT_NAME: {
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.TSM.TSM_COMPUTER_SCIENCE_IT_NAME);
                pathwayText = CourseContract.TSM.TSM_COMPUTER_SCIENCE_IT_NAME;
                break;
            }
            case CourseContract.TSM.TSM_COMPUTER_SCIENCE_NAME: {
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.TSM.TSM_COMPUTER_SCIENCE_NAME);
                pathwayText = CourseContract.TSM.TSM_COMPUTER_SCIENCE_NAME;
                break;
            }
            default: {
                Log.e("CCL", "You loaded default!");
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME;
                break;
            }
        }

        coursePrereqs = loadInPreReqs(courseLabels);
        courseFullTitles = loadInTitles(courseLabels);
        courseURLs = new String[courseLabels.length];

        //This holds the courseclass list
        sortedObject = new ArrayList<CourseClass>();

        //These Objects are instantiated to hold the categories of courseobjects
        List<CourseClass> courseDone = new ArrayList<CourseClass>();
        List<CourseClass> courseInProgress = new ArrayList<CourseClass>();
        List<CourseClass> courseTop = new ArrayList<CourseClass>();
        List<CourseClass> courseAvailable = new ArrayList<CourseClass>();

        CourseClass course = null;
        boolean hasBeenAdded = false;

        boolean canJump = false;
        //This Loop determines what category each of the courses is in.
        for (int i = courseLabels.length-1; i>=0; i--)
        {
            hasBeenAdded = false;
            //This section of code initializes from the shared preferences whether a course is done, inprogress or has prerequisites

            boolean isCourseAvailableForRegistration = false;

            boolean isDoublePrereq = false;
            if (coursePrereqs[i].split(",").length>1){isDoublePrereq = true;}  //This stands to be the trigger if the class has either/or prereqs.

            boolean isDoubleClass = false;
            if (courseLabels[i].substring(0,2).equals("GE")||courseLabels[i].substring(0,2).equals("PR")){
                isDoubleClass = true;
            }

            int courseStatus = DatabaseWrapper.getClassStatus(courseLabels[i]);

            boolean done =false;
            if (courseStatus ==2){
                done = true;
            }

            boolean inProgress=false;
            if (courseStatus == 1){
                inProgress = true;
            }

            boolean preReq = false;


            if (isDoubleClass){
                String title;
                if (pathwayDoubleCourse.contains("Double"+courseLabels[i])){
                    title = pathwayDoubleCourse.getString(("Double"+courseLabels[i]),courseLabels[i]);
                } else {
                    title = courseLabels[i];
                }


                String[] doubleClasses = {""};
                if (title.equals(courseLabels[i])) {
                    doubleClasses = DatabaseWrapper.getCoursesThatQualify(title, subpathway);
                } else {

                    doubleClasses = new String[] {title};
                    //Why did this break things
                    isDoubleClass = false;
                }

                String iCoursePrereq = coursePrereqs[i];
                //These lines check if the course has a listed prerequisite, and sets the corresponding flag.
                if (!iCoursePrereq.equals("NONE")){preReq = true;}

                //this complicated bit of logic asks if prerequisites have been done for a course that is not done nor in progress.
                //I must mention, I don't follow the logic today, but I'm sure that it works... somehow.
                if (!done&&!inProgress){

                    for (int j =0; j<courseLabels.length-1; j++)
                    {
                        String courseString = title;
                        int preRecStatus = DatabaseWrapper.getClassStatus(courseLabels[j]);
                        boolean prereqDone = false;
                        if (preRecStatus==2){
                            prereqDone =true;
                        }
                        if (courseString.equals(iCoursePrereq)&&prereqDone){
                            isCourseAvailableForRegistration = true;
                        }
                    }
                    canJump = pathwayPermission.getBoolean("permission"+title,false);
                    if (!isCourseAvailableForRegistration && !done && !inProgress && preReq && canJump){ isCourseAvailableForRegistration = true;}
                    if (!isCourseAvailableForRegistration && !done && !inProgress &&!preReq){isCourseAvailableForRegistration = true;}
                }


                //This sets the flag to see if you need to meet with the advisor to decide which class to take
                boolean meet = false;
                if (courseLabels[i].substring(0,2).equals("GE")){meet = true;}

                String[] courseInfo = DatabaseWrapper.getClassInfo(title);
                if (title.substring(0,2).equals("PR")){
                    courseInfo = DatabaseWrapper.getClassInfo("PRGELEC");
                } else if (courseInfo.length == 0){
                    Log.e("WEHAVEERROR",title);
                    courseInfo = DatabaseWrapper.getClassInfo("GENMATH");
                }




                //After setting all of the appropriate flags,  The course object itself is instantiated.

                course = new CourseClass(title.substring(0,7),
                        courseInfo[1],
                        courseInfo[2],
                        done,
                        inProgress,
                        preReq,
                        new String[] {coursePrereqs[i]},
                        isCourseAvailableForRegistration,
                        i,
                        meet,
                        canJump,
                        isDoubleClass,
                        doubleClasses,
                        courseStatus);
                hasBeenAdded = true;
            }


            if(isDoublePrereq && !hasBeenAdded){
                String[] iCoursePrereqArray = coursePrereqs[i].split(",");
                for (int iterator = 0; iterator<iCoursePrereqArray.length; iterator++) {
                    String iCoursePrereq = iCoursePrereqArray[iterator];
                    //These lines check if the course has a listed prerequisite, and sets the corresponding flag.
                    if (!iCoursePrereq.equals("NONE")) {
                        preReq = true;
                    }

                    //this complicated bit of logic asks if prerequisites have been done for a course that is not done nor in progress.
                    //I must mention, I don't follow the logic today, but I'm sure that it works... somehow.
                    if (!done && !inProgress) {

                        for (int j = 0; j < courseLabels.length - 1; j++) {
                            String courseString = courseLabels[j];
                            int preRecStatus = DatabaseWrapper.getClassStatus(courseLabels[j]);
                            boolean prereqDone = false;
                            if (preRecStatus==2){
                                prereqDone =true;
                            }
                            if (courseString.equals(iCoursePrereq) && prereqDone) {
                                isCourseAvailableForRegistration = true;
                            }
                        }
                        canJump = pathwayPermission.getBoolean("permission" + courseLabels[i], false);
                        if (!isCourseAvailableForRegistration && !done && !inProgress && preReq && canJump) {
                            isCourseAvailableForRegistration = true;
                        }
                        if (!isCourseAvailableForRegistration && !done && !inProgress && !preReq) {
                            isCourseAvailableForRegistration = true;
                        }
                    }
                }

                boolean meet = false;
                if (courseLabels[i].substring(0,2).equals("GE")){meet = true;}



                //After setting all of the appropriate flags,  The course object itself is instantiated.
                course = new CourseClass(courseLabels[i].substring(0,7),
                        courseFullTitles[i],
                        courseURLs[i],
                        done,
                        inProgress,
                        preReq,
                        new String[] {coursePrereqs[i]},
                        isCourseAvailableForRegistration,
                        i,
                        meet,
                        canJump,
                        false,
                        new String[] {""},
                        courseStatus);
                hasBeenAdded = true;
            }

            if(!hasBeenAdded) {
                String iCoursePrereq = coursePrereqs[i];
                //These lines check if the course has a listed prerequisite, and sets the corresponding flag.
                if (!iCoursePrereq.equals("NONE")){preReq = true;}

                //this complicated bit of logic asks if prerequisites have been done for a course that is not done nor in progress.
                //I must mention, I don't follow the logic today, but I'm sure that it works... somehow.
                if (!done&&!inProgress){

                    for (int j =0; j<courseLabels.length-1; j++)
                    {
                        String courseString = courseLabels[j];
                        int preRecStatus = DatabaseWrapper.getClassStatus(courseLabels[j]);
                        boolean prereqDone = false;
                        if (preRecStatus==2){
                            prereqDone =true;
                        }
                        if (courseString.equals(iCoursePrereq)&&prereqDone){
                            isCourseAvailableForRegistration = true;
                        }
                    }
                    canJump = pathwayPermission.getBoolean("permission"+courseLabels[i],false);
                    if (!isCourseAvailableForRegistration && !done && !inProgress && preReq && canJump){ isCourseAvailableForRegistration = true;}
                    if (!isCourseAvailableForRegistration && !done && !inProgress &&!preReq){isCourseAvailableForRegistration = true;}
                }

                boolean meet = false;
                if (courseLabels[i].substring(0,2).equals("GE")){meet = true;}



                //After setting all of the appropriate flags,  The course object itself is instantiated.
                course = new CourseClass(courseLabels[i].substring(0,7),
                        courseFullTitles[i],
                        courseURLs[i],
                        done,
                        inProgress,
                        preReq,
                        new String[] {coursePrereqs[i]},
                        isCourseAvailableForRegistration,
                        i,
                        meet,
                        canJump,
                        false,
                        new String[] {""},
                        courseStatus);
            }


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

        }

        //This section of loops adds the courses object into the sortedobject correctly.
        for (CourseClass course1 : courseTop) {
            sortedObject.add(course1);

        }
        for (CourseClass course1 : courseAvailable){
            sortedObject.add(course1);
        }

        for (CourseClass course1 : courseInProgress){
            sortedObject.add(course1);

        }
        for (CourseClass course1 : courseDone){
            sortedObject.add(course1);

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

    public CourseClass instantiateNewCourse(String courseID, Context context, int count){
        CourseClass course = null;

        boolean canJump = false;
        boolean isCourseAvailableForRegistration = false;
        String iCoursePrereq;
        if (DatabaseWrapper.getClassPrereqs(courseID).length>0) {
            iCoursePrereq = DatabaseWrapper.getClassPrereqs(courseID)[0];
        } else {
            iCoursePrereq ="NONE";
        }
        boolean isDoublePrereq = false;
        //TODO FIGURE OUT WHAT THIS IS TRIGGERED BY? DATABASE CALL?
        //if (somecondition){ isDoublePrereq = true;}  //This stands to be the trigger if the class has either/or prereqs.

        boolean isDoubleClass = false;
        if (courseID.substring(0,2).equals("GE")){
            isDoubleClass = true;
        }

        //The fourth instance of sharedpreferences is to get the permission of a course
        SharedPreferences pathwayPermission = context.getSharedPreferences("permission",Context.MODE_PRIVATE);

        //The fifth instance of sharedpreferences is to get the double class status
        SharedPreferences pathwayDoubleCourse = context.getSharedPreferences("DoubleCourse",Context.MODE_PRIVATE);

        int courseStatus = DatabaseWrapper.getClassStatus(courseID);
        boolean done =false;
        if (courseStatus ==2){
            done = true;
        }

        boolean inProgress=false;
        if (courseStatus == 1){
            inProgress = true;
        }
        boolean preReq = false;
        //This Loop determines what category each of the courses is in.
        //Assume the courses don't have extra prereqs.  Don't want to hurt my head here.

        //These lines check if the course has a listed prerequisite, and sets the corresponding flag.
        if (!iCoursePrereq.equals("NONE")){preReq = true;}

        //this complicated bit of logic asks if prerequisites have been done for a course that is not done nor in progress.
        //I must mention, I don't follow the logic today, but I'm sure that it works... somehow.
        if (!done&&!inProgress){

            for (int j =0; j<courseLabels.length-1; j++)
            {
                String courseString = courseLabels[j];
                int preRecStatus = DatabaseWrapper.getClassStatus(courseLabels[j]);
                boolean prereqDone = false;
                if (preRecStatus==2){
                    prereqDone =true;
                }
                if (courseString.equals(iCoursePrereq)&&prereqDone){
                    isCourseAvailableForRegistration = true;
                }
            }
            canJump = pathwayPermission.getBoolean("permission"+courseID,false);
            if (!isCourseAvailableForRegistration && !done && !inProgress && preReq && canJump){ isCourseAvailableForRegistration = true;}
            if (!isCourseAvailableForRegistration && !done && !inProgress &&!preReq){isCourseAvailableForRegistration = true;}
        }

        boolean meet = false;
        if (courseID.substring(0,2).equals("GE")){meet = true;}

        String[] courseInfo = DatabaseWrapper.getClassInfo(courseID);

        //After setting all of the appropriate flags,  The course object itself is instantiated.
        course = new CourseClass(courseID.substring(0,7),
                courseInfo[1],
                courseInfo[2],
                done,
                inProgress,
                preReq,
                new String[] {iCoursePrereq},
                isCourseAvailableForRegistration,
                count,
                meet,
                canJump,
                false,
                new String[] {""},
                courseStatus);
        return  course;
    }

    public CourseClass getCourseByName(String name, int count, Context context){
        for (CourseClass course : sortedObject){
            if (course.getTitle().equals(name))
            {
                return course;
            }
        }
        //We must instantiate the course in question!
        return instantiateNewCourse(name,context, count);
    }

    public String getPathway() {return pathwayText;}

    private String[] loadInPreReqs(String[] courses){
        String[] preReq = new String[courses.length];
        for ( int i = 0; i < courses.length; i++) {
            if (DatabaseWrapper.getClassPrereqs(courses[i]).length!=0)
                preReq[i] = DatabaseWrapper.getClassPrereqs(courses[i])[0];
            else
                preReq[i] = "NONE";
        }


        return preReq;
    }

    private String[] loadInTitles(String[] courses){
        String[] titles = new String[courses.length];
        DatabaseWrapper wrapper = new DatabaseWrapper();
        for (int i = 0; i<courses.length; i++){
            String[] cur = DatabaseWrapper.getClassInfo(courses[i]);


            if (cur.length !=0)
                titles[i] = cur[1];
            else
                titles[i] = "Null";
        }
        return titles;
    }
}