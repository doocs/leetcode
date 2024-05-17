---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1066.Campus%20Bikes%20II/README.md
rating: 1885
source: 第 1 场双周赛 Q3
tags:
    - 位运算
    - 数组
    - 动态规划
    - 回溯
    - 状态压缩
---

<!-- problem:start -->

# [1066. 校园自行车分配 II 🔒](https://leetcode.cn/problems/campus-bikes-ii)

[English Version](/solution/1000-1099/1066.Campus%20Bikes%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在由 2D 网格表示的校园里有&nbsp;<code>n</code>&nbsp;位工人（<code>worker</code>）和 <code>m</code>&nbsp;辆自行车（<code>bike</code>），<code>n &lt;= m</code>。所有工人和自行车的位置都用网格上的 2D 坐标表示。</p>

<p>我们为每一位工人分配一辆专属自行车，使每个工人与其分配到的自行车之间的 <strong>曼哈顿距离</strong> 最小化。</p>

<p>返回 <code>每个工人与分配到的自行车之间的曼哈顿距离的最小可能总和</code> 。</p>

<p><code>p1</code> 和&nbsp;<code>p2</code>&nbsp;之间的 <strong>曼哈顿距离</strong> 为&nbsp;<code>Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1066.Campus%20Bikes%20II/images/1261_example_1_v2.png" /></p>

<pre>
<strong>输入：</strong>workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
<strong>输出：</strong>6
<strong>解释：</strong>
自行车 0 分配给工人 0，自行车 1 分配给工人 1 。分配得到的曼哈顿距离都是 3, 所以输出为 6 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1066.Campus%20Bikes%20II/images/1261_example_2_v2.png" /></p>

<pre>
<strong>输入：</strong>workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
<strong>输出：</strong>4
<strong>解释：</strong>
先将自行车 0 分配给工人 0，再将自行车 1 分配给工人 1（或工人 2），自行车 2 给工人 2（或工人 1）。如此分配使得曼哈顿距离的总和为 4。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入：</strong>workers = [[0,0],[1,0],[2,0],[3,0],[4,0]], bikes = [[0,999],[1,999],[2,999],[3,999],[4,999]]
<strong>输出：</strong>4995
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == workers.length</code></li>
	<li><code>m == bikes.length</code></li>
	<li><code>1 &lt;= n &lt;= m &lt;= 10</code></li>
	<li><code>workers[i].length == 2</code></li>
	<li><code>bikes[i].length == 2</code></li>
	<li><code>0 &lt;= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] &lt; 1000</code></li>
	<li>所有的工人和自行车的位置都是 <strong>不同</strong>&nbsp;的。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：状态压缩动态规划

我们定义 $f[i][j]$ 表示前 $i$ 个工人分配到自行车的状态为 $j$ 时的最小曼哈顿距离总和，其中 $j$ 是一个二进制数，表示自行车的分配情况。初始时 $f[0][0]=0$，其余 $f[0][j]=+\infty$。

考虑 $f[i][j]$，我们枚举第 $i$ 个工人分配到的自行车的编号 $k$，那么 $f[i][j]$ 可以从 $f[i-1][j\oplus 2^k]$ 转移而来，其中 $\oplus$ 表示异或运算。这是因为 $f[i-1][j\oplus 2^k]$ 表示前 $i-1$ 个工人分配到自行车的状态为 $j\oplus 2^k$ 时的最小曼哈顿距离总和，而第 $i$ 个工人分配到自行车 $k$ 时，其曼哈顿距离为 $|worker[i]-bike[k]|$，其中 $|x|$ 表示 $x$ 的绝对值。因此我们可以列出状态转移方程：

$$
f[i][j]=\min_{k=0}^{m-1}\{f[i-1][j\oplus 2^k]+|worker[i]-bike[k]|\}
$$

最终的答案为 $\min_{j=0}^{2^m-1}\{f[n][j]\}$。

时间复杂度 $O(n \times 2^m \times m)$，空间复杂度 $O(n \times 2^m)$。其中 $n$ 和 $m$ 分别是工人和自行车的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> int:
        n, m = len(workers), len(bikes)
        f = [[inf] * (1 << m) for _ in range(n + 1)]
        f[0][0] = 0
        for i, (x1, y1) in enumerate(workers, 1):
            for j in range(1 << m):
                for k, (x2, y2) in enumerate(bikes):
                    if j >> k & 1:
                        f[i][j] = min(
                            f[i][j],
                            f[i - 1][j ^ (1 << k)] + abs(x1 - x2) + abs(y1 - y2),
                        )
        return min(f[n])
```

#### Java

```java
class Solution {
    public int assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        int[][] f = new int[n + 1][1 << m];
        for (var g : f) {
            Arrays.fill(g, 1 << 30);
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 1 << m; ++j) {
                for (int k = 0; k < m; ++k) {
                    if ((j >> k & 1) == 1) {
                        int d = Math.abs(workers[i - 1][0] - bikes[k][0])
                            + Math.abs(workers[i - 1][1] - bikes[k][1]);
                        f[i][j] = Math.min(f[i][j], f[i - 1][j ^ (1 << k)] + d);
                    }
                }
            }
        }
        return Arrays.stream(f[n]).min().getAsInt();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
        int n = workers.size(), m = bikes.size();
        int f[n + 1][1 << m];
        memset(f, 0x3f, sizeof(f));
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 1 << m; ++j) {
                for (int k = 0; k < m; ++k) {
                    if (j >> k & 1) {
                        int d = abs(workers[i - 1][0] - bikes[k][0]) + abs(workers[i - 1][1] - bikes[k][1]);
                        f[i][j] = min(f[i][j], f[i - 1][j ^ (1 << k)] + d);
                    }
                }
            }
        }
        return *min_element(f[n], f[n] + (1 << m));
    }
};
```

#### Go

```go
func assignBikes(workers [][]int, bikes [][]int) int {
	n, m := len(workers), len(bikes)
	f := make([][]int, n+1)
	const inf = 1 << 30
	for i := range f {
		f[i] = make([]int, 1<<m)
		for j := range f[i] {
			f[i][j] = inf
		}
	}
	f[0][0] = 0
	for i := 1; i <= n; i++ {
		for j := 0; j < 1<<m; j++ {
			for k := 0; k < m; k++ {
				if j>>k&1 == 1 {
					d := abs(workers[i-1][0]-bikes[k][0]) + abs(workers[i-1][1]-bikes[k][1])
					f[i][j] = min(f[i][j], f[i-1][j^(1<<k)]+d)
				}
			}
		}
	}
	return slices.Min(f[n])
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function assignBikes(workers: number[][], bikes: number[][]): number {
    const n = workers.length;
    const m = bikes.length;
    const inf = 1 << 30;
    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(1 << m).fill(inf));
    f[0][0] = 0;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j < 1 << m; ++j) {
            for (let k = 0; k < m; ++k) {
                if (((j >> k) & 1) === 1) {
                    const d =
                        Math.abs(workers[i - 1][0] - bikes[k][0]) +
                        Math.abs(workers[i - 1][1] - bikes[k][1]);
                    f[i][j] = Math.min(f[i][j], f[i - 1][j ^ (1 << k)] + d);
                }
            }
        }
    }
    return Math.min(...f[n]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
