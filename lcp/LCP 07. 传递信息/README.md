---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2007.%20%E4%BC%A0%E9%80%92%E4%BF%A1%E6%81%AF/README.md
---

<!-- problem:start -->

# [LCP 07. 传递信息](https://leetcode.cn/problems/chuan-di-xin-xi)

## 题目描述

<!-- description:start -->

<p>小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：</p>

<ol>
	<li>有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0</li>
	<li>每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。</li>
	<li>每轮信息必须需要传递给另一个人，且信息可重复经过同一个人</li>
</ol>

<p>给定总玩家数 <code>n</code>，以及按 <code>[玩家编号,对应可传递玩家编号]</code> 关系组成的二维数组 <code>relation</code>。返回信息从小 A (编号 0 ) 经过 <code>k</code> 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。</p>

<p><strong>示例 1：</strong></p>

<blockquote>
<p>输入：<code>n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3</code></p>

<p>输出：<code>3</code></p>

<p>解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0-&gt;2-&gt;0-&gt;4， 0-&gt;2-&gt;1-&gt;4， 0-&gt;2-&gt;3-&gt;4。</p>
</blockquote>

<p><strong>示例 2：</strong></p>

<blockquote>
<p>输入：<code>n = 3, relation = [[0,2],[2,1]], k = 2</code></p>

<p>输出：<code>0</code></p>

<p>解释：信息不能从小 A 处经过 2 轮传递到编号 2</p>
</blockquote>

<p><strong>限制：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10</code></li>
	<li><code>1 &lt;= k &lt;= 5</code></li>
	<li><code>1 &lt;= relation.length &lt;= 90, 且 relation[i].length == 2</code></li>
	<li><code>0 &lt;= relation[i][0],relation[i][1] &lt; n 且 relation[i][0] != relation[i][1]</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示经过 $i$ 轮传递到编号 $j$ 的方案数，那么最终答案即为 $f[k][n-1]$。初始时 $f[0][0]=1$，其余均为 $0$。

当 $i \gt 0$ 时，对于每个玩家 $b$，考虑所有传递到他的玩家 $a$，有 $f[i][b]=\sum_{a \to b} f[i-1][a]$，其中 $a \to b$ 表示所有满足 $a$ 可以传递到 $b$ 的玩家 $a$。

最终答案即为 $f[k][n-1]$。

我们注意到 $f[i][b]$ 只与 $f[i-1][a]$ 有关，根据状态转移方程，我们可以使用滚动数组的方式，将空间复杂度优化到 $O(n)$。

时间复杂度 $O(k \times m)$，空间复杂度 $O(n)$，其中 $m$ 为 $relation$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def numWays(self, n: int, relation: List[List[int]], k: int) -> int:
        f = [[0] * n for _ in range(k + 1)]
        f[0][0] = 1
        for i in range(1, k + 1):
            for a, b in relation:
                f[i][b] += f[i - 1][a]
        return f[-1][-1]
```

```java
class Solution {
    public int numWays(int n, int[][] relation, int k) {
        int[][] f = new int[k + 1][n];
        f[0][0] = 1;
        for (int i = 1; i <= k; ++i) {
            for (int[] r : relation) {
                int a = r[0], b = r[1];
                f[i][b] += f[i - 1][a];
            }
        }
        return f[k][n - 1];
    }
}
```

```cpp
class Solution {
public:
    int numWays(int n, vector<vector<int>>& relation, int k) {
        int f[k + 1][n];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= k; ++i) {
            for (auto& r : relation) {
                int a = r[0], b = r[1];
                f[i][b] += f[i - 1][a];
            }
        }
        return f[k][n - 1];
    }
};
```

```go
func numWays(n int, relation [][]int, k int) int {
	f := make([][]int, k+1)
	for i := range f {
		f[i] = make([]int, n)
	}
	f[0][0] = 1
	for i := 1; i <= k; i++ {
		for _, r := range relation {
			a, b := r[0], r[1]
			f[i][b] += f[i-1][a]
		}
	}
	return f[k][n-1]
}
```

```ts
function numWays(n: number, relation: number[][], k: number): number {
    const f: number[][] = Array.from({ length: k + 1 }, () => Array(n).fill(0));
    f[0][0] = 1;
    for (let i = 1; i <= k; ++i) {
        for (const [a, b] of relation) {
            f[i][b] += f[i - 1][a];
        }
    }
    return f[k][n - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def numWays(self, n: int, relation: List[List[int]], k: int) -> int:
        f = [1] + [0] * (n - 1)
        for _ in range(k):
            g = [0] * n
            for a, b in relation:
                g[b] += f[a]
            f = g
        return f[-1]
```

```java
class Solution {
    public int numWays(int n, int[][] relation, int k) {
        int[] f = new int[n];
        f[0] = 1;
        while (k-- > 0) {
            int[] g = new int[n];
            for (int[] r : relation) {
                int a = r[0], b = r[1];
                g[b] += f[a];
            }
            f = g;
        }
        return f[n - 1];
    }
}
```

```cpp
class Solution {
public:
    int numWays(int n, vector<vector<int>>& relation, int k) {
        vector<int> f(n);
        f[0] = 1;
        while (k--) {
            vector<int> g(n);
            for (auto& r : relation) {
                int a = r[0], b = r[1];
                g[b] += f[a];
            }
            f = move(g);
        }
        return f[n - 1];
    }
};
```

```go
func numWays(n int, relation [][]int, k int) int {
	f := make([]int, n)
	f[0] = 1
	for ; k > 0; k-- {
		g := make([]int, n)
		for _, r := range relation {
			a, b := r[0], r[1]
			g[b] += f[a]
		}
		f = g
	}
	return f[n-1]
}
```

```ts
function numWays(n: number, relation: number[][], k: number): number {
    let f: number[] = new Array(n).fill(0);
    f[0] = 1;
    while (k--) {
        const g: number[] = new Array(n).fill(0);
        for (const [a, b] of relation) {
            g[b] += f[a];
        }
        f = g;
    }
    return f[n - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
