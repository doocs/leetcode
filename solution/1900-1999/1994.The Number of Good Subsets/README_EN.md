# [1994. The Number of Good Subsets](https://leetcode.com/problems/the-number-of-good-subsets)

[中文文档](/solution/1900-1999/1994.The%20Number%20of%20Good%20Subsets/README.md)

## Description

<p>You are given an integer array <code>nums</code>. We call a subset of <code>nums</code> <strong>good</strong> if its product can be represented as a product of one or more <strong>distinct prime</strong> numbers.</p>

<ul>
	<li>For example, if <code>nums = [1, 2, 3, 4]</code>:
	<ul>
		<li><code>[2, 3]</code>, <code>[1, 2, 3]</code>, and <code>[1, 3]</code> are <strong>good</strong> subsets with products <code>6 = 2*3</code>, <code>6 = 2*3</code>, and <code>3 = 3</code> respectively.</li>
		<li><code>[1, 4]</code> and <code>[4]</code> are not <strong>good</strong> subsets with products <code>4 = 2*2</code> and <code>4 = 2*2</code> respectively.</li>
	</ul>
	</li>
</ul>

<p>Return <em>the number of different <strong>good</strong> subsets in </em><code>nums</code><em> <strong>modulo</strong> </em><code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>subset</strong> of <code>nums</code> is any array that can be obtained by deleting some (possibly none or all) elements from <code>nums</code>. Two subsets are different if and only if the chosen indices to delete are different.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The good subsets are:
- [1,2]: product is 2, which is the product of distinct prime 2.
- [1,2,3]: product is 6, which is the product of distinct primes 2 and 3.
- [1,3]: product is 3, which is the product of distinct prime 3.
- [2]: product is 2, which is the product of distinct prime 2.
- [2,3]: product is 6, which is the product of distinct primes 2 and 3.
- [3]: product is 3, which is the product of distinct prime 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,2,3,15]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The good subsets are:
- [2]: product is 2, which is the product of distinct prime 2.
- [2,3]: product is 6, which is the product of distinct primes 2 and 3.
- [2,15]: product is 30, which is the product of distinct primes 2, 3, and 5.
- [3]: product is 3, which is the product of distinct prime 3.
- [15]: product is 15, which is the product of distinct primes 3 and 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 30</code></li>
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
