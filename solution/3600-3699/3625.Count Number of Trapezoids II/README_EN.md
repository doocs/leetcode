---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3625.Count%20Number%20of%20Trapezoids%20II/README_EN.md
rating: 2643
source: Weekly Contest 459 Q4
tags:
    - Geometry
    - Array
    - Hash Table
    - Math
---

<!-- problem:start -->

# [3625. Count Number of Trapezoids II](https://leetcode.com/problems/count-number-of-trapezoids-ii)

[中文文档](/solution/3600-3699/3625.Count%20Number%20of%20Trapezoids%20II/README.md)

## Description

<!-- description:start -->

<p data-end="189" data-start="146">You are given a 2D integer array <code>points</code> where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents the coordinates of the <code>i<sup>th</sup></code> point on the Cartesian plane.</p>

<p data-end="189" data-start="146">Return <em data-end="330" data-start="297">the number of unique </em><em>trapezoids</em> that can be formed by choosing any four distinct points from <code>points</code>.</p>

<p data-end="579" data-start="405">A<b> </b><strong>trapezoid</strong> is a convex quadrilateral with <strong data-end="496" data-start="475">at least one pair</strong> of parallel sides. Two lines are parallel if and only if they have the same slope.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [[-3,2],[3,0],[2,3],[3,2],[2,-3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3625.Count%20Number%20of%20Trapezoids%20II/images/desmos-graph-4.png" style="width: 250px; height: 250px;" /> <img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3625.Count%20Number%20of%20Trapezoids%20II/images/desmos-graph-3.png" style="width: 250px; height: 250px;" /></p>

<p>There are two distinct ways to pick four points that form a trapezoid:</p>

<ul>
	<li>The points <code>[-3,2], [2,3], [3,2], [2,-3]</code> form one trapezoid.</li>
	<li>The points <code>[2,3], [3,2], [3,0], [2,-3]</code> form another trapezoid.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [[0,0],[1,0],[0,1],[2,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3625.Count%20Number%20of%20Trapezoids%20II/images/desmos-graph-5.png" style="width: 250px; height: 250px;" /></p>

<p>There is only one trapezoid which can be formed.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>4 &lt;= points.length &lt;= 500</code></li>
	<li><code>&ndash;1000 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 1000</code></li>
	<li>All points are pairwise distinct.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Enumeration

We can combine all points pairwise, calculate the slope and intercept of the line corresponding to each pair of points, record them using a hash table, and calculate the sum of the number of pairs formed by lines with the same slope but different intercepts. Note that for parallelograms, we will count them twice in the above calculation, so we need to subtract them.

The diagonals of a parallelogram share the same midpoint. Therefore, we also combine all points pairwise, calculate the midpoint coordinates and slope of each pair of points, record them using a hash table, and calculate the sum of the number of pairs formed by point pairs with the same slope and the same midpoint coordinates.

Specifically, we use two hash tables $\textit{cnt1}$ and $\textit{cnt2}$ to record the following information:

-   $\textit{cnt1}$ records the number of occurrences of slope $k$ and intercept $b$, with the key being the slope $k$ and the value being another hash table that records the number of occurrences of intercept $b$;
-   $\textit{cnt2}$ records the number of occurrences of the midpoint coordinates and slope $k$ of point pairs, with the key being the midpoint coordinates $p$ of the point pair and the value being another hash table that records the number of occurrences of slope $k$.

For a point pair $(x_1, y_1)$ and $(x_2, y_2)$, we denote $dx = x_2 - x_1$ and $dy = y_2 - y_1$. If $dx = 0$, it means the two points are on the same vertical line, and we denote the slope $k = +\infty$ and the intercept $b = x_1$; otherwise, the slope $k = \frac{dy}{dx}$ and the intercept $b = \frac{y_1 \cdot dx - x_1 \cdot dy}{dx}$. The midpoint coordinates $p$ of the point pair can be expressed as $p = (x_1 + x_2 + 2000) \cdot 4000 + (y_1 + y_2 + 2000)$, where the offset is added to avoid negative numbers.

Next, we iterate through all point pairs, calculate the corresponding slope $k$, intercept $b$, and midpoint coordinates $p$, and update the hash tables $\textit{cnt1}$ and $\textit{cnt2}$.

Then, we iterate through the hash table $\textit{cnt1}$. For each slope $k$, we calculate the sum of all pairwise combinations of the occurrence counts of intercept $b$ and add it to the answer. Finally, we iterate through the hash table $\textit{cnt2}$. For each midpoint coordinate $p$, we calculate the sum of all pairwise combinations of the occurrence counts of slope $k$ and subtract it from the answer.

The time complexity is $O(n^2)$ and the space complexity is $O(n^2)$, where $n$ is the number of points.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countTrapezoids(self, points: List[List[int]]) -> int:
        n = len(points)

        # cnt1: k -> (b -> count)
        cnt1: dict[float, dict[float, int]] = defaultdict(lambda: defaultdict(int))
        # cnt2: p -> (k -> count)
        cnt2: dict[int, dict[float, int]] = defaultdict(lambda: defaultdict(int))

        for i in range(n):
            x1, y1 = points[i]
            for j in range(i):
                x2, y2 = points[j]
                dx, dy = x2 - x1, y2 - y1

                if dx == 0:
                    k = 1e9
                    b = x1
                else:
                    k = dy / dx
                    b = (y1 * dx - x1 * dy) / dx

                cnt1[k][b] += 1

                p = (x1 + x2 + 2000) * 4000 + (y1 + y2 + 2000)
                cnt2[p][k] += 1

        ans = 0

        for e in cnt1.values():
            s = 0
            for t in e.values():
                ans += s * t
                s += t

        for e in cnt2.values():
            s = 0
            for t in e.values():
                ans -= s * t
                s += t

        return ans
```

#### Java

```java
class Solution {
    public int countTrapezoids(int[][] points) {
        int n = points.length;
        Map<Double, Map<Double, Integer>> cnt1 = new HashMap<>(n * n);
        Map<Integer, Map<Double, Integer>> cnt2 = new HashMap<>(n * n);

        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = 0; j < i; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int dx = x2 - x1, dy = y2 - y1;
                double k = dx == 0 ? Double.MAX_VALUE : 1.0 * dy / dx;
                double b = dx == 0 ? x1 : 1.0 * (y1 * dx - x1 * dy) / dx;
                if (k == -0.0) {
                    k = 0.0;
                }
                if (b == -0.0) {
                    b = 0.0;
                }
                cnt1.computeIfAbsent(k, _ -> new HashMap<>()).merge(b, 1, Integer::sum);
                int p = (x1 + x2 + 2000) * 4000 + (y1 + y2 + 2000);
                cnt2.computeIfAbsent(p, _ -> new HashMap<>()).merge(k, 1, Integer::sum);
            }
        }

        int ans = 0;
        for (var e : cnt1.values()) {
            int s = 0;
            for (int t : e.values()) {
                ans += s * t;
                s += t;
            }
        }
        for (var e : cnt2.values()) {
            int s = 0;
            for (int t : e.values()) {
                ans -= s * t;
                s += t;
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
    int countTrapezoids(vector<vector<int>>& points) {
        int n = points.size();
        unordered_map<double, unordered_map<double, int>> cnt1;
        unordered_map<int, unordered_map<double, int>> cnt2;

        cnt1.reserve(n * n);
        cnt2.reserve(n * n);

        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = 0; j < i; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int dx = x2 - x1, dy = y2 - y1;
                double k = (dx == 0 ? 1e9 : 1.0 * dy / dx);
                double b = (dx == 0 ? x1 : 1.0 * (1LL * y1 * dx - 1LL * x1 * dy) / dx);

                cnt1[k][b] += 1;
                int p = (x1 + x2 + 2000) * 4000 + (y1 + y2 + 2000);
                cnt2[p][k] += 1;
            }
        }

        int ans = 0;
        for (auto& [_, e] : cnt1) {
            int s = 0;
            for (auto& [_, t] : e) {
                ans += s * t;
                s += t;
            }
        }
        for (auto& [_, e] : cnt2) {
            int s = 0;
            for (auto& [_, t] : e) {
                ans -= s * t;
                s += t;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countTrapezoids(points [][]int) int {
	n := len(points)
	cnt1 := make(map[float64]map[float64]int, n*n)
	cnt2 := make(map[int]map[float64]int, n*n)

	for i := 0; i < n; i++ {
		x1, y1 := points[i][0], points[i][1]
		for j := 0; j < i; j++ {
			x2, y2 := points[j][0], points[j][1]
			dx, dy := x2-x1, y2-y1

			var k, b float64
			if dx == 0 {
				k = 1e9
				b = float64(x1)
			} else {
				k = float64(dy) / float64(dx)
				b = float64(int64(y1)*int64(dx)-int64(x1)*int64(dy)) / float64(dx)
			}

			if cnt1[k] == nil {
				cnt1[k] = make(map[float64]int)
			}
			cnt1[k][b]++

			p := (x1+x2+2000)*4000 + (y1 + y2 + 2000)
			if cnt2[p] == nil {
				cnt2[p] = make(map[float64]int)
			}
			cnt2[p][k]++
		}
	}

	ans := 0
	for _, e := range cnt1 {
		s := 0
		for _, t := range e {
			ans += s * t
			s += t
		}
	}
	for _, e := range cnt2 {
		s := 0
		for _, t := range e {
			ans -= s * t
			s += t
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countTrapezoids(points: number[][]): number {
    const n = points.length;

    const cnt1: Map<number, Map<number, number>> = new Map();
    const cnt2: Map<number, Map<number, number>> = new Map();

    for (let i = 0; i < n; i++) {
        const [x1, y1] = points[i];
        for (let j = 0; j < i; j++) {
            const [x2, y2] = points[j];
            const [dx, dy] = [x2 - x1, y2 - y1];

            const k = dx === 0 ? 1e9 : dy / dx;
            const b = dx === 0 ? x1 : (y1 * dx - x1 * dy) / dx;

            if (!cnt1.has(k)) {
                cnt1.set(k, new Map());
            }
            const mapB = cnt1.get(k)!;
            mapB.set(b, (mapB.get(b) || 0) + 1);

            const p = (x1 + x2 + 2000) * 4000 + (y1 + y2 + 2000);

            if (!cnt2.has(p)) {
                cnt2.set(p, new Map());
            }
            const mapK = cnt2.get(p)!;
            mapK.set(k, (mapK.get(k) || 0) + 1);
        }
    }

    let ans = 0;
    for (const e of cnt1.values()) {
        let s = 0;
        for (const t of e.values()) {
            ans += s * t;
            s += t;
        }
    }
    for (const e of cnt2.values()) {
        let s = 0;
        for (const t of e.values()) {
            ans -= s * t;
            s += t;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
