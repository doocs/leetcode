# [2850. Minimum Moves to Spread Stones Over Grid](https://leetcode.com/problems/minimum-moves-to-spread-stones-over-grid)

[中文文档](/solution/2800-2899/2850.Minimum%20Moves%20to%20Spread%20Stones%20Over%20Grid/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> 2D integer matrix <code>grid</code> of size <code>3 * 3</code>, representing the number of stones in each cell. The grid contains exactly <code>9</code> stones, and there can be <strong>multiple</strong> stones in a single cell.</p>

<p>In one move, you can move a single stone from its current cell to any other cell if the two cells share a side.</p>

<p>Return <em>the <strong>minimum number of moves</strong> required to place one stone in each cell</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2850.Minimum%20Moves%20to%20Spread%20Stones%20Over%20Grid/images/example1-3.svg" style="width: 401px; height: 281px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,0],[1,1,1],[1,2,1]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> One possible sequence of moves to place one stone in each cell is: 
1- Move one stone from cell (2,1) to cell (2,2).
2- Move one stone from cell (2,2) to cell (1,2).
3- Move one stone from cell (1,2) to cell (0,2).
In total, it takes 3 moves to place one stone in each cell of the grid.
It can be shown that 3 is the minimum number of moves required to place one stone in each cell.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2850.Minimum%20Moves%20to%20Spread%20Stones%20Over%20Grid/images/example2-2.svg" style="width: 401px; height: 281px;" />
<pre>
<strong>Input:</strong> grid = [[1,3,0],[1,0,0],[1,0,3]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> One possible sequence of moves to place one stone in each cell is:
1- Move one stone from cell (0,1) to cell (0,2).
2- Move one stone from cell (0,1) to cell (1,1).
3- Move one stone from cell (2,2) to cell (1,2).
4- Move one stone from cell (2,2) to cell (2,1).
In total, it takes 4 moves to place one stone in each cell of the grid.
It can be shown that 4 is the minimum number of moves required to place one stone in each cell.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>grid.length == grid[i].length == 3</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 9</code></li>
	<li>Sum of <code>grid</code> is equal to <code>9</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumMoves(self, grid: List[List[int]]) -> int:
        q = deque([tuple(tuple(row) for row in grid)])
        vis = set(q)
        ans = 0
        dirs = (-1, 0, 1, 0, -1)
        while 1:
            for _ in range(len(q)):
                cur = q.popleft()
                if all(x for row in cur for x in row):
                    return ans
                for i in range(3):
                    for j in range(3):
                        if cur[i][j] > 1:
                            for a, b in pairwise(dirs):
                                x, y = i + a, j + b
                                if 0 <= x < 3 and 0 <= y < 3 and cur[x][y] < 2:
                                    nxt = [list(row) for row in cur]
                                    nxt[i][j] -= 1
                                    nxt[x][y] += 1
                                    nxt = tuple(tuple(row) for row in nxt)
                                    if nxt not in vis:
                                        vis.add(nxt)
                                        q.append(nxt)
            ans += 1
```

### **Java**

```java
class Solution {
    public int minimumMoves(int[][] grid) {
        Deque<String> q = new ArrayDeque<>();
        q.add(f(grid));
        Set<String> vis = new HashSet<>();
        vis.add(f(grid));
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int ans = 0;; ++ans) {
            for (int k = q.size(); k > 0; --k) {
                String p = q.poll();
                if ("111111111".equals(p)) {
                    return ans;
                }
                int[][] cur = g(p);
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        if (cur[i][j] > 1) {
                            for (int d = 0; d < 4; ++d) {
                                int x = i + dirs[d];
                                int y = j + dirs[d + 1];
                                if (x >= 0 && x < 3 && y >= 0 && y < 3 && cur[x][y] < 2) {
                                    int[][] nxt = new int[3][3];
                                    for (int r = 0; r < 3; ++r) {
                                        for (int c = 0; c < 3; ++c) {
                                            nxt[r][c] = cur[r][c];
                                        }
                                    }
                                    nxt[i][j]--;
                                    nxt[x][y]++;
                                    String s = f(nxt);
                                    if (!vis.contains(s)) {
                                        vis.add(s);
                                        q.add(s);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private String f(int[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : grid) {
            for (int x : row) {
                sb.append(x);
            }
        }
        return sb.toString();
    }

    private int[][] g(String s) {
        int[][] grid = new int[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                grid[i][j] = s.charAt(i * 3 + j) - '0';
            }
        }
        return grid;
    }
}
```

### **C++**

```cpp

```

### **Go**

```go

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
