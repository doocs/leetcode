---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0079.Word%20Search/README_EN.md
tags:
    - Array
    - String
    - Backtracking
    - Matrix
---

<!-- problem:start -->

# [79. Word Search](https://leetcode.com/problems/word-search)

[中文文档](/solution/0000-0099/0079.Word%20Search/README.md)

## Description

<!-- description:start -->

<p>Given an <code>m x n</code> grid of characters <code>board</code> and a string <code>word</code>, return <code>true</code> <em>if</em> <code>word</code> <em>exists in the grid</em>.</p>

<p>The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0079.Word%20Search/images/word2.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> board = [[&quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;E&quot;],[&quot;S&quot;,&quot;F&quot;,&quot;C&quot;,&quot;S&quot;],[&quot;A&quot;,&quot;D&quot;,&quot;E&quot;,&quot;E&quot;]], word = &quot;ABCCED&quot;
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0079.Word%20Search/images/word-1.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> board = [[&quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;E&quot;],[&quot;S&quot;,&quot;F&quot;,&quot;C&quot;,&quot;S&quot;],[&quot;A&quot;,&quot;D&quot;,&quot;E&quot;,&quot;E&quot;]], word = &quot;SEE&quot;
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0079.Word%20Search/images/word3.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> board = [[&quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;E&quot;],[&quot;S&quot;,&quot;F&quot;,&quot;C&quot;,&quot;S&quot;],[&quot;A&quot;,&quot;D&quot;,&quot;E&quot;,&quot;E&quot;]], word = &quot;ABCB&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n = board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 6</code></li>
	<li><code>1 &lt;= word.length &lt;= 15</code></li>
	<li><code>board</code> and <code>word</code> consists of only lowercase and uppercase English letters.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you use search pruning to make your solution faster with a larger <code>board</code>?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS (Backtracking)

We can enumerate each position $(i, j)$ in the grid as the starting point of the search, and then start a depth-first search from the starting point. If we can search to the end of the word, it means the word exists, otherwise, it means the word does not exist.

Therefore, we design a function $dfs(i, j, k)$, which represents whether we can successfully search from the $(i, j)$ position of the grid, starting from the $k$th character of the word. The execution steps of the function $dfs(i, j, k)$ are as follows:

-   If $k = |word|-1$, it means that we have searched to the last character of the word. At this time, we only need to judge whether the character at the $(i, j)$ position of the grid is equal to $word[k]$. If they are equal, it means the word exists, otherwise, it means the word does not exist. Whether the word exists or not, there is no need to continue to search, so return the result directly.
-   Otherwise, if the $word[k]$ character is not equal to the character at the $(i, j)$ position of the grid, it means that the search failed this time, so return `false` directly.
-   Otherwise, we temporarily store the character at the $(i, j)$ position of the grid in $c$, and then modify the character at this position to a special character `'0'`, indicating that the character at this position has been used to prevent it from being reused in subsequent searches. Then we start from the up, down, left, and right directions of the $(i, j)$ position to search for the $k+1$th character in the grid. If any direction is successful, it means the search is successful, otherwise, it means the search failed. At this time, we need to restore the character at the $(i, j)$ position of the grid, that is, put $c$ back to the $(i, j)$ position (backtracking).

In the main function, we enumerate each position $(i, j)$ in the grid as the starting point. If calling $dfs(i, j, 0)$ returns `true`, it means the word exists, otherwise, it means the word does not exist, so return `false`.

The time complexity is $O(m \times n \times 3^k)$, and the space complexity is $O(\min(m \times n, k))$. Here, $m$ and $n$ are the number of rows and columns of the grid, respectively; and $k$ is the length of the string $word$.

<!-- tabs:start -->

```python
class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        def dfs(i: int, j: int, k: int) -> bool:
            if k == len(word) - 1:
                return board[i][j] == word[k]
            if board[i][j] != word[k]:
                return False
            c = board[i][j]
            board[i][j] = "0"
            for a, b in pairwise((-1, 0, 1, 0, -1)):
                x, y = i + a, j + b
                ok = 0 <= x < m and 0 <= y < n and board[x][y] != "0"
                if ok and dfs(x, y, k + 1):
                    return True
            board[i][j] = c
            return False

        m, n = len(board), len(board[0])
        return any(dfs(i, j, 0) for i in range(m) for j in range(n))
```

```java
class Solution {
    private int m;
    private int n;
    private String word;
    private char[][] board;

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        this.word = word;
        this.board = board;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int k) {
        if (k == word.length() - 1) {
            return board[i][j] == word.charAt(k);
        }
        if (board[i][j] != word.charAt(k)) {
            return false;
        }
        char c = board[i][j];
        board[i][j] = '0';
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int u = 0; u < 4; ++u) {
            int x = i + dirs[u], y = j + dirs[u + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '0' && dfs(x, y, k + 1)) {
                return true;
            }
        }
        board[i][j] = c;
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool exist(vector<vector<char>>& board, string word) {
        int m = board.size(), n = board[0].size();
        int dirs[5] = {-1, 0, 1, 0, -1};
        function<bool(int, int, int)> dfs = [&](int i, int j, int k) -> bool {
            if (k == word.size() - 1) {
                return board[i][j] == word[k];
            }
            if (board[i][j] != word[k]) {
                return false;
            }
            char c = board[i][j];
            board[i][j] = '0';
            for (int u = 0; u < 4; ++u) {
                int x = i + dirs[u], y = j + dirs[u + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '0' && dfs(x, y, k + 1)) {
                    return true;
                }
            }
            board[i][j] = c;
            return false;
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
};
```

```go
func exist(board [][]byte, word string) bool {
	m, n := len(board), len(board[0])
	var dfs func(int, int, int) bool
	dfs = func(i, j, k int) bool {
		if k == len(word)-1 {
			return board[i][j] == word[k]
		}
		if board[i][j] != word[k] {
			return false
		}
		dirs := [5]int{-1, 0, 1, 0, -1}
		c := board[i][j]
		board[i][j] = '0'
		for u := 0; u < 4; u++ {
			x, y := i+dirs[u], j+dirs[u+1]
			if x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '0' && dfs(x, y, k+1) {
				return true
			}
		}
		board[i][j] = c
		return false
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if dfs(i, j, 0) {
				return true
			}
		}
	}
	return false
}
```

```ts
function exist(board: string[][], word: string): boolean {
    const [m, n] = [board.length, board[0].length];
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number, k: number): boolean => {
        if (k === word.length - 1) {
            return board[i][j] === word[k];
        }
        if (board[i][j] !== word[k]) {
            return false;
        }
        const c = board[i][j];
        board[i][j] = '0';
        for (let u = 0; u < 4; ++u) {
            const [x, y] = [i + dirs[u], j + dirs[u + 1]];
            const ok = x >= 0 && x < m && y >= 0 && y < n;
            if (ok && board[x][y] !== '0' && dfs(x, y, k + 1)) {
                return true;
            }
        }
        board[i][j] = c;
        return false;
    };
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (dfs(i, j, 0)) {
                return true;
            }
        }
    }
    return false;
}
```

```rust
impl Solution {
    fn dfs(
        i: usize,
        j: usize,
        c: usize,
        word: &[u8],
        board: &Vec<Vec<char>>,
        vis: &mut Vec<Vec<bool>>
    ) -> bool {
        if (board[i][j] as u8) != word[c] {
            return false;
        }
        if c == word.len() - 1 {
            return true;
        }
        vis[i][j] = true;
        let dirs = [
            [-1, 0],
            [0, -1],
            [1, 0],
            [0, 1],
        ];
        for [x, y] in dirs.into_iter() {
            let i = x + (i as i32);
            let j = y + (j as i32);
            if i < 0 || i == (board.len() as i32) || j < 0 || j == (board[0].len() as i32) {
                continue;
            }
            let (i, j) = (i as usize, j as usize);
            if !vis[i][j] && Self::dfs(i, j, c + 1, word, board, vis) {
                return true;
            }
        }
        vis[i][j] = false;
        false
    }

    pub fn exist(board: Vec<Vec<char>>, word: String) -> bool {
        let m = board.len();
        let n = board[0].len();
        let word = word.as_bytes();
        let mut vis = vec![vec![false; n]; m];
        for i in 0..m {
            for j in 0..n {
                if Self::dfs(i, j, 0, word, &board, &mut vis) {
                    return true;
                }
            }
        }
        false
    }
}
```

```cs
public class Solution {
    private int m;
    private int n;
    private char[][] board;
    private string word;

    public bool Exist(char[][] board, string word) {
        m = board.Length;
        n = board[0].Length;
        this.board = board;
        this.word = word;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private bool dfs(int i, int j, int k) {
        if (k == word.Length - 1) {
            return board[i][j] == word[k];
        }
        if (board[i][j] != word[k]) {
            return false;
        }
        char c = board[i][j];
        board[i][j] = '0';
        int[] dirs = { -1, 0, 1, 0, -1 };
        for (int u = 0; u < 4; ++u) {
            int x = i + dirs[u];
            int y = j + dirs[u + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '0' && dfs(x, y, k + 1)) {
                return true;
            }
        }
        board[i][j] = c;
        return false;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
