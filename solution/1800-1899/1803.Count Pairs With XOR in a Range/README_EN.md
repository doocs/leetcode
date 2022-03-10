# [1803. Count Pairs With XOR in a Range](https://leetcode.com/problems/count-pairs-with-xor-in-a-range)

[中文文档](/solution/1800-1899/1803.Count%20Pairs%20With%20XOR%20in%20a%20Range/README.md)

## Description

<p>Given a <strong>(0-indexed)</strong> integer array <code>nums</code> and two integers <code>low</code> and <code>high</code>, return <em>the number of <strong>nice pairs</strong></em>.</p>

<p>A <strong>nice pair</strong> is a pair <code>(i, j)</code> where <code>0 &lt;= i &lt; j &lt; nums.length</code> and <code>low &lt;= (nums[i] XOR nums[j]) &lt;= high</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> nums = [1,4,2,7], low = 2, high = 6

<strong>Output:</strong> 6

<strong>Explanation:</strong> All nice pairs (i, j) are as follows:

    - (0, 1): nums[0] XOR nums[1] = 5 

    - (0, 2): nums[0] XOR nums[2] = 3

    - (0, 3): nums[0] XOR nums[3] = 6

    - (1, 2): nums[1] XOR nums[2] = 6

    - (1, 3): nums[1] XOR nums[3] = 3

    - (2, 3): nums[2] XOR nums[3] = 5

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> nums = [9,8,4,2,1], low = 5, high = 14

<strong>Output:</strong> 8

<strong>Explanation:</strong> All nice pairs (i, j) are as follows:

​​​​​    - (0, 2): nums[0] XOR nums[2] = 13

&nbsp;   - (0, 3): nums[0] XOR nums[3] = 11

&nbsp;   - (0, 4): nums[0] XOR nums[4] = 8

&nbsp;   - (1, 2): nums[1] XOR nums[2] = 12

&nbsp;   - (1, 3): nums[1] XOR nums[3] = 10

&nbsp;   - (1, 4): nums[1] XOR nums[4] = 9

&nbsp;   - (2, 3): nums[2] XOR nums[3] = 6

&nbsp;   - (2, 4): nums[2] XOR nums[4] = 5</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>

    <li><code>1 &lt;= nums[i] &lt;= 2 * 10<sup>4</sup></code></li>

    <li><code>1 &lt;= low &lt;= high &lt;= 2 * 10<sup>4</sup></code></li>

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
