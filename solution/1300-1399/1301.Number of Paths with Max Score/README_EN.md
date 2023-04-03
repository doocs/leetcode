# [1301. Number of Paths with Max Score](https://leetcode.com/problems/number-of-paths-with-max-score)

[中文文档](/solution/1300-1399/1301.Number%20of%20Paths%20with%20Max%20Score/README.md)

## Description

<p>You are given a square <code>board</code>&nbsp;of characters. You can move on the board starting at the bottom right square marked with the character&nbsp;<code>&#39;S&#39;</code>.</p>

<p>You need&nbsp;to reach the top left square marked with the character <code>&#39;E&#39;</code>. The rest of the squares are labeled either with a numeric character&nbsp;<code>1, 2, ..., 9</code> or with an obstacle <code>&#39;X&#39;</code>. In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.</p>

<p>Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the second is the number of such paths that you can take to get that maximum sum, <strong>taken modulo <code>10^9 + 7</code></strong>.</p>

<p>In case there is no path, return&nbsp;<code>[0, 0]</code>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre><strong>Input:</strong> board = ["E23","2X2","12S"]

<strong>Output:</strong> [7,1]

</pre><p><strong class="example">Example 2:</strong></p>

<pre><strong>Input:</strong> board = ["E12","1X1","21S"]

<strong>Output:</strong> [4,2]

</pre><p><strong class="example">Example 3:</strong></p>

<pre><strong>Input:</strong> board = ["E11","XXX","11S"]

<strong>Output:</strong> [0,0]

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>2 &lt;= board.length == board[i].length &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def pathsWithMaxScore(self, board: List[str]) -> List[int]:
        def update(i, j, x, y):
            if x >= n or y >= n or f[x][y] == -1 or board[i][j] in "XS":
                return
            if f[x][y] > f[i][j]:
                f[i][j] = f[x][y]
                g[i][j] = g[x][y]
            elif f[x][y] == f[i][j]:
                g[i][j] += g[x][y]

        n = len(board)
        f = [[-1] * n for _ in range(n)]
        g = [[0] * n for _ in range(n)]
        f[-1][-1], g[-1][-1] = 0, 1
        for i in range(n - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                update(i, j, i + 1, j)
                update(i, j, i, j + 1)
                update(i, j, i + 1, j + 1)
                if f[i][j] != -1 and board[i][j].isdigit():
                    f[i][j] += int(board[i][j])
        mod = 10**9 + 7
        return [0, 0] if f[0][0] == -1 else [f[0][0], g[0][0] % mod]
```

### **Java**

```java
class Solution {
    private List<String> board;
    private int n;
    private int[][] f;
    private int[][] g;
    private final int mod = (int) 1e9 + 7;

    public int[] pathsWithMaxScore(List<String> board) {
        n = board.size();
        this.board = board;
        f = new int[n][n];
        g = new int[n][n];
        for (var e : f) {
            Arrays.fill(e, -1);
        }
        f[n - 1][n - 1] = 0;
        g[n - 1][n - 1] = 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                update(i, j, i + 1, j);
                update(i, j, i, j + 1);
                update(i, j, i + 1, j + 1);
                if (f[i][j] != -1) {
                    char c = board.get(i).charAt(j);
                    if (c >= '0' && c <= '9') {
                        f[i][j] += (c - '0');
                    }
                }
            }
        }
        int[] ans = new int[2];
        if (f[0][0] != -1) {
            ans[0] = f[0][0];
            ans[1] = g[0][0];
        }
        return ans;
    }

    private void update(int i, int j, int x, int y) {
        if (x >= n || y >= n || f[x][y] == -1 || board.get(i).charAt(j) == 'X'
            || board.get(i).charAt(j) == 'S') {
            return;
        }
        if (f[x][y] > f[i][j]) {
            f[i][j] = f[x][y];
            g[i][j] = g[x][y];
        } else if (f[x][y] == f[i][j]) {
            g[i][j] = (g[i][j] + g[x][y]) % mod;
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> pathsWithMaxScore(vector<string>& board) {
        int n = board.size();
        const int mod = 1e9 + 7;
        int f[n][n];
        int g[n][n];
        memset(f, -1, sizeof(f));
        memset(g, 0, sizeof(g));
        f[n - 1][n - 1] = 0;
        g[n - 1][n - 1] = 1;

        auto update = [&](int i, int j, int x, int y) {
            if (x >= n || y >= n || f[x][y] == -1 || board[i][j] == 'X' || board[i][j] == 'S') {
                return;
            }
            if (f[x][y] > f[i][j]) {
                f[i][j] = f[x][y];
                g[i][j] = g[x][y];
            } else if (f[x][y] == f[i][j]) {
                g[i][j] = (g[i][j] + g[x][y]) % mod;
            }
        };

        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                update(i, j, i + 1, j);
                update(i, j, i, j + 1);
                update(i, j, i + 1, j + 1);
                if (f[i][j] != -1) {
                    if (board[i][j] >= '0' && board[i][j] <= '9') {
                        f[i][j] += (board[i][j] - '0');
                    }
                }
            }
        }
        vector<int> ans(2);
        if (f[0][0] != -1) {
            ans[0] = f[0][0];
            ans[1] = g[0][0];
        }
        return ans;
    }
};
```

### **Go**

```go
func pathsWithMaxScore(board []string) []int {
	n := len(board)
	f := make([][]int, n)
	g := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		g[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	f[n-1][n-1] = 0
	g[n-1][n-1] = 1
	const mod = 1e9 + 7

	update := func(i, j, x, y int) {
		if x >= n || y >= n || f[x][y] == -1 || board[i][j] == 'X' || board[i][j] == 'S' {
			return
		}
		if f[x][y] > f[i][j] {
			f[i][j] = f[x][y]
			g[i][j] = g[x][y]
		} else if f[x][y] == f[i][j] {
			g[i][j] = (g[i][j] + g[x][y]) % mod
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			update(i, j, i+1, j)
			update(i, j, i, j+1)
			update(i, j, i+1, j+1)
			if f[i][j] != -1 && board[i][j] >= '0' && board[i][j] <= '9' {
				f[i][j] += int(board[i][j] - '0')
			}
		}
	}
	ans := make([]int, 2)
	if f[0][0] != -1 {
		ans[0], ans[1] = f[0][0], g[0][0]
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
