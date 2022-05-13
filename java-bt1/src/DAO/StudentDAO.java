package DAO;

import DTO.StudentDTO;
import interfaces.IManager;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentDAO implements IManager {
    /**
     * Get all students' information
     *
     * @return List of students
     */
    private static List<StudentDTO> getListStudent() {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        String query = "select * from students";
        ResultSet resultSet = DataProvider.executeQuery(query);
        try {
            while (resultSet.next()) {
                StudentDTO studentDTO = new StudentDTO(resultSet);
                studentDTOS.add(studentDTO);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return studentDTOS;
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
    private static List<StudentDTO> getListStudentById(SortOrder sortOrder) {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        String query = "select * from students ORDER BY student_id ";
        String order = sortOrder == SortOrder.ASCENDING ? "asc" : "desc";
        ResultSet resultSet = DataProvider.executeQuery(query + order);
        try {
            while (resultSet.next()) {
                StudentDTO studentDTO = new StudentDTO(resultSet);
                studentDTOS.add(studentDTO);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return studentDTOS;
    }

    /**
     * Add new student to database
     *
     * @param studentDTO student you want to add
     * @return 1 if the query is executed successfully, 0 if the query is failed to be executed
     */
    public static int addStudent(StudentDTO studentDTO) {
        ///If we pass in the value of 0 0 0 then the value below will be set
        final String nullStringValue = "31-12-0002";
        String id = studentDTO.getId();
        String firstName = studentDTO.getFirstName();
        String lastName = studentDTO.getLastName();
        Date dateOfBirth = studentDTO.getDateOfBirth();
        String department = studentDTO.getDepartment();
        Date entranceDate = studentDTO.getEntranceDate();
        Date graduationDate = studentDTO.getGraduationDate();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String dateOfBirthString = formatter.format(dateOfBirth);
        String entranceDateString = formatter.format(entranceDate);
        String graduationDateString = formatter.format(graduationDate);

        if (graduationDateString.equals(nullStringValue)) {
            graduationDateString = "";
        }
        int credits = studentDTO.getCredits();
        BigDecimal score = studentDTO.getScore();
        String addString = "(" + "'" + id + "'" + "," + "'" + firstName + "'" + "," + "'" + lastName + "'" + "," + "'" + dateOfBirthString + "'" + "," + "'" + department + "'" + "," + "'" + entranceDateString + "'" + "," + (graduationDateString.equals("") ? "NULL" : ("'" + graduationDateString + "'")) + "," + "'" + credits + "'" + "," + "'" + score + "'" + ")";

        String query = "INSERT INTO students (student_id, first_name, last_name, dob, department, entrance_date, graduation_date, credits,score) VALUES ";
        query += addString;

        int result = DataProvider.executeNonQuery(query);
        return result;
    }


    /**
     * Delete
     *
     * @param id id of student you want to delete
     * @return 1 if the query is executed successfully, 0 if the query is failed to be executed
     */
    @Override
    public int delete(String id) {
        String query = "delete from students where student_id = '" + id + "'";
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
    @Override
    public int updateId(String oldId, String newId) {
        String query = "UPDATE students SET student_id = '" + newId + "' WHERE student_id = '" + oldId + "'";
        int result = DataProvider.executeNonQuery(query);
        return result;
    }

    /**
     * Print all student's information in the database with default order
     *
     * This equal to SELECT * FROM students in the database
     */
    @Override
    public void getAll() {
        List<StudentDTO> studentDTOS = getListStudent();
        for (StudentDTO studentDTO : studentDTOS) {
            studentDTO.printInformation();
        }
    }

    /**
     * Print all student's information in the database with default order
     *
     * @param order order you want the returned list will be.
     * This equal to SELECT * FROM students ORDER BY student_id @order in the database
     */
    @Override
    public void getAllOrderById(SortOrder order) {
        List<StudentDTO> studentDTOS = getListStudentById(order);
        for (StudentDTO studentDTO : studentDTOS) {
            studentDTO.printInformation();
        }
    }
}
