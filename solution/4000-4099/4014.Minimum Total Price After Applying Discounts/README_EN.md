---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4014.Minimum%20Total%20Price%20After%20Applying%20Discounts/README_EN.md
---

<!-- problem:start -->

# [4014. Minimum Total Price After Applying Discounts](https://leetcode.com/problems/minimum-total-price-after-applying-discounts)

[中文文档](/solution/4000-4099/4014.Minimum%20Total%20Price%20After%20Applying%20Discounts/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>prices</code> and <code>discounts</code>.</p>

<p>The value <code>prices[i]</code> represents the price of the <code>i<sup>th</sup></code> item, and <code>discounts[j]</code> represents a discount percentage.</p>

<p>You may apply discounts subject to the following rules:</p>

<ul>
	<li>Each discount can be applied to <strong>at most</strong> one item.</li>
	<li>Each item can receive <strong>at most</strong> one discount.</li>
	<li>An item may also receive no discount.</li>
</ul>

<p>If a discount of <code>d</code> percent is applied to an item with price <code>p</code>, its final price becomes <code>(p * (100 - d)) / 100</code>. The final price is <strong>not</strong> rounded.</p>

<p>Return the <strong>minimum</strong> possible sum of final prices after assigning discounts optimally. Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [10,30,21], discounts = [50,60]</span></p>

<p><strong>Output:</strong> <span class="example-io">32.50000</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Apply <code>discounts[1] = 60</code> to <code>prices[1] = 30</code>, thus <code>30 * (100 - 60) / 100 = 12</code>.</li>
	<li>Apply <code>discounts[0] = 50</code> to <code>prices[2] = 21</code>, thus <code>21 * (100 - 50) / 100 = 10.5</code>.</li>
	<li><code>prices[0] = 10</code> receives no discount, so it stays 10.</li>
</ul>

<p>The total is <code>12 + 10.5 + 10 = 32.50000</code>, which is the minimum possible.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [100,70], discounts = [10,40,50]</span></p>

<p><strong>Output:</strong> <span class="example-io">92.00000</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Apply <code>discounts[2] = 50</code> to <code>prices[0] = 100</code>, thus <code>100 * (100 - 50) / 100 = 50</code>.</li>
	<li>Apply <code>discounts[1] = 40</code> to <code>prices[1] = 70</code>, thus <code>70 * (100 - 40) / 100 = 42</code>.</li>
</ul>

<p>The total is <code>50 + 42 = 92.00000</code>, which is the minimum possible.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [7,3,9], discounts = [100,100]</span></p>

<p><strong>Output:</strong> <span class="example-io">3.00000</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Apply <code>discounts[0] = 100</code> to <code>prices[2] = 9</code>, thus <code>9 * (100 - 100) / 100 = 0</code>.</li>
	<li>Apply <code>discounts[1] = 100</code> to <code>prices[0] = 7</code>, thus <code>7 * (100 - 100) / 100 = 0</code>.</li>
	<li><code>prices[1] = 3</code> receives no discount, so it stays 3.</li>
</ul>

<p>The total is <code>0 + 0 + 3 = 3.00000</code>, which is the minimum possible.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length, discounts.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= discounts[j] &lt;= 100</code></li>
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
