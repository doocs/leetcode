# [2365. 任务调度器 II](https://leetcode.cn/problems/task-scheduler-ii)

[English Version](/solution/2300-2399/2365.Task%20Scheduler%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的正整数数组&nbsp;<code>tasks</code>&nbsp;，表示需要 <strong>按顺序</strong>&nbsp;完成的任务，其中&nbsp;<code>tasks[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;件任务的 <strong>类型</strong>&nbsp;。</p>

<p>同时给你一个正整数&nbsp;<code>space</code>&nbsp;，表示一个任务完成&nbsp;<strong>后</strong>&nbsp;，另一个&nbsp;<strong>相同</strong>&nbsp;类型任务完成前需要间隔的&nbsp;<strong>最少</strong>&nbsp;天数。</p>

<p>在所有任务完成前的每一天，你都必须进行以下两种操作中的一种：</p>

<ul>
	<li>完成&nbsp;<code>tasks</code>&nbsp;中的下一个任务</li>
	<li>休息一天</li>
</ul>

<p>请你返回完成所有任务所需的 <strong>最少</strong>&nbsp;天数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>tasks = [1,2,1,2,3,1], space = 3
<b>输出：</b>9
<strong>解释：</strong>
9 天完成所有任务的一种方法是：
第 1 天：完成任务 0 。
第 2 天：完成任务 1 。
第 3 天：休息。
第 4 天：休息。
第 5 天：完成任务 2 。
第 6 天：完成任务 3 。
第 7 天：休息。
第 8 天：完成任务 4 。
第 9 天：完成任务 5 。
可以证明无法少于 9 天完成所有任务。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>tasks = [5,8,8,5], space = 2
<b>输出：</b>6
<strong>解释：</strong>
6 天完成所有任务的一种方法是：
第 1 天：完成任务 0 。
第 2 天：完成任务 1 。
第 3 天：休息。
第 4 天：休息。
第 5 天：完成任务 2 。
第 6 天：完成任务 3 。
可以证明无法少于 6 天完成所有任务。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= tasks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= tasks[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= space &lt;= tasks.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 模拟**

我们可以用哈希表 $day$ 记录每个任务下一次可以被执行的时间，初始时 $day$ 中的所有值都为 $0$，用变量 $ans$ 记录当前时间。

遍历数组 $tasks$，对于每个任务 $task$，当前时间 $ans$ 加一，表示从上一次执行任务到现在已经过去了一天，如果此时 $day[task] \gt ans$，说明任务 $task$ 需要在第 $day[task]$ 天才能被执行，因此我们更新当前时间 $ans = max(ans, day[task])$。然后更新 $day[task]$ 的值为 $ans + space + 1$，表示任务 $task$ 下一次可以被执行的时间为 $ans + space + 1$。

遍历结束后，将 $ans$ 返回即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $tasks$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
