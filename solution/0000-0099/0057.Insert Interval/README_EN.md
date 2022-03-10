# [57. Insert Interval](https://leetcode.com/problems/insert-interval)

[中文文档](/solution/0000-0099/0057.Insert%20Interval/README.md)

## Description

<p>You are given an array of non-overlapping intervals <code>intervals</code> where <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> represent the start and the end of the <code>i<sup>th</sup></code> interval and <code>intervals</code> is sorted in ascending order by <code>start<sub>i</sub></code>. You are also given an interval <code>newInterval = [start, end]</code> that represents the start and end of another interval.</p>

<p>Insert <code>newInterval</code> into <code>intervals</code> such that <code>intervals</code> is still sorted in ascending order by <code>start<sub>i</sub></code> and <code>intervals</code> still does not have any overlapping intervals (merge overlapping intervals if necessary).</p>

<p>Return <code>intervals</code><em> after the insertion</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,3],[6,9]], newInterval = [2,5]
<strong>Output:</strong> [[1,5],[6,9]]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
<strong>Output:</strong> [[1,2],[3,10],[12,16]]
<strong>Explanation:</strong> Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= intervals.length &lt;= 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>intervals</code> is sorted by <code>start<sub>i</sub></code> in <strong>ascending</strong> order.</li>
	<li><code>newInterval.length == 2</code></li>
	<li><code>0 &lt;= start &lt;= end &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def insert(self, intervals: List[List[int]], newInterval: List[int]) -> List[List[int]]:
        def merge(intervals: List[List[int]]) -> List[List[int]]:
            intervals.sort(key=lambda x: x[0])
            st = ed = -1
            res = []
            for s, e in intervals:
                if ed < s:
                    if st != -1:
                        res.append([st, ed])
                    st, ed = s, e
                else:
                    ed = max(ed, e)
            if st != -1:
                res.append([st, ed])
            return res

        intervals.append(newInterval)
        return merge(intervals)
```

### **Java**

```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new LinkedList<>();
        int i = 0;
        while ((i < intervals.length) && (intervals[i][1] < newInterval[0])) list.add(intervals[i++]);
        while ((i < intervals.length) && (intervals[i][0] <= newInterval[1])) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        list.add(newInterval);
        while (i < intervals.length) list.add(intervals[i++]);
        return list.toArray(new int[list.size()][]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> insert(vector<vector<int>> &intervals, vector<int> &newInterval) {
        intervals.push_back(newInterval);
        return merge(intervals);
    }

    vector<vector<int>> merge(vector<vector<int>> &intervals) {
        sort(intervals.begin(), intervals.end());
        vector<vector<int>> res;
        int st = -1, ed = -1;
        for (auto e : intervals)
        {
            if (ed < e[0])
            {
                if (st != -1)
                {
                    res.push_back({st, ed});
                }
                st = e[0];
                ed = e[1];
            }
            else
            {
                ed = max(ed, e[1]);
            }
        }
        if (st != -1)
        {
            res.push_back({st, ed});
        }
        return res;
    }
};
```

### **Go**

```go
func insert(intervals [][]int, newInterval []int) [][]int {
	intervals = append(intervals, newInterval)
	return merge(intervals)
}

func merge(intervals [][]int) [][]int {
	var res [][]int
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] < intervals[j][0]
	})
	st, ed := -1, -1
	for _, e := range intervals {
		if ed < e[0] {
			if st != -1 {
				res = append(res, []int{st, ed})
			}
			st, ed = e[0], e[1]
		} else {
			ed = max(ed, e[1])
		}
	}
	if st != -1 {
		res = append(res, []int{st, ed})
	}
	return res
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
