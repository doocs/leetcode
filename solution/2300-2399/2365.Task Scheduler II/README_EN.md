# [2365. Task Scheduler II](https://leetcode.com/problems/task-scheduler-ii)

[中文文档](/solution/2300-2399/2365.Task%20Scheduler%20II/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of positive integers <code>tasks</code>, representing tasks that need to be completed <strong>in order</strong>, where <code>tasks[i]</code> represents the <strong>type</strong> of the <code>i<sup>th</sup></code> task.</p>

<p>You are also given a positive integer <code>space</code>, which represents the <strong>minimum</strong> number of days that must pass <strong>after</strong> the completion of a task before another task of the <strong>same</strong> type can be performed.</p>

<p>Each day, until all tasks have been completed, you must either:</p>

<ul>
	<li>Complete the next task from <code>tasks</code>, or</li>
	<li>Take a break.</li>
</ul>

<p>Return<em> the <strong>minimum</strong> number of days needed to complete all tasks</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> tasks = [1,2,1,2,3,1], space = 3
<strong>Output:</strong> 9
<strong>Explanation:</strong>
One way to complete all tasks in 9 days is as follows:
Day 1: Complete the 0th task.
Day 2: Complete the 1st task.
Day 3: Take a break.
Day 4: Take a break.
Day 5: Complete the 2nd task.
Day 6: Complete the 3rd task.
Day 7: Take a break.
Day 8: Complete the 4th task.
Day 9: Complete the 5th task.
It can be shown that the tasks cannot be completed in less than 9 days.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> tasks = [5,8,8,5], space = 2
<strong>Output:</strong> 6
<strong>Explanation:</strong>
One way to complete all tasks in 6 days is as follows:
Day 1: Complete the 0th task.
Day 2: Complete the 1st task.
Day 3: Take a break.
Day 4: Take a break.
Day 5: Complete the 2nd task.
Day 6: Complete the 3rd task.
It can be shown that the tasks cannot be completed in less than 6 days.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= tasks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= tasks[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= space &lt;= tasks.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def taskSchedulerII(self, tasks: List[int], space: int) -> int:
        day = defaultdict(int)
        ans = 0
        for task in tasks:
            ans += 1
            ans = max(ans, day[task])
            day[task] = ans + space + 1
        return ans
```

### **Java**

```java
class Solution {
    public long taskSchedulerII(int[] tasks, int space) {
        Map<Integer, Long> day = new HashMap<>();
        long ans = 0;
        for (int task : tasks) {
            ++ans;
            ans = Math.max(ans, day.getOrDefault(task, 0L));
            day.put(task, ans + space + 1);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long taskSchedulerII(vector<int>& tasks, int space) {
        unordered_map<int, long long> day;
        long long ans = 0;
        for (int& task : tasks) {
            ++ans;
            ans = max(ans, day[task]);
            day[task] = ans + space + 1;
        }
        return ans;
    }
};
```

### **Go**

```go
func taskSchedulerII(tasks []int, space int) (ans int64) {
	day := map[int]int64{}
	for _, task := range tasks {
		ans++
		if ans < day[task] {
			ans = day[task]
		}
		day[task] = ans + int64(space) + 1
	}
	return
}
```

### **TypeScript**

```ts
function taskSchedulerII(tasks: number[], space: number): number {
    const day = new Map<number, number>();
    let ans = 0;
    for (const task of tasks) {
        ++ans;
        ans = Math.max(ans, day.get(task) ?? 0);
        day.set(task, ans + space + 1);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
