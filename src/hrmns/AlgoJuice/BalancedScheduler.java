package hrmns.AlgoJuice;

import hrmns.dto.Employee;

import java.util.*;

import static hrmns.Utils.Constants.week;

/**
 * Created by: Harmanjeet Singh | github/hrmns | 2022-08-10
 */

final public class BalancedScheduler {

    public static Employee[][] schedule = new Employee[7][2];

    public static boolean canSchedule(HashMap<String, HashSet<String>> requests, PriorityQueue<Employee> totalWorkWeekly, int day) {
        if (day == week.size()) {
            PrintingPress.printSchedule(totalWorkWeekly, schedule);
            return true;
        }

        Set<String> onLeave = null;
        if (requests.containsKey(week.get(day)))
            onLeave = requests.get(week.get(day));
        else
            onLeave = new HashSet<>();

        boolean ans = false;

        Iterator<Employee> it = totalWorkWeekly.iterator();
        while (it.hasNext()) {
            Employee pair = it.next();
            Iterator<Employee> it2 = it;

            if (onLeave.contains(pair.name) || pair.workDone >= 40)
                continue;

            while (it2.hasNext()) {

                Employee pair2 = it2.next();
                if (onLeave.contains(pair.name) || pair.workDone >= 40)
                    continue;
                if (onLeave.contains(pair2.name) || pair2.workDone >= 40)
                    continue;

                totalWorkWeekly.remove(pair);
                totalWorkWeekly.remove(pair2);
                pair.workDone = pair.workDone + 8;
                pair2.workDone = pair2.workDone + 8;
                totalWorkWeekly.add(pair);
                totalWorkWeekly.add(pair2);
                schedule[day][0] = pair;
                schedule[day][1] = pair2;
                ans = ans || canSchedule(requests, totalWorkWeekly, day + 1);
                if (ans)
                    return ans;
                totalWorkWeekly.remove(pair);
                totalWorkWeekly.remove(pair2);
                pair.workDone = pair.workDone - 8;
                pair2.workDone = pair2.workDone - 8;
                totalWorkWeekly.add(pair);
                totalWorkWeekly.add(pair2);
            }
        }

        return ans;
    }

    public static boolean schedulable(
            HashMap<String, ArrayList<String>> requests
    ) {

        PriorityQueue<Employee> totalWorkWeekly = new PriorityQueue<>(new Comparator<Employee>() {
            public int compare(Employee a, Employee b) {
                return a.workDone - b.workDone;
            }
        });

        HashMap<String, HashSet<String>> requestsModified = new HashMap<>();

        for (var entry : requests.entrySet()) {
            String name = entry.getKey();
            totalWorkWeekly.add(new Employee(name, 0));

            for (String day : entry.getValue()) {
                if (!requestsModified.containsKey(day)) {
                    requestsModified.put(day, new HashSet<>());
                }
                requestsModified.get(day).add(name);
            }
        }

        Boolean result = canSchedule(requestsModified, totalWorkWeekly, 0);

        PrintingPress.printMessage(result);
        return result;
    }
}