# [51. N-Queens](https://leetcode.com/problems/n-queens)

[中文文档](/solution/0000-0099/0051.N-Queens/README.md)

## Description

<p>The <strong>n-queens</strong> puzzle is the problem of placing <code>n</code> queens on an <code>n x n</code> chessboard such that no two queens attack each other.</p>

<p>Given an integer <code>n</code>, return <em>all distinct solutions to the <strong>n-queens puzzle</strong></em>. You may return the answer in <strong>any order</strong>.</p>

<p>Each solution contains a distinct board configuration of the n-queens&#39; placement, where <code>&#39;Q&#39;</code> and <code>&#39;.&#39;</code> both indicate a queen and an empty space, respectively.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0051.N-Queens/images/queens.jpg" style="width: 600px; height: 268px;" />
<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> [[&quot;.Q..&quot;,&quot;...Q&quot;,&quot;Q...&quot;,&quot;..Q.&quot;],[&quot;..Q.&quot;,&quot;Q...&quot;,&quot;...Q&quot;,&quot;.Q..&quot;]]
<strong>Explanation:</strong> There exist two distinct solutions to the 4-queens puzzle as shown above
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> [[&quot;Q&quot;]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 9</code></li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        def dfs(i: int):
            if i == n:
                ans.append(["".join(row) for row in g])
                return
            for j in range(n):
                if col[j] + dg[i + j] + udg[n - i + j] == 0:
                    g[i][j] = "Q"
                    col[j] = dg[i + j] = udg[n - i + j] = 1
                    dfs(i + 1)
                    col[j] = dg[i + j] = udg[n - i + j] = 0
                    g[i][j] = "."

        ans = []
        g = [["."] * n for _ in range(n)]
        col = [0] * n
        dg = [0] * (n << 1)
        udg = [0] * (n << 1)
        dfs(0)
        return ans
```

### **Java**

```java
class Solution {
    private List<List<String>> ans = new ArrayList<>();
    private int[] col;
    private int[] dg;
    private int[] udg;
    private String[][] g;
    private int n;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        col = new int[n];
        dg = new int[n << 1];
        udg = new int[n << 1];
        g = new String[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], ".");
        }
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == n) {
            List<String> t = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                t.add(String.join("", g[j]));
            }
            ans.add(t);
            return;
        }
        for (int j = 0; j < n; ++j) {
            if (col[j] + dg[i + j] + udg[n - i + j] == 0) {
                g[i][j] = "Q";
                col[j] = dg[i + j] = udg[n - i + j] = 1;
                dfs(i + 1);
                col[j] = dg[i + j] = udg[n - i + j] = 0;
                g[i][j] = ".";
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
        vector<int> col(n);
        vector<int> dg(n << 1);
        vector<int> udg(n << 1);
        vector<vector<string>> ans;
        vector<string> t(n, string(n, '.'));
        function<void(int)> dfs = [&](int i) -> void {
            if (i == n) {
                ans.push_back(t);
                return;
            }
            for (int j = 0; j < n; ++j) {
                if (col[j] + dg[i + j] + udg[n - i + j] == 0) {
                    t[i][j] = 'Q';
                    col[j] = dg[i + j] = udg[n - i + j] = 1;
                    dfs(i + 1);
                    col[j] = dg[i + j] = udg[n - i + j] = 0;
                    t[i][j] = '.';
                }
            }
        };
        dfs(0);
        return ans;
    }
};
```

### **Go**

```go
func solveNQueens(n int) (ans [][]string) {
	col := make([]int, n)
	dg := make([]int, n<<1)
	udg := make([]int, n<<1)
	t := make([][]byte, n)
	for i := range t {
		t[i] = make([]byte, n)
		for j := range t[i] {
			t[i][j] = '.'
		}
	}
	var dfs func(int)
	dfs = func(i int) {
		if i == n {
			tmp := make([]string, n)
			for i := range tmp {
				tmp[i] = string(t[i])
			}
			ans = append(ans, tmp)
			return
		}
		for j := 0; j < n; j++ {
			if col[j]+dg[i+j]+udg[n-i+j] == 0 {
				col[j], dg[i+j], udg[n-i+j] = 1, 1, 1
				t[i][j] = 'Q'
				dfs(i + 1)
				t[i][j] = '.'
				col[j], dg[i+j], udg[n-i+j] = 0, 0, 0
			}
		}
	}
	dfs(0)
	return
}
```

### **TypeScript**

```ts
function solveNQueens(n: number): string[][] {
    const col: number[] = new Array(n).fill(0);
    const dg: number[] = new Array(n << 1).fill(0);
    const udg: number[] = new Array(n << 1).fill(0);
    const ans: string[][] = [];
    const t: string[][] = Array(n)
        .fill(0)
        .map(() => Array(n).fill('.'));
    const dfs = (i: number) => {
        if (i === n) {
            ans.push(t.map(x => x.join('')));
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (col[j] + dg[i + j] + udg[n - i + j] === 0) {
                t[i][j] = 'Q';
                col[j] = dg[i + j] = udg[n - i + j] = 1;
                dfs(i + 1);
                col[j] = dg[i + j] = udg[n - i + j] = 0;
                t[i][j] = '.';
            }
        }
    };
    dfs(0);
    return ans;
}
```

### **C#**

```cs
public class Solution {
    private int n;
    private int[] col;
    private int[] dg;
    private int[] udg;
    private IList<IList<string>> ans = new List<IList<string>>();
    private IList<string> t = new List<string>();

    public IList<IList<string>> SolveNQueens(int n) {
        this.n = n;
        col = new int[n];
        dg = new int[n << 1];
        udg = new int[n << 1];
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == n) {
            ans.Add(new List<string>(t));
            return;
        }
        for (int j = 0; j < n; ++j) {
            if (col[j] + dg[i + j] + udg[n - i + j] == 0) {
                char[] row = new char[n];
                Array.Fill(row, '.');
                row[j] = 'Q';
                t.Add(new string(row));
                col[j] = dg[i + j] = udg[n - i + j] = 1;
                dfs(i + 1);
                col[j] = dg[i + j] = udg[n - i + j] = 0;
                t.RemoveAt(t.Count - 1);
            }
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
