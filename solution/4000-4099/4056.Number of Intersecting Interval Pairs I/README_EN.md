---
comments: true
difficulty: Easy
---

<!-- problem:start -->

# [4056. Number of Intersecting Interval Pairs I](https://leetcode.com/problems/number-of-intersecting-interval-pairs-i)

[中文文档](/solution/4000-4099/4056.Number%20of%20Intersecting%20Interval%20Pairs%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>intervals</code> of <code>n</code> elements, where <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> represents the <strong>closed </strong>interval from <code>start<sub>i</sub></code> to <code>end<sub>i</sub></code>.</p>

<p>Return the number of pairs of indices <code>(i, j)</code> such that <code>0 &lt;= i &lt; j &lt; n</code> and <code>intervals[i]</code> and <code>intervals[j]</code> <strong>intersect</strong>.</p>

<p>Two intervals <strong>intersect</strong> if they have at least one point in common, including when they only share an endpoint.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">intervals = [[1,2],[2,3],[3,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 2 intersecting interval pairs:</p>

<ul>
	<li>Intervals <code>[1, 2]</code> and <code>[2, 3]</code> intersect at the point 2.</li>
	<li>Intervals <code>[2, 3]</code> and <code>[3, 4]</code> intersect at the point 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">intervals = [[1,5],[2,4],[3,6]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 3 intersecting interval pairs:</p>

<ul>
	<li>The intersection of <code>[1, 5]</code> and <code>[2, 4]</code> is <code>[2, 4]</code>.</li>
	<li>The intersection of <code>[1, 5]</code> and <code>[3, 6]</code> is <code>[3, 5]</code>.</li>
	<li>The intersection of <code>[2, 4]</code> and <code>[3, 6]</code> is <code>[3, 4]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">intervals = [[1,2],[3,4],[5,6]]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no intersecting interval pairs. Hence, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == intervals.length &lt;= 100</code></li>
	<li><code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Two Pointers

<!-- thinking:start -->

> **Thinking**
>
> $n \le 100$, so enumerating every index pair and checking intersection would pass. Two closed intervals are disjoint if and only if one right endpoint is strictly less than the other left endpoint.
>
> Pairwise checks need both sides of that test. It is cleaner to start from $\frac{n(n-1)}{2}$ and subtract the disjoint pairs: for each left endpoint, count how many intervals have already ended before it starts.
>
> After sorting the left and right endpoints separately, a pointer that only moves right counts those finished intervals in one scan.

<!-- thinking:end -->

Two closed intervals $[l_1, r_1]$ and $[l_2, r_2]$ are disjoint if and only if $r_1 < l_2$ or $r_2 < l_1$.

The total number of pairs is $\frac{n(n-1)}{2}$. We count the disjoint pairs and subtract them from the total.

Sort all left endpoints and all right endpoints in ascending order. Enumerate each left endpoint $s$ from left to right, and maintain a pointer $i$ for the number of intervals with $\textit{ends}[i] < s$. Those intervals are disjoint from the current one, so we subtract that count from the answer.

Each disjoint pair is counted exactly once: the interval with the smaller right endpoint is charged when we scan the other interval's left endpoint.

The time complexity is $O(n \times \log n)$ and the space complexity is $O(n)$, where $n$ is the number of intervals.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countIntersectingIntervals(self, intervals: list[list[int]]) -> int:
        n = len(intervals)
        starts = sorted(s for s, _ in intervals)
        ends = sorted(e for _, e in intervals)
        ans = n * (n - 1) // 2
        i = 0
        for start in starts:
            while i < n and ends[i] < start:
                i += 1
            ans -= i
        return ans
```

#### Java

```java
class Solution {
    public int countIntersectingIntervals(int[][] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int ans = n * (n - 1) / 2;
        int i = 0;
        for (int start : starts) {
            while (i < n && ends[i] < start) {
                i++;
            }
            ans -= i;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countIntersectingIntervals(vector<vector<int>>& intervals) {
        int n = intervals.size();
        vector<int> starts(n), ends(n);
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        sort(starts.begin(), starts.end());
        sort(ends.begin(), ends.end());
        int ans = n * (n - 1) / 2;
        int i = 0;
        for (int start : starts) {
            while (i < n && ends[i] < start) {
                i++;
            }
            ans -= i;
        }
        return ans;
    }
};
```

#### Go

```go
func countIntersectingIntervals(intervals [][]int) int {
	n := len(intervals)
	starts := make([]int, n)
	ends := make([]int, n)
	for i, p := range intervals {
		starts[i] = p[0]
		ends[i] = p[1]
	}
	slices.Sort(starts)
	slices.Sort(ends)
	ans := n * (n - 1) / 2
	i := 0
	for _, start := range starts {
		for i < n && ends[i] < start {
			i++
		}
		ans -= i
	}
	return ans
}
```

#### TypeScript

```ts
function countIntersectingIntervals(intervals: number[][]): number {
    const n = intervals.length;
    const starts = intervals.map(([s]) => s).sort((a, b) => a - b);
    const ends = intervals.map(([, e]) => e).sort((a, b) => a - b);
    let ans = (n * (n - 1)) / 2;
    let i = 0;
    for (const start of starts) {
        while (i < n && ends[i] < start) {
            i++;
        }
        ans -= i;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
