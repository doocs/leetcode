# [822. Card Flipping Game](https://leetcode.com/problems/card-flipping-game)

[中文文档](/solution/0800-0899/0822.Card%20Flipping%20Game/README.md)

## Description

<p>You are given <code>n</code> cards, with a positive integer printed on the front and back of each card (possibly different). You can flip any number of cards (possibly zero).</p>

<p>After choosing the front and the back of each card, you will pick each card, and if the integer printed on the back of this card is not printed on the front of any other card, then this integer is <strong>good</strong>.</p>

<p>You are given two integer array <code>fronts</code> and <code>backs</code> where <code>fronts[i]</code> and <code>backs[i]</code> are the integers printer on the front and the back of the <code>i<sup>th</sup></code> card respectively.</p>

<p>Return <em>the smallest good and integer after flipping the cards</em>. If there are no good integers, return <code>0</code>.</p>

<p><strong>Note</strong> that a <strong>flip</strong> swaps the front and back numbers, so the value on the front is now on the back and vice versa.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> fronts = [1,2,4,4,7], backs = [1,3,4,1,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> If we flip the second card, the fronts are [1,3,4,4,7] and the backs are [1,2,4,1,3].
We choose the second card, which has the number 2 on the back, and it is not on the front of any card, so 2 is good.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> fronts = [1], backs = [1]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == fronts.length</code></li>
	<li><code>n == backs.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= fronts[i], backs[i] &lt;= 2000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
