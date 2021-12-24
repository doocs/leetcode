# [1449. Form Largest Integer With Digits That Add up to Target](https://leetcode.com/problems/form-largest-integer-with-digits-that-add-up-to-target)

[中文文档](/solution/1400-1499/1449.Form%20Largest%20Integer%20With%20Digits%20That%20Add%20up%20to%20Target/README.md)

## Description

<p>Given an array of integers <code>cost</code> and an integer <code>target</code>. Return the <strong>maximum</strong> integer you can paint&nbsp;under the following rules:</p>

<ul>
	<li>The cost of painting a&nbsp;digit (i+1) is given by&nbsp;<code>cost[i]</code>&nbsp;(0 indexed).</li>
	<li>The total cost used must&nbsp;be equal to <code>target</code>.</li>
	<li>Integer does not have digits 0.</li>
</ul>

<p>Since the answer may be too large, return it as string.</p>

<p>If there is no way to paint any integer given the condition, return &quot;0&quot;.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> cost = [4,3,2,5,6,7,2,5,5], target = 9
<strong>Output:</strong> &quot;7772&quot;
<strong>Explanation: </strong> The cost to paint the digit &#39;7&#39; is 2, and the digit &#39;2&#39; is 3. Then cost(&quot;7772&quot;) = 2*3+ 3*1 = 9. You could also paint &quot;977&quot;, but &quot;7772&quot; is the largest number.
<strong>Digit    cost</strong>
  1  -&gt;   4
  2  -&gt;   3
  3  -&gt;   2
  4  -&gt;   5
  5  -&gt;   6
  6  -&gt;   7
  7  -&gt;   2
  8  -&gt;   5
  9  -&gt;   5
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> cost = [7,6,5,5,5,6,8,7,8], target = 12
<strong>Output:</strong> &quot;85&quot;
<strong>Explanation:</strong> The cost to paint the digit &#39;8&#39; is 7, and the digit &#39;5&#39; is 5. Then cost(&quot;85&quot;) = 7 + 5 = 12.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> cost = [2,4,6,2,4,6,4,4,4], target = 5
<strong>Output:</strong> &quot;0&quot;
<strong>Explanation:</strong> It&#39;s not possible to paint any integer with total cost equal to target.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> cost = [6,10,15,40,40,40,40,40,40], target = 47
<strong>Output:</strong> &quot;32211&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>cost.length == 9</code></li>
	<li><code>1 &lt;= cost[i] &lt;= 5000</code></li>
	<li><code>1 &lt;= target &lt;= 5000</code></li>
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
