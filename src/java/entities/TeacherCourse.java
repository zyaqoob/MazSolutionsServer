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
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 *Class that represents TeacherCourse entity.
 * @author Aitor Ruiz de Gauna
 */
/**
 * Entity that has the info of the courses of the Teacher.
 */
@NamedQueries({
    @NamedQuery(
            name = "findTeacherCourseById",
            query = "SELECT t FROM TeacherCourse t WHERE t.idTeacherCourse=:idTeacherCourse"
    ),
    @NamedQuery(
            name="findTeacherCourseByName", query="SELECT t from TeacherCourse t WHERE t.name=:name"
    )
    ,
    @NamedQuery(
            name = "findAllTeacherCourses",
            query = "SELECT t FROM TeacherCourse t ORDER BY t.idTeacherCourse ASC"
    )
    ,
    @NamedQuery(
            name = "findTeacherCoursesByTeacher",
            query = "SELECT tc FROM TeacherCourse tc,Teacher t WHERE t.fullName=:fullName and t.teacherCourse.idTeacherCourse=tc.idTeacherCourse"
    )
    ,
    @NamedQuery(
            name = "findTeacherCoursesBySubject",
            query = "SELECT ts.teacherCourse FROM TeacherCourseSubject ts WHERE ts.subject.name=:name"
    )
})
@Entity
@Table(name = "teacher_course", schema = "maz_solutions")
@XmlRootElement
public class TeacherCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTeacherCourse;
    // Date when the TeacherCourse starts.
    @Temporal(TemporalType.DATE)
    private Date dateStart;
    // Date when the TeacherCourse ends.
    @Temporal(TemporalType.DATE)
    private Date dateEnd;
    //Collection of the subject that the teacher has.
    @OneToMany(cascade=ALL,mappedBy = "teacherCourse", fetch = FetchType.EAGER)
    private Set<TeacherCourseSubject> teacherCourseSubjects;
    //Teacher of the TeacherCourse
    @OneToMany(mappedBy = "teacherCourse", fetch = FetchType.EAGER)
    private Set<Teacher> teachers;

    private String name;

    /**
     * Method that returns the class that contains the id's of TeacherCourse.
     *
     * @return idTeacherCourseId;
     */
    public Long getIdTeacherCourse() {
        return idTeacherCourse;
    }

    /**
     * Method that set the value of the object of the class TeacherCourseId that
     * contains the id's of TeacherCourse.
     *
     * @param idTeacherCourse idTeacherCourse to set
     */
    public void setIdTeacherCourse(Long idTeacherCourse) {
        this.idTeacherCourse = idTeacherCourse;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that returns the date start of the TeacherCourse.
     *
     * @return dateStart
     */
    public Date getDateStart() {
        return dateStart;
    }

    /**
     * Method that set the value of the dateStart of the TeacherCourse.
     *
     * @param dateStart dateStart
     */
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * Method that return the value of the dateEnd of TeacherCourse.
     *
     * @return dateEnd
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * Method that set the value of the dateEnd of TeacherCourse.
     *
     * @param dateEnd dateEnd
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * Method that return the value of the collection of subjects of
     * TeacherCourse.
     *
     * @return teacherCourseSUbject collection
     */
    @XmlTransient
    public Set<TeacherCourseSubject> getTeacherCourseSubjects() {
        return teacherCourseSubjects;
    }

    /**
     * Method that set the value of the collection of subjects of TeacherCourse.
     *
     * @param teacherCourseSubjects teacherCourseSubjects collection
     */
    public void setTeacherCourseSubjects(Set<TeacherCourseSubject> teacherCourseSubjects) {
        this.teacherCourseSubjects = teacherCourseSubjects;
    }

    /**
     * Method that return the value of the collection of teachers of
     * TeacherCourse.
     *
     * @return teachers collection
     */
    @XmlTransient
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    /**
     * Method that set the value of the collection of subjects of TeacherCourse.
     *
     * @param teachers teachers collection to set
     */
    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    /**
     * Integer representation for TeacherCourse instance.
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idTeacherCourse);
        return hash;
    }

    /**
     * Method that compares if two objects of TeacherCourse are equals.
     *
     * @param obj object
     * @return boolean
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
        final TeacherCourse other = (TeacherCourse) obj;
        if (!Objects.equals(this.idTeacherCourse, other.idTeacherCourse)) {
            return false;
        }
        return true;
    }

    /**
     * Method that return a String of the parameters of TeacherCourse.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "TeacherCourse{" + "idTeacherCourseId=" + idTeacherCourse + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", subjects=" + teacherCourseSubjects + ", teacher=" + teachers + '}';
    }
}
