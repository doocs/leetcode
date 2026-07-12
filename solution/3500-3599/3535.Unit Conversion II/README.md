---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3535.Unit%20Conversion%20II/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 数组
    - 数学
---

<!-- problem:start -->

# [3535. 单位转换 II 🔒](https://leetcode.cn/problems/unit-conversion-ii)

[English Version](/solution/3500-3599/3535.Unit%20Conversion%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有 <code>n</code> 种单位，编号从 <code>0</code> 到 <code>n - 1</code>。</p>

<p>给定一个二维整数数组 <code>conversions</code>，长度为 <code>n - 1</code>，其中 <code>conversions[i] = [sourceUnit<sub>i</sub>, targetUnit<sub>i</sub>, conversionFactor<sub>i</sub>]</code>&nbsp;，表示一个&nbsp;<code>sourceUnit<sub>i</sub></code> 类型的单位等于 <code>conversionFactor<sub>i</sub></code> 个 <code>targetUnit<sub>i</sub></code> 类型的单位。</p>

<p>同时给定一个长度为&nbsp;<code>q</code>&nbsp;的 2 维整数数组&nbsp;<code>queries</code>，其中&nbsp;<code>queries[i] = [unitA<sub>i</sub>, unitB<sub>i</sub>]</code>。</p>

<p>返回一个长度为 <code>q</code>&nbsp;的数组&nbsp;<code face="monospace">answer</code>，其中&nbsp;<code>answer[i]</code>&nbsp;表示多少个&nbsp;<code>unitB<sub>i</sub></code>&nbsp;类型的单位等于 1 个&nbsp;<code>unitA<sub>i</sub></code>&nbsp;类型的单位，并且当&nbsp;<code>p</code>&nbsp;和&nbsp;<code>q</code>&nbsp;互质的时候可以表示为 <code>p/q</code>。以&nbsp;<code>pq<sup>-1</sup></code>&nbsp;返回每个&nbsp;<code>answer[i]</code>&nbsp;对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取模</strong>&nbsp;的值，其中&nbsp;<code>q<sup>-1</sup></code>&nbsp;表示&nbsp;<code>q</code> 模&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;的乘法逆元。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>conversions = [[0,1,2],[0,2,6]], queries = [[1,2],[1,0]]</span></p>

<p><span class="example-io"><b>输出：</b>[3,500000004]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>在第一次查询中，我们可以反向使用&nbsp;<code>conversions[0]</code>，然后使用&nbsp;<code>conversions[1]</code>&nbsp;将单位 1 转换为 3 个单位的类型 2。</li>
	<li>在第二次查询中，我们可以反向使用 <code>conversions[0]</code>&nbsp;将单位 1 转换为 1/2 个单位的类型 0。我们返回&nbsp;500000004 因为它是 2 的乘法逆元。</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3535.Unit%20Conversion%20II/images/example1.png" style="width: 500px; height: 500px;" /></div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>conversions = [[0,1,2],[0,2,6],[0,3,8],[2,4,2],[2,5,4],[3,6,3]], queries = [[1,2],[0,4],[6,5],[4,6],[6,1]]</span></p>

<p><span class="example-io"><b>输出：</b>[3,12,1,2,83333334]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>在第一次查询中，我们可以反向使用&nbsp;<code>conversions[0]</code>，然后使用&nbsp;<code>conversions[1]</code> 将单位 1 转换为 3 个单位的类型 2。</li>
	<li>在第二次查询中，我们可以使用 <code>conversions[1]</code>，然后使用&nbsp;<code>conversions[3]</code> 将单位 0 转换为&nbsp;12 个单位的类型 4。</li>
	<li>在第三次查询中，我们可以使用&nbsp;<code>conversions[5]</code>，反向使用&nbsp;<code>conversions[2]</code>，<code>conversions[1]</code>，然后使用&nbsp;<code>conversions[4]</code> 将单位 6 转换为 1 个单位的类型 5。</li>
	<li>在第四次查询中，我们可以反向使用&nbsp;<code>conversions[3]</code>，反向使用&nbsp;<code>conversions[1]</code>，<code>conversions[2]</code>，然后使用&nbsp;<code>conversions[5]</code>&nbsp;将单位 4 转换为 2 个单位的类型 6。</li>
	<li>在第五次查询中，我们可以反向使用&nbsp;<code>conversions[5]</code>，反向使用&nbsp;<code>conversions[2]</code>，然后使用&nbsp;<code>conversions[0]</code> 将单位 6 转换为 1/12 个单位的类型 1。我们返回&nbsp;83333334 因为它是 12 的乘法逆元。</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3535.Unit%20Conversion%20II/images/example2.png" style="width: 504px; height: 493px;" /></div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>conversions.length == n - 1</code></li>
	<li><code>0 &lt;= sourceUnit<sub>i</sub>, targetUnit<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= conversionFactor<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= q &lt;= 10<sup>5</sup></code></li>
	<li><code>queries.length == q</code></li>
	<li><code>0 &lt;= unitA<sub>i</sub>, unitB<sub>i</sub> &lt; n</code></li>
	<li>保证&nbsp;0 单位可以通过正向或反向转换的组合唯一地转换为任何其他单位。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS + 模逆元

由题意可知，转换关系构成一棵以 $0$ 为根的有向树。从根节点 $0$ 出发 DFS，维护 `res[i]` 表示 $1$ 个单位 $0$ 等于多少个单位 $i$。

对于查询 $(unitA, unitB)$，答案为 $\frac{res[unitB]}{res[unitA]}$，对 $10^9 + 7$ 取模即 `res[unitB] * res[unitA]^(MOD - 2) % MOD`，其中 `MOD - 2` 利用费马小定理求模逆。

时间复杂度 $O(n + q \log MOD)$，空间复杂度 $O(n)$。其中 $n$ 为单位种类数，而 $q$ 为查询次数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def queryConversions(
        self, conversions: List[List[int]], queries: List[List[int]]
    ) -> List[int]:
        def dfs(s: int, mul: int) -> None:
            res[s] = mul
            for t, w in g[s]:
                dfs(t, mul * w % mod)

        mod = 10**9 + 7
        n = len(conversions) + 1
        g = [[] for _ in range(n)]
        for s, t, w in conversions:
            g[s].append((t, w))
        res = [0] * n
        dfs(0, 1)
        ans = []
        for x, y in queries:
            ans.append(res[y] * pow(res[x], mod - 2, mod) % mod)
        return ans
```

#### Java

```java
class Solution {
    private final int mod = (int) 1e9 + 7;
    private List<int[]>[] g;
    private int[] res;

    public int[] queryConversions(int[][] conversions, int[][] queries) {
        int n = conversions.length + 1;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : conversions) {
            g[e[0]].add(new int[] {e[1], e[2]});
        }

        res = new int[n];
        dfs(0, 1);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0], y = queries[i][1];
            ans[i] = (int) ((long) res[y] * qpow(res[x], mod - 2) % mod);
        }
        return ans;
    }

    private void dfs(int s, long mul) {
        res[s] = (int) mul;
        for (var e : g[s]) {
            dfs(e[0], mul * e[1] % mod);
        }
    }

    private long qpow(long x, int n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = res * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> queryConversions(vector<vector<int>>& conversions, vector<vector<int>>& queries) {
        const int mod = 1e9 + 7;
        int n = conversions.size() + 1;
        vector<vector<pair<int, int>>> g(n);
        for (auto& e : conversions) {
            g[e[0]].emplace_back(e[1], e[2]);
        }

        vector<int> res(n);

        auto dfs = [&](this auto&& dfs, int s, long long mul) -> void {
            res[s] = mul;
            for (auto [t, w] : g[s]) {
                dfs(t, mul * w % mod);
            }
        };
        dfs(0, 1);

        auto qpow = [&](long long x, int n) {
            long long res = 1;
            while (n) {
                if (n & 1) {
                    res = res * x % mod;
                }
                x = x * x % mod;
                n >>= 1;
            }
            return res;
        };

        vector<int> ans;
        for (auto& q : queries) {
            ans.push_back(res[q[1]] * qpow(res[q[0]], mod - 2) % mod);
        }
        return ans;
    }
};
```

#### Go

```go
func queryConversions(conversions [][]int, queries [][]int) []int {
	const mod = int(1e9 + 7)
	n := len(conversions) + 1

	g := make([][]struct{ t, w int }, n)
	for _, e := range conversions {
		s, t, w := e[0], e[1], e[2]
		g[s] = append(g[s], struct{ t, w int }{t, w})
	}

	res := make([]int, n)

	var dfs func(int, int)
	dfs = func(s, mul int) {
		res[s] = mul
		for _, e := range g[s] {
			dfs(e.t, mul*e.w%mod)
		}
	}
	dfs(0, 1)

	qpow := func(x, n int) int {
		res := 1
		for n > 0 {
			if n&1 > 0 {
				res = res * x % mod
			}
			x = x * x % mod
			n >>= 1
		}
		return res
	}

	ans := make([]int, len(queries))
	for i, q := range queries {
		ans[i] = res[q[1]] * qpow(res[q[0]], mod-2) % mod
	}
	return ans
}
```

#### TypeScript

```ts
function queryConversions(conversions: number[][], queries: number[][]): number[] {
    const mod = BigInt(1e9 + 7);
    const n = conversions.length + 1;

    const g: { t: number; w: number }[][] = Array.from({ length: n }, () => []);
    for (const [s, t, w] of conversions) {
        g[s].push({ t, w });
    }

    const res: number[] = Array(n).fill(0);

    const dfs = (s: number, mul: number): void => {
        res[s] = mul;
        for (const { t, w } of g[s]) {
            dfs(t, Number((BigInt(mul) * BigInt(w)) % mod));
        }
    };
    dfs(0, 1);

    const qpow = (x: number, n: number): number => {
        let res = 1n;
        let a = BigInt(x);
        while (n > 0) {
            if (n & 1) {
                res = (res * a) % mod;
            }
            a = (a * a) % mod;
            n >>= 1;
        }
        return Number(res);
    };

    const ans: number[] = [];
    for (const [x, y] of queries) {
        ans.push(Number((BigInt(res[y]) * BigInt(qpow(res[x], 1e9 + 5))) % mod));
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn query_conversions(conversions: Vec<Vec<i32>>, queries: Vec<Vec<i32>>) -> Vec<i32> {
        const MOD: i64 = 1_000_000_007;
        let n = conversions.len() + 1;

        let mut g = vec![Vec::<(usize, i64)>::new(); n];
        for e in conversions {
            g[e[0] as usize].push((e[1] as usize, e[2] as i64));
        }

        let mut res = vec![0_i64; n];

        fn dfs(s: usize, mul: i64, g: &Vec<Vec<(usize, i64)>>, res: &mut Vec<i64>) {
            res[s] = mul;
            for &(t, w) in &g[s] {
                dfs(t, mul * w % MOD, g, res);
            }
        }

        dfs(0, 1, &g, &mut res);

        fn qpow(mut x: i64, mut n: i32) -> i64 {
            let mut res = 1_i64;
            while n > 0 {
                if n & 1 == 1 {
                    res = res * x % MOD;
                }
                x = x * x % MOD;
                n >>= 1;
            }
            res
        }

        let mut ans = Vec::with_capacity(queries.len());
        for q in queries {
            let x = q[0] as usize;
            let y = q[1] as usize;
            ans.push((res[y] * qpow(res[x], 1_000_000_005) % MOD) as i32);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
