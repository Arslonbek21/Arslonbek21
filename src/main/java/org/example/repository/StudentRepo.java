package org.example.repository;

import org.example.employee.Student;

import java.io.*;
import java.util.List;
import java.util.UUID;

public class StudentRepo implements Repository<Student>{

    List<Student> students;
    private static StudentRepo studentRepo;

    private StudentRepo(List<Student> students){
        this.students = students;
    }
    public static StudentRepo getInstance(){
        if (studentRepo == null){
            studentRepo = new StudentRepo(loading());
        }
        return studentRepo;
    }

    private static final String PATH = "src/main/java/org/example/DB/students.txt";

    @SuppressWarnings("unchecked")
    private static List<Student> loading(){
        try (
                InputStream inputStream = new FileInputStream(PATH);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)
        ){
            return (List<Student>)objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void uploading(){
        try(
                OutputStream outputStream = new FileOutputStream(PATH);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)
                ) {
            objectOutputStream.writeObject(students);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void create(Student student) {
        students.add(student);
        uploading();
    }

    @Override
    public Student getById(UUID id) {
        for (Student student : students) {
            if (student.getId().equals(id)){
                return student;
            }
        }
        return null;
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void deleteById(UUID id) {

    }
}
