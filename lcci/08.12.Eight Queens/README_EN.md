# [08.12. Eight Queens](https://leetcode.cn/problems/eight-queens-lcci)

[中文文档](/lcci/08.12.Eight%20Queens/README.md)

## Description

<p>Write an algorithm to print all ways of arranging n queens on an n x n&nbsp;chess board so that none of them share the same row, column, or diagonal. In this case, &quot;diagonal&quot; means all diagonals, not just the two that bisect the board.</p>

<p><strong>Notes: </strong>This&nbsp;problem is a generalization of the original one in the book.</p>

<p><strong>Example:</strong></p>

<pre>

<strong> Input</strong>: 4

<strong> Output</strong>: [[&quot;.Q..&quot;,&quot;...Q&quot;,&quot;Q...&quot;,&quot;..Q.&quot;],[&quot;..Q.&quot;,&quot;Q...&quot;,&quot;...Q&quot;,&quot;.Q..&quot;]]

<strong> Explanation</strong>: 4 queens has following two solutions

[

&nbsp;[&quot;.Q..&quot;, &nbsp;// solution 1

&nbsp; &quot;...Q&quot;,

&nbsp; &quot;Q...&quot;,

&nbsp; &quot;..Q.&quot;],



&nbsp;[&quot;..Q.&quot;, &nbsp;// solution 2

&nbsp; &quot;Q...&quot;,

&nbsp; &quot;...Q&quot;,

&nbsp; &quot;.Q..&quot;]

]

</pre>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        res = []
        g = [['.'] * n for _ in range(n)]
        col = [False] * n
        dg = [False] * (2 * n)
        udg = [False] * (2 * n)

        def dfs(u):
            if u == n:
                res.append([''.join(item) for item in g])
                return
            for i in range(n):
                if not col[i] and not dg[u + i] and not udg[n - u + i]:
                    g[u][i] = 'Q'
                    col[i] = dg[u + i] = udg[n - u + i] = True
                    dfs(u + 1)
                    g[u][i] = '.'
                    col[i] = dg[u + i] = udg[n - u + i] = False

        dfs(0)
        return res
```

### **Java**

```java
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        String[][] g = new String[n][n];
        for (int i = 0; i < n; ++i) {
            String[] t = new String[n];
            Arrays.fill(t, ".");
            g[i] = t;
        }
        boolean[] col = new boolean[n];
        boolean[] dg = new boolean[2 * n];
        boolean[] udg = new boolean[2 * n];
        dfs(0, n, col, dg, udg, g, res);
        return res;
    }

    private void dfs(int u, int n, boolean[] col, boolean[] dg, boolean[] udg, String[][] g,
        List<List<String>> res) {
        if (u == n) {
            List<String> t = new ArrayList<>();
            for (String[] e : g) {
                t.add(String.join("", e));
            }
            res.add(t);
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (!col[i] && !dg[u + i] && !udg[n - u + i]) {
                g[u][i] = "Q";
                col[i] = dg[u + i] = udg[n - u + i] = true;
                dfs(u + 1, n, col, dg, udg, g, res);
                g[u][i] = ".";
                col[i] = dg[u + i] = udg[n - u + i] = false;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<string>> solveNQueens(int n) {
        vector<vector<string>> res;
        vector<string> g(n, string(n, '.'));
        vector<bool> col(n, false);
        vector<bool> dg(2 * n, false);
        vector<bool> udg(2 * n, false);
        dfs(0, n, col, dg, udg, g, res);
        return res;
    }

    void dfs(int u, int n, vector<bool>& col, vector<bool>& dg, vector<bool>& udg, vector<string>& g, vector<vector<string>>& res) {
        if (u == n) {
            res.push_back(g);
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (!col[i] && !dg[u + i] && !udg[n - u + i]) {
                g[u][i] = 'Q';
                col[i] = dg[u + i] = udg[n - u + i] = true;
                dfs(u + 1, n, col, dg, udg, g, res);
                g[u][i] = '.';
                col[i] = dg[u + i] = udg[n - u + i] = false;
            }
        }
    }
};
```

### **Go**

```go
func solveNQueens(n int) [][]string {
	res := [][]string{}
	g := make([][]string, n)
	for i := range g {
		g[i] = make([]string, n)
		for j := range g[i] {
			g[i][j] = "."
		}
	}
	col := make([]bool, n)
	dg := make([]bool, 2*n)
	udg := make([]bool, 2*n)
	dfs(0, n, col, dg, udg, g, &res)
	return res
}

func dfs(u, n int, col, dg, udg []bool, g [][]string, res *[][]string) {
	if u == n {
		t := make([]string, n)
		for i := 0; i < n; i++ {
			t[i] = strings.Join(g[i], "")
		}
		*res = append(*res, t)
		return
	}
	for i := 0; i < n; i++ {
		if !col[i] && !dg[u+i] && !udg[n-u+i] {
			g[u][i] = "Q"
			col[i], dg[u+i], udg[n-u+i] = true, true, true
			dfs(u+1, n, col, dg, udg, g, res)
			g[u][i] = "."
			col[i], dg[u+i], udg[n-u+i] = false, false, false
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
