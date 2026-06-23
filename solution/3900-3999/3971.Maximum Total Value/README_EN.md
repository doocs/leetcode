---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3971.Maximum%20Total%20Value/README_EN.md
---

<!-- problem:start -->

# [3971. Maximum Total Value](https://leetcode.com/problems/maximum-total-value)

[中文文档](/solution/3900-3999/3971.Maximum%20Total%20Value/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>value</code> and <code>decay</code>, and an integer <code>m</code>.</p>

<ul>
	<li><code>value[i]</code> represents the initial value at index <code>i</code>.</li>
	<li><code>decay[i]</code> represents how much the value decreases after each selection of index <code>i</code>.</li>
</ul>

<p>You may select any index <strong>multiple</strong> times. The total number of selections across all indices must not exceed <code>m</code>.</p>

<p>If you select index <code>i</code> for the <code>t<sup>th</sup></code> time, where <code>t</code> is 1-indexed, the value gained is <code>value[i] - decay[i] * (t - 1)</code>.</p>

<p>Return the <strong>maximum</strong> total value you can obtain. Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">value = [6,5,4], decay = [2,1,1], m = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">19</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal sequence of selections is as follows:</p>

<ul>
	<li>By selecting index 0, the value gained is 6.</li>
	<li>By selecting index 1, the value gained is 5.</li>
	<li>By selecting index 2, the value gained is 4.</li>
	<li>By selecting index 0 again, the value gained is <code>6 - 2 = 4</code>.</li>
</ul>

<p>The total value is <code>6 + 5 + 4 + 4 = 19</code>. No other sequence of at most 4 selections gives a higher total value.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">value = [7,2,2], decay = [3,2,1], m = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">11</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal sequence of selections is as follows:</p>

<ul>
	<li>By selecting index 0, the value gained is 7.</li>
	<li>By selecting index 0 again, the value gained is <code>7 - 3 = 4</code>.</li>
</ul>

<p>The total value is <code>7 + 4 = 11</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">value = [4,3], decay = [5,4], m = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal sequence of selections is as follows:</p>

<ul>
	<li>By selecting index 0, the value gained is 4.</li>
	<li>By selecting index 1, the value gained is 3.</li>
</ul>

<p>The total value is <code>4 + 3 = 7</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= value.length == decay.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= value[i], decay[i] &lt;= 10<sup>9</sup>​​​​​​​</code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>9</sup></code></li>
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
