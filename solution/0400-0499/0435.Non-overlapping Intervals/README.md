---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0435.Non-overlapping%20Intervals/README.md
tags:
    - 贪心
    - 数组
    - 动态规划
    - 排序
---

<!-- problem:start -->

# [435. 无重叠区间](https://leetcode.cn/problems/non-overlapping-intervals)

[English Version](/solution/0400-0499/0435.Non-overlapping%20Intervals/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个区间的集合&nbsp;<code>intervals</code>&nbsp;，其中 <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>&nbsp;。返回 <em>需要移除区间的最小数量，使剩余区间互不重叠&nbsp;</em>。</p>

<p><strong>注意</strong>&nbsp;只在一点上接触的区间是&nbsp;<strong>不重叠的</strong>。例如&nbsp;<code>[1, 2]</code>&nbsp;和&nbsp;<code>[2, 3]</code>&nbsp;是不重叠的。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> intervals = [[1,2],[2,3],[3,4],[1,3]]
<strong>输出:</strong> 1
<strong>解释:</strong> 移除 [1,3] 后，剩下的区间没有重叠。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> intervals = [ [1,2], [1,2], [1,2] ]
<strong>输出:</strong> 2
<strong>解释:</strong> 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> intervals = [ [1,2], [2,3] ]
<strong>输出:</strong> 0
<strong>解释:</strong> 你不需要移除任何区间，因为它们已经是无重叠的了。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 10<sup>5</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>-5 * 10<sup>4</sup>&nbsp;&lt;= start<sub>i</sub>&nbsp;&lt; end<sub>i</sub>&nbsp;&lt;= 5 * 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 贪心

我们首先将区间按照右边界升序排序，用一个变量 $\textit{pre}$ 记录上一个区间的右边界，用一个变量 $\textit{ans}$ 记录需要移除的区间数量，初始时 $\textit{ans} = \textit{intervals.length}$。

然后遍历区间，对于每一个区间：

- 若当前区间的左边界大于等于 $\textit{pre}$，说明该区间无需移除，直接更新 $\textit{pre}$ 为当前区间的右边界，然后将 $\textit{ans}$ 减一；
- 否则，说明该区间需要移除，不需要更新 $\textit{pre}$ 和 $\textit{ans}$。

最后返回 $\textit{ans}$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为区间的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
        intervals.sort(key=lambda x: x[1])
        ans = len(intervals)
        pre = -inf
        for l, r in intervals:
            if pre <= l:
                ans -= 1
                pre = r
        return ans
```

#### Java

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int ans = intervals.length;
        int pre = Integer.MIN_VALUE;
        for (var e : intervals) {
            int l = e[0], r = e[1];
            if (pre <= l) {
                --ans;
                pre = r;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int eraseOverlapIntervals(vector<vector<int>>& intervals) {
        ranges::sort(intervals, [](const vector<int>& a, const vector<int>& b) {
            return a[1] < b[1];
        });
        int ans = intervals.size();
        int pre = INT_MIN;
        for (const auto& e : intervals) {
            int l = e[0], r = e[1];
            if (pre <= l) {
                --ans;
                pre = r;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func eraseOverlapIntervals(intervals [][]int) int {
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][1] < intervals[j][1]
	})
	ans := len(intervals)
	pre := math.MinInt32
	for _, e := range intervals {
		l, r := e[0], e[1]
		if pre <= l {
			ans--
			pre = r
		}
	}
	return ans
}
```

#### TypeScript

```ts
function eraseOverlapIntervals(intervals: number[][]): number {
    intervals.sort((a, b) => a[1] - b[1]);
    let [ans, pre] = [intervals.length, -Infinity];
    for (const [l, r] of intervals) {
        if (pre <= l) {
            --ans;
            pre = r;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
