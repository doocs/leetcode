# [1237. Find Positive Integer Solution for a Given Equation](https://leetcode.com/problems/find-positive-integer-solution-for-a-given-equation)

[中文文档](/solution/1200-1299/1237.Find%20Positive%20Integer%20Solution%20for%20a%20Given%20Equation/README.md)

## Description

<p>Given a callable function <code>f(x, y)</code> <strong>with a hidden formula</strong> and a value <code>z</code>, reverse engineer the formula and return <em>all positive integer pairs </em><code>x</code><em> and </em><code>y</code><em> where </em><code>f(x,y) == z</code>. You may return the pairs in any order.</p>

<p>While the exact formula is hidden, the function is monotonically increasing, i.e.:</p>

<ul>
	<li><code>f(x, y) &lt; f(x + 1, y)</code></li>
	<li><code>f(x, y) &lt; f(x, y + 1)</code></li>
</ul>

<p>The function interface is defined like this:</p>

<pre>
interface CustomFunction {
public:
  // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
  int f(int x, int y);
};
</pre>

<p>We will judge your solution as follows:</p>

<ul>
	<li>The judge has a list of <code>9</code> hidden implementations of <code>CustomFunction</code>, along with a way to generate an <strong>answer key</strong> of all valid pairs for a specific <code>z</code>.</li>
	<li>The judge will receive two inputs: a <code>function_id</code> (to determine which implementation to test your code with), and the target <code>z</code>.</li>
	<li>The judge will call your <code>findSolution</code> and compare your results with the <strong>answer key</strong>.</li>
	<li>If your results match the <strong>answer key</strong>, your solution will be <code>Accepted</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> function_id = 1, z = 5
<strong>Output:</strong> [[1,4],[2,3],[3,2],[4,1]]
<strong>Explanation:</strong> The hidden formula for function_id = 1 is f(x, y) = x + y.
The following positive integer values of x and y make f(x, y) equal to 5:
x=1, y=4 -&gt; f(1, 4) = 1 + 4 = 5.
x=2, y=3 -&gt; f(2, 3) = 2 + 3 = 5.
x=3, y=2 -&gt; f(3, 2) = 3 + 2 = 5.
x=4, y=1 -&gt; f(4, 1) = 4 + 1 = 5.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> function_id = 2, z = 5
<strong>Output:</strong> [[1,5],[5,1]]
<strong>Explanation:</strong> The hidden formula for function_id = 2 is f(x, y) = x * y.
The following positive integer values of x and y make f(x, y) equal to 5:
x=1, y=5 -&gt; f(1, 5) = 1 * 5 = 5.
x=5, y=1 -&gt; f(5, 1) = 5 * 1 = 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= function_id &lt;= 9</code></li>
	<li><code>1 &lt;= z &lt;= 100</code></li>
	<li>It is guaranteed that the solutions of <code>f(x, y) == z</code> will be in the range <code>1 &lt;= x, y &lt;= 1000</code>.</li>
	<li>It is also guaranteed that <code>f(x, y)</code> will fit in 32 bit signed integer if <code>1 &lt;= x, y &lt;= 1000</code>.</li>
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
