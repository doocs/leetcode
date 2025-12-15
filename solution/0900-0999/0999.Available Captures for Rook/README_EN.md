---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0999.Available%20Captures%20for%20Rook/README_EN.md
tags:
    - Array
    - Matrix
    - Simulation
---

<!-- problem:start -->

# [999. Available Captures for Rook](https://leetcode.com/problems/available-captures-for-rook)

[中文文档](/solution/0900-0999/0999.Available%20Captures%20for%20Rook/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>8 x 8</code> <strong>matrix</strong> representing a chessboard. There is <strong>exactly one</strong> white rook represented by <code>&#39;R&#39;</code>, some number of white bishops <code>&#39;B&#39;</code>, and some number of black pawns <code>&#39;p&#39;</code>. Empty squares are represented by <code>&#39;.&#39;</code>.</p>

<p>A rook can move any number of squares horizontally or vertically (up, down, left, right) until it reaches another piece <em>or</em> the edge of the board. A rook is <strong>attacking</strong> a pawn if it can move to the pawn&#39;s square in one move.</p>

<p>Note: A rook cannot move through other pieces, such as bishops or pawns. This means a rook cannot attack a pawn if there is another piece blocking the path.</p>

<p>Return the <strong>number of pawns</strong> the white rook is <strong>attacking</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0999.Available%20Captures%20for%20Rook/images/1253_example_1_improved.png" style="width: 300px; height: 305px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">board = [[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;R&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>In this example, the rook is attacking all the pawns.</p>
</div>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0999.Available%20Captures%20for%20Rook/images/1253_example_2_improved.png" style="width: 300px; height: 306px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">board = [[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;p&quot;,&quot;B&quot;,&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;B&quot;,&quot;R&quot;,&quot;B&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;p&quot;,&quot;B&quot;,&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The bishops are blocking the rook from attacking any of the pawns.</p>
</div>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0999.Available%20Captures%20for%20Rook/images/1253_example_3_improved.png" style="width: 300px; height: 305px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">board = [[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;R&quot;,&quot;.&quot;,&quot;p&quot;,&quot;B&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;B&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The rook is attacking the pawns at positions b5, d6, and f5.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>board.length == 8</code></li>
	<li><code>board[i].length == 8</code></li>
	<li><code>board[i][j]</code> is either <code>&#39;R&#39;</code>, <code>&#39;.&#39;</code>, <code>&#39;B&#39;</code>, or <code>&#39;p&#39;</code></li>
	<li>There is exactly one cell with <code>board[i][j] == &#39;R&#39;</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We first traverse the board to find the position of the rook $(i, j)$. Then, starting from $(i, j)$, we traverse in four directions: up, down, left, and right.

- If it is not the boundary and not a bishop, continue moving forward.
- If it is a pawn, increment the answer by one and stop traversing in that direction.

After traversing in all four directions, we get the answer.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the board, respectively. In this problem, $m = n = 8$. The space complexity is $O(1)$.

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
