# [3040. Maximum Number of Operations With the Same Score II](https://leetcode.com/problems/maximum-number-of-operations-with-the-same-score-ii)

[中文文档](/solution/3000-3099/3040.Maximum%20Number%20of%20Operations%20With%20the%20Same%20Score%20II/README.md)

<!-- tags: -->

## Description

<p>Given an array of integers called <code>nums</code>, you can perform <strong>any</strong> of the following operation while <code>nums</code> contains <strong>at least</strong> <code>2</code> elements:</p>

<ul>
	<li>Choose the first two elements of <code>nums</code> and delete them.</li>
	<li>Choose the last two elements of <code>nums</code> and delete them.</li>
	<li>Choose the first and the last elements of <code>nums</code> and delete them.</li>
</ul>

<p>The<strong> score</strong> of the operation is the sum of the deleted elements.</p>

<p>Your task is to find the <strong>maximum</strong> number of operations that can be performed, such that <strong>all operations have the same score</strong>.</p>

<p>Return <em>the <strong>maximum</strong> number of operations possible that satisfy the condition mentioned above</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,2,3,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We perform the following operations:
- Delete the first two elements, with score 3 + 2 = 5, nums = [1,2,3,4].
- Delete the first and the last elements, with score 1 + 4 = 5, nums = [2,3].
- Delete the first and the last elements, with score 2 + 3 = 5, nums = [].
We are unable to perform any more operations as nums is empty.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,6,1,4]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We perform the following operations:
- Delete the first two elements, with score 3 + 2 = 5, nums = [6,1,4].
- Delete the last two elements, with score 1 + 4 = 5, nums = [6].
It can be proven that we can perform at most 2 operations.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
