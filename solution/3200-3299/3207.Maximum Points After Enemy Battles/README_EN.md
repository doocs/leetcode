---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3207.Maximum%20Points%20After%20Enemy%20Battles/README_EN.md
---

<!-- problem:start -->

# [3207. Maximum Points After Enemy Battles](https://leetcode.com/problems/maximum-points-after-enemy-battles)

[中文文档](/solution/3200-3299/3207.Maximum%20Points%20After%20Enemy%20Battles/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>enemyEnergies</code> denoting the energy values of various enemies.</p>

<p>You are also given an integer <code>currentEnergy</code> denoting the amount of energy you have initially.</p>

<p>You start with 0 points, and all the enemies are unmarked initially.</p>

<p>You can perform <strong>either</strong> of the following operations <strong>zero </strong>or multiple times to gain points:</p>

<ul>
	<li>Choose an <strong>unmarked</strong> enemy, <code>i</code>, such that <code>currentEnergy &gt;= enemyEnergies[i]</code>. By choosing this option:

    <ul>
    	<li>You gain 1 point.</li>
    	<li>Your energy is reduced by the enemy&#39;s energy, i.e. <code>currentEnergy = currentEnergy - enemyEnergies[i]</code>.</li>
    </ul>
    </li>
    <li>If you have <strong>at least</strong> 1 point, you can choose an <strong>unmarked</strong> enemy, <code>i</code>. By choosing this option:
    <ul>
    	<li>Your energy increases by the enemy&#39;s energy, i.e. <code>currentEnergy = currentEnergy + enemyEnergies[i]</code>.</li>
    	<li>The <font face="monospace">e</font>nemy <code>i</code> is <strong>marked</strong>.</li>
    </ul>
    </li>

</ul>

<p>Return an integer denoting the <strong>maximum</strong> points you can get in the end by optimally performing operations.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">enemyEnergies = [3,2,2], currentEnergy = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The following operations can be performed to get 3 points, which is the maximum:</p>

<ul>
	<li>First operation on enemy 1: <code>points</code> increases by 1, and <code>currentEnergy</code> decreases by 2. So, <code>points = 1</code>, and <code>currentEnergy = 0</code>.</li>
	<li>Second operation on enemy 0: <code>currentEnergy</code> increases by 3, and enemy 0 is marked. So, <code>points = 1</code>, <code>currentEnergy = 3</code>, and marked enemies = <code>[0]</code>.</li>
	<li>First operation on enemy 2: <code>points</code> increases by 1, and <code>currentEnergy</code> decreases by 2. So, <code>points = 2</code>, <code>currentEnergy = 1</code>, and marked enemies = <code>[0]</code>.</li>
	<li>Second operation on enemy 2: <code>currentEnergy</code> increases by 2, and enemy 2 is marked. So, <code>points = 2</code>, <code>currentEnergy = 3</code>, and marked enemies = <code>[0, 2]</code>.</li>
	<li>First operation on enemy 1: <code>points</code> increases by 1, and <code>currentEnergy</code> decreases by 2. So, <code>points = 3</code>, <code>currentEnergy = 1</code>, and marked enemies = <code>[0, 2]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">enemyEnergies = </span>[2]<span class="example-io">, currentEnergy = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation: </strong></p>

<p>Performing the first operation 5 times on enemy 0 results in the maximum number of points.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= enemyEnergies.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= enemyEnergies[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= currentEnergy &lt;= 10<sup>9</sup></code></li>
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
