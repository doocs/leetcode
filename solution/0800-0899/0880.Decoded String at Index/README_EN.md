# [880. Decoded String at Index](https://leetcode.com/problems/decoded-string-at-index)

[中文文档](/solution/0800-0899/0880.Decoded%20String%20at%20Index/README.md)

## Description

<p>An encoded string <code>S</code> is given.&nbsp; To find and write the <em>decoded</em> string to a tape, the encoded string is read <strong>one character at a time</strong>&nbsp;and the following steps are taken:</p>

<ul>
	<li>If the character read is a letter, that letter is written onto the tape.</li>
	<li>If the character read is a digit (say <code>d</code>), the entire current tape is repeatedly written&nbsp;<code>d-1</code>&nbsp;more times in total.</li>
</ul>

<p>Now for some encoded string <code>S</code>, and an index <code>K</code>, find and return the <code>K</code>-th letter (1 indexed) in the decoded string.</p>

<p>&nbsp;</p>

<div>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong>S = <span id="example-input-1-1">&quot;leet2code3&quot;</span>, K = <span id="example-input-1-2">10</span>
<strong>Output: </strong><span id="example-output-1">&quot;o&quot;</span>
<strong>Explanation: </strong>
The decoded string is &quot;leetleetcodeleetleetcodeleetleetcode&quot;.
The 10th letter in the string is &quot;o&quot;.
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: </strong>S = <span id="example-input-2-1">&quot;ha22&quot;</span>, K = <span id="example-input-2-2">5</span>
<strong>Output: </strong><span id="example-output-2">&quot;h&quot;</span>
<strong>Explanation: </strong>
The decoded string is &quot;hahahaha&quot;.  The 5th letter is &quot;h&quot;.
</pre>

<div>
<p><strong>Example 3:</strong></p>

<pre>
<strong>Input: </strong>S = <span id="example-input-3-1">&quot;a2345678999999999999999&quot;</span>, K = <span id="example-input-3-2">1</span>
<strong>Output: </strong><span id="example-output-3">&quot;a&quot;</span>
<strong>Explanation: </strong>
The decoded string is &quot;a&quot; repeated 8301530446056247680 times.  The 1st letter is &quot;a&quot;.
</pre>
</div>
</div>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= S.length &lt;= 100</code></li>
	<li><code>S</code>&nbsp;will only contain lowercase letters and digits <code>2</code> through <code>9</code>.</li>
	<li><code>S</code>&nbsp;starts with a letter.</li>
	<li><code>1 &lt;= K &lt;= 10^9</code></li>
	<li>It&#39;s guaranteed that <code>K</code>&nbsp;is less than or equal to the length of the decoded string.</li>
	<li>The decoded string is guaranteed to have less than <code>2^63</code> letters.</li>
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
