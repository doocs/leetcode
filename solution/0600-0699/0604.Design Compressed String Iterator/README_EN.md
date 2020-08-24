# [604. Design Compressed String Iterator](https://leetcode.com/problems/design-compressed-string-iterator)

[中文文档](/solution/0600-0699/0604.Design%20Compressed%20String%20Iterator/README.md)

## Description
<p>Design and implement a data structure for a compressed string iterator. The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.</p>

<p>Implement the StringIterator class:</p>

<ul>
	<li><code>next()</code> Returns <strong>the next character</strong> if the original string still has uncompressed characters, otherwise returns a <strong>white space</strong>.</li>
	<li><code>hasNext()</code> Returns true if there is any letter needs to be uncompressed in the original string, otherwise returns <code>false</code>.</li>
</ul>

<p> </p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
["StringIterator", "next", "next", "next", "next", "next", "next", "hasNext", "next", "hasNext"]
[["L1e2t1C1o1d1e1"], [], [], [], [], [], [], [], [], []]
<strong>Output</strong>
[null, "L", "e", "e", "t", "C", "o", true, "d", true]

<strong>Explanation</strong>
StringIterator stringIterator = new StringIterator("L1e2t1C1o1d1e1");
stringIterator.next(); // return "L"
stringIterator.next(); // return "e"
stringIterator.next(); // return "e"
stringIterator.next(); // return "t"
stringIterator.next(); // return "C"
stringIterator.next(); // return "o"
stringIterator.hasNext(); // return True
stringIterator.next(); // return "d"
stringIterator.hasNext(); // return True
</pre>

<p> </p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 <= compressedString.length <= 1000</code></li>
	<li><code>compressedString</code> consists of lower-case an upper-case English letters and digits.</li>
	<li>The number of a single character repetitions in <code>compressedString</code> is in the range <code>[1, 10^9]</code></li>
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