# [1958. 检查操作是否合法](https://leetcode.cn/problems/check-if-move-is-legal)

[English Version](/solution/1900-1999/1958.Check%20if%20Move%20is%20Legal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从&nbsp;<strong>0</strong>&nbsp;开始的&nbsp;<code>8 x 8</code> 网格&nbsp;<code>board</code>&nbsp;，其中&nbsp;<code>board[r][c]</code>&nbsp;表示游戏棋盘上的格子&nbsp;<code>(r, c)</code>&nbsp;。棋盘上空格用&nbsp;<code>'.'</code>&nbsp;表示，白色格子用&nbsp;<code>'W'</code>&nbsp;表示，黑色格子用&nbsp;<code>'B'</code>&nbsp;表示。</p>

<p>游戏中每次操作步骤为：选择一个空格子，将它变成你正在执行的颜色（要么白色，要么黑色）。但是，<strong>合法 </strong>操作必须满足：涂色后这个格子是 <strong>好线段的一个端点</strong>&nbsp;（好线段可以是水平的，竖直的或者是对角线）。</p>

<p><strong>好线段</strong>&nbsp;指的是一个包含 <strong>三个或者更多格子（包含端点格子）</strong>的线段，线段两个端点格子为 <strong>同一种颜色</strong>&nbsp;，且中间剩余格子的颜色都为 <strong>另一种颜色</strong>&nbsp;（线段上不能有任何空格子）。你可以在下图找到好线段的例子：</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1958.Check%20if%20Move%20is%20Legal/images/goodlines5.png" style="width: 500px; height: 312px;" />
<p>给你两个整数&nbsp;<code>rMove</code> 和&nbsp;<code>cMove</code>&nbsp;以及一个字符&nbsp;<code>color</code>&nbsp;，表示你正在执行操作的颜色（白或者黑），如果将格子&nbsp;<code>(rMove, cMove)</code>&nbsp;变成颜色&nbsp;<code>color</code>&nbsp;后，是一个&nbsp;<strong>合法</strong>&nbsp;操作，那么返回&nbsp;<code>true</code>&nbsp;，如果不是合法操作返回&nbsp;<code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1958.Check%20if%20Move%20is%20Legal/images/grid11.png" style="width: 350px; height: 350px;" /></p>

<pre>
<b>输入：</b>board = [[".",".",".","B",".",".",".","."],[".",".",".","W",".",".",".","."],[".",".",".","W",".",".",".","."],[".",".",".","W",".",".",".","."],["W","B","B",".","W","W","W","B"],[".",".",".","B",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","W",".",".",".","."]], rMove = 4, cMove = 3, color = "B"
<b>输出：</b>true
<b>解释：</b>'.'，'W' 和 'B' 分别用颜色蓝色，白色和黑色表示。格子 (rMove, cMove) 用 'X' 标记。
以选中格子为端点的两个好线段在上图中用红色矩形标注出来了。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1958.Check%20if%20Move%20is%20Legal/images/grid2.png" style="width: 350px; height: 351px;" /></p>

<pre>
<b>输入：</b>board = [[".",".",".",".",".",".",".","."],[".","B",".",".","W",".",".","."],[".",".","W",".",".",".",".","."],[".",".",".","W","B",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".","B","W",".","."],[".",".",".",".",".",".","W","."],[".",".",".",".",".",".",".","B"]], rMove = 4, cMove = 4, color = "W"
<b>输出：</b>false
<b>解释：</b>虽然选中格子涂色后，棋盘上产生了好线段，但选中格子是作为中间格子，没有产生以选中格子为端点的好线段。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>board.length == board[r].length == 8</code></li>
	<li><code>0 &lt;= rMove, cMove &lt; 8</code></li>
	<li><code>board[rMove][cMove] == '.'</code></li>
	<li><code>color</code>&nbsp;要么是&nbsp;<code>'B'</code> 要么是&nbsp;<code>'W'</code>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkMove(
        self, board: List[List[str]], rMove: int, cMove: int, color: str
    ) -> bool:
        dirs = [(1, 0), (0, 1), (-1, 0), (0, -1), (1, 1), (1, -1), (-1, 1), (-1, -1)]
        n = 8
        for a, b in dirs:
            i, j = rMove, cMove
            t = 0
            while 0 <= i + a < n and 0 <= j + b < n:
                t += 1
                i, j = i + a, j + b
                if board[i][j] in ['.', color]:
                    break
            if board[i][j] == color and t > 1:
                return True
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    private static final int N = 8;

    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        for (int[] d : DIRS) {
            int i = rMove, j = cMove;
            int t = 0;
            int a = d[0], b = d[1];
            while (0 <= i + a && i + a < N && 0 <= j + b && j + b < N) {
                ++t;
                i += a;
                j += b;
                if (board[i][j] == '.' || board[i][j] == color) {
                    break;
                }
            }
            if (board[i][j] == color && t > 1) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    int n = 8;

    bool checkMove(vector<vector<char>>& board, int rMove, int cMove, char color) {
        for (auto& d : dirs) {
            int a = d[0], b = d[1];
            int i = rMove, j = cMove;
            int t = 0;
            while (0 <= i + a && i + a < n && 0 <= j + b && j + b < n) {
                ++t;
                i += a;
                j += b;
                if (board[i][j] == '.' || board[i][j] == color) break;
            }
            if (board[i][j] == color && t > 1) return true;
        }
        return false;
    }
};
```

### **Go**

```go
func checkMove(board [][]byte, rMove int, cMove int, color byte) bool {
	dirs := [8][2]int{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}
	n := 8
	for _, d := range dirs {
		a, b := d[0], d[1]
		i, j := rMove, cMove
		t := 0
		for 0 <= i+a && i+a < n && 0 <= j+b && j+b < n {
			t++
			i += a
			j += b
			if board[i][j] == '.' || board[i][j] == color {
				break
			}
		}
		if board[i][j] == color && t > 1 {
			return true
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
