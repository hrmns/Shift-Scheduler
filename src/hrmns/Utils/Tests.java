package hrmns.Utils;

import hrmns.AlgoJuice.Scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by: Harmanjeet Singh | github/hrmns | 2022-08-09
 */
public class Tests {

    HashMap<String, ArrayList<String>> leaveRequests = new HashMap<>();

    public boolean testcase1() {
        leaveRequests.clear();
        leaveRequests.put("Emp1", new ArrayList<>(Arrays.asList("mon", "tue")));
        leaveRequests.put("Emp2", new ArrayList<>(Arrays.asList("mon", "tue", "wed")));
        leaveRequests.put("Emp3", new ArrayList<>(Arrays.asList("thu", "fri", "sat", "sun")));
        leaveRequests.put("Emp4", new ArrayList<>(Arrays.asList("sat", "sun")));

        return Scheduler.schedulable(leaveRequests);
    }

    public boolean testcase2() {
        leaveRequests.clear();
        leaveRequests.put("Emp1", new ArrayList<>(Arrays.asList("mon", "tue")));
        leaveRequests.put("Emp2", new ArrayList<>(Arrays.asList("mon", "tue", "wed")));
        leaveRequests.put("Emp3", new ArrayList<>(Arrays.asList("thu", "fri", "sat", "sun")));

        return Scheduler.schedulable(leaveRequests);
    }
}
