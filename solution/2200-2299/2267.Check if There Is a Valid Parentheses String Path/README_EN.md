# [2267. Check if There Is a Valid Parentheses String Path](https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path)

[中文文档](/solution/2200-2299/2267.Check%20if%20There%20Is%20a%20Valid%20Parentheses%20String%20Path/README.md)

## Description

<p>A parentheses string is a <strong>non-empty</strong> string consisting only of <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code>. It is <strong>valid</strong> if <strong>any</strong> of the following conditions is <strong>true</strong>:</p>

<ul>
	<li>It is <code>()</code>.</li>
	<li>It can be written as <code>AB</code> (<code>A</code> concatenated with <code>B</code>), where <code>A</code> and <code>B</code> are valid parentheses strings.</li>
	<li>It can be written as <code>(A)</code>, where <code>A</code> is a valid parentheses string.</li>
</ul>

<p>You are given an <code>m x n</code> matrix of parentheses <code>grid</code>. A <strong>valid parentheses string path</strong> in the grid is a path satisfying <strong>all</strong> of the following conditions:</p>

<ul>
	<li>The path starts from the upper left cell <code>(0, 0)</code>.</li>
	<li>The path ends at the bottom-right cell <code>(m - 1, n - 1)</code>.</li>
	<li>The path only ever moves <strong>down</strong> or <strong>right</strong>.</li>
	<li>The resulting parentheses string formed by the path is <strong>valid</strong>.</li>
</ul>

<p>Return <code>true</code> <em>if there exists a <strong>valid parentheses string path</strong> in the grid.</em> Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2267.Check%20if%20There%20Is%20a%20Valid%20Parentheses%20String%20Path/images/example1drawio.png" style="width: 521px; height: 300px;" />
<pre>
<strong>Input:</strong> grid = [[&quot;(&quot;,&quot;(&quot;,&quot;(&quot;],[&quot;)&quot;,&quot;(&quot;,&quot;)&quot;],[&quot;(&quot;,&quot;(&quot;,&quot;)&quot;],[&quot;(&quot;,&quot;(&quot;,&quot;)&quot;]]
<strong>Output:</strong> true
<strong>Explanation:</strong> The above diagram shows two possible paths that form valid parentheses strings.
The first path shown results in the valid parentheses string &quot;()(())&quot;.
The second path shown results in the valid parentheses string &quot;((()))&quot;.
Note that there may be other valid parentheses string paths.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2267.Check%20if%20There%20Is%20a%20Valid%20Parentheses%20String%20Path/images/example2drawio.png" style="width: 165px; height: 165px;" />
<pre>
<strong>Input:</strong> grid = [[&quot;)&quot;,&quot;)&quot;],[&quot;(&quot;,&quot;(&quot;]]
<strong>Output:</strong> false
<strong>Explanation:</strong> The two possible paths form the parentheses strings &quot;))(&quot; and &quot;)((&quot;. Since neither of them are valid parentheses strings, we return false.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>grid[i][j]</code> is either <code>&#39;(&#39;</code> or <code>&#39;)&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def hasValidPath(self, grid: List[List[str]]) -> bool:
        @cache
        def dfs(i, j, t):
            if grid[i][j] == '(':
                t += 1
            else:
                t -= 1
            if t < 0:
                return False
            if i == m - 1 and j == n - 1:
                return t == 0
            for x, y in [(i + 1, j), (i, j + 1)]:
                if x < m and y < n and dfs(x, y, t):
                    return True
            return False

        m, n = len(grid), len(grid[0])
        return dfs(0, 0, 0)
```

### **Java**

```java
class Solution {
    private boolean[][][] vis;
    private char[][] grid;
    private int m;
    private int n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        vis = new boolean[m][n][m + n];
        return dfs(0, 0, 0);
    }

    private boolean dfs(int i, int j, int t) {
        if (vis[i][j][t]) {
            return false;
        }
        vis[i][j][t] = true;
        t += grid[i][j] == '(' ? 1 : -1;
        if (t < 0) {
            return false;
        }
        if (i == m - 1 && j == n - 1) {
            return t == 0;
        }
        int[] dirs = {0, 1, 0};
        for (int k = 0; k < 2; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < m && y < n && dfs(x, y, t)) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
bool vis[100][100][200];
int dirs[3] = {1, 0, 1};

class Solution {
public:
    bool hasValidPath(vector<vector<char>>& grid) {
        memset(vis, 0, sizeof(vis));
        return dfs(0, 0, 0, grid);
    }

    bool dfs(int i, int j, int t, vector<vector<char>>& grid) {
        if (vis[i][j][t]) return false;
        vis[i][j][t] = true;
        t += grid[i][j] == '(' ? 1 : -1;
        if (t < 0) return false;
        int m = grid.size(), n = grid[0].size();
        if (i == m - 1 && j == n - 1) return t == 0;
        for (int k = 0; k < 2; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < m && y < n && dfs(x, y, t, grid)) return true;
        }
        return false;
    }
};
```

### **Go**

```go
func hasValidPath(grid [][]byte) bool {
	m, n := len(grid), len(grid[0])
	vis := make([][][]bool, m)
	for i := range vis {
		vis[i] = make([][]bool, n)
		for j := range vis[i] {
			vis[i][j] = make([]bool, m+n)
		}
	}
	var dfs func(int, int, int) bool
	dfs = func(i, j, t int) bool {
		if vis[i][j][t] {
			return false
		}
		vis[i][j][t] = true
		if grid[i][j] == '(' {
			t += 1
		} else {
			t -= 1
		}
		if t < 0 {
			return false
		}
		if i == m-1 && j == n-1 {
			return t == 0
		}
		dirs := []int{1, 0, 1}
		for k := 0; k < 2; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x < m && y < n && dfs(x, y, t) {
				return true
			}
		}
		return false
	}
	return dfs(0, 0, 0)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
