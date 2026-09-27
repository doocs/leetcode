---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4062. Transform Array Using Pair Operations](https://leetcode.com/problems/transform-array-using-pair-operations)

[中文文档](/solution/4000-4099/4062.Transform%20Array%20Using%20Pair%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>source</code> and <code>target</code>.</p>

<p>In one <strong>operation</strong>, you may choose two <strong>distinct</strong> indices <code>i</code> and <code>j</code> in <code>source</code>, along with any integer <code>delta</code>. Then update <code>source</code> as follows:</p>

<ul>
	<li><code>source[i] = source[i] + source[j] - delta</code></li>
	<li><code>source[j] = delta</code></li>
</ul>

<p>Return <code>true</code> if it is possible to make <code>source</code> equal to <code>target</code> after performing the operation <strong>any</strong> (including zero) number of times. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = [1,2,3], target = [0,2,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose indices <code>i = 0</code> and <code>j = 2</code>, and set <code>delta = 4</code>.</li>
	<li>Before operation, <code>source[0] = 1</code> and <code>source[2] = 3</code>.</li>
	<li>After the operation,
	<ul>
		<li><code>source[0] = 1 + 3 - 4 = 0</code></li>
		<li><code>source[2] = 4</code></li>
	</ul>
	</li>
	<li>Hence, <code>source</code> becomes <code>[0, 2, 4]</code>, which is equal to <code>target</code>.</li>
	<li>Therefore, the answer is <code>true</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = [-5,-5], target = [-15,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose indices <code>i = 1</code> and <code>j = 0</code>, and set <code>delta = -15</code>.</li>
	<li>Before operation, <code>source[1] = -5</code> and <code>source[0] = -5</code>.</li>
	<li>After the operation,
	<ul>
		<li><code>source[1] = -5 + (-5) - (-15) = 5</code></li>
		<li><code>source[0] = -15</code></li>
	</ul>
	</li>
	<li>Hence, <code>source</code> becomes <code>[-15, 5]</code>, which is equal to <code>target</code>.</li>
	<li>Therefore, the answer is <code>true</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = [1,2,1], target = [0,2,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>It can be shown that no matter what operations are performed, <code>source</code> can never be made equal to <code>target</code>. Therefore, the answer is <code>false</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= source.length == target.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= source[i], target[i] &lt;= 10<sup>9</sup></code></li>
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
