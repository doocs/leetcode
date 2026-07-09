---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3535.Unit%20Conversion%20II/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Graph
    - Array
    - Math
---

<!-- problem:start -->

# [3535. Unit Conversion II 🔒](https://leetcode.com/problems/unit-conversion-ii)

[中文文档](/solution/3500-3599/3535.Unit%20Conversion%20II/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> types of units indexed from <code>0</code> to <code>n - 1</code>.</p>

<p>You are given a 2D integer array <code>conversions</code> of length <code>n - 1</code>, where <code>conversions[i] = [sourceUnit<sub>i</sub>, targetUnit<sub>i</sub>, conversionFactor<sub>i</sub>]</code>. This indicates that a single unit of type <code>sourceUnit<sub>i</sub></code> is equivalent to <code>conversionFactor<sub>i</sub></code> units of type <code>targetUnit<sub>i</sub></code>.</p>

<p>You are also given a 2D integer array <code>queries</code> of length <code>q</code>, where <code>queries[i] = [unitA<sub>i</sub>, unitB<sub>i</sub>]</code>.</p>

<p>Return an array <code face="monospace">answer</code> of length <code>q</code> where <code>answer[i]</code> is the number of units of type <code>unitB<sub>i</sub></code> equivalent to 1 unit of type <code>unitA<sub>i</sub></code>, and can be represented as <code>p/q</code> where <code>p</code> and <code>q</code> are coprime. Return each <code>answer[i]</code> as <code>pq<sup>-1</sup></code> <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>, where <code>q<sup>-1</sup></code> represents the multiplicative inverse of <code>q</code> modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">conversions = [[0,1,2],[0,2,6]], queries = [[1,2],[1,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,500000004]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>In the first query, we can convert unit 1 into 3 units of type 2 using the inverse of <code>conversions[0]</code>, then <code>conversions[1]</code>.</li>
	<li>In the second query, we can convert unit 1 into 1/2 units of type 0 using the inverse of <code>conversions[0]</code>. We return 500000004 since it is the multiplicative inverse of 2.</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3535.Unit%20Conversion%20II/images/example1.png" style="width: 500px; height: 500px;" /></div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">conversions = [[0,1,2],[0,2,6],[0,3,8],[2,4,2],[2,5,4],[3,6,3]], queries = [[1,2],[0,4],[6,5],[4,6],[6,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,12,1,2,83333334]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>In the first query, we can convert unit 1 into 3 units of type 2 using the inverse of <code>conversions[0]</code>, then <code>conversions[1]</code>.</li>
	<li>In the second query, we can convert unit 0 into 12 units of type 4 using <code>conversions[1]</code>, then <code>conversions[3]</code>.</li>
	<li>In the third query, we can convert unit 6 into 1 unit of type 5 using the inverse of <code>conversions[5]</code>, the inverse of <code>conversions[2]</code>, <code>conversions[1]</code>, then <code>conversions[4]</code>.</li>
	<li>In the fourth query, we can convert unit 4 into 2 units of type 6 using the inverse of <code>conversions[3]</code>, the inverse of <code>conversions[1]</code>, <code>conversions[2]</code>, then <code>conversions[5]</code>.</li>
	<li>In the fifth query, we can convert unit 6 into 1/12 units of type 1 using the inverse of <code>conversions[5]</code>, the inverse of <code>conversions[2]</code>, then <code>conversions[0]</code>. We return 83333334 since it is the multiplicative inverse of 12.</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3535.Unit%20Conversion%20II/images/example2.png" style="width: 504px; height: 493px;" /></div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>conversions.length == n - 1</code></li>
	<li><code>0 &lt;= sourceUnit<sub>i</sub>, targetUnit<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= conversionFactor<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= q &lt;= 10<sup>5</sup></code></li>
	<li><code>queries.length == q</code></li>
	<li><code>0 &lt;= unitA<sub>i</sub>, unitB<sub>i</sub> &lt; n</code></li>
	<li>It is guaranteed that unit 0 can be <strong>uniquely</strong> converted into any other unit through a combination of forward or backward conversions.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS + Modular Inverse

The conversion relations form a directed tree rooted at $0$. Starting a DFS from node $0$, we maintain `res[i]` as the number of units of type $i$ that equal $1$ unit of type $0$.

For a query $(unitA, unitB)$, the answer is $\frac{res[unitB]}{res[unitA]}$, which modulo $10^9 + 7$ equals `res[unitB] * res[unitA]^(MOD - 2) % MOD`, where `MOD - 2` is used to compute the modular inverse via Fermat's little theorem.

The time complexity is $O(n + q \log MOD)$ and the space complexity is $O(n)$, where $n$ is the number of unit types and $q$ is the number of queries.

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
