# [1656. Design an Ordered Stream](https://leetcode.com/problems/design-an-ordered-stream)

[中文文档](/solution/1600-1699/1656.Design%20an%20Ordered%20Stream/README.md)

## Description

<p>There is a stream of <code>n</code> <code>(idKey, value)</code> pairs arriving in an <strong>arbitrary</strong> order, where <code>idKey</code> is an integer between <code>1</code> and <code>n</code> and <code>value</code> is a string. No two pairs have the same <code>id</code>.</p>

<p>Design a stream that returns the values in <strong>increasing order of their IDs</strong> by returning a <strong>chunk</strong> (list) of values after each insertion. The concatenation of all the <strong>chunks</strong> should result in a list of the sorted values.</p>

<p>Implement the <code>OrderedStream</code> class:</p>

<ul>
	<li><code>OrderedStream(int n)</code> Constructs the stream to take <code>n</code> values.</li>
	<li><code>String[] insert(int idKey, String value)</code> Inserts the pair <code>(idKey, value)</code> into the stream, then returns the <strong>largest possible chunk</strong> of currently inserted values that appear next in the order.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1656.Design%20an%20Ordered%20Stream/images/q1.gif" style="width: 682px; height: 240px;" /></strong></p>

<pre>
<strong>Input</strong>
[&quot;OrderedStream&quot;, &quot;insert&quot;, &quot;insert&quot;, &quot;insert&quot;, &quot;insert&quot;, &quot;insert&quot;]
[[5], [3, &quot;ccccc&quot;], [1, &quot;aaaaa&quot;], [2, &quot;bbbbb&quot;], [5, &quot;eeeee&quot;], [4, &quot;ddddd&quot;]]
<strong>Output</strong>
[null, [], [&quot;aaaaa&quot;], [&quot;bbbbb&quot;, &quot;ccccc&quot;], [], [&quot;ddddd&quot;, &quot;eeeee&quot;]]

<strong>Explanation</strong>
// Note that the values ordered by ID is [&quot;aaaaa&quot;, &quot;bbbbb&quot;, &quot;ccccc&quot;, &quot;ddddd&quot;, &quot;eeeee&quot;].
OrderedStream os = new OrderedStream(5);
os.insert(3, &quot;ccccc&quot;); // Inserts (3, &quot;ccccc&quot;), returns [].
os.insert(1, &quot;aaaaa&quot;); // Inserts (1, &quot;aaaaa&quot;), returns [&quot;aaaaa&quot;].
os.insert(2, &quot;bbbbb&quot;); // Inserts (2, &quot;bbbbb&quot;), returns [&quot;bbbbb&quot;, &quot;ccccc&quot;].
os.insert(5, &quot;eeeee&quot;); // Inserts (5, &quot;eeeee&quot;), returns [].
os.insert(4, &quot;ddddd&quot;); // Inserts (4, &quot;ddddd&quot;), returns [&quot;ddddd&quot;, &quot;eeeee&quot;].
// Concatentating all the chunks returned:
// [] + [&quot;aaaaa&quot;] + [&quot;bbbbb&quot;, &quot;ccccc&quot;] + [] + [&quot;ddddd&quot;, &quot;eeeee&quot;] = [&quot;aaaaa&quot;, &quot;bbbbb&quot;, &quot;ccccc&quot;, &quot;ddddd&quot;, &quot;eeeee&quot;]
// The resulting order is the same as the order above.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= id &lt;= n</code></li>
	<li><code>value.length == 5</code></li>
	<li><code>value</code>&nbsp;consists only of lowercase letters.</li>
	<li>Each call to <code>insert</code>&nbsp;will have a unique <code>id.</code></li>
	<li>Exactly <code>n</code> calls will be made to <code>insert</code>.</li>
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
