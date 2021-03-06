/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Class that represents Exam entity.
 *
 * @author Zeeshan Yaqoob
 */
@NamedQueries({
    @NamedQuery(name = "findAllExam", query = "SELECT e FROM Exam e")
    ,@NamedQuery(name = "findExamsBySubject",
            query = "SELECT e FROM Exam e WHERE e.subject.name=:subject_name")
    ,@NamedQuery(name = "findExamsByStudent",
            query = "SELECT es.exam FROM ExamSession es WHERE es.student.fullName=:student_name")
    ,@NamedQuery(name = "findExamByExamSession",
            query = "SELECT es.exam FROM ExamSession es WHERE es.exam.idExam=:idExam")

})
@Entity
@Table(name = "exam", schema = "maz_solutions")
@XmlRootElement
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Field that identify ExamSession.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idExam;

    /**
     * Field that represent statement of exam.
     */
    private String examStatement;

    /**
     * An object of student.
     */
    @ManyToOne
    private Subject subject;

    /**
     * Collection of examSession.
     */
    @OneToMany(mappedBy = "exam", fetch = FetchType.EAGER)
    private Set<ExamSession> sessions;

    /**
     *
     * @return examStatement
     */
    public String getExamStatement() {
        return examStatement;
    }

    /**
     * Field that represent statement of exam.
     *
     * @param examStatement the examStatement to set
     */
    public void setExamStatement(String examStatement) {
        this.examStatement = examStatement;
    }

    /**
     *
     * @return subject.
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * An object of subject.
     *
     * @param subject the subject to set.
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * Method that return the sessions.
     *
     * @return sessions
     */
    @XmlTransient
    public Set<ExamSession> getSessions() {
        return sessions;
    }

    /**
     * Collection of examSession.
     *
     * @param sessions the collection of ExamSession to set.
     */
    public void setSessions(Set<ExamSession> sessions) {
        this.sessions = sessions;
    }

    /**
     *
     * @return idExam.
     */
    public Long getIdExam() {
        return idExam;
    }

    /**
     * Field that identify ExamSession.
     *
     * @param idExam the id of exam to set
     */
    public void setIdExam(Long idExam) {
        this.idExam = idExam;
    }

    /**
     * Integer representation of Exam instance
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.idExam);
        return hash;
    }

    /**
     * Compares two objects of Exam.
     *
     * @return true if they are same.
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
        final Exam other = (Exam) obj;
        if (!Objects.equals(this.idExam, other.idExam)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Exam{" + "idExam=" + idExam + ", examStatement=" + examStatement + ", subject=" + subject + ", sessions=" + sessions + '}';
    }

}
