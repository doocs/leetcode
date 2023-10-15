# [2902. Count of Sub-Multisets With Bounded Sum](https://leetcode.com/problems/count-of-sub-multisets-with-bounded-sum)

[中文文档](/solution/2900-2999/2902.Count%20of%20Sub-Multisets%20With%20Bounded%20Sum/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> of non-negative integers, and two integers <code>l</code> and <code>r</code>.</p>

<p>Return <em>the <strong>count of sub-multisets</strong> within</em> <code>nums</code> <em>where the sum of elements in each subset falls within the inclusive range of</em> <code>[l, r]</code>.</p>

<p>Since the answer may be large, return it modulo <code>10<sup>9 </sup>+ 7</code>.</p>

<p>A <strong>sub-multiset</strong> is an <strong>unordered</strong> collection of elements of the array in which a given value <code>x</code> can occur <code>0, 1, ..., occ[x]</code> times, where <code>occ[x]</code> is the number of occurrences of <code>x</code> in the array.</p>

<p><strong>Note</strong> that:</p>

<ul>
	<li>Two <strong>sub-multisets</strong> are the same if sorting both sub-multisets results in identical multisets.</li>
	<li>The sum of an <strong>empty</strong> multiset is <code>0</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2,3], l = 6, r = 6
<strong>Output:</strong> 1
<strong>Explanation:</strong> The only subset of nums that has a sum of 6 is {1, 2, 3}.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,4,2,7], l = 1, r = 5
<strong>Output:</strong> 7
<strong>Explanation:</strong> The subsets of nums that have a sum within the range [1, 5] are {1}, {2}, {4}, {2, 2}, {1, 2}, {1, 4}, and {1, 2, 2}.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1,3,5,2], l = 3, r = 5
<strong>Output:</strong> 9
<strong>Explanation:</strong> The subsets of nums that have a sum within the range [3, 5] are {3}, {5}, {1, 2}, {1, 3}, {2, 2}, {2, 3}, {1, 1, 2}, {1, 1, 3}, and {1, 2, 2}.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 2 * 10<sup>4</sup></code></li>
	<li>Sum of <code>nums</code> does not exceed <code>2 * 10<sup>4</sup></code>.</li>
	<li><code>0 &lt;= l &lt;= r &lt;= 2 * 10<sup>4</sup></code></li>
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
