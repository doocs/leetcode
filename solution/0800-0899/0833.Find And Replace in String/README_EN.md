# [833. Find And Replace in String](https://leetcode.com/problems/find-and-replace-in-string)

[中文文档](/solution/0800-0899/0833.Find%20And%20Replace%20in%20String/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>s</code> that you must perform <code>k</code> replacement operations on. The replacement operations are given as three <strong>0-indexed</strong> parallel arrays, <code>indices</code>, <code>sources</code>, and <code>targets</code>, all of length <code>k</code>.</p>

<p>To complete the <code>i<sup>th</sup></code> replacement operation:</p>

<ol>
	<li>Check if the <strong>substring</strong> <code>sources[i]</code> occurs at index <code>indices[i]</code> in the <strong>original string</strong> <code>s</code>.</li>
	<li>If it does not occur, <strong>do nothing</strong>.</li>
	<li>Otherwise if it does occur, <strong>replace</strong> that substring with <code>targets[i]</code>.</li>
</ol>

<p>For example, if <code>s = &quot;<u>ab</u>cd&quot;</code>, <code>indices[i] = 0</code>, <code>sources[i] = &quot;ab&quot;</code>, and <code>targets[i] = &quot;eee&quot;</code>, then the result of this replacement will be <code>&quot;<u>eee</u>cd&quot;</code>.</p>

<p>All replacement operations must occur <strong>simultaneously</strong>, meaning the replacement operations should not affect the indexing of each other. The testcases will be generated such that the replacements will <strong>not overlap</strong>.</p>

<ul>
	<li>For example, a testcase with <code>s = &quot;abc&quot;</code>, <code>indices = [0, 1]</code>, and <code>sources = [&quot;ab&quot;,&quot;bc&quot;]</code> will not be generated because the <code>&quot;ab&quot;</code> and <code>&quot;bc&quot;</code> replacements overlap.</li>
</ul>

<p>Return <em>the <strong>resulting string</strong> after performing all replacement operations on </em><code>s</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters in a string.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0833.Find%20And%20Replace%20in%20String/images/833-ex1.png" style="width: 411px; height: 251px;" />
<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, indices = [0, 2], sources = [&quot;a&quot;, &quot;cd&quot;], targets = [&quot;eee&quot;, &quot;ffff&quot;]
<strong>Output:</strong> &quot;eeebffff&quot;
<strong>Explanation:</strong>
&quot;a&quot; occurs at index 0 in s, so we replace it with &quot;eee&quot;.
&quot;cd&quot; occurs at index 2 in s, so we replace it with &quot;ffff&quot;.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0833.Find%20And%20Replace%20in%20String/images/833-ex2-1.png" style="width: 411px; height: 251px;" />
<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, indices = [0, 2], sources = [&quot;ab&quot;,&quot;ec&quot;], targets = [&quot;eee&quot;,&quot;ffff&quot;]
<strong>Output:</strong> &quot;eeecd&quot;
<strong>Explanation:</strong>
&quot;ab&quot; occurs at index 0 in s, so we replace it with &quot;eee&quot;.
&quot;ec&quot; does not occur at index 2 in s, so we do nothing.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>k == indices.length == sources.length == targets.length</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
	<li><code>0 &lt;= indexes[i] &lt; s.length</code></li>
	<li><code>1 &lt;= sources[i].length, targets[i].length &lt;= 50</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
	<li><code>sources[i]</code> and <code>targets[i]</code> consist of only lowercase English letters.</li>
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
