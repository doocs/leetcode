---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0435.Non-overlapping%20Intervals/README_EN.md
tags:
    - Greedy
    - Array
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals)

[中文文档](/solution/0400-0499/0435.Non-overlapping%20Intervals/README.md)

## Description

<!-- description:start -->

<p>Given an array of intervals <code>intervals</code> where <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>, return <em>the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping</em>.</p>

<p><strong>Note</strong> that intervals which only touch at a point are <strong>non-overlapping</strong>. For example, <code>[1, 2]</code> and <code>[2, 3]</code> are non-overlapping.</p>

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Greedy

We first sort the intervals in ascending order by their right boundary. We use a variable $\textit{pre}$ to record the right boundary of the previous interval and a variable $\textit{ans}$ to record the number of intervals that need to be removed. Initially, $\textit{ans} = \textit{intervals.length}$.

Then we iterate through the intervals. For each interval:

- If the left boundary of the current interval is greater than or equal to $\textit{pre}$, it means that this interval does not need to be removed. We directly update $\textit{pre}$ to the right boundary of the current interval and decrement $\textit{ans}$ by one;
- Otherwise, it means that this interval needs to be removed, and we do not need to update $\textit{pre}$ and $\textit{ans}$.

Finally, we return $\textit{ans}$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$, where $n$ is the number of intervals.

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
