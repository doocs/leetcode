---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0690.Employee%20Importance/README.md
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [690. 员工的重要性](https://leetcode.cn/problems/employee-importance)

[English Version](/solution/0600-0699/0690.Employee%20Importance/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你有一个保存员工信息的数据结构，它包含了员工唯一的 id ，重要度和直系下属的 id 。</p>

<p>给定一个员工数组&nbsp;<code>employees</code>，其中：</p>

<ul>
	<li><code>employees[i].id</code> 是第&nbsp;<code>i</code>&nbsp;个员工的 ID。</li>
	<li><code>employees[i].importance</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;个员工的重要度。</li>
	<li><code>employees[i].subordinates</code> 是第 <code>i</code> 名员工的直接下属的 ID 列表。</li>
</ul>

<p>给定一个整数&nbsp;<code>id</code>&nbsp;表示一个员工的 ID，返回这个员工和他所有下属的重要度的 <strong>总和</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0690.Employee%20Importance/images/1716170448-dKZffb-image.png" style="width: 400px; height: 258px;" /></strong></p>

<pre>
<strong>输入：</strong>employees = [[1,5,[2,3]],[2,3,[]],[3,3,[]]], id = 1
<strong>输出：</strong>11
<strong>解释：</strong>
员工 1 自身的重要度是 5 ，他有两个直系下属 2 和 3 ，而且 2 和 3 的重要度均为 3 。因此员工 1 的总重要度是 5 + 3 + 3 = 11 。
</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0690.Employee%20Importance/images/1716170929-dkWpra-image.png" style="width: 362px; height: 361px;" /></strong></p>

<pre>
<strong>输入：</strong>employees = [[1,2,[5]],[5,-3,[]]], id = 5
<strong>输出：</strong>-3
<strong>解释：</strong>员工 5 的重要度为 -3 并且没有直接下属。
因此，员工 5 的总重要度为 -3。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= employees.length &lt;= 2000</code></li>
	<li><code>1 &lt;= employees[i].id &lt;= 2000</code></li>
	<li>所有的&nbsp;<code>employees[i].id</code>&nbsp;<strong>互不相同</strong>。</li>
	<li><code>-100 &lt;= employees[i].importance &lt;= 100</code></li>
	<li>一名员工最多有一名直接领导，并可能有多名下属。</li>
	<li><code>employees[i].subordinates</code>&nbsp;中的 ID 都有效。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
