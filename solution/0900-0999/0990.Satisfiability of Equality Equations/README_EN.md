# [990. Satisfiability of Equality Equations](https://leetcode.com/problems/satisfiability-of-equality-equations)

[中文文档](/solution/0900-0999/0990.Satisfiability%20of%20Equality%20Equations/README.md)

## Description

<p>Given an array <font face="monospace">equations</font>&nbsp;of strings that represent relationships between variables, each string <code>equations[i]</code>&nbsp;has length <code>4</code> and takes one of two different forms: <code>&quot;a==b&quot;</code> or <code>&quot;a!=b&quot;</code>.&nbsp; Here, <code>a</code> and <code>b</code> are lowercase letters (not necessarily different) that represent one-letter variable names.</p>

<p>Return <code>true</code>&nbsp;if and only if it is possible to assign integers to variable names&nbsp;so as to satisfy all the given equations.</p>

<p>&nbsp;</p>

<ol>
</ol>

<div>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-1-1">[&quot;a==b&quot;,&quot;b!=a&quot;]</span>
<strong>Output: </strong><span id="example-output-1">false</span>
<strong>Explanation: </strong>If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-2-1">[&quot;b==a&quot;,&quot;a==b&quot;]</span>
<strong>Output: </strong><span id="example-output-2">true</span>
<strong>Explanation: </strong>We could assign a = 1 and b = 1 to satisfy both equations.
</pre>

<div>
<p><strong>Example 3:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-3-1">[&quot;a==b&quot;,&quot;b==c&quot;,&quot;a==c&quot;]</span>
<strong>Output: </strong><span id="example-output-3">true</span>
</pre>

<div>
<p><strong>Example 4:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-4-1">[&quot;a==b&quot;,&quot;b!=c&quot;,&quot;c==a&quot;]</span>
<strong>Output: </strong><span id="example-output-4">false</span>
</pre>

<div>
<p><strong>Example 5:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-5-1">[&quot;c==c&quot;,&quot;b==d&quot;,&quot;x!=z&quot;]</span>
<strong>Output: </strong><span id="example-output-5">true</span>
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= equations.length &lt;= 500</code></li>
	<li><code>equations[i].length == 4</code></li>
	<li><code>equations[i][0]</code> and <code>equations[i][3]</code> are lowercase letters</li>
	<li><code>equations[i][1]</code> is either <code>&#39;=&#39;</code> or <code>&#39;!&#39;</code></li>
	<li><code>equations[i][2]</code> is&nbsp;<code>&#39;=&#39;</code></li>
</ol>
</div>
</div>
</div>
</div>
</div>


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
