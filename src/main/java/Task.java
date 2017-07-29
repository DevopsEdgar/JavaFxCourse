import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Task {

    public static void main(String[] args) {
        SimpleIntegerProperty intProp = new SimpleIntegerProperty();
        intProp.set(15);
        System.out.println(intProp.get());

        SimpleStringProperty stringProp = new SimpleStringProperty();
        System.out.println(stringProp.getValue());
        stringProp.set("New Value");
        System.out.println(stringProp.get());


        ReadOnlyBooleanWrapper readOnlyBooleanWrapper = new ReadOnlyBooleanWrapper();
        readOnlyBooleanWrapper.set(true);
        ReadOnlyBooleanProperty readOnlyBooleanProperty = readOnlyBooleanWrapper.getReadOnlyProperty();

        System.out.println(readOnlyBooleanProperty.get());
        readOnlyBooleanWrapper.set(false);
        System.out.println(readOnlyBooleanProperty.get());
        //version normal
//        intProp.addListener(new ChangeListener<Number>() {
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println("Integer Property is change to "+ newValue);
//            }
//        });
        //version con lambas
        intProp.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue)->
                System.out.println("Listerner 1 Integer Property is change to "+ newValue));
        intProp.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue)->
                System.out.println(" Listener 2 Integer Property is change to "+ newValue));

        intProp.addListener((Observable observable)-> {
            System.out.println("Int property channged");
        });


        intProp.set(90);
        intProp.set(100);

    }
}
