# [1714. Sum Of Special Evenly-Spaced Elements In Array](https://leetcode.com/problems/sum-of-special-evenly-spaced-elements-in-array)

[中文文档](/solution/1700-1799/1714.Sum%20Of%20Special%20Evenly-Spaced%20Elements%20In%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> consisting of <code>n</code> non-negative integers.</p>

<p>You are also given an array <code>queries</code>, where <code>queries[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>. The answer to the <code>i<sup>th</sup></code> query is the sum of all <code>nums[j]</code> where <code>x<sub>i</sub> &lt;= j &lt; n</code> and <code>(j - x<sub>i</sub>)</code> is divisible by <code>y<sub>i</sub></code>.</p>

<p>Return <em>an array </em><code>answer</code><em> where </em><code>answer.length == queries.length</code><em> and </em><code>answer[i]</code><em> is the answer to the </em><code>i<sup>th</sup></code><em> query <b>modulo</b> </em><code>10<sup>9 </sup>+ 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,2,3,4,5,6,7], queries = [[0,3],[5,1],[4,2]]
<strong>Output:</strong> [9,18,10]
<strong>Explanation:</strong> The answers of the queries are as follows:
1) The j indices that satisfy this query are 0, 3, and 6. nums[0] + nums[3] + nums[6] = 9
2) The j indices that satisfy this query are 5, 6, and 7. nums[5] + nums[6] + nums[7] = 18
3) The j indices that satisfy this query are 4 and 6. nums[4] + nums[6] = 10
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [100,200,101,201,102,202,103,203], queries = [[0,7]]
<strong>Output:</strong> [303]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 1.5 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= x<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= y<sub>i</sub> &lt;= 5 * 10<sup>4</sup></code></li>
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
