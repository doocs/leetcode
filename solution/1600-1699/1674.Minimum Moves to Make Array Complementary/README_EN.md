# [1674. Minimum Moves to Make Array Complementary](https://leetcode.com/problems/minimum-moves-to-make-array-complementary)

[中文文档](/solution/1600-1699/1674.Minimum%20Moves%20to%20Make%20Array%20Complementary/README.md)

## Description

<p>You are given an integer array <code>nums</code> of <strong>even</strong> length <code>n</code> and an integer <code>limit</code>. In one move, you can replace any integer from <code>nums</code> with another integer between <code>1</code> and <code>limit</code>, inclusive.</p>

<p>The array <code>nums</code> is <strong>complementary</strong> if for all indices <code>i</code> (<strong>0-indexed</strong>), <code>nums[i] + nums[n - 1 - i]</code> equals the same number. For example, the array <code>[1,2,3,4]</code> is complementary because for all indices <code>i</code>, <code>nums[i] + nums[n - 1 - i] = 5</code>.</p>

<p>Return the <em><strong>minimum</strong> number of moves required to make </em><code>nums</code><em> <strong>complementary</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,4,3], limit = 4
<strong>Output:</strong> 1
<strong>Explanation:</strong> In 1 move, you can change nums to [1,2,<u>2</u>,3] (underlined elements are changed).
nums[0] + nums[3] = 1 + 3 = 4.
nums[1] + nums[2] = 2 + 2 = 4.
nums[2] + nums[1] = 2 + 2 = 4.
nums[3] + nums[0] = 3 + 1 = 4.
Therefore, nums[i] + nums[n-1-i] = 4 for every i, so nums is complementary.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2,1], limit = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> In 2 moves, you can change nums to [<u>2</u>,2,2,<u>2</u>]. You cannot change any number to 3 since 3 &gt; limit.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1,2], limit = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong> nums is already complementary.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n&nbsp;&lt;=&nbsp;10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i]&nbsp;&lt;= limit &lt;=&nbsp;10<sup>5</sup></code></li>
	<li><code>n</code> is even.</li>
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
