# [812. Largest Triangle Area](https://leetcode.com/problems/largest-triangle-area)

[中文文档](/solution/0800-0899/0812.Largest%20Triangle%20Area/README.md)

## Description

<p>Given an array of points on the <strong>X-Y</strong> plane <code>points</code> where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>, return <em>the area of the largest triangle that can be formed by any three different points</em>. Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0812.Largest%20Triangle%20Area/images/1027.png" style="height: 369px; width: 450px;" />
<pre>
<strong>Input:</strong> points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
<strong>Output:</strong> 2.00000
<strong>Explanation:</strong> The five points are shown in the above figure. The red triangle is the largest.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> points = [[1,0],[0,0],[0,1]]
<strong>Output:</strong> 0.50000
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= points.length &lt;= 50</code></li>
	<li><code>-50 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 50</code></li>
	<li>All the given points are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestTriangleArea(self, points: List[List[int]]) -> float:
        ans = 0
        for x1, y1 in points:
            for x2, y2 in points:
                for x3, y3 in points:
                    u1, v1 = x2 - x1, y2 - y1
                    u2, v2 = x3 - x1, y3 - y1
                    t = abs(u1 * v2 - u2 * v1) / 2
                    ans = max(ans, t)
        return ans
```

### **Java**

```java
class Solution {
    public double largestTriangleArea(int[][] points) {
        double ans = 0;
        for (int[] p1 : points) {
            int x1 = p1[0], y1 = p1[1];
            for (int[] p2 : points) {
                int x2 = p2[0], y2 = p2[1];
                for (int[] p3 : points) {
                    int x3 = p3[0], y3 = p3[1];
                    int u1 = x2 - x1, v1 = y2 - y1;
                    int u2 = x3 - x1, v2 = y3 - y1;
                    double t = Math.abs(u1 * v2 - u2 * v1) / 2.0;
                    ans = Math.max(ans, t);
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double largestTriangleArea(vector<vector<int>>& points) {
        double ans = 0;
        for (auto& p1 : points) {
            int x1 = p1[0], y1 = p1[1];
            for (auto& p2 : points) {
                int x2 = p2[0], y2 = p2[1];
                for (auto& p3 : points) {
                    int x3 = p3[0], y3 = p3[1];
                    int u1 = x2 - x1, v1 = y2 - y1;
                    int u2 = x3 - x1, v2 = y3 - y1;
                    double t = abs(u1 * v2 - u2 * v1) / 2.0;
                    ans = max(ans, t);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func largestTriangleArea(points [][]int) float64 {
	ans := 0.0
	for _, p1 := range points {
		x1, y1 := p1[0], p1[1]
		for _, p2 := range points {
			x2, y2 := p2[0], p2[1]
			for _, p3 := range points {
				x3, y3 := p3[0], p3[1]
				u1, v1 := x2-x1, y2-y1
				u2, v2 := x3-x1, y3-y1
				t := float64(abs(u1*v2-u2*v1)) / 2.0
				ans = math.Max(ans, t)
			}
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
