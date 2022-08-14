# [1643. Kth Smallest Instructions](https://leetcode.com/problems/kth-smallest-instructions)

[中文文档](/solution/1600-1699/1643.Kth%20Smallest%20Instructions/README.md)

## Description

<p>Bob is standing at cell <code>(0, 0)</code>, and he wants to reach <code>destination</code>: <code>(row, column)</code>. He can only travel <strong>right</strong> and <strong>down</strong>. You are going to help Bob by providing <strong>instructions</strong> for him to reach <code>destination</code>.</p>

<p>The <strong>instructions</strong> are represented as a string, where each character is either:</p>

<ul>
	<li><code>&#39;H&#39;</code>, meaning move horizontally (go <strong>right</strong>), or</li>
	<li><code>&#39;V&#39;</code>, meaning move vertically (go <strong>down</strong>).</li>
</ul>

<p>Multiple <strong>instructions</strong> will lead Bob to <code>destination</code>. For example, if <code>destination</code> is <code>(2, 3)</code>, both <code>&quot;HHHVV&quot;</code> and <code>&quot;HVHVH&quot;</code> are valid <strong>instructions</strong>.</p>

<p>However, Bob is very picky. Bob has a lucky number <code>k</code>, and he wants the <code>k<sup>th</sup></code> <strong>lexicographically smallest instructions</strong> that will lead him to <code>destination</code>. <code>k</code> is <strong>1-indexed</strong>.</p>

<p>Given an integer array <code>destination</code> and an integer <code>k</code>, return <em>the </em><code>k<sup>th</sup></code><em> <strong>lexicographically smallest instructions</strong> that will take Bob to </em><code>destination</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1643.Kth%20Smallest%20Instructions/images/ex1.png" style="width: 300px; height: 229px;" /></p>

<pre>
<strong>Input:</strong> destination = [2,3], k = 1
<strong>Output:</strong> &quot;HHHVV&quot;
<strong>Explanation:</strong> All the instructions that reach (2, 3) in lexicographic order are as follows:
[&quot;HHHVV&quot;, &quot;HHVHV&quot;, &quot;HHVVH&quot;, &quot;HVHHV&quot;, &quot;HVHVH&quot;, &quot;HVVHH&quot;, &quot;VHHHV&quot;, &quot;VHHVH&quot;, &quot;VHVHH&quot;, &quot;VVHHH&quot;].
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1643.Kth%20Smallest%20Instructions/images/ex2.png" style="width: 300px; height: 229px;" /></strong></p>

<pre>
<strong>Input:</strong> destination = [2,3], k = 2
<strong>Output:</strong> &quot;HHVHV&quot;
</pre>

<p><strong>Example 3:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1643.Kth%20Smallest%20Instructions/images/ex3.png" style="width: 300px; height: 229px;" /></strong></p>

<pre>
<strong>Input:</strong> destination = [2,3], k = 3
<strong>Output:</strong> &quot;HHVVH&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>destination.length == 2</code></li>
	<li><code>1 &lt;= row, column &lt;= 15</code></li>
	<li><code>1 &lt;= k &lt;= nCr(row + column, row)</code>, where <code>nCr(a, b)</code> denotes <code>a</code> choose <code>b</code>​​​​​.</li>
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
