# [10038. Maximize the Number of Partitions After Operations](https://leetcode.com/problems/maximize-the-number-of-partitions-after-operations)

[中文文档](/solution/10000-10099/10038.Maximize%20the%20Number%20of%20Partitions%20After%20Operations/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>s</code> and an integer <code>k</code>.</p>

<p>You are to perform the following partitioning operations until <code>s</code> is <strong>empty</strong>:</p>

<ul>
	<li>Choose the <strong>longest</strong> <strong>prefix</strong> of <code>s</code> containing at most <code>k</code> <strong>distinct</strong> characters.</li>
	<li><strong>Delete</strong> the prefix from <code>s</code> and increase the number of partitions by one. The remaining characters (if any) in <code>s</code> maintain their initial order.</li>
</ul>

<p><strong>Before</strong> the operations, you are allowed to change <strong>at most</strong> <strong>one</strong> index in <code>s</code> to another lowercase English letter.</p>

<p>Return <em>an integer denoting the <strong>maximum</strong> number of resulting partitions after the operations by optimally choosing at most one index to change.</em></p>
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;accca&quot;, k = 2
<strong>Output:</strong> 3
<strong>Explanation: </strong>In this example, to maximize the number of resulting partitions, s[2] can be changed to &#39;b&#39;.
s becomes &quot;acbca&quot;.
The operations can now be performed as follows until s becomes empty:
- Choose the longest prefix containing at most 2 distinct characters, &quot;<u>ac</u>bca&quot;.
- Delete the prefix, and s becomes &quot;bca&quot;. The number of partitions is now 1.
- Choose the longest prefix containing at most 2 distinct characters, &quot;<u>bc</u>a&quot;.
- Delete the prefix, and s becomes &quot;a&quot;. The number of partitions is now 2.
- Choose the longest prefix containing at most 2 distinct characters, &quot;<u>a</u>&quot;.
- Delete the prefix, and s becomes empty. The number of partitions is now 3.
Hence, the answer is 3.
It can be shown that it is not possible to obtain more than 3 partitions.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabaab&quot;, k = 3
<strong>Output:</strong> 1
<strong>Explanation: </strong>In this example, to maximize the number of resulting partitions we can leave s as it is.
The operations can now be performed as follows until s becomes empty: 
- Choose the longest prefix containing at most 3 distinct characters, &quot;<u>aabaab</u>&quot;.
- Delete the prefix, and s becomes empty. The number of partitions becomes 1. 
Hence, the answer is 1. 
It can be shown that it is not possible to obtain more than 1 partition.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;xxyz&quot;, k = 1
<strong>Output:</strong> 4
<strong>Explanation:</strong> In this example, to maximize the number of resulting partitions, s[1] can be changed to &#39;a&#39;.
s becomes &quot;xayz&quot;.
The operations can now be performed as follows until s becomes empty:
- Choose the longest prefix containing at most 1 distinct character, &quot;<u>x</u>ayz&quot;.
- Delete the prefix, and s becomes &quot;ayz&quot;. The number of partitions is now 1.
- Choose the longest prefix containing at most 1 distinct character, &quot;<u>a</u>yz&quot;.
- Delete the prefix, and s becomes &quot;yz&quot;. The number of partitions is now 2.
- Choose the longest prefix containing at most 1 distinct character, &quot;<u>y</u>z&quot;.
- Delete the prefix, and s becomes &quot;z&quot;. The number of partitions is now 3.
- Choose the longest prefix containing at most 1 distinct character, &quot;<u>z</u>&quot;.
- Delete the prefix, and s becomes empty. The number of partitions is now 4.
Hence, the answer is 4.
It can be shown that it is not possible to obtain more than 4 partitions.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
	<li><code>1 &lt;= k &lt;= 26</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
