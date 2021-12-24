# [548. Split Array with Equal Sum](https://leetcode.com/problems/split-array-with-equal-sum)

[中文文档](/solution/0500-0599/0548.Split%20Array%20with%20Equal%20Sum/README.md)

## Description

<p>

Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:

<ol>

<li> 0 < i, i + 1 < j, j + 1 < k < n - 1 </li>

<li> Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal. </li>

</ol>

where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.

</p>

<p><b>Example:</b><br />

<pre>

<b>Input:</b> [1,2,1,2,1,2,1]

<b>Output:</b> True

<b>Explanation:</b>

i = 1, j = 3, k = 5. 

sum(0, i - 1) = sum(0, 0) = 1

sum(i + 1, j - 1) = sum(2, 2) = 1

sum(j + 1, k - 1) = sum(4, 4) = 1

sum(k + 1, n - 1) = sum(6, 6) = 1

</pre>

</p>

<b>Note:</b>

<ol>

<li> 1 <= n <= 2000. </li>

<li> Elements in the given array will be in range [-1,000,000, 1,000,000]. </li>

</ol>

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
