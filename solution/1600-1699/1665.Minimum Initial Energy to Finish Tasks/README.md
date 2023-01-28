# [1665. 完成所有任务的最少初始能量](https://leetcode.cn/problems/minimum-initial-energy-to-finish-tasks)

[English Version](/solution/1600-1699/1665.Minimum%20Initial%20Energy%20to%20Finish%20Tasks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个任务数组 <code>tasks</code> ，其中 <code>tasks[i] = [actual<sub>i</sub>, minimum<sub>i</sub>]</code> ：</p>

<ul>
	<li><code>actual<sub>i</sub></code> 是完成第 <code>i</code> 个任务 <strong>需要耗费</strong> 的实际能量。</li>
	<li><code>minimum<sub>i</sub></code> 是开始第 <code>i</code> 个任务前需要达到的最低能量。</li>
</ul>

<p>比方说，如果任务为 <code>[10, 12]</code> 且你当前的能量为 <code>11</code> ，那么你不能开始这个任务。如果你当前的能量为 <code>13</code> ，你可以完成这个任务，且完成它后剩余能量为 <code>3</code> 。</p>

<p>你可以按照 <strong>任意顺序</strong> 完成任务。</p>

<p>请你返回完成所有任务的 <strong>最少</strong> 初始能量。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>tasks = [[1,2],[2,4],[4,8]]
<b>输出：</b>8
<strong>解释：</strong>
一开始有 8 能量，我们按照如下顺序完成任务：
    - 完成第 3 个任务，剩余能量为 8 - 4 = 4 。
    - 完成第 2 个任务，剩余能量为 4 - 2 = 2 。
    - 完成第 1 个任务，剩余能量为 2 - 1 = 1 。
注意到尽管我们有能量剩余，但是如果一开始只有 7 能量是不能完成所有任务的，因为我们无法开始第 3 个任务。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>tasks = [[1,3],[2,4],[10,11],[10,12],[8,9]]
<b>输出：</b>32
<strong>解释：</strong>
一开始有 32 能量，我们按照如下顺序完成任务：
    - 完成第 1 个任务，剩余能量为 32 - 1 = 31 。
    - 完成第 2 个任务，剩余能量为 31 - 2 = 29 。
    - 完成第 3 个任务，剩余能量为 29 - 10 = 19 。
    - 完成第 4 个任务，剩余能量为 19 - 10 = 9 。
    - 完成第 5 个任务，剩余能量为 9 - 8 = 1 。</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>tasks = [[1,7],[2,8],[3,9],[4,10],[5,11],[6,12]]
<b>输出：</b>27
<strong>解释：</strong>
一开始有 27 能量，我们按照如下顺序完成任务：
    - 完成第 5 个任务，剩余能量为 27 - 5 = 22 。
    - 完成第 2 个任务，剩余能量为 22 - 2 = 20 。
    - 完成第 3 个任务，剩余能量为 20 - 3 = 17 。
    - 完成第 1 个任务，剩余能量为 17 - 1 = 16 。
    - 完成第 4 个任务，剩余能量为 16 - 4 = 12 。
    - 完成第 6 个任务，剩余能量为 12 - 6 = 6 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= tasks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= actual<sub>​i</sub> &lt;= minimum<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 自定义排序**

我们假设任务数为 $n$，初始能量值为 $E$，考虑完成最后一个任务，这需要我们完成前 $n-1$ 个任务后，剩余的能量值不小于完成最后一个任务需要达到的能量值 $m_n$，即：

$$
E-\sum_{i=1}^{n-1} a_i \geq m_n
$$

我们可以将 $m_n$ 表示成 $a_n+(m_n - a_n)$，然后将上面的式子变形，得到：

$$
E-\sum_{i=1}^{n-1} a_i \geq a_n+(m_n - a_n)
$$

整理可得：

$$
E \geq \sum_{i=1}^{n} a_i + (m_n - a_n)
$$

其中 $\sum_{i=1}^{n} a_i$ 是固定不变的，要使得初始值能量值 $E$ 最小，我们需要让 $m_n - a_n$ 最小，即 $a_n-m_n$ 最大。

因此，我们可以将任务按照 $a_i-m_i$ 从小到大排序。然后从前往后遍历任务，对于每个任务，如果当前能量值 $cur$ 小于 $m_i$，则需要增加能量值 $m_i - cur$，使得当前能量值刚好等于 $m_i$，然后再完成任务，更新 $cur = cur - a_i$。继续遍历，直到完成所有任务，即可得到初始所需的最少能量值。

时间复杂度 $O(n\times \log n)$。其中 $n$ 为任务数。忽略排序的空间开销，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
