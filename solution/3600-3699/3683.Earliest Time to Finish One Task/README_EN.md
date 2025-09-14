---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3683.Earliest%20Time%20to%20Finish%20One%20Task/README_EN.md
---

<!-- problem:start -->

# [3683. Earliest Time to Finish One Task](https://leetcode.com/problems/earliest-time-to-finish-one-task)

[中文文档](/solution/3600-3699/3683.Earliest%20Time%20to%20Finish%20One%20Task/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>tasks</code> where <code>tasks[i] = [s<sub>i</sub>, t<sub>i</sub>]</code>.</p>

<p>Each <code>[s<sub>i</sub>, t<sub>i</sub>]</code> in <code>tasks</code> represents a task with start time <code>s<sub>i</sub></code> that takes <code>t<sub>i</sub></code> units of time to finish.</p>

<p>Return the earliest time at which at least one task is finished.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">tasks = [[1,6],[2,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The first task starts at time <code>t = 1</code> and finishes at time <code>1 + 6 = 7</code>. The second task finishes at time <code>2 + 3 = 5</code>. You can finish one task at time 5.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">tasks = [[100,100],[100,100],[100,100]]</span></p>

<p><strong>Output:</strong> <span class="example-io">200</span></p>

<p><strong>Explanation:</strong></p>

<p>All three tasks finish at time <code>100 + 100 = 200</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= tasks.length &lt;= 100</code></li>
	<li><code>tasks[i] = [s<sub>i</sub>, t<sub>i</sub>]</code></li>
	<li><code>1 &lt;= s<sub>i</sub>, t<sub>i</sub> &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Single Pass

We iterate through the $\textit{tasks}$ array and, for each task, calculate its completion time $s_i + t_i$. The minimum of all task completion times is the earliest time to finish at least one task.

The time complexity is $O(n)$, where $n$ is the length of the $\textit{tasks}$ array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def earliestTime(self, tasks: List[List[int]]) -> int:
        return min(s + t for s, t in tasks)
```

#### Java

```java
class Solution {
    public int earliestTime(int[][] tasks) {
        int ans = 200;
        for (var task : tasks) {
            ans = Math.min(ans, task[0] + task[1]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int earliestTime(vector<vector<int>>& tasks) {
        int ans = 200;
        for (const auto& task : tasks) {
            ans = min(ans, task[0] + task[1]);
        }
        return ans;
    }
};
```

#### Go

```go
func earliestTime(tasks [][]int) int {
	ans := 200
	for _, task := range tasks {
		ans = min(ans, task[0]+task[1])
	}
	return ans
}
```

#### TypeScript

```ts
function earliestTime(tasks: number[][]): number {
    return Math.min(...tasks.map(task => task[0] + task[1]));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
