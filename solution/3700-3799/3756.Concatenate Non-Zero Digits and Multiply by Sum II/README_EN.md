---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3756.Concatenate%20Non-Zero%20Digits%20and%20Multiply%20by%20Sum%20II/README_EN.md
rating: 1968
source: Weekly Contest 477 Q3
tags:
    - Math
    - String
    - Prefix Sum
---

<!-- problem:start -->

# [3756. Concatenate Non-Zero Digits and Multiply by Sum II](https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-ii)

[中文文档](/solution/3700-3799/3756.Concatenate%20Non-Zero%20Digits%20and%20Multiply%20by%20Sum%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> of length <code>m</code> consisting of digits. You are also given a 2D integer array <code>queries</code>, where <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code>.</p>

<p>For each <code>queries[i]</code>, extract the <strong><span data-keyword="substring-nonempty">substring</span></strong> <code>s[l<sub>i</sub>..r<sub>i</sub>]</code>. Then, perform the following:</p>

<ul>
	<li>Form a new integer <code>x</code> by concatenating all the <strong>non-zero digits</strong> from the substring in their original order. If there are no non-zero digits, <code>x = 0</code>.</li>
	<li>Let <code>sum</code> be the <strong>sum of digits</strong> in <code>x</code>. The answer is <code>x * sum</code>.</li>
</ul>

<p>Return an array of integers <code>answer</code> where <code>answer[i]</code> is the answer to the <code>i<sup>th</sup></code> query.</p>

<p>Since the answers may be very large, return them <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;10203004&quot;, queries = [[0,7],[1,3],[4,6]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[12340, 4, 9]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>s[0..7] = &quot;10203004&quot;</code>

    <ul>
    	<li><code>x = 1234</code></li>
    	<li><code>sum = 1 + 2 + 3 + 4 = 10</code></li>
    	<li>Therefore, answer is <code>1234 * 10 = 12340</code>.</li>
    </ul>
    </li>
    <li><code>s[1..3] = &quot;020&quot;</code>
    <ul>
    	<li><code>x = 2</code></li>
    	<li><code>sum = 2</code></li>
    	<li>Therefore, the answer is <code>2 * 2 = 4</code>.</li>
    </ul>
    </li>
    <li><code>s[4..6] = &quot;300&quot;</code>
    <ul>
    	<li><code>x = 3</code></li>
    	<li><code>sum = 3</code></li>
    	<li>Therefore, the answer is <code>3 * 3 = 9</code>.</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1000&quot;, queries = [[0,3],[1,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1, 0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>s[0..3] = &quot;1000&quot;</code>

    <ul>
    	<li><code>x = 1</code></li>
    	<li><code>sum = 1</code></li>
    	<li>Therefore, the answer is <code>1 * 1 = 1</code>.</li>
    </ul>
    </li>
    <li><code>s[1..1] = &quot;0&quot;</code>
    <ul>
    	<li><code>x = 0</code></li>
    	<li><code>sum = 0</code></li>
    	<li>Therefore, the answer is <code>0 * 0 = 0</code>.</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;9876543210&quot;, queries = [[0,9]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[444444137]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>s[0..9] = &quot;9876543210&quot;</code>

    <ul>
    	<li><code>x = 987654321</code></li>
    	<li><code>sum = 9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 = 45</code></li>
    	<li>Therefore, the answer is <code>987654321 * 45 = 44444444445</code>.</li>
    	<li>We return <code>44444444445 modulo (10<sup>9</sup> + 7) = 444444137</code>.</li>
    </ul>
    </li>

</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of digits only.</li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; m</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum

We preprocess three prefix arrays:

- `sumD[i]` is the sum of digits in the first $i$ characters of the string;
- `cntN0[i]` is the count of non-zero digits in the first $i$ characters;
- `p[i]` is the number formed by concatenating all non-zero digits in the first $i$ characters, modulo $10^9 + 7$.

For a query $[l, r]$, the number of non-zero digits in the substring is $n_0 = cntN0[r + 1] - cntN0[l]$, and the digit sum is $sd = sumD[r + 1] - sumD[l]$. Since $p[r + 1] = p[l] \cdot 10^{n_0} + x$, we have $x = p[r + 1] - p[l] \cdot 10^{n_0}$, and the answer is $x \cdot sd$.

We precompute powers of $10$ and answer each query in $O(1)$.

The time complexity is $O(n + q)$ and the space complexity is $O(n)$, where $n$ is the string length and $q$ is the number of queries.

<!-- tabs:start -->

#### Python3

```python
mx = 10**5 + 1
mod = 10**9 + 7
pow10 = [1] * mx
for i in range(1, mx):
    pow10[i] = pow10[i - 1] * 10 % mod


class Solution:
    def sumAndMultiply(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        sum_d = [0] * (n + 1)
        cnt_n0 = [0] * (n + 1)
        p = [0] * (n + 1)
        for i, d in enumerate(map(int, s), 1):
            sum_d[i] = sum_d[i - 1] + d
            cnt_n0[i] = cnt_n0[i - 1] + int(d > 0)
            p[i] = (p[i - 1] * 10 + d) % mod if d else p[i - 1]

        ans = []
        for l, r in queries:
            n0 = cnt_n0[r + 1] - cnt_n0[l]
            sd = sum_d[r + 1] - sum_d[l]
            x = p[r + 1] - p[l] * pow10[n0] % mod
            ans.append(x * sd % mod)
        return ans
```

#### Java

```java
class Solution {
    private static final int MX = 100001;
    private static final int MOD = 1_000_000_007;
    private static final long[] POW10 = new long[MX];

    static {
        POW10[0] = 1;
        for (int i = 1; i < MX; i++) {
            POW10[i] = POW10[i - 1] * 10 % MOD;
        }
    }

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        int[] sumD = new int[n + 1];
        int[] cntN0 = new int[n + 1];
        long[] p = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            int d = s.charAt(i - 1) - '0';
            sumD[i] = sumD[i - 1] + d;
            cntN0[i] = cntN0[i - 1] + (d > 0 ? 1 : 0);
            p[i] = d > 0 ? (p[i - 1] * 10 + d) % MOD : p[i - 1];
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            int n0 = cntN0[r + 1] - cntN0[l];
            int sd = sumD[r + 1] - sumD[l];
            long x = (p[r + 1] - p[l] * POW10[n0] % MOD + MOD) % MOD;
            ans[i] = (int) (x * sd % MOD);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> sumAndMultiply(string s, vector<vector<int>>& queries) {
        static const int MX = 100001;
        static const int MOD = 1000000007;
        static vector<long long> pow10 = [] {
            vector<long long> p(MX);
            p[0] = 1;
            for (int i = 1; i < MX; i++) {
                p[i] = p[i - 1] * 10 % MOD;
            }
            return p;
        }();

        int n = s.size();
        vector<int> sumD(n + 1), cntN0(n + 1);
        vector<long long> p(n + 1);

        for (int i = 1; i <= n; i++) {
            int d = s[i - 1] - '0';
            sumD[i] = sumD[i - 1] + d;
            cntN0[i] = cntN0[i - 1] + (d > 0);
            p[i] = d ? (p[i - 1] * 10 + d) % MOD : p[i - 1];
        }

        vector<int> ans;
        ans.reserve(queries.size());
        for (auto& q : queries) {
            int l = q[0], r = q[1];
            int n0 = cntN0[r + 1] - cntN0[l];
            int sd = sumD[r + 1] - sumD[l];
            long long x = (p[r + 1] - p[l] * pow10[n0] % MOD + MOD) % MOD;
            ans.push_back(x * sd % MOD);
        }
        return ans;
    }
};
```

#### Go

```go
const (
	mx        = 100001
	mod int64 = 1000000007
)

var pow10 = func() []int64 {
	p := make([]int64, mx)
	p[0] = 1
	for i := 1; i < mx; i++ {
		p[i] = p[i-1] * 10 % mod
	}
	return p
}()

func sumAndMultiply(s string, queries [][]int) []int {
	n := len(s)
	sumD := make([]int, n+1)
	cntN0 := make([]int, n+1)
	p := make([]int64, n+1)

	for i := 1; i <= n; i++ {
		d := int64(s[i-1] - '0')
		sumD[i] = sumD[i-1] + int(d)
		cntN0[i] = cntN0[i-1]
		if d > 0 {
			cntN0[i]++
			p[i] = (p[i-1]*10 + d) % mod
		} else {
			p[i] = p[i-1]
		}
	}

	ans := make([]int, len(queries))
	for i, q := range queries {
		l, r := q[0], q[1]
		n0 := cntN0[r+1] - cntN0[l]
		sd := int64(sumD[r+1] - sumD[l])
		x := (p[r+1] - p[l]*pow10[n0]%mod + mod) % mod
		ans[i] = int(x * sd % mod)
	}
	return ans
}
```

#### TypeScript

```ts
const MX = 100001;
const MOD = 1000000007n;

const pow10: bigint[] = Array(MX).fill(1n);
for (let i = 1; i < MX; i++) {
    pow10[i] = (pow10[i - 1] * 10n) % MOD;
}

function sumAndMultiply(s: string, queries: number[][]): number[] {
    const n = s.length;
    const sumD = Array<number>(n + 1).fill(0);
    const cntN0 = Array<number>(n + 1).fill(0);
    const p: bigint[] = Array(n + 1).fill(0n);

    for (let i = 1; i <= n; i++) {
        const d = s.charCodeAt(i - 1) - 48;
        sumD[i] = sumD[i - 1] + d;
        cntN0[i] = cntN0[i - 1] + (d > 0 ? 1 : 0);
        p[i] = d > 0 ? (p[i - 1] * 10n + BigInt(d)) % MOD : p[i - 1];
    }

    const ans: number[] = [];
    for (const [l, r] of queries) {
        const n0 = cntN0[r + 1] - cntN0[l];
        const sd = BigInt(sumD[r + 1] - sumD[l]);
        const x = (p[r + 1] - ((p[l] * pow10[n0]) % MOD) + MOD) % MOD;
        ans.push(Number((x * sd) % MOD));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
