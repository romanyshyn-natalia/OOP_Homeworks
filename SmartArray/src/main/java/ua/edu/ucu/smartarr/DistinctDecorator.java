package ua.edu.ucu.smartarr;

import java.util.ArrayList;

// Remove duplicates from SmartArray. Use method equals() to compare objects
public class DistinctDecorator extends SmartArrayDecorator {

    public DistinctDecorator(SmartArray smartArray) {
        super(distDecor(smartArray));

    }

    private static SmartArray distDecor(SmartArray smartArray) {
        Object[] baseArr = smartArray.toArray();
        ArrayList<Object> updated = new ArrayList<>();
        for (Object obj : baseArr) {
            boolean flag = true;
            for (Object elem : updated) {
                if (elem.equals(obj)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                updated.add(obj);
            }
        }
        return new BaseArray(updated.toArray());
    }

    @Override
    public Object[] toArray() {
        return smartArray.toArray();
    }

    @Override
    public String operationDescription() {
        return "Removed duplicates from array.";
    }

    @Override
    public int size() {
        return smartArray.size();
    }
}
