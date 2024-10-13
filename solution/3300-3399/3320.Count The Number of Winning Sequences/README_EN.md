---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3320.Count%20The%20Number%20of%20Winning%20Sequences/README_EN.md
---

<!-- problem:start -->

# [3320. Count The Number of Winning Sequences](https://leetcode.com/problems/count-the-number-of-winning-sequences)

[中文文档](/solution/3300-3399/3320.Count%20The%20Number%20of%20Winning%20Sequences/README.md)

## Description

<!-- description:start -->

<p>Alice and Bob are playing a fantasy battle game consisting of <code>n</code> rounds where they summon one of three magical creatures each round: a Fire Dragon, a Water Serpent, or an Earth Golem. In each round, players <strong>simultaneously</strong> summon their creature and are awarded points as follows:</p>

<ul>
	<li>If one player summons a Fire Dragon and the other summons an Earth Golem, the player who summoned the <strong>Fire Dragon</strong> is awarded a point.</li>
	<li>If one player summons a Water Serpent and the other summons a Fire Dragon, the player who summoned the <strong>Water Serpent</strong> is awarded a point.</li>
	<li>If one player summons an Earth Golem and the other summons a Water Serpent, the player who summoned the <strong>Earth Golem</strong> is awarded a point.</li>
	<li>If both players summon the same creature, no player is awarded a point.</li>
</ul>

<p>You are given a string <code>s</code> consisting of <code>n</code> characters <code>&#39;F&#39;</code>, <code>&#39;W&#39;</code>, and <code>&#39;E&#39;</code>, representing the sequence of creatures Alice will summon in each round:</p>

<ul>
	<li>If <code>s[i] == &#39;F&#39;</code>, Alice summons a Fire Dragon.</li>
	<li>If <code>s[i] == &#39;W&#39;</code>, Alice summons a Water Serpent.</li>
	<li>If <code>s[i] == &#39;E&#39;</code>, Alice summons an Earth Golem.</li>
</ul>

<p>Bob&rsquo;s sequence of moves is unknown, but it is guaranteed that Bob will never summon the same creature in two consecutive rounds. Bob <em>beats</em> Alice if the total number of points awarded to Bob after <code>n</code> rounds is <strong>strictly greater</strong> than the points awarded to Alice.</p>

<p>Return the number of distinct sequences Bob can use to beat Alice.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;FFF&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Bob can beat Alice by making one of the following sequences of moves: <code>&quot;WFW&quot;</code>, <code>&quot;FWF&quot;</code>, or <code>&quot;WEW&quot;</code>. Note that other winning sequences like <code>&quot;WWE&quot;</code> or <code>&quot;EWW&quot;</code> are invalid since Bob cannot make the same move twice in a row.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;FWEFW&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">18</span></p>

<p><strong>Explanation:</strong></p>

<p><w>Bob can beat Alice by making one of the following sequences of moves: <code>&quot;FWFWF&quot;</code>, <code>&quot;FWFWE&quot;</code>, <code>&quot;FWEFE&quot;</code>, <code>&quot;FWEWE&quot;</code>, <code>&quot;FEFWF&quot;</code>, <code>&quot;FEFWE&quot;</code>, <code>&quot;FEFEW&quot;</code>, <code>&quot;FEWFE&quot;</code>, <code>&quot;WFEFE&quot;</code>, <code>&quot;WFEWE&quot;</code>, <code>&quot;WEFWF&quot;</code>, <code>&quot;WEFWE&quot;</code>, <code>&quot;WEFEF&quot;</code>, <code>&quot;WEFEW&quot;</code>, <code>&quot;WEWFW&quot;</code>, <code>&quot;WEWFE&quot;</code>, <code>&quot;EWFWE&quot;</code>, or <code>&quot;EWEWE&quot;</code>.</w></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> is either <code>&#39;F&#39;</code>, <code>&#39;W&#39;</code>, or <code>&#39;E&#39;</code>.</li>
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
