package com.davidbriard.suncalc;

import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.google.android.gms.common.util.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A ViewModel that is also an Observable,
 * to be used with the Data Binding Library.
 */
class ObservableViewModel extends ViewModel implements Observable {
    private PropertyChangeRegistry callbacks = new PropertyChangeRegistry();

    private Map<Integer, List<Integer>> mInternalPropertyDependencies;

    public ObservableViewModel()
    {
        mInternalPropertyDependencies = BuildInternalPropertyDependencies();
    }

    private Map<Integer, List<Integer>> BuildInternalPropertyDependencies()
    {
        Map<Integer, List<Integer>> dictionary = null;

        Class classToInvestigate = getClass();

        Method[] fields = classToInvestigate.getDeclaredMethods();
        for(Method field : fields)
        {
            Annotation annotation = field.getAnnotation(DependsOnField.class);

            if(annotation instanceof DependsOnField)
            {
                if (dictionary == null)
                {
                    dictionary = new HashMap<Integer, List<Integer>>();
                }

                DependsOnField myAnnotation = (DependsOnField) annotation;

                for(Integer propertyName : myAnnotation.fieldIds()) {
                    List<Integer> list = dictionary.get(propertyName);
                    if (list == null)
                    {
                        list = new ArrayList<Integer>();
                        dictionary.put(propertyName, list);
                    }

                    Integer fieldName = getFieldId(field.getName());
                    list.add(fieldName);
                }
            }
        }

        return dictionary;
    }

    private int getFieldId(String brName)
    {
        brName = brName.replace("get", "");
        brName = brName.substring(0, 1).toLowerCase() + brName.substring(1);

        try
        {
            Field f = BR.class.getDeclaredField(brName);
            return f.getInt(null);
        }
        catch (Exception ex)
        {
            return -1;
        }
    }

    @Override
    public void addOnPropertyChangedCallback(
            Observable.OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(
            OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    /**
     * Notifies observers that all properties of this instance have changed.
     */
    protected void notifyChange() {
        callbacks.notifyCallbacks(this, 0, null);
    }

    /**
     * Notifies observers that a specific property has changed. The getter for the
     * property that changes should be marked with the @Bindable annotation to
     * generate a field in the BR class to be used as the fieldId parameter.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    protected void notifyPropertyChanged(int fieldId, boolean inter) {
        callbacks.notifyCallbacks(this, fieldId, null);

        if (!inter || mInternalPropertyDependencies == null)
        {
            return;
        }
        if (fieldId != 0)
        {
            notifyPropertyChanged(mInternalPropertyDependencies, fieldId, new int[0]);
            notifyPropertyChanged(mInternalPropertyDependencies, fieldId, new int[] { fieldId });
        }
    }

    private void notifyPropertyChanged(Map<Integer, List<Integer>> dictionary, int fieldId, int[] propertiesToExclude)
    {
        List<Integer> list = dictionary.get(fieldId);

        if (list != null)
        {
            for(Integer i : list)
            {
               if (!ArrayUtils.contains(propertiesToExclude, i))
               {
                   notifyPropertyChanged(i);
               }
            }
        }
    }

    protected void notifyPropertyChanged(int fieldId) {
        this.notifyPropertyChanged(fieldId, true);
    }
}