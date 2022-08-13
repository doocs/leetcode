# [289. 生命游戏](https://leetcode.cn/problems/game-of-life)

[English Version](/solution/0200-0299/0289.Game%20of%20Life/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>根据&nbsp;<a href="https://baike.baidu.com/item/%E7%94%9F%E5%91%BD%E6%B8%B8%E6%88%8F/2926434?fr=aladdin" target="_blank">百度百科</a>&nbsp;，&nbsp;<strong>生命游戏</strong>&nbsp;，简称为 <strong>生命</strong> ，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。</p>

<p>给定一个包含 <code>m × n</code>&nbsp;个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态： <code>1</code> 即为 <strong>活细胞</strong> （live），或 <code>0</code> 即为 <strong>死细胞</strong> （dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：</p>

<ol>
	<li>如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；</li>
	<li>如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；</li>
	<li>如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；</li>
	<li>如果死细胞周围正好有三个活细胞，则该位置死细胞复活；</li>
</ol>

<p>下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。给你 <code>m x n</code> 网格面板 <code>board</code> 的当前状态，返回下一个状态。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0289.Game%20of%20Life/images/grid1.jpg" />
<pre>
<strong>输入：</strong>board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
<strong>输出：</strong>[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0289.Game%20of%20Life/images/grid2.jpg" />
<pre>
<strong>输入：</strong>board = [[1,1],[1,0]]
<strong>输出：</strong>[[1,1],[1,1]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 25</code></li>
	<li><code>board[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。</li>
	<li>本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

由于细胞的存活状态变化是同时进行的，因此不能直接遍历格子，依次修改每个细胞的状态，否则前面细胞的状态变化将会影响到下一个要遍历到的细胞的状态。

因此，可以复制 board 样本，每次判断复制样本 cb 中的每个格子，然后对应修改 board 每个细胞的状态。空间复杂度复杂度 `O(mn)`。

也可以多定义两个状态，`status = 2` 表示从活细胞转死细胞，`status = 3` 表示从死细胞转活细胞。最后将 `status = 2` 的细胞状态置为 0，而将 `status = 3` 的细胞状态置为 1。空间复杂度 `O(1)`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
                    # 活细胞转死细胞
                    board[i][j] = 2
                elif board[i][j] == 0 and (cnt == 3):
                    # 死细胞转活细胞
                    board[i][j] = 3
        for i in range(m):
            for j in range(n):
                board[i][j] %= 2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
