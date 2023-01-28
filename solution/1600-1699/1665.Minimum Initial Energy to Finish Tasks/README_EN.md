# [1665. Minimum Initial Energy to Finish Tasks](https://leetcode.com/problems/minimum-initial-energy-to-finish-tasks)

[中文文档](/solution/1600-1699/1665.Minimum%20Initial%20Energy%20to%20Finish%20Tasks/README.md)

## Description

<p>You are given an array <code>tasks</code> where <code>tasks[i] = [actual<sub>i</sub>, minimum<sub>i</sub>]</code>:</p>

<ul>
	<li><code>actual<sub>i</sub></code> is the actual amount of energy you <strong>spend to finish</strong> the <code>i<sup>th</sup></code> task.</li>
	<li><code>minimum<sub>i</sub></code> is the minimum amount of energy you <strong>require to begin</strong> the <code>i<sup>th</sup></code> task.</li>
</ul>

<p>For example, if the task is <code>[10, 12]</code> and your current energy is <code>11</code>, you cannot start this task. However, if your current energy is <code>13</code>, you can complete this task, and your energy will be <code>3</code> after finishing it.</p>

<p>You can finish the tasks in <strong>any order</strong> you like.</p>

<p>Return <em>the <strong>minimum</strong> initial amount of energy you will need</em> <em>to finish all the tasks</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> tasks = [[1,2],[2,4],[4,8]]
<strong>Output:</strong> 8
<strong>Explanation:</strong>
Starting with 8 energy, we finish the tasks in the following order:
    - 3rd task. Now energy = 8 - 4 = 4.
    - 2nd task. Now energy = 4 - 2 = 2.
    - 1st task. Now energy = 2 - 1 = 1.
Notice that even though we have leftover energy, starting with 7 energy does not work because we cannot do the 3rd task.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> tasks = [[1,3],[2,4],[10,11],[10,12],[8,9]]
<strong>Output:</strong> 32
<strong>Explanation:</strong>
Starting with 32 energy, we finish the tasks in the following order:
    - 1st task. Now energy = 32 - 1 = 31.
    - 2nd task. Now energy = 31 - 2 = 29.
    - 3rd task. Now energy = 29 - 10 = 19.
    - 4th task. Now energy = 19 - 10 = 9.
    - 5th task. Now energy = 9 - 8 = 1.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> tasks = [[1,7],[2,8],[3,9],[4,10],[5,11],[6,12]]
<strong>Output:</strong> 27
<strong>Explanation:</strong>
Starting with 27 energy, we finish the tasks in the following order:
    - 5th task. Now energy = 27 - 5 = 22.
    - 2nd task. Now energy = 22 - 2 = 20.
    - 3rd task. Now energy = 20 - 3 = 17.
    - 1st task. Now energy = 17 - 1 = 16.
    - 4th task. Now energy = 16 - 4 = 12.
    - 6th task. Now energy = 12 - 6 = 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= tasks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= actual<sub>​i</sub>&nbsp;&lt;= minimum<sub>i</sub>&nbsp;&lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumEffort(self, tasks: List[List[int]]) -> int:
        ans = cur = 0
        for a, m in sorted(tasks, key=lambda x: x[0] - x[1]):
            if cur < m:
                ans += m - cur
                cur = m
            cur -= a
        return ans
```

### **Java**

```java
class Solution {
    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[0] - b[0] - (a[1] - b[1]));
        int ans = 0, cur = 0;
        for (var task : tasks) {
            int a = task[0], m = task[1];
            if (cur < m) {
                ans += m - cur;
                cur = m;
            }
            cur -= a;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumEffort(vector<vector<int>>& tasks) {
        sort(tasks.begin(), tasks.end(), [&](const auto& a, const auto& b) { return a[0] - a[1] < b[0] - b[1]; });
        int ans = 0, cur = 0;
        for (auto& task : tasks) {
            int a = task[0], m = task[1];
            if (cur < m) {
                ans += m - cur;
                cur = m;
            }
            cur -= a;
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumEffort(tasks [][]int) (ans int) {
	sort.Slice(tasks, func(i, j int) bool { return tasks[i][0]-tasks[i][1] < tasks[j][0]-tasks[j][1] })
	cur := 0
	for _, task := range tasks {
		a, m := task[0], task[1]
		if cur < m {
			ans += m - cur
			cur = m
		}
		cur -= a
	}
	return
}
```

### **TypeScript**

```ts
function minimumEffort(tasks: number[][]): number {
    tasks.sort((a, b) => a[0] - a[1] - (b[0] - b[1]));
    let ans = 0;
    let cur = 0;
    for (const [a, m] of tasks) {
        if (cur < m) {
            ans += m - cur;
            cur = m;
        }
        cur -= a;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
