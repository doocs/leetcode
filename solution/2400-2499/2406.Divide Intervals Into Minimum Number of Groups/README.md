# [2406. 将区间分为最少组数](https://leetcode.cn/problems/divide-intervals-into-minimum-number-of-groups)

[English Version](/solution/2400-2499/2406.Divide%20Intervals%20Into%20Minimum%20Number%20of%20Groups/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组&nbsp;<code>intervals</code>&nbsp;，其中&nbsp;<code>intervals[i] = [left<sub>i</sub>, right<sub>i</sub>]</code>&nbsp;表示 <strong>闭</strong>&nbsp;区间&nbsp;<code>[left<sub>i</sub>, right<sub>i</sub>]</code>&nbsp;。</p>

<p>你需要将&nbsp;<code>intervals</code> 划分为一个或者多个区间&nbsp;<strong>组</strong>&nbsp;，每个区间 <b>只</b>&nbsp;属于一个组，且同一个组中任意两个区间 <strong>不相交</strong>&nbsp;。</p>

<p>请你返回 <strong>最少</strong>&nbsp;需要划分成多少个组。</p>

<p>如果两个区间覆盖的范围有重叠（即至少有一个公共数字），那么我们称这两个区间是 <strong>相交</strong>&nbsp;的。比方说区间&nbsp;<code>[1, 5]</code> 和&nbsp;<code>[5, 8]</code>&nbsp;相交。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
<b>输出：</b>3
<b>解释：</b>我们可以将区间划分为如下的区间组：
- 第 1 组：[1, 5] ，[6, 8] 。
- 第 2 组：[2, 3] ，[5, 10] 。
- 第 3 组：[1, 10] 。
可以证明无法将区间划分为少于 3 个组。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>intervals = [[1,3],[5,6],[8,10],[11,13]]
<b>输出：</b>1
<b>解释：</b>所有区间互不相交，所以我们可以把它们全部放在一个组内。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 10<sup>5</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>1 &lt;= left<sub>i</sub> &lt;= right<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 优先队列（小根堆）**

先将区间按左端点排序，用小根堆维护每组的最右端点（堆顶是所有组的最右端点的最小值）。遍历每个区间：

-   若当前区间左端点大于堆顶元素，说明当前区间可以加入到堆顶元素所在的组中，我们直接弹出堆顶元素，然后将当前区间的右端点放入堆中；
-   否则，说明当前没有组能容纳当前区间，那么我们就新建一个组，将当前区间的右端点放入堆中。

时间复杂度 $O(n\log n)$。其中 $n$ 是数组 `intervals` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minGroups(self, intervals: List[List[int]]) -> int:
        h = []
        for a, b in sorted(intervals):
            if h and h[0] < a:
                heappop(h)
            heappush(h, b)
        return len(h)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (var e : intervals) {
            if (!q.isEmpty() && q.peek() < e[0]) {
                q.poll();
            }
            q.offer(e[1]);
        }
        return q.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minGroups(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end());
        priority_queue<int, vector<int>, greater<int>> q;
        for (auto& e : intervals) {
            if (q.size() && q.top() < e[0]) {
                q.pop();
            }
            q.push(e[1]);
        }
        return q.size();
    }
};
```

### **Go**

```go
func minGroups(intervals [][]int) int {
	sort.Slice(intervals, func(i, j int) bool { return intervals[i][0] < intervals[j][0] })
	q := hp{}
	for _, e := range intervals {
		if q.Len() > 0 && q.IntSlice[0] < e[0] {
			heap.Pop(&q)
		}
		heap.Push(&q, e[1])
	}
	return q.Len()
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
