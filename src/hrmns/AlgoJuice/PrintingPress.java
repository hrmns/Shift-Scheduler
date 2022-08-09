package hrmns.AlgoJuice;

import hrmns.dto.Employee;

import java.util.Map;
import java.util.PriorityQueue;

import static hrmns.Utils.Constants.week;

/**
 * Created by: Harmanjeet Singh | github/hrmns | 2022-08-09
 */
public class PrintingPress {

    public static void printMessage(boolean schedulable) {
        if (schedulable)
            System.out.println("Shifts assigned successfully for this week");
        else
            System.out.println("Shifts for this week cannot be covered with the given params");
    }

    public static void printSchedule(PriorityQueue<Employee> totalWorkWeekly, Employee[][] schedule) {
        System.out.println("Total work to be done by each employee");

        for (var pair : totalWorkWeekly) {
            System.out.println(pair.name + " - " + pair.workDone + "hrs");
        }

        System.out.println("weekly Schedule:");
        for (int i = 0; i < 7; i++) {
            System.out.println(week.get(i) + ": " + schedule[i][0].name + " and " + schedule[i][1].name);
        }

    }

    public static void printSchedule(Map<String, Integer> totalWorkWeekly, Employee[][] schedule) {
        System.out.println("Total work to be done by each employee");

        for (var pair : totalWorkWeekly.entrySet()) {
            System.out.println(pair.getKey() + " - " + pair.getValue() + "hrs");
        }

        System.out.println("weekly Schedule:");

        for (int i = 0; i < 7; i++) {
            System.out.println(week.get(i) + ": " + schedule[i][0].name + " and " + schedule[i][1].name);
        }
    }
}
