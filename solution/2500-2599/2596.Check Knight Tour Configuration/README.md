# [2596. 检查骑士巡视方案](https://leetcode.cn/problems/check-knight-tour-configuration)

[English Version](/solution/2500-2599/2596.Check%20Knight%20Tour%20Configuration/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>骑士在一张 <code>n x n</code> 的棋盘上巡视。在有效的巡视方案中，骑士会从棋盘的 <strong>左上角</strong> 出发，并且访问棋盘上的每个格子 <strong>恰好一次</strong> 。</p>

<p>给你一个 <code>n x n</code> 的整数矩阵 <code>grid</code> ，由范围 <code>[0, n * n - 1]</code> 内的不同整数组成，其中 <code>grid[row][col]</code> 表示单元格 <code>(row, col)</code> 是骑士访问的第 <code>grid[row][col]</code> 个单元格。骑士的行动是从下标 <strong>0</strong> 开始的。</p>

<p>如果 <code>grid</code> 表示了骑士的有效巡视方案，返回 <code>true</code>；否则返回 <code>false</code>。</p>

<p><strong>注意</strong>，骑士行动时可以垂直移动两个格子且水平移动一个格子，或水平移动两个格子且垂直移动一个格子。下图展示了骑士从某个格子出发可能的八种行动路线。<br>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2596.Check%20Knight%20Tour%20Configuration/images/knight.png" style="width: 300px; height: 300px;"></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2596.Check%20Knight%20Tour%20Configuration/images/yetgriddrawio-5.png" style="width: 251px; height: 251px;">
<pre><strong>输入：</strong>grid = [[0,11,16,5,20],[17,4,19,10,15],[12,1,8,21,6],[3,18,23,14,9],[24,13,2,7,22]]
<strong>输出：</strong>true
<strong>解释：</strong>grid 如上图所示，可以证明这是一个有效的巡视方案。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2596.Check%20Knight%20Tour%20Configuration/images/yetgriddrawio-6.png" style="width: 151px; height: 151px;">
<pre><strong>输入：</strong>grid = [[0,3,6],[5,8,1],[2,7,4]]
<strong>输出：</strong>false
<strong>解释：</strong>grid 如上图所示，考虑到骑士第 7 次行动后的位置，第 8 次行动是无效的。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>3 &lt;= n &lt;= 7</code></li>
	<li><code>0 &lt;= grid[row][col] &lt; n * n</code></li>
	<li><code>grid</code> 中的所有整数 <strong>互不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们先用数组 $pos$ 记录骑士访问的每个格子的坐标，然后遍历 $pos$ 数组，检查相邻两个格子的坐标差是否为 $(1, 2)$ 或 $(2, 1)$ 即可。若不满足，则返回 `false`。

否则遍历结束后，返回 `true`。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为棋盘的边长。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkValidGrid(self, grid: List[List[int]]) -> bool:
        if grid[0][0]:
            return False
        n = len(grid)
        pos = [None] * (n * n)
        for i in range(n):
            for j in range(n):
                pos[grid[i][j]] = (i, j)
        for (x1, y1), (x2, y2) in pairwise(pos):
            dx, dy = abs(x1 - x2), abs(y1 - y2)
            ok = (dx == 1 and dy == 2) or (dx == 2 and dy == 1)
            if not ok:
                return False
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        int n = grid.length;
        int[][] pos = new int[n * n][2];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                pos[grid[i][j]] = new int[] {i, j};
            }
        }
        for (int i = 1; i < n * n; ++i) {
            int[] p1 = pos[i - 1];
            int[] p2 = pos[i];
            int dx = Math.abs(p1[0] - p2[0]);
            int dy = Math.abs(p1[1] - p2[1]);
            boolean ok = (dx == 1 && dy == 2) || (dx == 2 && dy == 1);
            if (!ok) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkValidGrid(vector<vector<int>>& grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        int n = grid.size();
        vector<pair<int, int>> pos(n * n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                pos[grid[i][j]] = {i, j};
            }
        }
        for (int i = 1; i < n * n; ++i) {
            auto [x1, y1] = pos[i - 1];
            auto [x2, y2] = pos[i];
            int dx = abs(x1 - x2);
            int dy = abs(y1 - y2);
            bool ok = (dx == 1 && dy == 2) || (dx == 2 && dy == 1);
            if (!ok) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func checkValidGrid(grid [][]int) bool {
	if grid[0][0] != 0 {
		return false
	}
	n := len(grid)
	type pair struct{ x, y int }
	pos := make([]pair, n*n)
	for i, row := range grid {
		for j, x := range row {
			pos[x] = pair{i, j}
		}
	}
	for i := 1; i < n*n; i++ {
		p1, p2 := pos[i-1], pos[i]
		dx := abs(p1.x - p2.x)
		dy := abs(p1.y - p2.y)
		ok := (dx == 2 && dy == 1) || (dx == 1 && dy == 2)
		if !ok {
			return false
		}
	}
	return true
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function checkValidGrid(grid: number[][]): boolean {
    if (grid[0][0] !== 0) {
        return false;
    }
    const n = grid.length;
    const pos = Array.from(new Array(n * n), () => new Array(2).fill(0));
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            pos[grid[i][j]] = [i, j];
        }
    }
    for (let i = 1; i < n * n; ++i) {
        const p1 = pos[i - 1];
        const p2 = pos[i];
        const dx = Math.abs(p1[0] - p2[0]);
        const dy = Math.abs(p1[1] - p2[1]);
        const ok = (dx === 1 && dy === 2) || (dx === 2 && dy === 1);
        if (!ok) {
            return false;
        }
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
