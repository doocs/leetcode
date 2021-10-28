# [435. 无重叠区间](https://leetcode-cn.com/problems/non-overlapping-intervals)

[English Version](/solution/0400-0499/0435.Non-overlapping%20Intervals/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。</p>

<p><strong>注意:</strong></p>

<ol>
	<li>可以认为区间的终点总是大于它的起点。</li>
	<li>区间 [1,2] 和 [2,3] 的边界相互&ldquo;接触&rdquo;，但没有相互重叠。</li>
</ol>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> [ [1,2], [2,3], [3,4], [1,3] ]

<strong>输出:</strong> 1

<strong>解释:</strong> 移除 [1,3] 后，剩下的区间没有重叠。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> [ [1,2], [1,2], [1,2] ]

<strong>输出:</strong> 2

<strong>解释:</strong> 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> [ [1,2], [2,3] ]

<strong>输出:</strong> 0

<strong>解释:</strong> 你不需要移除任何区间，因为它们已经是无重叠的了。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

贪心。

先按照区间右边界排序。优先选择最小的区间的右边界作为起始边界。遍历区间：

- 若当前区间左边界大于等于起始右边界，说明该区间无需移除，直接更新起始右边界；
- 否则说明该区间需要移除，更新移除区间的数量 cnt。

最后返回 cnt 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
        if not intervals:
            return 0
        intervals.sort(key=lambda x: x[1])
        cnt, end = 0, intervals[0][1]
        for interval in intervals[1:]:
            if interval[0] >= end:
                end = interval[1]
            else:
                cnt += 1
        return cnt
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int end = intervals[0][1], cnt = 0;
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
            } else {
                ++cnt;
            }
        }
        return cnt;
    }
}
```

### **TypeScript**

```ts
function eraseOverlapIntervals(intervals: number[][]): number {
    let n = intervals.length;
    if (n == 0) return 0;
    intervals.sort((a, b) => a[1] - b[1]);
    let end = intervals[0][1], ans = 0;
    for (let i = 1; i < n; ++i) {
        let cur = intervals[i];
        if (end > cur[0]) {
            ans++;
        } else {
            end = cur[1];
        }
    }
    return ans;
};
```

### **C++**

```cpp
class Solution {
public:
    int eraseOverlapIntervals(vector<vector<int>> &intervals) {
        if (intervals.empty())
        {
            return 0;
        }
        sort(intervals.begin(), intervals.end(), [](const auto &a, const auto &b)
             { return a[1] < b[1]; });
        int ed = intervals[0][1], cnt = 0;
        for (int i = 1; i < intervals.size(); ++i)
        {
            if (ed <= intervals[i][0])
            {
                ed = intervals[i][1];
            }
            else
            {
                ++cnt;
            }
        }
        return cnt;
    }
};
```

### **Go**

```go
func eraseOverlapIntervals(intervals [][]int) int {
	if intervals == nil || len(intervals) == 0 {
		return 0
	}
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][1] < intervals[j][1]
	})
	end, cnt := intervals[0][1], 0
	for i := 1; i < len(intervals); i++ {
		if intervals[i][0] >= end {
			end = intervals[i][1]
		} else {
			cnt++
		}
	}
	return cnt
}
```

### **...**

```

```

<!-- tabs:end -->
