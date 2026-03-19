---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3864.Minimum%20Cost%20to%20Partition%20a%20Binary%20String/README_EN.md
rating: 2032
source: Weekly Contest 492 Q4
tags:
    - String
    - Divide and Conquer
    - Prefix Sum
---

<!-- problem:start -->

# [3864. Minimum Cost to Partition a Binary String](https://leetcode.com/problems/minimum-cost-to-partition-a-binary-string)

[中文文档](/solution/3800-3899/3864.Minimum%20Cost%20to%20Partition%20a%20Binary%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a binary string <code>s</code> and two integers <code>encCost</code> and <code>flatCost</code>.</p>

<p>For each index <code>i</code>, <code>s[i] = &#39;1&#39;</code> indicates that the <code>i<sup>th</sup></code> element is sensitive, and <code>s[i] = &#39;0&#39;</code> indicates that it is not.</p>

<p>The string must be partitioned into <strong>segments</strong>. Initially, the entire string forms a single segment.</p>

<p>For a segment of length <code>L</code> containing <code>X</code> sensitive elements:</p>

<ul>
	<li>If <code>X = 0</code>, the cost is <code>flatCost</code>.</li>
	<li>If <code>X &gt; 0</code>, the cost is <code>L * X * encCost</code>.</li>
</ul>

<p>If a segment has <strong>even length</strong>, you may split it into <strong>two contiguous segments</strong> of <strong>equal</strong> length and the cost of this split is the <strong>sum</strong> of <strong>costs</strong> of the resulting segments.</p>

<p>Return an integer denoting the <strong>minimum possible total cost</strong> over all valid partitions.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1010&quot;, encCost = 2, flatCost = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The entire string <code>s = &quot;1010&quot;</code> has length 4 and contains 2 sensitive elements, giving a cost of <code>4 * 2 * 2 = 16</code>.</li>
	<li>Since the length is even, it can be split into <code>&quot;10&quot;</code> and <code>&quot;10&quot;</code>. Each segment has length 2 and contains 1 sensitive element, so each costs <code>2 * 1 * 2 = 4</code>, giving a total of 8.</li>
	<li>Splitting both segments into four single-character segments yields the segments <code>&quot;1&quot;</code>, <code>&quot;0&quot;</code>, <code>&quot;1&quot;</code>, and <code>&quot;0&quot;</code>. A segment containing <code>&quot;1&quot;</code> has length 1 and exactly one sensitive element, giving a cost of <code>1 * 1 * 2 = 2</code>, while a segment containing <code>&quot;0&quot;</code> has no sensitive elements and therefore costs <code>flatCost = 1</code>.</li>
	<li>​​​​​​​The total cost is thus <code>2 + 1 + 2 + 1 = 6</code>, which is the minimum possible total cost.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1010&quot;, encCost = 3, flatCost = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The entire string <code>s = &quot;1010&quot;</code> has length 4 and contains 2 sensitive elements, giving a cost of <code>4 * 2 * 3 = 24</code>.</li>
	<li>Since the length is even, it can be split into two segments <code>&quot;10&quot;</code> and <code>&quot;10&quot;</code>.</li>
	<li>Each segment has length 2 and contains one sensitive element, so each costs <code>2 * 1 * 3 = 6</code>, giving a total of 12, which is the minimum possible total cost.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;00&quot;, encCost = 1, flatCost = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The string <code>s = &quot;00&quot;</code> has length 2 and contains no sensitive elements, so storing it as a single segment costs <code>flatCost = 2</code>, which is the minimum possible total cost.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</li>
	<li><code>1 &lt;= encCost, flatCost &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Recursion

We define a function $\text{dfs}(l, r)$ that represents the minimum cost for the interval $[l, r)$ of string $s$. We can use the prefix sum array $\text{pre}$ to calculate the number of sensitive elements $x$ in the interval $[l, r)$, thereby computing the cost without splitting.

The calculation process of function $\text{dfs}(l, r)$ is as follows:

1. Calculate the number of sensitive elements $x$ in the interval $[l, r)$.
2. Calculate the cost without splitting: if $x > 0$, the cost is $(r - l) \cdot x \cdot \text{encCost}$; if $x = 0$, the cost is $\text{flatCost}$.
3. If the interval length is even, we can try to split it into two consecutive segments of equal length, and calculate the cost after splitting as $\text{dfs}(l, m) + \text{dfs}(m, r)$, where $m = \frac{l + r}{2}$. Finally, return the smaller of the two values.

The answer is $\text{dfs}(0, n)$, where $n$ is the length of string $s$.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, s: str, encCost: int, flatCost: int) -> int:
        def dfs(l: int, r: int) -> int:
            x = pre[r] - pre[l]
            res = (r - l) * x * encCost if x else flatCost
            if (r - l) % 2 == 0:
                m = (l + r) // 2
                res = min(res, dfs(l, m) + dfs(m, r))
            return res

        n = len(s)
        pre = [0] * (n + 1)
        for i, c in enumerate(s, 1):
            pre[i] = pre[i - 1] + int(c)
        return dfs(0, n)
```

#### Java

```java
class Solution {
    private int[] pre;
    private int encCost;
    private int flatCost;

    public long minCost(String s, int encCost, int flatCost) {
        int n = s.length();
        this.encCost = encCost;
        this.flatCost = flatCost;

        pre = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            pre[i] = pre[i - 1] + (s.charAt(i - 1) - '0');
        }

        return dfs(0, n);
    }

    private long dfs(int l, int r) {
        int x = pre[r] - pre[l];
        long res = x != 0 ? (long) (r - l) * x * encCost : flatCost;

        if ((r - l) % 2 == 0) {
            int m = (l + r) >> 1;
            res = Math.min(res, dfs(l, m) + dfs(m, r));
        }

        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minCost(string s, int encCost, int flatCost) {
        int n = s.size();
        vector<int> pre(n + 1);

        for (int i = 1; i <= n; ++i) {
            pre[i] = pre[i - 1] + (s[i - 1] - '0');
        }

        auto dfs = [&](this auto&& dfs, int l, int r) -> long long {
            int x = pre[r] - pre[l];
            long long res = x ? 1LL * (r - l) * x * encCost : flatCost;

            if ((r - l) % 2 == 0) {
                int m = (l + r) >> 1;
                res = min(res, dfs(l, m) + dfs(m, r));
            }

            return res;
        };

        return dfs(0, n);
    }
};
```

#### Go

```go
func minCost(s string, encCost int, flatCost int) int64 {
	n := len(s)
	pre := make([]int, n+1)

	for i := 1; i <= n; i++ {
		pre[i] = pre[i-1] + int(s[i-1]-'0')
	}

	var dfs func(int, int) int64
	dfs = func(l, r int) int64 {
		x := pre[r] - pre[l]

		var res int64
		if x != 0 {
			res = int64(r-l) * int64(x) * int64(encCost)
		} else {
			res = int64(flatCost)
		}

		if (r-l)%2 == 0 {
			m := (l + r) / 2
			res = min(res, dfs(l, m)+dfs(m, r))
		}

		return res
	}

	return dfs(0, n)
}
```

#### TypeScript

```ts
function minCost(s: string, encCost: number, flatCost: number): number {
    const n = s.length;
    const pre: number[] = new Array(n + 1).fill(0);

    for (let i = 1; i <= n; i++) {
        pre[i] = pre[i - 1] + Number(s[i - 1]);
    }

    const dfs = (l: number, r: number): number => {
        const x = pre[r] - pre[l];
        let res = x ? (r - l) * x * encCost : flatCost;

        if ((r - l) % 2 === 0) {
            const m = (l + r) >> 1;
            res = Math.min(res, dfs(l, m) + dfs(m, r));
        }

        return res;
    };

    return dfs(0, n);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
