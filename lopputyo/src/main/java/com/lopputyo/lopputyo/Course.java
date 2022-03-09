package com.lopputyo.lopputyo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Course {
    public String coursename;
    public String teacher;
    public String courseID;


    public Course() {
      /*  this.coursename = coursename;
        this.courseID = courseID;
        this.teacher = teacher;*/
    }
    //haetaan kaikki kurssit
    public String getAllCourses() throws IOException {
        String contentToShow = Files.readString(Path.of("courseinfo.txt"));
        if(contentToShow.equals("")){
            return "There are no courses inserted in the system. Please add course first.";
        }else {
            return "<h3> " + contentToShow.replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</h3>";
        }
    }
    //Haetaan kurssia nimen perusteella
    public String getCourseByName(String coursename, File kurssiFile) throws FileNotFoundException {
       //Luodaan scanneri lukemaan kurssit sisältävää tiedostoa, controllerista on metodin kutsussa välitetty parametrina
        //kurssit sisältävä courseFile, jota vastaa tässä kurssiFile mutta nyt verrataan toista tokenia (kurssin nimeä) parametrina annettuun
        //kurssinimeen ja palautetaan osuma käyttäjälle ruudulle. Jos osumaa ei tule, palautetaan siitä tieto.
        Scanner reader = new Scanner(kurssiFile);
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] tokens = line.split(" "); //Käytetään split() -funktiota erottelemaan kurssitiedoston tiedot erillisiksi "tokeneiksi"
            System.out.println(tokens[0] + " " + tokens[1] + " " + tokens[2]);
            if(coursename.equals(tokens[1])){ //verrataan osuuko kurssin nimi
                System.out.println(line);
                reader.close();
                return "<h3>" + line + "</h3>";
            }
        }
        reader.close(); //jos osumaa ei tule:
        return "No courses found by given course name.";
    }

    //Haetaan kurssin ID:n perusteella
    public String getCourseById(String courseID, File kurssiFile) throws FileNotFoundException {
        Scanner reader = new Scanner(kurssiFile);
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] tokens = line.split(" ");
            System.out.println(tokens[0] + " " + tokens[1]);
            if(courseID.equals(tokens[0])){
                System.out.println(line);
                reader.close();
                return "<h3>" + line + "</h3>";
            }
        }
        reader.close();
        return "None course found by given ID.";
    }

    public String getCoursesByTeacher(String teacher, File kurssiFile, File dumppFile) throws IOException {
        //luodaan scanneri lukemaan kurssitiedostoa. Sen lisäksi luodaan PrintWriter, jota käytetään tiedostoon
        //kirjoittamiseen.
        Scanner reader = new Scanner(kurssiFile);
        PrintWriter dumpWriter = new PrintWriter(dumppFile); //kun opettajaa haetaan, niin tieto menee dumppi- tiedostoon
        dumpWriter.print("");
        //while-loopissa vertaillaan taas tokeneiksi hajotettua kurssitiedostoa annettuun parametriin
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] tokens = line.split(" ");
            //System.out.println(tokens[0] + " " + tokens[1] + " " + tokens[2]);
             if (teacher.equals(tokens[2])) { //katsotaan löytyykö nimellä, tässä pelkästään etunimi
                dumpWriter.print(line + System.lineSeparator());
                System.out.println(line);

            }
        }
        reader.close();
        dumpWriter.close();
        String contentToShow = Files.readString(Path.of("dumppaus.txt"));
        if (contentToShow.equals("")) {  //Jos ei löydy, palautetaan käyttäjälle tieto
            return "Teacher " + teacher + " doesn't have any courses. Try another teacher.";
            //Tulostuu: Teacher xxx doesn't have any....
        } else {
            return "<h3> " + contentToShow.replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</h3>";
        }
    }

    //lisätään kurssi
            public String addCourse (String courseID, String coursename, String teacher, File kurssiFile) throws
            IOException {
                //Luodaan FileWriter, jolla kirjoitetaan kurssitiedostoon parametrina annetut tiedot (id, kurssin nimi, opettaja)
                FileWriter fw = new FileWriter(kurssiFile, true);
                fw.write(courseID + " " + coursename + " " + teacher + System.lineSeparator());
                fw.close();

                return "Course added succesfully!";
            }
        }

