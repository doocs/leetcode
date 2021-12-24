# [690. Employee Importance](https://leetcode.com/problems/employee-importance)

[中文文档](/solution/0600-0699/0690.Employee%20Importance/README.md)

## Description

<p>You are given a data structure of employee information, which includes the employee&#39;s <b>unique id</b>, their&nbsp;<b>importance value</b> and their&nbsp;<b>direct</b> subordinates&#39; id.</p>

<p>For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is <b>not direct</b>.</p>

<p>Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all their&nbsp;subordinates.</p>

<p><b>Example 1:</b></p>

<pre>
<b>Input:</b> [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
<b>Output:</b> 11
<b>Explanation:</b>
Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3. They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ol>
	<li>One employee has at most one <b>direct</b> leader and may have several subordinates.</li>
	<li>The maximum number of employees won&#39;t exceed 2000.</li>
</ol>

## Solutions

"all their subordinates" include "subordinates of subordinates", first use a hash table to store the mapping relationship between `employee.id` and `employee`, and then recursively solve it (it can also be implemented with BFS)

<!-- tabs:start -->

### **Python3**

```python
"""
# Definition for Employee.
class Employee:
    def __init__(self, id: int, importance: int, subordinates: List[int]):
        self.id = id
        self.importance = importance
        self.subordinates = subordinates
"""

class Solution:
    def getImportance(self, employees: List['Employee'], id: int) -> int:
        m = {emp.id: emp for emp in employees}

        def dfs(id: int) -> int:
            emp = m[id]
            s = emp.importance
            for sub in emp.subordinates:
                s += dfs(sub)
            return s

        return dfs(id)
```

### **Java**

```java
/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/

class Solution {

    private final Map<Integer, Employee> map = new HashMap<>();

    public int getImportance(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return dfs(id);
    }

    private int dfs(int id) {
        Employee employee = map.get(id);
        int sum = employee.importance;
        for (Integer subordinate : employee.subordinates) {
            sum += dfs(subordinate);
        }
        return sum;
    }
}
```

### **JavaScript**

```js
/**
 * Definition for Employee.
 * function Employee(id, importance, subordinates) {
 *     this.id = id;
 *     this.importance = importance;
 *     this.subordinates = subordinates;
 * }
 */

/**
 * @param {Employee[]} employees
 * @param {number} id
 * @return {number}
 */
var GetImportance = function (employees, id) {
    const map = new Map();
    for (const employee of employees) {
        map.set(employee.id, employee);
    }
    const dfs = id => {
        const employee = map.get(id);
        let sum = employee.importance;
        for (const subId of employee.subordinates) {
            sum += dfs(subId);
        }
        return sum;
    };
    return dfs(id);
};
```

### **...**

```

```

<!-- tabs:end -->
