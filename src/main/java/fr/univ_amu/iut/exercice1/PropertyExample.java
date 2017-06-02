package fr.univ_amu.iut.exercice1;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;

public class PropertyExample {
    private ChangeListener changeListener = (observable, oldvalue, newvalue) ->
            System.out.println("The observableValue has changed: oldValue = " + oldvalue + ", newValue = " + newvalue );

    private InvalidationListener invalidationListener = (observable)->
            System.out.println("The observable has been invalidated.");

    private IntegerProperty anIntProperty;

    public static void main(String[] args) {
        PropertyExample propertyExample = new PropertyExample();
        propertyExample.createProperty();
        propertyExample.addAndRemoveInvalidationListener();
        propertyExample.addAndRemoveChangeListener();
    }

    void createProperty() {
        anIntProperty = new SimpleIntegerProperty(1024);
        System.out.println();

        System.out.println("anIntProperty = " + anIntProperty.toString());
        System.out.println("anIntProperty.get() = " + anIntProperty.get());
        System.out.println("anIntProperty.getValue() = " + anIntProperty.getValue());
    }

    void addAndRemoveInvalidationListener() {
        System.out.println();

        System.out.println("Add invalidation listener.");
        anIntProperty.addListener(invalidationListener);

        System.out.println("setValue() with 1024.");
        anIntProperty.setValue(1024);

        System.out.println("set() with 2105.");
        anIntProperty.set(2105);

        System.out.println("setValue() with 5012.");
        anIntProperty.setValue(5012);

        System.out.println("Remove invalidation listener.");
        anIntProperty.removeListener(invalidationListener);

        System.out.println("set() with 1024.");
        anIntProperty.set(1024);
    }

    void addAndRemoveChangeListener() {
        System.out.println();

        System.out.println("Add change listener.");
        anIntProperty.addListener(changeListener);

        System.out.println("setValue() with 1024.");
        anIntProperty.setValue(1024);

        System.out.println("set() with 2105.");
        anIntProperty.set(2105);

        System.out.println("setValue() with 5012.");
        anIntProperty.setValue(5012);

        System.out.println("Remove change listener.");
        anIntProperty.removeListener(changeListener);

        System.out.println("set() with 1024.");
        anIntProperty.set(1024);
    }


    public int getAnInt() {
        return anIntProperty.get();
    }

    public void setAnInt(int anInt) {
        if (anIntProperty == null)
            anIntProperty = new SimpleIntegerProperty();
        this.anIntProperty.set(anInt);
    }

    public IntegerProperty anIntProperty() {
        return anIntProperty;
    }}