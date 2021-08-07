# [436. Find Right Interval](https://leetcode.com/problems/find-right-interval)

[中文文档](/solution/0400-0499/0436.Find%20Right%20Interval/README.md)

## Description

<p>You are given an array of&nbsp;<code>intervals</code>, where <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>&nbsp;and each <code>start<sub>i</sub></code>&nbsp;is <strong>unique</strong>.</p>

<p>The <strong>r</strong><strong>ight</strong><strong>&nbsp;interval</strong>&nbsp;for an interval <code>i</code> is an interval&nbsp;<code>j</code>&nbsp;such that <code>start<sub>j</sub></code><code>&nbsp;&gt;= end<sub>i</sub></code>&nbsp;and&nbsp;<code>start<sub>j</sub></code>&nbsp;is&nbsp;<strong>minimized</strong>.</p>

<p>Return&nbsp;<em>an array of&nbsp;<strong>right interval</strong>&nbsp;indices for each interval&nbsp;<code>i</code></em>. If no&nbsp;<strong>right interval</strong>&nbsp;exists for interval&nbsp;<code>i</code>, then put&nbsp;<code>-1</code>&nbsp;at index <code>i</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,2]]
<strong>Output:</strong> [-1]
<strong>Explanation:</strong> There is only one interval in the collection, so it outputs -1.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[3,4],[2,3],[1,2]]
<strong>Output:</strong> [-1,0,1]
<strong>Explanation:</strong> There is no right interval for [3,4].
The right interval for [2,3] is [3,4] since start<sub>0</sub>&nbsp;= 3 is the smallest start that is &gt;= end<sub>1</sub>&nbsp;= 3.
The right interval for [1,2] is [2,3] since start<sub>1</sub>&nbsp;= 2 is the smallest start that is &gt;= end<sub>2</sub>&nbsp;= 2.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,4],[2,3],[3,4]]
<strong>Output:</strong> [-1,2,-1]
<strong>Explanation:</strong> There is no right interval for [1,4] and [3,4].
The right interval for [2,3] is [3,4] since start<sub>2</sub> = 3 is the smallest start that is &gt;= end<sub>1</sub>&nbsp;= 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;intervals.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>-10<sup>6</sup> &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li>The start point&nbsp;of each interval is <strong>unique</strong>.</li>
</ul>

## Solutions

Binary search.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findRightInterval(self, intervals: List[List[int]]) -> List[int]:
        n = len(intervals)
        starts = [(intervals[i][0], i) for i in range(n)]
        starts.sort(key=lambda x : x[0])
        res = []
        for _, end in intervals:
            left, right = 0, n - 1
            while left < right:
                mid = (left + right) >> 1
                if starts[mid][0] >= end:
                    right = mid
                else:
                    left = mid + 1
            res.append(-1 if starts[left][0] < end else starts[left][1])
        return res
```

### **Java**

```java
class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        List<int[]> starts = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            starts.add(new int[]{intervals[i][0], i});
        }
        starts.sort(Comparator.comparingInt(a -> a[0]));
        int[] res = new int[n];
        int i = 0;
        for (int[] interval : intervals) {
            int left = 0, right = n - 1;
            int end = interval[1];
            while (left < right) {
                int mid = (left + right) >> 1;
                if (starts.get(mid)[0] >= end) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            res[i++] = starts.get(left)[0] < end ? -1 : starts.get(left)[1];
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findRightInterval(vector<vector<int>>& intervals) {
        int n = intervals.size();
        vector<pair<int, int>> starts;
        for (int i = 0; i < n; ++i) {
            starts.emplace_back(make_pair(intervals[i][0], i));
        }
        sort(starts.begin(), starts.end());
        vector<int> res;
        for (auto interval : intervals) {
            int left = 0, right = n - 1;
            int end = interval[1];
            while (left < right) {
                int mid = left + right >> 1;
                if (starts[mid].first >= end) right = mid;
                else left = mid + 1;
            }
            res.push_back(starts[left].first < end ? -1 : starts[left].second);
        }
        return res;
    }
};
```

### **Go**

```go
func findRightInterval(intervals [][]int) []int {
	n := len(intervals)
	starts := make([][]int, n)
	for i := 0; i < n; i++ {
		starts[i] = make([]int, 2)
		starts[i][0] = intervals[i][0]
		starts[i][1] = i
	}
	sort.Slice(starts, func(i, j int) bool {
		return starts[i][0] < starts[j][0]
	})
	var res []int
	for _, interval := range intervals {
		left, right, end := 0, n-1, interval[1]
		for left < right {
			mid := (left + right) >> 1
			if starts[mid][0] >= end {
				right = mid
			} else {
				left = mid + 1
			}
		}
		val := -1
		if starts[left][0] >= end {
			val = starts[left][1]
		}
		res = append(res, val)
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
