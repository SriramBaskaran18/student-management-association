package com.i2i.sms.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.i2i.sms.common.RoleEnum;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Address;
import com.i2i.sms.models.Role;
import com.i2i.sms.models.Student;
import com.i2i.sms.service.RoleService; 
import com.i2i.sms.service.StudentService;
import com.i2i.sms.utils.DateUtils;
import com.i2i.sms.utils.StringValidationUtil;

public class StudentController {
    public static Scanner scanner = new Scanner(System.in);
    public StudentService studentService = new StudentService();
    public RoleService roleService = new RoleService();
 
    /**
     * <p>
     * Gather information from the user and validate like Name contains only Alphabets,
     * D.O.B in the yyyy-MM-dd format, standard, section and create a student.
     * </p>
     */
    public void createStudent() {
        try {
            String name;
            while (true) {
                System.out.println("Enter Your Name : ");
                name = scanner.next();
                if (!StringValidationUtil.isValidString(name)) {
                    System.out.println("Numbers are not allowed, Entered value is in invalid format");
                    continue;
                }
                break;
            }
            String dob;
            while (true) {
                System.out.println("Enter Your D.O.B Like yyyy-MM-dd : ");
                dob = scanner.next();
                if (!DateUtils.isValidDate(dob)) {
                    System.out.println("Entered D.O.B is in invalid format");
                    continue;
                }
                break;
            }
            System.out.println("Enter the Standard : ");
            int std = scanner.nextInt();
            String section;
            while (true) {
                System.out.println("Enter the Section : ");
                section = scanner.next();
                if (!StringValidationUtil.isValidString(section)) {
                    System.out.println("Numbers are not allowed, Entered value is in invalid format");
                    continue;
                }
                break;
            }
            System.out.println("Enter Your Address : \n");
            System.out.println("Enter Your Door NO. :");
            String doorNumber = scanner.next();
            scanner.nextLine();
            System.out.println("Enter Your Street :");
            String street = scanner.nextLine();
            System.out.println("Enter Your City :");
            String city = scanner.nextLine();
            System.out.println("Enter Your State :");
            String state = scanner.nextLine(); 
            System.out.println("Enter Yout Zipcode :");
            int zipcode = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Your Mobile Number :");
            String mobileNumber = scanner.nextLine();
            Address address = new Address();
            address.setDoorNumber(doorNumber);
            address.setStreet(street);
            address.setCity(city);
            address.setState(state);
            address.setZipcode(zipcode);
            address.setMobileNumber(mobileNumber);
            Set<Role> roles = new HashSet<Role>();
            roles = addRole();
            Student insertedStudent = studentService.addStudent(name, dob, std, section, address, roles);
            displayStudent(insertedStudent);
            System.out.println(insertedStudent.getGrade());
            System.out.println("**Student Data Added to the Database**");
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    } 

    /**
     * <p>
     * Allow Student to select roles from a predefined list and adds the selected roles to a set.
     * The student can select multiple roles and the method ensures that each role is picked only once.
     * The method continues to prompt for role selection until the user chooses to exit.
     * </p>
     * @return a set of roles selected by the user.
     */
    public Set<Role> addRole() {
        List<Integer> picks = new ArrayList<Integer>();
        Set<Role> pickedRoles = new HashSet<Role>();
        try {
            while (true) {
                System.out.println("Choose The Role You Want");
                System.out.println("Pick 1 For Class Representative");
                System.out.println("Pick 2 For Board Incharge");
                System.out.println("Pick 3 For Cabinet Incharge");
                int pick = scanner.nextInt();
                if (!picks.contains(pick)) {
                    switch (pick) {
                        case 1:
                            pickedRoles.add(roleService.getRoleIfRoleExists(RoleEnum.CLASS_REPRESENTATIVE.getRole()));
                            //"Class Representative"
                            break;
                        case 2:
                            pickedRoles.add(roleService.getRoleIfRoleExists(RoleEnum.BOARD_INCHARGE.getRole()));
                            //"Board Incharge"
                            break;
                        case 3:
                            pickedRoles.add(roleService.getRoleIfRoleExists(RoleEnum.CABINET_INCHARGE.getRole()));
                            //"Cabinet Incharge"
                            break;
                        default:
                            System.out.println("\n____Invalid Pick____");
                    }
                    picks.add(pick);
                }
                else {
                    System.out.println("You Already Picked That Role");
                }
                System.out.println("1--> Continue with another role \n press any number and Enter--> Exit....");
                int option = scanner.nextInt();
                if (option == 1) {
                    continue;
                } 
                break;
            } 
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return pickedRoles;
    }

    /**
     * <p>
     * Retrieves All the student details and displays the student information to the user.
     * </p>
     */
    public void getAllStudents() {
        try {
            for (Student student : studentService.getAllStudents()) {
                displayStudent(student);
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the corresponding student details and displays the student information to the user.
     */
    public void getStudentById() {
        try {
            System.out.println("Enter StudentId to Search Specific Student : ");
            int searchId = scanner.nextInt();
            Student student = studentService.getStudentById(searchId);
            displayStudent(student);
            System.out.println(student.getGrade()+ "\n");
            System.out.println(student.getAddress()+ "\n");
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    } 

    /**
     * <p>
     * Prompts User to enter the id of the student to be removed, 
     * it will display a success message if the specified student removed or 
     * else it will display a warning message with the corresponding student id.
     * </p> 
     */
    public void deleteStudentById() {
        try {
            System.out.println("Enter Student ID to Delete Specific Student :");
            int studentId = scanner.nextInt();
            boolean isStudentDelete = studentService.deleteStudentById(studentId);
            if (isStudentDelete) {
                System.out.println("**Student Deleted successfully**");
            } else {
                System.out.println("**Student Id:"+ studentId +" not found to delete**");
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * <p>
     * Display the student data. 
     * </p>
     * @param student 
     *           Student will have Name, D.O.B in the yyyy-MM-dd format, Age, and Grade.
     */
    public void displayStudent(Student student) {
        System.out.println("\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n");
        System.out.println(student);
        System.out.println("\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n");
    }

}