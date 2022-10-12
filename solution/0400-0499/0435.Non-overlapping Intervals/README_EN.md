# [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals)

[中文文档](/solution/0400-0499/0435.Non-overlapping%20Intervals/README.md)

## Description

<p>Given an array of intervals <code>intervals</code> where <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>, return <em>the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,2],[2,3],[3,4],[1,3]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> [1,3] can be removed and the rest of the intervals are non-overlapping.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,2],[1,2],[1,2]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> You need to remove two [1,2] to make the rest of the intervals non-overlapping.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,2],[2,3]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> You don&#39;t need to remove any of the intervals since they&#39;re already non-overlapping.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 10<sup>5</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>-5 * 10<sup>4</sup> &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= 5 * 10<sup>4</sup></code></li>
</ul>

## Solutions

Greedy.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
        intervals.sort(key=lambda x: x[1])
        ans, t = 0, intervals[0][1]
        for s, e in intervals[1:]:
            if s >= t:
                t = e
            else:
                ans += 1
        return ans
```

```python
class Solution:
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
        intervals.sort()
        d = [intervals[0][1]]
        for s, e in intervals[1:]:
            if s >= d[-1]:
                d.append(e)
            else:
                idx = bisect_left(d, s)
                d[idx] = min(d[idx], e)
        return len(intervals) - len(d)
```

### **Java**

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int t = intervals[0][1], ans = 0;
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i][0] >= t) {
                t = intervals[i][1];
            } else {
                ++ans;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        int n = intervals.length;
        int[] d = new int[n + 1];
        d[1] = intervals[0][1];
        int size = 1;
        for (int i = 1; i < n; ++i) {
            int s = intervals[i][0], e = intervals[i][1];
            if (s >= d[size]) {
                d[++size] = e;
            } else {
                int left = 1, right = size;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (d[mid] >= s) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                d[left] = Math.min(d[left], e);
            }
        }
        return n - size;
    }
}
```

### **TypeScript**

```ts
function eraseOverlapIntervals(intervals: number[][]): number {
    intervals.sort((a, b) => a[1] - b[1]);
    let end = intervals[0][1],
        ans = 0;
    for (let i = 1; i < intervals.length; ++i) {
        let cur = intervals[i];
        if (end > cur[0]) {
            ans++;
        } else {
            end = cur[1];
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int eraseOverlapIntervals(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end(), [](const auto& a, const auto& b) { return a[1] < b[1]; });
        int ans = 0, t = intervals[0][1];
        for (int i = 1; i < intervals.size(); ++i) {
            if (t <= intervals[i][0])
                t = intervals[i][1];
            else
                ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func eraseOverlapIntervals(intervals [][]int) int {
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][1] < intervals[j][1]
	})
	t, ans := intervals[0][1], 0
	for i := 1; i < len(intervals); i++ {
		if intervals[i][0] >= t {
			t = intervals[i][1]
		} else {
			ans++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
