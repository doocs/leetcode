# [679. 24 Game](https://leetcode.com/problems/24-game)

[中文文档](/solution/0600-0699/0679.24%20Game/README.md)

## Description

<p>You are given an integer array <code>cards</code> of length <code>4</code>. You have four cards, each containing a number in the range <code>[1, 9]</code>. You should arrange the numbers on these cards in a mathematical expression using the operators <code>[&#39;+&#39;, &#39;-&#39;, &#39;*&#39;, &#39;/&#39;]</code> and the parentheses <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code> to get the value 24.</p>

<p>You are restricted with the following rules:</p>

<ul>
	<li>The division operator <code>&#39;/&#39;</code> represents real division, not integer division.

    <ul>
    	<li>For example, <code>4 / (1 - 2 / 3) = 4 / (1 / 3) = 12</code>.</li>
    </ul>
    </li>
    <li>Every operation done is between two numbers. In particular, we cannot use <code>&#39;-&#39;</code> as a unary operator.
    <ul>
    	<li>For example, if <code>cards = [1, 1, 1, 1]</code>, the expression <code>&quot;-1 - 1 - 1 - 1&quot;</code> is <strong>not allowed</strong>.</li>
    </ul>
    </li>
    <li>You cannot concatenate numbers together
    <ul>
    	<li>For example, if <code>cards = [1, 2, 1, 2]</code>, the expression <code>&quot;12 + 12&quot;</code> is not valid.</li>
    </ul>
    </li>

</ul>

<p>Return <code>true</code> if you can get such expression that evaluates to <code>24</code>, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> cards = [4,1,8,7]
<strong>Output:</strong> true
<strong>Explanation:</strong> (8-4) * (7-1) = 24
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> cards = [1,2,1,2]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>cards.length == 4</code></li>
	<li><code>1 &lt;= cards[i] &lt;= 9</code></li>
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
