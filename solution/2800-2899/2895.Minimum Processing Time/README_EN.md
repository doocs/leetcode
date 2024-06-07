---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2895.Minimum%20Processing%20Time/README_EN.md
rating: 1351
source: Weekly Contest 366 Q2
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [2895. Minimum Processing Time](https://leetcode.com/problems/minimum-processing-time)

[中文文档](/solution/2800-2899/2895.Minimum%20Processing%20Time/README.md)

## Description

<!-- description:start -->

<p>You have a certain number of processors, each having 4 cores. The number of tasks to be executed is four times the number of processors. Each task must be assigned to a unique core, and each core can only be used once.</p>

<p>You are given an array <code>processorTime</code> representing the time each processor becomes available and an array <code>tasks</code> representing how long each task takes to complete. Return the&nbsp;<em>minimum</em> time needed to complete all tasks.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">processorTime = [8,10], tasks = [2,2,3,1,8,7,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">16</span></p>

<p><strong>Explanation:</strong></p>

<p>Assign the tasks at indices 4, 5, 6, 7 to the first processor which becomes available at <code>time = 8</code>, and the tasks at indices 0, 1, 2, 3 to the second processor which becomes available at <code>time = 10</code>.&nbsp;</p>

<p>The time taken by the first processor to finish the execution of all tasks is&nbsp;<code>max(8 + 8, 8 + 7, 8 + 4, 8 + 5) = 16</code>.</p>

<p>The time taken by the second processor to finish the execution of all tasks is&nbsp;<code>max(10 + 2, 10 + 2, 10 + 3, 10 + 1) = 13</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">processorTime = [10,20], tasks = [2,3,1,2,5,8,4,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">23</span></p>

<p><strong>Explanation:</strong></p>

<p>Assign the tasks at indices 1, 4, 5, 6 to the first processor and the others to the second processor.</p>

<p>The time taken by the first processor to finish the execution of all tasks is <code>max(10 + 3, 10 + 5, 10 + 8, 10 + 4) = 18</code>.</p>

<p>The time taken by the second processor to finish the execution of all tasks is <code>max(20 + 2, 20 + 1, 20 + 2, 20 + 3) = 23</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == processorTime.length &lt;= 25000</code></li>
	<li><code>1 &lt;= tasks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= processorTime[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= tasks[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>tasks.length == 4 * n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

To minimize the time required to process all tasks, the four tasks with the longest processing time should be assigned to the processors that become idle earliest.

Therefore, we sort the processors by their idle time and sort the tasks by their processing time. Then, we assign the four tasks with the longest processing time to the processor that becomes idle earliest, and calculate the maximum end time.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the number of tasks.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minProcessingTime(self, processorTime: List[int], tasks: List[int]) -> int:
        processorTime.sort()
        tasks.sort()
        ans = 0
        i = len(tasks) - 1
        for t in processorTime:
            ans = max(ans, t + tasks[i])
            i -= 4
        return ans
```

#### Java

```java
class Solution {
    public int minProcessingTime(List<Integer> processorTime, List<Integer> tasks) {
        processorTime.sort((a, b) -> a - b);
        tasks.sort((a, b) -> a - b);
        int ans = 0, i = tasks.size() - 1;
        for (int t : processorTime) {
            ans = Math.max(ans, t + tasks.get(i));
            i -= 4;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minProcessingTime(vector<int>& processorTime, vector<int>& tasks) {
        sort(processorTime.begin(), processorTime.end());
        sort(tasks.begin(), tasks.end());
        int ans = 0, i = tasks.size() - 1;
        for (int t : processorTime) {
            ans = max(ans, t + tasks[i]);
            i -= 4;
        }
        return ans;
    }
};
```

#### Go

```go
func minProcessingTime(processorTime []int, tasks []int) (ans int) {
	sort.Ints(processorTime)
	sort.Ints(tasks)
	i := len(tasks) - 1
	for _, t := range processorTime {
		ans = max(ans, t+tasks[i])
		i -= 4
	}
	return
}
```

#### TypeScript

```ts
function minProcessingTime(processorTime: number[], tasks: number[]): number {
    processorTime.sort((a, b) => a - b);
    tasks.sort((a, b) => a - b);
    let [ans, i] = [0, tasks.length - 1];
    for (const t of processorTime) {
        ans = Math.max(ans, t + tasks[i]);
        i -= 4;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
