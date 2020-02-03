package app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class App {

    public static ArrayList<Nomination> loadNominations() {
        try {
            FileInputStream fileStream = new FileInputStream("nominations.ser");
            ObjectInputStream os = new ObjectInputStream(fileStream);
            ArrayList<Nomination> nominations = (ArrayList<Nomination>) os.readObject();
            os.close();
            return nominations;
        } catch (IOException | ClassNotFoundException ex) {
            return new ArrayList<Nomination>();
        }
    }

    private static void saveNominations(ArrayList<Nomination> nominations) {
        try {
            FileOutputStream fileStream = new FileOutputStream("nominations.ser");
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(nominations);
            os.close();
        } catch (IOException ex) {
            System.out.println("Failed to update nomination.");
        }
    }

    private static void getTeacherInfo(Dictionary<String, String> teacherInfo, Scanner scanner) {
        System.out.println("Name ?: ");

        String teacherName = scanner.nextLine();
        System.out.println("\nEmail ?: ");
        String teacherEmail = scanner.nextLine();
        System.out.println("\nSchool District/Affiliation ?: ");
        String schoolDistrict = scanner.nextLine();
        System.out.println("\nPosition ?: ");
        String position = scanner.nextLine();
        System.out.println(
                " \nRelationship to Nominee ? :\n (For how long and in what capacity do you know the nominee)");
        String relationToStudent = scanner.nextLine();

        teacherInfo.put("name", teacherName);
        teacherInfo.put("email", teacherEmail);
        teacherInfo.put("school-district", schoolDistrict);
        teacherInfo.put("position", position);
        teacherInfo.put("relation", relationToStudent);
    }

    private static void getStudentInfo(Dictionary<String, String> studentInfo, Scanner scanner) {
        System.out.println("\nName ?: ");
        String studentName = scanner.nextLine();
        System.out.println("\nAge ?: ");
        String studentAge = scanner.nextLine();
        System.out.println("\nContact Info ?: ");
        String studentContactInfo = scanner.nextLine();
        System.out.println("\nExpected Graduation Date ?: ");
        String gradDate = scanner.nextLine();
        System.out.println(
                "\nBase Camp Coding Academy looks for students with the aptitude and\n dedication to succeed in this program. Please explain why you believe\n this student will make an excellent candidate ");
        System.out.println(
                " \nAptitude ? :\n (Do you have any experiences when this student has demonstrated a\n strong ability to think logically and/or strategically ?)");
        String aptitude = scanner.nextLine();
        System.out.println(
                " \nPerseverance? :\n (Is there any evidence of the applicant being\n engaged in something they are passionate about ?)");
        String perseverance = scanner.nextLine();
        System.out.println(
                " \nDedication ? :\n (Do you think the applicant would be able to reliably\n attend the program five days a week in Water Valley ?)");
        String dedication = scanner.nextLine();
        System.out.println(" \nWork Ethic / Heart ? :\n (Why does this student deserve a position at Base Camp ?)");
        String workEthic = scanner.nextLine();

        studentInfo.put("name", studentName);
        studentInfo.put("age", studentAge);
        studentInfo.put("contact-info", studentContactInfo);
        studentInfo.put("graduation-date", gradDate);
        studentInfo.put("aptitude", aptitude);
        studentInfo.put("perseverance", perseverance);
        studentInfo.put("dedication", dedication);
        studentInfo.put("workEthic", workEthic);
    }

    private static ArrayList<Nomination> createNomination(ArrayList<Nomination> nominations,
            Dictionary<String, String> teacherInfo, Dictionary<String, String> studentInfo) {
        Nomination nomination = new Nomination(teacherInfo, studentInfo);
        nominations.add(nomination);
        return nominations;
    }

    public static void main(String[] args) {

        ArrayList<Nomination> nominations = loadNominations();

        Dictionary<String, String> teacherInfo = new Hashtable<>();
        Dictionary<String, String> studentInfo = new Hashtable<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Nominate a student for BCCA ! \n");

        System.out.println("Nominator Information: \n");
        getTeacherInfo(teacherInfo, scanner);

        System.out.println("\nNominee Information: \n");
        getStudentInfo(studentInfo, scanner);

        createNomination(nominations, teacherInfo, studentInfo);

        saveNominations(nominations);

    }

}