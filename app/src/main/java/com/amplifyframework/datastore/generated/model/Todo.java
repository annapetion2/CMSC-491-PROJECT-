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

/** This is an auto generated class representing the Todo type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Todos", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Todo implements Model {
  public static final QueryField ID = field("Todo", "id");
  public static final QueryField FNAME = field("Todo", "fname");
  public static final QueryField LNAME = field("Todo", "lname");
  public static final QueryField STUDENTID = field("Todo", "studentid");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String fname;
  private final @ModelField(targetType="String") String lname;
  private final @ModelField(targetType="ID", isRequired = true) String studentid;
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
  
  public String getFname() {
      return fname;
  }
  
  public String getLname() {
      return lname;
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
  
  private Todo(String id, String fname, String lname, String studentid) {
    this.id = id;
    this.fname = fname;
    this.lname = lname;
    this.studentid = studentid;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Todo todo = (Todo) obj;
      return ObjectsCompat.equals(getId(), todo.getId()) &&
              ObjectsCompat.equals(getFname(), todo.getFname()) &&
              ObjectsCompat.equals(getLname(), todo.getLname()) &&
              ObjectsCompat.equals(getStudentid(), todo.getStudentid()) &&
              ObjectsCompat.equals(getCreatedAt(), todo.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), todo.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getFname())
      .append(getLname())
      .append(getStudentid())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Todo {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("fname=" + String.valueOf(getFname()) + ", ")
      .append("lname=" + String.valueOf(getLname()) + ", ")
      .append("studentid=" + String.valueOf(getStudentid()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static FnameStep builder() {
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
  public static Todo justId(String id) {
    return new Todo(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      fname,
      lname,
      studentid);
  }
  public interface FnameStep {
    StudentidStep fname(String fname);
  }
  

  public interface StudentidStep {
    BuildStep studentid(String studentid);
  }
  

  public interface BuildStep {
    Todo build();
    BuildStep id(String id);
    BuildStep lname(String lname);
  }
  

  public static class Builder implements FnameStep, StudentidStep, BuildStep {
    private String id;
    private String fname;
    private String studentid;
    private String lname;
    public Builder() {
      
    }
    
    private Builder(String id, String fname, String lname, String studentid) {
      this.id = id;
      this.fname = fname;
      this.lname = lname;
      this.studentid = studentid;
    }
    
    @Override
     public Todo build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Todo(
          id,
          fname,
          lname,
          studentid);
    }
    
    @Override
     public StudentidStep fname(String fname) {
        Objects.requireNonNull(fname);
        this.fname = fname;
        return this;
    }
    
    @Override
     public BuildStep studentid(String studentid) {
        Objects.requireNonNull(studentid);
        this.studentid = studentid;
        return this;
    }
    
    @Override
     public BuildStep lname(String lname) {
        this.lname = lname;
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
    private CopyOfBuilder(String id, String fname, String lname, String studentid) {
      super(id, fname, lname, studentid);
      Objects.requireNonNull(fname);
      Objects.requireNonNull(studentid);
    }
    
    @Override
     public CopyOfBuilder fname(String fname) {
      return (CopyOfBuilder) super.fname(fname);
    }
    
    @Override
     public CopyOfBuilder studentid(String studentid) {
      return (CopyOfBuilder) super.studentid(studentid);
    }
    
    @Override
     public CopyOfBuilder lname(String lname) {
      return (CopyOfBuilder) super.lname(lname);
    }
  }
  

  public static class TodoIdentifier extends ModelIdentifier<Todo> {
    private static final long serialVersionUID = 1L;
    public TodoIdentifier(String id) {
      super(id);
    }
  }
  
}
