import DAO.StudentDAO;
import DTO.StudentDTO;

import javax.swing.SortOrder;
import java.util.*;

public class Main {
    private static int result;


    public static void main(String[] args) {
        List<StudentDTO> studentDTOS;
        StudentDAO studentDAO = new StudentDAO();
        int choice;
        Scanner scanner = new Scanner(System.in);
        boolean isBreak = false;
        while (!isBreak) {
            System.out.println("");
            System.out.println("Input your choice with value below, other value will exit the program");
            System.out.println("1. Get students information");
            System.out.println("2. Get students information by id");
            System.out.println("3. Add new student");
            System.out.println("4. Update student' id");
            System.out.println("5. Delete student");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    studentDAO.getAll();
                    break;
                case 2:
                    System.out.println("Type in the order you want to get, 1 to get ASCENDING order, 0 to get DESCENDING order");
                    int orderChoice = scanner.nextInt();
                    while (orderChoice != 1 && orderChoice != 0) {
                        System.out.println("Invalid value, only 1 or 0 is accepted");
                        System.out.println("Type in the order you want to get, 1 to get ASCENDING order, 0 to get DESCENDING order");
                        orderChoice = scanner.nextInt();
                    }
                    SortOrder sortOrder = orderChoice == 1 ? SortOrder.ASCENDING : SortOrder.DESCENDING;
                    studentDAO.getAllOrderById(sortOrder);
                    break;
                case 3:
                    StudentDTO newStudentDTO = StudentDTO.inputNewStudentInformation();
                    result = StudentDAO.addStudent(newStudentDTO);
                    if (result == 1) {
                        System.out.println("Student added");
                    } else {
                        System.err.println("Error adding new student");
                    }
                    break;
                case 4:
                    System.out.println("Type in the student id you want to change");
                    String oldId = scanner.next();
                    System.out.println("Type in the new student id");
                    String newId = scanner.next();
                    result = studentDAO.updateId(oldId, newId);
                    if (result == 1) {
                        System.out.println("Student information updated");
                    } else {
                        System.err.println("Error updating new information");
                    }
                    break;
                case 5:
                    System.out.println("Type in the student id you want to delete");
                    String id = scanner.next();
                    result = studentDAO.delete(id);
                    if (result == 1) {
                        System.out.println("Student deleted");
                    } else {
                        System.err.println("Error deleting new student");
                    }
                    break;
                default:
                    isBreak = true;
                    System.out.println("Program exited");
                    break;
            }
        }

    }
}