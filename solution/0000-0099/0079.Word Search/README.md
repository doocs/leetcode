# [79. 单词搜索](https://leetcode.cn/problems/word-search)

[English Version](/solution/0000-0099/0079.Word%20Search/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <code>m x n</code> 二维字符网格 <code>board</code> 和一个字符串单词 <code>word</code> 。如果 <code>word</code> 存在于网格中，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0079.Word%20Search/images/word2.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>输入：</strong>board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0079.Word%20Search/images/word-1.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>输入：</strong>board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0079.Word%20Search/images/word3.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>输入：</strong>board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n = board[i].length</code></li>
	<li><code>1 <= m, n <= 6</code></li>
	<li><code>1 <= word.length <= 15</code></li>
	<li><code>board</code> 和 <code>word</code> 仅由大小写英文字母组成</li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你可以使用搜索剪枝的技术来优化解决方案，使其在 <code>board</code> 更大的情况下可以更快解决问题？</p>

## 解法

### 方法一：DFS(回溯)

我们可以枚举网格的每一个位置 $(i, j)$ 作为搜索的起点，然后从起点开始进行深度优先搜索，如果可以搜索到单词的末尾，就说明单词存在，否则说明单词不存在。

因此，我们设计一个函数 $dfs(i, j, k)$，表示从网格的 $(i, j)$ 位置出发，且从单词的第 $k$ 个字符开始搜索，是否能够搜索成功。函数 $dfs(i, j, k)$ 的执行步骤如下：

-   如果 $k = |word|-1$，说明已经搜索到单词的最后一个字符，此时只需要判断网格 $(i, j)$ 位置的字符是否等于 $word[k]$，如果相等则说明单词存在，否则说明单词不存在。无论单词是否存在，都无需继续往下搜索，因此直接返回结果。
-   否则，如果 $word[k]$ 字符不等于网格 $(i, j)$ 位置的字符，说明本次搜索失败，直接返回 `false`。
-   否则，我们将网格 $(i, j)$ 位置的字符暂存于 $c$ 中，然后将此位置的字符修改为一个特殊字符 `'0'`，代表此位置的字符已经被使用过，防止之后搜索时重复使用。然后我们从 $(i, j)$ 位置的上、下、左、右四个方向分别出发，去搜索网格中第 $k+1$ 个字符，如果四个方向有任何一个方向搜索成功，就说明搜索成功，否则说明搜索失败，此时我们需要还原网格 $(i, j)$ 位置的字符，即把 $c$ 放回网格 $(i, j)$ 位置（回溯）。

在主函数中，我们枚举网格中的每一个位置 $(i, j)$ 作为起点，如果调用 $dfs(i, j, 0)$ 返回 `true`，就说明单词存在，否则说明单词不存在，返回 `false`。

时间复杂度 $O(m \times n \times 3^k)$，空间复杂度 $O(\min(m \times n, k))$。其中 $m$ 和 $n$ 分别是网格的行数和列数；而 $k$ 是字符串 $word$ 的长度。

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

<!-- end -->
