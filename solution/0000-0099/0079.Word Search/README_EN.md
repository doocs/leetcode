# [79. Word Search](https://leetcode.com/problems/word-search)

[中文文档](/solution/0000-0099/0079.Word%20Search/README.md)

## Description

<p>Given a 2D board and a word, find if the word exists in the grid.</p>

<p>The word can be constructed from letters of sequentially adjacent cell, where &quot;adjacent&quot; cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.</p>

<p><strong>Example:</strong></p>

<pre>

board =

[

  [&#39;A&#39;,&#39;B&#39;,&#39;C&#39;,&#39;E&#39;],

  [&#39;S&#39;,&#39;F&#39;,&#39;C&#39;,&#39;S&#39;],

  [&#39;A&#39;,&#39;D&#39;,&#39;E&#39;,&#39;E&#39;]

]



Given word = &quot;<strong>ABCCED</strong>&quot;, return <strong>true</strong>.

Given word = &quot;<strong>SEE</strong>&quot;, return <strong>true</strong>.

Given word = &quot;<strong>ABCB</strong>&quot;, return <strong>false</strong>.

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>board</code>&nbsp;and <code>word</code> consists only of lowercase and uppercase English letters.</li>
	<li><code>1 &lt;= board.length &lt;= 200</code></li>
	<li><code>1 &lt;= board[i].length &lt;= 200</code></li>
	<li><code>1 &lt;= word.length &lt;= 10^3</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        def dfs(i, j, cur):
            if cur == len(word):
                return True
            if i < 0 or i >= m or j < 0 or j >= n or visited[i][j] or word[cur] != board[i][j]:
                return False
            visited[i][j] = True
            next = cur + 1
            res = dfs(i + 1, j, next) or dfs(i - 1, j, next) or dfs(i, j + 1, next) or dfs(i, j - 1, next)
            visited[i][j] = False
            return res
        m, n = len(board), len(board[0])
        visited = [[False for _ in range(n)] for _ in range(m)]
        for i in range(m):
            for j in range(n):
                res = dfs(i, j, 0)
                if res:
                    return True
        return False
```

### **Java**

```java
class Solution {
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        visited = new boolean[m][n];
        char[] chars = word.toCharArray();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                boolean res = dfs(board, i, j, chars, 0);
                if (res) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, char[] chars, int cur) {
        if (cur == chars.length) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        if (visited[i][j] || board[i][j] != chars[cur]) return false;
        visited[i][j] = true;
        int next = cur + 1;
        boolean res = dfs(board, i + 1, j, chars, next)
                || dfs(board, i - 1, j, chars, next)
                || dfs(board, i, j + 1, chars, next)
                || dfs(board, i, j - 1, chars, next);
        visited[i][j] = false;
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
