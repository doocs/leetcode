# [2054. 两个最好的不重叠活动](https://leetcode.cn/problems/two-best-non-overlapping-events)

[English Version](/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>events</code>&nbsp;，其中&nbsp;<code>events[i] = [startTime<sub>i</sub>, endTime<sub>i</sub>, value<sub>i</sub>]</code>&nbsp;。第&nbsp;<code>i</code>&nbsp;个活动开始于&nbsp;<code>startTime<sub>i</sub></code>&nbsp;，结束于&nbsp;<code>endTime<sub>i</sub></code>&nbsp;，如果你参加这个活动，那么你可以得到价值&nbsp;<code>value<sub>i</sub></code>&nbsp;。你 <strong>最多</strong>&nbsp;可以参加&nbsp;<strong>两个时间不重叠</strong>&nbsp;活动，使得它们的价值之和 <strong>最大</strong>&nbsp;。</p>

<p>请你返回价值之和的 <strong>最大值</strong>&nbsp;。</p>

<p>注意，活动的开始时间和结束时间是 <strong>包括</strong>&nbsp;在活动时间内的，也就是说，你不能参加两个活动且它们之一的开始时间等于另一个活动的结束时间。更具体的，如果你参加一个活动，且结束时间为 <code>t</code>&nbsp;，那么下一个活动必须在&nbsp;<code>t + 1</code>&nbsp;或之后的时间开始。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/images/picture5.png" style="width: 400px; height: 75px;"></p>

<pre><b>输入：</b>events = [[1,3,2],[4,5,2],[2,4,3]]
<b>输出：</b>4
<strong>解释：</strong>选择绿色的活动 0 和 1 ，价值之和为 2 + 2 = 4 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="Example 1 Diagram" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/images/picture1.png" style="width: 400px; height: 77px;"></p>

<pre><b>输入：</b>events = [[1,3,2],[4,5,2],[1,5,5]]
<b>输出：</b>5
<strong>解释：</strong>选择活动 2 ，价值和为 5 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/images/picture3.png" style="width: 400px; height: 66px;"></p>

<pre><b>输入：</b>events = [[1,5,3],[1,5,1],[6,6,5]]
<b>输出：</b>8
<strong>解释：</strong>选择活动 0 和 2 ，价值之和为 3 + 5 = 8 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= events.length &lt;= 10<sup>5</sup></code></li>
	<li><code>events[i].length == 3</code></li>
	<li><code>1 &lt;= startTime<sub>i</sub> &lt;= endTime<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= value<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 二分查找**

时间复杂度 $O(nlogn)$，其中 $n$ 表示 $events$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxTwoEvents(self, events: List[List[int]]) -> int:
        events.sort()
        n = len(events)
        f = [events[-1][2]] * n
        for i in range(n - 2, -1, -1):
            f[i] = max(f[i + 1], events[i][2])
        ans = 0
        for _, e, v in events:
            idx = bisect_right(events, e, key=lambda x: x[0])
            if idx < n:
                v += f[idx]
            ans = max(ans, v)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int n = events.length;
        int[] f = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            f[i] = Math.max(f[i + 1], events[i][2]);
        }
        int ans = 0;
        for (int[] e : events) {
            int v = e[2];
            int left = 0, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (events[mid][0] > e[1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (left < n) {
                v += f[left];
            }
            ans = Math.max(ans, v);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxTwoEvents(vector<vector<int>>& events) {
        sort(events.begin(), events.end());
        int n = events.size();
        vector<int> f(n + 1);
        for (int i = n - 1; ~i; --i) f[i] = max(f[i + 1], events[i][2]);
        int ans = 0;
        for (auto& e : events) {
            int v = e[2];
            int left = 0, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (events[mid][0] > e[1])
                    right = mid;
                else
                    left = mid + 1;
            }
            if (left < n) v += f[left];
            ans = max(ans, v);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxTwoEvents(events [][]int) int {
	sort.Slice(events, func(i, j int) bool {
		return events[i][0] < events[j][0]
	})
	n := len(events)
	f := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		f[i] = max(f[i+1], events[i][2])
	}
	ans := 0
	for _, e := range events {
		v := e[2]
		left, right := 0, n
		for left < right {
			mid := (left + right) >> 1
			if events[mid][0] > e[1] {
				right = mid
			} else {
				left = mid + 1
			}
		}
		if left < n {
			v += f[left]
		}
		ans = max(ans, v)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
