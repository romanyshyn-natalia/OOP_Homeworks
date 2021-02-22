package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyFunction;


// Map every element to another object using MyFunction
public class MapDecorator extends SmartArrayDecorator {
    private final MyFunction function;

    public MapDecorator(SmartArray sa, MyFunction func) {
        super(sa);
        this.function = func;
    }

    @Override
    public Object[] toArray() {
        Object[] baseArr = smartArray.toArray();
        for (int i = 0; i < smartArray.size(); i++) {
            baseArr[i] = function.apply(baseArr[i]);
        }
        return baseArr;
    }

    @Override
    public String operationDescription() {
        return "Mapped array.";
    }

    @Override
    public int size() {
        return smartArray.size();
    }
}
