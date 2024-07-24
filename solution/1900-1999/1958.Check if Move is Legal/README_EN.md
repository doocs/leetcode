---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1958.Check%20if%20Move%20is%20Legal/README_EN.md
rating: 1658
source: Biweekly Contest 58 Q2
tags:
    - Array
    - Enumeration
    - Matrix
---

<!-- problem:start -->

# [1958. Check if Move is Legal](https://leetcode.com/problems/check-if-move-is-legal)

[中文文档](/solution/1900-1999/1958.Check%20if%20Move%20is%20Legal/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> <code>8 x 8</code> grid <code>board</code>, where <code>board[r][c]</code> represents the cell <code>(r, c)</code> on a game board. On the board, free cells are represented by <code>&#39;.&#39;</code>, white cells are represented by <code>&#39;W&#39;</code>, and black cells are represented by <code>&#39;B&#39;</code>.</p>

<p>Each move in this game consists of choosing a free cell and changing it to the color you are playing as (either white or black). However, a move is only <strong>legal</strong> if, after changing it, the cell becomes the <strong>endpoint of a good line</strong> (horizontal, vertical, or diagonal).</p>

<p>A <strong>good line</strong> is a line of <strong>three or more cells (including the endpoints)</strong> where the endpoints of the line are <strong>one color</strong>, and the remaining cells in the middle are the <strong>opposite color</strong> (no cells in the line are free). You can find examples for good lines in the figure below:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1958.Check%20if%20Move%20is%20Legal/images/goodlines5.png" style="width: 500px; height: 312px;" />
<p>Given two integers <code>rMove</code> and <code>cMove</code> and a character <code>color</code> representing the color you are playing as (white or black), return <code>true</code> <em>if changing cell </em><code>(rMove, cMove)</code> <em>to color</em> <code>color</code> <em>is a <strong>legal</strong> move, or </em><code>false</code><em> if it is not legal</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1958.Check%20if%20Move%20is%20Legal/images/grid11.png" style="width: 350px; height: 350px;" />
<pre>
<strong>Input:</strong> board = [[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;B&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;W&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;W&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;W&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;W&quot;,&quot;B&quot;,&quot;B&quot;,&quot;.&quot;,&quot;W&quot;,&quot;W&quot;,&quot;W&quot;,&quot;B&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;B&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;B&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;W&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]], rMove = 4, cMove = 3, color = &quot;B&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> &#39;.&#39;, &#39;W&#39;, and &#39;B&#39; are represented by the colors blue, white, and black respectively, and cell (rMove, cMove) is marked with an &#39;X&#39;.
The two good lines with the chosen cell as an endpoint are annotated above with the red rectangles.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1958.Check%20if%20Move%20is%20Legal/images/grid2.png" style="width: 350px; height: 351px;" />
<pre>
<strong>Input:</strong> board = [[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;B&quot;,&quot;.&quot;,&quot;.&quot;,&quot;W&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;W&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;W&quot;,&quot;B&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;B&quot;,&quot;W&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;W&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;B&quot;]], rMove = 4, cMove = 4, color = &quot;W&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> While there are good lines with the chosen cell as a middle cell, there are no good lines with the chosen cell as an endpoint.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>board.length == board[r].length == 8</code></li>
	<li><code>0 &lt;= rMove, cMove &lt; 8</code></li>
	<li><code>board[rMove][cMove] == &#39;.&#39;</code></li>
	<li><code>color</code> is either <code>&#39;B&#39;</code> or <code>&#39;W&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We enumerate all possible directions. For each direction $(a, b)$, we start from $(\textit{rMove}, \textit{cMove})$ and use a variable $\textit{cnt}$ to record the number of cells we have passed. If, during our traversal, we encounter a cell of color $\textit{color}$ and $\textit{cnt} > 1$, then we have found a good line segment and return $\textit{true}$.

If no good line segments are found after the enumeration, we return $\textit{false}$.

The time complexity is $O(m + n)$, where $m$ is the number of rows and $n$ is the number of columns in $\textit{board}$, with $m = n = 8$ in this problem. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkMove(
        self, board: List[List[str]], rMove: int, cMove: int, color: str
    ) -> bool:
        for a in range(-1, 2):
            for b in range(-1, 2):
                if a == 0 and b == 0:
                    continue
                i, j = rMove, cMove
                cnt = 0
                while 0 <= i + a < 8 and 0 <= j + b < 8:
                    cnt += 1
                    i, j = i + a, j + b
                    if cnt > 1 and board[i][j] == color:
                        return True
                    if board[i][j] in (color, "."):
                        break
        return False
```

#### Java

```java
class Solution {
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        for (int a = -1; a <= 1; ++a) {
            for (int b = -1; b <= 1; ++b) {
                if (a == 0 && b == 0) {
                    continue;
                }
                int i = rMove, j = cMove;
                int cnt = 0;
                while (0 <= i + a && i + a < 8 && 0 <= j + b && j + b < 8) {
                    i += a;
                    j += b;
                    if (++cnt > 1 && board[i][j] == color) {
                        return true;
                    }
                    if (board[i][j] == color || board[i][j] == '.') {
                        break;
                    }
                }
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkMove(vector<vector<char>>& board, int rMove, int cMove, char color) {
        for (int a = -1; a <= 1; ++a) {
            for (int b = -1; b <= 1; ++b) {
                if (a == 0 && b == 0) {
                    continue;
                }
                int i = rMove, j = cMove;
                int cnt = 0;
                while (0 <= i + a && i + a < 8 && 0 <= j + b && j + b < 8) {
                    i += a;
                    j += b;
                    if (++cnt > 1 && board[i][j] == color) {
                        return true;
                    }
                    if (board[i][j] == color || board[i][j] == '.') {
                        break;
                    }
                }
            }
        }
        return false;
    }
};
```

#### Go

```go
func checkMove(board [][]byte, rMove int, cMove int, color byte) bool {
	for a := -1; a <= 1; a++ {
		for b := -1; b <= 1; b++ {
			if a == 0 && b == 0 {
				continue
			}
			i, j := rMove, cMove
			cnt := 0
			for 0 <= i+a && i+a < 8 && 0 <= j+b && j+b < 8 {
				i += a
				j += b
				cnt++
				if cnt > 1 && board[i][j] == color {
					return true
				}
				if board[i][j] == color || board[i][j] == '.' {
					break
				}
			}
		}
	}
	return false
}
```

#### TypeScript

```ts
function checkMove(board: string[][], rMove: number, cMove: number, color: string): boolean {
    for (let a = -1; a <= 1; ++a) {
        for (let b = -1; b <= 1; ++b) {
            if (a === 0 && b === 0) {
                continue;
            }
            let [i, j] = [rMove, cMove];
            let cnt = 0;
            while (0 <= i + a && i + a < 8 && 0 <= j + b && j + b < 8) {
                i += a;
                j += b;
                if (++cnt > 1 && board[i][j] === color) {
                    return true;
                }
                if (board[i][j] === color || board[i][j] === '.') {
                    break;
                }
            }
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
