package OLP;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StudentTest {

    @BeforeTest
    public void studentCreation(){
        Student student_1 = new Student("Astghik", "Ayvazyan", "School1", "Course1");
        Student student_2 = new Student("Armen", "Hakobyan", "School2", "Course3");
        student_1.addCourse("Course2");
        student_1.addCourse("Course3");
        student_2.addCourse("Course4");
    }

    @DataProvider(name="studentsData")
    public Object [][] studentsData(){
        Object [][] data = new Object[2][5];
        data[0][0] = "Astghik"; data[0][1] = "Course1"; data[0][2]= 60; data[0][3]= 50; data[0][4] = true;
        data[1][0] = "Armen"; data[1][1] = "Course5"; data[1][2]= 10; data[1][3]= 20; data[1][4] = false;

        return data;
    }

    @Test(priority = 2)
    public void testCalculateStudentsAverageScore() {
        Student student = Student.getAllStudents().get(0);
        assertEquals(student.calculateStudentsAverageScore(), 60);
    }

    @Test(priority = 3)
    public void testCalculateStudentsAverageProgress() {
        Student student = Student.getAllStudents().get(0);
        System.out.println(student.getFirstName());
        assertEquals(student.calculateStudentsAverageProgress(), 50);
    }

    @Test(dataProvider = "studentsData", priority = 1)
    public void testCompleteCourse(String studentName, String course, int score, int progress, boolean isCompleted) {
        Student student = null;
        for(Student st: Student.getAllStudents()){
            if(st.getFirstName().equals(studentName)){
                student = st;
            }
        }
        assertEquals(student.completeCourse(course, score, progress), isCompleted);
    }
}