---
comments: true
difficulty: 中等
rating: 1845
source: 第 196 场周赛 Q3
tags:
    - 栈
    - 数组
    - 动态规划
    - 矩阵
    - 单调栈
---

<!-- problem:start -->

# [1504. 统计全 1 子矩形](https://leetcode.cn/problems/count-submatrices-with-all-ones)

[English Version](/solution/1500-1599/1504.Count%20Submatrices%20With%20All%20Ones/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的二进制矩阵&nbsp;<code>mat</code>&nbsp;，请你返回有多少个&nbsp;<strong>子矩形</strong>&nbsp;的元素全部都是 1 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1504.Count%20Submatrices%20With%20All%20Ones/images/ones1-grid.jpg" /></p>

<pre>
<strong>输入：</strong>mat = [[1,0,1],[1,1,0],[1,1,0]]
<strong>输出：</strong>13
<strong>解释：
</strong>有 <strong>6</strong>&nbsp;个 1x1 的矩形。
有 <strong>2</strong> 个 1x2 的矩形。
有 <strong>3</strong> 个 2x1 的矩形。
有 <strong>1</strong> 个 2x2 的矩形。
有 <strong>1</strong> 个 3x1 的矩形。
矩形数目总共 = 6 + 2 + 3 + 1 + 1 = <strong>13</strong>&nbsp;。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1504.Count%20Submatrices%20With%20All%20Ones/images/ones2-grid.jpg" /></p>

<pre>
<strong>输入：</strong>mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
<strong>输出：</strong>24
<strong>解释：</strong>
有 <strong>8</strong> 个 1x1 的子矩形。
有 <strong>5</strong> 个 1x2 的子矩形。
有 <strong>2</strong> 个 1x3 的子矩形。
有 <strong>4</strong> 个 2x1 的子矩形。
有 <strong>2</strong> 个 2x2 的子矩形。
有 <strong>2</strong> 个 3x1 的子矩形。
有 <strong>1</strong> 个 3x2 的子矩形。
矩形数目总共 = 8 + 5 + 2 + 4 + 2 + 2 + 1 = <strong>24</strong><strong> 。</strong>

</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 150</code></li>
	<li><code>mat[i][j]</code>&nbsp;仅包含&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举 + 前缀和

<!-- thinking:start -->

> **思考**
>
> 统计全 $1$ 子矩阵的个数，若枚举四个边界再检查内部，时间可达 $O(m^2 n^2\cdot mn)$。即便改为枚举右下角与左上角后用二维前缀和判断，仍是 $O(m^2 n^2)$。矩阵边长可达 $150$，需要再降一维。
>
> 固定右下角 $(i,j)$ 后，子矩阵由向上延伸的高度决定。先对每一行预处理「以该格为右端的连续 $1$ 宽度」 $g[i][j]$，再从 $i$ 向上枚举上边界 $k$，宽度取沿途 $g[k][j]$ 的最小值，每升高一行就累加当前宽度。这样每个右下角用 $O(m)$ 完成统计，总时间 $O(m^2 n)$。

<!-- thinking:end -->

我们可以枚举矩阵的右下角 $(i, j)$，然后向上枚举矩阵的第一行 $k$，那么每一行以 $(i, j)$ 为右下角的矩阵的宽度就是 $\min_{k \leq i} \textit{g}[k][j]$，其中 $\textit{g}[k][j]$ 表示第 $k$ 行以 $(k, j)$ 为右下角的矩阵的宽度。

因此，我们可以预处理得到二维数组 $g[i][j]$，其中 $g[i][j]$ 表示第 $i$ 行中，从第 $j$ 列向左连续的 $1$ 的个数。

时间复杂度 $O(m^2 \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numSubmat(self, mat: List[List[int]]) -> int:
        m, n = len(mat), len(mat[0])
        g = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                if mat[i][j]:
                    g[i][j] = 1 if j == 0 else 1 + g[i][j - 1]
        ans = 0
        for i in range(m):
            for j in range(n):
                col = inf
                for k in range(i, -1, -1):
                    col = min(col, g[k][j])
                    ans += col
        return ans
```

#### Java

```java
class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] g = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1) {
                    g[i][j] = j == 0 ? 1 : 1 + g[i][j - 1];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int col = 1 << 30;
                for (int k = i; k >= 0 && col > 0; --k) {
                    col = Math.min(col, g[k][j]);
                    ans += col;
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numSubmat(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> g(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1) {
                    g[i][j] = j == 0 ? 1 : 1 + g[i][j - 1];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int col = 1 << 30;
                for (int k = i; k >= 0 && col > 0; --k) {
                    col = min(col, g[k][j]);
                    ans += col;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numSubmat(mat [][]int) (ans int) {
	m, n := len(mat), len(mat[0])
	g := make([][]int, m)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			if mat[i][j] == 1 {
				if j == 0 {
					g[i][j] = 1
				} else {
					g[i][j] = 1 + g[i][j-1]
				}
			}
		}
	}
	for i := range g {
		for j := range g[i] {
			col := 1 << 30
			for k := i; k >= 0 && col > 0; k-- {
				col = min(col, g[k][j])
				ans += col
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function numSubmat(mat: number[][]): number {
    const m = mat.length;
    const n = mat[0].length;
    const g: number[][] = Array.from({ length: m }, () => Array(n).fill(0));

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (mat[i][j]) {
                g[i][j] = j === 0 ? 1 : 1 + g[i][j - 1];
            }
        }
    }

    let ans = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            let col = Infinity;
            for (let k = i; k >= 0; k--) {
                col = Math.min(col, g[k][j]);
                ans += col;
            }
        }
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn num_submat(mat: Vec<Vec<i32>>) -> i32 {
        let m = mat.len();
        let n = mat[0].len();
        let mut g = vec![vec![0; n]; m];

        for i in 0..m {
            for j in 0..n {
                if mat[i][j] == 1 {
                    if j == 0 {
                        g[i][j] = 1;
                    } else {
                        g[i][j] = 1 + g[i][j - 1];
                    }
                }
            }
        }

        let mut ans = 0;
        for i in 0..m {
            for j in 0..n {
                let mut col = i32::MAX;
                let mut k = i as i32;
                while k >= 0 && col > 0 {
                    col = col.min(g[k as usize][j]);
                    ans += col;
                    k -= 1;
                }
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} mat
 * @return {number}
 */
var numSubmat = function (mat) {
    const m = mat.length;
    const n = mat[0].length;
    const g = Array.from({ length: m }, () => Array(n).fill(0));

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (mat[i][j]) {
                g[i][j] = j === 0 ? 1 : 1 + g[i][j - 1];
            }
        }
    }

    let ans = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            let col = Infinity;
            for (let k = i; k >= 0; k--) {
                col = Math.min(col, g[k][j]);
                ans += col;
            }
        }
    }

    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：单调栈

<!-- thinking:start -->

> **思考**
>
> 方法一里，每个右下角仍要从当前行向上扫描 $O(m)$ 行，再把沿途最小宽度累加进去，总时间是 $O(m^2 \times n)$。矩阵边长可达 $150$，这一层循环就是剩余的开销。
>
> 同一列上，一旦出现更窄的宽度，它上方每一行的贡献都会被这个宽度卡住，没有必要从当前行再逐行重扫。对每一列维护宽度严格递增的单调栈，栈顶就是上方最近的更窄位置：当前行到栈顶之间的最小宽度就是本行的 $g[i][j]$，栈顶及其上方的答案已经算好，直接沿用。每个格子至多入栈一次、出栈一次，向上的扫描就被摊成 $O(1)$。

<!-- thinking:end -->

我们仍先预处理二维数组 $g$，其中 $g[i][j]$ 表示第 $i$ 行从第 $j$ 列向左连续 $1$ 的个数。

然后按列统计。对于第 $j$ 列，从上到下扫描每一行 $i$，并用单调栈保存三元组 $(w, r, c)$：宽度 $w$、所在行 $r$，以及以 $(r, j)$ 为右下角的全 $1$ 子矩阵个数 $c$。栈中的宽度保持严格递增。

处理 $g[i][j]$ 时，弹出所有宽度大于等于当前值的栈顶。若栈为空，则从第 $0$ 行到第 $i$ 行的最小宽度都是 $g[i][j]$，以 $(i, j)$ 为右下角的子矩阵个数为 $g[i][j] \times (i + 1)$。否则设栈顶为 $(w, r, c)$，第 $r$ 行及以上的贡献已经记在 $c$ 中，第 $r + 1$ 行到第 $i$ 行的最小宽度都是 $g[i][j]$，因此个数为 $c + g[i][j] \times (i - r)$。将该结果累加到答案，并把当前宽度、行号与这个个数压入栈中。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numSubmat(self, mat: List[List[int]]) -> int:
        m, n = len(mat), len(mat[0])
        g = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                if mat[i][j]:
                    g[i][j] = 1 if j == 0 else 1 + g[i][j - 1]
        ans = 0
        for j in range(n):
            stk = []
            for i in range(m):
                cur = g[i][j]
                while stk and stk[-1][0] >= cur:
                    stk.pop()
                cnt = cur * (i + 1)
                if stk:
                    cnt = stk[-1][2] + cur * (i - stk[-1][1])
                ans += cnt
                stk.append((cur, i, cnt))
        return ans
```

#### Java

```java
class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] g = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1) {
                    g[i][j] = j == 0 ? 1 : 1 + g[i][j - 1];
                }
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            List<int[]> stk = new ArrayList<>();
            for (int i = 0; i < m; ++i) {
                int cur = g[i][j];
                while (!stk.isEmpty() && stk.get(stk.size() - 1)[0] >= cur) {
                    stk.remove(stk.size() - 1);
                }
                int cnt = cur * (i + 1);
                if (!stk.isEmpty()) {
                    int[] t = stk.get(stk.size() - 1);
                    cnt = t[2] + cur * (i - t[1]);
                }
                ans += cnt;
                stk.add(new int[] {cur, i, cnt});
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numSubmat(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> g(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1) {
                    g[i][j] = j == 0 ? 1 : 1 + g[i][j - 1];
                }
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            vector<array<int, 3>> stk;
            for (int i = 0; i < m; ++i) {
                int cur = g[i][j];
                while (!stk.empty() && stk.back()[0] >= cur) {
                    stk.pop_back();
                }
                int cnt = cur * (i + 1);
                if (!stk.empty()) {
                    cnt = stk.back()[2] + cur * (i - stk.back()[1]);
                }
                ans += cnt;
                stk.push_back({cur, i, cnt});
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numSubmat(mat [][]int) (ans int) {
	m, n := len(mat), len(mat[0])
	g := make([][]int, m)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			if mat[i][j] == 1 {
				if j == 0 {
					g[i][j] = 1
				} else {
					g[i][j] = 1 + g[i][j-1]
				}
			}
		}
	}
	for j := 0; j < n; j++ {
		stk := [][3]int{}
		for i := 0; i < m; i++ {
			cur := g[i][j]
			for len(stk) > 0 && stk[len(stk)-1][0] >= cur {
				stk = stk[:len(stk)-1]
			}
			cnt := cur * (i + 1)
			if len(stk) > 0 {
				cnt = stk[len(stk)-1][2] + cur*(i-stk[len(stk)-1][1])
			}
			ans += cnt
			stk = append(stk, [3]int{cur, i, cnt})
		}
	}
	return
}
```

#### TypeScript

```ts
function numSubmat(mat: number[][]): number {
    const m = mat.length;
    const n = mat[0].length;
    const g: number[][] = Array.from({ length: m }, () => Array(n).fill(0));
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (mat[i][j]) {
                g[i][j] = j === 0 ? 1 : 1 + g[i][j - 1];
            }
        }
    }
    let ans = 0;
    for (let j = 0; j < n; j++) {
        const stk: number[][] = [];
        for (let i = 0; i < m; i++) {
            const cur = g[i][j];
            while (stk.length > 0 && stk[stk.length - 1][0] >= cur) {
                stk.pop();
            }
            let cnt = cur * (i + 1);
            if (stk.length > 0) {
                const t = stk[stk.length - 1];
                cnt = t[2] + cur * (i - t[1]);
            }
            ans += cnt;
            stk.push([cur, i, cnt]);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn num_submat(mat: Vec<Vec<i32>>) -> i32 {
        let m = mat.len();
        let n = mat[0].len();
        let mut g = vec![vec![0; n]; m];
        for i in 0..m {
            for j in 0..n {
                if mat[i][j] == 1 {
                    g[i][j] = if j == 0 { 1 } else { 1 + g[i][j - 1] };
                }
            }
        }
        let mut ans = 0;
        for j in 0..n {
            let mut stk: Vec<(i32, i32, i32)> = Vec::new();
            for i in 0..m {
                let cur = g[i][j];
                while !stk.is_empty() && stk.last().unwrap().0 >= cur {
                    stk.pop();
                }
                let cnt = if stk.is_empty() {
                    cur * (i as i32 + 1)
                } else {
                    let t = stk.last().unwrap();
                    t.2 + cur * (i as i32 - t.1)
                };
                ans += cnt;
                stk.push((cur, i as i32, cnt));
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} mat
 * @return {number}
 */
var numSubmat = function (mat) {
    const m = mat.length;
    const n = mat[0].length;
    const g = Array.from({ length: m }, () => Array(n).fill(0));
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (mat[i][j]) {
                g[i][j] = j === 0 ? 1 : 1 + g[i][j - 1];
            }
        }
    }
    let ans = 0;
    for (let j = 0; j < n; j++) {
        const stk = [];
        for (let i = 0; i < m; i++) {
            const cur = g[i][j];
            while (stk.length > 0 && stk[stk.length - 1][0] >= cur) {
                stk.pop();
            }
            let cnt = cur * (i + 1);
            if (stk.length > 0) {
                const t = stk[stk.length - 1];
                cnt = t[2] + cur * (i - t[1]);
            }
            ans += cnt;
            stk.push([cur, i, cnt]);
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
