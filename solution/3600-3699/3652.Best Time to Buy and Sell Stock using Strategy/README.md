---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3652.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20using%20Strategy/README.md
rating: 1556
source: 第 463 场周赛 Q1
tags:
    - 数组
    - 前缀和
    - 滑动窗口
---

<!-- problem:start -->

# [3652. 按策略买卖股票的最佳时机](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-using-strategy)

[English Version](/solution/3600-3699/3652.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20using%20Strategy/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组 <code>prices</code> 和 <code>strategy</code>，其中：</p>

<ul>
	<li><code>prices[i]</code> 表示第 <code>i</code> 天某股票的价格。</li>
	<li><code>strategy[i]</code> 表示第 <code>i</code> 天的交易策略，其中：
	<ul>
		<li><code>-1</code> 表示买入一单位股票。</li>
		<li><code>0</code> 表示持有股票。</li>
		<li><code>1</code> 表示卖出一单位股票。</li>
	</ul>
	</li>
</ul>

<p>同时给你一个&nbsp;<strong>偶数&nbsp;</strong>整数 <code>k</code>，你可以对 <code>strategy</code> 进行&nbsp;<strong>最多一次&nbsp;</strong>修改。一次修改包括：</p>

<ul>
	<li>选择 <code>strategy</code> 中恰好 <code>k</code> 个&nbsp;<strong>连续&nbsp;</strong>元素。</li>
	<li>将前 <code>k / 2</code> 个元素设为 <code>0</code>（持有）。</li>
	<li>将后 <code>k / 2</code> 个元素设为 <code>1</code>（卖出）。</li>
</ul>

<p><strong>利润&nbsp;</strong>定义为所有天数中 <code>strategy[i] * prices[i]</code> 的&nbsp;<strong>总和&nbsp;</strong>。</p>

<p>返回你可以获得的&nbsp;<strong>最大&nbsp;</strong>可能利润。</p>

<p><strong>注意：</strong> 没有预算或股票持有数量的限制，因此所有买入和卖出操作均可行，无需考虑过去的操作。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">prices = [4,2,8], strategy = [-1,0,1], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">10</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">修改</th>
			<th style="border: 1px solid black;">策略</th>
			<th style="border: 1px solid black;">利润计算</th>
			<th style="border: 1px solid black;">利润</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">原始</td>
			<td style="border: 1px solid black;">[-1, 0, 1]</td>
			<td style="border: 1px solid black;">(-1 × 4) + (0 × 2) + (1 × 8) = -4 + 0 + 8</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">修改 [0, 1]</td>
			<td style="border: 1px solid black;">[0, 1, 1]</td>
			<td style="border: 1px solid black;">(0 × 4) + (1 × 2) + (1 × 8) = 0 + 2 + 8</td>
			<td style="border: 1px solid black;">10</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">修改 [1, 2]</td>
			<td style="border: 1px solid black;">[-1, 0, 1]</td>
			<td style="border: 1px solid black;">(-1 × 4) + (0 × 2) + (1 × 8) = -4 + 0 + 8</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
	</tbody>
</table>

<p>因此，最大可能利润是 10，通过修改子数组 <code>[0, 1]</code> 实现。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">prices = [5,4,3], strategy = [1,1,0], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">9</span></p>

<p><strong>解释：</strong></p>

<div class="example-block">
<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">修改</th>
			<th style="border: 1px solid black;">策略</th>
			<th style="border: 1px solid black;">利润计算</th>
			<th style="border: 1px solid black;">利润</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">原始</td>
			<td style="border: 1px solid black;">[1, 1, 0]</td>
			<td style="border: 1px solid black;">(1 × 5) + (1 × 4) + (0 × 3) = 5 + 4 + 0</td>
			<td style="border: 1px solid black;">9</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">修改 [0, 1]</td>
			<td style="border: 1px solid black;">[0, 1, 0]</td>
			<td style="border: 1px solid black;">(0 × 5) + (1 × 4) + (0 × 3) = 0 + 4 + 0</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">修改 [1, 2]</td>
			<td style="border: 1px solid black;">[1, 0, 1]</td>
			<td style="border: 1px solid black;">(1 × 5) + (0 × 4) + (1 × 3) = 5 + 0 + 3</td>
			<td style="border: 1px solid black;">8</td>
		</tr>
	</tbody>
</table>

<p>因此，最大可能利润是 9，无需任何修改即可达成。</p>
</div>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= prices.length == strategy.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>-1 &lt;= strategy[i] &lt;= 1</code></li>
	<li><code>2 &lt;= k &lt;= prices.length</code></li>
	<li><code>k</code> 是偶数</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和 + 枚举

我们用一个数组 $\textit{s}$ 来表示前缀和，其中 $\textit{s}[i]$ 表示前 $i$ 天的利润和，即 $\textit{s}[i] = \sum_{j=0}^{i-1} \textit{prices}[j] \times \textit{strategy}[j]$。我们还用一个数组 $\textit{t}$ 来表示前缀和，其中 $\textit{t}[i]$ 表示前 $i$ 天的股票价格和，即 $\textit{t}[i] = \sum_{j=0}^{i-1} \textit{prices}[j]$。

初始时，最大利润为 $\textit{s}[n]$。我们枚举修改的子数组的右端点 $i$，则左端点为 $i-k$。修改后，子数组内前 $k/2$ 天的策略变为 $0$，后 $k/2$ 天的策略变为 $1$，因此利润变化为：

$$\Delta = -(\textit{s}[i] - \textit{s}[i-k]) + (\textit{t}[i] - \textit{t}[i-k/2])$$

因此，我们可以通过枚举所有可能的 $i$ 来更新最大利润。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

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
