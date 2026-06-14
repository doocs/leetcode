---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3951.Minimum%20Energy%20to%20Maintain%20Brightness/README_EN.md
rating: 1529
source: Biweekly Contest 184 Q2
tags:
    - Array
    - Sorting
---

<!-- problem:start -->

# [3951. Minimum Energy to Maintain Brightness](https://leetcode.com/problems/minimum-energy-to-maintain-brightness)

[中文文档](/solution/3900-3999/3951.Minimum%20Energy%20to%20Maintain%20Brightness/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>, representing <code>n</code> light bulbs arranged in a line and indexed from 0 to <code>n - 1</code>.</p>

<p>You are also given an integer <code>brightness</code> and a 2D integer array <code>intervals</code>, where <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> represents an <strong>inclusive</strong> time interval during which the lighting requirement <strong>must</strong> be satisfied.</p>

<p>At each time unit, every bulb can independently be either on or off. A bulb that is on <strong>illuminates</strong> its own position and its <strong>adjacent</strong> positions, if they exist.</p>

<p>The <strong>total illumination</strong> at a time unit is the number of <strong>illuminated</strong> positions. Each position is counted <strong>at most once</strong>.</p>

<p>For every integer time unit covered by <strong>at least</strong> one interval in <code>intervals</code>, the <strong>total illumination</strong> must be <strong>at least</strong> <code>brightness</code>. At time units not covered by any interval, all bulbs may remain off. Each bulb that is on consumes 1 unit of energy for that time unit.</p>

<p>Return an integer denoting the <strong>minimum</strong> total energy required.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, brightness = 5, intervals = [[6,12]]</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Turn on the light bulbs at positions 1 and 4.</li>
	<li>Current state of line: <code>0 1 0 0 1</code>.</li>
	<li>All 5 positions are illuminated, so the required brightness is reached.</li>
	<li>The active interval has length <code>12 - 6 + 1 = 7</code>, so the total energy is <code>2 * 7 = 14</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, brightness = 1, intervals = [[0,0],[2,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Turn on one light bulb during each active interval.</li>
	<li>Each interval has length 1, so the total active time is <code>1 + 1 = 2</code>.</li>
	<li>The total energy is <code>1 * 2 = 2</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, brightness = 2, intervals = [[1,3],[2,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Turn on one light bulb. It can illuminate at least 2 positions.</li>
	<li>The active intervals overlap, so the total active time is the length of <code>[1,4]</code>, which is 4.</li>
	<li>The total energy is <code>1 * 4 = 4</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= brightness &lt;= n</code></li>
	<li><code>1 &lt;= intervals.length &lt;= 10<sup>5</sup></code></li>
	<li><code>intervals[i] == [start<sub>i</sub>, end<sub>i</sub>]</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Interval Merge

A single bulb can illuminate at most 3 positions. To ensure the total brightness is at least $\textit{brightness}$, the number of bulbs required to be turned on is $\lceil \frac{\textit{brightness}}{3} \rceil$. In programming, this is commonly written in integer division form as `(brightness + 2) / 3`.

This problem can be solved through the following steps:

1. **Merge Overlapping Intervals**: Merge all intervals that intersect with each other to obtain a set of mutually disjoint continuous intervals.
2. **Calculate Length Contribution**: For each merged interval $[start, end]$, the number of integer points (i.e., positions) it covers is $m = end - start + 1$. Since every position within the interval must satisfy the minimum brightness, the total energy required for this interval is:
   $$\text{Energy} = \lceil \frac{\textit{brightness}}{3} \rceil \times m$$
3. **Accumulate and Sum**: Accumulate the energy of all disjoint intervals to get the final answer $\textit{ans}$.

The time complexity is $O(n \log n)$, and the space complexity is $O(n)$, where $n$ is the number of intervals.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minEnergy(self, n: int, brightness: int, intervals: list[list[int]]) -> int:
        intervals.sort()
        merged = [intervals[0]]
        for x in intervals[1:]:
            if merged[-1][1] < x[0]:
                merged.append(x)
            else:
                merged[-1][1] = max(merged[-1][1], x[1])
        ans = 0
        for start, end in merged:
            m = end - start + 1
            ans += (brightness + 2) // 3 * m
        return ans
```

#### Java

```java
class Solution {
    public long minEnergy(int n, int brightness, int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] x = intervals[i];
            int[] last = merged.get(merged.size() - 1);
            if (last[1] < x[0]) {
                merged.add(x);
            } else {
                last[1] = Math.max(last[1], x[1]);
            }
        }
        long ans = 0;
        for (int[] interval : merged) {
            int start = interval[0];
            int end = interval[1];
            int m = end - start + 1;
            ans += (brightness + 2L) / 3 * m;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minEnergy(int n, int brightness, vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end());
        vector<vector<int>> merged = {intervals[0]};
        for (int i = 1; i < intervals.size(); ++i) {
            auto& x = intervals[i];
            if (merged.back()[1] < x[0]) {
                merged.push_back(x);
            } else {
                merged.back()[1] = max(merged.back()[1], x[1]);
            }
        }
        long long ans = 0;
        for (const auto& interval : merged) {
            int start = interval[0];
            int end = interval[1];
            int m = end - start + 1;
            ans += (brightness + 2LL) / 3 * m;
        }
        return ans;
    }
};
```

#### Go

```go
func minEnergy(n int, brightness int, intervals [][]int) int64 {
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] < intervals[j][0]
	})
	merged := [][]int{intervals[0]}
	for _, x := range intervals[1:] {
		if merged[len(merged)-1][1] < x[0] {
			merged = append(merged, x)
		} else {
			if x[1] > merged[len(merged)-1][1] {
				merged[len(merged)-1][1] = x[1]
			}
		}
	}
	ans := 0
	for _, interval := range merged {
		start := interval[0]
		end := interval[1]
		m := end - start + 1
		ans += (brightness + 2) / 3 * m
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function minEnergy(n: number, brightness: number, intervals: number[][]): number {
    intervals.sort((a, b) => a[0] - b[0]);
    const merged: number[][] = [intervals[0]];
    for (let i = 1; i < intervals.length; i++) {
        const x = intervals[i];
        if (merged[merged.length - 1][1] < x[0]) {
            merged.push(x);
        } else {
            merged[merged.length - 1][1] = Math.max(merged[merged.length - 1][1], x[1]);
        }
    }
    let ans = 0;
    for (const [start, end] of merged) {
        const m = end - start + 1;
        ans += Math.ceil(brightness / 3) * m;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
