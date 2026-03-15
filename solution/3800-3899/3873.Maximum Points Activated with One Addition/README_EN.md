---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3873.Maximum%20Points%20Activated%20with%20One%20Addition/README_EN.md
---

<!-- problem:start -->

# [3873. Maximum Points Activated with One Addition](https://leetcode.com/problems/maximum-points-activated-with-one-addition)

[中文文档](/solution/3800-3899/3873.Maximum%20Points%20Activated%20with%20One%20Addition/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>points</code>, where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents the coordinates of the <code>i<sup>th</sup></code> point. All coordinates in <code>points</code> are <strong>distinct</strong>.</p>

<p>If a point is <strong>activated</strong>, then all points that have the <strong>same</strong> x-coordinate <strong>or</strong> y-coordinate become <strong>activated</strong> as well.</p>

<p>Activation continues until no additional points can be activated.</p>

<p>You may add <strong>one additional</strong> point at any integer coordinate <code>(x, y)</code> not already present in <code>points</code>. Activation begins by <strong>activating</strong> this <strong>newly added point</strong>.</p>

<p>Return an integer denoting the <strong>maximum</strong> number of points that can be activated, including the newly added point.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [[1,1],[1,2],[2,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Adding and activating a point such as <code>(1, 3)</code> causes activations:</p>

<ul>
	<li><code>(1, 3)</code> shares <code>x = 1</code> with <code>(1, 1)</code> and <code>(1, 2)</code> -&gt; <code>(1, 1)</code> and <code>(1, 2)</code> become activated.</li>
	<li><code>(1, 2)</code> shares <code>y = 2</code> with <code>(2, 2)</code> -&gt; <code>(2, 2)</code> becomes activated.</li>
</ul>

<p>Thus, the activated points are <code>(1, 3)</code>, <code>(1, 1)</code>, <code>(1, 2)</code>, <code>(2, 2)</code>, so 4 points in total. We can show this is the maximum activated.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [[2,2],[1,1],[3,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Adding and activating a point such as <code>(1, 2)</code> causes activations:</p>

<ul>
	<li><code>(1, 2)</code> shares <code>x = 1</code> with <code>(1, 1)</code> -&gt; <code>(1, 1)</code> becomes activated.</li>
	<li><code>(1, 2)</code> shares <code>y = 2</code> with <code>(2, 2)</code> -&gt; <code>(2, 2)</code> becomes activated.</li>
</ul>

<p>Thus, the activated points are <code>(1, 2)</code>, <code>(1, 1)</code>, <code>(2, 2)</code>, so 3 points in total. We can show this is the maximum activated.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [[2,3],[2,2],[1,1],[4,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Adding and activating a point such as <code>(2, 1)</code> causes activations:</p>

<ul>
	<li><code>(2, 1)</code> shares <code>x = 2</code> with <code>(2, 3)</code> and <code>(2, 2)</code> -&gt; <code>(2, 3)</code> and <code>(2, 2)</code> become activated.</li>
	<li><code>(2, 1)</code> shares <code>y = 1</code> with <code>(1, 1)</code> -&gt; <code>(1, 1)</code> becomes activated.</li>
</ul>

<p>Thus, the activated points are <code>(2, 1)</code>, <code>(2, 3)</code>, <code>(2, 2)</code>, <code>(1, 1)</code>, so 4 points in total.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 10<sup>5</sup></code></li>
	<li><code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code></li>
	<li><code>-10<sup>9</sup> &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>points</code> contains all <strong>distinct</strong> coordinates.</li>
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
