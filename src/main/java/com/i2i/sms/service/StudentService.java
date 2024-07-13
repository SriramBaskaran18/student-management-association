package com.i2i.sms.service;

import java.util.List;
import java.util.UUID;

import com.i2i.sms.dto.RequestStudentDto;
import com.i2i.sms.dto.RequestUpdateStudentDto;
import com.i2i.sms.dto.ResponseStudentDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.exception.StudentManagementException;

public interface StudentService {

    /**
     * <p>
     * Add student information to the database.
     * </p>
     *
     * @param requestStudentDto {@link RequestStudentDto}
     * @return The Added student object or null if any exception occurs.
     * @throws StudentManagementException if an error occurs during student addition.
     */
    ResponseStudentDto addStudent(RequestStudentDto requestStudentDto);

    /**
     * <p>
     * update student information in the database by their id with the requested values.
     * </p>
     * @param studentId id of the student to update for.
     * @param requestUpdateStudentDto  {@link RequestUpdateStudentDto}
     * @return The updated student object or null if any exception occurs.
     * @throws StudentManagementException if an error occurs during student addition.
     */
    ResponseStudentDto updateStudent(UUID studentId, RequestUpdateStudentDto requestUpdateStudentDto);

    /**
     * <p>
     * Searches for a student in the database by their Id.
     * </p>
     *
     * @param studentId ID of the student to search for.
     * @return The Student object corresponding to the given id, or null if not found.
     * @throws StudentManagementException if an error occurs while fetching the student.
     */
    ResponseStudentDto getStudentById(UUID studentId);

    /**
     * <p>
     * Retrieves a list of all students from the database.
     * </p>
     *
     * @return a list of all students in the database.
     * @throws StudentManagementException if an error occurs while fetching the students.
     */
    List<StudentDto> getAllStudents();

    /**
     * <p>
     * Deletes a student from the database by their ID.
     * </p>
     *
     * @param studentId the ID of the student to be deleted.
     * @return true if the student was found and deleted, false if the student was not found.
     * @throws StudentManagementException if an error occurs while deleting the student.
     */
    boolean deleteStudentById(UUID studentId);

}
