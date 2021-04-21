# [284. Peeking Iterator](https://leetcode.com/problems/peeking-iterator)

[中文文档](/solution/0200-0299/0284.Peeking%20Iterator/README.md)

## Description

<p>Design an iterator that supports the <code>peek</code> operation on a list in addition to the <code>hasNext</code> and the <code>next</code> operations.</p>

<p>Implement the <code>PeekingIterator</code> class:</p>

<ul>
	<li><code>PeekingIterator(int[] nums)</code> Initializes the object with the given integer array <code>nums</code>.</li>
	<li><code>int next()</code> Returns the next element in the array and moves the pointer to the next element.</li>
	<li><code>bool hasNext()</code> Returns <code>true</code> if there are still elements in the array.</li>
	<li><code>int peek()</code> Returns the next element in the array <strong>without</strong> moving the pointer.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;PeekingIterator&quot;, &quot;next&quot;, &quot;peek&quot;, &quot;next&quot;, &quot;next&quot;, &quot;hasNext&quot;]
[[[1, 2, 3]], [], [], [], [], []]
<strong>Output</strong>
[null, 1, 2, 2, 3, false]

<strong>Explanation</strong>
PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [<u><strong>1</strong></u>,2,3]
peekingIterator.next();    // return 1, the pointer moves to the next element [1,<u><strong>2</strong></u>,3].
peekingIterator.peek();    // return 2, the pointer does not move [1,<u><strong>2</strong></u>,3].
peekingIterator.next();    // return 2, the pointer moves to the next element [1,2,<u><strong>3</strong></u>]
peekingIterator.next();    // return 3, the pointer moves to the next element [1,2,3]
peekingIterator.hasNext(); // return False
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li>All the calls to <code>next</code> and <code>peek</code> are valid.</li>
	<li>At most <code>1000</code> calls will be made to <code>next</code>, <code>hasNext</code>, and <code>peek</code>.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> How would you extend your design to be generic and work with all types, not just integer?

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
