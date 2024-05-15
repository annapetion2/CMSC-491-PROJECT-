package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.ModelIdentifier;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Examgrade type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Examgrades", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Examgrade implements Model {
  public static final QueryField ID = field("Examgrade", "id");
  public static final QueryField COURSE = field("Examgrade", "course");
  public static final QueryField EXAM = field("Examgrade", "exam");
  public static final QueryField GRADE = field("Examgrade", "grade");
  public static final QueryField STUDENTID = field("Examgrade", "studentid");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String course;
  private final @ModelField(targetType="String") String exam;
  private final @ModelField(targetType="Int") Integer grade;
  private final @ModelField(targetType="String") String studentid;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  /** @deprecated This API is internal to Amplify and should not be used. */
  @Deprecated
   public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getCourse() {
      return course;
  }
  
  public String getExam() {
      return exam;
  }
  
  public Integer getGrade() {
      return grade;
  }
  
  public String getStudentid() {
      return studentid;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Examgrade(String id, String course, String exam, Integer grade, String studentid) {
    this.id = id;
    this.course = course;
    this.exam = exam;
    this.grade = grade;
    this.studentid = studentid;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Examgrade examgrade = (Examgrade) obj;
      return ObjectsCompat.equals(getId(), examgrade.getId()) &&
              ObjectsCompat.equals(getCourse(), examgrade.getCourse()) &&
              ObjectsCompat.equals(getExam(), examgrade.getExam()) &&
              ObjectsCompat.equals(getGrade(), examgrade.getGrade()) &&
              ObjectsCompat.equals(getStudentid(), examgrade.getStudentid()) &&
              ObjectsCompat.equals(getCreatedAt(), examgrade.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), examgrade.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCourse())
      .append(getExam())
      .append(getGrade())
      .append(getStudentid())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Examgrade {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("course=" + String.valueOf(getCourse()) + ", ")
      .append("exam=" + String.valueOf(getExam()) + ", ")
      .append("grade=" + String.valueOf(getGrade()) + ", ")
      .append("studentid=" + String.valueOf(getStudentid()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Examgrade justId(String id) {
    return new Examgrade(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      course,
      exam,
      grade,
      studentid);
  }
  public interface BuildStep {
    Examgrade build();
    BuildStep id(String id);
    BuildStep course(String course);
    BuildStep exam(String exam);
    BuildStep grade(Integer grade);
    BuildStep studentid(String studentid);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String course;
    private String exam;
    private Integer grade;
    private String studentid;
    public Builder() {
      
    }
    
    private Builder(String id, String course, String exam, Integer grade, String studentid) {
      this.id = id;
      this.course = course;
      this.exam = exam;
      this.grade = grade;
      this.studentid = studentid;
    }
    
    @Override
     public Examgrade build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Examgrade(
          id,
          course,
          exam,
          grade,
          studentid);
    }
    
    @Override
     public BuildStep course(String course) {
        this.course = course;
        return this;
    }
    
    @Override
     public BuildStep exam(String exam) {
        this.exam = exam;
        return this;
    }
    
    @Override
     public BuildStep grade(Integer grade) {
        this.grade = grade;
        return this;
    }
    
    @Override
     public BuildStep studentid(String studentid) {
        this.studentid = studentid;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String course, String exam, Integer grade, String studentid) {
      super(id, course, exam, grade, studentid);
      
    }
    
    @Override
     public CopyOfBuilder course(String course) {
      return (CopyOfBuilder) super.course(course);
    }
    
    @Override
     public CopyOfBuilder exam(String exam) {
      return (CopyOfBuilder) super.exam(exam);
    }
    
    @Override
     public CopyOfBuilder grade(Integer grade) {
      return (CopyOfBuilder) super.grade(grade);
    }
    
    @Override
     public CopyOfBuilder studentid(String studentid) {
      return (CopyOfBuilder) super.studentid(studentid);
    }
  }
  

  public static class ExamgradeIdentifier extends ModelIdentifier<Examgrade> {
    private static final long serialVersionUID = 1L;
    public ExamgradeIdentifier(String id) {
      super(id);
    }
  }
  
}
