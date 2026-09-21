---
comments: true
difficulty: Hard
rating: 2201
source: Weekly Contest 189 Q4
tags:
    - Geometry
    - Array
    - Math
---

<!-- problem:start -->

# [1453. Maximum Number of Darts Inside of a Circular Dartboard](https://leetcode.com/problems/maximum-number-of-darts-inside-of-a-circular-dartboard)

[中文文档](/solution/1400-1499/1453.Maximum%20Number%20of%20Darts%20Inside%20of%20a%20Circular%20Dartboard/README.md)

## Description

<!-- description:start -->

<p>Alice is throwing <code>n</code> darts on a very large wall. You are given an array <code>darts</code> where <code>darts[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> is the position of the <code>i<sup>th</sup></code> dart that Alice threw on the wall.</p>

<p>Bob knows the positions of the <code>n</code> darts on the wall. He wants to place a dartboard of radius <code>r</code> on the wall so that the maximum number of darts that Alice throws lie&nbsp;on the dartboard.</p>

<p>Given the integer <code>r</code>, return <em>the maximum number of darts that can lie on the dartboard</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1453.Maximum%20Number%20of%20Darts%20Inside%20of%20a%20Circular%20Dartboard/images/sample_1_1806.png" style="width: 248px; height: 211px;" />
<pre>
<strong>Input:</strong> darts = [[-2,0],[2,0],[0,2],[0,-2]], r = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> Circle dartboard with center in (0,0) and radius = 2 contain all points.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1453.Maximum%20Number%20of%20Darts%20Inside%20of%20a%20Circular%20Dartboard/images/sample_2_1806.png" style="width: 306px; height: 244px;" />
<pre>
<strong>Input:</strong> darts = [[-3,0],[3,0],[2,6],[5,4],[0,9],[7,8]], r = 5
<strong>Output:</strong> 5
<strong>Explanation:</strong> Circle dartboard with center in (0,4) and radius = 5 contain all points except the point (7,8).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= darts.length &lt;= 100</code></li>
	<li><code>darts[i].length == 2</code></li>
	<li><code>-10<sup>4</sup> &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>All the <code>darts</code>&nbsp;are unique</li>
	<li><code>1 &lt;= r &lt;= 5000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> $n\le 100$. A maximum covering disk of radius $r$ can be assumed to pass through two points. For each pair at distance $\le 2r$, compute the two candidate centers and count covered darts.
>
> A single dart already gives $1$. Compare distances against $r$ with a small epsilon so boundary points are kept.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numPoints(self, darts: list[list[int]], r: int) -> int:
        def countDarts(x, y):
            count = 0
            for x1, y1 in darts:
                if dist((x, y), (x1, y1)) <= r + 1e-7:
                    count += 1
            return count

        def possibleCenters(x1, y1, x2, y2):
            dx, dy = x2 - x1, y2 - y1
            d = sqrt(dx * dx + dy * dy)
            if d > 2 * r:
                return []
            mid_x, mid_y = (x1 + x2) / 2, (y1 + y2) / 2
            dist_to_center = sqrt(r * r - (d / 2) * (d / 2))
            offset_x = dist_to_center * dy / d
            offset_y = dist_to_center * -dx / d
            return [
                (mid_x + offset_x, mid_y + offset_y),
                (mid_x - offset_x, mid_y - offset_y),
            ]

        n = len(darts)
        max_darts = 1

        for i in range(n):
            for j in range(i + 1, n):
                centers = possibleCenters(
                    darts[i][0], darts[i][1], darts[j][0], darts[j][1]
                )
                for center in centers:
                    max_darts = max(max_darts, countDarts(center[0], center[1]))

        return max_darts
```

#### Java

```java
class Solution {
    public int numPoints(int[][] darts, int r) {
        int n = darts.length;
        int maxDarts = 1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                List<double[]> centers
                    = possibleCenters(darts[i][0], darts[i][1], darts[j][0], darts[j][1], r);
                for (double[] center : centers) {
                    maxDarts = Math.max(maxDarts, countDarts(center[0], center[1], darts, r));
                }
            }
        }
        return maxDarts;
    }

    private List<double[]> possibleCenters(int x1, int y1, int x2, int y2, int r) {
        List<double[]> centers = new ArrayList<>();
        double dx = x2 - x1;
        double dy = y2 - y1;
        double d = Math.sqrt(dx * dx + dy * dy);
        if (d > 2 * r) {
            return centers;
        }
        double midX = (x1 + x2) / 2.0;
        double midY = (y1 + y2) / 2.0;
        double distToCenter = Math.sqrt(r * r - (d / 2.0) * (d / 2.0));
        double offsetX = distToCenter * dy / d;
        double offsetY = distToCenter * -dx / d;

        centers.add(new double[] {midX + offsetX, midY + offsetY});
        centers.add(new double[] {midX - offsetX, midY - offsetY});
        return centers;
    }

    private int countDarts(double x, double y, int[][] darts, int r) {
        int count = 0;
        for (int[] dart : darts) {
            if (Math.sqrt(Math.pow(dart[0] - x, 2) + Math.pow(dart[1] - y, 2)) <= r + 1e-7) {
                count++;
            }
        }
        return count;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numPoints(vector<vector<int>>& darts, int r) {
        int n = darts.size();
        int maxDarts = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                auto centers = possibleCenters(darts[i][0], darts[i][1], darts[j][0], darts[j][1], r);
                for (auto& c : centers) {
                    maxDarts = max(maxDarts, countDarts(c[0], c[1], darts, r));
                }
            }
        }
        return maxDarts;
    }

private:
    vector<array<double, 2>> possibleCenters(int x1, int y1, int x2, int y2, int r) {
        vector<array<double, 2>> centers;
        double dx = x2 - x1, dy = y2 - y1;
        double d = sqrt(dx * dx + dy * dy);
        if (d > 2.0 * r) {
            return centers;
        }
        double midX = (x1 + x2) / 2.0, midY = (y1 + y2) / 2.0;
        double distToCenter = sqrt(1.0 * r * r - (d / 2.0) * (d / 2.0));
        double offsetX = distToCenter * dy / d;
        double offsetY = distToCenter * -dx / d;
        centers.push_back({midX + offsetX, midY + offsetY});
        centers.push_back({midX - offsetX, midY - offsetY});
        return centers;
    }

    int countDarts(double x, double y, vector<vector<int>>& darts, int r) {
        int count = 0;
        for (auto& dart : darts) {
            if (hypot(dart[0] - x, dart[1] - y) <= r + 1e-7) {
                ++count;
            }
        }
        return count;
    }
};
```

#### Go

```go
func numPoints(darts [][]int, r int) int {
	n := len(darts)
	maxDarts := 1
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			for _, c := range possibleCenters(darts[i][0], darts[i][1], darts[j][0], darts[j][1], r) {
				maxDarts = max(maxDarts, countDarts(c[0], c[1], darts, r))
			}
		}
	}
	return maxDarts
}

func possibleCenters(x1, y1, x2, y2, r int) [][2]float64 {
	dx := float64(x2 - x1)
	dy := float64(y2 - y1)
	d := math.Sqrt(dx*dx + dy*dy)
	if d > 2*float64(r) {
		return nil
	}
	midX := float64(x1+x2) / 2
	midY := float64(y1+y2) / 2
	distToCenter := math.Sqrt(float64(r*r) - (d/2)*(d/2))
	offsetX := distToCenter * dy / d
	offsetY := distToCenter * -dx / d
	return [][2]float64{{midX + offsetX, midY + offsetY}, {midX - offsetX, midY - offsetY}}
}

func countDarts(x, y float64, darts [][]int, r int) (count int) {
	for _, dart := range darts {
		if math.Hypot(float64(dart[0])-x, float64(dart[1])-y) <= float64(r)+1e-7 {
			count++
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
