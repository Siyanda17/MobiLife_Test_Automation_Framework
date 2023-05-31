package com.mobilife.Driver.strategies;

import com.mobilife.Utilities.Constants;

public class DriverStrategyImplementer {
    public static DriverStrategy chooseStrategy(String strategy){
        //Enhanced Switch Statement
        return switch (strategy) {
            case Constants.CHROME -> new Chrome();
            case Constants.FIREFOX -> new FireFox();
            default -> null;
        };

    }
}
