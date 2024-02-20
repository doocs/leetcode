# [2664. 巡逻的骑士](https://leetcode.cn/problems/the-knights-tour)

[English Version](/solution/2600-2699/2664.The%20Knight%E2%80%99s%20Tour/README_EN.md)

<!-- tags:数组,回溯,矩阵 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个正整数 <code>m</code> 和 <code>n</code>&nbsp;，它们是一个 <strong>下标从 0 开始</strong> 的二维数组 <code>board</code> 的高度和宽度。还有一对正整数 <code>(r, c)</code> ，它们是骑士在棋盘上的起始位置。</p>

<p>你的任务是找到一个骑士的移动顺序，使得&nbsp;<code>board</code>&nbsp;中每个单元格都 <strong>恰好</strong> 被访问一次（起始单元格已被访问，<strong>不应</strong> 再次访问）。</p>

<p>返回数组 <code>board</code> ，其中单元格的值显示从 0 开始访问该单元格的顺序（骑士的初始位置为 0）。</p>

<p>注意，如果 <code>0 &lt;= r2 &lt;= m-1 且 0 &lt;= c2 &lt;= n-1</code>&nbsp;，并且 <code>min(abs(r1-r2), abs(c1-c2)) = 1</code> 且 <code>max(abs(r1-r2), abs(c1-c2)) = 2</code>&nbsp;，则骑士可以从单元格 <code>(r1, c1)</code> 移动到单元格 <code>(r2, c2)</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1 ：</strong></p>

<pre>
<b>输入：</b>m = 1, n = 1, r = 0, c = 0
<b>输出：</b>[[0]]
<b>解释</b>只有一个单元格，骑士最初在其中，因此 1x1 网格中只有一个 0。
</pre>

<p><strong>示例 2 ：</strong></p>

<pre>
<strong>输入：</strong>m = 3, n = 4, r = 0, c = 0
<b>输出：</b>[[0,3,6,9],[11,8,1,4],[2,5,10,7]]
<b>解释：</b>按照以下移动顺序，我们可以访问整个棋盘。 
(0,0)-&gt;(1,2)-&gt;(2,0)-&gt;(0,1)-&gt;(1,3)-&gt;(2,1)-&gt;(0,2)-&gt;(2,3)-&gt;(1,1)-&gt;(0,3)-&gt;(2,2)-&gt;(1,0)</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= m,&nbsp;n &lt;= 5</code></li>
	<li><code>0 &lt;= r &lt;= m - 1</code></li>
	<li><code>0 &lt;= c &lt;= n - 1</code></li>
	<li>输入的数据保证在给定条件下至少存在一种访问所有单元格的移动顺序。</li>
</ul>

## 解法

### 方法一：回溯

我们创建一个二维数组 $g$，用于记录骑士的移动顺序，初始时 $g[r][c] = -1$，其余位置均为 $-1$。另外，我们还需要一个变量 $ok$，用于记录是否找到了一种方案。

接下来，我们从 $(r, c)$ 开始进行深度优先搜索，每次搜索位置 $(i, j)$ 时，我们先判断 $g[i][j]$ 是否等于 $m \times n - 1$，若是，说明我们已经找到了一种方案，此时将 $ok$ 置为 `true` 并且返回。否则我们枚举骑士的八个移动方向对应的位置 $(x, y)$，若满足 $0 \leq x \lt m$, $0 \leq y \lt n$，并且 $g[x][y]=-1$，那么我们将 $g[x][y]$ 更新为 $g[i][j]+1$，然后递归搜索位置 $(x, y)$。如果搜索结束后，变量 $ok$ 为 `true`，则直接返回。否则，我们将 $g[x][y]$ 重置为 $-1$，继续搜索其他方向。

最后返回二维数组 $g$ 即可。

时间复杂度 $O(8^{m \times n})$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 为题目给定的整数。

<!-- tabs:start -->

```python
class Solution:
    def tourOfKnight(self, m: int, n: int, r: int, c: int) -> List[List[int]]:
        def dfs(i: int, j: int):
            nonlocal ok
            if g[i][j] == m * n - 1:
                ok = True
                return
            for a, b in pairwise((-2, -1, 2, 1, -2, 1, 2, -1, -2)):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and g[x][y] == -1:
                    g[x][y] = g[i][j] + 1
                    dfs(x, y)
                    if ok:
                        return
                    g[x][y] = -1

        g = [[-1] * n for _ in range(m)]
        g[r][c] = 0
        ok = False
        dfs(r, c)
        return g
```

```java
class Solution {
    private int[][] g;
    private int m;
    private int n;
    private boolean ok;

    public int[][] tourOfKnight(int m, int n, int r, int c) {
        this.m = m;
        this.n = n;
        this.g = new int[m][n];
        for (var row : g) {
            Arrays.fill(row, -1);
        }
        g[r][c] = 0;
        dfs(r, c);
        return g;
    }

    private void dfs(int i, int j) {
        if (g[i][j] == m * n - 1) {
            ok = true;
            return;
        }
        int[] dirs = {-2, -1, 2, 1, -2, 1, 2, -1, -2};
        for (int k = 0; k < 8; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == -1) {
                g[x][y] = g[i][j] + 1;
                dfs(x, y);
                if (ok) {
                    return;
                }
                g[x][y] = -1;
            }
        }
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> tourOfKnight(int m, int n, int r, int c) {
        vector<vector<int>> g(m, vector<int>(n, -1));
        g[r][c] = 0;
        int dirs[9] = {-2, -1, 2, 1, -2, 1, 2, -1, -2};
        bool ok = false;
        function<void(int, int)> dfs = [&](int i, int j) {
            if (g[i][j] == m * n - 1) {
                ok = true;
                return;
            }
            for (int k = 0; k < 8; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == -1) {
                    g[x][y] = g[i][j] + 1;
                    dfs(x, y);
                    if (ok) {
                        return;
                    }
                    g[x][y] = -1;
                }
            }
        };
        dfs(r, c);
        return g;
    }
};
```

```go
func tourOfKnight(m int, n int, r int, c int) [][]int {
	g := make([][]int, m)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = -1
		}
	}
	g[r][c] = 0
	ok := false
	var dfs func(i, j int)
	dfs = func(i, j int) {
		if g[i][j] == m*n-1 {
			ok = true
			return
		}
		dirs := []int{-2, -1, 2, 1, -2, 1, 2, -1, -2}
		for k := 0; k < 8; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && g[x][y] == -1 {
				g[x][y] = g[i][j] + 1
				dfs(x, y)
				if ok {
					return
				}
				g[x][y] = -1
			}
		}
	}
	dfs(r, c)
	return g
}
```

```ts
function tourOfKnight(m: number, n: number, r: number, c: number): number[][] {
    const g: number[][] = Array.from({ length: m }, () => Array(n).fill(-1));
    const dirs = [-2, -1, 2, 1, -2, 1, 2, -1, -2];
    let ok = false;
    const dfs = (i: number, j: number) => {
        if (g[i][j] === m * n - 1) {
            ok = true;
            return;
        }
        for (let k = 0; k < 8; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] === -1) {
                g[x][y] = g[i][j] + 1;
                dfs(x, y);
                if (ok) {
                    return;
                }
                g[x][y] = -1;
            }
        }
    };
    g[r][c] = 0;
    dfs(r, c);
    return g;
}
```

```rust
impl Solution {
    pub fn tour_of_knight(m: i32, n: i32, r: i32, c: i32) -> Vec<Vec<i32>> {
        let mut g: Vec<Vec<i32>> = vec![vec![-1; n as usize]; m as usize];
        g[r as usize][c as usize] = 0;
        let dirs: [i32; 9] = [-2, -1, 2, 1, -2, 1, 2, -1, -2];
        let mut ok = false;

        fn dfs(
            i: usize,
            j: usize,
            g: &mut Vec<Vec<i32>>,
            m: i32,
            n: i32,
            dirs: &[i32; 9],
            ok: &mut bool
        ) {
            if g[i][j] == m * n - 1 {
                *ok = true;
                return;
            }
            for k in 0..8 {
                let x = ((i as i32) + dirs[k]) as usize;
                let y = ((j as i32) + dirs[k + 1]) as usize;
                if x < (m as usize) && y < (n as usize) && g[x][y] == -1 {
                    g[x][y] = g[i][j] + 1;
                    dfs(x, y, g, m, n, dirs, ok);
                    if *ok {
                        return;
                    }
                    g[x][y] = -1;
                }
            }
        }

        dfs(r as usize, c as usize, &mut g, m, n, &dirs, &mut ok);
        g
    }
}
```

<!-- tabs:end -->

<!-- end -->
