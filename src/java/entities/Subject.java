/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *Class that represents Subject entity.
 * @author Aitor Ruiz de Gauna
 */
/**
 * Entity that contains the info of a subject.
 */
@NamedQueries({
    @NamedQuery(
        name="findSubjectById",
            query="SELECT s FROM Subject s WHERE s.idSubject=:id"
    ),
    @NamedQuery(
        name="findAllSubjects",
            query="SELECT s FROM Subject s ORDER BY s.idSubject ASC"
    ),
    @NamedQuery(
        name="findSubjectsByStudent",
            query="SELECT cs.subject FROM Subject s,Student st, Course c, CourseSubject cs WHERE st.fullName=:fullName and c.idCourse=st.course.idCourse and cs.course.idCourse=st.course.idCourse"       
    ),
    @NamedQuery(
        name="findSubjectsByCourse",
            query="SELECT cs.subject FROM CourseSubject cs WHERE  cs.course.name=:name"
    ),
    @NamedQuery(
        name="findSubjectsByTeacherCourse",
            query="SELECT ts.subject FROM TeacherCourseSubject ts WHERE ts.teacherCourse.idTeacherCourse=:idTeacherCourse"    
    ),
    @NamedQuery(
        name="findSubjectByExam",
            query="SELECT e.subject FROM Exam e WHERE e.idExam=:idExam"    
    )    
})
@Entity
@Table(name="subject",schema="maz_solutions")
@XmlRootElement
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    //Subject identifier.
    private Long idSubject;
    //Name of the subject.
    private String name;
    //Password to register in the subject
    private int totalHours;
    //TeacherCourse where the subject appears
    @OneToMany(cascade=CascadeType.MERGE,mappedBy="subject",fetch=EAGER)
    private Set<TeacherCourseSubject> teacherCourseSubjects;
    //Collection of exams that the subject has had
    @OneToMany(cascade=CascadeType.MERGE,mappedBy="subject",fetch=EAGER)
    private Set<Exam>exams;
    //Collection of courses where the subject is teached
    @OneToMany(cascade=CascadeType.MERGE,mappedBy="subject",fetch=EAGER)
    private Set<CourseSubject> courseSubjects;
    /**
    * Method that return the identifier of the subject.
    * @return idSubject
    */
    public Long getIdSubject() {
        return idSubject;
    }
    /**
    * Method that set the value of the identifier of the subject.
    * @param idSubject idsubject
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
    * @param name name
    */
    public void setName(String name) {
        this.name = name;
    }
    /**
    * Method that return the password of the subject.
    * @return password
    */
    public int getTotalHours() {
        return totalHours;
    }
    /**
    * Method that set the value of the password of the subject.
    * @param totalHours totalHours
    */
    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }
    /**
    * Method that return the TeacherCourse of the subject.
    * @return teacherCourse
    */
    @XmlTransient
    public Set<TeacherCourseSubject> getTeacherCourseSubjects() {    
        return teacherCourseSubjects;
    }
    /**
     * Method that set the value of the TeacherCourse of the subject.
     * @param teacherCourseSubjects teacherCourseSubject
     */
    public void setTeacherCourseSubjects(Set<TeacherCourseSubject> teacherCourseSubjects) {    
        this.teacherCourseSubjects = teacherCourseSubjects;
    }

    /**
     * Method that return the Exams of the subject.
     * @return exams
     */
    @XmlTransient
    public Set<Exam> getExams() {
        return exams;
    }
    /**
     * Method that set the value of the exams of the subject.
     * @param exams  exams collection
     */
    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }
    /**
     * Method that return the courses where the subject is teached.
     * @return courses
     */
    @XmlTransient
    public Set<CourseSubject> getCourseSubjects() {    
        return courseSubjects;
    }
    /**
     * Method that set the value of the courses where the subject is teached.
     * @param courseSubjects courseSubject
     */
    public void setCourseSubjects(Set<CourseSubject> courseSubjects) {
        this.courseSubjects = courseSubjects;
    }  
    /**
     * Integer representation for Subject instance.
     * @return hash
     */
    @Override    
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.idSubject);
        return hash;
    }  

    /**
     * 
     * Method that compares if two objects of Subjects are equals.
     * @param object object
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
        return "Subject{" + "idSubject=" + idSubject + ", name=" + name + ", password=" + totalHours + ", teacherCourses=" + teacherCourseSubjects + ", exams=" + exams + ", courses=" + courseSubjects + '}';
    }
    
}
