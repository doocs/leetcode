# [978. Longest Turbulent Subarray](https://leetcode.com/problems/longest-turbulent-subarray)

[中文文档](/solution/0900-0999/0978.Longest%20Turbulent%20Subarray/README.md)

## Description

<p>Given an integer array <code>arr</code>, return <em>the length of a maximum size turbulent subarray of</em> <code>arr</code>.</p>

<p>A subarray is <strong>turbulent</strong> if the comparison sign flips between each adjacent pair of elements in the subarray.</p>

<p>More formally, a subarray <code>[arr[i], arr[i + 1], ..., arr[j]]</code> of <code>arr</code> is said to be turbulent if and only if:</p>

<ul>
	<li>For <code>i &lt;= k &lt; j</code>:
    <ul>
    	<li><code>arr[k] &gt; arr[k + 1]</code> when <code>k</code> is odd, and</li>
    	<li><code>arr[k] &lt; arr[k + 1]</code> when <code>k</code> is even.</li>
    </ul>
    </li>
    <li>Or, for <code>i &lt;= k &lt; j</code>:
    <ul>
    	<li><code>arr[k] &gt; arr[k + 1]</code> when <code>k</code> is even, and</li>
    	<li><code>arr[k] &lt; arr[k + 1]</code> when <code>k</code> is odd.</li>
    </ul>
    </li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [9,4,2,10,7,8,8,1,9]
<strong>Output:</strong> 5
<strong>Explanation:</strong> arr[1] &gt; arr[2] &lt; arr[3] &gt; arr[4] &lt; arr[5]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [4,8,12,16]
<strong>Output:</strong> 2
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [100]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
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
