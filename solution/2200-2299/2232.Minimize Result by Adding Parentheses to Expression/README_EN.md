# [2232. Minimize Result by Adding Parentheses to Expression](https://leetcode.com/problems/minimize-result-by-adding-parentheses-to-expression)

[中文文档](/solution/2200-2299/2232.Minimize%20Result%20by%20Adding%20Parentheses%20to%20Expression/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>expression</code> of the form <code>&quot;&lt;num1&gt;+&lt;num2&gt;&quot;</code> where <code>&lt;num1&gt;</code> and <code>&lt;num2&gt;</code> represent positive integers.</p>

<p>Add a pair of parentheses to <code>expression</code> such that after the addition of parentheses, <code>expression</code> is a <strong>valid</strong> mathematical expression and evaluates to the <strong>smallest</strong> possible value. The left parenthesis <strong>must</strong> be added to the left of <code>&#39;+&#39;</code> and the right parenthesis <strong>must</strong> be added to the right of <code>&#39;+&#39;</code>.</p>

<p>Return <code>expression</code><em> after adding a pair of parentheses such that </em><code>expression</code><em> evaluates to the <strong>smallest</strong> possible value.</em> If there are multiple answers that yield the same result, return any of them.</p>

<p>The input has been generated such that the original value of <code>expression</code>, and the value of <code>expression</code> after adding any pair of parentheses that meets the requirements fits within a signed 32-bit integer.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;247+38&quot;
<strong>Output:</strong> &quot;2(47+38)&quot;
<strong>Explanation:</strong> The <code>expression</code> evaluates to 2 * (47 + 38) = 2 * 85 = 170.
Note that &quot;2(4)7+38&quot; is invalid because the right parenthesis must be to the right of the <code>&#39;+&#39;</code>.
It can be shown that 170 is the smallest possible value.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;12+34&quot;
<strong>Output:</strong> &quot;1(2+3)4&quot;
<strong>Explanation:</strong> The expression evaluates to 1 * (2 + 3) * 4 = 1 * 5 * 4 = 20.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;999+999&quot;
<strong>Output:</strong> &quot;(999+999)&quot;
<strong>Explanation:</strong> The <code>expression</code> evaluates to 999 + 999 = 1998.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= expression.length &lt;= 10</code></li>
	<li><code>expression</code> consists of digits from <code>&#39;1&#39;</code> to <code>&#39;9&#39;</code> and <code>&#39;+&#39;</code>.</li>
	<li><code>expression</code> starts and ends with digits.</li>
	<li><code>expression</code> contains exactly one <code>&#39;+&#39;</code>.</li>
	<li>The original value of <code>expression</code>, and the value of <code>expression</code> after adding any pair of parentheses that meets the requirements fits within a signed 32-bit integer.</li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
