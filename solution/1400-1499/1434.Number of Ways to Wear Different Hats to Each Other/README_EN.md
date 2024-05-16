---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1434.Number%20of%20Ways%20to%20Wear%20Different%20Hats%20to%20Each%20Other/README_EN.md
rating: 2273
source: Biweekly Contest 25 Q4
tags:
    - Bit Manipulation
    - Array
    - Dynamic Programming
    - Bitmask
---

<!-- problem:start -->

# [1434. Number of Ways to Wear Different Hats to Each Other](https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other)

[中文文档](/solution/1400-1499/1434.Number%20of%20Ways%20to%20Wear%20Different%20Hats%20to%20Each%20Other/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> people and <code>40</code> types of hats labeled from <code>1</code> to <code>40</code>.</p>

<p>Given a 2D integer array <code>hats</code>, where <code>hats[i]</code> is a list of all hats preferred by the <code>i<sup>th</sup></code> person.</p>

<p>Return <em>the number of ways that the <code>n</code> people wear different hats to each other</em>.</p>

<p>Since the answer may be too large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> hats = [[3,4],[4,5],[5]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only one way to choose hats given the conditions. 
First person choose hat 3, Second person choose hat 4 and last one hat 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> hats = [[3,5,1],[3,5]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 ways to choose hats:
(3,5), (5,3), (1,3) and (1,5)
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
<strong>Output:</strong> 24
<strong>Explanation:</strong> Each person can choose hats labeled from 1 to 4.
Number of Permutations of (1,2,3,4) = 24.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == hats.length</code></li>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>1 &lt;= hats[i].length &lt;= 40</code></li>
	<li><code>1 &lt;= hats[i][j] &lt;= 40</code></li>
	<li><code>hats[i]</code> contains a list of <strong>unique</strong> integers.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We notice that $n$ is not greater than $10$, so we consider using DP with state compression to solve this problem.

We define $f[i][j]$ as the number of ways to assign the first $i$ hats to the people whose state is $j$. Here $j$ is a binary number, which represents a set of people. We have $f[0][0]=1$ at the beginning, and the answer is $f[m][2^n - 1]$, where $m$ is the maximum number of hats and $n$ is the number of people.

Consider $f[i][j]$. If we don't assign the $i$-th hat to anyone, then $f[i][j]=f[i-1][j]$; if we assign the $i$-th hat to the person $k$ who likes it, then $f[i][j]=f[i-1][j \oplus 2^k]$. Here $\oplus$ denotes the XOR operation. Therefore, we can get the state transition equation:

$$
f[i][j]=f[i-1][j]+ \sum_{k \in like[i]} f[i-1][j \oplus 2^k]
$$

where $like[i]$ denotes the set of people who like the $i$-th hat.

The final answer is $f[m][2^n - 1]$, and the answer may be very large, so we need to take it modulo $10^9 + 7$.

Time complexity $O(m \times 2^n \times n)$, space complexity $O(m \times 2^n)$. Here $m$ is the maximum number of hats, which is no more than $40$ in this problem; and $n$ is the number of people, which is no more than $10$ in this problem.

<!-- tabs:start -->

```python
class Solution:
    def numberWays(self, hats: List[List[int]]) -> int:
        g = defaultdict(list)
        for i, h in enumerate(hats):
            for v in h:
                g[v].append(i)
        mod = 10**9 + 7
        n = len(hats)
        m = max(max(h) for h in hats)
        f = [[0] * (1 << n) for _ in range(m + 1)]
        f[0][0] = 1
        for i in range(1, m + 1):
            for j in range(1 << n):
                f[i][j] = f[i - 1][j]
                for k in g[i]:
                    if j >> k & 1:
                        f[i][j] = (f[i][j] + f[i - 1][j ^ (1 << k)]) % mod
        return f[m][-1]
```

```java
class Solution {
    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();
        int m = 0;
        for (var h : hats) {
            for (int v : h) {
                m = Math.max(m, v);
            }
        }
        List<Integer>[] g = new List[m + 1];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            for (int v : hats.get(i)) {
                g[v].add(i);
            }
        }
        final int mod = (int) 1e9 + 7;
        int[][] f = new int[m + 1][1 << n];
        f[0][0] = 1;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j < 1 << n; ++j) {
                f[i][j] = f[i - 1][j];
                for (int k : g[i]) {
                    if ((j >> k & 1) == 1) {
                        f[i][j] = (f[i][j] + f[i - 1][j ^ (1 << k)]) % mod;
                    }
                }
            }
        }
        return f[m][(1 << n) - 1];
    }
}
```

```cpp
class Solution {
public:
    int numberWays(vector<vector<int>>& hats) {
        int n = hats.size();
        int m = 0;
        for (auto& h : hats) {
            m = max(m, *max_element(h.begin(), h.end()));
        }
        vector<vector<int>> g(m + 1);
        for (int i = 0; i < n; ++i) {
            for (int& v : hats[i]) {
                g[v].push_back(i);
            }
        }
        const int mod = 1e9 + 7;
        int f[m + 1][1 << n];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j < 1 << n; ++j) {
                f[i][j] = f[i - 1][j];
                for (int k : g[i]) {
                    if (j >> k & 1) {
                        f[i][j] = (f[i][j] + f[i - 1][j ^ (1 << k)]) % mod;
                    }
                }
            }
        }
        return f[m][(1 << n) - 1];
    }
};
```

```go
func numberWays(hats [][]int) int {
	n := len(hats)
	m := 0
	for _, h := range hats {
		m = max(m, slices.Max(h))
	}
	g := make([][]int, m+1)
	for i, h := range hats {
		for _, v := range h {
			g[v] = append(g[v], i)
		}
	}
	const mod = 1e9 + 7
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, 1<<n)
	}
	f[0][0] = 1
	for i := 1; i <= m; i++ {
		for j := 0; j < 1<<n; j++ {
			f[i][j] = f[i-1][j]
			for _, k := range g[i] {
				if j>>k&1 == 1 {
					f[i][j] = (f[i][j] + f[i-1][j^(1<<k)]) % mod
				}
			}
		}
	}
	return f[m][(1<<n)-1]
}
```

```ts
function numberWays(hats: number[][]): number {
    const n = hats.length;
    const m = Math.max(...hats.flat());
    const g: number[][] = Array.from({ length: m + 1 }, () => []);
    for (let i = 0; i < n; ++i) {
        for (const v of hats[i]) {
            g[v].push(i);
        }
    }
    const f: number[][] = Array.from({ length: m + 1 }, () =>
        Array.from({ length: 1 << n }, () => 0),
    );
    f[0][0] = 1;
    const mod = 1e9 + 7;
    for (let i = 1; i <= m; ++i) {
        for (let j = 0; j < 1 << n; ++j) {
            f[i][j] = f[i - 1][j];
            for (const k of g[i]) {
                if (((j >> k) & 1) === 1) {
                    f[i][j] = (f[i][j] + f[i - 1][j ^ (1 << k)]) % mod;
                }
            }
        }
    }
    return f[m][(1 << n) - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
