---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1266.Minimum%20Time%20Visiting%20All%20Points/README_EN.md
rating: 1302
source: Weekly Contest 164 Q1
tags:
    - Geometry
    - Array
    - Math
---

<!-- problem:start -->

# [1266. Minimum Time Visiting All Points](https://leetcode.com/problems/minimum-time-visiting-all-points)

[中文文档](/solution/1200-1299/1266.Minimum%20Time%20Visiting%20All%20Points/README.md)

## Description

<!-- description:start -->

<p>On a 2D plane, there are <code>n</code> points with integer coordinates <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>. Return <em>the <strong>minimum time</strong> in seconds to visit all the points in the order given by </em><code>points</code>.</p>

<p>You can move according to these rules:</p>

<ul>
	<li>In <code>1</code> second, you can either:

    <ul>
    	<li>move vertically by one&nbsp;unit,</li>
    	<li>move horizontally by one unit, or</li>
    	<li>move diagonally <code>sqrt(2)</code> units (in other words, move one unit vertically then one unit horizontally in <code>1</code> second).</li>
    </ul>
    </li>
    <li>You have to visit the points in the same order as they appear in the array.</li>
    <li>You are allowed to pass through points that appear later in the order, but these do not count as visits.</li>

</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1266.Minimum%20Time%20Visiting%20All%20Points/images/1626_example_1.png" style="width: 500px; height: 428px;" />
<pre>
<strong>Input:</strong> points = [[1,1],[3,4],[-1,0]]
<strong>Output:</strong> 7
<strong>Explanation: </strong>One optimal path is <strong>[1,1]</strong> -&gt; [2,2] -&gt; [3,3] -&gt; <strong>[3,4] </strong>-&gt; [2,3] -&gt; [1,2] -&gt; [0,1] -&gt; <strong>[-1,0]</strong>   
Time from [1,1] to [3,4] = 3 seconds 
Time from [3,4] to [-1,0] = 4 seconds
Total time = 7 seconds</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> points = [[3,2],[-2,2]]
<strong>Output:</strong> 5
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>points.length == n</code></li>
	<li><code>1 &lt;= n&nbsp;&lt;= 100</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>-1000&nbsp;&lt;= points[i][0], points[i][1]&nbsp;&lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

For two points $p_1=(x_1, y_1)$ and $p_2=(x_2, y_2)$, the distances moved in the horizontal and vertical directions are $d_x = |x_1 - x_2|$ and $d_y = |y_1 - y_2|$ respectively.

If $d_x \ge d_y$, we move diagonally for $d_y$ steps, then move horizontally for $d_x - d_y$ steps; if $d_x < d_y$, we move diagonally for $d_x$ steps, then move vertically for $d_y - d_x$ steps. Therefore, the shortest distance between two points is $\max(d_x, d_y)$.

We can iterate through all pairs of points, calculate the shortest distance between each pair, and sum them up.

The time complexity is $O(n)$, where $n$ is the number of points. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minTimeToVisitAllPoints(self, points: List[List[int]]) -> int:
        return sum(
            max(abs(p1[0] - p2[0]), abs(p1[1] - p2[1])) for p1, p2 in pairwise(points)
        )
```

#### Java

```java
class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0;
        for (int i = 1; i < points.length; ++i) {
            int dx = Math.abs(points[i][0] - points[i - 1][0]);
            int dy = Math.abs(points[i][1] - points[i - 1][1]);
            ans += Math.max(dx, dy);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minTimeToVisitAllPoints(vector<vector<int>>& points) {
        int ans = 0;
        for (int i = 1; i < points.size(); ++i) {
            int dx = abs(points[i][0] - points[i - 1][0]);
            int dy = abs(points[i][1] - points[i - 1][1]);
            ans += max(dx, dy);
        }
        return ans;
    }
};
```

#### Go

```go
func minTimeToVisitAllPoints(points [][]int) (ans int) {
	for i, p := range points[1:] {
		dx := abs(p[0] - points[i][0])
		dy := abs(p[1] - points[i][1])
		ans += max(dx, dy)
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minTimeToVisitAllPoints(points: number[][]): number {
    let ans = 0;
    for (let i = 1; i < points.length; i++) {
        const dx = Math.abs(points[i][0] - points[i - 1][0]);
        const dy = Math.abs(points[i][1] - points[i - 1][1]);
        ans += Math.max(dx, dy);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_time_to_visit_all_points(points: Vec<Vec<i32>>) -> i32 {
        let mut ans = 0;
        for i in 1..points.len() {
            let dx = (points[i][0] - points[i - 1][0]).abs();
            let dy = (points[i][1] - points[i - 1][1]).abs();
            ans += dx.max(dy);
        }
        ans
    }
}
```

#### C

```c
#define max(a, b) ((a) > (b) ? (a) : (b))

int minTimeToVisitAllPoints(int** points, int pointsSize, int* pointsColSize) {
    int ans = 0;
    for (int i = 1; i < pointsSize; ++i) {
        int dx = abs(points[i][0] - points[i - 1][0]);
        int dy = abs(points[i][1] - points[i - 1][1]);
        ans += max(dx, dy);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
