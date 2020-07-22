# [942. DI String Match](https://leetcode.com/problems/di-string-match)

[中文文档](/solution/0900-0999/0942.DI%20String%20Match/README.md)

## Description
<p>Given a string <code>S</code> that <strong>only</strong> contains &quot;I&quot; (increase) or &quot;D&quot; (decrease), let <code>N = S.length</code>.</p>



<p>Return <strong>any</strong> permutation <code>A</code> of <code>[0, 1, ..., N]</code> such that for all <code>i = 0,&nbsp;..., N-1</code>:</p>



<ul>

	<li>If <code>S[i] == &quot;I&quot;</code>, then <code>A[i] &lt; A[i+1]</code></li>

	<li>If <code>S[i] == &quot;D&quot;</code>, then <code>A[i] &gt; A[i+1]</code></li>

</ul>



<p>&nbsp;</p>



<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-1-1">&quot;IDID&quot;</span>

<strong>Output: </strong><span id="example-output-1">[0,4,1,3,2]</span>

</pre>



<div>

<p><strong>Example 2:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-2-1">&quot;III&quot;</span>

<strong>Output: </strong><span id="example-output-2">[0,1,2,3]</span>

</pre>



<div>

<p><strong>Example 3:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-3-1">&quot;DDI&quot;</span>

<strong>Output: </strong><span id="example-output-3">[3,2,0,1]</span></pre>

</div>

</div>



<p>&nbsp;</p>



<p><strong>Note:</strong></p>



<ol>

	<li><code>1 &lt;= S.length &lt;= 10000</code></li>

	<li><code>S</code> only contains characters <code>&quot;I&quot;</code> or <code>&quot;D&quot;</code>.</li>

</ol>


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