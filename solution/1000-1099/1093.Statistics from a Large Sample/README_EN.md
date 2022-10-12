# [1093. Statistics from a Large Sample](https://leetcode.com/problems/statistics-from-a-large-sample)

[中文文档](/solution/1000-1099/1093.Statistics%20from%20a%20Large%20Sample/README.md)

## Description

<p>You are given a large sample of integers in the range <code>[0, 255]</code>. Since the sample is so large, it is represented by an array <code>count</code>&nbsp;where <code>count[k]</code> is the <strong>number of times</strong> that <code>k</code> appears in the sample.</p>

<p>Calculate the following statistics:</p>

<ul>
	<li><code>minimum</code>: The minimum element in the sample.</li>
	<li><code>maximum</code>: The maximum element in the sample.</li>
	<li><code>mean</code>: The average of the sample, calculated as the total sum of all elements divided by the total number of elements.</li>
	<li><code>median</code>:
	<ul>
		<li>If the sample has an odd number of elements, then the <code>median</code> is the middle element once the sample is sorted.</li>
		<li>If the sample has an even number of elements, then the <code>median</code> is the average of the two middle elements once the sample is sorted.</li>
	</ul>
	</li>
	<li><code>mode</code>: The number that appears the most in the sample. It is guaranteed to be <strong>unique</strong>.</li>
</ul>

<p>Return <em>the statistics of the sample as an array of floating-point numbers </em><code>[minimum, maximum, mean, median, mode]</code><em>. Answers within </em><code>10<sup>-5</sup></code><em> of the actual answer will be accepted.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
<strong>Output:</strong> [1.00000,3.00000,2.37500,2.50000,3.00000]
<strong>Explanation:</strong> The sample represented by count is [1,2,2,2,3,3,3,3].
The minimum and maximum are 1 and 3 respectively.
The mean is (1+2+2+2+3+3+3+3) / 8 = 19 / 8 = 2.375.
Since the size of the sample is even, the median is the average of the two middle elements 2 and 3, which is 2.5.
The mode is 3 as it appears the most in the sample.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> count = [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
<strong>Output:</strong> [1.00000,4.00000,2.18182,2.00000,1.00000]
<strong>Explanation:</strong> The sample represented by count is [1,1,1,1,2,2,2,3,3,4,4].
The minimum and maximum are 1 and 4 respectively.
The mean is (1+1+1+1+2+2+2+3+3+4+4) / 11 = 24 / 11 = 2.18181818... (for display purposes, the output shows the rounded number 2.18182).
Since the size of the sample is odd, the median is the middle element 2.
The mode is 1 as it appears the most in the sample.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>count.length == 256</code></li>
	<li><code>0 &lt;= count[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= sum(count) &lt;= 10<sup>9</sup></code></li>
	<li>The mode of the sample that <code>count</code> represents is <strong>unique</strong>.</li>
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
