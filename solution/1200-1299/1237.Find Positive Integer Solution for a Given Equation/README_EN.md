# [1237. Find Positive Integer Solution for a Given Equation](https://leetcode.com/problems/find-positive-integer-solution-for-a-given-equation)

[中文文档](/solution/1200-1299/1237.Find%20Positive%20Integer%20Solution%20for%20a%20Given%20Equation/README.md)

## Description

<p>Given a&nbsp;function&nbsp; <code>f(x, y)</code>&nbsp;and a value <code>z</code>, return all positive integer&nbsp;pairs <code>x</code> and <code>y</code> where <code>f(x,y) == z</code>.</p>

<p>The function is constantly increasing, i.e.:</p>

<ul>
	<li><code>f(x, y) &lt; f(x + 1, y)</code></li>
	<li><code>f(x, y) &lt; f(x, y + 1)</code></li>
</ul>

<p>The function interface is defined like this:&nbsp;</p>

<pre>
interface CustomFunction {
public:
&nbsp; // Returns positive integer f(x, y) for any given positive integer x and y.
&nbsp; int f(int x, int y);
};
</pre>

<p>For custom testing purposes you&#39;re given an integer <code>function_id</code> and a target <code>z</code> as input, where <code>function_id</code> represent one function from an secret internal list, on the examples you&#39;ll know only two functions from the list. &nbsp;</p>

<p>You may return the solutions in any order.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> function_id = 1, z = 5
<strong>Output:</strong> [[1,4],[2,3],[3,2],[4,1]]
<strong>Explanation:</strong>&nbsp;function_id = 1 means that f(x, y) = x + y</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> function_id = 2, z = 5
<strong>Output:</strong> [[1,5],[5,1]]
<strong>Explanation:</strong>&nbsp;function_id = 2 means that f(x, y) = x * y
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= function_id &lt;= 9</code></li>
	<li><code>1 &lt;= z &lt;= 100</code></li>
	<li>It&#39;s guaranteed that the solutions of <code>f(x, y) == z</code> will be on the range <code>1 &lt;= x, y &lt;= 1000</code></li>
	<li>It&#39;s also guaranteed that <code>f(x, y)</code> will fit in 32 bit signed integer if <code>1 &lt;= x, y &lt;= 1000</code></li>
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
