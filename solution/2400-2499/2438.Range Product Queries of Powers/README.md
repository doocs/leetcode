---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2438.Range%20Product%20Queries%20of%20Powers/README.md
rating: 1609
source: 第 89 场双周赛 Q2
tags:
    - 位运算
    - 数组
    - 前缀和
---

<!-- problem:start -->

# [2438. 二的幂数组中查询范围内的乘积](https://leetcode.cn/problems/range-product-queries-of-powers)

[English Version](/solution/2400-2499/2438.Range%20Product%20Queries%20of%20Powers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数&nbsp;<code>n</code>&nbsp;，你需要找到一个下标从&nbsp;<strong>0</strong>&nbsp;开始的数组&nbsp;<code>powers</code>&nbsp;，它包含 <strong>最少</strong>&nbsp;数目的 <code>2</code>&nbsp;的幂，且它们的和为&nbsp;<code>n</code>&nbsp;。<code>powers</code>&nbsp;数组是&nbsp;<strong>非递减</strong>&nbsp;顺序的。根据前面描述，构造&nbsp;<code>powers</code>&nbsp;数组的方法是唯一的。</p>

<p>同时给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>queries</code>&nbsp;，其中&nbsp;<code>queries[i] = [left<sub>i</sub>, right<sub>i</sub>]</code>&nbsp;，其中&nbsp;<code>queries[i]</code>&nbsp;表示请你求出满足&nbsp;<code>left<sub>i</sub> &lt;= j &lt;= right<sub>i</sub></code>&nbsp;的所有&nbsp;<code>powers[j]</code>&nbsp;的乘积。</p>

<p>请你返回一个数组<em>&nbsp;</em><code>answers</code>&nbsp;，长度与<em>&nbsp;</em><code>queries</code>&nbsp;的长度相同，其中<em>&nbsp;</em><code>answers[i]</code>是第<em>&nbsp;</em><code>i</code>&nbsp;个查询的答案。由于查询的结果可能非常大，请你将每个&nbsp;<code>answers[i]</code>&nbsp;都对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>n = 15, queries = [[0,1],[2,2],[0,3]]
<b>输出：</b>[2,4,64]
<strong>解释：</strong>
对于 n = 15 ，得到 powers = [1,2,4,8] 。没法得到元素数目更少的数组。
第 1 个查询的答案：powers[0] * powers[1] = 1 * 2 = 2 。
第 2 个查询的答案：powers[2] = 4 。
第 3 个查询的答案：powers[0] * powers[1] * powers[2] * powers[3] = 1 * 2 * 4 * 8 = 64 。
每个答案对 10<sup>9</sup> + 7 取余得到的结果都相同，所以返回 [2,4,64] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 2, queries = [[0,0]]
<b>输出：</b>[2]
<strong>解释：</strong>
对于 n = 2, powers = [2] 。
唯一一个查询的答案是 powers[0] = 2 。答案对 10<sup>9</sup> + 7 取余后结果相同，所以返回 [2] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt; powers.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算 + 模拟

我们可以使用位运算（Lowbit）来得到 $\textit{powers}$ 数组，然后通过模拟的方式求出每个查询的答案。

首先，对于给定的正整数 $n$，我们通过 $n \& -n$ 可以快速得到二进制表示中最低位的 $1$ 对应的数值，也就是当前数的最小 $2$ 的幂因子。对 $n$ 反复执行这个操作并减去该值，就能依次得到所有置位的 $2$ 的幂，形成 $\textit{powers}$ 数组。这个数组是递增的，且长度等于 $n$ 的二进制表示中 $1$ 的个数。

接下来，我们需要处理每个查询。对于当前查询 $(l, r)$，我们需要计算

$$
\textit{answers}[i] = \prod_{j=l}^{r} \textit{powers}[j]
$$

其中 $\textit{answers}[i]$ 是第 $i$ 个查询的答案。由于查询的结果可能非常大，我们需要对每个答案取模 $10^9 + 7$。

时间复杂度 $O(m \times \log n)$，其中 $m$ 为数组 $\textit{queries}$ 的长度。忽略答案的空间消耗，空间复杂度 $O(\log n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def productQueries(self, n: int, queries: List[List[int]]) -> List[int]:
        powers = []
        while n:
            x = n & -n
            powers.append(x)
            n -= x
        mod = 10**9 + 7
        ans = []
        for l, r in queries:
            x = 1
            for i in range(l, r + 1):
                x = x * powers[i] % mod
            ans.append(x)
        return ans
```

#### Java

```java
class Solution {
    public int[] productQueries(int n, int[][] queries) {
        int[] powers = new int[Integer.bitCount(n)];
        for (int i = 0; n > 0; ++i) {
            int x = n & -n;
            powers[i] = x;
            n -= x;
        }
        int m = queries.length;
        int[] ans = new int[m];
        final int mod = (int) 1e9 + 7;
        for (int i = 0; i < m; ++i) {
            int l = queries[i][0], r = queries[i][1];
            long x = 1;
            for (int j = l; j <= r; ++j) {
                x = x * powers[j] % mod;
            }
            ans[i] = (int) x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> productQueries(int n, vector<vector<int>>& queries) {
        vector<int> powers;
        while (n) {
            int x = n & -n;
            powers.push_back(x);
            n -= x;
        }
        vector<int> ans;
        const int mod = 1e9 + 7;
        for (const auto& q : queries) {
            int l = q[0], r = q[1];
            long long x = 1;
            for (int j = l; j <= r; ++j) {
                x = x * powers[j] % mod;
            }
            ans.push_back(x);
        }
        return ans;
    }
};
```

#### Go

```go
func productQueries(n int, queries [][]int) []int {
	var powers []int
	for n > 0 {
		x := n & -n
		powers = append(powers, x)
		n -= x
	}
	const mod = 1_000_000_007
	ans := make([]int, 0, len(queries))
	for _, q := range queries {
		l, r := q[0], q[1]
		x := 1
		for j := l; j <= r; j++ {
			x = x * powers[j] % mod
		}
		ans = append(ans, x)
	}
	return ans
}
```

#### TypeScript

```ts
function productQueries(n: number, queries: number[][]): number[] {
    const powers: number[] = [];
    while (n > 0) {
        const x = n & -n;
        powers.push(x);
        n -= x;
    }
    const mod = 1_000_000_007;
    const ans: number[] = [];
    for (const [l, r] of queries) {
        let x = 1;
        for (let j = l; j <= r; j++) {
            x = (x * powers[j]) % mod;
        }
        ans.push(x);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn product_queries(mut n: i32, queries: Vec<Vec<i32>>) -> Vec<i32> {
        let mut powers = Vec::new();
        while n > 0 {
            let x = n & -n;
            powers.push(x);
            n -= x;
        }
        let modulo = 1_000_000_007;
        let mut ans = Vec::with_capacity(queries.len());
        for q in queries {
            let l = q[0] as usize;
            let r = q[1] as usize;
            let mut x: i64 = 1;
            for j in l..=r {
                x = x * powers[j] as i64 % modulo;
            }
            ans.push(x as i32);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
