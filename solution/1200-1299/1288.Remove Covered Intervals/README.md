# [1288. 删除被覆盖区间](https://leetcode.cn/problems/remove-covered-intervals)

[English Version](/solution/1200-1299/1288.Remove%20Covered%20Intervals/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。</p>

<p>只有当&nbsp;<code>c &lt;= a</code>&nbsp;且&nbsp;<code>b &lt;= d</code>&nbsp;时，我们才认为区间&nbsp;<code>[a,b)</code> 被区间&nbsp;<code>[c,d)</code> 覆盖。</p>

<p>在完成所有删除操作后，请你返回列表中剩余区间的数目。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,4],[3,6],[2,8]]
<strong>输出：</strong>2
<strong>解释：</strong>区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong>​​​​​​</p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 1000</code></li>
	<li><code>0 &lt;= intervals[i][0] &lt;&nbsp;intervals[i][1] &lt;= 10^5</code></li>
	<li>对于所有的&nbsp;<code>i != j</code>：<code>intervals[i] != intervals[j]</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

对起点按升序排列，若起点相同，则对终点按降序排列。

设 cnt 表示没有被覆盖的区间数，初始化为 1，pre 表示前一个未被覆盖的区间，初始化为 `intervals[0]`。

从下标 1 开始遍历区间列表，若 `pre[1] < intervals[i][1]`，说明当前区间不被前一个区间覆盖，`cnt++`，并且更新 pre 为 `intervals[i]`。否则表示当前区间被前一个区间覆盖，不做任何操作。

最后返回 cnt 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def removeCoveredIntervals(self, intervals: List[List[int]]) -> int:
        intervals.sort(key=lambda x: (x[0], -x[1]))
        cnt, pre = 1, intervals[0]
        for e in intervals[1:]:
            if pre[1] < e[1]:
                cnt += 1
                pre = e
        return cnt
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0] == 0 ? b[1] - a[1] : a[0] - b[0]);
        int[] pre = intervals[0];
        int cnt = 1;
        for (int i = 1; i < intervals.length; ++i) {
            if (pre[1] < intervals[i][1]) {
                ++cnt;
                pre = intervals[i];
            }
        }
        return cnt;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int removeCoveredIntervals(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end(), [](const vector<int>& a, const vector<int>& b) { return a[0] == b[0] ? b[1] < a[1] : a[0] < b[0]; });
        int cnt = 1;
        vector<int> pre = intervals[0];
        for (int i = 1; i < intervals.size(); ++i) {
            if (pre[1] < intervals[i][1]) {
                ++cnt;
                pre = intervals[i];
            }
        }
        return cnt;
    }
};
```

### **Go**

```go
func removeCoveredIntervals(intervals [][]int) int {
	sort.Slice(intervals, func(i, j int) bool {
		if intervals[i][0] == intervals[j][0] {
			return intervals[j][1] < intervals[i][1]
		}
		return intervals[i][0] < intervals[j][0]
	})
	cnt := 1
	pre := intervals[0]
	for i := 1; i < len(intervals); i++ {
		if pre[1] < intervals[i][1] {
			cnt++
			pre = intervals[i]
		}
	}
	return cnt
}
```

### **...**

```

```

<!-- tabs:end -->
