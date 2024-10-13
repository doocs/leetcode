---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3317.Find%20the%20Number%20of%20Possible%20Ways%20for%20an%20Event/README_EN.md
---

<!-- problem:start -->

# [3317. Find the Number of Possible Ways for an Event](https://leetcode.com/problems/find-the-number-of-possible-ways-for-an-event)

[中文文档](/solution/3300-3399/3317.Find%20the%20Number%20of%20Possible%20Ways%20for%20an%20Event/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>n</code>, <code>x</code>, and <code>y</code>.</p>

<p>An event is being held for <code>n</code> performers. When a performer arrives, they are <strong>assigned</strong> to one of the <code>x</code> stages. All performers assigned to the <strong>same</strong> stage will perform together as a band, though some stages <em>might</em> remain <strong>empty</strong>.</p>

<p>After all performances are completed, the jury will <strong>award</strong> each band a score in the range <code>[1, y]</code>.</p>

<p>Return the <strong>total</strong> number of possible ways the event can take place.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note</strong> that two events are considered to have been held <strong>differently</strong> if <strong>either</strong> of the following conditions is satisfied:</p>

<ul>
	<li><strong>Any</strong> performer is <em>assigned</em> a different stage.</li>
	<li><strong>Any</strong> band is <em>awarded</em> a different score.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1, x = 2, y = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>There are 2 ways to assign a stage to the performer.</li>
	<li>The jury can award a score of either 1, 2, or 3 to the only band.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, x = 2, y = 1</span></p>

<p><strong>Output:</strong> 32</p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Each performer will be assigned either stage 1 or stage 2.</li>
	<li>All bands will be awarded a score of 1.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, x = 3, y = 4</span></p>

<p><strong>Output:</strong> 684</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, x, y &lt;= 1000</code></li>
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
