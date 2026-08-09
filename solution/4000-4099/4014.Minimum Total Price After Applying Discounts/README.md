---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4014.Minimum%20Total%20Price%20After%20Applying%20Discounts/README.md
---

<!-- problem:start -->

# [4014. 应用折扣后的最低总价](https://leetcode.cn/problems/minimum-total-price-after-applying-discounts)

[English Version](/solution/4000-4099/4014.Minimum%20Total%20Price%20After%20Applying%20Discounts/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组 <code>prices</code> 和 <code>discounts</code>。</p>

<p><code>prices[i]</code> 表示第 <code>i<sup>th</sup></code> 件商品的价格，<code>discounts[j]</code> 表示一个折扣百分比。</p>

<p>你可以按照以下规则使用折扣：</p>

<ul>
	<li>每个折扣&nbsp;<strong>最多&nbsp;</strong>只能用于一件商品。</li>
	<li>每件商品<strong>&nbsp;最多</strong>&nbsp;只能使用一个折扣。</li>
	<li>商品也可以不使用任何折扣。</li>
</ul>

<p>如果将 <code>d</code>% 的折扣应用于价格为 <code>p</code> 的商品，则其最终价格为 <code>(p * (100 - d)) / 100</code>。最终价格<strong>&nbsp;不进行四舍五入&nbsp;</strong>。</p>

<p>请以最优方式分配折扣，并返回所有商品最终价格之和的&nbsp;<strong>最小值&nbsp;</strong>。与实际答案的误差在 <code>10<sup>-5</sup></code> 以内的结果都将被接受。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">prices = [10,30,21], discounts = [50,60]</span></p>

<p><strong>输出：</strong> <span class="example-io">32.50000</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将 <code>discounts[1] = 60</code> 应用于 <code>prices[1] = 30</code>，则最终价格为 <code>30 * (100 - 60) / 100 = 12</code>。</li>
	<li>将 <code>discounts[0] = 50</code> 应用于 <code>prices[2] = 21</code>，则最终价格为 <code>21 * (100 - 50) / 100 = 10.5</code>。</li>
	<li><code>prices[0] = 10</code> 不使用折扣，因此价格仍为 10。</li>
</ul>

<p>总价为 <code>12 + 10.5 + 10 = 32.50000</code>，这是可能得到的最小值。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">prices = [100,70], discounts = [10,40,50]</span></p>

<p><strong>输出：</strong> <span class="example-io">92.00000</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将 <code>discounts[2] = 50</code> 应用于 <code>prices[0] = 100</code>，则最终价格为 <code>100 * (100 - 50) / 100 = 50</code>。</li>
	<li>将 <code>discounts[1] = 40</code> 应用于 <code>prices[1] = 70</code>，则最终价格为 <code>70 * (100 - 40) / 100 = 42</code>。</li>
</ul>

<p>总价为 <code>50 + 42 = 92.00000</code>，这是可能得到的最小值。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">prices = [7,3,9], discounts = [100,100]</span></p>

<p><strong>输出：</strong> <span class="example-io">3.00000</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将 <code>discounts[0] = 100</code> 应用于 <code>prices[2] = 9</code>，则最终价格为 <code>9 * (100 - 100) / 100 = 0</code>。</li>
	<li>将 <code>discounts[1] = 100</code> 应用于 <code>prices[0] = 7</code>，则最终价格为 <code>7 * (100 - 100) / 100 = 0</code>。</li>
	<li><code>prices[1] = 3</code> 不使用折扣，因此价格仍为 3。</li>
</ul>

<p>总价为 <code>0 + 0 + 3 = 3.00000</code>，这是可能得到的最小值。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prices.length, discounts.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= discounts[j] &lt;= 100</code></li>
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
