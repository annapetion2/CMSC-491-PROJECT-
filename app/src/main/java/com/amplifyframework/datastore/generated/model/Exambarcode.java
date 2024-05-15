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

/** This is an auto generated class representing the Exambarcode type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Exambarcodes", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Exambarcode implements Model {
  public static final QueryField ID = field("Exambarcode", "id");
  public static final QueryField COURSE = field("Exambarcode", "course");
  public static final QueryField BARCODESTRING = field("Exambarcode", "barcodestring");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String course;
  private final @ModelField(targetType="String") String barcodestring;
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
  
  public String getBarcodestring() {
      return barcodestring;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Exambarcode(String id, String course, String barcodestring) {
    this.id = id;
    this.course = course;
    this.barcodestring = barcodestring;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Exambarcode exambarcode = (Exambarcode) obj;
      return ObjectsCompat.equals(getId(), exambarcode.getId()) &&
              ObjectsCompat.equals(getCourse(), exambarcode.getCourse()) &&
              ObjectsCompat.equals(getBarcodestring(), exambarcode.getBarcodestring()) &&
              ObjectsCompat.equals(getCreatedAt(), exambarcode.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), exambarcode.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCourse())
      .append(getBarcodestring())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Exambarcode {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("course=" + String.valueOf(getCourse()) + ", ")
      .append("barcodestring=" + String.valueOf(getBarcodestring()) + ", ")
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
  public static Exambarcode justId(String id) {
    return new Exambarcode(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      course,
      barcodestring);
  }
  public interface BuildStep {
    Exambarcode build();
    BuildStep id(String id);
    BuildStep course(String course);
    BuildStep barcodestring(String barcodestring);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String course;
    private String barcodestring;
    public Builder() {
      
    }
    
    private Builder(String id, String course, String barcodestring) {
      this.id = id;
      this.course = course;
      this.barcodestring = barcodestring;
    }
    
    @Override
     public Exambarcode build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Exambarcode(
          id,
          course,
          barcodestring);
    }
    
    @Override
     public BuildStep course(String course) {
        this.course = course;
        return this;
    }
    
    @Override
     public BuildStep barcodestring(String barcodestring) {
        this.barcodestring = barcodestring;
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
    private CopyOfBuilder(String id, String course, String barcodestring) {
      super(id, course, barcodestring);
      
    }
    
    @Override
     public CopyOfBuilder course(String course) {
      return (CopyOfBuilder) super.course(course);
    }
    
    @Override
     public CopyOfBuilder barcodestring(String barcodestring) {
      return (CopyOfBuilder) super.barcodestring(barcodestring);
    }
  }
  

  public static class ExambarcodeIdentifier extends ModelIdentifier<Exambarcode> {
    private static final long serialVersionUID = 1L;
    public ExambarcodeIdentifier(String id) {
      super(id);
    }
  }
  
}
