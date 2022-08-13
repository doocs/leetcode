# [807. 保持城市天际线](https://leetcode.cn/problems/max-increase-to-keep-city-skyline)

[English Version](/solution/0800-0899/0807.Max%20Increase%20to%20Keep%20City%20Skyline/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一座由 <code>n x n</code> 个街区组成的城市，每个街区都包含一座立方体建筑。给你一个下标从 <strong>0</strong> 开始的 <code>n x n</code> 整数矩阵 <code>grid</code> ，其中 <code>grid[r][c]</code> 表示坐落于 <code>r</code> 行 <code>c</code> 列的建筑物的 <strong>高度</strong> 。</p>

<p>城市的 <strong>天际线</strong> 是从远处观察城市时，所有建筑物形成的外部轮廓。从东、南、西、北四个主要方向观测到的 <strong>天际线</strong> 可能不同。</p>

<p>我们被允许为 <strong>任意数量的建筑物 </strong>的高度增加<strong> 任意增量（不同建筑物的增量可能不同）</strong> 。 高度为 <code>0</code> 的建筑物的高度也可以增加。然而，增加的建筑物高度 <strong>不能影响</strong> 从任何主要方向观察城市得到的 <strong>天际线</strong> 。</p>

<p>在 <strong>不改变</strong> 从任何主要方向观测到的城市 <strong>天际线</strong> 的前提下，返回建筑物可以增加的 <strong>最大高度增量总和</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0807.Max%20Increase%20to%20Keep%20City%20Skyline/images/807-ex1.png" style="width: 700px; height: 603px;" />
<pre>
<strong>输入：</strong>grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]
<strong>输出：</strong>35
<strong>解释：</strong>建筑物的高度如上图中心所示。
用红色绘制从不同方向观看得到的天际线。
在不影响天际线的情况下，增加建筑物的高度：
gridNew = [ [8, 4, 8, 7],
            [7, 4, 7, 7],
            [9, 4, 8, 7],
            [3, 3, 3, 3] ]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[0,0,0],[0,0,0],[0,0,0]]
<strong>输出：</strong>0
<strong>解释：</strong>增加任何建筑物的高度都会导致天际线的变化。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[r].length</code></li>
	<li><code>2 &lt;= n &lt;= 50</code></li>
	<li><code>0 &lt;= grid[r][c] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先求每一行、每一列的最大值 `rmx`, `cmx`，然后对于每个元素 `grid[i][j]`，能增加的高度是 `min(rmx[i], cmx[j]) - grid[i][j]`。累加所有能增加的高度即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxIncreaseKeepingSkyline(self, grid: List[List[int]]) -> int:
        rmx = [max(row) for row in grid]
        cmx = [max(col) for col in zip(*grid)]
        return sum(
            (min(rmx[i], cmx[j]) - grid[i][j])
            for i in range(len(grid))
            for j in range(len(grid[0]))
        )
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rmx = new int[m];
        int[] cmx = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rmx[i] = Math.max(rmx[i], grid[i][j]);
                cmx[j] = Math.max(cmx[j], grid[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += Math.min(rmx[i], cmx[j]) - grid[i][j];
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function maxIncreaseKeepingSkyline(grid: number[][]): number {
    let rows = grid.map(arr => Math.max(...arr)),
        cols = [];
    let m = grid.length,
        n = grid[0].length;
    for (let j = 0; j < n; ++j) {
        cols[j] = grid[0][j];
        for (let i = 1; i < m; ++i) {
            cols[j] = Math.max(cols[j], grid[i][j]);
        }
    }

    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            ans += Math.min(rows[i], cols[j]) - grid[i][j];
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int maxIncreaseKeepingSkyline(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<int> rmx(m, 0);
        vector<int> cmx(n, 0);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rmx[i] = max(rmx[i], grid[i][j]);
                cmx[j] = max(cmx[j], grid[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans += min(rmx[i], cmx[j]) - grid[i][j];
        return ans;
    }
};
```

### **Go**

```go
func maxIncreaseKeepingSkyline(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	rmx := make([]int, m)
	cmx := make([]int, n)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			rmx[i] = max(rmx[i], grid[i][j])
			cmx[j] = max(cmx[j], grid[i][j])
		}
	}
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			ans += min(rmx[i], cmx[j]) - grid[i][j]
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
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
