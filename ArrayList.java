import java.util.Collection;

    public class ArrayList<T> implements Collection<T> {
    private T[] data;
    private int size = 0;
    public ArrayList() {
        data = create(10);
    }
    
    private T[] create(int size) {
        Object[] array = new Object[size];
        return (T[])array;
    }

    @Override
    public T get(int index) {
        return data[index];
    }

    @Override
    public void add(T value) {
        if (size == data.length)
        {
            T[] newArray = create(2 * size);
            for (int i = 0; i < data.length; i++)
                newArray[i] = data[i];
            data = newArray;
        }

        data[size] = value;
        size++;
    }

    @Override
    public int size() {
        return size;
    }
}
    
