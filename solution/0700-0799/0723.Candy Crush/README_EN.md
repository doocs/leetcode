# [723. Candy Crush](https://leetcode.com/problems/candy-crush)

[中文文档](/solution/0700-0799/0723.Candy%20Crush/README.md)

## Description

<p>This question is about implementing a basic elimination algorithm for Candy Crush.</p>

<p>Given a 2D integer array <code>board</code> representing the grid of candy, different positive integers <code>board[i][j]</code> represent different types of candies. A value of <code>board[i][j] = 0</code> represents that the cell at position <code>(i, j)</code> is empty. The given board represents the state of the game following the player&#39;s move. Now, you need to restore the board to a <i>stable state</i> by crushing candies according to the following rules:</p>

<ol>
	<li>If three or more candies of the same type are adjacent vertically or horizontally, &quot;crush&quot; them all at the same time - these positions become empty.</li>
	<li>After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)</li>
	<li>After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.</li>
	<li>If there does not exist more candies that can be crushed (ie. the board is <i>stable</i>), then return the current board.</li>
</ol>

<p>You need to perform the above rules until the board becomes stable, then return the current board.</p>

<p>&nbsp;</p>

<p><b>Example:</b></p>

<pre style="white-space: pre-line">

<b>Input:</b>

board = 

[[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410,411,412,5,414],[5,1,512,3,3],[610,4,1,613,614],[710,1,2,713,714],[810,1,2,1,1],[1,1,2,2,2],[4,1,4,4,1014]]



<b>Output:</b>

[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],[610,211,112,313,614],[710,311,412,613,714],[810,411,512,713,1014]]



<b>Explanation:</b> 

<img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0723.Candy%20Crush/images/candy_crush_example_2.png" style="width: 777px; height: 532px;" />

</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ol>
	<li>The length of <code>board</code> will be in the range [3, 50].</li>
	<li>The length of <code>board[i]</code> will be in the range [3, 50].</li>
	<li>Each <code>board[i][j]</code> will initially start as an integer in the range [1, 2000].</li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def candyCrush(self, board: List[List[int]]) -> List[List[int]]:
        m, n = len(board), len(board[0])
        run = True
        while run:
            run = False
            for i in range(m):
                for j in range(n - 2):
                    if board[i][j] != 0 and abs(board[i][j]) == abs(board[i][j + 1]) and abs(board[i][j]) == abs(board[i][j + 2]):
                        run = True
                        board[i][j] = board[i][j + 1] = board[i][j + 2] = -abs(board[i][j])
            for j in range(n):
                for i in range(m - 2):
                    if board[i][j] != 0 and abs(board[i][j]) == abs(board[i + 1][j]) and abs(board[i][j]) == abs(board[i + 2][j]):
                        run = True
                        board[i][j] = board[i + 1][j] = board[i + 2][j] = -abs(board[i][j])
            if run:
                for j in range(n):
                    curr = m - 1
                    for i in range(m - 1, -1, -1):
                        if board[i][j] > 0:
                            board[curr][j] = board[i][j]
                            curr -= 1
                    while curr > -1:
                        board[curr][j] = 0
                        curr -= 1
        return board
```

### **Java**

```java
class Solution {
    public int[][] candyCrush(int[][] board) {
        int m = board.length, n = board[0].length;
        boolean run = true;
        while (run) {
            run = false;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n - 2; ++j) {
                    if (board[i][j] != 0 && Math.abs(board[i][j]) == Math.abs(board[i][j + 1]) && Math.abs(board[i][j]) == Math.abs(board[i][j + 2])) {
                        run = true;
                        board[i][j] = board[i][j + 1] = board[i][j + 2] = -Math.abs(board[i][j]);
                    }
                }
            }
            for (int j = 0; j < n; ++j) {
                for (int i = 0; i < m - 2; ++i) {
                    if (board[i][j] != 0 && Math.abs(board[i][j]) == Math.abs(board[i + 1][j]) && Math.abs(board[i][j]) == Math.abs(board[i + 2][j])) {
                        run = true;
                        board[i][j] = board[i + 1][j] = board[i + 2][j] = -Math.abs(board[i][j]);
                    }
                }
            }
            if (run) {
                for (int j = 0; j < n; ++j) {
                    int curr = m - 1;
                    for (int i = m - 1; i >= 0; --i) {
                        if (board[i][j] > 0) {
                            board[curr][j] = board[i][j];
                            --curr;
                        }
                    }
                    while (curr > -1) {
                        board[curr][j] = 0;
                        --curr;
                    }
                }
            }
        }
        return board;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> candyCrush(vector<vector<int>>& board) {
        int m = board.size(), n = board[0].size();
        bool run = true;
        while (run)
        {
            run = false;
            for (int i = 0; i < m; ++i)
            {
                for (int j = 0; j < n - 2; ++j)
                {
                    if (board[i][j] != 0 && abs(board[i][j]) == abs(board[i][j + 1]) && abs(board[i][j]) == abs(board[i][j + 2]))
                    {
                        run = true;
                        board[i][j] = board[i][j + 1] = board[i][j + 2] = -abs(board[i][j]);
                    }
                }
            }
            for (int j = 0; j < n; ++j)
            {
                for (int i = 0; i < m - 2; ++i)
                {
                    if (board[i][j] != 0 && abs(board[i][j]) == abs(board[i + 1][j]) && abs(board[i][j]) == abs(board[i + 2][j]))
                    {
                        run = true;
                        board[i][j] = board[i + 1][j] = board[i + 2][j] = -abs(board[i][j]);
                    }
                }
            }
            if (run)
            {
                for (int j = 0; j < n; ++j)
                {
                    int curr = m - 1;
                    for (int i = m - 1; i >= 0; --i)
                    {
                        if (board[i][j] > 0)
                        {
                            board[curr][j] = board[i][j];
                            --curr;
                        }
                    }
                    while (curr > -1)
                    {
                        board[curr][j] = 0;
                        --curr;
                    }
                }
            }
        }
        return board;
    }
};
```

### **Go**

```go
func candyCrush(board [][]int) [][]int {
	m, n := len(board), len(board[0])
	run := true
	for run {
		run = false
		for i := 0; i < m; i++ {
			for j := 0; j < n-2; j++ {
				if board[i][j] != 0 && abs(board[i][j]) == abs(board[i][j+1]) && abs(board[i][j]) == abs(board[i][j+2]) {
					run = true
					t := -abs(board[i][j])
					board[i][j], board[i][j+1], board[i][j+2] = t, t, t
				}
			}
		}
		for j := 0; j < n; j++ {
			for i := 0; i < m-2; i++ {
				if board[i][j] != 0 && abs(board[i][j]) == abs(board[i+1][j]) && abs(board[i][j]) == abs(board[i+2][j]) {
					run = true
					t := -abs(board[i][j])
					board[i][j], board[i+1][j], board[i+2][j] = t, t, t
				}
			}
		}
		if run {
			for j := 0; j < n; j++ {
				curr := m - 1
				for i := m - 1; i >= 0; i-- {
					if board[i][j] > 0 {
						board[curr][j] = board[i][j]
						curr--
					}
				}
				for curr > -1 {
					board[curr][j] = 0
					curr--
				}
			}
		}
	}
	return board
}

func abs(x int) int {
	if x >= 0 {
		return x
	}
	return -x
}
```

### **...**

```

```

<!-- tabs:end -->
