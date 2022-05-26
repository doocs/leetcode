# [1286. Iterator for Combination](https://leetcode.com/problems/iterator-for-combination)

[中文文档](/solution/1200-1299/1286.Iterator%20for%20Combination/README.md)

## Description

<p>Design the <code>CombinationIterator</code> class:</p>

<ul>
	<li><code>CombinationIterator(string characters, int combinationLength)</code> Initializes the object with a string <code>characters</code> of <strong>sorted distinct</strong> lowercase English letters and a number <code>combinationLength</code> as arguments.</li>
	<li><code>next()</code> Returns the next combination of length <code>combinationLength</code> in <strong>lexicographical order</strong>.</li>
	<li><code>hasNext()</code> Returns <code>true</code> if and only if there exists a next combination.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;CombinationIterator&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;next&quot;, &quot;hasNext&quot;]
[[&quot;abc&quot;, 2], [], [], [], [], [], []]
<strong>Output</strong>
[null, &quot;ab&quot;, true, &quot;ac&quot;, true, &quot;bc&quot;, false]

<strong>Explanation</strong>
CombinationIterator itr = new CombinationIterator(&quot;abc&quot;, 2);
itr.next();    // return &quot;ab&quot;
itr.hasNext(); // return True
itr.next();    // return &quot;ac&quot;
itr.hasNext(); // return True
itr.next();    // return &quot;bc&quot;
itr.hasNext(); // return False
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= combinationLength &lt;= characters.length &lt;= 15</code></li>
	<li>All the characters of <code>characters</code> are <strong>unique</strong>.</li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>next</code> and <code>hasNext</code>.</li>
	<li>It is guaranteed that all calls of the function <code>next</code> are valid.</li>
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
