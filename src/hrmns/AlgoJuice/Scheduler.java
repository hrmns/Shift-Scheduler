package hrmns.AlgoJuice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

import static hrmns.Utils.Constants.week;

/**
 * Created by: Harmanjeet Singh | github/hrmns | 2022-08-09
 */

final public class Scheduler {

    public static void printMessage(boolean schedulable) {
        if (schedulable)
            System.out.println("Shifts assigned successfully for this week");
        else
            System.out.println("Shifts for this week cannot be covered with the given params");
    }

    public static boolean canSchedule(HashMap<String, HashSet<String>> requests, Map<String, Integer> totalWorkWeekly, List<String> names, int day) {
        if (day == week.size()) {
            return true;
        }

        Set<String> onLeave = null;
        if (requests.containsKey(week.get(day)))
            onLeave = requests.get(week.get(day));
        else
            onLeave = new HashSet<>();

        boolean ans = false;
        for (int i = 0; i < names.size(); i++) {
            if (onLeave.contains(names.get(i)) || totalWorkWeekly.get(names.get(i)) >= 40)
                continue;

            for (int j = i + 1; j < names.size(); j++) {
                if (onLeave.contains(names.get(i)) || totalWorkWeekly.get(names.get(i)) >= 40)
                    continue;
                if (onLeave.contains(names.get(j)) || totalWorkWeekly.get(names.get(j)) >= 40)
                    continue;

                totalWorkWeekly.put(names.get(i), totalWorkWeekly.get(names.get(i)) + 8);
                totalWorkWeekly.put(names.get(j), totalWorkWeekly.get(names.get(j)) + 8);
                System.out.println(names.get(i) + " - " + totalWorkWeekly.get(names.get(i)) + "hrs, " + names.get(j) + " - " + totalWorkWeekly.get(names.get(j)) + "hrs ,day:" + day);
                ans = ans || canSchedule(requests, totalWorkWeekly, names, day + 1);
                if (ans)
                    return ans;

                totalWorkWeekly.put(names.get(i), totalWorkWeekly.get(names.get(i)) - 8);
                totalWorkWeekly.put(names.get(j), totalWorkWeekly.get(names.get(j)) - 8);
            }
        }
        return ans;
    }

    public static boolean schedulable(
            HashMap<String, ArrayList<String>> requests
    ) {
        Map<String, Integer> totalWorkWeekly = new HashMap<>();
        List<String> names = new ArrayList<>();
        HashMap<String, HashSet<String>> requestsModified = new HashMap<>();

        for (var entry : requests.entrySet()) {
            String name = entry.getKey();
            if (!totalWorkWeekly.containsKey(name)) {
                totalWorkWeekly.put(name, 0);
                names.add(name);
            }

            for (String day : entry.getValue()) {
                if (!requestsModified.containsKey(day)) {
                    requestsModified.put(day, new HashSet<>());
                }
                requestsModified.get(day).add(name);
            }
        }
        System.out.println("FORMAT: Employee name, Work done till that day, day");

        Boolean result = canSchedule(requestsModified, totalWorkWeekly, names, 0);
        printMessage(result);
        return result;
    }
}