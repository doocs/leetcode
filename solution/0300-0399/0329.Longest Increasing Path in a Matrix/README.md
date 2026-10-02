---
comments: true
difficulty: 困难
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 拓扑排序
    - 记忆化
    - 数组
    - 动态规划
    - 矩阵
---

<!-- problem:start -->

# [329. 矩阵中的最长递增路径](https://leetcode.cn/problems/longest-increasing-path-in-a-matrix)

[English Version](/solution/0300-0399/0329.Longest%20Increasing%20Path%20in%20a%20Matrix/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个 <code>m x n</code> 整数矩阵 <code>matrix</code> ，找出其中 <strong>最长递增路径</strong> 的长度。</p>

<p>对于每个单元格，你可以往上，下，左，右四个方向移动。 你 <strong>不能</strong> 在 <strong>对角线</strong> 方向上移动或移动到 <strong>边界外</strong>（即不允许环绕）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0329.Longest%20Increasing%20Path%20in%20a%20Matrix/images/grid1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>输入：</strong>matrix = [[9,9,4],[6,6,8],[2,1,1]]
<strong>输出：</strong>4 
<strong>解释：</strong>最长递增路径为 <code>[1, 2, 6, 9]</code>。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0329.Longest%20Increasing%20Path%20in%20a%20Matrix/images/tmp-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>输入：</strong>matrix = [[3,4,5],[3,2,6],[2,2,1]]
<strong>输出：</strong>4 
<strong>解释：</strong>最长递增路径是 <code>[3, 4, 5, 6]</code>。注意不允许在对角线方向上移动。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[1]]
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 <= m, n <= 200</code></li>
	<li><code>0 <= matrix[i][j] <= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：反向拓扑遍历

<!-- thinking:start -->

> **思考**
>
> 严格递增路径构成有向无环图。每个格子的出度等于相邻且数值更大的格子数。
>
> 从局部最大值开始按反向拓扑顺序处理，并将路径长度传给数值更小的相邻格子。一个格子的所有更大邻居处理完后，才将它加入队列。

<!-- thinking:end -->

将每个格子到相邻且数值更大的格子连边，得到有向无环图。统计每个格子的出度，将所有局部最大值加入队列。从较大值向较小值处理队列：更新较小邻居的最长路径长度并减少其出度；当它的所有较大邻居都处理完后，再将其加入队列。遍历完成后，最长路径长度即为答案。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

相似题目：

- [2328. 网格图中递增路径的数目](https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2328.Number%20of%20Increasing%20Paths%20in%20a%20Grid/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestIncreasingPath(self, matrix: List[List[int]]) -> int:
        m, n = len(matrix), len(matrix[0])
        dirs = (-1, 0, 1, 0, -1)
        outdegree = [[0] * n for _ in range(m)]
        length = [[1] * n for _ in range(m)]
        q = []
        for i in range(m):
            for j in range(n):
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and matrix[x][y] > matrix[i][j]:
                        outdegree[i][j] += 1
                if outdegree[i][j] == 0:
                    q.append((i, j))

        head = 0
        while head < len(q):
            i, j = q[head]
            head += 1
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and matrix[x][y] < matrix[i][j]:
                    length[x][y] = max(length[x][y], length[i][j] + 1)
                    outdegree[x][y] -= 1
                    if outdegree[x][y] == 0:
                        q.append((x, y))
        return max(map(max, length))
```

#### Java

```java
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] outdegree = new int[m][n];
        int[][] length = new int[m][n];
        Deque<int[]> q = new ArrayDeque<>();
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                length[i][j] = 1;
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k];
                    int y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                        ++outdegree[i][j];
                    }
                }
                if (outdegree[i][j] == 0) {
                    q.offer(new int[] {i, j});
                }
            }
        }

        int ans = 1;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            ans = Math.max(ans, length[i][j]);
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] < matrix[i][j]) {
                    length[x][y] = Math.max(length[x][y], length[i][j] + 1);
                    if (--outdegree[x][y] == 0) {
                        q.offer(new int[] {x, y});
                    }
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestIncreasingPath(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        vector<vector<int>> outdegree(m, vector<int>(n));
        vector<vector<int>> length(m, vector<int>(n, 1));
        queue<pair<int, int>> q;
        int dirs[5] = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                        ++outdegree[i][j];
                    }
                }
                if (outdegree[i][j] == 0) q.emplace(i, j);
            }
        }

        int ans = 1;
        while (!q.empty()) {
            auto [i, j] = q.front();
            q.pop();
            ans = max(ans, length[i][j]);
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] < matrix[i][j]) {
                    length[x][y] = max(length[x][y], length[i][j] + 1);
                    if (--outdegree[x][y] == 0) q.emplace(x, y);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestIncreasingPath(matrix [][]int) (ans int) {
	m, n := len(matrix), len(matrix[0])
	outdegree := make([][]int, m)
	length := make([][]int, m)
	for i := range outdegree {
		outdegree[i] = make([]int, n)
		length[i] = make([]int, n)
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	queue := make([][2]int, 0)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			length[i][j] = 1
			for k := 0; k < 4; k++ {
				x, y := i+dirs[k], j+dirs[k+1]
				if 0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j] {
					outdegree[i][j]++
				}
			}
			if outdegree[i][j] == 0 {
				queue = append(queue, [2]int{i, j})
			}
		}
	}
	for head := 0; head < len(queue); head++ {
		i, j := queue[head][0], queue[head][1]
		ans = max(ans, length[i][j])
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if 0 <= x && x < m && 0 <= y && y < n && matrix[x][y] < matrix[i][j] {
				length[x][y] = max(length[x][y], length[i][j]+1)
				outdegree[x][y]--
				if outdegree[x][y] == 0 {
					queue = append(queue, [2]int{x, y})
				}
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function longestIncreasingPath(matrix: number[][]): number {
    const m = matrix.length;
    const n = matrix[0].length;
    const outdegree: number[][] = Array.from({ length: m }, () => Array(n).fill(0));
    const length: number[][] = Array.from({ length: m }, () => Array(n).fill(1));
    const dirs = [-1, 0, 1, 0, -1];
    const q: [number, number][] = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let k = 0; k < 4; ++k) {
                const x = i + dirs[k];
                const y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                    ++outdegree[i][j];
                }
            }
            if (outdegree[i][j] === 0) q.push([i, j]);
        }
    }
    let ans = 1;
    for (let head = 0; head < q.length; ++head) {
        const [i, j] = q[head];
        ans = Math.max(ans, length[i][j]);
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] < matrix[i][j]) {
                length[x][y] = Math.max(length[x][y], length[i][j] + 1);
                if (--outdegree[x][y] === 0) q.push([x, y]);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
