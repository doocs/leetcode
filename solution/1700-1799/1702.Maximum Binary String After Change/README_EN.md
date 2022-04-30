# [1702. Maximum Binary String After Change](https://leetcode.com/problems/maximum-binary-string-after-change)

[中文文档](/solution/1700-1799/1702.Maximum%20Binary%20String%20After%20Change/README.md)

## Description

<p>You are given a binary string <code>binary</code> consisting of only <code>0</code>&#39;s or <code>1</code>&#39;s. You can apply each of the following operations any number of times:</p>

<ul>
	<li>Operation 1: If the number contains the substring <code>&quot;00&quot;</code>, you can replace it with <code>&quot;10&quot;</code>.
    <ul>
    	<li>For example, <code>&quot;<u>00</u>010&quot; -&gt; &quot;<u>10</u>010</code>&quot;</li>
    </ul>
    </li>
    <li>Operation 2: If the number contains the substring <code>&quot;10&quot;</code>, you can replace it with <code>&quot;01&quot;</code>.
    <ul>
    	<li>For example, <code>&quot;000<u>10</u>&quot; -&gt; &quot;000<u>01</u>&quot;</code></li>
    </ul>
    </li>
</ul>

<p><em>Return the <strong>maximum binary string</strong> you can obtain after any number of operations. Binary string <code>x</code> is greater than binary string <code>y</code> if <code>x</code>&#39;s decimal representation is greater than <code>y</code>&#39;s decimal representation.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> binary = &quot;000110&quot;
<strong>Output:</strong> &quot;111011&quot;
<strong>Explanation:</strong> A valid transformation sequence can be:
&quot;0001<u>10</u>&quot; -&gt; &quot;0001<u>01</u>&quot; 
&quot;<u>00</u>0101&quot; -&gt; &quot;<u>10</u>0101&quot; 
&quot;1<u>00</u>101&quot; -&gt; &quot;1<u>10</u>101&quot; 
&quot;110<u>10</u>1&quot; -&gt; &quot;110<u>01</u>1&quot; 
&quot;11<u>00</u>11&quot; -&gt; &quot;11<u>10</u>11&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> binary = &quot;01&quot;
<strong>Output:</strong> &quot;01&quot;
<strong>Explanation:</strong>&nbsp;&quot;01&quot; cannot be transformed any further.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= binary.length &lt;= 10<sup>5</sup></code></li>
	<li><code>binary</code> consist of <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</li>
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
