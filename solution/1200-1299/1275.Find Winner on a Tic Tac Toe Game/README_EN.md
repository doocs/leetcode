# [1275. Find Winner on a Tic Tac Toe Game](https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game)

[中文文档](/solution/1200-1299/1275.Find%20Winner%20on%20a%20Tic%20Tac%20Toe%20Game/README.md)

## Description

<p><strong>Tic-tac-toe</strong> is played by two players <code>A</code> and <code>B</code> on a <code>3 x 3</code> grid. The rules of Tic-Tac-Toe are:</p>

<ul>
	<li>Players take turns placing characters into empty squares <code>&#39; &#39;</code>.</li>
	<li>The first player <code>A</code> always places <code>&#39;X&#39;</code> characters, while the second player <code>B</code> always places <code>&#39;O&#39;</code> characters.</li>
	<li><code>&#39;X&#39;</code> and <code>&#39;O&#39;</code> characters are always placed into empty squares, never on filled ones.</li>
	<li>The game ends when there are <strong>three</strong> of the same (non-empty) character filling any row, column, or diagonal.</li>
	<li>The game also ends if all squares are non-empty.</li>
	<li>No more moves can be played if the game is over.</li>
</ul>

<p>Given a 2D integer array <code>moves</code> where <code>moves[i] = [row<sub>i</sub>, col<sub>i</sub>]</code> indicates that the <code>i<sup>th</sup></code> move will be played on <code>grid[row<sub>i</sub>][col<sub>i</sub>]</code>. return <em>the winner of the game if it exists</em> (<code>A</code> or <code>B</code>). In case the game ends in a draw return <code>&quot;Draw&quot;</code>. If there are still movements to play return <code>&quot;Pending&quot;</code>.</p>

<p>You can assume that <code>moves</code> is valid (i.e., it follows the rules of <strong>Tic-Tac-Toe</strong>), the grid is initially empty, and <code>A</code> will play first.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1275.Find%20Winner%20on%20a%20Tic%20Tac%20Toe%20Game/images/xo1-grid.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
<strong>Output:</strong> &quot;A&quot;
<strong>Explanation:</strong> A wins, they always play first.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1275.Find%20Winner%20on%20a%20Tic%20Tac%20Toe%20Game/images/xo2-grid.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
<strong>Output:</strong> &quot;B&quot;
<strong>Explanation:</strong> B wins.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1275.Find%20Winner%20on%20a%20Tic%20Tac%20Toe%20Game/images/xo3-grid.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
<strong>Output:</strong> &quot;Draw&quot;
<strong>Explanation:</strong> The game ends in a draw since there are no moves to make.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= moves.length &lt;= 9</code></li>
	<li><code>moves[i].length == 2</code></li>
	<li><code>0 &lt;= row<sub>i</sub>, col<sub>i</sub> &lt;= 2</code></li>
	<li>There are no repeated elements on <code>moves</code>.</li>
	<li><code>moves</code> follow the rules of tic tac toe.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def tictactoe(self, moves: List[List[int]]) -> str:
        n = len(moves)
        counter = [0] * 8
        for i in range(n - 1, -1, -2):
            row, col = moves[i][0], moves[i][1]
            counter[row] += 1
            counter[col + 3] += 1
            if row == col:
                counter[6] += 1
            if row + col == 2:
                counter[7] += 1
            if (
                counter[row] == 3
                or counter[col + 3] == 3
                or counter[6] == 3
                or counter[7] == 3
            ):
                return "A" if (i % 2) == 0 else "B"
        return "Draw" if n == 9 else "Pending"
```

### **Java**

```java
class Solution {
    public String tictactoe(int[][] moves) {
        int n = moves.length;
        int[] counter = new int[8];
        for (int i = n - 1; i >= 0; i -= 2) {
            int row = moves[i][0], col = moves[i][1];
            ++counter[row];
            ++counter[col + 3];
            if (row == col) ++counter[6];
            if (row + col == 2) ++counter[7];
            if (counter[row] == 3 || counter[col + 3] == 3 || counter[6] == 3 || counter[7] == 3) {
                return (i % 2) == 0 ? "A" : "B";
            }
        }
        return n == 9 ? "Draw" : "Pending";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string tictactoe(vector<vector<int>>& moves) {
        int n = moves.size();
        vector<int> counter(8, 0);
        for (int i = n - 1; i >= 0; i -= 2) {
            int row = moves[i][0], col = moves[i][1];
            ++counter[row];
            ++counter[col + 3];
            if (row == col) ++counter[6];
            if (row + col == 2) ++counter[7];
            if (counter[row] == 3 || counter[col + 3] == 3 || counter[6] == 3 || counter[7] == 3) {
                return (i % 2 == 0) ? "A" : "B";
            }
        }
        return n == 9 ? "Draw" : "Pending";
    }
};
```

### **...**

```

```

<!-- tabs:end -->
