---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/README_EN.md
rating: 1579
source: Weekly Contest 459 Q2
tags:
    - Geometry
    - Array
    - Hash Table
    - Math
---

<!-- problem:start -->

# [3623. Count Number of Trapezoids I](https://leetcode.com/problems/count-number-of-trapezoids-i)

[中文文档](/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/README.md)

## Description

<!-- description:start -->

<p data-end="189" data-start="146">You are given a 2D integer array <code>points</code>, where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents the coordinates of the <code>i<sup>th</sup></code> point on the Cartesian plane.</p>

<p data-end="579" data-start="405">A <strong>horizontal</strong> <strong>trapezoid</strong> is a convex quadrilateral with <strong data-end="496" data-start="475">at least one pair</strong> of horizontal sides (i.e. parallel to the x-axis). Two lines are parallel if and only if they have the same slope.</p>

<p data-end="579" data-start="405">Return the <em data-end="330" data-start="297"> number of unique </em><strong><em>horizontal</em> <em>trapezoids</em></strong> that can be formed by choosing any four distinct points from <code>points</code>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [[1,0],[2,0],[3,0],[2,2],[3,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/images/desmos-graph-6.png" style="width: 250px; height: 250px;" /> <img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/images/desmos-graph-7.png" style="width: 250px; height: 250px;" /> <img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/images/desmos-graph-8.png" style="width: 250px; height: 250px;" /></p>

<p>There are three distinct ways to pick four points that form a horizontal trapezoid:</p>

<ul>
	<li data-end="247" data-start="193">Using points <code data-end="213" data-start="206">[1,0]</code>, <code data-end="222" data-start="215">[2,0]</code>, <code data-end="231" data-start="224">[3,2]</code>, and <code data-end="244" data-start="237">[2,2]</code>.</li>
	<li data-end="305" data-start="251">Using points <code data-end="271" data-start="264">[2,0]</code>, <code data-end="280" data-start="273">[3,0]</code>, <code data-end="289" data-start="282">[3,2]</code>, and <code data-end="302" data-start="295">[2,2]</code>.</li>
	<li data-end="361" data-start="309">Using points <code data-end="329" data-start="322">[1,0]</code>, <code data-end="338" data-start="331">[3,0]</code>, <code data-end="347" data-start="340">[3,2]</code>, and <code data-end="360" data-start="353">[2,2]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [[0,0],[1,0],[0,1],[2,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/images/desmos-graph-5.png" style="width: 250px; height: 250px;" /></p>

<p>There is only one horizontal trapezoid that can be formed.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>4 &lt;= points.length &lt;= 10<sup>5</sup></code></li>
	<li><code>&ndash;10<sup>8</sup> &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
	<li>All points are pairwise distinct.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

According to the problem description, horizontal edges have the same $y$ coordinate. Therefore, we can group points by their $y$ coordinates and count the number of points for each $y$ coordinate.

We use a hash table $\textit{cnt}$ to store the number of points for each $y$ coordinate. For each $y$ coordinate $y_i$, assuming the number of corresponding points is $v$, the number of ways to select two points from these points as a horizontal edge is $\binom{v}{2} = \frac{v(v-1)}{2}$, denoted as $t$.

We use a variable $s$ to record the sum of the number of horizontal edges for all previous $y$ coordinates. Then, we can multiply the number of horizontal edges $t$ for the current $y$ coordinate by the sum $s$ of the number of horizontal edges for all previous $y$ coordinates to get the number of trapezoids with the current $y$ coordinate as one pair of horizontal edges, and add it to the answer. Finally, we add the number of horizontal edges $t$ for the current $y$ coordinate to $s$ for subsequent calculations.

Note that since the answer may be very large, we need to take the modulo $10^9 + 7$.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the number of points.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countTrapezoids(self, points: List[List[int]]) -> int:
        mod = 10**9 + 7
        cnt = Counter(p[1] for p in points)
        ans = s = 0
        for v in cnt.values():
            t = v * (v - 1) // 2
            ans = (ans + s * t) % mod
            s += t
        return ans
```

#### Java

```java
class Solution {
    public int countTrapezoids(int[][] points) {
        final int mod = (int) 1e9 + 7;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (var p : points) {
            cnt.merge(p[1], 1, Integer::sum);
        }
        long ans = 0, s = 0;
        for (int v : cnt.values()) {
            long t = 1L * v * (v - 1) / 2;
            ans = (ans + s * t) % mod;
            s += t;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countTrapezoids(vector<vector<int>>& points) {
        const int mod = 1e9 + 7;
        unordered_map<int, int> cnt;
        for (auto& p : points) {
            cnt[p[1]]++;
        }
        long long ans = 0, s = 0;
        for (auto& [_, v] : cnt) {
            long long t = 1LL * v * (v - 1) / 2;
            ans = (ans + s * t) % mod;
            s += t;
        }
        return (int) ans;
    }
};
```

#### Go

```go
func countTrapezoids(points [][]int) int {
	const mod = 1_000_000_007
	cnt := make(map[int]int)
	for _, p := range points {
		cnt[p[1]]++
	}

	var ans, s int64
	for _, v := range cnt {
		t := int64(v) * int64(v-1) / 2
		ans = (ans + s*t) % mod
		s += t
	}
	return int(ans)
}
```

#### TypeScript

```ts
function countTrapezoids(points: number[][]): number {
    const mod = 1_000_000_007;
    const cnt = new Map<number, number>();

    for (const p of points) {
        cnt.set(p[1], (cnt.get(p[1]) ?? 0) + 1);
    }

    let ans = 0;
    let s = 0;
    for (const v of cnt.values()) {
        const t = (v * (v - 1)) / 2;
        const mul = BigInt(s) * BigInt(t);
        ans = Number((BigInt(ans) + mul) % BigInt(mod));
        s += t;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
