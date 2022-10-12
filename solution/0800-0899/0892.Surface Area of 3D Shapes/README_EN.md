# [892. Surface Area of 3D Shapes](https://leetcode.com/problems/surface-area-of-3d-shapes)

[中文文档](/solution/0800-0899/0892.Surface%20Area%20of%203D%20Shapes/README.md)

## Description

<p>You are given an <code>n x n</code> <code>grid</code> where you have placed some <code>1 x 1 x 1</code> cubes. Each value <code>v = grid[i][j]</code> represents a tower of <code>v</code> cubes placed on top of cell <code>(i, j)</code>.</p>

<p>After placing these cubes, you have decided to glue any directly adjacent cubes to each other, forming several irregular 3D shapes.</p>

<p>Return <em>the total surface area of the resulting shapes</em>.</p>

<p><strong>Note:</strong> The bottom face of each shape counts toward its surface area.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0892.Surface%20Area%20of%203D%20Shapes/images/tmp-grid2.jpg" style="width: 162px; height: 162px;" />
<pre>
<strong>Input:</strong> grid = [[1,2],[3,4]]
<strong>Output:</strong> 34
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0892.Surface%20Area%20of%203D%20Shapes/images/tmp-grid4.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,1],[1,0,1],[1,1,1]]
<strong>Output:</strong> 32
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0892.Surface%20Area%20of%203D%20Shapes/images/tmp-grid5.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> grid = [[2,2,2],[2,1,2],[2,2,2]]
<strong>Output:</strong> 46
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 50</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
