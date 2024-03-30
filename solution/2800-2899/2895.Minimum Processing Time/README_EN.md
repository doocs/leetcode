# [2895. Minimum Processing Time](https://leetcode.com/problems/minimum-processing-time)

[中文文档](/solution/2800-2899/2895.Minimum%20Processing%20Time/README.md)

<!-- tags:Greedy,Array,Sorting -->

## Description

<p>You have <code>n</code> processors each having <code>4</code> cores and <code>n * 4</code> tasks that need to be executed such that each core should perform only <strong>one</strong> task.</p>

<p>Given a <strong>0-indexed</strong> integer array <code>processorTime</code> representing the time at which each processor becomes available for the first time and a <strong>0-indexed </strong>integer array <code>tasks</code> representing the time it takes to execute each task, return <em>the <strong>minimum</strong> time when all of the tasks have been executed by the processors.</em></p>

<p><strong>Note: </strong>Each core executes the task independently of the others.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> processorTime = [8,10], tasks = [2,2,3,1,8,7,4,5]
<strong>Output:</strong> 16
<strong>Explanation:</strong> 
It&#39;s optimal to assign the tasks at indexes 4, 5, 6, 7 to the first processor which becomes available at time = 8, and the tasks at indexes 0, 1, 2, 3 to the second processor which becomes available at time = 10. 
Time taken by the first processor to finish execution of all tasks = max(8 + 8, 8 + 7, 8 + 4, 8 + 5) = 16.
Time taken by the second processor to finish execution of all tasks = max(10 + 2, 10 + 2, 10 + 3, 10 + 1) = 13.
Hence, it can be shown that the minimum time taken to execute all the tasks is 16.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> processorTime = [10,20], tasks = [2,3,1,2,5,8,4,3]
<strong>Output:</strong> 23
<strong>Explanation:</strong> 
It&#39;s optimal to assign the tasks at indexes 1, 4, 5, 6 to the first processor which becomes available at time = 10, and the tasks at indexes 0, 2, 3, 7 to the second processor which becomes available at time = 20.
Time taken by the first processor to finish execution of all tasks = max(10 + 3, 10 + 5, 10 + 8, 10 + 4) = 18.
Time taken by the second processor to finish execution of all tasks = max(20 + 2, 20 + 1, 20 + 2, 20 + 3) = 23.
Hence, it can be shown that the minimum time taken to execute all the tasks is 23.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == processorTime.length &lt;= 25000</code></li>
	<li><code>1 &lt;= tasks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= processorTime[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= tasks[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>tasks.length == 4 * n</code></li>
</ul>

## Solutions

### Solution 1: Greedy + Sorting

To minimize the time required to process all tasks, the four tasks with the longest processing time should be assigned to the processors that become idle earliest.

Therefore, we sort the processors by their idle time and sort the tasks by their processing time. Then, we assign the four tasks with the longest processing time to the processor that becomes idle earliest, and calculate the maximum end time.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the number of tasks.

<!-- tabs:start -->

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

<!-- end -->
