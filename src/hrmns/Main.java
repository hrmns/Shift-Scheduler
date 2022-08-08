package hrmns;

import hrmns.AlgoJuice.Scheduler;
import hrmns.Utils.Tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by: Harmanjeet Singh | github/hrmns | 2022-08-09
 */

public class Main {

    public static void main(String[] args) {

        //considering two 8 hour shifts in a day, No single employee can work both shifts
        //an employee can work for max 5 days in a week
        //Will add above in the form of parameters in later release
        Tests t = new Tests();
        t.testcase1();
        t.testcase2();
    }
}
