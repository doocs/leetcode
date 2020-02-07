/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/

import java.util.*;

class Solution {
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        Stack<Employee> stack = new Stack<>();
        stack.add(map.get(id));
        int ant = 0;
        while (!stack.isEmpty()) {
            Employee pop = stack.pop();
            ant += pop.importance;
            for (Integer subordinate : pop.subordinates) {
                stack.add(map.get(subordinate));
            }
        }
        return ant;
    }
}