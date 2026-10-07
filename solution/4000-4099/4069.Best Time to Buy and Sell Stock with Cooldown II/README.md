---
comments: true
difficulty: 中等
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [4069. 买卖股票的最佳时机含冷冻期 II 🔒](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown-ii)

[English Version](/solution/4000-4099/4069.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Cooldown%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为 <code>n</code> 的整数数组 <code>prices</code>，其中 <code>prices[i]</code> 表示第 <code>i</code> 天的股票价格。</p>

<p>同时给定一个整数 <code>cooldown</code> 和一个长度为 <code>n</code> 的整数数组 <code>costs</code>。其中，<code>costs[k]</code> 表示一笔股票恰好持有 <code>k</code> 天时需要支付的&nbsp;<strong>总持有费用</strong>。</p>

<p>你可以进行任意次数的交易，也可以不进行任何交易，但需要满足以下规则：</p>

<ul>
	<li>任意时刻你&nbsp;<strong>最多只能持有一股&nbsp;</strong>股票。在再次买入之前，必须先卖出当前持有的股票。</li>
	<li>如果你在第 <code>j</code> 天卖出股票，那么最早可以在第 <code>j + cooldown + 1</code> 天再次买入股票。</li>
	<li>如果你在第 <code>i</code> 天买入，并在第 <code>j</code> 天卖出，那么持有时长为 <code>j - i</code> 天。你需要为该笔交易支付一次费用 <code>costs[j - i]</code>。</li>
</ul>

<p>在第 <code>i</code> 天买入并在第 <code>j</code> 天卖出的利润为 <code>prices[j] - prices[i] - costs[j - i]</code>。</p>

<p>返回你能够获得的&nbsp;<strong>最大总利润</strong>。如果不存在任何有利可图的交易，则返回 0。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">prices = [1,5,3], cooldown = 1, costs = [2,0,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>最优策略如下：</p>

<ul>
	<li>在第 0 天以 <code>prices[0] = 1</code> 的价格买入。</li>
	<li>在第 1 天以 <code>prices[1] = 5</code> 的价格卖出。持有时长为 1 天，因此持有费用为 <code>costs[1] = 0</code>。</li>
	<li>因此，该笔交易的利润为 <code>5 - 1 - 0 = 4</code>。</li>
	<li>卖出后，由于 <code>cooldown = 1</code>，第 2 天不能再买入股票，因此无法进行更多交易。</li>
	<li>能够获得的最大利润为 4。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">prices = [1,2,5], cooldown = 0, costs = [0,2,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>最优策略如下：</p>

<ul>
	<li>在第 0 天以 <code>prices[0] = 1</code> 的价格买入。</li>
	<li>在第 2 天以 <code>prices[2] = 5</code> 的价格卖出。持有时长为 2 天，因此持有费用为 <code>costs[2] = 1</code>。</li>
	<li>因此，该笔交易的利润为 <code>5 - 1 - 1 = 3</code>。</li>
	<li>继续进行交易无法获得更高利润。能够获得的最大利润为 3。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">prices = [4,1,7], cooldown = 2, costs = [3,0,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<p>最优策略如下：</p>

<ul>
	<li>在第 1 天以 <code>prices[1] = 1</code> 的价格买入。</li>
	<li>在第 2 天以 <code>prices[2] = 7</code> 的价格卖出。持有时长为 1 天，因此持有费用为 <code>costs[1] = 0</code>。</li>
	<li>该笔交易的利润为 <code>7 - 1 - 0 = 6</code>。</li>
	<li>之后无法再进行更多交易。能够获得的最大利润为 6。</li>
</ul>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">prices = [3,1,4], cooldown = 0, costs = [2,1,0]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>最优策略如下：</p>

<ul>
	<li>在第 1 天以 <code>prices[1] = 1</code> 的价格买入。</li>
	<li>在第 2 天以 <code>prices[2] = 4</code> 的价格卖出。持有时长为 1 天，因此持有费用为 <code>costs[1] = 1</code>。</li>
	<li>因此，该笔交易的利润为 <code>4 - 1 - 1 = 2</code>。</li>
	<li>继续进行交易无法获得更高利润。能够获得的最大利润为 2。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == prices.length &lt;= 1500</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= cooldown &lt;= n - 1</code></li>
	<li><code>costs.length == n</code></li>
	<li><code>0 &lt;= costs[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
