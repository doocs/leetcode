# [56. Merge Intervals](https://leetcode.com/problems/merge-intervals)

[中文文档](/solution/0000-0099/0056.Merge%20Intervals/README.md)

## Description

<p>Given an array&nbsp;of <code>intervals</code>&nbsp;where <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>, merge all overlapping intervals, and return <em>an array of the non-overlapping intervals that cover all the intervals in the input</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,3],[2,6],[8,10],[15,18]]
<strong>Output:</strong> [[1,6],[8,10],[15,18]]
<strong>Explanation:</strong> Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,4],[4,5]]
<strong>Output:</strong> [[1,5]]
<strong>Explanation:</strong> Intervals [1,4] and [4,5] are considered overlapping.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
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
```

### **Java**

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int st = -1, ed = -1;
        List<int[]> res = new ArrayList<>();
        for (int[] e : intervals) {
            if (ed < e[0]) {
                if (st != -1) {
                    res.add(new int[]{st, ed});
                }
                st = e[0];
                ed = e[1];
            } else {
                ed = Math.max(ed, e[1]);
            }
        }
        if (st != -1) {
            res.add(new int[]{st, ed});
        }
        return res.toArray(new int[res.size()][]);
    }
}
```

### **TypeScript**

```ts
function merge(intervals: number[][]): number[][] {
    intervals.sort((a, b) => a[0] - b[0]);
    let ans: number[][] = [];
    let index: number = -1;
    for (let interval of intervals) {
        if (index == -1 || ans[index][1] < interval[0]) {
            // 保留
            ans.push(interval);
            index++;
        } else {
            // 求交集
            ans[index][1] = Math.max(ans[index][1], interval[1]);
        }
    }
    return ans;
};
```

### **C++**

```cpp
class Solution {
public:
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

### **C#**

```cpp
public class Solution {
    public int[][] Merge(int[][] intervals) {
        var res = new List<int[]>();
        int st = -1, ed = -1;
        foreach (var e in intervals.OrderBy(a => a[0]))
        {
            if (ed < e[0])
            {
                if (st != -1)
                {
                    res.Add(new int[] { st, ed });
                }
                st = e[0];
                ed = e[1];
            }
            else
            {
                ed = Math.Max(ed, e[1]);
            }
        }
        if (st != -1)
        {
            res.Add(new int[] { st, ed });
        }
        return res.ToArray();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
