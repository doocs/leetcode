---
comments: true
difficulty: Medium
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [4069. Best Time to Buy and Sell Stock with Cooldown II 🔒](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown-ii)

[中文文档](/solution/4000-4099/4069.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Cooldown%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>prices</code> of length <code>n</code>, where <code>prices[i]</code> is the price of a stock on day <code>i</code>.</p>

<p>You are also given an integer <code>cooldown</code> and an integer array <code>costs</code> of length <code>n</code>. The value <code>costs[k]</code> is the <strong>total holding fee</strong> for a transaction in which the stock is held for exactly <code>k</code> days.</p>

<p>You may perform any number of transactions, including zero, subject to the following rules:</p>

<ul>
	<li>You may hold <strong>at most one</strong> share at a time. You must sell your current share before buying another.</li>
	<li>If you sell on day <code>j</code>, the earliest day you may buy again is <code>j + cooldown + 1</code>.</li>
	<li>If you buy on day <code>i</code> and sell on day <code>j</code>, the holding duration is <code>j - i</code> days. You pay the fee <code>costs[j - i]</code> once for that transaction.</li>
</ul>

<p>The profit from buying on day <code>i</code> and selling on day <code>j</code> is <code>prices[j] - prices[i] - costs[j - i]</code>.</p>

<p>Return the <strong>maximum total profit</strong> you can achieve. If no profitable transactions are possible, return 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [1,5,3], cooldown = 1, costs = [2,0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal strategy is:</p>

<ul>
	<li>Buy on day 0 at <code>prices[0] = 1</code>.</li>
	<li>Sell on day 1 at <code>prices[1] = 5</code>. The holding duration is 1 day, so the holding cost is <code>costs[1] = 0</code>.</li>
	<li>Therefore, the profit from this transaction is <code>5 - 1 - 0 = 4</code>.</li>
	<li>After selling, <code>cooldown = 1</code> prevents buying any stock on day 2, so no further transactions are possible.</li>
	<li>Maximum profit achievable is 4.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [1,2,5], cooldown = 0, costs = [0,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal strategy is:</p>

<ul>
	<li>Buy on day 0 at <code>prices[0] = 1</code>.</li>
	<li>Sell on day 2 at <code>prices[2] = 5</code>. The holding duration is 2 days, so the holding cost is <code>costs[2] = 1</code>.</li>
	<li>Therefore, the profit from this transaction is <code>5 - 1 - 1 = 3</code>.</li>
	<li>No further transactions improve the profit. Maximum profit achievable is 3.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [4,1,7], cooldown = 2, costs = [3,0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal strategy is:</p>

<ul>
	<li>Buy on day 1 at <code>prices[1] = 1</code>.</li>
	<li>Sell on day 2 at <code>prices[2] = 7</code>. The holding duration is 1 day, so the holding cost is <code>costs[1] = 0</code>.</li>
	<li>Profit from this transaction = <code>7 - 1 - 0 = 6</code>.</li>
	<li>No further transactions are possible. Maximum profit achievable = 6.</li>
</ul>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [3,1,4], cooldown = 0, costs = [2,1,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal strategy is:</p>

<ul>
	<li>Buy on day 1 at <code>prices[1] = 1</code>.</li>
	<li>Sell on day 2 at <code>prices[2] = 4</code>. The holding duration is 1 day, so the holding cost is <code>costs[1] = 1</code>.</li>
	<li>Therefore, the profit from this transaction is <code>4 - 1 - 1 = 2</code>.</li>
	<li>No further transactions improve the profit. Maximum profit achievable is 2.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == prices.length &lt;= 1500</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= cooldown &lt;= n - 1</code></li>
	<li><code>costs.length == n</code></li>
	<li><code>0 &lt;= costs[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
