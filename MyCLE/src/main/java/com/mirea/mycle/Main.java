package com.mirea.mycle;

import com.mirea.mycle.api.MyCLE;
import com.mirea.mycle.impl.MyCLEImpl;

public class Main {
    public static void main(String[] args) {
        MyCLE myCLE = new MyCLEImpl();
        myCLE.launch(args);
    }
}
