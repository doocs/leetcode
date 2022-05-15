# [2269. Find the K-Beauty of a Number](https://leetcode.com/problems/find-the-k-beauty-of-a-number)

[中文文档](/solution/2200-2299/2269.Find%20the%20K-Beauty%20of%20a%20Number/README.md)

## Description

<p>The <strong>k-beauty</strong> of an integer <code>num</code> is defined as the number of <strong>substrings</strong> of <code>num</code> when it is read as a string that meet the following conditions:</p>

<ul>
	<li>It has a length of <code>k</code>.</li>
	<li>It is a divisor of <code>num</code>.</li>
</ul>

<p>Given integers <code>num</code> and <code>k</code>, return <em>the k-beauty of </em><code>num</code>.</p>

<p>Note:</p>

<ul>
	<li><strong>Leading zeros</strong> are allowed.</li>
	<li><code>0</code> is not a divisor of any value.</li>
</ul>

<p>A <strong>substring</strong> is a contiguous sequence of characters in a string.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 240, k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> The following are the substrings of num of length k:
- &quot;24&quot; from &quot;<strong><u>24</u></strong>0&quot;: 24 is a divisor of 240.
- &quot;40&quot; from &quot;2<u><strong>40</strong></u>&quot;: 40 is a divisor of 240.
Therefore, the k-beauty is 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 430043, k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> The following are the substrings of num of length k:
- &quot;43&quot; from &quot;<u><strong>43</strong></u>0043&quot;: 43 is a divisor of 430043.
- &quot;30&quot; from &quot;4<u><strong>30</strong></u>043&quot;: 30 is not a divisor of 430043.
- &quot;00&quot; from &quot;43<u><strong>00</strong></u>43&quot;: 0 is not a divisor of 430043.
- &quot;04&quot; from &quot;430<u><strong>04</strong></u>3&quot;: 4 is not a divisor of 430043.
- &quot;43&quot; from &quot;4300<u><strong>43</strong></u>&quot;: 43 is a divisor of 430043.
Therefore, the k-beauty is 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= num.length</code> (taking <code>num</code> as a string)</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
