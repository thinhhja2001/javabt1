package DTO;

import interfaces.IHuman;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class StudentDTO extends HumanDTO implements IHuman {
    private String id;

    private String department;
    private Date entranceDate;
    private Date graduationDate;
    private int credits;
    private BigDecimal score;

    public StudentDTO(String id, String firstName, String lastName, Date dateOfBirth, String department, Date entranceDate, Date graduationDate, int credits, BigDecimal score) {
        this.id = id;
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
        this.department = department;
        this.entranceDate = entranceDate;
        this.graduationDate = graduationDate;
        this.credits = credits;
        this.score = score;

    }

    public StudentDTO(ResultSet resultSet) throws SQLException {
        this.id = (String) resultSet.getObject(1);

        setFirstName((String) resultSet.getObject(2));
        setLastName((String) resultSet.getObject(3));
        setDateOfBirth((Date) resultSet.getObject(4));

        this.department = (String) resultSet.getObject(5);
        this.entranceDate = (Date) resultSet.getObject(6);
        this.graduationDate = (Date) resultSet.getObject(7);
        this.credits = (int) resultSet.getObject(8);
        this.score = (BigDecimal) resultSet.getObject(9);
    }

    /**
     * Input new student information
     * /
     */
    public static StudentDTO inputNewStudentInformation() {
        int day;
        int month;
        int year;
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");


        System.out.println("Type in id: ");
        String id = scanner.next();
        System.out.println("Type in first name: ");
        String firstName = scanner.next();
        System.out.println("Type in last name: ");

        String lastName = scanner.next();
        System.out.println("Type in department: ");

        String department = scanner.next();
        System.out.println("Type in birthday: day, month, year respectively");
        System.out.println("Type in day");
        day = scanner.nextInt();
        System.out.println("Type in month");
        month = scanner.nextInt();
        System.out.println("Type in year");
        year = scanner.nextInt();
        Date dateOfBirth = new GregorianCalendar(year, month, day).getTime();

        System.out.println("Type in entrance day: day, month, year respectively");
        System.out.println("Type in day");
        day = scanner.nextInt();
        System.out.println("Type in month");
        month = scanner.nextInt();
        System.out.println("Type in year");
        year = scanner.nextInt();
        Date entranceDate = new GregorianCalendar(year, month, day).getTime();

        System.out.println("Type in graduation day\nType in 0 0 0 to leave it blank");
        System.out.println("Type in day");
        day = scanner.nextInt();
        System.out.println("Type in month");
        month = scanner.nextInt();
        System.out.println("Type in year");
        year = scanner.nextInt();

        Date graduationDate = new GregorianCalendar(year, month, day).getTime();
        System.out.println("Type in credit: ");

        int credits = scanner.nextInt();

        System.out.println("Type in score: ");
        String scoreInString = scanner.next();
        BigDecimal score = new BigDecimal(scoreInString);
        StudentDTO studentDTO = new StudentDTO(id, firstName, lastName, dateOfBirth, department, entranceDate, graduationDate, credits, score);

        System.out.println(studentDTO.graduationDate);
        return studentDTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(Date entranceDate) {
        this.entranceDate = entranceDate;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public BigDecimal getScore() {
        return score;
    }

    public BigDecimal setScore(BigDecimal score) {
        return score;
    }

    public void setScore(double score) {
        this.score = BigDecimal.valueOf(score);
    }


    /**
     * Print all information of the student
     */
    @Override
    public void printInformation() {
        System.out.println(id + " " + getFirstName() + " " + getLastName() + " " + getDateOfBirth() + " " + department + " " + entranceDate + " " + graduationDate + " " + credits + " " + score);
    }
}
