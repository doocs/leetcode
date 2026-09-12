---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3802.Number%20of%20Ways%20to%20Paint%20Sheets/README_EN.md
---

<!-- problem:start -->

# [3802. Number of Ways to Paint Sheets 🔒](https://leetcode.com/problems/number-of-ways-to-paint-sheets)

[中文文档](/solution/3800-3899/3802.Number%20of%20Ways%20to%20Paint%20Sheets/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> representing the number of sheets.</p>

<p>You are also given an integer array <code>limit</code> of size <code>m</code>, where <code>limit[i]</code> is the <strong>maximum</strong> number of sheets that can be painted using color <code>i</code>.</p>

<p>You must paint <strong>all</strong> <code>n</code> sheets under the following conditions:</p>

<ul>
	<li><strong>Exactly two distinct</strong> colors are used.</li>
	<li>Each color must cover a <strong>single contiguous</strong> segment of sheets.</li>
	<li>The number of sheets painted with color <code>i</code> cannot exceed <code>limit[i]</code>.</li>
</ul>

<p>Return an integer denoting the number of <strong>distinct</strong> ways to paint all sheets. Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note:</strong> Two ways differ if <strong>at least</strong> one sheet is painted with a different color.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, limit = [3,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>
For each ordered pair <code>(i, j)</code>, where color <code>i</code> is used for the first segment and color <code>j</code> for the second segment (<code>i != j</code>), a split of <code>x</code> and <code>4 - x</code> is valid if <code>1 &lt;= x &lt;= limit[i]</code> and <code>1 &lt;= 4 - x &lt;= limit[j]</code>.

<p>Valid pairs and counts are:</p>

<ul>
	<li><code>(0, 1): x = 3</code></li>
	<li><code>(0, 2): x = 2, 3</code></li>
	<li><code>(1, 0): x = 1</code></li>
	<li><code>(2, 0): x = 1, 2</code></li>
</ul>

<p>Therefore, there are 6 valid ways in total.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, limit = [1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>For each ordered pair <code>(i, j)</code>, where color <code>i</code> is used for the first segment and color <code>j</code> for the second segment (<code>i != j</code>), a split of <code>x</code> and <code>3 - x</code> is valid if <code>1 &lt;= x &lt;= limit[i]</code> and <code>1 &lt;= 3 - x &lt;= limit[j]</code>.</p>

<p>Valid pairs and counts are:</p>

<ul>
	<li><code>(0, 1): x = 1</code></li>
	<li><code>(1, 0): x = 2</code></li>
</ul>

<p>Hence, there are 2 valid ways in total.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, limit = [2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>For each ordered pair <code>(i, j)</code>, where color <code>i</code> is used for the first segment and color <code>j</code> for the second segment (<code>i != j</code>), a split of <code>x</code> and <code>3 - x</code> is valid if <code>1 &lt;= x &lt;= limit[i]</code> and <code>1 &lt;= 3 - x &lt;= limit[j]</code>.</p>

<p>Valid pairs and counts are:</p>

<ul>
	<li><code>(0, 1): x = 1, 2</code></li>
	<li><code>(1, 0): x = 1, 2</code></li>
</ul>

<p>Therefore, there are 4 valid ways in total.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>2 &lt;= m == limit.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= limit[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> We must use exactly two colors, each on a contiguous block, without exceeding $\textit{limit}$. $n \le 10^9$ forbids enumerating cut positions sheet by sheet.
>
> For an ordered pair $(i,j)$, valid cuts $x$ form an integer interval determined by $n$ and the two limits, whose length is $O(1)$.
>
> With $m \le 10^5$, pairing every two colors is $O(m^2)$ even after the cut is closed-form. The bottleneck is counting many limits at once.
>
> Each color's contribution depends only on how its limit sits relative to $n$. Sorting $\textit{limit}$ and accumulating interval lengths with prefix sums yields the answer modulo $10^9+7$.

<!-- thinking:end -->
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
