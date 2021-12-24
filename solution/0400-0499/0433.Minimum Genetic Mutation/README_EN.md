# [433. Minimum Genetic Mutation](https://leetcode.com/problems/minimum-genetic-mutation)

[中文文档](/solution/0400-0499/0433.Minimum%20Genetic%20Mutation/README.md)

## Description

<p>A gene string can be represented by an 8-character long string, with choices from <code>&#39;A&#39;</code>, <code>&#39;C&#39;</code>, <code>&#39;G&#39;</code>, and <code>&#39;T&#39;</code>.</p>

<p>Suppose we need to investigate a mutation from a gene string <code>start</code> to a gene string <code>end</code> where one mutation is defined as one single character changed in the gene string.</p>

<ul>
	<li>For example, <code>&quot;AACCGGTT&quot; --&gt; &quot;AACCGGTA&quot;</code> is one mutation.</li>
</ul>

<p>There is also a gene bank <code>bank</code> that records all the valid gene mutations. A gene must be in <code>bank</code> to make it a valid gene string.</p>

<p>Given the two gene strings <code>start</code> and <code>end</code> and the gene bank <code>bank</code>, return <em>the minimum number of mutations needed to mutate from </em><code>start</code><em> to </em><code>end</code>. If there is no such a mutation, return <code>-1</code>.</p>

<p>Note that the starting point is assumed to be valid, so it might not be included in the bank.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> start = &quot;AACCGGTT&quot;, end = &quot;AACCGGTA&quot;, bank = [&quot;AACCGGTA&quot;]
<strong>Output:</strong> 1
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> start = &quot;AACCGGTT&quot;, end = &quot;AAACGGTA&quot;, bank = [&quot;AACCGGTA&quot;,&quot;AACCGCTA&quot;,&quot;AAACGGTA&quot;]
<strong>Output:</strong> 2
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> start = &quot;AAAAACCC&quot;, end = &quot;AACCCCCC&quot;, bank = [&quot;AAAACCCC&quot;,&quot;AAACCCCC&quot;,&quot;AACCCCCC&quot;]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>start.length == 8</code></li>
	<li><code>end.length == 8</code></li>
	<li><code>0 &lt;= bank.length &lt;= 10</code></li>
	<li><code>bank[i].length == 8</code></li>
	<li><code>start</code>, <code>end</code>, and <code>bank[i]</code> consist of only the characters <code>[&#39;A&#39;, &#39;C&#39;, &#39;G&#39;, &#39;T&#39;]</code>.</li>
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
