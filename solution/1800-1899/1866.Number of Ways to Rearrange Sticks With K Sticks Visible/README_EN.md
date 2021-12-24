# [1866. Number of Ways to Rearrange Sticks With K Sticks Visible](https://leetcode.com/problems/number-of-ways-to-rearrange-sticks-with-k-sticks-visible)

[中文文档](/solution/1800-1899/1866.Number%20of%20Ways%20to%20Rearrange%20Sticks%20With%20K%20Sticks%20Visible/README.md)

## Description

<p>There are <code>n</code> uniquely-sized sticks whose lengths are integers from <code>1</code> to <code>n</code>. You want to arrange the sticks such that <strong>exactly</strong> <code>k</code>&nbsp;sticks are <strong>visible</strong> from the left. A stick&nbsp;is <strong>visible</strong> from the left if there are no <strong>longer</strong>&nbsp;sticks to the <strong>left</strong> of it.</p>

<ul>
	<li>For example, if the sticks are arranged <code>[<u>1</u>,<u>3</u>,2,<u>5</u>,4]</code>, then the sticks with lengths <code>1</code>, <code>3</code>, and <code>5</code> are visible from the left.</li>
</ul>

<p>Given <code>n</code> and <code>k</code>, return <em>the <strong>number</strong> of such arrangements</em>. Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> [<u>1</u>,<u>3</u>,2], [<u>2</u>,<u>3</u>,1], and [<u>2</u>,1,<u>3</u>] are the only arrangements such that exactly 2 sticks are visible.
The visible sticks are underlined.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5, k = 5
<strong>Output:</strong> 1
<strong>Explanation:</strong> [<u>1</u>,<u>2</u>,<u>3</u>,<u>4</u>,<u>5</u>] is the only arrangement such that all 5 sticks are visible.
The visible sticks are underlined.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 20, k = 11
<strong>Output:</strong> 647427950
<strong>Explanation:</strong> There are 647427950 (mod 10<sup>9 </sup>+ 7) ways to rearrange the sticks such that exactly 11 sticks are visible.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
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
