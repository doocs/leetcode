# [868. Binary Gap](https://leetcode.com/problems/binary-gap)

[中文文档](/solution/0800-0899/0868.Binary%20Gap/README.md)

## Description

<p>Given a positive integer <code>n</code>, find and return <em>the <strong>longest distance</strong> between any two <strong>adjacent</strong> </em><code>1</code><em>&#39;s in the binary representation of </em><code>n</code><em>. If there are no two adjacent </em><code>1</code><em>&#39;s, return </em><code>0</code><em>.</em></p>

<p>Two <code>1</code>&#39;s are <strong>adjacent</strong> if there are only <code>0</code>&#39;s separating them (possibly no <code>0</code>&#39;s). The <b>distance</b> between two <code>1</code>&#39;s is the absolute difference between their bit positions. For example, the two <code>1</code>&#39;s in <code>&quot;1001&quot;</code> have a distance of 3.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 22
<strong>Output:</strong> 2
<strong>Explanation:</strong> 22 in binary is &quot;10110&quot;.
The first adjacent pair of 1&#39;s is &quot;<u>1</u>0<u>1</u>10&quot; with a distance of 2.
The second adjacent pair of 1&#39;s is &quot;10<u>11</u>0&quot; with a distance of 1.
The answer is the largest of these two distances, which is 2.
Note that &quot;<u>1</u>01<u>1</u>0&quot; is not a valid pair since there is a 1 separating the two 1&#39;s underlined.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> 5 in binary is &quot;101&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 6
<strong>Output:</strong> 1
<strong>Explanation:</strong> 6 in binary is &quot;110&quot;.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> n = 8
<strong>Output:</strong> 0
<strong>Explanation:</strong> 8 in binary is &quot;1000&quot;.
There aren&#39;t any adjacent pairs of 1&#39;s in the binary representation of 8, so we return 0.
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
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
