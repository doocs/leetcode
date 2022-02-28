# [79. Word Search](https://leetcode.com/problems/word-search)

[中文文档](/solution/0000-0099/0079.Word%20Search/README.md)

## Description

<p>Given an <code>m x n</code> grid of characters <code>board</code> and a string <code>word</code>, return <code>true</code> <em>if</em> <code>word</code> <em>exists in the grid</em>.</p>

<p>The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0079.Word%20Search/images/word2.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> board = [[&quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;E&quot;],[&quot;S&quot;,&quot;F&quot;,&quot;C&quot;,&quot;S&quot;],[&quot;A&quot;,&quot;D&quot;,&quot;E&quot;,&quot;E&quot;]], word = &quot;ABCCED&quot;
<strong>Output:</strong> true
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0079.Word%20Search/images/word-1.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> board = [[&quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;E&quot;],[&quot;S&quot;,&quot;F&quot;,&quot;C&quot;,&quot;S&quot;],[&quot;A&quot;,&quot;D&quot;,&quot;E&quot;,&quot;E&quot;]], word = &quot;SEE&quot;
<strong>Output:</strong> true
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0079.Word%20Search/images/word3.jpg" style="width: 322px; height: 242px;" />
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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        def dfs(i, j, cur):
            if cur == len(word):
                return True
            if i < 0 or i >= m or j < 0 or j >= n or board[i][j] == '0' or word[cur] != board[i][j]:
                return False
            t = board[i][j]
            board[i][j] = '0'
            for a, b in [[0, 1], [0, -1], [-1, 0], [1, 0]]:
                x, y = i + a, j + b
                if dfs(x, y, cur + 1):
                    return True
            board[i][j] = t
            return False

        m, n = len(board), len(board[0])
        return any(dfs(i, j, 0) for i in range(m) for j in range(n))
```

### **Java**

```java
class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(i, j, 0, m, n, board, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int cur, int m, int n, char[][] board, String word) {
        if (cur == word.length()) {
            return true;
        }
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(cur)) {
            return false;
        }
        board[i][j] += 256;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (dfs(x, y, cur + 1, m, n, board, word)) {
                return true;
            }
        }
        board[i][j] -= 256;
        return false;
    }
}
```

### **TypeScript**

```ts
function exist(board: string[][], word: string): boolean {
    let m = board.length,
        n = board[0].length;
    let visited = Array.from({ length: m }, v => new Array(n).fill(false));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (dfs(board, word, i, j, 0, visited)) {
                return true;
            }
        }
    }
    return false;
}

function dfs(
    board: string[][],
    word: string,
    i: number,
    j: number,
    depth: number,
    visited: boolean[][],
): boolean {
    let m = board.length,
        n = board[0].length;
    if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || visited[i][j]) {
        return false;
    }
    if (board[i][j] != word.charAt(depth)) {
        return false;
    }

    if (depth == word.length - 1) {
        return true;
    }

    visited[i][j] = true;
    ++depth;
    let res = false;
    for (let [dx, dy] of [
        [0, 1],
        [0, -1],
        [1, 0],
        [-1, 0],
    ]) {
        let x = i + dx,
            y = j + dy;
        res = res || dfs(board, word, x, y, depth, visited);
    }
    visited[i][j] = false;
    return res;
}
```

### **C++**

```cpp
class Solution {
public:
    bool exist(vector<vector<char>>& board, string word) {
        int m = board.size(), n = board[0].size();
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (dfs(i, j, 0, m, n, board, word))
                    return true;
        return false;
    }

    bool dfs(int i, int j, int cur, int m, int n, vector<vector<char>>& board, string& word) {
        if (cur == word.size()) return true;
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word[cur]) return false;
        char t = board[i][j];
        board[i][j] = '0';
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k)
        {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (dfs(x, y, cur + 1, m, n, board, word)) return true;
        }
        board[i][j] = t;
        return false;
    }
};
```

### **Go**

```go
func exist(board [][]byte, word string) bool {
	m, n := len(board), len(board[0])
	var dfs func(i, j, cur int) bool
	dfs = func(i, j, cur int) bool {
		if cur == len(word) {
			return true
		}
		if i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word[cur] {
			return false
		}
		t := board[i][j]
		board[i][j] = '0'
		dirs := []int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if dfs(x, y, cur+1) {
				return true
			}
		}
		board[i][j] = t
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

### **...**

```

```

<!-- tabs:end -->
