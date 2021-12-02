/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Aitor Ruiz de Gauna
 */
/**
 * Entity that contains the info of a subject.
 */
@Entity
@Table(name="subject",schema="maz_solutions")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    //Subject identifier.
    private Long idSubject;
    //Name of the subject.
    private String name;
    //Password to register in the subject
    private String password;
    //TeacherCourse where the subject appears
    @ManyToMany(mappedBy="subjects", fetch=FetchType.EAGER)
    private Set<TeacherCourse>teacherCourses;
    //Collection of exams that the subject has had
    @OneToMany(cascade=ALL,mappedBy="subject")
    private Set<Exam>exams;
    //Collection of courses where the subject is teached
    @ManyToMany(mappedBy="subjects",fetch=FetchType.EAGER)
    private Set<Course>courses;
    /**
    * Method that return the identifier of the subject.
    * @return idSubject
    */
    public Long getIdSubject() {
        return idSubject;
    }
    /**
    * Method that set the value of the identifier of the subject.
    * @param idSubject
    */
    public void setIdSubject(Long idSubject) {
        this.idSubject = idSubject;
    }
    /**
    * Method that return the name of the subject.
    * @return name
    */
    public String getName() {
        return name;
    }
    /**
    * Method that set the value of the name of the subject.
    * @param name
    */
    public void setName(String name) {
        this.name = name;
    }
    /**
    * Method that return the password of the subject.
    * @return password
    */
    public String getPassword() {
        return password;
    }
    /**
    * Method that set the value of the password of the subject.
    * @param password
    */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
    * Method that return the TeacherCourse of the subject.
    * @return teacherCourse
    */
    public Set<TeacherCourse> getTeacherCourses() {
        return teacherCourses;
    }
    /**
    * Method that set the value of the TeacherCourse of the subject.
    * @param teacherCourses
    */
    public void setTeacherCourses(Set<TeacherCourse> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }
    /**
    * Method that return the Exams of the subject.
    * @return exams
    */
    public Set<Exam> getExams() {
        return exams;
    }
    /**
     * Method that set the value of the exams of the subject.
     * @param exams 
     */
    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }
    /**
     * Method that return the courses where the subject is teached.
     * @return courses
     */
    public Set<Course> getCourses() {
        return courses;
    }
    /**
     * Method that set the value of the courses where the subject is teached.
     * @param courses 
     */
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
    
    /**
     * Integer representation for Subject instance.
     * @return 
     */
    @Override    
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.idSubject);
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.password);
        hash = 31 * hash + Objects.hashCode(this.teacherCourses);
        hash = 31 * hash + Objects.hashCode(this.exams);
        hash = 31 * hash + Objects.hashCode(this.courses);
        return hash;
    }  

    /**
     * 
     * Method that compares if two objects of Subjects are equals.
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.idSubject == null && other.idSubject != null) || (this.idSubject != null && !this.idSubject.equals(other.idSubject))) {
            return false;
        }
        return true;
    }  
    /**
     * Method that return a String of the parameters of Subject.
     * @return String
     */
    @Override
    public String toString() {
        return "Subject{" + "idSubject=" + idSubject + ", name=" + name + ", password=" + password + ", teacherCourses=" + teacherCourses + ", exams=" + exams + ", courses=" + courses + '}';
    }
    
}