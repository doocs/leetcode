---
comments: true
difficulty: Hard
rating: 2189
source: Weekly Contest 521 Q4
tags:
    - Array
    - Binary Search
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [4068. Maximize Meeting Earnings with Idle Gaps](https://leetcode.com/problems/maximize-meeting-earnings-with-idle-gaps)

[中文文档](/solution/4000-4099/4068.Maximize%20Meeting%20Earnings%20with%20Idle%20Gaps/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>meetings</code>, where <code>meetings[i] = [start<sub>i</sub>, end<sub>i</sub>, revenue<sub>i</sub>]</code> represents a meeting starting at time <code>start<sub>i</sub></code>, ending at time <code>end<sub>i</sub></code>, with revenue <code>revenue<sub>i</sub></code>.</p>

<p>All meetings use <strong>half-open intervals</strong> <code>[start, end)</code>, so meetings that only touch at endpoints do <strong>not</strong> overlap.</p>

<p>You may select any <strong>non-empty <span data-keyword="subset">subset</span></strong> of meetings such that no two selected meetings overlap. You earn the revenue of each selected meeting.</p>

<p>Arrange the selected meetings in <strong>increasing order of their start times</strong>. For each pair of adjacent meetings in this order, you also earn 1 unit of revenue per unit of idle time between them. This idle time equals the later meeting&#39;s start time minus the earlier meeting&#39;s end time.</p>

<p>No idle revenue is earned before the earliest selected meeting starts or after the latest selected meeting ends. If only one meeting is selected, no idle revenue is earned.</p>

<p>Return the <strong>maximum total earnings</strong> achievable.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">meetings = [[2,5,4],[6,8,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Select both meetings. They do not overlap and earn <code>4 + 3 = 7</code> units of meeting revenue.</li>
	<li>The first meeting ends at time 5, and the second starts at time 6. This idle gap earns <code>6 - 5 = 1</code> additional unit.</li>
	<li>The maximum total earnings are <code>7 + 1 = 8</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">meetings = [[3,5,4],[4,7,8],[8,10,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Select the meetings at indices 1 and 2. They do not overlap and earn <code>8 + 3 = 11</code> units of meeting revenue.</li>
	<li>In chronological order, these meetings run from time 4 to 7 and from time 8 to 10. The idle gap earns <code>8 - 7 = 1</code> additional unit.</li>
	<li>The maximum total earnings are <code>11 + 1 = 12</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">meetings = [[1,2,2],[4,5,2],[7,9,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">11</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Select all three meetings. They do not overlap and earn <code>2 + 2 + 3 = 7</code> units of meeting revenue.</li>
	<li>The idle gap from time 2 to 4 earns <code>4 - 2 = 2</code> additional units.</li>
	<li>The idle gap from time 5 to 7 earns <code>7 - 5 = 2</code> additional units.</li>
	<li>The maximum total earnings are <code>7 + 2 + 2 = 11</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<h3><strong>Constraints</strong></h3>

<ul>
	<li><code>1 &lt;= meetings.length &lt;= 10<sup>5</sup></code></li>
	<li><code>meetings[i] = [start<sub>i</sub>, end<sub>i</sub>, revenue<sub>i</sub>]</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= revenue<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sort, Binary Search, and DP

<!-- thinking:start -->

> **Thinking**
>
> There can be $10^5$ meetings, so enumerating subsets does not work. Earnings come from two parts: the revenue of each selected meeting, and the idle gap between meetings that are adjacent after sorting by start time. A single meeting earns no idle revenue.
>
> Fix one meeting as the last meeting of a schedule. Every meeting placed before it must end no later than its start, and the new gap is $\textit{start}$ minus that previous end. The quantity to maximize is therefore the best earnings of a schedule ending at a meeting, minus that meeting's end time.
>
> After sorting by end time, the meetings that can precede the current one form a prefix, and the prefix maximum can be located by binary search. Revenues and gaps are accumulated in $64$-bit integers.

<!-- thinking:end -->

Sort the meetings by ascending end time. Let $f[i]$ be the maximum earnings of a schedule whose last meeting is meeting $i$. Every non-empty schedule has a meeting that ends last, so the answer is the maximum of all $f[i]$.

Selecting meeting $i$ alone gives $f[i]=\textit{revenue}_i$. If a meeting $j$ with $\textit{end}_j\le\textit{start}_i$ is placed before it, then

$$
f[i]=f[j]+\textit{revenue}_i+(\textit{start}_i-\textit{end}_j),
$$

which rearranges to

$$
f[i]=\textit{revenue}_i+\textit{start}_i+\max_j(f[j]-\textit{end}_j).
$$

After the sort, those indices $j$ form a prefix. Let

$$
\textit{preMax}[k]=\max_{j<k}(f[j]-\textit{end}_j),
$$

using a sentinel while no meeting has been processed. When handling meeting $i$, binary-search the first index $p$ in $[0,i)$ whose end time is greater than $\textit{start}_i$. Then $\textit{preMax}[p]$ is the maximum above. If $\textit{start}_i$ is smaller than the earliest end time, the prefix is empty and meeting $i$ must be taken alone.

Then set $\textit{preMax}[i+1]=\max(\textit{preMax}[i], f[i]-\textit{end}_i)$. Two meetings with the same end time overlap, so the binary search does not chain them.

The time complexity is $O(n\log n)$ and the space complexity is $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxEarnings(self, meetings: list[list[int]]) -> int:
        n = len(meetings)
        meetings.sort(key=lambda x: x[1])

        pre_max = [-inf] * (n + 1)
        ans = 0

        for i, (start, end, revenue) in enumerate(meetings):
            val = revenue
            if start >= meetings[0][1]:
                j = bisect_right(meetings, start, hi=i, key=lambda x: x[1])
                val += pre_max[j] + start

            ans = max(ans, val)
            pre_max[i + 1] = max(pre_max[i], val - end)

        return ans
```

#### Java

```java
class Solution {
    public long maxEarnings(int[][] meetings) {
        int n = meetings.length;
        Arrays.sort(meetings, (a, b) -> a[1] - b[1]);

        long[] preMax = new long[n + 1];
        Arrays.fill(preMax, Long.MIN_VALUE / 2);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];
            int revenue = meetings[i][2];

            long val = revenue;
            if (start >= meetings[0][1]) {
                int j = upperBound(meetings, start, i);
                val += preMax[j] + start;
            }

            ans = Math.max(ans, val);
            preMax[i + 1] = Math.max(preMax[i], val - end);
        }

        return ans;
    }

    private int upperBound(int[][] meetings, int target, int hi) {
        int l = 0, r = hi;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (meetings[m][1] <= target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxEarnings(vector<vector<int>>& meetings) {
        int n = meetings.size();
        sort(meetings.begin(), meetings.end(), [](auto& a, auto& b) {
            return a[1] < b[1];
        });

        vector<long long> preMax(n + 1, LLONG_MIN / 2);
        long long ans = 0;

        for (int i = 0; i < n; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];
            int revenue = meetings[i][2];

            long long val = revenue;
            if (start >= meetings[0][1]) {
                int j = upper_bound(meetings.begin(), meetings.begin() + i, start,
                            [](int x, const vector<int>& y) {
                                return x < y[1];
                            })
                    - meetings.begin();
                val += preMax[j] + start;
            }

            ans = max(ans, val);
            preMax[i + 1] = max(preMax[i], val - end);
        }

        return ans;
    }
};
```

#### Go

```go
func maxEarnings(meetings [][]int) int64 {
	n := len(meetings)
	sort.Slice(meetings, func(i, j int) bool {
		return meetings[i][1] < meetings[j][1]
	})

	preMax := make([]int64, n+1)
	for i := range preMax {
		preMax[i] = -1 << 60
	}

	var ans int64

	for i, meeting := range meetings {
		start, end, revenue := meeting[0], meeting[1], meeting[2]

		val := int64(revenue)
		if start >= meetings[0][1] {
			j := sort.Search(i, func(j int) bool {
				return meetings[j][1] > start
			})
			val += preMax[j] + int64(start)
		}

		ans = max(ans, val)
		preMax[i+1] = max(preMax[i], val-int64(end))
	}

	return ans
}
```

#### TypeScript

```ts
function maxEarnings(meetings: number[][]): number {
    const n = meetings.length;
    meetings.sort((a, b) => a[1] - b[1]);

    const preMax = new Array<number>(n + 1).fill(-Infinity);
    let ans = 0;

    for (let i = 0; i < n; i++) {
        const start = meetings[i][0];
        const end = meetings[i][1];
        const revenue = meetings[i][2];

        let val = revenue;
        if (start >= meetings[0][1]) {
            let l = 0;
            let r = i;
            while (l < r) {
                const m = (l + r) >> 1;
                if (meetings[m][1] <= start) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            val += preMax[l] + start;
        }

        ans = Math.max(ans, val);
        preMax[i + 1] = Math.max(preMax[i], val - end);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
