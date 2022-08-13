# [1030. 距离顺序排列矩阵单元格](https://leetcode.cn/problems/matrix-cells-in-distance-order)

[English Version](/solution/1000-1099/1030.Matrix%20Cells%20in%20Distance%20Order/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定四个整数 <code>rows</code>&nbsp;,&nbsp; &nbsp;<code>cols</code> ,&nbsp; <code>rCenter</code> 和 <code>cCenter</code> 。有一个&nbsp;<code>rows x cols</code>&nbsp;的矩阵，你在单元格上的坐标是&nbsp;<code>(rCenter, cCenter)</code> 。</p>

<p>返回矩阵中的所有单元格的坐标，并按与<em>&nbsp;</em><code>(rCenter, cCenter)</code><em>&nbsp;</em>的 <strong>距离</strong> 从最小到最大的顺序排。你可以按 <strong>任何</strong> 满足此条件的顺序返回答案。</p>

<p>单元格<code>(r1, c1)</code> 和 <code>(r2, c2)</code> 之间的距离为<code>|r1 - r2| + |c1 - c2|</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>rows = 1, cols = 2, rCenter = 0, cCenter = 0
<strong>输出：</strong>[[0,0],[0,1]]
<strong>解释</strong>：从 (r0, c0) 到其他单元格的距离为：[0,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rows = 2, cols = 2, rCenter = 0, cCenter = 1
<strong>输出：</strong>[[0,1],[0,0],[1,1],[1,0]]
<strong>解释</strong>：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
[[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>rows = 2, cols = 3, rCenter = 1, cCenter = 2
<strong>输出：</strong>[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
<strong>解释</strong>：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= rows, cols &lt;= 100</code></li>
	<li><code>0 &lt;= rCenter &lt; rows</code></li>
	<li><code>0 &lt;= cCenter &lt; cols</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

从坐标点 `(rCenter, cCenter)` 往上下左右 4 个方向进行搜索，将搜索到的坐标点添加到结果列表 ans 中，并记录访问过的节点，防止重复搜索。

搜索结束，返回结果列表 ans 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def allCellsDistOrder(
        self, rows: int, cols: int, rCenter: int, cCenter: int
    ) -> List[List[int]]:
        q = deque([(rCenter, cCenter)])
        vis = [[False] * cols for _ in range(rows)]
        vis[rCenter][cCenter] = True
        ans = []
        while q:
            for _ in range(len(q)):
                i, j = q.popleft()
                ans.append([i, j])
                for a, b in [[1, 0], [-1, 0], [0, 1], [0, -1]]:
                    x, y = i + a, j + b
                    if 0 <= x < rows and 0 <= y < cols and not vis[x][y]:
                        q.append((x, y))
                        vis[x][y] = True
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{rCenter, cCenter});
        boolean[][] vis = new boolean[rows][cols];
        vis[rCenter][cCenter] = true;
        int[][] ans = new int[rows * cols][2];
        int idx = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            for (int n = q.size(); n > 0; --n) {
                int[] p = q.poll();
                ans[idx++] = p;
                for (int k = 0; k < 4; ++k) {
                    int x = p[0] + dirs[k];
                    int y = p[1] + dirs[k + 1];
                    if (x >= 0 && x < rows && y >= 0 && y < cols && !vis[x][y]) {
                        q.offer(new int[]{x, y});
                        vis[x][y] = true;
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
    vector<vector<int>> allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        queue<vector<int>> q;
        q.push({rCenter, cCenter});
        vector<vector<bool>> vis(rows, vector<bool>(cols));
        vis[rCenter][cCenter] = true;
        vector<vector<int>> ans;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            for (int n = q.size(); n > 0; --n) {
                auto p = q.front();
                q.pop();
                ans.push_back(p);
                for (int k = 0; k < 4; ++k) {
                    int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
                    if (x >= 0 && x < rows && y >= 0 && y < cols && !vis[x][y]) {
                        q.push({x, y});
                        vis[x][y] = true;
                    }
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func allCellsDistOrder(rows int, cols int, rCenter int, cCenter int) [][]int {
	q := [][]int{{rCenter, cCenter}}
	vis := make([][]bool, rows)
	for i := range vis {
		vis[i] = make([]bool, cols)
	}
	vis[rCenter][cCenter] = true
	var ans [][]int
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		for n := len(q); n > 0; n-- {
			p := q[0]
			q = q[1:]
			ans = append(ans, p)
			for k := 0; k < 4; k++ {
				x, y := p[0]+dirs[k], p[1]+dirs[k+1]
				if x >= 0 && x < rows && y >= 0 && y < cols && !vis[x][y] {
					q = append(q, []int{x, y})
					vis[x][y] = true
				}
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
