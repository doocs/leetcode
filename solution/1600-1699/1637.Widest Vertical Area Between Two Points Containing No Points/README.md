# [1637. 两点之间不包含任何点的最宽垂直面积](https://leetcode.cn/problems/widest-vertical-area-between-two-points-containing-no-points)

[English Version](/solution/1600-1699/1637.Widest%20Vertical%20Area%20Between%20Two%20Points%20Containing%20No%20Points/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你 <code>n</code> 个二维平面上的点 <code>points</code> ，其中 <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> ，请你返回两点之间内部不包含任何点的 <strong>最宽垂直面积</strong> 的宽度。</p>

<p><strong>垂直面积</strong> 的定义是固定宽度，而 y 轴上无限延伸的一块区域（也就是高度为无穷大）。 <strong>最宽垂直面积</strong> 为宽度最大的一个垂直面积。</p>

<p>请注意，垂直区域 <strong>边上</strong> 的点 <strong>不在</strong> 区域内。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1637.Widest%20Vertical%20Area%20Between%20Two%20Points%20Containing%20No%20Points/images/points3.png" style="width: 276px; height: 371px;" />​
<pre>
<b>输入：</b>points = [[8,7],[9,9],[7,4],[9,7]]
<b>输出：</b>1
<b>解释：</b>红色区域和蓝色区域都是最优区域。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>points = [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]
<b>输出：</b>3
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == points.length</code></li>
	<li><code>2 <= n <= 10<sup>5</sup></code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 <= x<sub>i</sub>, y<sub>i</sub> <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序**

对 $points$ 按照 $x$ 升序排列，获取相邻点之间 $x$ 的差值的最大值。

时间复杂度 $O(nlogn)$，其中 $n$ 表示 $points$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxWidthOfVerticalArea(self, points: List[List[int]]) -> int:
        points.sort()
        return max(b[0] - a[0] for a, b in pairwise(points))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        int ans = 0;
        for (int i = 0; i < points.length - 1; ++i) {
            ans = Math.max(ans, points[i + 1][0] - points[i][0]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxWidthOfVerticalArea(vector<vector<int>>& points) {
        sort(points.begin(), points.end());
        int ans = 0;
        for (int i = 0; i < points.size() - 1; ++i) ans = max(ans, points[i + 1][0] - points[i][0]);
        return ans;
    }
};
```

### **Go**

```go
func maxWidthOfVerticalArea(points [][]int) int {
	sort.Slice(points, func(i, j int) bool {
		return points[i][0] < points[j][0]
	})
	ans := 0
	for i, p := range points[1:] {
		ans = max(ans, p[0]-points[i][0])
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
