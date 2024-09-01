---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2895.Minimum%20Processing%20Time/README.md
rating: 1351
source: 第 366 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [2895. 最小处理时间](https://leetcode.cn/problems/minimum-processing-time)

[English Version](/solution/2800-2899/2895.Minimum%20Processing%20Time/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你有 <code>n</code> 颗处理器，每颗处理器都有 <code>4</code> 个核心。现有 <code>n * 4</code> 个待执行任务，每个核心只执行 <strong>一次</strong>&nbsp;任务。</p>

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>processorTime</code> ，表示每颗处理器最早空闲时间。另给你一个下标从 <strong>0</strong> 开始的整数数组 <code>tasks</code> ，表示执行每个任务所需的时间。返回所有任务都执行完毕需要的 <strong>最小时间</strong> 。</p>

<p>注意：每个核心独立执行任务。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>processorTime = [8,10], tasks = [2,2,3,1,8,7,4,5]
<strong>输出：</strong>16
<strong>解释：</strong>
最优的方案是将下标为 4, 5, 6, 7 的任务分配给第一颗处理器（最早空闲时间 time = 8），下标为 0, 1, 2, 3 的任务分配给第二颗处理器（最早空闲时间 time = 10）。 
第一颗处理器执行完所有任务需要花费的时间 = max(8 + 8, 8 + 7, 8 + 4, 8 + 5) = 16 。
第二颗处理器执行完所有任务需要花费的时间 = max(10 + 2, 10 + 2, 10 + 3, 10 + 1) = 13 。
因此，可以证明执行完所有任务需要花费的最小时间是 16 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>processorTime = [10,20], tasks = [2,3,1,2,5,8,4,3]
<strong>输出：</strong>23
<strong>解释：</strong>
最优的方案是将下标为 1, 4, 5, 6 的任务分配给第一颗处理器（最早空闲时间 time = 10），下标为 0, 2, 3, 7 的任务分配给第二颗处理器（最早空闲时间 time = 20）。 
第一颗处理器执行完所有任务需要花费的时间 = max(10 + 3, 10 + 5, 10 + 8, 10 + 4) = 18 。 
第二颗处理器执行完所有任务需要花费的时间 = max(20 + 2, 20 + 1, 20 + 2, 20 + 3) = 23 。 
因此，可以证明执行完所有任务需要花费的最小时间是 23 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == processorTime.length &lt;= 25000</code></li>
	<li><code>1 &lt;= tasks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= processorTime[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= tasks[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>tasks.length == 4 * n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 排序

要使得处理完所有任务的时间最小，那么越早处于空闲状态的处理器应该处理耗时最长的 $4$ 个任务。

因此，我们对处理器的空闲时间和任务的耗时分别进行排序，然后依次将耗时最长的 $4$ 个任务分配给空闲时间最早的处理器，计算最大的结束时间即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为任务的数量。

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
