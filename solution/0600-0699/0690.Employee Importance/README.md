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

### 方法一：哈希表 + DFS

我们用一个哈希表 $d$ 存储所有员工的信息，其中键是员工的 ID，值是员工对象。然后我们从给定的员工 ID 开始深度优先搜索，每次遍历到一个员工时，将该员工的重要度加到答案中，并递归遍历该员工的所有下属，将下属的重要度也加到答案中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是员工的数量。

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
