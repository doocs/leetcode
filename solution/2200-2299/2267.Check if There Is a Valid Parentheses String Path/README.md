# [2267. 检查是否有合法括号字符串路径](https://leetcode.cn/problems/check-if-there-is-a-valid-parentheses-string-path)

[English Version](/solution/2200-2299/2267.Check%20if%20There%20Is%20a%20Valid%20Parentheses%20String%20Path/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个括号字符串是一个 <strong>非空</strong>&nbsp;且只包含&nbsp;<code>'('</code>&nbsp;和&nbsp;<code>')'</code>&nbsp;的字符串。如果下面&nbsp;<strong>任意</strong>&nbsp;条件为&nbsp;<strong>真</strong>&nbsp;，那么这个括号字符串就是&nbsp;<strong>合法的</strong>&nbsp;。</p>

<ul>
	<li>字符串是&nbsp;<code>()</code>&nbsp;。</li>
	<li>字符串可以表示为&nbsp;<code>AB</code>（<code>A</code>&nbsp;连接&nbsp;<code>B</code>），<code>A</code> 和&nbsp;<code>B</code>&nbsp;都是合法括号序列。</li>
	<li>字符串可以表示为&nbsp;<code>(A)</code>&nbsp;，其中&nbsp;<code>A</code>&nbsp;是合法括号序列。</li>
</ul>

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的括号网格图矩阵&nbsp;<code>grid</code>&nbsp;。网格图中一个&nbsp;<strong>合法括号路径</strong>&nbsp;是满足以下所有条件的一条路径：</p>

<ul>
	<li>路径开始于左上角格子&nbsp;<code>(0, 0)</code>&nbsp;。</li>
	<li>路径结束于右下角格子&nbsp;<code>(m - 1, n - 1)</code>&nbsp;。</li>
	<li>路径每次只会向 <strong>下</strong>&nbsp;或者向 <strong>右</strong>&nbsp;移动。</li>
	<li>路径经过的格子组成的括号字符串是<strong>&nbsp;合法</strong>&nbsp;的。</li>
</ul>

<p>如果网格图中存在一条 <strong>合法括号路径</strong>&nbsp;，请返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2267.Check%20if%20There%20Is%20a%20Valid%20Parentheses%20String%20Path/images/example1drawio.png" style="width: 521px; height: 300px;" /></p>

<pre>
<b>输入：</b>grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
<b>输出：</b>true
<b>解释：</b>上图展示了两条路径，它们都是合法括号字符串路径。
第一条路径得到的合法字符串是 "()(())" 。
第二条路径得到的合法字符串是 "((()))" 。
注意可能有其他的合法括号字符串路径。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2267.Check%20if%20There%20Is%20a%20Valid%20Parentheses%20String%20Path/images/example2drawio.png" style="width: 165px; height: 165px;" /></p>

<pre>
<b>输入：</b>grid = [[")",")"],["(","("]]
<b>输出：</b>false
<b>解释：</b>两条可行路径分别得到 "))(" 和 ")((" 。由于它们都不是合法括号字符串，我们返回 false 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>grid[i][j]</code>&nbsp;要么是&nbsp;<code>'('</code>&nbsp;，要么是&nbsp;<code>')'</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
