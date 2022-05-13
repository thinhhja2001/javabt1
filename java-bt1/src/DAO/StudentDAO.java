package DAO;

import DTO.Student;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentDAO {
    /**
     * Get all students' information
     *
     * @return List of students
     */
    public static List<Student> getListStudent() {
        List<Student> students = new ArrayList<>();
        String query = "select * from students";
        ResultSet resultSet = DataProvider.executeQuery(query);
        try {
            while (resultSet.next()) {
                Student student = new Student(resultSet);
                students.add(student);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return students;
    }

    /**
     * Get list student by id
     *
     * @param sortOrder SortOrder.ASCENDING to get the list with ascending order
     *                  <p>
     *                  SortOrder.DESCENDING to get the list with descending order
     *                  /
     * @return List of students ordered by [sortOrder]
     */
    public static List<Student> getListStudentById(SortOrder sortOrder) {
        List<Student> students = new ArrayList<>();
        String query = "select * from students ORDER BY student_id ";
        String order = sortOrder == SortOrder.ASCENDING ? "asc" : "desc";
        ResultSet resultSet = DataProvider.executeQuery(query + order);
        try {
            while (resultSet.next()) {
                Student student = new Student(resultSet);
                students.add(student);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return students;
    }

    /**
     * Add new student to database
     *
     * @param student student you want to add
     * @return 1 if the query is executed successfully, 0 if the query is failed to be executed
     */
    public static int addStudent(Student student) {
        final String nullStringValue = "31-12-0002";
        String id = student.getId();
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        Date dateOfBirth = student.getDateOfBirth();
        String department = student.getDepartment();
        Date entranceDate = student.getEntranceDate();
        Date graduationDate = student.getGraduationDate();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String dateOfBirthString = formatter.format(dateOfBirth);
        String entranceDateString = formatter.format(entranceDate);
        String graduationDateString = formatter.format(graduationDate);

        if (graduationDateString.equals(nullStringValue)) {
            graduationDateString = "";
        }
        System.out.println(graduationDateString);
        int credits = student.getCredits();
        BigDecimal score = student.getScore();
        String addString = "(" + "'" + id + "'" + "," + "'" + firstName + "'" + "," + "'" + lastName + "'" + "," + "'" + dateOfBirthString + "'" + "," + "'" + department + "'" + "," + "'" + entranceDateString + "'" + "," + (graduationDateString.equals("") ? "NULL" : ("'" + graduationDateString + "'")) + "," + "'" + credits + "'" + "," + "'" + score + "'" + ")";

        String query = "INSERT INTO students (student_id, first_name, last_name, dob, department, entrance_date, graduation_date, credits,score) VALUES ";
        query += addString;

        int result = DataProvider.executeNonQuery(query);
        return result;
    }

    /**
     * Update id of student
     *
     * @param oldId id of student you want to change
     * @param newId new id of student
     * @return 1 if the query is executed successfully, 0 if the query is failed to be executed
     */
    public static int updateIdOfStudent(String oldId, String newId) {
        String query = "UPDATE students SET student_id = '" + newId + "' WHERE student_id = '" + oldId + "'";
        int result = DataProvider.executeNonQuery(query);
        return result;
    }

    /**
     * Update id of student
     *
     * @param id id of student you want to delete
     * @return 1 if the query is executed successfully, 0 if the query is failed to be executed
     */
    public static int deleteStudent(String id) {
        String query = "delete from students where student_id = '" + id + "'";
        int result = DataProvider.executeNonQuery(query);
        return result;
    }


}
