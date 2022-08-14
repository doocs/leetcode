# [1814. Count Nice Pairs in an Array](https://leetcode.com/problems/count-nice-pairs-in-an-array)

[中文文档](/solution/1800-1899/1814.Count%20Nice%20Pairs%20in%20an%20Array/README.md)

## Description

<p>You are given an array <code>nums</code> that consists of non-negative integers. Let us define <code>rev(x)</code> as the reverse of the non-negative integer <code>x</code>. For example, <code>rev(123) = 321</code>, and <code>rev(120) = 21</code>. A pair of indices <code>(i, j)</code> is <strong>nice</strong> if it satisfies all of the following conditions:</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; nums.length</code></li>
	<li><code>nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])</code></li>
</ul>

<p>Return <em>the number of nice pairs of indices</em>. Since that number can be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [42,11,1,97]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The two pairs are:
 - (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
 - (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [13,10,35,24,76]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
