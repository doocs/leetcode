# [57. 插入区间](https://leetcode.cn/problems/insert-interval)

[English Version](/solution/0000-0099/0057.Insert%20Interval/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个<strong> 无重叠的</strong><em> ，</em>按照区间起始端点排序的区间列表。</p>

<p>在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,3],[6,9]], newInterval = [2,5]
<strong>输出：</strong>[[1,5],[6,9]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
<strong>输出：</strong>[[1,2],[3,10],[12,16]]
<strong>解释：</strong>这是因为新的区间 <code>[4,8]</code> 与 <code>[3,5],[6,7],[8,10]</code> 重叠。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>intervals = [], newInterval = [5,7]
<strong>输出：</strong>[[5,7]]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,5]], newInterval = [2,3]
<strong>输出：</strong>[[1,5]]
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,5]], newInterval = [2,7]
<strong>输出：</strong>[[1,7]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= intervals.length <= 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>0 <= intervals[i][0] <= intervals[i][1] <= 10<sup>5</sup></code></li>
	<li><code>intervals</code> 根据 <code>intervals[i][0]</code> 按 <strong>升序</strong> 排列</li>
	<li><code>newInterval.length == 2</code></li>
	<li><code>0 <= newInterval[0] <= newInterval[1] <= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：区间合并**

区间合并，将所有存在交集的区间进行合并。方法是：先对区间**按照左端点升序排列**，然后遍历区间进行合并。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def insert(
        self, intervals: List[List[int]], newInterval: List[int]
    ) -> List[List[int]]:
        def merge(intervals):
            intervals.sort()
            ans = []
            st, ed = intervals[0]
            for s, e in intervals[1:]:
                if ed < s:
                    ans.append([st, ed])
                    st, ed = s, e
                else:
                    ed = max(ed, e)
            ans.append([st, ed])
            return ans

        intervals.append(newInterval)
        return merge(intervals)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    vector<vector<int>> insert(vector<vector<int>>& intervals, vector<int>& newInterval) {
        intervals.push_back(newInterval);
        return merge(intervals);
    }

    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end());
        int st = intervals[0][0], ed = intervals[0][1];
        vector<vector<int>> ans;
        for (int i = 1; i < intervals.size(); ++i) {
            int s = intervals[i][0], e = intervals[i][1];
            if (ed < s) {
                ans.push_back({st, ed});
                st = s, ed = e;
            } else
                ed = max(ed, e);
        }
        ans.push_back({st, ed});
        return ans;
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
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] < intervals[j][0]
	})
	st, ed := intervals[0][0], intervals[0][1]
	var ans [][]int
	for _, e := range intervals[1:] {
		if ed < e[0] {
			ans = append(ans, []int{st, ed})
			st, ed = e[0], e[1]
		} else if ed < e[1] {
			ed = e[1]
		}
	}
	ans = append(ans, []int{st, ed})
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
