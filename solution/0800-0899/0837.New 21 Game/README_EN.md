# [837. New 21 Game](https://leetcode.com/problems/new-21-game)

[中文文档](/solution/0800-0899/0837.New%2021%20Game/README.md)

## Description

<p>Alice plays the following game, loosely based on the card game &quot;21&quot;.</p>

<p>Alice starts with <code>0</code> points, and draws numbers while she has less than <code>K</code> points.&nbsp; During each draw, she gains an integer number of points randomly from the range <code>[1, W]</code>, where <code>W</code> is an integer.&nbsp; Each draw is independent and the outcomes have equal probabilities.</p>

<p>Alice stops drawing numbers when she gets <code>K</code> or more points.&nbsp; What is the probability&nbsp;that she has <code>N</code> or less points?</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>N = 10, K = 1, W = 10

<strong>Output: </strong>1.00000

<strong>Explanation: </strong> Alice gets a single card, then stops.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>N = 6, K = 1, W = 10

<strong>Output: </strong>0.60000

<strong>Explanation: </strong> Alice gets a single card, then stops.

In 6 out of W = 10 possibilities, she is at or below N = 6 points.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong>N = 21, K = 17, W = 10

<strong>Output: </strong>0.73278</pre>

<p><strong>Note:</strong></p>

<ol>
	<li><code>0 &lt;= K &lt;= N &lt;= 10000</code></li>
	<li><code>1 &lt;= W &lt;= 10000</code></li>
	<li>Answers will be accepted as correct if they are within <code>10^-5</code> of the correct answer.</li>
	<li>The judging time limit has been reduced for this question.</li>
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
