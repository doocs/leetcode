---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3664.Two-Letter%20Card%20Game/README_EN.md
rating: 2157
source: Biweekly Contest 164 Q2
---

<!-- problem:start -->

# [3664. Two-Letter Card Game](https://leetcode.com/problems/two-letter-card-game)

[中文文档](/solution/3600-3699/3664.Two-Letter%20Card%20Game/README.md)

## Description

<!-- description:start -->

<p>You are given a deck of cards represented by a string array <code>cards</code>, and each card displays two lowercase letters.</p>

<p>You are also given a letter <code>x</code>. You play a game with the following rules:</p>

<ul>
	<li>Start with 0 points.</li>
	<li>On each turn, you must find two <strong>compatible</strong> cards from the deck that both contain the letter <code>x</code> in any position.</li>
	<li>Remove the pair of cards and earn <strong>1 point</strong>.</li>
	<li>The game ends when you can no longer find a pair of compatible cards.</li>
</ul>

<p>Return the <strong>maximum</strong> number of points you can gain with optimal play.</p>

<p>Two cards are <strong>compatible</strong> if the strings differ in <strong>exactly</strong> 1 position.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">cards = [&quot;aa&quot;,&quot;ab&quot;,&quot;ba&quot;,&quot;ac&quot;], x = &quot;a&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>On the first turn, select and remove cards <code>&quot;ab&quot;</code> and <code>&quot;ac&quot;</code>, which are compatible because they differ at only index 1.</li>
	<li>On the second turn, select and remove cards <code>&quot;aa&quot;</code> and <code>&quot;ba&quot;</code>, which are compatible because they differ at only index 0.</li>
</ul>

<p>Because there are no more compatible pairs, the total score is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">cards = [&quot;aa&quot;,&quot;ab&quot;,&quot;ba&quot;], x = &quot;a&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>On the first turn, select and remove cards <code>&quot;aa&quot;</code> and <code>&quot;ba&quot;</code>.</li>
</ul>

<p>Because there are no more compatible pairs, the total score is 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">cards = [&quot;aa&quot;,&quot;ab&quot;,&quot;ba&quot;,&quot;ac&quot;], x = &quot;b&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The only cards that contain the character <code>&#39;b&#39;</code> are <code>&quot;ab&quot;</code> and <code>&quot;ba&quot;</code>. However, they differ in both indices, so they are not compatible. Thus, the output is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= cards.length &lt;= 10<sup>5</sup></code></li>
	<li><code>cards[i].length == 2</code></li>
	<li>Each <code>cards[i]</code> is composed of only lowercase English letters between <code>&#39;a&#39;</code> and <code>&#39;j&#39;</code>.</li>
	<li><code>x</code> is a lowercase English letter between <code>&#39;a&#39;</code> and <code>&#39;j&#39;</code>.</li>
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
