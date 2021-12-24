# [56. 合并区间](https://leetcode-cn.com/problems/merge-intervals)

[English Version](/solution/0000-0099/0056.Merge%20Intervals/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>以数组 <code>intervals</code> 表示若干个区间的集合，其中单个区间为 <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,3],[2,6],[8,10],[15,18]]
<strong>输出：</strong>[[1,6],[8,10],[15,18]]
<strong>解释：</strong>区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,4],[4,5]]
<strong>输出：</strong>[[1,5]]
<strong>解释：</strong>区间 [1,4] 和 [4,5] 可被视为重叠区间。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= intervals.length <= 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>0 <= start<sub>i</sub> <= end<sub>i</sub> <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

区间合并，将所有存在交集的区间进行合并。

模板：

```py
def merge(intervals):
    res = []
    intervals.sort(key=lambda x: x[0])
    st = ed = -1
    for s, e in intervals:
        if ed < s:
            if st != -1:
                res.append([st, ed])
            st, ed = e[0], e[1]
        else:
            ed = max(ed, e[1])
    if st != -1:
        res.append([st, ed])
    return res
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
}
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
