# [1030. Matrix Cells in Distance Order](https://leetcode.com/problems/matrix-cells-in-distance-order)

[中文文档](/solution/1000-1099/1030.Matrix%20Cells%20in%20Distance%20Order/README.md)

## Description

<p>You are given four integers <code>row</code>, <code>cols</code>, <code>rCenter</code>, and <code>cCenter</code>. There is a <code>rows x cols</code> matrix and you are on the cell with the coordinates <code>(rCenter, cCenter)</code>.</p>

<p>Return <em>the coordinates of all cells in the matrix, sorted by their <strong>distance</strong> from </em><code>(rCenter, cCenter)</code><em> from the smallest distance to the largest distance</em>. You may return the answer in <strong>any order</strong> that satisfies this condition.</p>

<p>The <strong>distance</strong> between two cells <code>(r<sub>1</sub>, c<sub>1</sub>)</code> and <code>(r<sub>2</sub>, c<sub>2</sub>)</code> is <code>|r<sub>1</sub> - r<sub>2</sub>| + |c<sub>1</sub> - c<sub>2</sub>|</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> rows = 1, cols = 2, rCenter = 0, cCenter = 0
<strong>Output:</strong> [[0,0],[0,1]]
<strong>Explanation:</strong> The distances from (0, 0) to other cells are: [0,1]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> rows = 2, cols = 2, rCenter = 0, cCenter = 1
<strong>Output:</strong> [[0,1],[0,0],[1,1],[1,0]]
<strong>Explanation:</strong> The distances from (0, 1) to other cells are: [0,1,1,2]
The answer [[0,1],[1,1],[0,0],[1,0]] would also be accepted as correct.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> rows = 2, cols = 3, rCenter = 1, cCenter = 2
<strong>Output:</strong> [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
<strong>Explanation:</strong> The distances from (1, 2) to other cells are: [0,1,1,2,2,3]
There are other answers that would also be accepted as correct, such as [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rows, cols &lt;= 100</code></li>
	<li><code>0 &lt;= rCenter &lt; rows</code></li>
	<li><code>0 &lt;= cCenter &lt; cols</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
