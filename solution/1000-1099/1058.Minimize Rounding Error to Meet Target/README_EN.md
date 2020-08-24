# [1058. Minimize Rounding Error to Meet Target](https://leetcode.com/problems/minimize-rounding-error-to-meet-target)

[中文文档](/solution/1000-1099/1058.Minimize%20Rounding%20Error%20to%20Meet%20Target/README.md)

## Description
<p>Given an array of prices <code>[p<sub>1</sub>,p<sub>2</sub>...,p<sub>n</sub>]</code> and a <code>target</code>, round each price <code>p<sub>i</sub></code> to <code>Round<sub>i</sub>(p<sub>i</sub>)</code> so that the rounded array <code>[Round<sub>1</sub>(p<sub>1</sub>),Round<sub>2</sub>(p<sub>2</sub>)...,Round<sub>n</sub>(p<sub>n</sub>)]</code> sums to the given <code>target</code>. Each operation <code>Round<sub>i</sub>(p<sub>i</sub>)</code> could be either <code>Floor(p<sub>i</sub>)</code> or <code>Ceil(p<sub>i</sub>)</code>.</p>

<p>Return the string <code>"-1"</code> if the rounded array is impossible to sum to <code>target</code>. Otherwise, return the smallest rounding error, which is defined as Σ |Round<sub>i</sub>(p<sub>i</sub>) - (p<sub>i</sub>)| for <italic>i</italic> from 1 to <italic>n</italic>, as a string with three places after the decimal.</p>

<p> </p>

<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong>prices = <span id="example-input-1-1">["0.700","2.800","4.900"]</span>, target = <span id="example-input-1-2">8</span>
<strong>Output: </strong><span id="example-output-1">"1.000"</span>
<strong>Explanation: </strong>
Use Floor, Ceil and Ceil operations to get (0.7 - 0) + (3 - 2.8) + (5 - 4.9) = 0.7 + 0.2 + 0.1 = 1.0 .
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: </strong>prices = <span id="example-input-2-1">["1.500","2.500","3.500"]</span>, target = <span id="example-input-2-2">10</span>
<strong>Output: </strong><span id="example-output-2">"-1"</span>
<strong>Explanation: </strong>
It is impossible to meet the target.
</pre>

<p> </p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 <= prices.length <= 500</code>.</li>
	<li>Each string of prices <code>prices[i]</code> represents a real number which is between 0 and 1000 and has exactly 3 decimal places.</li>
	<li><code>target</code> is between 0 and 1000000.</li>
</ol>


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