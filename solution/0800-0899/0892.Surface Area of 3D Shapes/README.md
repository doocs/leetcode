# [892. 三维形体的表面积](https://leetcode.cn/problems/surface-area-of-3d-shapes)

[English Version](/solution/0800-0899/0892.Surface%20Area%20of%203D%20Shapes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>n * n</code> 的网格&nbsp;<code>grid</code> ，上面放置着一些&nbsp;<code>1 x 1 x 1</code>&nbsp;的正方体。每个值&nbsp;<code>v = grid[i][j]</code>&nbsp;表示&nbsp;<code>v</code>&nbsp;个正方体叠放在对应单元格&nbsp;<code>(i, j)</code>&nbsp;上。</p>

<p>放置好正方体后，任何直接相邻的正方体都会互相粘在一起，形成一些不规则的三维形体。</p>

<p>请你返回最终这些形体的总表面积。</p>

<p><strong>注意：</strong>每个形体的底面也需要计入表面积中。</p>

<p>&nbsp;</p>

<ul>
</ul>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0892.Surface%20Area%20of%203D%20Shapes/images/tmp-grid2.jpg" style="height: 80px; width: 80px;" />
<pre>
<strong>输入：</strong>grid = [[1,2],[3,4]]
<strong>输出：</strong>34
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0892.Surface%20Area%20of%203D%20Shapes/images/tmp-grid4.jpg" style="height: 100px; width: 100px;" />
<pre>
<strong>输入：</strong>grid = [[1,1,1],[1,0,1],[1,1,1]]
<strong>输出：</strong>32
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0892.Surface%20Area%20of%203D%20Shapes/images/tmp-grid5.jpg" style="height: 100px; width: 100px;" />
<pre>
<strong>输入：</strong>grid = [[2,2,2],[2,1,2],[2,2,2]]
<strong>输出：</strong>46
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 50</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历，逐个累加**

时间复杂度 $O(n^2)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def surfaceArea(self, grid: List[List[int]]) -> int:
        ans = 0
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v:
                    ans += 2 + v * 4
                    if i:
                        ans -= min(v, grid[i - 1][j]) * 2
                    if j:
                        ans -= min(v, grid[i][j - 1]) * 2
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int surfaceArea(int[][] grid) {
        int n = grid.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0) {
                    ans += 2 + grid[i][j] * 4;
                    if (i > 0) {
                        ans -= Math.min(grid[i][j], grid[i - 1][j]) * 2;
                    }
                    if (j > 0) {
                        ans -= Math.min(grid[i][j], grid[i][j - 1]) * 2;
                    }
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
    int surfaceArea(vector<vector<int>>& grid) {
        int n = grid.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    ans += 2 + grid[i][j] * 4;
                    if (i) ans -= min(grid[i][j], grid[i - 1][j]) * 2;
                    if (j) ans -= min(grid[i][j], grid[i][j - 1]) * 2;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func surfaceArea(grid [][]int) int {
	ans := 0
	for i, row := range grid {
		for j, v := range row {
			if v > 0 {
				ans += 2 + v*4
				if i > 0 {
					ans -= min(v, grid[i-1][j]) * 2
				}
				if j > 0 {
					ans -= min(v, grid[i][j-1]) * 2
				}
			}
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
