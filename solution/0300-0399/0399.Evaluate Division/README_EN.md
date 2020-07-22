# [399. Evaluate Division](https://leetcode.com/problems/evaluate-division)

## Description
<p>Equations are given in the format <code>A / B = k</code>, where <code>A</code> and <code>B</code> are variables represented as strings, and <code>k</code> is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return <code>-1.0</code>.</p>



<p><b>Example:</b><br />

Given <code> a / b = 2.0, b / c = 3.0.</code><br />

queries are: <code> a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .</code><br />

return <code> [6.0, 0.5, -1.0, 1.0, -1.0 ].</code></p>



<p>The input is: <code> vector&lt;pair&lt;string, string&gt;&gt; equations, vector&lt;double&gt;&amp; values, vector&lt;pair&lt;string, string&gt;&gt; queries </code>, where <code>equations.size() == values.size()</code>, and the values are positive. This represents the equations. Return <code> vector&lt;double&gt;</code>.</p>



<p>According to the example above:</p>



<pre>

equations = [ [&quot;a&quot;, &quot;b&quot;], [&quot;b&quot;, &quot;c&quot;] ],

values = [2.0, 3.0],

queries = [ [&quot;a&quot;, &quot;c&quot;], [&quot;b&quot;, &quot;a&quot;], [&quot;a&quot;, &quot;e&quot;], [&quot;a&quot;, &quot;a&quot;], [&quot;x&quot;, &quot;x&quot;] ]. </pre>



<p>&nbsp;</p>



<p>The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.</p>




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