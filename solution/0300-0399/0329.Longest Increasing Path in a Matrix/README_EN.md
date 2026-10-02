---
comments: true
difficulty: Hard
tags:
    - Depth-First Search
    - Breadth-First Search
    - Graph
    - Topological Sort
    - Memoization
    - Array
    - Dynamic Programming
    - Directed Acyclic Graph
    - Matrix
---

<!-- problem:start -->

# [329. Longest Increasing Path in a Matrix](https://leetcode.com/problems/longest-increasing-path-in-a-matrix)

[中文文档](/solution/0300-0399/0329.Longest%20Increasing%20Path%20in%20a%20Matrix/README.md)

## Description

<!-- description:start -->

<p>Given an <code>m x n</code> integers <code>matrix</code>, return <em>the length of the longest increasing path in </em><code>matrix</code>.</p>

<p>From each cell, you can either move in four directions: left, right, up, or down. You <strong>may not</strong> move <strong>diagonally</strong> or move <strong>outside the boundary</strong> (i.e., wrap-around is not allowed).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0329.Longest%20Increasing%20Path%20in%20a%20Matrix/images/grid1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> matrix = [[9,9,4],[6,6,8],[2,1,1]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest increasing path is <code>[1, 2, 6, 9]</code>.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0329.Longest%20Increasing%20Path%20in%20a%20Matrix/images/tmp-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>Input:</strong> matrix = [[3,4,5],[3,2,6],[2,2,1]]
<strong>Output:</strong> 4
<strong>Explanation: </strong>The longest increasing path is <code>[3, 4, 5, 6]</code>. Moving diagonally is not allowed.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[1]]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>0 &lt;= matrix[i][j] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Reverse Topological BFS

<!-- thinking:start -->

> **Thinking**
>
> A strictly increasing path forms a directed acyclic graph. Each cell's outdegree is its number of larger neighbors.
>
> Start from local maxima and process cells in reverse topological order. Propagate path lengths to smaller neighbors; a cell is ready after all of its larger neighbors are processed.

<!-- thinking:end -->

Orient an edge from each cell to every adjacent cell with a larger value. The resulting graph is acyclic. Count each cell's outgoing edges and enqueue all local maxima, whose outdegree is zero. Process this queue from larger values toward smaller ones. For each lower neighbor, update its longest-path length and decrease its outdegree; enqueue it once all of its larger neighbors have been processed. The maximum length is the answer.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Where $m$ and $n$ are the number of rows and columns of the matrix, respectively.

Similar problems:

- [2328. Number of Increasing Paths in a Grid](https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2328.Number%20of%20Increasing%20Paths%20in%20a%20Grid/README_EN.md)

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
