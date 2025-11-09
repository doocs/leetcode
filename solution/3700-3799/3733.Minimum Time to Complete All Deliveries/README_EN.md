---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3733.Minimum%20Time%20to%20Complete%20All%20Deliveries/README_EN.md
rating: 1972
source: Weekly Contest 474 Q3
---

<!-- problem:start -->

# [3733. Minimum Time to Complete All Deliveries](https://leetcode.com/problems/minimum-time-to-complete-all-deliveries)

[中文文档](/solution/3700-3799/3733.Minimum%20Time%20to%20Complete%20All%20Deliveries/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays of size 2: <code>d = [d<sub>1</sub>, d<sub>2</sub>]</code> and <code>r = [r<sub>1</sub>, r<sub>2</sub>]</code>.</p>

<p>Two delivery drones are tasked with completing a specific number of deliveries. Drone <code>i</code> must complete <code>d<sub>i</sub></code> deliveries.</p>

<p>Each delivery takes <strong>exactly</strong> one hour and <strong>only one</strong> drone can make a delivery at any given hour.</p>

<p>Additionally, both drones require recharging at specific intervals during which they cannot make deliveries. Drone <code>i</code> must recharge every <code>r<sub>i</sub></code> hours (i.e. at hours that are multiples of <code>r<sub>i</sub></code>).</p>

<p>Return an integer denoting the <strong>minimum</strong> total time (in hours) required to complete all deliveries.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">d = [3,1], r = [2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The first drone delivers at hours 1, 3, 5 (recharges at hours 2, 4).</li>
	<li>The second drone delivers at hour 2 (recharges at hour 3).</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">d = [1,3], r = [2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The first drone delivers at hour 3 (recharges at hours 2, 4, 6).</li>
	<li>The second drone delivers at hours 1, 5, 7 (recharges at hours 2, 4, 6).</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">d = [2,1], r = [3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The first drone delivers at hours 1, 2 (recharges at hour 3).</li>
	<li>The second drone delivers at hour 3.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>d = [d<sub>1</sub>, d<sub>2</sub>]</code></li>
	<li><code>1 &lt;= d<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>r = [r<sub>1</sub>, r<sub>2</sub>]</code></li>
	<li><code>2 &lt;= r<sub>i</sub> &lt;= 3 * 10<sup>4</sup></code></li>
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
