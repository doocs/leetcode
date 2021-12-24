# [857. Minimum Cost to Hire K Workers](https://leetcode.com/problems/minimum-cost-to-hire-k-workers)

[中文文档](/solution/0800-0899/0857.Minimum%20Cost%20to%20Hire%20K%20Workers/README.md)

## Description

<p>There are <code>N</code> workers.&nbsp; The <code>i</code>-th worker has a <code>quality[i]</code> and a minimum wage expectation <code>wage[i]</code>.</p>

<p>Now we want to hire exactly <code>K</code>&nbsp;workers to form a <em>paid group</em>.&nbsp; When hiring a group of K workers, we must pay them according to the following rules:</p>

<ol>
	<li>Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.</li>
	<li>Every worker in the paid group must be paid at least their minimum wage expectation.</li>
</ol>

<p>Return the least amount of money needed to form a paid group satisfying the above conditions.</p>

<p>&nbsp;</p>

<ol>

</ol>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>quality = <span id="example-input-1-1">[10,20,5]</span>, wage = <span id="example-input-1-2">[70,50,30]</span>, K = <span id="example-input-1-3">2</span>

<strong>Output: </strong><span id="example-output-1">105.00000

<strong>Explanation</strong>: </span><span>We pay 70 to 0-th worker and 35 to 2-th worker.</span>

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>quality = <span id="example-input-2-1">[3,1,10,10,1]</span>, wage = <span id="example-input-2-2">[4,8,2,2,7]</span>, K = <span id="example-input-2-3">3</span>

<strong>Output: </strong><span id="example-output-2">30.66667

<strong>Explanation</strong>: </span><span>We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.</span> 

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= K &lt;= N &lt;= 10000</code>, where <code>N = quality.length = wage.length</code></li>
	<li><code>1 &lt;= quality[i] &lt;= 10000</code></li>
	<li><code>1 &lt;= wage[i] &lt;= 10000</code></li>
	<li>Answers within <code>10^-5</code> of the correct answer will be considered correct.</li>
</ol>

</div>

</div>

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
