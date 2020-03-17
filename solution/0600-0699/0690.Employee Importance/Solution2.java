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
        int ans = 0;
        for (Employee item : employees) {
            if (item.id == id) {
                if (item.subordinates.size() == 0) {
                    return item.importance;
                }
                ans += item.importance;
                for (Employee e : item.subordinates) {
                    ans += getImportance(employees, e.id);
                }
                return ans;
            }
        }
        return ans;
    }
}