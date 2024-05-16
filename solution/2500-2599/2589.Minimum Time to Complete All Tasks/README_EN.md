---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2589.Minimum%20Time%20to%20Complete%20All%20Tasks/README_EN.md
rating: 2380
source: Weekly Contest 336 Q4
tags:
    - Stack
    - Greedy
    - Array
    - Binary Search
    - Sorting
---

# [2589. Minimum Time to Complete All Tasks](https://leetcode.com/problems/minimum-time-to-complete-all-tasks)

[中文文档](/solution/2500-2599/2589.Minimum%20Time%20to%20Complete%20All%20Tasks/README.md)

## Description

<p>There is a computer that can run an unlimited number of tasks <strong>at the same time</strong>. You are given a 2D integer array <code>tasks</code> where <code>tasks[i] = [start<sub>i</sub>, end<sub>i</sub>, duration<sub>i</sub>]</code> indicates that the <code>i<sup>th</sup></code> task should run for a total of <code>duration<sub>i</sub></code> seconds (not necessarily continuous) within the <strong>inclusive</strong> time range <code>[start<sub>i</sub>, end<sub>i</sub>]</code>.</p>

<p>You may turn on the computer only when it needs to run a task. You can also turn it off if it is idle.</p>

<p>Return <em>the minimum time during which the computer should be turned on to complete all tasks</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> tasks = [[2,3,1],[4,5,1],[1,5,2]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
- The first task can be run in the inclusive time range [2, 2].
- The second task can be run in the inclusive time range [5, 5].
- The third task can be run in the two inclusive time ranges [2, 2] and [5, 5].
The computer will be on for a total of 2 seconds.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> tasks = [[1,3,2],[2,5,3],[5,6,2]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> 
- The first task can be run in the inclusive time range [2, 3].
- The second task can be run in the inclusive time ranges [2, 3] and [5, 5].
- The third task can be run in the two inclusive time range [5, 6].
The computer will be on for a total of 4 seconds.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= tasks.length &lt;= 2000</code></li>
	<li><code>tasks[i].length == 3</code></li>
	<li><code>1 &lt;= start<sub>i</sub>, end<sub>i</sub> &lt;= 2000</code></li>
	<li><code>1 &lt;= duration<sub>i</sub> &lt;= end<sub>i</sub> - start<sub>i</sub> + 1 </code></li>
</ul>

## Solutions

### Solution 1: Greedy + Sorting

We observe that the problem is equivalent to selecting $duration$ integer time points in each interval $[start,..,end]$, so that the total number of selected integer time points is minimized.

Therefore, we can first sort $tasks$ in ascending order of end time $end$. Then we greedily make selections. For each task, we start from the end time $end$ and choose the points as late as possible from back to front. This way, these points are more likely to be reused by later tasks.

In our implementation, we can use an array $vis$ of length $2010$ to record whether each time point has been selected. Then for each task, we first count the number of points $cnt$ that have been selected in the interval $[start,..,end]$, and then select $duration - cnt$ points from back to front, while recording the number of selected points $ans$ and updating the $vis$ array.

Finally, we return $ans$.

The time complexity is $O(n \times \log n + n \times m)$, and the space complexity is $O(m)$. Here, $n$ and $m$ are the lengths of $tasks$ and $vis$ array, respectively. In this problem, $m = 2010$.

<!-- tabs:start -->

```python
class Solution:
    def findMinimumTime(self, tasks: List[List[int]]) -> int:
        tasks.sort(key=lambda x: x[1])
        vis = [0] * 2010
        ans = 0
        for start, end, duration in tasks:
            duration -= sum(vis[start : end + 1])
            i = end
            while i >= start and duration > 0:
                if not vis[i]:
                    duration -= 1
                    vis[i] = 1
                    ans += 1
                i -= 1
        return ans
```

```java
class Solution {
    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        int[] vis = new int[2010];
        int ans = 0;
        for (var task : tasks) {
            int start = task[0], end = task[1], duration = task[2];
            for (int i = start; i <= end; ++i) {
                duration -= vis[i];
            }
            for (int i = end; i >= start && duration > 0; --i) {
                if (vis[i] == 0) {
                    --duration;
                    ans += vis[i] = 1;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findMinimumTime(vector<vector<int>>& tasks) {
        sort(tasks.begin(), tasks.end(), [&](auto& a, auto& b) { return a[1] < b[1]; });
        bitset<2010> vis;
        int ans = 0;
        for (auto& task : tasks) {
            int start = task[0], end = task[1], duration = task[2];
            for (int i = start; i <= end; ++i) {
                duration -= vis[i];
            }
            for (int i = end; i >= start && duration > 0; --i) {
                if (!vis[i]) {
                    --duration;
                    ans += vis[i] = 1;
                }
            }
        }
        return ans;
    }
};
```

```go
func findMinimumTime(tasks [][]int) (ans int) {
	sort.Slice(tasks, func(i, j int) bool { return tasks[i][1] < tasks[j][1] })
	vis := [2010]int{}
	for _, task := range tasks {
		start, end, duration := task[0], task[1], task[2]
		for _, x := range vis[start : end+1] {
			duration -= x
		}
		for i := end; i >= start && duration > 0; i-- {
			if vis[i] == 0 {
				vis[i] = 1
				duration--
				ans++
			}
		}
	}
	return
}
```

```ts
function findMinimumTime(tasks: number[][]): number {
    tasks.sort((a, b) => a[1] - b[1]);
    const vis: number[] = Array(2010).fill(0);
    let ans = 0;
    for (let [start, end, duration] of tasks) {
        for (let i = start; i <= end; ++i) {
            duration -= vis[i];
        }
        for (let i = end; i >= start && duration > 0; --i) {
            if (vis[i] === 0) {
                --duration;
                ans += vis[i] = 1;
            }
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn find_minimum_time(tasks: Vec<Vec<i32>>) -> i32 {
        let mut tasks = tasks;
        tasks.sort_by(|a, b| a[1].cmp(&b[1]));
        let mut vis = vec![0; 2010];
        let mut ans = 0;

        for task in tasks {
            let start = task[0] as usize;
            let end = task[1] as usize;
            let mut duration = task[2] - vis[start..=end].iter().sum::<i32>();
            let mut i = end;

            while i >= start && duration > 0 {
                if vis[i] == 0 {
                    duration -= 1;
                    vis[i] = 1;
                    ans += 1;
                }
                i -= 1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
