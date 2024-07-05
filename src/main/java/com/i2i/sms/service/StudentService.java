package com.i2i.sms.service;

import com.i2i.sms.dto.RequestStudentDto;
import com.i2i.sms.dto.ResponseStudentDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.exception.StudentManagementException;

import java.util.List;

public interface StudentService {

    /**
     * <p>
     * Add student information to the database.
     * </p>
     *
     * @param requestStudentDto requested dto object from the user to add.
     * @return The Added student object or null if any exception occurs.
     * @throws StudentManagementException if an error occurs during student addition.
     */
    ResponseStudentDto addStudent(RequestStudentDto requestStudentDto) throws StudentManagementException;

    ResponseStudentDto updateStudent(int studentId, RequestStudentDto requestStudentDto) throws StudentManagementException;

    /**
     * <p>
     * Searches for a student in the database by their Id.
     * </p>
     *
     * @param studentId ID of the student to search for.
     * @return The Student object corresponding to the given id, or null if not found.
     * @throws StudentManagementException if an error occurs while fetching the student.
     */
    ResponseStudentDto getStudentById(int studentId) throws StudentManagementException;

    /**
     * <p>
     * Retrieves a list of all students from the database.
     * </p>
     *
     * @return a list of all students in the database.
     * @throws StudentManagementException if an error occurs while fetching the students.
     */
    List<StudentDto> getAllStudents() throws StudentManagementException;

    /**
     * <p>
     * Deletes a student from the database by their ID.
     * </p>
     *
     * @param studentId the ID of the student to be deleted.
     * @return true if the student was found and deleted, false if the student was not found.
     * @throws StudentManagementException if an error occurs while deleting the student.
     */
    boolean deleteStudentById(int studentId) throws StudentManagementException;

}
