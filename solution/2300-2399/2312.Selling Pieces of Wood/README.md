---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2312.Selling%20Pieces%20of%20Wood/README.md
rating: 2363
tags:
    - 记忆化搜索
    - 数组
    - 动态规划
---

# [2312. 卖木头块](https://leetcode.cn/problems/selling-pieces-of-wood)

[English Version](/solution/2300-2399/2312.Selling%20Pieces%20of%20Wood/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数&nbsp;<code>m</code> 和&nbsp;<code>n</code>&nbsp;，分别表示一块矩形木块的高和宽。同时给你一个二维整数数组&nbsp;<code>prices</code>&nbsp;，其中&nbsp;<code>prices[i] = [h<sub>i</sub>, w<sub>i</sub>, price<sub>i</sub>]</code>&nbsp;表示你可以以&nbsp;<code>price<sub>i</sub></code>&nbsp;元的价格卖一块高为&nbsp;<code>h<sub>i</sub></code>&nbsp;宽为&nbsp;<code>w<sub>i</sub></code>&nbsp;的矩形木块。</p>

<p>每一次操作中，你必须按下述方式之一执行切割操作，以得到两块更小的矩形木块：</p>

<ul>
	<li>沿垂直方向按高度 <strong>完全</strong> 切割木块，或</li>
	<li>沿水平方向按宽度 <strong>完全</strong> 切割木块</li>
</ul>

<p>在将一块木块切成若干小木块后，你可以根据 <code>prices</code>&nbsp;卖木块。你可以卖多块同样尺寸的木块。你不需要将所有小木块都卖出去。你 <strong>不能</strong>&nbsp;旋转切好后木块来交换它的高度值和宽度值。</p>

<p>请你返回切割一块大小为<em>&nbsp;</em><code>m x n</code><em> </em>的木块后，能得到的&nbsp;<strong>最多</strong>&nbsp;钱数。</p>

<p>注意你可以切割木块任意次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2312.Selling%20Pieces%20of%20Wood/images/ex1.png" style="width: 239px; height: 150px;" /></p>

<pre>
<b>输入：</b>m = 3, n = 5, prices = [[1,4,2],[2,2,7],[2,1,3]]
<b>输出：</b>19
<b>解释：</b>上图展示了一个可行的方案。包括：
- 2 块 2 x 2 的小木块，售出 2 * 7 = 14 元。
- 1 块 2 x 1 的小木块，售出 1 * 3 = 3 元。
- 1 块 1 x 4 的小木块，售出 1 * 2 = 2 元。
总共售出 14 + 3 + 2 = 19 元。
19 元是最多能得到的钱数。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2312.Selling%20Pieces%20of%20Wood/images/ex2new.png" style="width: 250px; height: 175px;" /></p>

<pre>
<b>输入：</b>m = 4, n = 6, prices = [[3,2,10],[1,4,2],[4,1,3]]
<b>输出：</b>32
<b>解释：</b>上图展示了一个可行的方案。包括：
- 3 块 3 x 2 的小木块，售出 3 * 10 = 30 元。
- 1 块 1 x 4 的小木块，售出 1 * 2 = 2 元。
总共售出 30 + 2 = 32 元。
32 元是最多能得到的钱数。
注意我们不能旋转 1 x 4 的木块来得到 4 x 1 的木块。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>1 &lt;= prices.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>prices[i].length == 3</code></li>
	<li><code>1 &lt;= h<sub>i</sub> &lt;= m</code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= n</code></li>
	<li><code>1 &lt;= price<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li>所有&nbsp;<code>(h<sub>i</sub>, w<sub>i</sub>)</code> <strong>互不相同</strong>&nbsp;。</li>
</ul>

## 解法

### 方法一：记忆化搜索

我们先定义一个二维数组 $d$，其中 $d[i][j]$ 表示高为 $i$，宽为 $j$ 的木块的价格。初始时，我们遍历价格数组 $prices$，将每一块木块 $(h, w, p)$ 的价格 $p$ 存入 $d[h][w]$ 中，其余价格为 $0$。

然后我们设计一个函数 $dfs(h, w)$，表示对一块高为 $h$，宽为 $w$ 的木块切割后能得到的最多钱数。答案就是 $dfs(m, n)$。

函数 $dfs(h, w)$ 的执行过程如下：

-   如果 $(h, w)$ 已经被计算过了，直接返回答案。
-   否则，我们先初始化答案为 $d[h][w]$，然后枚举切割的位置，分别计算切割后的两块木块能得到的最多钱数，取最大值即可。

时间复杂度 $(m \times n \times (m + n) + p)$，空间复杂度 $O(m \times n)$。其中 $p$ 表示价格数组的长度，而 $m$ 和 $n$ 分别表示木块的高和宽。

<!-- tabs:start -->

```python
class Solution:
    def sellingWood(self, m: int, n: int, prices: List[List[int]]) -> int:
        @cache
        def dfs(h: int, w: int) -> int:
            ans = d[h].get(w, 0)
            for i in range(1, h // 2 + 1):
                ans = max(ans, dfs(i, w) + dfs(h - i, w))
            for i in range(1, w // 2 + 1):
                ans = max(ans, dfs(h, i) + dfs(h, w - i))
            return ans

        d = defaultdict(dict)
        for h, w, p in prices:
            d[h][w] = p
        return dfs(m, n)
```

```java
class Solution {
    private int[][] d;
    private Long[][] f;

    public long sellingWood(int m, int n, int[][] prices) {
        d = new int[m + 1][n + 1];
        f = new Long[m + 1][n + 1];
        for (var p : prices) {
            d[p[0]][p[1]] = p[2];
        }
        return dfs(m, n);
    }

    private long dfs(int h, int w) {
        if (f[h][w] != null) {
            return f[h][w];
        }
        long ans = d[h][w];
        for (int i = 1; i < h / 2 + 1; ++i) {
            ans = Math.max(ans, dfs(i, w) + dfs(h - i, w));
        }
        for (int i = 1; i < w / 2 + 1; ++i) {
            ans = Math.max(ans, dfs(h, i) + dfs(h, w - i));
        }
        return f[h][w] = ans;
    }
}
```

```cpp
class Solution {
public:
    long long sellingWood(int m, int n, vector<vector<int>>& prices) {
        using ll = long long;
        ll f[m + 1][n + 1];
        int d[m + 1][n + 1];
        memset(f, -1, sizeof(f));
        memset(d, 0, sizeof(d));
        for (auto& p : prices) {
            d[p[0]][p[1]] = p[2];
        }
        function<ll(int, int)> dfs = [&](int h, int w) -> ll {
            if (f[h][w] != -1) {
                return f[h][w];
            }
            ll ans = d[h][w];
            for (int i = 1; i < h / 2 + 1; ++i) {
                ans = max(ans, dfs(i, w) + dfs(h - i, w));
            }
            for (int i = 1; i < w / 2 + 1; ++i) {
                ans = max(ans, dfs(h, i) + dfs(h, w - i));
            }
            return f[h][w] = ans;
        };
        return dfs(m, n);
    }
};
```

```go
func sellingWood(m int, n int, prices [][]int) int64 {
	f := make([][]int64, m+1)
	d := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int64, n+1)
		for j := range f[i] {
			f[i][j] = -1
		}
		d[i] = make([]int, n+1)
	}
	for _, p := range prices {
		d[p[0]][p[1]] = p[2]
	}
	var dfs func(int, int) int64
	dfs = func(h, w int) int64 {
		if f[h][w] != -1 {
			return f[h][w]
		}
		ans := int64(d[h][w])
		for i := 1; i < h/2+1; i++ {
			ans = max(ans, dfs(i, w)+dfs(h-i, w))
		}
		for i := 1; i < w/2+1; i++ {
			ans = max(ans, dfs(h, i)+dfs(h, w-i))
		}
		f[h][w] = ans
		return ans
	}
	return dfs(m, n)
}
```

```ts
function sellingWood(m: number, n: number, prices: number[][]): number {
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(-1));
    const d: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (const [h, w, p] of prices) {
        d[h][w] = p;
    }

    const dfs = (h: number, w: number): number => {
        if (f[h][w] !== -1) {
            return f[h][w];
        }

        let ans = d[h][w];
        for (let i = 1; i <= Math.floor(h / 2); i++) {
            ans = Math.max(ans, dfs(i, w) + dfs(h - i, w));
        }
        for (let i = 1; i <= Math.floor(w / 2); i++) {
            ans = Math.max(ans, dfs(h, i) + dfs(h, w - i));
        }
        return (f[h][w] = ans);
    };

    return dfs(m, n);
}
```

<!-- tabs:end -->

### 方法二：动态规划

我们可以将方法一的记忆化搜索转换为动态规划。

与方法一类似，我们定义一个二维数组 $d$，其中 $d[i][j]$ 表示高为 $i$，宽为 $j$ 的木块的价格。初始时，我们遍历价格数组 $prices$，将每一块木块 $(h, w, p)$ 的价格 $p$ 存入 $d[h][w]$ 中，其余价格为 $0$。

然后，我们定义另一个二维数组 $f$，其中 $f[i][j]$ 表示对一块高为 $i$，宽为 $j$ 的木块切割后能得到的最多钱数。答案就是 $f[m][n]$。

考虑 $f[i][j]$ 如何转移，初始时 $f[i][j] = d[i][j]$。我们枚举切割的位置，分别计算切割后的两块木块能得到的最多钱数，取最大值即可。

时间复杂度 $O(m \times n \times (m + n) + p)$，空间复杂度 $O(m \times n)$。其中 $p$ 表示价格数组的长度，而 $m$ 和 $n$ 分别表示木块的高和宽。

相似题目：

-   [1444. 切披萨的方案数](https://github.com/doocs/leetcode/blob/main/solution/1400-1499/1444.Number%20of%20Ways%20of%20Cutting%20a%20Pizza/README.md)

<!-- tabs:start -->

```python
class Solution:
    def sellingWood(self, m: int, n: int, prices: List[List[int]]) -> int:
        d = defaultdict(dict)
        for h, w, p in prices:
            d[h][w] = p
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                f[i][j] = d[i].get(j, 0)
                for k in range(1, i):
                    f[i][j] = max(f[i][j], f[k][j] + f[i - k][j])
                for k in range(1, j):
                    f[i][j] = max(f[i][j], f[i][k] + f[i][j - k])
        return f[m][n]
```

```java
class Solution {
    public long sellingWood(int m, int n, int[][] prices) {
        int[][] d = new int[m + 1][n + 1];
        long[][] f = new long[m + 1][n + 1];
        for (int[] p : prices) {
            d[p[0]][p[1]] = p[2];
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i][j] = d[i][j];
                for (int k = 1; k < i; ++k) {
                    f[i][j] = Math.max(f[i][j], f[k][j] + f[i - k][j]);
                }
                for (int k = 1; k < j; ++k) {
                    f[i][j] = Math.max(f[i][j], f[i][k] + f[i][j - k]);
                }
            }
        }
        return f[m][n];
    }
}
```

```cpp
class Solution {
public:
    long long sellingWood(int m, int n, vector<vector<int>>& prices) {
        long long f[m + 1][n + 1];
        int d[m + 1][n + 1];
        memset(f, -1, sizeof(f));
        memset(d, 0, sizeof(d));
        for (auto& p : prices) {
            d[p[0]][p[1]] = p[2];
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i][j] = d[i][j];
                for (int k = 1; k < i; ++k) {
                    f[i][j] = max(f[i][j], f[k][j] + f[i - k][j]);
                }
                for (int k = 1; k < j; ++k) {
                    f[i][j] = max(f[i][j], f[i][k] + f[i][j - k]);
                }
            }
        }
        return f[m][n];
    }
};
```

```go
func sellingWood(m int, n int, prices [][]int) int64 {
	d := make([][]int, m+1)
	f := make([][]int64, m+1)
	for i := range d {
		d[i] = make([]int, n+1)
		f[i] = make([]int64, n+1)
	}
	for _, p := range prices {
		d[p[0]][p[1]] = p[2]
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			f[i][j] = int64(d[i][j])
			for k := 1; k < i; k++ {
				f[i][j] = max(f[i][j], f[k][j]+f[i-k][j])
			}
			for k := 1; k < j; k++ {
				f[i][j] = max(f[i][j], f[i][k]+f[i][j-k])
			}
		}
	}
	return f[m][n]
}
```

```ts
function sellingWood(m: number, n: number, prices: number[][]): number {
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    const d: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (const [h, w, p] of prices) {
        d[h][w] = p;
    }

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            f[i][j] = d[i][j];
            for (let k = 1; k < i; k++) {
                f[i][j] = Math.max(f[i][j], f[k][j] + f[i - k][j]);
            }
            for (let k = 1; k < j; k++) {
                f[i][j] = Math.max(f[i][j], f[i][k] + f[i][j - k]);
            }
        }
    }

    return f[m][n];
}
```

<!-- tabs:end -->

<!-- end -->
