# [307. Range Sum Query - Mutable](https://leetcode.com/problems/range-sum-query-mutable)

[中文文档](/solution/0300-0399/0307.Range%20Sum%20Query%20-%20Mutable/README.md)

## Description

<p>Given an array <code>nums</code> and two types of queries where you should update the value of an index in the array, and retrieve the sum of a range in the array.</p>

<p>Implement the <code>NumArray</code> class:</p>

<ul>
	<li><code>NumArray(int[] nums)</code> initializes the object with the integer array <code>nums</code>.</li>
	<li><code>void update(int index, int val)</code> updates the value of <code>nums[index]</code> to be <code>val</code>.</li>
	<li><code>int sumRange(int left, int right)</code> returns the sum of the subarray <code>nums[left, right]</code> (i.e., <code>nums[left] + nums[left + 1], ..., nums[right]</code>).</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;NumArray&quot;, &quot;sumRange&quot;, &quot;update&quot;, &quot;sumRange&quot;]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
<strong>Output</strong>
[null, 9, null, 8]

<strong>Explanation</strong>
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // return 9 = sum([1,3,5])
numArray.update(1, 2);   // nums = [1,2,5]
numArray.sumRange(0, 2); // return 8 = sum([1,2,5])
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>0 &lt;= index &lt; nums.length</code></li>
	<li><code>-100 &lt;= val &lt;= 100</code></li>
	<li><code>0 &lt;= left &lt;= right &lt; nums.length</code></li>
	<li>At most <code>3 * 10<sup>4</sup></code> calls will be made to <code>update</code> and <code>sumRange</code>.</li>
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
