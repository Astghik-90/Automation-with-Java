package OLP;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
    private String firstName;
    private String lastName;
    private String school;
    private ArrayList<String> courses = new ArrayList<>();
    private HashMap<String, Integer[]> gradesPerCourse = new HashMap<>();

    private static ArrayList<Student> allStudents = new ArrayList<>();

    public Student(String firstName, String lastName, String school, String course) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.school = school;
        courses.add(course);
        allStudents.add(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSchool() {
        return school;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public HashMap<String, Integer[]> getGradesPerCourse() {
        return gradesPerCourse;
    }

    public static ArrayList<Student> getAllStudents(){
        return allStudents;
    }

    public void setSchool(String school) {
        if(school!=null) this.school = school;
    }

    public void addCourse(String course) {
        if (course !=null) {
            courses.add(course);
            gradesPerCourse.put(course, new Integer[]{0, 0});

        }
    }

    public boolean completeCourse(String course, int score, int progress) {
        if (course != null && courses.contains(course)) {
            if (score >= 0 && progress > 0) {
                gradesPerCourse.put(course, new Integer[]{score, progress});
                return true;
            }else{
                System.out.println("wrong data");
                return false;
            }
        }else{
            System.out.println("Invalid course");
            return false;
        }
    }

    public int calculateStudentsAverageScore(){
        int totalScore = 0;
        if(gradesPerCourse.size() == 0) return 0;
        for(String course: gradesPerCourse.keySet()){
            totalScore += gradesPerCourse.get(course)[0];
        }
        return totalScore/gradesPerCourse.size();
    }

    public int calculateStudentsAverageProgress(){
        int totalScore = 0;
        if(gradesPerCourse.size() == 0) return 0;
        for(String course: gradesPerCourse.keySet()){
            totalScore += gradesPerCourse.get(course)[1];
        }
        return totalScore/gradesPerCourse.size();
    }
}
