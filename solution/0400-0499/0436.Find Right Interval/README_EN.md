# [436. Find Right Interval](https://leetcode.com/problems/find-right-interval)

[中文文档](/solution/0400-0499/0436.Find%20Right%20Interval/README.md)

## Description

<p>You are given an array of <code>intervals</code>, where <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> and each <code>start<sub>i</sub></code> is <strong>unique</strong>.</p>

<p>The <strong>right interval</strong> for an interval <code>i</code> is an interval <code>j</code> such that <code>start<sub>j</sub> &gt;= end<sub>i</sub></code> and <code>start<sub>j</sub></code> is <strong>minimized</strong>. Note that <code>i</code> may equal <code>j</code>.</p>

<p>Return <em>an array of <strong>right interval</strong> indices for each interval <code>i</code></em>. If no <strong>right interval</strong> exists for interval <code>i</code>, then put <code>-1</code> at index <code>i</code>.</p>

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
The right interval for [2,3] is [3,4] since start<sub>0</sub> = 3 is the smallest start that is &gt;= end<sub>1</sub> = 3.
The right interval for [1,2] is [2,3] since start<sub>1</sub> = 2 is the smallest start that is &gt;= end<sub>2</sub> = 2.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,4],[2,3],[3,4]]
<strong>Output:</strong> [-1,2,-1]
<strong>Explanation:</strong> There is no right interval for [1,4] and [3,4].
The right interval for [2,3] is [3,4] since start<sub>2</sub> = 3 is the smallest start that is &gt;= end<sub>1</sub> = 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>-10<sup>6</sup> &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li>The start point of each interval is <strong>unique</strong>.</li>
</ul>

## Solutions

Binary search.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findRightInterval(self, intervals: List[List[int]]) -> List[int]:
        for i, v in enumerate(intervals):
            v.append(i)
        intervals.sort()
        n = len(intervals)
        ans = [-1] * n
        for _, e, i in intervals:
            j = bisect_left(intervals, [e])
            if j < n:
                ans[i] = intervals[j][2]
        return ans
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
                if (starts[mid].first >= end)
                    right = mid;
                else
                    left = mid + 1;
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

### **TypeScript**

```ts
function findRightInterval(intervals: number[][]): number[] {
    const n = intervals.length;
    const starts = Array.from({ length: n }).map(() => new Array<number>(2));
    for (let i = 0; i < n; i++) {
        starts[i][0] = intervals[i][0];
        starts[i][1] = i;
    }
    starts.sort((a, b) => a[0] - b[0]);

    return intervals.map(([_, target]) => {
        let left = 0;
        let right = n;
        while (left < right) {
            const mid = (left + right) >>> 1;
            if (starts[mid][0] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (left >= n) {
            return -1;
        }
        return starts[left][1];
    });
}
```

### **...**

```

```

<!-- tabs:end -->
