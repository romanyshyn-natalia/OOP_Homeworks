package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyComparator;

import java.util.Arrays;

// Sorts elements using MyComparator to compare them
public class SortDecorator extends SmartArrayDecorator {
    private final MyComparator mcp;

    public SortDecorator(SmartArray smartArray, MyComparator comparator) {
        super(smartArray);
        this.mcp = comparator;
    }

    @Override
    public Object[] toArray() {
        Object[] baseArr = getSmartArray().toArray();
        Arrays.sort(baseArr, mcp);
        return baseArr;
    }

    @Override
    public String operationDescription() {
        return "Sorted elements using MyComparator";
    }

    @Override
    public int size() {
        return smartArray.size();
    }
}
