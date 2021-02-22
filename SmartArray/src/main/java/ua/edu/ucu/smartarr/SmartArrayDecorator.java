package ua.edu.ucu.smartarr;

import lombok.Getter;
import lombok.Setter;

abstract class SmartArrayDecorator implements SmartArray {

    @Getter
    @Setter
    protected SmartArray smartArray;

    public SmartArrayDecorator(SmartArray smartArray) {
        this.smartArray = smartArray;
    }

}
