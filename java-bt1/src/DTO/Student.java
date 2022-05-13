package DTO;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String department;
    private Date entranceDate;
    private Date graduationDate;
    private int credits;
    private BigDecimal score;

    public Student(String id, String firstName, String lastName, Date dateOfBirth, String department, Date entranceDate, Date graduationDate, int credits, BigDecimal score) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.entranceDate = entranceDate;
        this.graduationDate = graduationDate;
        this.credits = credits;
        this.score = score;

    }

    public Student(ResultSet resultSet) throws SQLException {
        this.id = (String) resultSet.getObject(1);
        this.firstName = (String) resultSet.getObject(2);
        this.lastName = (String) resultSet.getObject(3);
        this.dateOfBirth = (Date) resultSet.getObject(4);
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
    public static Student inputNewStudentInformation() {
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
        Student student = new Student(id, firstName, lastName, dateOfBirth, department, entranceDate, graduationDate, credits, score);

        System.out.println(student.graduationDate);
        return student;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    @Override
    public String toString() {
        return id + " " + firstName + " " + lastName + " " + dateOfBirth + " " + department + " " + entranceDate + " " + graduationDate + " " + credits + " " + score;
    }


}
