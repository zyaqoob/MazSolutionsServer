/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity representing Student of the application. It contains the following
 * fields: year, sessions and courser.
 *
 * @author Miguel Ángel Sánchez
 */
@NamedQueries({
    @NamedQuery(
        name="findStudentById", query="SELECT s FROM Student s WHERE s.idUser=:idUser"
    ),
    @NamedQuery(
        name="findStudentsByCourse", query="SELECT s FROM Student s WHERE s.course.idCourse=:idCourse"
    ),
    @NamedQuery(
        name="findAllStudents", query="SELECT s FROM Student s ORDER BY s.idUser DESC"
    ),
    @NamedQuery(
        name="findStudentByExSes",query="SELECT es.student FROM ExamSession es WHERE es.idExamSession=:idExamSession"
    ),
    @NamedQuery(
        name="findStudentByEmail",query="SELECT s FROM Student s WHERE s.email=:email"
    ),
    @NamedQuery(
        name="findStudentsByTeacher",query="Select s FROM Student s, Teacher t WHERE t.fullName=:full_name AND t.teacherCourse.name=s.course.name"
    ),
    @NamedQuery(name="findExistingStudent",
            query="SELECT s from Student s WHERE s.login=:login or s.email=:email")
})
@Entity
@Table(name = "student", schema = "maz_solutions")
@XmlRootElement
public class Student extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Year when the Student is registered.
     */
    @Temporal(TemporalType.DATE)
    private Date year;
    /**
     * Exam sessions where the student are being evaluated.
     */
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "student",fetch=FetchType.EAGER)
    private Set<ExamSession> sessions;
    /**
     * Course where the student is registered.
     */
    @ManyToOne
    private Course course;

    /**
     *
     * @return This method returns the year of the student.
     */
    public Date getYear() {
        return year;
    }

    /**
     *
     * @param year This method set the year of the student.
     */
    public void setYear(Date year) {
        this.year = year;
    }

    /**
     *
     * @return This method returns a Set with the exam sessions of the student.
     */
    @XmlTransient
    public Set<ExamSession> getSessions() {
        return sessions;
    }

    /**
     *
     * @param sessions This method set the sessions of the student.
     */
    public void setSessions(Set<ExamSession> sessions) {
        this.sessions = sessions;
    }

    /**
     *
     * @return This method returns a course.
     */
    public Course getCourse() {
        return course;
    }

    /**
     *
     * @param course This method set a course.
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     *
     * @return Integer representation for Student instance.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.getIdUser());
        return hash;
    }

    /**
     * Compares two Student objects for equality. This method consider a Student
     * equal to another Student if their id fields have the same value.
     *
     * @param obj The other Student to compare to
     * @return Returns true or false depending if the fields are equals.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (!Objects.equals(this.sessions, other.sessions)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.getIdUser(), other.getIdUser())) {
            return false;
        }
        return true;
    }

    /**
     * Obtains a string representation of the Student.
     *
     * @return The String representing the Student.
     */
    @Override
    public String toString() {
        return "Student{" + "year=" + year + ", sessions=" + sessions + ", course=" + course + '}';
    }

}
