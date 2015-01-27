package ahmetkucuk.com.horizontalpickerwidgetlibrary.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ARCELIK on 09.01.2015.
 */
public abstract class HorizontalPickerAdapter<T> {

    protected List<T> values;
    protected DataSetChangeListener listener;

    public HorizontalPickerAdapter(List<T> v) {
        if(v == null)
            this.values = new ArrayList<>();
        this.values = v;
    }

    public int getCount() {
        return values.size();
    }

    public T getItem(int position) {
        if(position < 0 || values.size() <= position) {
            throw new IllegalArgumentException("bad item position");
        }
        return values.get(position);
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T>  values) {
        this.values = values;
        listener.onDataSetChanged();
    }

    public void addItem(T item) {
        values.add(values.size(), item);
        if(listener != null)
            listener.onDataSetChanged();
    }

    public void removeItem(T item) {
        values.remove(item);
        if(listener != null)
            listener.onDataSetChanged();
    }

    public String getItemText(int position) {
        return getItem(position).toString();
    }

    public void registerAdapter(DataSetChangeListener l) {
        listener = l;
    }
}
