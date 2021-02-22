package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyPredicate;

import java.util.ArrayList;

// Tests every element and removes it if it doesn't satisfy MyPredicate
public class FilterDecorator extends SmartArrayDecorator {
    private final MyPredicate mpd;

    public FilterDecorator(SmartArray smartArray, MyPredicate predicate) {
        super(smartArray);
        this.mpd = predicate;
        filterArray();
    }

    public void filterArray() {
        Object[] baseArr = smartArray.toArray();
        ArrayList<Object> updated = new ArrayList<>();
        for (Object obj : baseArr) {
            if (this.mpd.test(obj)) {
                updated.add(obj);
            }
        }
        smartArray = new BaseArray(updated.toArray());
    }

    @Override
    public Object[] toArray() {
        return this.getSmartArray().toArray();
    }

    @Override
    public String operationDescription() {
        return "Filtered array";
    }

    @Override
    public int size() {
        return smartArray.size();
    }
}
