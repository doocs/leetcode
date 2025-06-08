---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3573.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20V/README.md
---

<!-- problem:start -->

# [3573. 买卖股票的最佳时机 V](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-v)

[English Version](/solution/3500-3599/3573.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20V/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>prices</code>，其中 <code>prices[i]</code> 是第 <code>i</code> 天股票的价格（美元），以及一个整数 <code>k</code>。</p>

<p>你最多可以进行 <code>k</code> 笔交易，每笔交易可以是以下任一类型：</p>

<ul>
	<li>
	<p><strong>普通交易</strong>：在第 <code>i</code> 天买入，然后在之后的第 <code>j</code> 天卖出，其中 <code>i &lt; j</code>。你的利润是 <code>prices[j] - prices[i]</code>。</p>
	</li>
	<li>
	<p><strong>做空交易</strong>：在第 <code>i</code> 天卖出，然后在之后的第 <code>j</code> 天买回，其中 <code>i &lt; j</code>。你的利润是 <code>prices[i] - prices[j]</code>。</p>
	</li>
</ul>

<p><strong>注意</strong>：你必须在开始下一笔交易之前完成当前交易。此外，你不能在已经进行买入或卖出操作的同一天再次进行买入或卖出操作。</p>

<p>通过进行&nbsp;<strong>最多</strong> <code>k</code> 笔交易，返回你可以获得的最大总利润。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">prices = [1,7,9,8,2], k = 2</span></p>

<p><strong>输出:</strong> <span class="example-io">14</span></p>

<p><strong>解释:</strong></p>
我们可以通过 2 笔交易获得 14 美元的利润：

<ul>
	<li>一笔普通交易：第 0 天以 1 美元买入，第 2 天以 9 美元卖出。</li>
	<li>一笔做空交易：第 3 天以 8 美元卖出，第 4 天以 2 美元买回。</li>
</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">prices = [12,16,19,19,8,1,19,13,9], k = 3</span></p>

<p><strong>输出:</strong> <span class="example-io">36</span></p>

<p><strong>解释:</strong></p>
我们可以通过 3 笔交易获得 36 美元的利润：

<ul>
	<li>一笔普通交易：第 0 天以 12 美元买入，第 2 天以 19 美元卖出。</li>
	<li>一笔做空交易：第 3 天以 19 美元卖出，第 4 天以 8 美元买回。</li>
	<li>一笔普通交易：第 5 天以 1 美元买入，第 6 天以 19 美元卖出。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= prices.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= prices.length / 2</code></li>
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
