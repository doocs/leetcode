---
comments: true
difficulty: 困难
---

<!-- problem:start -->

# [4119. 有效整数中最少置位数目](https://leetcode.cn/problems/minimum-set-bits-in-a-valid-integer)

[English Version](/solution/4100-4199/4119.Minimum%20Set%20Bits%20in%20a%20Valid%20Integer/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code> 和一个整数 <code>k</code>。</p>

<p>令 <code>x<sub>1</sub>, x<sub>2</sub>, ..., x<sub>n-k+1</sub></code> 按从左到右的顺序，依次表示 <code>nums</code> 中每个长度为 <code>k</code> 的 <strong>子数组</strong> 的 <strong>按位与</strong> 结果。</p>

<p>如果对于每个 <code>1 &lt;= i &lt;= n - k + 1</code>，都有 <code>(y &amp; x<sub>i</sub>) != 0</code>，则称正整数 <code>y</code> 是 <strong>有效的</strong>。</p>

<p>返回所有 <strong>有效</strong> 整数 <code>y</code> 中 <strong>置位</strong> 数量的 <strong>最小值</strong>。如果不存在有效的整数，返回 -1。</p>

<p><strong>置位</strong> 是指一个数的二进制表示中值为 1 的位。</p>

<p><strong>子数组</strong> 是数组中连续的 <b>非空</b> 元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [6,7,3], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>长度为 <code>k = 2</code> 的两个子数组为 <code>[6, 7]</code> 和 <code>[7, 3]</code>。</li>
	<li>它们的按位与结果为 <code>x<sub>1</sub> = 6 &amp; 7 = 6</code> 和 <code>x<sub>2</sub> = 7 &amp; 3 = 3</code>。</li>
	<li><code>y = 2</code> 是有效的，因为 <code>2 &amp; 6 = 2</code> 且 <code>2 &amp; 3 = 2</code>，均不为零。</li>
	<li><code>y = 2</code> 有 1 个置位（<code>"010"</code>），因此答案为 1。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,5,12], k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>由于 <code>k = 1</code>，每个子数组只有一个元素，因此 <code>x<sub>1</sub> = 3</code>，<code>x<sub>2</sub> = 5</code>，<code>x<sub>3</sub> = 12</code>。</li>
	<li><code>y = 9</code> 是有效的，因为 <code>9 &amp; 3 = 1</code>，<code>9 &amp; 5 = 1</code>，且 <code>9 &amp; 12 = 8</code>，均不为零。</li>
	<li><code>y = 9</code> 有 2 个置位（<code>"1001"</code>），因此答案为 2。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,1], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>长度为 <code>k = 2</code> 的唯一子数组为 <code>[2, 1]</code>，其按位与结果为 <code>x<sub>1</sub> = 2 &amp; 1 = 0</code>。</li>
	<li>由于 <code>x<sub>1</sub> = 0</code>，不存在有效的 <code>y</code>。因此，答案为 -1。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
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
