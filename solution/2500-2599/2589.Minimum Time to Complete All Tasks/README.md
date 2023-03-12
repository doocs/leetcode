# [2589. 完成所有任务的最少时间](https://leetcode.cn/problems/minimum-time-to-complete-all-tasks)

[English Version](/solution/2500-2599/2589.Minimum%20Time%20to%20Complete%20All%20Tasks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有一台电脑，它可以 <strong>同时</strong>&nbsp;运行无数个任务。给你一个二维整数数组&nbsp;<code>tasks</code>&nbsp;，其中&nbsp;<code>tasks[i] = [start<sub>i</sub>, end<sub>i</sub>, duration<sub>i</sub>]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个任务需要在 <strong>闭区间</strong>&nbsp;时间段&nbsp;<code>[start<sub>i</sub>, end<sub>i</sub>]</code>&nbsp;内运行&nbsp;<code>duration<sub>i</sub></code>&nbsp;个整数时间点（但不需要连续）。</p>

<p>当电脑需要运行任务时，你可以打开电脑，如果空闲时，你可以将电脑关闭。</p>

<p>请你返回完成所有任务的情况下，电脑最少需要运行多少秒。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>tasks = [[2,3,1],[4,5,1],[1,5,2]]
<b>输出：</b>2
<b>解释：</b>
- 第一个任务在闭区间 [2, 2] 运行。
- 第二个任务在闭区间 [5, 5] 运行。
- 第三个任务在闭区间 [2, 2] 和 [5, 5] 运行。
电脑总共运行 2 个整数时间点。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>tasks = [[1,3,2],[2,5,3],[5,6,2]]
<b>输出：</b>4
<b>解释：</b>
- 第一个任务在闭区间 [2, 3] 运行
- 第二个任务在闭区间 [2, 3] 和 [5, 5] 运行。
- 第三个任务在闭区间 [5, 6] 运行。
电脑总共运行 4 个整数时间点。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= tasks.length &lt;= 2000</code></li>
	<li><code>tasks[i].length == 3</code></li>
	<li><code>1 &lt;= start<sub>i</sub>, end<sub>i</sub> &lt;= 2000</code></li>
	<li><code>1 &lt;= duration<sub>i</sub> &lt;= end<sub>i</sub> - start<sub>i</sub> + 1 </code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 排序**

我们观察发现，题目相当于在每一个区间 $[start,..,end]$ 中，选择 $duration$ 个整数时间点，使得总共选择的整数时间点最少。

因此，我们可以先对 $tasks$ 按照结束时间 $end$ 从小到大排序。然后贪心地进行选择，对于每一个任务，我们从结束时间 $end$ 开始，从后往前选择尽可能靠后的点，这样这些点更有可能被后面的任务重复利用。

我们在实现上，可以用一个长度为 $2010$ 的数组 $vis$ 记录每个时间点是否被选择过。然后对于每一个任务，我们先统计 $[start,..,end]$ 区间内已经被选择过的点的个数 $cnt$，然后从后往前选择 $duration - cnt$ 个点，同时记录选择的点的个数 $ans$ 以及更新 $vis$ 数组。

最后，我们返回 $ans$ 即可。

时间复杂度 $O(n \times \log n + n \times m)$，空间复杂度 $O(m)$。其中 $n$ 和 $m$ 分别为 $tasks$ 的长度和 $vis$ 数组的长度。本题中 $m = 2010$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findMinimumTime(self, tasks: List[List[int]]) -> int:
        tasks.sort(key=lambda x: x[1])
        vis = [0] * 2010
        ans = 0
        for start, end, duration in tasks:
            duration -= sum(vis[start: end + 1])
            i = end
            while i >= start and duration > 0:
                if not vis[i]:
                    duration -= 1
                    vis[i] = 1
                    ans += 1
                i -= 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **TypeScript**

```ts
function findMinimumTime(tasks: number[][]): number {
    tasks.sort((a, b) => a[1] - b[1]);
    const vis = new Array(2010).fill(0);
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

### **...**

```

```

<!-- tabs:end -->
