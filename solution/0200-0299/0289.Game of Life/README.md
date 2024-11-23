---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0289.Game%20of%20Life/README.md
tags:
    - 数组
    - 矩阵
    - 模拟
---

<!-- problem:start -->

# [289. 生命游戏](https://leetcode.cn/problems/game-of-life)

[English Version](/solution/0200-0299/0289.Game%20of%20Life/README_EN.md)

## 题目描述

<!-- description:start -->

<p>根据&nbsp;<a href="https://baike.baidu.com/item/%E7%94%9F%E5%91%BD%E6%B8%B8%E6%88%8F/2926434?fr=aladdin" target="_blank">百度百科</a>&nbsp;，&nbsp;<strong>生命游戏</strong>&nbsp;，简称为 <strong>生命</strong> ，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。</p>

<p>给定一个包含 <code>m × n</code>&nbsp;个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态： <code>1</code> 即为 <strong>活细胞</strong> （live），或 <code>0</code> 即为 <strong>死细胞</strong> （dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：</p>

<ol>
	<li>如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；</li>
	<li>如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；</li>
	<li>如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；</li>
	<li>如果死细胞周围正好有三个活细胞，则该位置死细胞复活；</li>
</ol>

<p>下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是 <strong>同时</strong> 发生的。给你 <code>m x n</code> 网格面板 <code>board</code> 的当前状态，返回下一个状态。</p>

<p>给定当前&nbsp;<code>board</code>&nbsp;的状态，<strong>更新</strong>&nbsp;<code>board</code>&nbsp;到下一个状态。</p>

<p><strong>注意</strong> 你不需要返回任何东西。</p>

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：原地标记

我们不妨定义两个新的状态，其中状态 $2$ 表示活细胞在下一个状态转为死细胞，状态 $-1$ 表示死细胞在下一个状态转为活细胞。那么，对于当前遍历到的格子，如果格子大于 $0$，就表示当前格子是活细胞，否则就是死细胞。

因此，我们可以遍历整个面板，对于每个格子，统计其周围的活细胞数目，用变量 $live$ 表示。如果当前格子是活细胞，那么当 $live \lt 2$ 或者 $live \gt 3$ 时，当前格子的下一个状态是死细胞，即状态 $2$；如果当前格子是死细胞，那么当 $live = 3$ 时，当前格子的下一个状态是活细胞，即状态 $-1$。

最后，我们再遍历一遍面板，将状态 $2$ 的格子更新为死细胞，将状态 $-1$ 的格子更新为活细胞。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别是面板的行数和列数。我们需要遍历整个面板。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def gameOfLife(self, board: List[List[int]]) -> None:
        m, n = len(board), len(board[0])
        for i in range(m):
            for j in range(n):
                live = -board[i][j]
                for x in range(i - 1, i + 2):
                    for y in range(j - 1, j + 2):
                        if 0 <= x < m and 0 <= y < n and board[x][y] > 0:
                            live += 1
                if board[i][j] and (live < 2 or live > 3):
                    board[i][j] = 2
                if board[i][j] == 0 and live == 3:
                    board[i][j] = -1
        for i in range(m):
            for j in range(n):
                if board[i][j] == 2:
                    board[i][j] = 0
                elif board[i][j] == -1:
                    board[i][j] = 1
```

#### Java

```java
class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int live = -board[i][j];
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] > 0) {
                            ++live;
                        }
                    }
                }
                if (board[i][j] == 1 && (live < 2 || live > 3)) {
                    board[i][j] = 2;
                }
                if (board[i][j] == 0 && live == 3) {
                    board[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                } else if (board[i][j] == -1) {
                    board[i][j] = 1;
                }
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    void gameOfLife(vector<vector<int>>& board) {
        int m = board.size(), n = board[0].size();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int live = -board[i][j];
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] > 0) {
                            ++live;
                        }
                    }
                }
                if (board[i][j] == 1 && (live < 2 || live > 3)) {
                    board[i][j] = 2;
                }
                if (board[i][j] == 0 && live == 3) {
                    board[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                } else if (board[i][j] == -1) {
                    board[i][j] = 1;
                }
            }
        }
    }
};
```

#### Go

```go
func gameOfLife(board [][]int) {
	m, n := len(board), len(board[0])
	for i := 0; i < m; i++ {
		for j, v := range board[i] {
			live := -v
			for x := i - 1; x <= i+1; x++ {
				for y := j - 1; y <= j+1; y++ {
					if x >= 0 && x < m && y >= 0 && y < n && board[x][y] > 0 {
						live++
					}
				}
			}
			if v == 1 && (live < 2 || live > 3) {
				board[i][j] = 2
			}
			if v == 0 && live == 3 {
				board[i][j] = -1
			}
		}
	}
	for i := 0; i < m; i++ {
		for j, v := range board[i] {
			if v == 2 {
				board[i][j] = 0
			}
			if v == -1 {
				board[i][j] = 1
			}
		}
	}
}
```

#### TypeScript

```ts
/**
 Do not return anything, modify board in-place instead.
 */
function gameOfLife(board: number[][]): void {
    const m = board.length;
    const n = board[0].length;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            let live = -board[i][j];
            for (let x = i - 1; x <= i + 1; ++x) {
                for (let y = j - 1; y <= j + 1; ++y) {
                    if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] > 0) {
                        ++live;
                    }
                }
            }
            if (board[i][j] === 1 && (live < 2 || live > 3)) {
                board[i][j] = 2;
            }
            if (board[i][j] === 0 && live === 3) {
                board[i][j] = -1;
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] === 2) {
                board[i][j] = 0;
            }
            if (board[i][j] === -1) {
                board[i][j] = 1;
            }
        }
    }
}
```

#### Rust

```rust
const DIR: [(i32, i32); 8] = [
    (-1, 0),
    (1, 0),
    (0, -1),
    (0, 1),
    (-1, -1),
    (-1, 1),
    (1, -1),
    (1, 1),
];

impl Solution {
    #[allow(dead_code)]
    pub fn game_of_life(board: &mut Vec<Vec<i32>>) {
        let n = board.len();
        let m = board[0].len();
        let mut weight_vec: Vec<Vec<i32>> = vec![vec![0; m]; n];

        // Initialize the weight vector
        for i in 0..n {
            for j in 0..m {
                if board[i][j] == 0 {
                    continue;
                }
                for (dx, dy) in DIR {
                    let x = (i as i32) + dx;
                    let y = (j as i32) + dy;
                    if Self::check_bounds(x, y, n as i32, m as i32) {
                        weight_vec[x as usize][y as usize] += 1;
                    }
                }
            }
        }

        // Update the board
        for i in 0..n {
            for j in 0..m {
                if weight_vec[i][j] < 2 {
                    board[i][j] = 0;
                } else if weight_vec[i][j] <= 3 {
                    if board[i][j] == 0 && weight_vec[i][j] == 3 {
                        board[i][j] = 1;
                    }
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }

    #[allow(dead_code)]
    fn check_bounds(i: i32, j: i32, n: i32, m: i32) -> bool {
        i >= 0 && i < n && j >= 0 && j < m
    }
}
```

#### C#

```cs
public class Solution {
    public void GameOfLife(int[][] board) {
        int m = board.Length;
        int n = board[0].Length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int live = -board[i][j];
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] > 0) {
                            ++live;
                        }
                    }
                }
                if (board[i][j] == 1 && (live < 2 || live > 3)) {
                    board[i][j] = 2;
                }
                if (board[i][j] == 0 && live == 3) {
                    board[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                }
                if (board[i][j] == -1) {
                    board[i][j] = 1;
                }
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
