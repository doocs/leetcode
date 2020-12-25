# [79. 单词搜索](https://leetcode-cn.com/problems/word-search)

[English Version](/solution/0000-0099/0079.Word%20Search/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个二维网格和一个单词，找出该单词是否存在于网格中。</p>

<p>单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中&ldquo;相邻&rdquo;单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre>board =
[
  [&#39;A&#39;,&#39;B&#39;,&#39;C&#39;,&#39;E&#39;],
  [&#39;S&#39;,&#39;F&#39;,&#39;C&#39;,&#39;S&#39;],
  [&#39;A&#39;,&#39;D&#39;,&#39;E&#39;,&#39;E&#39;]
]

给定 word = &quot;<strong>ABCCED</strong>&quot;, 返回 <strong>true</strong>
给定 word = &quot;<strong>SEE</strong>&quot;, 返回 <strong>true</strong>
给定 word = &quot;<strong>ABCB</strong>&quot;, 返回 <strong>false</strong></pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>board</code> 和 <code>word</code> 中只包含大写和小写英文字母。</li>
	<li><code>1 &lt;= board.length &lt;= 200</code></li>
	<li><code>1 &lt;= board[i].length &lt;= 200</code></li>
	<li><code>1 &lt;= word.length &lt;= 10^3</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

深度优先搜索 DFS 实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
