# [289. Game of Life](https://leetcode.com/problems/game-of-life)

[中文文档](/solution/0200-0299/0289.Game%20of%20Life/README.md)

## Description

<p>According to&nbsp;<a href="https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life" target="_blank">Wikipedia&#39;s article</a>: &quot;The <b>Game of Life</b>, also known simply as <b>Life</b>, is a cellular automaton devised by the British mathematician John Horton Conway in 1970.&quot;</p>

<p>The board is made up of an <code>m x n</code> grid of cells, where each cell has an initial state: <b>live</b> (represented by a <code>1</code>) or <b>dead</b> (represented by a <code>0</code>). Each cell interacts with its <a href="https://en.wikipedia.org/wiki/Moore_neighborhood" target="_blank">eight neighbors</a> (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):</p>

<ol>
	<li>Any live cell with fewer than two live neighbors dies as if caused by under-population.</li>
	<li>Any live cell with two or three live neighbors lives on to the next generation.</li>
	<li>Any live cell with more than three live neighbors dies, as if by over-population.</li>
	<li>Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.</li>
</ol>

<p><span>The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. Given the current state of the <code>m x n</code> grid <code>board</code>, return <em>the next state</em>.</span></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0289.Game%20of%20Life/images/grid1.jpg" style="width: 562px; height: 322px;" />
<pre>
<strong>Input:</strong> board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
<strong>Output:</strong> [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0289.Game%20of%20Life/images/grid2.jpg" style="width: 402px; height: 162px;" />
<pre>
<strong>Input:</strong> board = [[1,1],[1,0]]
<strong>Output:</strong> [[1,1],[1,1]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 25</code></li>
	<li><code>board[i][j]</code> is <code>0</code> or <code>1</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells first and then use their updated values to update other cells.</li>
	<li>In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How would you address these problems?</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def gameOfLife(self, board: List[List[int]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        m, n = len(board), len(board[0])
        cb = [[board[i][j] for j in range(n)] for i in range(m)]
        dirs = [[-1, 0], [1, 0], [0, -1], [0, 1], [-1, -1], [-1, 1], [1, -1], [1, 1]]
        for i in range(m):
            for j in range(n):
                cnt = sum(
                    cb[i + a][j + b]
                    for a, b in dirs
                    if 0 <= i + a < m and 0 <= j + b < n
                )
                if cb[i][j] == 1 and (cnt < 2 or cnt > 3):
                    board[i][j] = 0
                elif cb[i][j] == 0 and (cnt == 3):
                    board[i][j] = 1
```

```python
class Solution:
    def gameOfLife(self, board: List[List[int]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        m, n = len(board), len(board[0])
        dirs = [[-1, 0], [1, 0], [0, -1], [0, 1],
                [-1, -1], [-1, 1], [1, -1], [1, 1]]
        for i in range(m):
            for j in range(n):
                cnt = sum(1 for a, b in dirs if 0 <= i + a < m and 0 <=
                          j + b < n and board[i + a][j + b] in (1, 2))
                if board[i][j] == 1 and (cnt < 2 or cnt > 3):
                    board[i][j] = 2
                elif board[i][j] == 0 and (cnt == 3):
                    board[i][j] = 3
        for i in range(m):
            for j in range(n):
                board[i][j] %= 2
```

### **Java**

```java
class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        int[][] cb = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                cb[i][j] = board[i][j];
            }
        }
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int cnt = 0;
                for (int[] dir : dirs) {
                    int x = i + dir[0], y = j + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        cnt += cb[x][y];
                    }
                }
                if (cb[i][j] == 1 && (cnt < 2 || cnt > 3)) {
                    board[i][j] = 0;
                } else if (cb[i][j] == 0 && cnt == 3) {
                    board[i][j] = 1;
                }
            }
        }
    }
}
```

```java
class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int cnt = 0;
                for (int[] dir : dirs) {
                    int x = i + dir[0], y = j + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && (board[x][y] == 1 || board[x][y] == 2)) {
                        ++cnt;
                    }
                }
                if (board[i][j] == 1 && (cnt < 2 || cnt > 3)) {
                    board[i][j] = 2;
                } else if (board[i][j] == 0 && cnt == 3) {
                    board[i][j] = 3;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                board[i][j] %= 2;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    void gameOfLife(vector<vector<int>>& board) {
        int m = board.size(), n = board[0].size();
        vector<vector<int>> cb(m, vector<int>(n, 0));
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                cb[i][j] = board[i][j];

        vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int cnt = 0;
                for (auto& dir : dirs) {
                    int x = i + dir[0], y = j + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n) cnt += cb[x][y];
                }
                if (cb[i][j] == 1 && (cnt < 2 || cnt > 3))
                    board[i][j] = 0;
                else if (cb[i][j] == 0 && cnt == 3)
                    board[i][j] = 1;
            }
        }
    }
};
```

```cpp
class Solution {
public:
    void gameOfLife(vector<vector<int>>& board) {
        int m = board.size(), n = board[0].size();
        vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int cnt = 0;
                for (auto& dir : dirs) {
                    int x = i + dir[0], y = j + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && (board[x][y] == 1 || board[x][y] == 2)) ++cnt;
                }
                if (board[i][j] == 1 && (cnt < 2 || cnt > 3))
                    board[i][j] = 2;
                else if (board[i][j] == 0 && cnt == 3)
                    board[i][j] = 3;
            }
        }
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                board[i][j] %= 2;
    }
};
```

### **Go**

```go
func gameOfLife(board [][]int)  {
    m, n := len(board), len(board[0])
    cb := make([][]int, m)
    for i := range cb {
        cb[i] = make([]int, n)
        for j := 0; j < n; j++ {
            cb[i][j] = board[i][j]
        }
    }
    dirs := [8][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}
    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            cnt := 0
            for _, dir := range dirs {
                x, y := i + dir[0], j + dir[1]
                if x >= 0 && x < m && y >= 0 && y < n {
                    cnt += cb[x][y]
                }
            }
            if cb[i][j] == 1 && (cnt < 2 || cnt > 3) {
                board[i][j] = 0
            } else if cb[i][j] == 0 && cnt == 3 {
                board[i][j] = 1
            }
        }
    }
}
```

```go
func gameOfLife(board [][]int) {
	m, n := len(board), len(board[0])
	dirs := [8][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			cnt := 0
			for _, dir := range dirs {
				x, y := i+dir[0], j+dir[1]
				if x >= 0 && x < m && y >= 0 && y < n && (board[x][y] == 1 || board[x][y] == 2) {
					cnt++
				}
			}
			if board[i][j] == 1 && (cnt < 2 || cnt > 3) {
				board[i][j] = 2
			} else if board[i][j] == 0 && cnt == 3 {
				board[i][j] = 3
			}
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			board[i][j] %= 2
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
