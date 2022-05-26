# [1058. Minimize Rounding Error to Meet Target](https://leetcode.com/problems/minimize-rounding-error-to-meet-target)

[中文文档](/solution/1000-1099/1058.Minimize%20Rounding%20Error%20to%20Meet%20Target/README.md)

## Description

<p>Given an array of <code>prices</code> <code>[p<sub>1</sub>,p<sub>2</sub>...,p<sub>n</sub>]</code> and a <code>target</code>, round each price <code>p<sub>i</sub></code> to <code>Round<sub>i</sub>(p<sub>i</sub>)</code> so that the rounded array <code>[Round<sub>1</sub>(p<sub>1</sub>),Round<sub>2</sub>(p<sub>2</sub>)...,Round<sub>n</sub>(p<sub>n</sub>)]</code> sums to the given <code>target</code>. Each operation <code>Round<sub>i</sub>(p<sub>i</sub>)</code> could be either <code>Floor(p<sub>i</sub>)</code> or <code>Ceil(p<sub>i</sub>)</code>.</p>

<p>Return the string <code>&quot;-1&quot;</code> if the rounded array is impossible to sum to <code>target</code>. Otherwise, return the smallest rounding error, which is defined as <code>&Sigma; |Round<sub>i</sub>(p<sub>i</sub>) - (p<sub>i</sub>)|</code> for <italic><code>i</code></italic> from <code>1</code> to <italic><code>n</code></italic>, as a string with three places after the decimal.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [&quot;0.700&quot;,&quot;2.800&quot;,&quot;4.900&quot;], target = 8
<strong>Output:</strong> &quot;1.000&quot;
<strong>Explanation:</strong>
Use Floor, Ceil and Ceil operations to get (0.7 - 0) + (3 - 2.8) + (5 - 4.9) = 0.7 + 0.2 + 0.1 = 1.0 .
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [&quot;1.500&quot;,&quot;2.500&quot;,&quot;3.500&quot;], target = 10
<strong>Output:</strong> &quot;-1&quot;
<strong>Explanation:</strong> It is impossible to meet the target.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> prices = [&quot;1.500&quot;,&quot;2.500&quot;,&quot;3.500&quot;], target = 9
<strong>Output:</strong> &quot;1.500&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 500</code></li>
	<li>Each string&nbsp;<code>prices[i]</code> represents a real number in the range <code>[0.0, 1000.0]</code> and has exactly 3 decimal places.</li>
	<li><code>0 &lt;= target &lt;= 10<sup>6</sup></code></li>
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
