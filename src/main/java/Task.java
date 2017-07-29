//import javafx.beans.binding.Bindings;
//import javafx.beans.binding.NumberBinding;
//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {

//    public static void main(String[] args) {
//        StringProperty lastNameProp = new SimpleStringProperty();
//        StringProperty firstNameProp = new SimpleStringProperty();
//
//        lastNameProp.set("Clarcke");
//        firstNameProp.set("Manuel");
//        //esto es binding unidireccional
//        //firstNameProp.bind(lastNameProp);
//        firstNameProp.bindBidirectional(lastNameProp);
//        //el valor de surName será el que tenga lastName
//        System.out.println(firstNameProp.get());
//        System.out.println(lastNameProp.get());
//
//        StringProperty fullNameProp = new SimpleStringProperty();
//        fullNameProp.bind(Bindings.concat(firstNameProp, " ", lastNameProp));
//        System.out.println(fullNameProp.get());
//
//        IntegerProperty length = new SimpleIntegerProperty(18);
//        IntegerProperty width = new SimpleIntegerProperty(13);
//
//        IntegerProperty area = new SimpleIntegerProperty();
//        area.bind(length.multiply(width));
//
//        NumberBinding perimeter = length.add(width).multiply(2);
//
//        System.out.println(area.get());
//        System.out.println(perimeter.getValue());

    private final StringProperty priority = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final ObjectProperty<Integer> progress = new SimpleObjectProperty(0);
    private final ObjectProperty<Integer> id = new SimpleObjectProperty<>(null);

    public Integer getId() {
        return id.get();
    }

    public ObjectProperty<Integer> idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public Task() {
    }
    public Task(Integer id, String prority, String description, Integer progress) {
        this.id.set(id);
        this.priority.set(prority);
        this.description.set(description);
        this.progress.set(progress);
    }



    public String getPriority() {
        return priority.get();
    }

    public StringProperty priorityProperty() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority.set(priority);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Integer getProgress() {
        return progress.get();
    }

    public ObjectProperty<Integer> progressProperty() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress.set(progress);
    }
    //    }
}
