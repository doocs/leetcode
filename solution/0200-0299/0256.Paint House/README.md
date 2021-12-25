# [256. 粉刷房子](https://leetcode-cn.com/problems/paint-house)

[English Version](/solution/0200-0299/0256.Paint%20House/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假如有一排房子，共 <em>n</em> 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。</p>

<p>当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个&nbsp;<code><em>n</em> x <em>3</em></code><em>&nbsp;</em>的矩阵来表示的。</p>

<p>例如，<code>costs[0][0]</code> 表示第 0 号房子粉刷成红色的成本花费；<code>costs[1][2]</code>&nbsp;表示第 1 号房子粉刷成绿色的花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。</p>

<p><strong>注意：</strong></p>

<p>所有花费均为正整数。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入: </strong>[[17,2,17],[16,16,5],[14,3,19]]
<strong>输出: </strong>10
<strong>解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。</strong>
&nbsp;    <strong>最少花费:</strong> 2 + 5 + 3 = 10。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCost(self, costs: List[List[int]]) -> int:
        r, g, b = 0, 0, 0
        for cost in costs:
            _r, _g, _b = r, g, b
            r = min(_g, _b) + cost[0]
            g = min(_r, _b) + cost[1]
            b = min(_r, _g) + cost[2]
        return min(r, g, b)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minCost(int[][] costs) {
        int r = 0, g = 0, b = 0;
        for (int[] cost : costs) {
            int _r = r, _g = g, _b = b;
            r = Math.min(_g, _b) + cost[0];
            g = Math.min(_r, _b) + cost[1];
            b = Math.min(_r, _g) + cost[2];
        }
        return Math.min(r, Math.min(g, b));
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minCost(vector<vector<int>>& costs) {
        int r = 0, g = 0, b = 0;
        for (auto& cost : costs) {
            int _r = r, _g = g, _b = b;
            r = min(_g, _b) + cost[0];
            g = min(_r, _b) + cost[1];
            b = min(_r, _g) + cost[2];
        }
        return min(r, min(g, b));
    }
};
```

### **Go**

```go
func minCost(costs [][]int) int {
	r, g, b := 0, 0, 0
	for _, cost := range costs {
		_r, _g, _b := r, g, b
		r = min(_g, _b) + cost[0]
		g = min(_r, _b) + cost[1]
		b = min(_r, _g) + cost[2]
	}
	return min(r, min(g, b))
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
```

### **...**

```

```

<!-- tabs:end -->
