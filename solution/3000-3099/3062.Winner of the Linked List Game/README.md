# [3062. Winner of the Linked List Game](https://leetcode.cn/problems/winner-of-the-linked-list-game)

[English Version](/solution/3000-3099/3062.Winner%20of%20the%20Linked%20List%20Game/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given the <code>head</code> of a linked list of <strong>even</strong> length containing integers.</p>

<p>Each <strong>odd-indexed</strong> node contains an odd integer and each <strong>even-indexed</strong> node contains an even integer.</p>

<p>We call each even-indexed node and its next node a <strong>pair</strong>, e.g., the nodes with indices <code>0</code> and <code>1</code> are a pair, the nodes with indices <code>2</code> and <code>3</code> are a pair, and so on.</p>

<p>For every <strong>pair</strong>, we compare the values of the nodes in the pair:</p>

<ul>
	<li>If the odd-indexed node is higher, the <code>&quot;Odd&quot;</code> team gets a point.</li>
	<li>If the even-indexed node is higher, the <code>&quot;Even&quot;</code> team gets a point.</li>
</ul>

<p>Return <em>the name of the team with the <strong>higher</strong> points, if the points are equal, return</em> <code>&quot;Tie&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> head = [2,1] </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> &quot;Even&quot; </span></p>

<p><strong>Explanation: </strong> There is only one pair in this linked list and that is <code>(2,1)</code>. Since <code>2 &gt; 1</code>, the Even team gets the point.</p>

<p>Hence, the answer would be <code>&quot;Even&quot;</code>.</p>
</div>

<p><strong class="example">Example 2: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> head = [2,5,4,7,20,5] </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> &quot;Odd&quot; </span></p>

<p><strong>Explanation: </strong> There are <code>3</code> pairs in this linked list. Let&#39;s investigate each pair individually:</p>

<p><code>(2,5)</code> -&gt; Since <code>2 &lt; 5</code>, The Odd team gets the point.</p>

<p><code>(4,7)</code> -&gt; Since <code>4 &lt; 7</code>, The Odd team gets the point.</p>

<p><code>(20,5)</code> -&gt; Since <code>20 &gt; 5</code>, The Even team gets the point.</p>

<p>The Odd team earned <code>2</code> points while the Even team got <code>1</code> point and the Odd team has the higher points.</p>

<p>Hence, the answer would be <code>&quot;Odd&quot;</code>.</p>
</div>

<p><strong class="example">Example 3: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> head = [4,5,2,1] </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> &quot;Tie&quot; </span></p>

<p><strong>Explanation: </strong> There are <code>2</code> pairs in this linked list. Let&#39;s investigate each pair individually:</p>

<p><code>(4,5)</code> -&gt; Since <code>4 &lt; 5</code>, the Odd team gets the point.</p>

<p><code>(2,1)</code> -&gt; Since <code>2 &gt; 1</code>, the Even team gets the point.</p>

<p>Both teams earned <code>1</code> point.</p>

<p>Hence, the answer would be <code>&quot;Tie&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[2, 100]</code>.</li>
	<li>The number of nodes in the list is even.</li>
	<li><code>1 &lt;= Node.val &lt;= 100</code></li>
	<li>The value of each odd-indexed node is odd.</li>
	<li>The value of each even-indexed node is even.</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
