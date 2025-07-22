---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0690.Employee%20Importance/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Array
    - Hash Table
---

<!-- problem:start -->

# [690. Employee Importance](https://leetcode.com/problems/employee-importance)

[中文文档](/solution/0600-0699/0690.Employee%20Importance/README.md)

## Description

<!-- description:start -->

<p>You have a data structure of employee information, including the employee&#39;s unique ID, importance value, and direct subordinates&#39; IDs.</p>

<p>You are given an array of employees <code>employees</code> where:</p>

<ul>
	<li><code>employees[i].id</code> is the ID of the <code>i<sup>th</sup></code> employee.</li>
	<li><code>employees[i].importance</code> is the importance value of the <code>i<sup>th</sup></code> employee.</li>
	<li><code>employees[i].subordinates</code> is a list of the IDs of the direct subordinates of the <code>i<sup>th</sup></code> employee.</li>
</ul>

<p>Given an integer <code>id</code> that represents an employee&#39;s ID, return <em>the <strong>total</strong> importance value of this employee and all their direct and indirect subordinates</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0690.Employee%20Importance/images/emp1-tree.jpg" style="width: 400px; height: 258px;" />
<pre>
<strong>Input:</strong> employees = [[1,5,[2,3]],[2,3,[]],[3,3,[]]], id = 1
<strong>Output:</strong> 11
<strong>Explanation:</strong> Employee 1 has an importance value of 5 and has two direct subordinates: employee 2 and employee 3.
They both have an importance value of 3.
Thus, the total importance value of employee 1 is 5 + 3 + 3 = 11.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0690.Employee%20Importance/images/emp2-tree.jpg" style="width: 362px; height: 361px;" />
<pre>
<strong>Input:</strong> employees = [[1,2,[5]],[5,-3,[]]], id = 5
<strong>Output:</strong> -3
<strong>Explanation:</strong> Employee 5 has an importance value of -3 and has no direct subordinates.
Thus, the total importance value of employee 5 is -3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= employees.length &lt;= 2000</code></li>
	<li><code>1 &lt;= employees[i].id &lt;= 2000</code></li>
	<li>All <code>employees[i].id</code> are <strong>unique</strong>.</li>
	<li><code>-100 &lt;= employees[i].importance &lt;= 100</code></li>
	<li>One employee has at most one direct leader and may have several subordinates.</li>
	<li>The IDs in <code>employees[i].subordinates</code> are valid IDs.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + DFS

We use a hash table $d$ to store all employee information, where the key is the employee's ID, and the value is the employee object. Then we start a depth-first search from the given employee ID. Each time we traverse to an employee, we add the employee's importance to the answer, and recursively traverse all the subordinates of the employee, adding the importance of the subordinates to the answer as well.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the number of employees.

<!-- tabs:start -->

#### Python3

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
    def getImportance(self, employees: List["Employee"], id: int) -> int:
        def dfs(i: int) -> int:
            return d[i].importance + sum(dfs(j) for j in d[i].subordinates)

        d = {e.id: e for e in employees}
        return dfs(id)
```

#### Java

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
    private final Map<Integer, Employee> d = new HashMap<>();

    public int getImportance(List<Employee> employees, int id) {
        for (var e : employees) {
            d.put(e.id, e);
        }
        return dfs(id);
    }

    private int dfs(int i) {
        Employee e = d.get(i);
        int s = e.importance;
        for (int j : e.subordinates) {
            s += dfs(j);
        }
        return s;
    }
}
```

#### C++

```cpp
/*
// Definition for Employee.
class Employee {
public:
    int id;
    int importance;
    vector<int> subordinates;
};
*/

class Solution {
public:
    int getImportance(vector<Employee*> employees, int id) {
        unordered_map<int, Employee*> d;
        for (auto& e : employees) {
            d[e->id] = e;
        }
        function<int(int)> dfs = [&](int i) -> int {
            int s = d[i]->importance;
            for (int j : d[i]->subordinates) {
                s += dfs(j);
            }
            return s;
        };
        return dfs(id);
    }
};
```

#### Go

```go
/**
 * Definition for Employee.
 * type Employee struct {
 *     Id int
 *     Importance int
 *     Subordinates []int
 * }
 */

func getImportance(employees []*Employee, id int) int {
	d := map[int]*Employee{}
	for _, e := range employees {
		d[e.Id] = e
	}
	var dfs func(int) int
	dfs = func(i int) int {
		s := d[i].Importance
		for _, j := range d[i].Subordinates {
			s += dfs(j)
		}
		return s
	}
	return dfs(id)
}
```

#### TypeScript

```ts
/**
 * Definition for Employee.
 * class Employee {
 *     id: number
 *     importance: number
 *     subordinates: number[]
 *     constructor(id: number, importance: number, subordinates: number[]) {
 *         this.id = (id === undefined) ? 0 : id;
 *         this.importance = (importance === undefined) ? 0 : importance;
 *         this.subordinates = (subordinates === undefined) ? [] : subordinates;
 *     }
 * }
 */

function getImportance(employees: Employee[], id: number): number {
    const d = new Map<number, Employee>();
    for (const e of employees) {
        d.set(e.id, e);
    }
    const dfs = (i: number): number => {
        let s = d.get(i)!.importance;
        for (const j of d.get(i)!.subordinates) {
            s += dfs(j);
        }
        return s;
    };
    return dfs(id);
}
```

#### JavaScript

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
    const d = new Map();
    for (const e of employees) {
        d.set(e.id, e);
    }
    const dfs = i => {
        let s = d.get(i).importance;
        for (const j of d.get(i).subordinates) {
            s += dfs(j);
        }
        return s;
    };
    return dfs(id);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
