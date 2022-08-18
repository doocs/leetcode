# [604. Design Compressed String Iterator](https://leetcode.com/problems/design-compressed-string-iterator)

[中文文档](/solution/0600-0699/0604.Design%20Compressed%20String%20Iterator/README.md)

## Description

<p>Design and implement a data structure for a compressed string iterator. The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.</p>

<p>Implement the&nbsp;StringIterator class:</p>

<ul>
	<li><code>next()</code>&nbsp;Returns <strong>the next character</strong> if the original string still has uncompressed characters, otherwise returns a <strong>white space</strong>.</li>
	<li><code>hasNext()</code>&nbsp;Returns true if&nbsp;there is any letter needs to be uncompressed in the original string, otherwise returns <code>false</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;StringIterator&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;next&quot;, &quot;hasNext&quot;]
[[&quot;L1e2t1C1o1d1e1&quot;], [], [], [], [], [], [], [], [], []]
<strong>Output</strong>
[null, &quot;L&quot;, &quot;e&quot;, &quot;e&quot;, &quot;t&quot;, &quot;C&quot;, &quot;o&quot;, true, &quot;d&quot;, true]

<strong>Explanation</strong>
StringIterator stringIterator = new StringIterator(&quot;L1e2t1C1o1d1e1&quot;);
stringIterator.next(); // return &quot;L&quot;
stringIterator.next(); // return &quot;e&quot;
stringIterator.next(); // return &quot;e&quot;
stringIterator.next(); // return &quot;t&quot;
stringIterator.next(); // return &quot;C&quot;
stringIterator.next(); // return &quot;o&quot;
stringIterator.hasNext(); // return True
stringIterator.next(); // return &quot;d&quot;
stringIterator.hasNext(); // return True
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;compressedString.length &lt;= 1000</code></li>
	<li><code>compressedString</code> consists of lower-case an upper-case English letters and digits.</li>
	<li>The number of a single character repetitions in&nbsp;<code>compressedString</code> is in the range <code>[1, 10^9]</code></li>
	<li>At most <code>100</code> calls will be made to <code>next</code> and <code>hasNext</code>.</li>
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
