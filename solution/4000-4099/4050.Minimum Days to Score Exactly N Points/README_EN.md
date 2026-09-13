---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4050. Minimum Days to Score Exactly N Points](https://leetcode.com/problems/minimum-days-to-score-exactly-n-points)

[中文文档](/solution/4000-4099/4050.Minimum%20Days%20to%20Score%20Exactly%20N%20Points/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> representing a target score.</p>

<p>Your score starts at 0, and each day you either <strong>earn</strong> points or <strong>skip</strong>.</p>

<p>Points are earned during a streak. On the first day of a streak you earn 1 point, on the second day 2 points, on the third day 3 points, and so on. <strong>Skipping</strong> a day earns <strong>nothing</strong> and <strong>resets</strong> the streak, so the next time you earn points, you start from 1 again.</p>

<p>Return the <strong>minimum</strong> number of days, including any skipped days, needed to reach a score of <strong>exactly</strong> <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Day 1: earn 1 point. Score is 1.</li>
	<li>Day 2: skip, which resets the streak. Earning here would add 2 points and take the score past <code>n = 2</code>.</li>
	<li>Day 3: the streak has reset, so earning gives 1 point. Score is exactly <code>n = 2</code> in 3 days.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 9</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Days 1 to 3: earn 1, 2, and 3 points. Score is <code>1 + 2 + 3 = 6</code>.</li>
	<li>Day 4: skip, which resets the streak.</li>
	<li>Days 5 and 6: earn 1 and 2 points. Score is exactly <code>6 + 1 + 2 = 9</code> in 6 days.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 12</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Days 1 to 3: earn 1, 2, and 3 points. Score is <code>1 + 2 + 3 = 6</code>.</li>
	<li>Day 4: skip, which resets the streak.</li>
	<li>Days 5 to 7: earn 1, 2, and 3 points. Score is exactly <code>6 + 1 + 2 + 3 = 12</code> in 7 days.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
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
