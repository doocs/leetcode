---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1937.Maximum%20Number%20of%20Points%20with%20Cost/README_EN.md
rating: 2105
source: Weekly Contest 250 Q3
tags:
    - Array
    - Dynamic Programming
---

# [1937. Maximum Number of Points with Cost](https://leetcode.com/problems/maximum-number-of-points-with-cost)

[中文文档](/solution/1900-1999/1937.Maximum%20Number%20of%20Points%20with%20Cost/README.md)

## Description

<p>You are given an <code>m x n</code> integer matrix <code>points</code> (<strong>0-indexed</strong>). Starting with <code>0</code> points, you want to <strong>maximize</strong> the number of points you can get from the matrix.</p>

<p>To gain points, you must pick one cell in <strong>each row</strong>. Picking the cell at coordinates <code>(r, c)</code> will <strong>add</strong> <code>points[r][c]</code> to your score.</p>

<p>However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. For every two adjacent rows <code>r</code> and <code>r + 1</code> (where <code>0 &lt;= r &lt; m - 1</code>), picking cells at coordinates <code>(r, c<sub>1</sub>)</code> and <code>(r + 1, c<sub>2</sub>)</code> will <strong>subtract</strong> <code>abs(c<sub>1</sub> - c<sub>2</sub>)</code> from your score.</p>

<p>Return <em>the <strong>maximum</strong> number of points you can achieve</em>.</p>

<p><code>abs(x)</code> is defined as:</p>

<ul>
	<li><code>x</code> for <code>x &gt;= 0</code>.</li>
	<li><code>-x</code> for <code>x &lt; 0</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong><strong> </strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1937.Maximum%20Number%20of%20Points%20with%20Cost/images/screenshot-2021-07-12-at-13-40-26-diagram-drawio-diagrams-net.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> points = [[1,2,3],[1,5,1],[3,1,1]]
<strong>Output:</strong> 9
<strong>Explanation:</strong>
The blue cells denote the optimal cells to pick, which have coordinates (0, 2), (1, 1), and (2, 0).
You add 3 + 5 + 3 = 11 to your score.
However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
Your final score is 11 - 2 = 9.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1937.Maximum%20Number%20of%20Points%20with%20Cost/images/screenshot-2021-07-12-at-13-42-14-diagram-drawio-diagrams-net.png" style="width: 200px; height: 299px;" />
<pre>
<strong>Input:</strong> points = [[1,5],[2,3],[4,2]]
<strong>Output:</strong> 11
<strong>Explanation:</strong>
The blue cells denote the optimal cells to pick, which have coordinates (0, 1), (1, 1), and (2, 0).
You add 5 + 3 + 4 = 12 to your score.
However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
Your final score is 12 - 1 = 11.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == points.length</code></li>
	<li><code>n == points[r].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= points[r][c] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def maxPoints(self, points: List[List[int]]) -> int:
        n = len(points[0])
        f = points[0][:]
        for p in points[1:]:
            g = [0] * n
            lmx = -inf
            for j in range(n):
                lmx = max(lmx, f[j] + j)
                g[j] = max(g[j], p[j] + lmx - j)
            rmx = -inf
            for j in range(n - 1, -1, -1):
                rmx = max(rmx, f[j] - j)
                g[j] = max(g[j], p[j] + rmx + j)
            f = g
        return max(f)
```

```java
class Solution {
    public long maxPoints(int[][] points) {
        int n = points[0].length;
        long[] f = new long[n];
        final long inf = 1L << 60;
        for (int[] p : points) {
            long[] g = new long[n];
            long lmx = -inf, rmx = -inf;
            for (int j = 0; j < n; ++j) {
                lmx = Math.max(lmx, f[j] + j);
                g[j] = Math.max(g[j], p[j] + lmx - j);
            }
            for (int j = n - 1; j >= 0; --j) {
                rmx = Math.max(rmx, f[j] - j);
                g[j] = Math.max(g[j], p[j] + rmx + j);
            }
            f = g;
        }
        long ans = 0;
        for (long x : f) {
            ans = Math.max(ans, x);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long maxPoints(vector<vector<int>>& points) {
        using ll = long long;
        int n = points[0].size();
        vector<ll> f(n);
        const ll inf = 1e18;
        for (auto& p : points) {
            vector<ll> g(n);
            ll lmx = -inf, rmx = -inf;
            for (int j = 0; j < n; ++j) {
                lmx = max(lmx, f[j] + j);
                g[j] = max(g[j], p[j] + lmx - j);
            }
            for (int j = n - 1; ~j; --j) {
                rmx = max(rmx, f[j] - j);
                g[j] = max(g[j], p[j] + rmx + j);
            }
            f = move(g);
        }
        return *max_element(f.begin(), f.end());
    }
};
```

```go
func maxPoints(points [][]int) int64 {
	n := len(points[0])
	const inf int64 = 1e18
	f := make([]int64, n)
	for _, p := range points {
		g := make([]int64, n)
		lmx, rmx := -inf, -inf
		for j := range p {
			lmx = max(lmx, f[j]+int64(j))
			g[j] = max(g[j], int64(p[j])+lmx-int64(j))
		}
		for j := n - 1; j >= 0; j-- {
			rmx = max(rmx, f[j]-int64(j))
			g[j] = max(g[j], int64(p[j])+rmx+int64(j))
		}
		f = g
	}
	return slices.Max(f)
}
```

```ts
function maxPoints(points: number[][]): number {
    const n = points[0].length;
    const f: number[] = new Array(n).fill(0);
    for (const p of points) {
        const g: number[] = new Array(n).fill(0);
        let lmx = -Infinity;
        let rmx = -Infinity;
        for (let j = 0; j < n; ++j) {
            lmx = Math.max(lmx, f[j] + j);
            g[j] = Math.max(g[j], p[j] + lmx - j);
        }
        for (let j = n - 1; ~j; --j) {
            rmx = Math.max(rmx, f[j] - j);
            g[j] = Math.max(g[j], p[j] + rmx + j);
        }
        f.splice(0, n, ...g);
    }
    return Math.max(...f);
}
```

<!-- tabs:end -->

<!-- end -->
