---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3588.Find%20Maximum%20Area%20of%20a%20Triangle/README_EN.md
rating: 1818
source: Biweekly Contest 159 Q2
tags:
    - Greedy
    - Geometry
    - Array
    - Hash Table
    - Math
    - Enumeration
---

<!-- problem:start -->

# [3588. Find Maximum Area of a Triangle](https://leetcode.com/problems/find-maximum-area-of-a-triangle)

[中文文档](/solution/3500-3599/3588.Find%20Maximum%20Area%20of%20a%20Triangle/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D array <code>coords</code> of size <code>n x 2</code>, representing the coordinates of <code>n</code> points in an infinite Cartesian plane.</p>

<p>Find <strong>twice</strong> the <strong>maximum</strong> area of a triangle with its corners at <em>any</em> three elements from <code>coords</code>, such that at least one side of this triangle is <strong>parallel</strong> to the x-axis or y-axis. Formally, if the maximum area of such a triangle is <code>A</code>, return <code>2 * A</code>.</p>

<p>If no such triangle exists, return -1.</p>

<p><strong>Note</strong> that a triangle <em>cannot</em> have zero area.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">coords = [[1,1],[1,2],[3,2],[3,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3588.Find%20Maximum%20Area%20of%20a%20Triangle/images/image-20250420010047-1.png" style="width: 300px; height: 289px;" /></p>

<p>The triangle shown in the image has a base 1 and height 2. Hence its area is <code>1/2 * base * height = 1</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">coords = [[1,1],[2,2],[3,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>The only possible triangle has corners <code>(1, 1)</code>, <code>(2, 2)</code>, and <code>(3, 3)</code>. None of its sides are parallel to the x-axis or the y-axis.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == coords.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= coords[i][0], coords[i][1] &lt;= 10<sup>6</sup></code></li>
	<li>All <code>coords[i]</code> are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Hash Map

The problem asks for twice the area of the triangle, so we can directly calculate the product of the base and height of the triangle.

Since the triangle must have at least one side parallel to the $x$-axis or $y$-axis, we can enumerate sides parallel to the $x$-axis and calculate the double area for all possible triangles, then swap the coordinates in $\textit{coords}$ and repeat the process to calculate the double area for triangles with sides parallel to the $y$-axis.

Therefore, we design a function $\textit{calc}$ to calculate the double area for all possible triangles with sides parallel to the $y$-axis.

We use two hash maps $\textit{f}$ and $\textit{g}$ to record the minimum and maximum $y$-coordinates for each $x$-coordinate. Then we iterate through $\textit{coords}$, updating $\textit{f}$ and $\textit{g}$, and also record the minimum and maximum $x$-coordinates. Finally, we iterate through $\textit{f}$, calculate the double area for each $x$-coordinate, and update the answer.

In the main function, we first call the $\textit{calc}$ function to calculate the double area for triangles with sides parallel to the $y$-axis, then swap the coordinates in $\textit{coords}$ and repeat the process to calculate the double area for triangles with sides parallel to the $x$-axis. Finally, we return the answer; if the answer is 0, we return -1.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of $\textit{coords}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxArea(self, coords: List[List[int]]) -> int:
        def calc() -> int:
            mn, mx = inf, 0
            f = {}
            g = {}
            for x, y in coords:
                mn = min(mn, x)
                mx = max(mx, x)
                if x in f:
                    f[x] = min(f[x], y)
                    g[x] = max(g[x], y)
                else:
                    f[x] = g[x] = y
            ans = 0
            for x, y in f.items():
                d = g[x] - y
                ans = max(ans, d * max(mx - x, x - mn))
            return ans

        ans = calc()
        for c in coords:
            c[0], c[1] = c[1], c[0]
        ans = max(ans, calc())
        return ans if ans else -1
```

#### Java

```java
class Solution {
    public long maxArea(int[][] coords) {
        long ans = calc(coords);
        for (int[] c : coords) {
            int tmp = c[0];
            c[0] = c[1];
            c[1] = tmp;
        }
        ans = Math.max(ans, calc(coords));
        return ans > 0 ? ans : -1;
    }

    private long calc(int[][] coords) {
        int mn = Integer.MAX_VALUE, mx = 0;
        Map<Integer, Integer> f = new HashMap<>();
        Map<Integer, Integer> g = new HashMap<>();

        for (int[] c : coords) {
            int x = c[0], y = c[1];
            mn = Math.min(mn, x);
            mx = Math.max(mx, x);
            if (f.containsKey(x)) {
                f.put(x, Math.min(f.get(x), y));
                g.put(x, Math.max(g.get(x), y));
            } else {
                f.put(x, y);
                g.put(x, y);
            }
        }

        long ans = 0;
        for (var e : f.entrySet()) {
            int x = e.getKey();
            int y = e.getValue();
            int d = g.get(x) - y;
            ans = Math.max(ans, (long) d * Math.max(mx - x, x - mn));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxArea(vector<vector<int>>& coords) {
        auto calc = [&]() -> long long {
            int mn = INT_MAX, mx = 0;
            unordered_map<int, int> f, g;
            for (auto& c : coords) {
                int x = c[0], y = c[1];
                mn = min(mn, x);
                mx = max(mx, x);
                if (f.count(x)) {
                    f[x] = min(f[x], y);
                    g[x] = max(g[x], y);
                } else {
                    f[x] = y;
                    g[x] = y;
                }
            }
            long long ans = 0;
            for (auto& [x, y] : f) {
                int d = g[x] - y;
                ans = max(ans, 1LL * d * max(mx - x, x - mn));
            }
            return ans;
        };

        long long ans = calc();
        for (auto& c : coords) {
            swap(c[0], c[1]);
        }
        ans = max(ans, calc());
        return ans > 0 ? ans : -1;
    }
};
```

#### Go

```go
func maxArea(coords [][]int) int64 {
	calc := func() int64 {
		mn, mx := int(1e9), 0
		f := make(map[int]int)
		g := make(map[int]int)
		for _, c := range coords {
			x, y := c[0], c[1]
			mn = min(mn, x)
			mx = max(mx, x)
			if _, ok := f[x]; ok {
				f[x] = min(f[x], y)
				g[x] = max(g[x], y)
			} else {
				f[x] = y
				g[x] = y
			}
		}
		var ans int64
		for x, y := range f {
			d := g[x] - y
			ans = max(ans, int64(d)*int64(max(mx-x, x-mn)))
		}
		return ans
	}

	ans := calc()
	for _, c := range coords {
		c[0], c[1] = c[1], c[0]
	}
	ans = max(ans, calc())
	if ans > 0 {
		return ans
	}
	return -1
}
```

#### TypeScript

```ts
function maxArea(coords: number[][]): number {
    function calc(): number {
        let [mn, mx] = [Infinity, 0];
        const f = new Map<number, number>();
        const g = new Map<number, number>();

        for (const [x, y] of coords) {
            mn = Math.min(mn, x);
            mx = Math.max(mx, x);
            if (f.has(x)) {
                f.set(x, Math.min(f.get(x)!, y));
                g.set(x, Math.max(g.get(x)!, y));
            } else {
                f.set(x, y);
                g.set(x, y);
            }
        }

        let ans = 0;
        for (const [x, y] of f) {
            const d = g.get(x)! - y;
            ans = Math.max(ans, d * Math.max(mx - x, x - mn));
        }
        return ans;
    }

    let ans = calc();
    for (const c of coords) {
        [c[0], c[1]] = [c[1], c[0]];
    }
    ans = Math.max(ans, calc());
    return ans > 0 ? ans : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
