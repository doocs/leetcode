---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3802.Number%20of%20Ways%20to%20Paint%20Sheets/README.md
---

<!-- problem:start -->

# [3802. 给纸张涂色的方式数量 🔒](https://leetcode.cn/problems/number-of-ways-to-paint-sheets)

[English Version](/solution/3800-3899/3802.Number%20of%20Ways%20to%20Paint%20Sheets/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数&nbsp;<code>n</code>&nbsp;表示纸张的数量。</p>

<p>同时给定一个长度为&nbsp;<code>m</code>&nbsp;的整数数组&nbsp;<code>limit</code>，其中&nbsp;<code>limit[i]</code> 是使用颜色 <code>i</code>&nbsp;能够涂色的最大纸张数。</p>

<p>你必须在下列条件下给 <strong>所有</strong>&nbsp;<code>n</code>&nbsp;张纸涂色：</p>

<ul>
	<li><strong>恰好使用两种不同</strong>&nbsp;颜色。</li>
	<li>每种颜色必须覆盖 <strong>连续的一段</strong> 纸张。</li>
	<li>用颜色 <code>i</code> 涂的纸张数量不能超过 <code>limit[i]</code>。</li>
</ul>

<p>返回一个整数表示给所有纸张染色的 <strong>不同</strong>&nbsp;方式数量。由于答案可能很大，返回答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;取模的结果。</p>

<p><strong>注意：</strong>如果 <strong>至少</strong> 有一张纸涂上了不同的颜色，就是不同的两种方式。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 4, limit = [3,1,2]</span></p>

<p><span class="example-io"><b>输出：</b>6</span></p>

<p><b>解释：</b></p>
对于每个有序数对&nbsp;<code>(i, j)</code>，其中颜色&nbsp;<code>i</code>&nbsp;被用于给第一段涂色，颜色&nbsp;<code>j</code>&nbsp;被用于给第二段涂色（<code>i != j</code>），<code>x</code> 和&nbsp;<code>4 - x</code>&nbsp;的分割是有效的，当且仅当&nbsp;<code>1 &lt;= x &lt;= limit[i]</code> 且&nbsp;<code>1 &lt;= 4 - x &lt;= limit[j]</code>。

<p>合法的数对以及数量是：</p>

<ul>
	<li><code>(0, 1): x = 3</code></li>
	<li><code>(0, 2): x = 2, 3</code></li>
	<li><code>(1, 0): x = 1</code></li>
	<li><code>(2, 0): x = 1, 2</code></li>
</ul>

<p>因此，总共有 6 种有效的方式。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 3, limit = [1,2]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>对于每个有序数对&nbsp;<code>(i, j)</code>，其中颜色&nbsp;<code>i</code>&nbsp;被用于给第一段涂色，颜色&nbsp;<code>j</code>&nbsp;被用于给第二段涂色（<code>i != j</code>），<code>x</code> 和 <code>3 - x</code>&nbsp;的分割是有效的，当且仅当&nbsp;<code>1 &lt;= x &lt;= limit[i]</code> 且&nbsp;<code>1 &lt;= 3 - x &lt;= limit[j]</code>。</p>

<p>合法的数对和数量是：</p>

<ul>
	<li><code>(0, 1): x = 1</code></li>
	<li><code>(1, 0): x = 2</code></li>
</ul>

<p>因此，总共有 2 种合法的方式。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 3, limit = [2,2]</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>对于每个有序数对&nbsp;<code>(i, j)</code>，其中颜色&nbsp;<code>i</code>&nbsp;被用于给第一段涂色，颜色&nbsp;<code>j</code>&nbsp;被用于给第二段涂色（<code>i != j</code>），<code>x</code> 和 <code>3 - x</code>&nbsp;的分割是有效的，当且仅当&nbsp;<code>1 &lt;= x &lt;= limit[i]</code> 且&nbsp;<code>1 &lt;= 3 - x &lt;= limit[j]</code>。</p>

<p>合法的数对和数量是：</p>

<ul>
	<li><code>(0, 1): x = 1, 2</code></li>
	<li><code>(1, 0): x = 1, 2</code></li>
</ul>

<p>因此，总共有 4 种合法的方式。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>2 &lt;= m == limit.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= limit[i] &lt;= 10<sup>9</sup></code></li>
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
