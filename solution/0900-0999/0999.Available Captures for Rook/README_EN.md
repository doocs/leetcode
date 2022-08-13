# [999. Available Captures for Rook](https://leetcode.com/problems/available-captures-for-rook)

[中文文档](/solution/0900-0999/0999.Available%20Captures%20for%20Rook/README.md)

## Description

<p>On an <code>8 x 8</code> chessboard, there is <strong>exactly one</strong> white rook <code>&#39;R&#39;</code> and some number of white bishops <code>&#39;B&#39;</code>, black pawns <code>&#39;p&#39;</code>, and empty squares <code>&#39;.&#39;</code>.</p>

<p>When the rook moves, it chooses one of four cardinal directions (north, east, south, or west), then moves in that direction until it chooses to stop, reaches the edge of the board, captures a black pawn, or is blocked by a white bishop. A rook is considered <strong>attacking</strong> a pawn if the rook can capture the pawn on the rook&#39;s turn. The <strong>number of available captures</strong> for the white rook is the number of pawns that the rook is <strong>attacking</strong>.</p>

<p>Return <em>the <strong>number of available captures</strong> for the white rook</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0999.Available%20Captures%20for%20Rook/images/1253_example_1_improved.png" style="width: 300px; height: 305px;" />
<pre>
<strong>Input:</strong> board = [[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;R&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> In this example, the rook is attacking all the pawns.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0999.Available%20Captures%20for%20Rook/images/1253_example_2_improved.png" style="width: 300px; height: 306px;" />
<pre>
<strong>Input:</strong> board = [[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;p&quot;,&quot;B&quot;,&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;B&quot;,&quot;R&quot;,&quot;B&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;p&quot;,&quot;B&quot;,&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The bishops are blocking the rook from attacking any of the pawns.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0999.Available%20Captures%20for%20Rook/images/1253_example_3_improved.png" style="width: 300px; height: 305px;" />
<pre>
<strong>Input:</strong> board = [[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;R&quot;,&quot;.&quot;,&quot;p&quot;,&quot;B&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;B&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The rook is attacking the pawns at positions b5, d6, and f5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>board.length == 8</code></li>
	<li><code>board[i].length == 8</code></li>
	<li><code>board[i][j]</code> is either <code>&#39;R&#39;</code>, <code>&#39;.&#39;</code>, <code>&#39;B&#39;</code>, or <code>&#39;p&#39;</code></li>
	<li>There is exactly one cell with <code>board[i][j] == &#39;R&#39;</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numRookCaptures(self, board: List[List[str]]) -> int:
        x, y, n = 0, 0, 8
        for i in range(n):
            for j in range(n):
                if board[i][j] == 'R':
                    x, y = i, j
                    break
        ans = 0
        for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
            i, j = x, y
            while 0 <= i + a < n and 0 <= j + b < n and board[i + a][j + b] != 'B':
                i, j = i + a, j + b
                if board[i][j] == 'p':
                    ans += 1
                    break
        return ans
```

### **Java**

```java
class Solution {
    public int numRookCaptures(char[][] board) {
        int[] pos = find(board);
        int ans = 0, n = 8;
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for (int[] dir : dirs) {
            int x = pos[0], y = pos[1], a = dir[0], b = dir[1];
            while (x + a >= 0 && x + a < n && y + b >= 0 && y + b < n && board[x + a][y + b] != 'B') {
                x += a;
                y += b;
                if (board[x][y] == 'p') {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }

    private int[] find(char[][] board) {
        int n = 8;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'R') {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numRookCaptures(vector<vector<char>>& board) {
        vector<int> pos = find(board);
        int ans = 0, n = 8;
        vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (auto& dir : dirs) {
            int x = pos[0], y = pos[1], a = dir[0], b = dir[1];
            while (x + a >= 0 && x + a < n && y + b >= 0 && y + b < n && board[x + a][y + b] != 'B') {
                x += a;
                y += b;
                if (board[x][y] == 'p') {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }

    vector<int> find(vector<vector<char>>& board) {
        int n = 8;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'R') {
                    return {i, j};
                }
            }
        }
        return {};
    }
};
```

### **Go**

```go
func numRookCaptures(board [][]byte) int {
	n := 8

	find := func() []int {
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				if board[i][j] == 'R' {
					return []int{i, j}
				}
			}
		}
		return []int{}
	}

	pos := find()
	ans := 0
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for _, dir := range dirs {
		x, y, a, b := pos[0], pos[1], dir[0], dir[1]
		for x+a >= 0 && x+a < n && y+b >= 0 && y+b < n && board[x+a][y+b] != 'B' {
			x += a
			y += b
			if board[x][y] == 'p' {
				ans++
				break
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
