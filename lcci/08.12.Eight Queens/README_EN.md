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

### Solution 1: DFS (Backtracking)

We define three arrays $col$, $dg$, and $udg$ to represent whether there is a queen in the column, the main diagonal, and the anti-diagonal, respectively. If there is a queen at position $(i, j)$, then $col[j]$, $dg[i + j]$, and $udg[n - i + j]$ are all $1$. In addition, we use an array $g$ to record the current state of the chessboard, where all elements in $g$ are initially `'.'`.

Next, we define a function $dfs(i)$, which represents placing queens starting from the $i$th row.

In $dfs(i)$, if $i = n$, it means that we have completed the placement of all queens. We put the current $g$ into the answer array and end the recursion.

Otherwise, we enumerate each column $j$ of the current row. If there is no queen at position $(i, j)$, that is, $col[j]$, $dg[i + j]$, and $udg[n - i + j]$ are all $0$, then we can place a queen, that is, change $g[i][j]$ to `'Q'`, and set $col[j]$, $dg[i + j]$, and $udg[n - i + j]$ to $1$. Then we continue to search the next row, that is, call $dfs(i + 1)$. After the recursion ends, we need to change $g[i][j]$ back to `'.'` and set $col[j]$, $dg[i + j]$, and $udg[n - i + j]$ to $0$.

In the main function, we call $dfs(0)$ to start recursion, and finally return the answer array.

The time complexity is $O(n^2 \times n!)$, and the space complexity is $O(n)$. Here, $n$ is the integer given in the problem.

<!-- tabs:start -->

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

```ts
function solveNQueens(n: number): string[][] {
    const col: number[] = Array(n).fill(0);
    const dg: number[] = Array(n << 1).fill(0);
    const udg: number[] = Array(n << 1).fill(0);
    const ans: string[][] = [];
    const t: string[][] = Array.from({ length: n }, () => Array(n).fill('.'));
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

```swift
class Solution {
    private var ans: [[String]] = []
    private var col: [Int] = Array(repeating: 0, count: 0)
    private var dg: [Int] = Array(repeating: 0, count: 0)
    private var udg: [Int] = Array(repeating: 0, count: 0)
    private var g: [[String]] = Array(repeating: Array(repeating: ".", count: 0), count: 0)
    private var n: Int = 0

    func solveNQueens(_ n: Int) -> [[String]] {
        self.n = n
        col = Array(repeating: 0, count: n)
        dg = Array(repeating: 0, count: n * 2)
        udg = Array(repeating: 0, count: n * 2)
        g = Array(repeating: Array(repeating: ".", count: n), count: n)
        dfs(0)
        return ans
    }

    private func dfs(_ i: Int) {
        guard i < n else {
            let t = g.map { $0.joined() }
            ans.append(t)
            return
        }
        for j in 0..<n {
            if col[j] + dg[i + j] + udg[n - i + j] == 0 {
                g[i][j] = "Q"
                col[j] = 1
                dg[i + j] = 1
                udg[n - i + j] = 1
                dfs(i + 1)
                col[j] = 0
                dg[i + j] = 0
                udg[n - i + j] = 0
                g[i][j] = "."
            }
        }
    }

}
```

<!-- tabs:end -->

<!-- end -->
