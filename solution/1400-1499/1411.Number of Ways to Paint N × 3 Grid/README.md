# [1411. 给 N x 3 网格图涂色的方案数](https://leetcode.cn/problems/number-of-ways-to-paint-n-3-grid)

[English Version](/solution/1400-1499/1411.Number%20of%20Ways%20to%20Paint%20N%20%C3%97%203%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有一个 <code>n x 3</code>&nbsp;的网格图 <code>grid</code>&nbsp;，你需要用 <strong>红，黄，绿</strong>&nbsp;三种颜色之一给每一个格子上色，且确保相邻格子颜色不同（也就是有相同水平边或者垂直边的格子颜色不同）。</p>

<p>给你网格图的行数 <code>n</code>&nbsp;。</p>

<p>请你返回给&nbsp;<code>grid</code>&nbsp;涂色的方案数。由于答案可能会非常大，请你返回答案对&nbsp;<code>10^9 + 7</code>&nbsp;取余的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 1
<strong>输出：</strong>12
<strong>解释：</strong>总共有 12 种可行的方法：
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1411.Number%20of%20Ways%20to%20Paint%20N%20%C3%97%203%20Grid/images/e1.png" style="height: 289px; width: 450px;">
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 2
<strong>输出：</strong>54
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 3
<strong>输出：</strong>246
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>n = 7
<strong>输出：</strong>106494
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>n = 5000
<strong>输出：</strong>30228214
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>grid[i].length == 3</code></li>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
</ul>

## 解法

### 方法一：递推

把每一行所有可能的状态进行分类。根据对称性原理，当一行只有 $3$ 个元素时，所有合法状态分类为 $010$ 型以及 $012$ 型。

-   当状态为 $010$ 型时：下一行可能的状态为 $101$, $102$, $121$, $201$, $202$。这 $5$ 个状态可归纳为 $3$ 个 $010$ 型，以及 $2$ 个 $012$ 型。
-   当状态为 $012$ 型时：下一行可能的状态为 $101$, $120$, $121$, $201$。这 $4$ 个状态可归纳为 $2$ 个 $010$ 型，以及 $2$ 个 $012$ 型。

综上所述，可以得到 $newf0 = 3 \times f0 + 2 \times f1$, $newf1 = 2 \times f0 + 2 \times f1$。

时间复杂度 $O(n)$，其中 $n$ 是网格的行数。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def numOfWays(self, n: int) -> int:
        mod = 10**9 + 7
        f0 = f1 = 6
        for _ in range(n - 1):
            g0 = (3 * f0 + 2 * f1) % mod
            g1 = (2 * f0 + 2 * f1) % mod
            f0, f1 = g0, g1
        return (f0 + f1) % mod
```

```java
class Solution {
    public int numOfWays(int n) {
        int mod = (int) 1e9 + 7;
        long f0 = 6, f1 = 6;
        for (int i = 0; i < n - 1; ++i) {
            long g0 = (3 * f0 + 2 * f1) % mod;
            long g1 = (2 * f0 + 2 * f1) % mod;
            f0 = g0;
            f1 = g1;
        }
        return (int) (f0 + f1) % mod;
    }
}
```

```cpp
using ll = long long;

class Solution {
public:
    int numOfWays(int n) {
        int mod = 1e9 + 7;
        ll f0 = 6, f1 = 6;
        while (--n) {
            ll g0 = (f0 * 3 + f1 * 2) % mod;
            ll g1 = (f0 * 2 + f1 * 2) % mod;
            f0 = g0;
            f1 = g1;
        }
        return (int) (f0 + f1) % mod;
    }
};
```

```go
func numOfWays(n int) int {
	mod := int(1e9) + 7
	f0, f1 := 6, 6
	for n > 1 {
		n--
		g0 := (f0*3 + f1*2) % mod
		g1 := (f0*2 + f1*2) % mod
		f0, f1 = g0, g1
	}
	return (f0 + f1) % mod
}
```

```ts
function numOfWays(n: number): number {
    const mod: number = 10 ** 9 + 7;
    let f0: number = 6;
    let f1: number = 6;

    for (let i = 1; i < n; i++) {
        const g0: number = (3 * f0 + 2 * f1) % mod;
        const g1: number = (2 * f0 + 2 * f1) % mod;
        f0 = g0;
        f1 = g1;
    }

    return (f0 + f1) % mod;
}
```

<!-- tabs:end -->

### 方法二：状态压缩 + 动态规划

我们注意到，网格只有 $3$ 列，那么一行中最多有 $3^3=27$ 种不同的涂色方案。

因此，我们定义 $f[i][j]$ 表示前 $i$ 行中，第 $i$ 行的涂色状态为 $j$ 的方案数。状态 $f[i][j]$ 由 $f[i - 1][k]$ 转移而来，其中 $k$ 是第 $i - 1$ 行的涂色状态，且 $k$ 和 $j$ 满足不同颜色相邻的要求。即：

$$
f[i][j] = \sum_{k \in \text{valid}(j)} f[i - 1][k]
$$

其中 $\text{valid}(j)$ 表示状态 $j$ 的所有合法前驱状态。

最终的答案即为 $f[n][j]$ 的总和，其中 $j$ 是任意合法的状态。

我们注意到 $f[i][j]$ 只和 $f[i - 1][k]$ 有关，因此我们可以使用滚动数组优化空间复杂度。

时间复杂度 $O((m + n) \times 3^{2m})$，空间复杂度 $O(3^m)$。其中 $n$ 和 $m$ 分别是网格的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def numOfWays(self, n: int) -> int:
        def f1(x: int) -> bool:
            last = -1
            for _ in range(3):
                if x % 3 == last:
                    return False
                last = x % 3
                x //= 3
            return True

        def f2(x: int, y: int) -> bool:
            for _ in range(3):
                if x % 3 == y % 3:
                    return False
                x //= 3
                y //= 3
            return True

        mod = 10**9 + 7
        m = 27
        valid = {i for i in range(m) if f1(i)}
        d = defaultdict(list)
        for i in valid:
            for j in valid:
                if f2(i, j):
                    d[i].append(j)
        f = [int(i in valid) for i in range(m)]
        for _ in range(n - 1):
            g = [0] * m
            for i in valid:
                for j in d[i]:
                    g[j] = (g[j] + f[i]) % mod
            f = g
        return sum(f) % mod
```

```java
class Solution {
    public int numOfWays(int n) {
        final int mod = (int) 1e9 + 7;
        int m = 27;
        Set<Integer> valid = new HashSet<>();
        int[] f = new int[m];
        for (int i = 0; i < m; ++i) {
            if (f1(i)) {
                valid.add(i);
                f[i] = 1;
            }
        }
        Map<Integer, List<Integer>> d = new HashMap<>();
        for (int i : valid) {
            for (int j : valid) {
                if (f2(i, j)) {
                    d.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                }
            }
        }
        for (int k = 1; k < n; ++k) {
            int[] g = new int[m];
            for (int i : valid) {
                for (int j : d.getOrDefault(i, List.of())) {
                    g[j] = (g[j] + f[i]) % mod;
                }
            }
            f = g;
        }
        int ans = 0;
        for (int x : f) {
            ans = (ans + x) % mod;
        }
        return ans;
    }

    private boolean f1(int x) {
        int last = -1;
        for (int i = 0; i < 3; ++i) {
            if (x % 3 == last) {
                return false;
            }
            last = x % 3;
            x /= 3;
        }
        return true;
    }

    private boolean f2(int x, int y) {
        for (int i = 0; i < 3; ++i) {
            if (x % 3 == y % 3) {
                return false;
            }
            x /= 3;
            y /= 3;
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    int numOfWays(int n) {
        int m = 27;

        auto f1 = [&](int x) {
            int last = -1;
            for (int i = 0; i < 3; ++i) {
                if (x % 3 == last) {
                    return false;
                }
                last = x % 3;
                x /= 3;
            }
            return true;
        };
        auto f2 = [&](int x, int y) {
            for (int i = 0; i < 3; ++i) {
                if (x % 3 == y % 3) {
                    return false;
                }
                x /= 3;
                y /= 3;
            }
            return true;
        };

        const int mod = 1e9 + 7;
        unordered_set<int> valid;
        vector<int> f(m);
        for (int i = 0; i < m; ++i) {
            if (f1(i)) {
                valid.insert(i);
                f[i] = 1;
            }
        }
        unordered_map<int, vector<int>> d;
        for (int i : valid) {
            for (int j : valid) {
                if (f2(i, j)) {
                    d[i].push_back(j);
                }
            }
        }
        for (int k = 1; k < n; ++k) {
            vector<int> g(m);
            for (int i : valid) {
                for (int j : d[i]) {
                    g[j] = (g[j] + f[i]) % mod;
                }
            }
            f = move(g);
        }
        int ans = 0;
        for (int x : f) {
            ans = (ans + x) % mod;
        }
        return ans;
    }
};
```

```go
func numOfWays(n int) (ans int) {
	f1 := func(x int) bool {
		last := -1
		for i := 0; i < 3; i++ {
			if x%3 == last {
				return false
			}
			last = x % 3
			x /= 3
		}
		return true
	}
	f2 := func(x, y int) bool {
		for i := 0; i < 3; i++ {
			if x%3 == y%3 {
				return false
			}
			x /= 3
			y /= 3
		}
		return true
	}
	m := 27
	valid := map[int]bool{}
	f := make([]int, m)
	for i := 0; i < m; i++ {
		if f1(i) {
			valid[i] = true
			f[i] = 1
		}
	}
	d := map[int][]int{}
	for i := range valid {
		for j := range valid {
			if f2(i, j) {
				d[i] = append(d[i], j)
			}
		}
	}
	const mod int = 1e9 + 7
	for k := 1; k < n; k++ {
		g := make([]int, m)
		for i := range valid {
			for _, j := range d[i] {
				g[i] = (g[i] + f[j]) % mod
			}
		}
		f = g
	}
	for _, x := range f {
		ans = (ans + x) % mod
	}
	return
}
```

```ts
function numOfWays(n: number): number {
    const f1 = (x: number): boolean => {
        let last = -1;
        for (let i = 0; i < 3; ++i) {
            if (x % 3 === last) {
                return false;
            }
            last = x % 3;
            x = Math.floor(x / 3);
        }
        return true;
    };
    const f2 = (x: number, y: number): boolean => {
        for (let i = 0; i < 3; ++i) {
            if (x % 3 === y % 3) {
                return false;
            }
            x = Math.floor(x / 3);
            y = Math.floor(y / 3);
        }
        return true;
    };
    const m = 27;
    const valid = new Set<number>();
    const f: number[] = Array(m).fill(0);
    for (let i = 0; i < m; ++i) {
        if (f1(i)) {
            valid.add(i);
            f[i] = 1;
        }
    }
    const d: Map<number, number[]> = new Map();
    for (const i of valid) {
        for (const j of valid) {
            if (f2(i, j)) {
                d.set(i, (d.get(i) || []).concat(j));
            }
        }
    }
    const mod = 10 ** 9 + 7;
    for (let k = 1; k < n; ++k) {
        const g: number[] = Array(m).fill(0);
        for (const i of valid) {
            for (const j of d.get(i) || []) {
                g[i] = (g[i] + f[j]) % mod;
            }
        }
        f.splice(0, f.length, ...g);
    }
    let ans = 0;
    for (const x of f) {
        ans = (ans + x) % mod;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
