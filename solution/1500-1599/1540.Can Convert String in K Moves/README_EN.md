# [1540. Can Convert String in K Moves](https://leetcode.com/problems/can-convert-string-in-k-moves)

[中文文档](/solution/1500-1599/1540.Can%20Convert%20String%20in%20K%20Moves/README.md)

## Description

<p>Given two strings&nbsp;<code>s</code>&nbsp;and&nbsp;<code>t</code>, your goal is to convert&nbsp;<code>s</code>&nbsp;into&nbsp;<code>t</code>&nbsp;in&nbsp;<code>k</code><strong>&nbsp;</strong>moves or less.</p>

<p>During the&nbsp;<code>i<sup>th</sup></code>&nbsp;(<font face="monospace"><code>1 &lt;= i &lt;= k</code>)&nbsp;</font>move you can:</p>

<ul>
	<li>Choose any index&nbsp;<code>j</code>&nbsp;(1-indexed) from&nbsp;<code>s</code>, such that&nbsp;<code>1 &lt;= j &lt;= s.length</code>&nbsp;and <code>j</code>&nbsp;has not been chosen in any previous move,&nbsp;and shift the character at that index&nbsp;<code>i</code>&nbsp;times.</li>
	<li>Do nothing.</li>
</ul>

<p>Shifting a character means replacing it by the next letter in the alphabet&nbsp;(wrapping around so that&nbsp;<code>&#39;z&#39;</code>&nbsp;becomes&nbsp;<code>&#39;a&#39;</code>). Shifting a character by&nbsp;<code>i</code>&nbsp;means applying the shift operations&nbsp;<code>i</code>&nbsp;times.</p>

<p>Remember that any index&nbsp;<code>j</code>&nbsp;can be picked at most once.</p>

<p>Return&nbsp;<code>true</code>&nbsp;if it&#39;s possible to convert&nbsp;<code>s</code>&nbsp;into&nbsp;<code>t</code>&nbsp;in no more than&nbsp;<code>k</code>&nbsp;moves, otherwise return&nbsp;<code>false</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;input&quot;, t = &quot;ouput&quot;, k = 9
<strong>Output:</strong> true
<b>Explanation: </b>In the 6th move, we shift &#39;i&#39; 6 times to get &#39;o&#39;. And in the 7th move we shift &#39;n&#39; to get &#39;u&#39;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc&quot;, t = &quot;bcd&quot;, k = 10
<strong>Output:</strong> false
<strong>Explanation: </strong>We need to shift each character in s one time to convert it into t. We can shift &#39;a&#39; to &#39;b&#39; during the 1st move. However, there is no way to shift the other characters in the remaining moves to obtain t from s.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aab&quot;, t = &quot;bbb&quot;, k = 27
<strong>Output:</strong> true
<b>Explanation: </b>In the 1st move, we shift the first &#39;a&#39; 1 time to get &#39;b&#39;. In the 27th move, we shift the second &#39;a&#39; 27 times to get &#39;b&#39;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 10^5</code></li>
	<li><code>0 &lt;= k &lt;= 10^9</code></li>
	<li><code>s</code>, <code>t</code> contain&nbsp;only lowercase English letters.</li>
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
