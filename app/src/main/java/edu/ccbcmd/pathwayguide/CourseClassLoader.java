package edu.ccbcmd.pathwayguide;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;


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
<<<<<<< HEAD
    String subPathText;
=======
    String pathwayText;
>>>>>>> refs/remotes/origin/master

    CourseClassLoader(Context context){
        super();

<<<<<<< HEAD
        //We need to load in three separate instances of the sharedpreferences as each of the first two instances only contains one vector
        //Each vector of data stores booleans.  These booleans indicate whether a course is done or inprogress.
        SharedPreferences sharedPrefDone = context.getSharedPreferences("courses", Context.MODE_PRIVATE);

        //The third instance of sharedpreferences is the particular pathway chosen.
        SharedPreferences pathwayPref = context.getSharedPreferences("pathway", Context.MODE_PRIVATE);

=======
>>>>>>> refs/remotes/origin/master
        //The fourth instance of sharedpreferences is to get the permission of a course
        SharedPreferences pathwayPermission = context.getSharedPreferences("permission",Context.MODE_PRIVATE);

        //The fifth instance of sharedpreferences is to get the double class status
        SharedPreferences pathwayDoubleCourse = context.getSharedPreferences("DoubleCourse",Context.MODE_PRIVATE);

<<<<<<< HEAD


        if (pathwayPref.contains("SubPathTitle"))
        {
            subPathText = pathwayPref.getString("SubPathTitle", null);
        } else {
            // TODO: 7/23/2016 Implement try/catch or other way to launch choosePathway.class if null
         subPathText = "Nursing";
=======
        int pathway =DatabaseWrapper.getSettingsPathway();
        if (pathway == -1){pathway = 100;}

        //Once the pathway choice is memorialized as an integer, the switch case statement here will load in the appropriate
        // vectors into the courseLabels and coursePrereqs and courseURLs variables.
        //TODO: FIX THIS SWITCH STATEMENT TO ENCOMPASS ALL OF THE PATHWAYS.
        //TODO: INCLUDE THE SUBPATHWAY STATMENTS AS WELL!
        switch (pathway){
            case CourseContract.PRE_ALLIED_HEALTH._PRE_ALLIED_HEALTH:
            {
                //Database way
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME;
                coursePrereqs = loadInPreReqs(courseLabels);
                courseFullTitles = loadInTitles(courseLabels);
                courseURLs = new String[courseLabels.length];
                break;
            }
            case CourseContract.TSM.TSM:
            {
                //DataBase Way
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.TSM.TSM_COMPUTER_SCIENCE_IT_NAME);
                pathwayText = CourseContract.TSM.TSM_COMPUTER_SCIENCE_IT_NAME;
                coursePrereqs = loadInPreReqs(courseLabels);
                courseFullTitles = loadInTitles(courseLabels);
                courseURLs = new String[courseLabels.length];
                break;
            }
            default:
            {
                //Database way
                courseLabels = DatabaseWrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME);
                pathwayText = CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME;
                coursePrereqs = loadInPreReqs(courseLabels);
                courseFullTitles = loadInTitles(courseLabels);
                courseURLs = new String[courseLabels.length];
                break;
            }
>>>>>>> refs/remotes/origin/master
        }

        courseLabels = DatabaseWrapper.getSubPathwayClasses(subPathText);


        coursePrereqs = loadInPreReqs(courseLabels);
        courseFullTitles = loadInTitles(courseLabels);

        //This is the assignment of courseObjects and sortedObjects
        //coursesObject = new ArrayList<CourseClass>();
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

<<<<<<< HEAD
            int status = DatabaseWrapper.getClassStatus(courseLabels[i]);

           // boolean done = sharedPrefDone.getBoolean(courseLabels[i], false);
           // boolean inProgress = sharedPrefInProgress.getBoolean(courseLabels[i], false);
=======
>>>>>>> refs/remotes/origin/master
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
                    doubleClasses = DatabaseWrapper.getCoursesThatQualify(title);
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
                if (status == 0){

                    for (int j =0; j<courseLabels.length-1; j++)
                    {
<<<<<<< HEAD

                        //Checks all courses to see if they match prereq, if so checks if that course is complete
                        if (courseLabels[j].equals(iCoursePrereq) && (DatabaseWrapper.getClassStatus(courseLabels[j]) == 2)) {
=======
                        String courseString = title;
                        int preRecStatus = DatabaseWrapper.getClassStatus(courseLabels[j]);
                        boolean prereqDone = false;
                        if (preRecStatus==2){
                            prereqDone =true;
                        }
                        if (courseString.equals(iCoursePrereq)&&prereqDone){
>>>>>>> refs/remotes/origin/master
                            isCourseAvailableForRegistration = true;
                        }

                    }
                    canJump = pathwayPermission.getBoolean("permission"+title,false);

                    if (!isCourseAvailableForRegistration && (status == 0) && preReq && canJump){ isCourseAvailableForRegistration = true;}
                    if (!isCourseAvailableForRegistration && (status == 0) &&!preReq){isCourseAvailableForRegistration = true;}
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
                        status,
                        preReq,
                        new String[] {coursePrereqs[i]},
                        isCourseAvailableForRegistration,
                        i,
                        meet,
                        canJump,
                        isDoubleClass,
<<<<<<< HEAD
                        doubleClasses);

=======
                        doubleClasses,
                        courseStatus);
>>>>>>> refs/remotes/origin/master
                hasBeenAdded = true;
            }


            if(isDoublePrereq && !hasBeenAdded){
                Log.e("CCL",i+"");
                String[] iCoursePrereqArray = coursePrereqs[i].split(",");
                for (int iterator = 0; iterator<iCoursePrereqArray.length; iterator++) {
                    String iCoursePrereq = iCoursePrereqArray[iterator];
                    //These lines check if the course has a listed prerequisite, and sets the corresponding flag.
                    if (!iCoursePrereq.equals("NONE")) {
                        preReq = true;
                    }

                    //this complicated bit of logic asks if prerequisites have been done for a course that is not done nor in progress.
                    //I must mention, I don't follow the logic today, but I'm sure that it works... somehow.
                    if (status == 0) {

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
                        if (!isCourseAvailableForRegistration && preReq && canJump) {
                            isCourseAvailableForRegistration = true;
                        }
                        if (!isCourseAvailableForRegistration && !preReq) {
                            isCourseAvailableForRegistration = true;
                        }
                    }
                }

                boolean meet = false;
                if (courseLabels[i].substring(0,2).equals("GE")){meet = true;}



                //After setting all of the appropriate flags,  The course object itself is instantiated.
                course = new CourseClass(courseLabels[i].substring(0,7),
                        courseFullTitles[i],
                        status,
                        preReq,
                        new String[] {coursePrereqs[i]},
                        isCourseAvailableForRegistration,
                        i,
                        meet,
                        canJump,
                        false,
<<<<<<< HEAD
                        new String[] {""});
=======
                        new String[] {""},
                        courseStatus);
>>>>>>> refs/remotes/origin/master
                hasBeenAdded = true;
            }

            if(!hasBeenAdded) {
                String iCoursePrereq = coursePrereqs[i];
                //These lines check if the course has a listed prerequisite, and sets the corresponding flag.
                if (!iCoursePrereq.equals("NONE")){preReq = true;}

                //this complicated bit of logic asks if prerequisites have been done for a course that is not done nor in progress.
                //I must mention, I don't follow the logic today, but I'm sure that it works... somehow.
                if (status == 0){

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
                    if (!isCourseAvailableForRegistration && preReq && canJump){ isCourseAvailableForRegistration = true;}
                    if (!isCourseAvailableForRegistration &&!preReq){isCourseAvailableForRegistration = true;}
                }

                boolean meet = false;
                if (courseLabels[i].substring(0,2).equals("GE")){meet = true;}



                //After setting all of the appropriate flags,  The course object itself is instantiated.
                course = new CourseClass(courseLabels[i].substring(0,7),
                        courseFullTitles[i],
                        status,
                        preReq,
                        new String[] {coursePrereqs[i]},
                        isCourseAvailableForRegistration,
                        i,
                        meet,
                        canJump,
                        false,
<<<<<<< HEAD
                        new String[] {""});
=======
                        new String[] {""},
                        courseStatus);
>>>>>>> refs/remotes/origin/master
            }


            //This section of code adds the course to the particular container, that is, done, inprogress, etc. container
            boolean added = false;
            if (status == 2){
                courseDone.add(course);
                added = true;
            }
            if ((status == 1) && !added){
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

<<<<<<< HEAD
        int status = DatabaseWrapper.getClassStatus(courseID);

=======
        int courseStatus = DatabaseWrapper.getClassStatus(courseID);
        boolean done =false;
        if (courseStatus ==2){
            done = true;
        }

        boolean inProgress=false;
        if (courseStatus == 1){
            inProgress = true;
        }
>>>>>>> refs/remotes/origin/master
        boolean preReq = false;
        //This Loop determines what category each of the courses is in.
        //Assume the courses don't have extra prereqs.  Don't want to hurt my head here.

        //These lines check if the course has a listed prerequisite, and sets the corresponding flag.
        if (!iCoursePrereq.equals("NONE")){preReq = true;}

        //this complicated bit of logic asks if prerequisites have been done for a course that is not done nor in progress.
        //I must mention, I don't follow the logic today, but I'm sure that it works... somehow.
        if (status == 0){

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
            if (!isCourseAvailableForRegistration && preReq && canJump){ isCourseAvailableForRegistration = true;}
            if (!isCourseAvailableForRegistration &&!preReq){isCourseAvailableForRegistration = true;}
        }

        boolean meet = false;
        if (courseID.substring(0,2).equals("GE")){meet = true;}

        String[] courseInfo = DatabaseWrapper.getClassInfo(courseID);

        //After setting all of the appropriate flags,  The course object itself is instantiated.
        course = new CourseClass(courseID.substring(0,7),
                courseInfo[1],
                status,
                preReq,
                new String[] {iCoursePrereq},
                isCourseAvailableForRegistration,
                count,
                meet,
                canJump,
                false,
<<<<<<< HEAD
                new String[] {""});
=======
                new String[] {""},
                courseStatus);
>>>>>>> refs/remotes/origin/master
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

    public String getPathway() {return subPathText;}

    private String[] loadInPreReqs(String[] courses){
        String[] preReq = new String[courses.length];
<<<<<<< HEAD

=======
>>>>>>> refs/remotes/origin/master
        for ( int i = 0; i < courses.length; i++) {
            if (DatabaseWrapper.getClassPrereqs(courses[i]).length!=0)
                preReq[i] = DatabaseWrapper.getClassPrereqs(courses[i])[0];
            else
                preReq[i] = "NONE";
            Log.e("loadInPreReqs",preReq[i]);
        }


        return preReq;
    }

    private String[] loadInTitles(String[] courses){
        String[] titles = new String[courses.length];

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