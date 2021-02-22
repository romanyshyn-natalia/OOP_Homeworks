package ua.edu.ucu.smartarr;

// Base array for decorators
public class BaseArray implements SmartArray {
    private final Object[] array;
    private final int size;

    public BaseArray(Object[] array) {
        this.size = array.length;
        this.array = new Object[size];
        System.arraycopy(array, 0, this.array, 0, size);
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(array, 0, result, 0, size);
        return result;
    }

    @Override
    public String operationDescription() {
        return "Base array description!";
    }

    @Override
    public int size() {
        return size;
    }
}
