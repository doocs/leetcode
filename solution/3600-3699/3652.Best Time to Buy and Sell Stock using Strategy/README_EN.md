---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3652.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20using%20Strategy/README_EN.md
rating: 1556
source: Weekly Contest 463 Q1
tags:
    - Array
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [3652. Best Time to Buy and Sell Stock using Strategy](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-using-strategy)

[中文文档](/solution/3600-3699/3652.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20using%20Strategy/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>prices</code> and <code>strategy</code>, where:</p>

<ul>
	<li><code>prices[i]</code> is the price of a given stock on the <code>i<sup>th</sup></code> day.</li>
	<li><code>strategy[i]</code> represents a trading action on the <code>i<sup>th</sup></code> day, where:
	<ul>
		<li><code>-1</code> indicates buying one unit of the stock.</li>
		<li><code>0</code> indicates holding the stock.</li>
		<li><code>1</code> indicates selling one unit of the stock.</li>
	</ul>
	</li>
</ul>

<p>You are also given an <strong>even</strong> integer <code>k</code>, and may perform <strong>at most one</strong> modification to <code>strategy</code>. A modification consists of:</p>

<ul>
	<li>Selecting exactly <code>k</code> <strong>consecutive</strong> elements in <code>strategy</code>.</li>
	<li>Set the <strong>first</strong> <code>k / 2</code> elements to <code>0</code> (hold).</li>
	<li>Set the <strong>last</strong> <code>k / 2</code> elements to <code>1</code> (sell).</li>
</ul>

<p>The <strong>profit</strong> is defined as the <strong>sum</strong> of <code>strategy[i] * prices[i]</code> across all days.</p>

<p>Return the <strong>maximum</strong> possible profit you can achieve.</p>

<p><strong>Note:</strong> There are no constraints on budget or stock ownership, so all buy and sell operations are feasible regardless of past actions.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [4,2,8], strategy = [-1,0,1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Modification</th>
			<th style="border: 1px solid black;">Strategy</th>
			<th style="border: 1px solid black;">Profit Calculation</th>
			<th style="border: 1px solid black;">Profit</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">Original</td>
			<td style="border: 1px solid black;">[-1, 0, 1]</td>
			<td style="border: 1px solid black;">(-1 &times; 4) + (0 &times; 2) + (1 &times; 8) = -4 + 0 + 8</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">Modify [0, 1]</td>
			<td style="border: 1px solid black;">[0, 1, 1]</td>
			<td style="border: 1px solid black;">(0 &times; 4) + (1 &times; 2) + (1 &times; 8) = 0 + 2 + 8</td>
			<td style="border: 1px solid black;">10</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">Modify [1, 2]</td>
			<td style="border: 1px solid black;">[-1, 0, 1]</td>
			<td style="border: 1px solid black;">(-1 &times; 4) + (0 &times; 2) + (1 &times; 8) = -4 + 0 + 8</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
	</tbody>
</table>

<p>Thus, the maximum possible profit is 10, which is achieved by modifying the subarray <code>[0, 1]</code>​​​​​​​.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [5,4,3], strategy = [1,1,0], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<div class="example-block">
<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Modification</th>
			<th style="border: 1px solid black;">Strategy</th>
			<th style="border: 1px solid black;">Profit Calculation</th>
			<th style="border: 1px solid black;">Profit</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">Original</td>
			<td style="border: 1px solid black;">[1, 1, 0]</td>
			<td style="border: 1px solid black;">(1 &times; 5) + (1 &times; 4) + (0 &times; 3) = 5 + 4 + 0</td>
			<td style="border: 1px solid black;">9</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">Modify [0, 1]</td>
			<td style="border: 1px solid black;">[0, 1, 0]</td>
			<td style="border: 1px solid black;">(0 &times; 5) + (1 &times; 4) + (0 &times; 3) = 0 + 4 + 0</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">Modify [1, 2]</td>
			<td style="border: 1px solid black;">[1, 0, 1]</td>
			<td style="border: 1px solid black;">(1 &times; 5) + (0 &times; 4) + (1 &times; 3) = 5 + 0 + 3</td>
			<td style="border: 1px solid black;">8</td>
		</tr>
	</tbody>
</table>

<p>Thus, the maximum possible profit is 9, which is achieved without any modification.</p>
</div>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= prices.length == strategy.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>-1 &lt;= strategy[i] &lt;= 1</code></li>
	<li><code>2 &lt;= k &lt;= prices.length</code></li>
	<li><code>k</code> is even</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Enumeration

We use an array $\textit{s}$ to represent the prefix sum, where $\textit{s}[i]$ is the total profit for the first $i$ days, i.e., $\textit{s}[i] = \sum_{j=0}^{i-1} \textit{prices}[j] \times \textit{strategy}[j]$. We also use an array $\textit{t}$ to represent the prefix sum of stock prices, where $\textit{t}[i] = \sum_{j=0}^{i-1} \textit{prices}[j]$.

Initially, the maximum profit is $\textit{s}[n]$. We enumerate the right endpoint $i$ of the subarray to be modified, with the left endpoint being $i-k$. After modification, the first $k/2$ days of the subarray have strategy $0$, and the last $k/2$ days have strategy $1$, so the profit change is:

$$\Delta = -(\textit{s}[i] - \textit{s}[i-k]) + (\textit{t}[i] - \textit{t}[i-k/2])$$

Therefore, we can update the maximum profit by enumerating all possible $i$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProfit(self, prices: List[int], strategy: List[int], k: int) -> int:
        n = len(prices)
        s = [0] * (n + 1)
        t = [0] * (n + 1)
        for i, (a, b) in enumerate(zip(prices, strategy), 1):
            s[i] = s[i - 1] + a * b
            t[i] = t[i - 1] + a
        ans = s[n]
        for i in range(k, n + 1):
            ans = max(ans, s[n] - (s[i] - s[i - k]) + t[i] - t[i - k // 2])
        return ans
```

#### Java

```java
class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long[] s = new long[n + 1];
        long[] t = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            int a = prices[i - 1];
            int b = strategy[i - 1];
            s[i] = s[i - 1] + a * b;
            t[i] = t[i - 1] + a;
        }
        long ans = s[n];
        for (int i = k; i <= n; i++) {
            ans = Math.max(ans, s[n] - (s[i] - s[i - k]) + (t[i] - t[i - k / 2]));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxProfit(vector<int>& prices, vector<int>& strategy, int k) {
        int n = prices.size();
        vector<long long> s(n + 1), t(n + 1);
        for (int i = 1; i <= n; i++) {
            int a = prices[i - 1];
            int b = strategy[i - 1];
            s[i] = s[i - 1] + a * b;
            t[i] = t[i - 1] + a;
        }
        long long ans = s[n];
        for (int i = k; i <= n; i++) {
            ans = max(ans, s[n] - (s[i] - s[i - k]) + (t[i] - t[i - k / 2]));
        }
        return ans;
    }
};
```

#### Go

```go
func maxProfit(prices []int, strategy []int, k int) int64 {
	n := len(prices)
	s := make([]int64, n+1)
	t := make([]int64, n+1)

	for i := 1; i <= n; i++ {
		a := prices[i-1]
		b := strategy[i-1]
		s[i] = s[i-1] + int64(a*b)
		t[i] = t[i-1] + int64(a)
	}

	ans := s[n]
	for i := k; i <= n; i++ {
		ans = max(ans, s[n]-(s[i]-s[i-k])+(t[i]-t[i-k/2]))
	}
	return ans
}
```

#### TypeScript

```ts
function maxProfit(prices: number[], strategy: number[], k: number): number {
    const n = prices.length;
    const s: number[] = Array(n + 1).fill(0);
    const t: number[] = Array(n + 1).fill(0);

    for (let i = 1; i <= n; i++) {
        const a = prices[i - 1];
        const b = strategy[i - 1];
        s[i] = s[i - 1] + a * b;
        t[i] = t[i - 1] + a;
    }

    let ans = s[n];
    for (let i = k; i <= n; i++) {
        const val = s[n] - (s[i] - s[i - k]) + (t[i] - t[i - Math.floor(k / 2)]);
        ans = Math.max(ans, val);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_profit(prices: Vec<i32>, strategy: Vec<i32>, k: i32) -> i64 {
        let n: usize = prices.len();
        let k: usize = k as usize;

        let mut s: Vec<i64> = vec![0; n + 1];
        let mut t: Vec<i64> = vec![0; n + 1];

        for i in 1..=n {
            let a: i64 = prices[i - 1] as i64;
            let b: i64 = strategy[i - 1] as i64;
            s[i] = s[i - 1] + a * b;
            t[i] = t[i - 1] + a;
        }

        let mut ans: i64 = s[n];
        for i in k..=n {
            let cur = s[n] - (s[i] - s[i - k]) + (t[i] - t[i - k / 2]);
            if cur > ans {
                ans = cur;
            }
        }

        ans
    }
}
```

#### C#

```cs
public class Solution {
    public long MaxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.Length;
        long[] s = new long[n + 1];
        long[] t = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            long a = prices[i - 1];
            long b = strategy[i - 1];
            s[i] = s[i - 1] + a * b;
            t[i] = t[i - 1] + a;
        }

        long ans = s[n];
        for (int i = k; i <= n; i++) {
            long cur = s[n] - (s[i] - s[i - k]) + (t[i] - t[i - k / 2]);
            if (cur > ans) {
                ans = cur;
            }
        }

        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
