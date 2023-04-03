# [1301. 最大得分的路径数目](https://leetcode.cn/problems/number-of-paths-with-max-score)

[English Version](/solution/1300-1399/1301.Number%20of%20Paths%20with%20Max%20Score/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正方形字符数组&nbsp;<code>board</code>&nbsp;，你从数组最右下方的字符&nbsp;<code>&#39;S&#39;</code>&nbsp;出发。</p>

<p>你的目标是到达数组最左上角的字符&nbsp;<code>&#39;E&#39;</code> ，数组剩余的部分为数字字符&nbsp;<code>1, 2, ..., 9</code>&nbsp;或者障碍 <code>&#39;X&#39;</code>。在每一步移动中，你可以向上、向左或者左上方移动，可以移动的前提是到达的格子没有障碍。</p>

<p>一条路径的 「得分」 定义为：路径上所有数字的和。</p>

<p>请你返回一个列表，包含两个整数：第一个整数是 「得分」 的最大值，第二个整数是得到最大得分的方案数，请把结果对&nbsp;<strong><code>10^9 + 7</code></strong> <strong>取余</strong>。</p>

<p>如果没有任何路径可以到达终点，请返回&nbsp;<code>[0, 0]</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>board = [&quot;E23&quot;,&quot;2X2&quot;,&quot;12S&quot;]
<strong>输出：</strong>[7,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>board = [&quot;E12&quot;,&quot;1X1&quot;,&quot;21S&quot;]
<strong>输出：</strong>[4,2]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>board = [&quot;E11&quot;,&quot;XXX&quot;,&quot;11S&quot;]
<strong>输出：</strong>[0,0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= board.length == board[i].length &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i][j]$ 表示从起点 $(n - 1, n - 1)$ 到达 $(i, j)$ 的最大得分，定义 $g[i][j]$ 表示从起点 $(n - 1, n - 1)$ 到达 $(i, j)$ 的最大得分的方案数。初始时 $f[n - 1][n - 1] = 0$，并且 $g[n - 1][n - 1] = 1$。其它位置的 $f[i][j]$ 均为 $-1$，而 $g[i][j]$ 均为 $0$。

对于当前位置 $(i, j)$，它可以由 $(i + 1, j)$, $(i, j + 1)$, $(i + 1, j + 1)$ 三个位置转移而来，因此我们可以枚举这三个位置，更新 $f[i][j]$ 和 $g[i][j]$ 的值。如果当前位置 $(i, j)$ 有障碍，或者当前位置是起点，或者其它位置越界，则不进行更新。否则，如果其它位置 $(x, y)$ 满足 $f[x][y] \gt f[i][j]$，那么我们更新 $f[i][j] = f[x][y]$，并且 $g[i][j] = g[x][y]$。如果 $f[x][y] = f[i][j]$，那么我们更新 $g[i][j] = g[i][j] + g[x][y]$。最后，如果当前位置 $(i, j)$ 可达并且是数字，我们更新 $f[i][j] = f[i][j] + board[i][j]$。

最后，如果 $f[0][0] \lt 0$，说明没有路径可以到达终点，返回 $[0, 0]$。否则，返回 $[f[0][0], g[0][0]]$。注意，返回结果需要对 $10^9 + 7$ 取余。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是数组的边长。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
