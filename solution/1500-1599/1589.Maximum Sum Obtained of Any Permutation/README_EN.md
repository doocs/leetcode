# [1589. Maximum Sum Obtained of Any Permutation](https://leetcode.com/problems/maximum-sum-obtained-of-any-permutation)

[中文文档](/solution/1500-1599/1589.Maximum%20Sum%20Obtained%20of%20Any%20Permutation/README.md)

## Description

<p>We have an array of integers, <code>nums</code>, and an array of <code>requests</code> where <code>requests[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>. The <code>i<sup>th</sup></code> request asks for the sum of <code>nums[start<sub>i</sub>] + nums[start<sub>i</sub> + 1] + ... + nums[end<sub>i</sub> - 1] + nums[end<sub>i</sub>]</code>. Both <code>start<sub>i</sub></code> and <code>end<sub>i</sub></code> are <em>0-indexed</em>.</p>

<p>Return <em>the maximum total sum of all requests <strong>among all permutations</strong> of</em> <code>nums</code>.</p>

<p>Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5], requests = [[1,3],[0,1]]
<strong>Output:</strong> 19
<strong>Explanation:</strong> One permutation of nums is [2,1,3,4,5] with the following result: 
requests[0] -&gt; nums[1] + nums[2] + nums[3] = 1 + 3 + 4 = 8
requests[1] -&gt; nums[0] + nums[1] = 2 + 1 = 3
Total sum: 8 + 3 = 11.
A permutation with a higher total sum is [3,5,4,2,1] with the following result:
requests[0] -&gt; nums[1] + nums[2] + nums[3] = 5 + 4 + 2 = 11
requests[1] -&gt; nums[0] + nums[1] = 3 + 5  = 8
Total sum: 11 + 8 = 19, which is the best that you can do.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6], requests = [[0,1]]
<strong>Output:</strong> 11
<strong>Explanation:</strong> A permutation with the max total sum is [6,5,4,3,2,1] with request sums [11].</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,10], requests = [[0,2],[1,3],[1,1]]
<strong>Output:</strong> 47
<strong>Explanation:</strong> A permutation with the max total sum is [4,10,5,3,2,1] with request sums [19,18,10].</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i]&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= requests.length &lt;=&nbsp;10<sup>5</sup></code></li>
	<li><code>requests[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub>&nbsp;&lt;= end<sub>i</sub>&nbsp;&lt;&nbsp;n</code></li>
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