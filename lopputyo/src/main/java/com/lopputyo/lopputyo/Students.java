package com.lopputyo.lopputyo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Students {
    public String fname;
    public String lname;
    public String address;
    public String studentID;


    public Students() {
      /*  this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.studentID = studentID;*/
    }

//kommentit toiminnallisuudesta löytyy coursesta


//Lisätään opiskelija tietokantaan ID, etunimi, Sukunimi, Osoite
    public String addStudents(String studentID, String fname, String lname, String address, File opiskelijaFile) throws IOException {
        FileWriter fw = new FileWriter(opiskelijaFile, true); //luodaan filewriter, jolla kirjoitetaan tiedostoon paramentrina annetut tiedot (id, etun, snimi, osoite)
        fw.write(studentID + " " + fname + " " + lname + " " + address + System.lineSeparator());
        fw.close();
        return "Student added successfully";
    }

//Haetaan opiskelija ID:n perusteella
    public String getStudentById(String studentID, File opiskelijaFile) throws FileNotFoundException {
       //luodaan scanneri lukemaan opiskelijoita sisältävää tiedostoa
        //opiskelijat sisältävä studentsFile vastaa täällä opiskelijaFile
        Scanner reader = new Scanner(opiskelijaFile);
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] tokens = line.split(" ");
            System.out.println(tokens[0] + " " + tokens[1]);
            if(studentID.equals(tokens[0])){
                System.out.println(line);
                reader.close();
                return "<h3>" + line + "</h3>";
            }
        }
        reader.close();
        return "None student found by given name.";
    }
}