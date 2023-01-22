# [812. 最大三角形面积](https://leetcode.cn/problems/largest-triangle-area)

[English Version](/solution/0800-0899/0812.Largest%20Triangle%20Area/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由 <strong>X-Y</strong> 平面上的点组成的数组 <code>points</code> ，其中 <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 。从其中取任意三个不同的点组成三角形，返回能组成的最大三角形的面积。与真实值误差在 <code>10<sup>-5</sup></code> 内的答案将会视为正确答案<strong>。</strong></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0812.Largest%20Triangle%20Area/images/1027.png" style="height: 369px; width: 450px;" />
<pre>
<strong>输入：</strong>points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
<strong>输出：</strong>2.00000
<strong>解释：</strong>输入中的 5 个点如上图所示，红色的三角形面积最大。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>points = [[1,0],[0,0],[0,1]]
<strong>输出：</strong>0.50000
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= points.length &lt;= 50</code></li>
	<li><code>-50 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 50</code></li>
	<li>给出的所有点 <strong>互不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
