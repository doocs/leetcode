---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3771.Total%20Score%20of%20Dungeon%20Runs/README_EN.md
rating: 1981
source: Weekly Contest 479 Q3
tags:
    - Array
    - Binary Search
    - Prefix Sum
---

<!-- problem:start -->

# [3771. Total Score of Dungeon Runs](https://leetcode.com/problems/total-score-of-dungeon-runs)

[中文文档](/solution/3700-3799/3771.Total%20Score%20of%20Dungeon%20Runs/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>positive</strong> integer <code>hp</code> and two <strong>positive</strong> <strong>1-indexed</strong> integer arrays <code>damage</code> and <code>requirement</code>.</p>

<p>There is a dungeon with <code>n</code> trap rooms numbered from 1 to <code>n</code>. Entering room <code>i</code> reduces your health points by <code>damage[i]</code>. After that reduction, if your remaining health points are <strong>at least</strong> <code>requirement[i]</code>, you earn <strong>1 point </strong>for that room.</p>

<p>Let <code>score(j)</code> be the number of <strong>points</strong> you get if you start with <code>hp</code> health points and enter the rooms <code>j</code>, <code>j + 1</code>, ..., <code>n</code> in this order.</p>

<p>Return the integer <code>score(1) + score(2) + ... + score(n)</code>, the sum of scores over all starting rooms.</p>

<p><strong>Note</strong>: You cannot skip rooms. You can finish your journey even if your health points become non-positive.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">hp = 11, damage = [3,6,7], requirement = [4,2,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><code>score(1) = 2</code>, <code>score(2) = 1</code>, <code>score(3) = 0</code>. The total score is <code>2 + 1 + 0 = 3</code>.</p>

<p>As an example, <code>score(1) = 2</code> because you get 2 points if you start from room 1.</p>

<ul>
	<li>You start with 11 health points.</li>
	<li>Enter room 1. Your health points are now <code>11 - 3 = 8</code>. You get 1 point because <code>8 &gt;= 4</code>.</li>
	<li>Enter room 2. Your health points are now <code>8 - 6 = 2</code>. You get 1 point because <code>2 &gt;= 2</code>.</li>
	<li>Enter room 3. Your health points are now <code>2 - 7 = -5</code>. You do not get any points because <code>-5 &lt; 5</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">hp = 2, damage = [10000,1], requirement = [1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><code>score(1) = 0</code>, <code>score(2) = 1</code>. The total score is <code>0 + 1 = 1</code>.</p>

<p><code>score(1) = 0</code> because you do not get any points if you start from room 1.</p>

<ul>
	<li>You start with 2 health points.</li>
	<li>Enter room 1. Your health points are now <code>2 - 10000 = -9998</code>. You do not get any points because <code>-9998 &lt; 1</code>.</li>
	<li>Enter room 2. Your health points are now <code>-9998 - 1 = -9999</code>. You do not get any points because <code>-9999 &lt; 1</code>.</li>
</ul>

<p><code>score(2) = 1</code> because you get 1 point if you start from room 2.</p>

<ul>
	<li>You start with 2 health points.</li>
	<li>Enter room 2. Your health points are now <code>2 - 1 = 1</code>. You get 1 point because <code>1 &gt;= 1</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= hp &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= n == damage.length == requirement.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= damage[i], requirement[i] &lt;= 10<sup>4</sup></code></li>
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
