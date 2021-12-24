# [57. Insert Interval](https://leetcode.com/problems/insert-interval)

[中文文档](/solution/0000-0099/0057.Insert%20Interval/README.md)

## Description

<p>Given a set of <em>non-overlapping</em> intervals, insert a new interval into the intervals (merge if necessary).</p>

<p>You may assume that the intervals were initially sorted according to their start times.</p>

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
<strong>Explanation:</strong> Because the new interval <code>[4,8]</code> overlaps with <code>[3,5],[6,7],[8,10]</code>.</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> intervals = [], newInterval = [5,7]
<strong>Output:</strong> [[5,7]]
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,5]], newInterval = [2,3]
<strong>Output:</strong> [[1,5]]
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,5]], newInterval = [2,7]
<strong>Output:</strong> [[1,7]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= intervals.length &lt;= 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>0 &lt;=&nbsp;intervals[i][0] &lt;=&nbsp;intervals[i][1] &lt;= 10<sup>5</sup></code></li>
	<li><code>intervals</code>&nbsp;is sorted by <code>intervals[i][0]</code> in <strong>ascending</strong>&nbsp;order.</li>
	<li><code>newInterval.length == 2</code></li>
	<li><code>0 &lt;=&nbsp;newInterval[0] &lt;=&nbsp;newInterval[1] &lt;= 10<sup>5</sup></code></li>
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
