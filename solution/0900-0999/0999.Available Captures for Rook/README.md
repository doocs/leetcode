---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0999.Available%20Captures%20for%20Rook/README.md
tags:
    - 数组
    - 矩阵
    - 模拟
---

<!-- problem:start -->

# [999. 可以被一步捕获的棋子数](https://leetcode.cn/problems/available-captures-for-rook)

[English Version](/solution/0900-0999/0999.Available%20Captures%20for%20Rook/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个&nbsp;<code>8 x 8</code> 的棋盘，<strong>只有一个</strong> 白色的车，用字符 <code>'R'</code> 表示。棋盘上还可能存在白色的象&nbsp;<code>'B'</code>&nbsp;以及黑色的卒&nbsp;<code>'p'</code>。空方块用字符 <code>'.'</code>&nbsp;表示。</p>

<p>车可以按水平或竖直方向（上，下，左，右）移动任意个方格直到它遇到另一个棋子或棋盘的边界。如果它能够在一次移动中移动到棋子的方格，则能够 <strong>吃掉</strong> 棋子。</p>

<p>注意：车不能穿过其它棋子，比如象和卒。这意味着如果有其它棋子挡住了路径，车就不能够吃掉棋子。</p>

<p>返回白车 <strong>攻击</strong>&nbsp;范围内 <strong>兵的数量</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0999.Available%20Captures%20for%20Rook/images/1253_example_1_improved.png" style="height: 305px; width: 300px;" /></p>

<pre>
<strong>输入：</strong>[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
<strong>输出：</strong>3
<strong>解释：
</strong>在本例中，车能够吃掉所有的卒。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0999.Available%20Captures%20for%20Rook/images/1253_example_2_improved.png" style="height: 306px; width: 300px;" /></p>

<pre>
<strong>输入：</strong>[[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],[".","p","p","B","p","p",".","."],[".","p","B","R","B","p",".","."],[".","p","p","B","p","p",".","."],[".","p","p","p","p","p",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
<strong>输出：</strong>0
<strong>解释：
</strong>象阻止了车吃掉任何卒。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0999.Available%20Captures%20for%20Rook/images/1253_example_3_improved.png" style="height: 305px; width: 300px;" /></p>

<pre>
<strong>输入：</strong>[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","p",".",".",".","."],["p","p",".","R",".","p","B","."],[".",".",".",".",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."]]
<strong>输出：</strong>3
<strong>解释： </strong>
车可以吃掉位置 b5，d6 和 f5 的卒。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>board.length == 8</code></li>
	<li><code>board[i].length == 8</code></li>
	<li><code>board[i][j]</code> 可以是&nbsp;<code>'R'</code>，<code>'.'</code>，<code>'B'</code>&nbsp;或&nbsp;<code>'p'</code></li>
	<li>只有一个格子上存在&nbsp;<code>board[i][j] == 'R'</code></li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们先遍历棋盘，找到车的位置 $(i, j)$，然后从 $(i, j)$ 出发，向上下左右四个方向遍历：

-   如果不是边界且不是象，则继续向前走；
-   如果是卒，则答案加一，并停止该方向的遍历。

遍历完四个方向后，即可得到答案。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别是棋盘的行数和列数，本题中 $m = n = 8$。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numRookCaptures(self, board: List[List[str]]) -> int:
        dirs = (-1, 0, 1, 0, -1)
        n = len(board)
        for i in range(n):
            for j in range(n):
                if board[i][j] == "R":
                    ans = 0
                    for a, b in pairwise(dirs):
                        x, y = i + a, j + b
                        while 0 <= x < n and 0 <= y < n and board[x][y] != "B":
                            if board[x][y] == "p":
                                ans += 1
                                break
                            x, y = x + a, y + b
                    return ans
```

#### Java

```java
class Solution {
    public int numRookCaptures(char[][] board) {
        final int[] dirs = {-1, 0, 1, 0, -1};
        int n = board.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'R') {
                    int ans = 0;
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        while (x >= 0 && x < n && y >= 0 && y < n && board[x][y] != 'B') {
                            if (board[x][y] == 'p') {
                                ++ans;
                                break;
                            }
                            x += dirs[k];
                            y += dirs[k + 1];
                        }
                    }
                    return ans;
                }
            }
        }
        return 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numRookCaptures(vector<vector<char>>& board) {
        const int dirs[5] = {-1, 0, 1, 0, -1};
        int n = board.size();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'R') {
                    int ans = 0;
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        while (x >= 0 && x < n && y >= 0 && y < n && board[x][y] != 'B') {
                            if (board[x][y] == 'p') {
                                ++ans;
                                break;
                            }
                            x += dirs[k];
                            y += dirs[k + 1];
                        }
                    }
                    return ans;
                }
            }
        }
        return 0;
    }
};
```

#### Go

```go
func numRookCaptures(board [][]byte) (ans int) {
    dirs := []int{-1, 0, 1, 0, -1}
    n := len(board)
    for i := 0; i < n; i++ {
        for j := 0; j < n; j++ {
            if board[i][j] == 'R' {
                for k := 0; k < 4; k++ {
                    x, y := i + dirs[k], j + dirs[k+1]
                    for x >= 0 && x < n && y >= 0 && y < n && board[x][y] != 'B' {
                        if board[x][y] == 'p' {
                            ans++
                            break
                        }
                        x += dirs[k]
                        y += dirs[k+1]
                    }
                }
                return
            }
        }
    }
    return
}
```

#### TypeScript

```ts
function numRookCaptures(board: string[][]): number {
    const dirs = [-1, 0, 1, 0, -1];
    const n = board.length;
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (board[i][j] === 'R') {
                let ans = 0;
                for (let k = 0; k < 4; k++) {
                    let [x, y] = [i + dirs[k], j + dirs[k + 1]];
                    while (x >= 0 && x < n && y >= 0 && y < n && board[x][y] !== 'B') {
                        if (board[x][y] === 'p') {
                            ans++;
                            break;
                        }
                        x += dirs[k];
                        y += dirs[k + 1];
                    }
                }
                return ans;
            }
        }
    }
    return 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn num_rook_captures(board: Vec<Vec<char>>) -> i32 {
        let dirs = [-1, 0, 1, 0, -1];
        let n = board.len();
        for i in 0..n {
            for j in 0..n {
                if board[i][j] == 'R' {
                    let mut ans = 0;
                    for k in 0..4 {
                        let mut x = i as i32 + dirs[k];
                        let mut y = j as i32 + dirs[k + 1];
                        while x >= 0
                            && x < n as i32
                            && y >= 0
                            && y < n as i32
                            && board[x as usize][y as usize] != 'B'
                        {
                            if board[x as usize][y as usize] == 'p' {
                                ans += 1;
                                break;
                            }
                            x += dirs[k];
                            y += dirs[k + 1];
                        }
                    }
                    return ans;
                }
            }
        }
        0
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
