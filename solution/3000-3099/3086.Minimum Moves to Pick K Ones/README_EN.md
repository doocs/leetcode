# [3086. Minimum Moves to Pick K Ones](https://leetcode.com/problems/minimum-moves-to-pick-k-ones)

[中文文档](/solution/3000-3099/3086.Minimum%20Moves%20to%20Pick%20K%20Ones/README.md)

<!-- tags:Greedy,Array,Prefix Sum,Sliding Window -->

<!-- difficulty:Hard -->

## Description

<p>You are given a binary array <code>nums</code> of length <code>n</code>, a <strong>positive</strong> integer <code>k</code> and a <strong>non-negative</strong> integer <code>maxChanges</code>.</p>

<p>Alice plays a game, where the goal is for Alice to pick up <code>k</code> ones from <code>nums</code> using the <strong>minimum</strong> number of <strong>moves</strong>. When the game starts, Alice picks up any index <code>aliceIndex</code> in the range <code>[0, n - 1]</code> and stands there. If <code>nums[aliceIndex] == 1</code> , Alice picks up the one and <code>nums[aliceIndex]</code> becomes <code>0</code>(this <strong>does not</strong> count as a move). After this, Alice can make <strong>any</strong> number of <strong>moves</strong> (<strong>including</strong> <strong>zero</strong>) where in each move Alice must perform <strong>exactly</strong> one of the following actions:</p>

<ul>
	<li>Select any index <code>j != aliceIndex</code> such that <code>nums[j] == 0</code> and set <code>nums[j] = 1</code>. This action can be performed <strong>at</strong> <strong>most</strong> <code>maxChanges</code> times.</li>
	<li>Select any two adjacent indices <code>x</code> and <code>y</code> (<code>|x - y| == 1</code>) such that <code>nums[x] == 1</code>, <code>nums[y] == 0</code>, then swap their values (set <code>nums[y] = 1</code> and <code>nums[x] = 0</code>). If <code>y == aliceIndex</code>, Alice picks up the one after this move and <code>nums[y]</code> becomes <code>0</code>.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of moves required by Alice to pick <strong>exactly </strong></em><code>k</code> <em>ones</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">nums = [1,1,0,0,0,1,1,0,0,1], k = 3, maxChanges = 1</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">3</span></p>

<p><strong>Explanation:</strong> Alice can pick up <code>3</code> ones in <code>3</code> moves, if Alice performs the following actions in each move when standing at <code>aliceIndex == 1</code>:</p>

<ul>
	<li>&nbsp;At the start of the game Alice picks up the one and <code>nums[1]</code> becomes <code>0</code>. <code>nums</code> becomes <code>[1,<strong><u>1</u></strong>,1,0,0,1,1,0,0,1]</code>.</li>
	<li>Select <code>j == 2</code> and perform an action of the first type. <code>nums</code> becomes <code>[1,<strong><u>0</u></strong>,1,0,0,1,1,0,0,1]</code></li>
	<li>Select <code>x == 2</code> and <code>y == 1</code>, and perform an action of the second type. <code>nums</code> becomes <code>[1,<strong><u>1</u></strong>,0,0,0,1,1,0,0,1]</code>. As <code>y == aliceIndex</code>, Alice picks up the one and <code>nums</code> becomes&nbsp;<code>[1,<strong><u>0</u></strong>,0,0,0,1,1,0,0,1]</code>.</li>
	<li>Select <code>x == 0</code> and <code>y == 1</code>, and perform an action of the second type. <code>nums</code> becomes <code>[0,<strong><u>1</u></strong>,0,0,0,1,1,0,0,1]</code>. As <code>y == aliceIndex</code>, Alice picks up the one and <code>nums</code> becomes&nbsp;<code>[0,<strong><u>0</u></strong>,0,0,0,1,1,0,0,1]</code>.</li>
</ul>

<p>Note that it may be possible for Alice to pick up <code>3</code> ones using some other sequence of <code>3</code> moves.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">nums = [0,0,0,0], k = 2, maxChanges = 3</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">4</span></p>

<p><strong>Explanation:</strong> Alice can pick up <code>2</code> ones in <code>4</code> moves, if Alice performs the following actions in each move when standing at <code>aliceIndex == 0</code>:</p>

<ul>
	<li>Select <code>j == 1</code> and perform an action of the first type. <code>nums</code> becomes <code>[<strong><u>0</u></strong>,1,0,0]</code>.</li>
	<li>Select <code>x == 1</code> and <code>y == 0</code>, and perform an action of the second type. <code>nums</code> becomes <code>[<strong><u>1</u></strong>,0,0,0]</code>. As <code>y == aliceIndex</code>, Alice picks up the one and <code>nums</code> becomes&nbsp;<code>[<strong><u>0</u></strong>,0,0,0]</code>.</li>
	<li>Select <code>j == 1</code> again and perform an action of the first type. <code>nums</code> becomes <code>[<strong><u>0</u></strong>,1,0,0]</code>.</li>
	<li>Select <code>x == 1</code> and <code>y == 0</code> again, and perform an action of the second type. <code>nums</code> becomes <code>[<strong><u>1</u></strong>,0,0,0]</code>. As <code>y == aliceIndex</code>, Alice picks up the one and <code>nums</code> becomes&nbsp;<code>[<strong><u>0</u></strong>,0,0,0]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= maxChanges &lt;= 10<sup>5</sup></code></li>
	<li><code>maxChanges + sum(nums) &gt;= k</code></li>
</ul>

## Solutions

### Solution 1

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
