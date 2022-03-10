# [1562. Find Latest Group of Size M](https://leetcode.com/problems/find-latest-group-of-size-m)

[中文文档](/solution/1500-1599/1562.Find%20Latest%20Group%20of%20Size%20M/README.md)

## Description

<p>Given an array <code>arr</code> that represents a permutation of numbers from <code>1</code> to <code>n</code>.</p>

<p>You have a binary string of size <code>n</code> that initially has all its bits set to zero. At each step <code>i</code> (assuming both the binary string and <code>arr</code> are 1-indexed) from <code>1</code> to <code>n</code>, the bit at position <code>arr[i]</code> is set to <code>1</code>.</p>

<p>You are also given an integer <code>m</code>. Find the latest step at which there exists a group of ones of length <code>m</code>. A group of ones is a contiguous substring of <code>1</code>&#39;s such that it cannot be extended in either direction.</p>

<p>Return <em>the latest step at which there exists a group of ones of length <strong>exactly</strong></em> <code>m</code>. <em>If no such group exists, return</em> <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,5,1,2,4], m = 1
<strong>Output:</strong> 4
<strong>Explanation:</strong> 
Step 1: &quot;00<u>1</u>00&quot;, groups: [&quot;1&quot;]
Step 2: &quot;0010<u>1</u>&quot;, groups: [&quot;1&quot;, &quot;1&quot;]
Step 3: &quot;<u>1</u>0101&quot;, groups: [&quot;1&quot;, &quot;1&quot;, &quot;1&quot;]
Step 4: &quot;1<u>1</u>101&quot;, groups: [&quot;111&quot;, &quot;1&quot;]
Step 5: &quot;111<u>1</u>1&quot;, groups: [&quot;11111&quot;]
The latest step at which there exists a group of size 1 is step 4.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,1,5,4,2], m = 2
<strong>Output:</strong> -1
<strong>Explanation:</strong> 
Step 1: &quot;00<u>1</u>00&quot;, groups: [&quot;1&quot;]
Step 2: &quot;<u>1</u>0100&quot;, groups: [&quot;1&quot;, &quot;1&quot;]
Step 3: &quot;1010<u>1</u>&quot;, groups: [&quot;1&quot;, &quot;1&quot;, &quot;1&quot;]
Step 4: &quot;101<u>1</u>1&quot;, groups: [&quot;1&quot;, &quot;111&quot;]
Step 5: &quot;1<u>1</u>111&quot;, groups: [&quot;11111&quot;]
No group of size 2 exists during any step.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == arr.length</code></li>
	<li><code>1 &lt;= m &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= n</code></li>
	<li>All integers in <code>arr</code> are <strong>distinct</strong>.</li>
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
