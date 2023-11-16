# [2936. Number of Equal Numbers Blocks](https://leetcode.cn/problems/number-of-equal-numbers-blocks)

[English Version](/solution/2900-2999/2936.Number%20of%20Equal%20Numbers%20Blocks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given a <strong>0-indexed</strong> array of integers, <code>nums</code>. The following property holds for <code>nums</code>:</p>

<ul>
	<li>All occurrences of a value are adjacent. In other words, if there are two indices <code>i &lt; j</code> such that <code>nums[i] == nums[j]</code>, then for every index <code>k</code> that <code>i &lt; k &lt; j</code>, <code>nums[k] == nums[i]</code>.</li>
</ul>

<p>Since <code>nums</code> is a very large array, you are given an instance of the class <code>BigArray</code> which has the following functions:</p>

<ul>
	<li><code>int at(long long index)</code>: Returns the value of <code>nums[i]</code>.</li>
	<li><code>void size()</code>: Returns <code>nums.length</code>.</li>
</ul>

<p>Let&#39;s partition the array into <strong>maximal</strong> blocks such that each block contains <strong>equal values</strong>. Return<em> the number of these blocks.</em></p>

<p><strong>Note</strong> that if you want to test your solution using a custom test, behavior for tests with <code>nums.length &gt; 10</code> is undefined.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3,3,3,3]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only one block here which is the whole array (because all numbers are equal) and that is: [<u>3,3,3,3,3</u>]. So the answer would be 1. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,3,9,9,9,2,10,10]
<strong>Output:</strong> 5
<strong>Explanation:</strong> There are 5 blocks here:
Block number 1: [<u>1,1,1</u>,3,9,9,9,2,10,10]
Block number 2: [1,1,1,<u>3</u>,9,9,9,2,10,10]
Block number 3: [1,1,1,3,<u>9,9,9</u>,2,10,10]
Block number 4: [1,1,1,3,9,9,9,<u>2</u>,10,10]
Block number 5: [1,1,1,3,9,9,9,2,<u>10,10</u>]
So the answer would be 5.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6,7]
<strong>Output:</strong> 7
<strong>Explanation:</strong> Since all numbers are distinct, there are 7 blocks here and each element representing one block. So the answer would be 7. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>15</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>The input is generated such that all equal values are adjacent.</li>
	<li>The sum of the elements of&nbsp;<code>nums</code>&nbsp;is at most&nbsp;<code>10<sup>15</sup></code>.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
