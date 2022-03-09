package com.lopputyo.lopputyo;


import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.io.*;
import java.util.Scanner;

@RestController
public class ProjectController {


    Course C = new Course();
    Students S = new Students();
    File courseFile = new File("courseinfo.txt");
    File dumpFile = new File("dumppaus.txt");
    File studentsFile = new File("studentsinto.txt");

    //txt tiedostoihin tallennetaan luodut tiedot

//Kurssijutut

    @GetMapping("allcourses")
    public String kaikKurssit() throws IOException {
        return C.getAllCourses();
    }

    @PostMapping("addcourse")
    public String addKurssi(@RequestParam String courseID, @RequestParam String coursename, @RequestParam String teacher) throws IOException {
        return C.addCourse(courseID, coursename, teacher, courseFile);

    }

    @GetMapping("coursesbyid")
    public String coursesbyid(@RequestParam String courseID) throws FileNotFoundException {
        return C.getCourseById(courseID, courseFile);
    }

    @GetMapping("coursesbyname")
    public String coursebyNimi(@RequestParam String coursename) throws FileNotFoundException {
        return C.getCourseByName(coursename, courseFile);
    }

    @GetMapping("coursesbyteacher")
    public String coursebyOpe(@RequestParam String teacher) throws IOException {
        return C.getCoursesByTeacher(teacher, courseFile, dumpFile);
    }

    @PostMapping("addStudents")
    public String lisaaOpiskelija(@RequestParam String studentID, @RequestParam String fname, @RequestParam String lname, @RequestParam String address) throws IOException {
        return S.addStudents(studentID, fname, lname, address, studentsFile);
    }

    @GetMapping("studentbyid")
    public String opiskelijaIDlla(@RequestParam String studentID) throws FileNotFoundException {
        return S.getStudentById(studentID, studentsFile);
    }
}
