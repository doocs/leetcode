# [782. Transform to Chessboard](https://leetcode.com/problems/transform-to-chessboard)

[中文文档](/solution/0700-0799/0782.Transform%20to%20Chessboard/README.md)

## Description

<p>You are given an <code>n x n</code> binary grid <code>board</code>. In each move, you can swap any two rows with each other, or any two columns with each other.</p>

<p>Return <em>the minimum number of moves to transform the board into a <strong>chessboard board</strong></em>. If the task is impossible, return <code>-1</code>.</p>

<p>A <strong>chessboard board</strong> is a board where no <code>0</code>&#39;s and no <code>1</code>&#39;s are 4-directionally adjacent.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0782.Transform%20to%20Chessboard/images/chessboard1-grid.jpg" style="width: 500px; height: 145px;" />
<pre>
<strong>Input:</strong> board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> One potential sequence of moves is shown.
The first move swaps the first and second column.
The second move swaps the second and third row.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0782.Transform%20to%20Chessboard/images/chessboard2-grid.jpg" style="width: 164px; height: 165px;" />
<pre>
<strong>Input:</strong> board = [[0,1],[1,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Also note that the board with 0 in the top left corner, is also a valid chessboard.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0782.Transform%20to%20Chessboard/images/chessboard3-grid.jpg" style="width: 164px; height: 165px;" />
<pre>
<strong>Input:</strong> board = [[1,0],[1,0]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> No matter what sequence of moves you make, you cannot end with a valid chessboard.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>2 &lt;= n &lt;= 30</code></li>
	<li><code>board[i][j]</code> is either&nbsp;<code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def movesToChessboard(self, board: List[List[int]]) -> int:
        def f(mask, cnt):
            ones = mask.bit_count()
            if n & 1:
                if abs(n - 2 * ones) != 1 or abs(n - 2 * cnt) != 1:
                    return -1
                if ones == n // 2:
                    return n // 2 - (mask & 0xAAAAAAAA).bit_count()
                return (n + 1) // 2 - (mask & 0x55555555).bit_count()
            else:
                if ones != n // 2 or cnt != n // 2:
                    return -1
                cnt0 = n // 2 - (mask & 0xAAAAAAAA).bit_count()
                cnt1 = n // 2 - (mask & 0x55555555).bit_count()
                return min(cnt0, cnt1)

        n = len(board)
        mask = (1 << n) - 1
        rowMask = colMask = 0
        for i in range(n):
            rowMask |= board[0][i] << i
            colMask |= board[i][0] << i
        revRowMask = mask ^ rowMask
        revColMask = mask ^ colMask
        sameRow = sameCol = 0
        for i in range(n):
            curRowMask = curColMask = 0
            for j in range(n):
                curRowMask |= board[i][j] << j
                curColMask |= board[j][i] << j
            if curRowMask not in (rowMask, revRowMask) or curColMask not in (colMask, revColMask):
                return -1
            sameRow += curRowMask == rowMask
            sameCol += curColMask == colMask
        t1 = f(rowMask, sameRow)
        t2 = f(colMask, sameCol)
        return -1 if t1 == -1 or t2 == -1 else t1 + t2
```

### **Java**

```java
class Solution {
    private int n;

    public int movesToChessboard(int[][] board) {
        n = board.length;
        int mask = (1 << n) - 1;
        int rowMask = 0, colMask = 0;
        for (int i = 0; i < n; ++i) {
            rowMask |= board[0][i] << i;
            colMask |= board[i][0] << i;
        }
        int revRowMask = mask ^ rowMask;
        int revColMask = mask ^ colMask;
        int sameRow = 0, sameCol = 0;
        for (int i = 0; i < n; ++i) {
            int curRowMask = 0, curColMask = 0;
            for (int j = 0; j < n; ++j) {
                curRowMask |= board[i][j] << j;
                curColMask |= board[j][i] << j;
            }
            if (curRowMask != rowMask && curRowMask != revRowMask) {
                return -1;
            }
            if (curColMask != colMask && curColMask != revColMask) {
                return -1;
            }
            sameRow += curRowMask == rowMask ? 1 : 0;
            sameCol += curColMask == colMask ? 1 : 0;
        }
        int t1 = f(rowMask, sameRow);
        int t2 = f(colMask, sameCol);
        return t1 == -1 || t2 == -1 ? -1 : t1 + t2;
    }

    private int f(int mask, int cnt) {
        int ones = Integer.bitCount(mask);
        if (n % 2 == 1) {
            if (Math.abs(n - ones * 2) != 1 || Math.abs(n - cnt * 2) != 1) {
                return -1;
            }
            if (ones == n / 2) {
                return n / 2 - Integer.bitCount(mask & 0xAAAAAAAA);
            }
            return (n / 2 + 1) - Integer.bitCount(mask & 0x55555555);
        } else {
            if (ones != n / 2 || cnt != n / 2) {
                return -1;
            }
            int cnt0 = n / 2 - Integer.bitCount(mask & 0xAAAAAAAA);
            int cnt1 = n / 2 - Integer.bitCount(mask & 0x55555555);
            return Math.min(cnt0, cnt1);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int n;
    int movesToChessboard(vector<vector<int>>& board) {
        n = board.size();
        int mask = (1 << n) - 1;
        int rowMask = 0, colMask = 0;
        for (int i = 0; i < n; ++i) {
            rowMask |= board[0][i] << i;
            colMask |= board[i][0] << i;
        }
        int revRowMask = mask ^ rowMask;
        int revColMask = mask ^ colMask;
        int sameRow = 0, sameCol = 0;
        for (int i = 0; i < n; ++i) {
            int curRowMask = 0, curColMask = 0;
            for (int j = 0; j < n; ++j) {
                curRowMask |= board[i][j] << j;
                curColMask |= board[j][i] << j;
            }
            if (curRowMask != rowMask && curRowMask != revRowMask) return -1;
            if (curColMask != colMask && curColMask != revColMask) return -1;
            sameRow += curRowMask == rowMask;
            sameCol += curColMask == colMask;
        }
        int t1 = f(rowMask, sameRow);
        int t2 = f(colMask, sameCol);
        return t1 == -1 || t2 == -1 ? -1 : t1 + t2;
    }

    int f(int mask, int cnt) {
        int ones = __builtin_popcount(mask);
        if (n & 1) {
            if (abs(n - ones * 2) != 1 || abs(n - cnt * 2) != 1) return -1;
            if (ones == n / 2) return n / 2 - __builtin_popcount(mask & 0xAAAAAAAA);
            return (n + 1) / 2 - __builtin_popcount(mask & 0x55555555);
        } else {
            if (ones != n / 2 || cnt != n / 2) return -1;
            int cnt0 = (n / 2 - __builtin_popcount(mask & 0xAAAAAAAA));
            int cnt1 = (n / 2 - __builtin_popcount(mask & 0x55555555));
            return min(cnt0, cnt1);
        }
    }
};
```

### **Go**

```go
func movesToChessboard(board [][]int) int {
	n := len(board)
	mask := (1 << n) - 1
	rowMask, colMask := 0, 0
	for i := 0; i < n; i++ {
		rowMask |= board[0][i] << i
		colMask |= board[i][0] << i
	}
	revRowMask := mask ^ rowMask
	revColMask := mask ^ colMask
	sameRow, sameCol := 0, 0
	for i := 0; i < n; i++ {
		curRowMask, curColMask := 0, 0
		for j := 0; j < n; j++ {
			curRowMask |= board[i][j] << j
			curColMask |= board[j][i] << j
		}
		if curRowMask != rowMask && curRowMask != revRowMask {
			return -1
		}
		if curColMask != colMask && curColMask != revColMask {
			return -1
		}
		if curRowMask == rowMask {
			sameRow++
		}
		if curColMask == colMask {
			sameCol++
		}
	}
	f := func(mask, cnt int) int {
		ones := bits.OnesCount(uint(mask))
		if n%2 == 1 {
			if abs(n-ones*2) != 1 || abs(n-cnt*2) != 1 {
				return -1
			}
			if ones == n/2 {
				return n/2 - bits.OnesCount(uint(mask&0xAAAAAAAA))
			}
			return (n+1)/2 - bits.OnesCount(uint(mask&0x55555555))
		} else {
			if ones != n/2 || cnt != n/2 {
				return -1
			}
			cnt0 := n/2 - bits.OnesCount(uint(mask&0xAAAAAAAA))
			cnt1 := n/2 - bits.OnesCount(uint(mask&0x55555555))
			return min(cnt0, cnt1)
		}
	}
	t1 := f(rowMask, sameRow)
	t2 := f(colMask, sameCol)
	if t1 == -1 || t2 == -1 {
		return -1
	}
	return t1 + t2
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
