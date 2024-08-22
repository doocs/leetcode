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

First, we traverse the chessboard to find the position of the rook $(x, y)$. Then, starting from $(x, y)$, we traverse in four directions: up, down, left, and right:

-   If we encounter a bishop or a boundary, we stop traversing in that direction.
-   If we encounter a pawn, we increment the answer by one, and then stop traversing in that direction.
-   Otherwise, we continue traversing.

After traversing in all four directions, we can get the answer.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the chessboard, respectively. In this problem, $m = n = 8$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numRookCaptures(self, board: List[List[str]]) -> int:
        ans = 0
        dirs = (-1, 0, 1, 0, -1)
        for i in range(8):
            for j in range(8):
                if board[i][j] == "R":
                    for a, b in pairwise(dirs):
                        x, y = i, j
                        while 0 <= x + a < 8 and 0 <= y + b < 8:
                            x, y = x + a, y + b
                            if board[x][y] == "p":
                                ans += 1
                                break
                            if board[x][y] == "B":
                                break
        return ans
```

#### Java

```java
class Solution {
    public int numRookCaptures(char[][] board) {
        int ans = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (board[i][j] == 'R') {
                    for (int k = 0; k < 4; ++k) {
                        int x = i, y = j;
                        int a = dirs[k], b = dirs[k + 1];
                        while (x + a >= 0 && x + a < 8 && y + b >= 0 && y + b < 8
                            && board[x + a][y + b] != 'B') {
                            x += a;
                            y += b;
                            if (board[x][y] == 'p') {
                                ++ans;
                                break;
                            }
                        }
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
    int numRookCaptures(vector<vector<char>>& board) {
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (board[i][j] == 'R') {
                    for (int k = 0; k < 4; ++k) {
                        int x = i, y = j;
                        int a = dirs[k], b = dirs[k + 1];
                        while (x + a >= 0 && x + a < 8 && y + b >= 0 && y + b < 8 && board[x + a][y + b] != 'B') {
                            x += a;
                            y += b;
                            if (board[x][y] == 'p') {
                                ++ans;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numRookCaptures(board [][]byte) (ans int) {
	dirs := [5]int{-1, 0, 1, 0, -1}
	for i := 0; i < 8; i++ {
		for j := 0; j < 8; j++ {
			if board[i][j] == 'R' {
				for k := 0; k < 4; k++ {
					x, y := i, j
					a, b := dirs[k], dirs[k+1]
					for x+a >= 0 && x+a < 8 && y+b >= 0 && y+b < 8 && board[x+a][y+b] != 'B' {
						x, y = x+a, y+b
						if board[x][y] == 'p' {
							ans++
							break
						}
					}
				}
			}
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
