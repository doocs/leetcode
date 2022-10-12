# [1288. Remove Covered Intervals](https://leetcode.com/problems/remove-covered-intervals)

[中文文档](/solution/1200-1299/1288.Remove%20Covered%20Intervals/README.md)

## Description

<p>Given an array <code>intervals</code> where <code>intervals[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> represent the interval <code>[l<sub>i</sub>, r<sub>i</sub>)</code>, remove all intervals that are covered by another interval in the list.</p>

<p>The interval <code>[a, b)</code> is covered by the interval <code>[c, d)</code> if and only if <code>c &lt;= a</code> and <code>b &lt;= d</code>.</p>

<p>Return <em>the number of remaining intervals</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,4],[3,6],[2,8]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Interval [3,6] is covered by [2,8], therefore it is removed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,4],[2,3]]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 1000</code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt; r<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li>All the given intervals are <strong>unique</strong>.</li>
</ul>

## Solutions

-   Sort `intervals` by increasing of `startTime` and decreasing of `endTime`.
-   `cnt = 1`: `cnt` is the result.
-   `pre = intervals[0]`: `pre` is the last interval
-   For each `interval` in `intervals`
    -   if `pre.endTime < interval.endTime`, means `interval` is not overlapped then we count `cnt`, and update `pre = interval`
    -   else we do nothing
-   Return `cnt`

<!-- tabs:start -->

### **Python3**

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
