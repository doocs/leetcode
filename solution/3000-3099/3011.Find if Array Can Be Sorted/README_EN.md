# [3011. Find if Array Can Be Sorted](https://leetcode.com/problems/find-if-array-can-be-sorted)

[中文文档](/solution/3000-3099/3011.Find%20if%20Array%20Can%20Be%20Sorted/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of <strong>positive</strong> integers <code>nums</code>.</p>

<p>In one <strong>operation</strong>, you can swap any two <strong>adjacent</strong> elements if they have the <strong>same</strong> number of <span data-keyword="set-bit">set bits</span>. You are allowed to do this operation <strong>any</strong> number of times (<strong>including zero</strong>).</p>

<p>Return <code>true</code> <em>if you can sort the array, else return </em><code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [8,4,2,30,15]
<strong>Output:</strong> true
<strong>Explanation:</strong> Let&#39;s look at the binary representation of every element. The numbers 2, 4, and 8 have one set bit each with binary representation &quot;10&quot;, &quot;100&quot;, and &quot;1000&quot; respectively. The numbers 15 and 30 have four set bits each with binary representation &quot;1111&quot; and &quot;11110&quot;.
We can sort the array using 4 operations:
- Swap nums[0] with nums[1]. This operation is valid because 8 and 4 have one set bit each. The array becomes [4,8,2,30,15].
- Swap nums[1] with nums[2]. This operation is valid because 8 and 2 have one set bit each. The array becomes [4,2,8,30,15].
- Swap nums[0] with nums[1]. This operation is valid because 4 and 2 have one set bit each. The array becomes [2,4,8,30,15].
- Swap nums[3] with nums[4]. This operation is valid because 30 and 15 have four set bits each. The array becomes [2,4,8,15,30].
The array has become sorted, hence we return true.
Note that there may be other sequences of operations which also sort the array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> true
<strong>Explanation:</strong> The array is already sorted, hence we return true.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,16,8,4,2]
<strong>Output:</strong> false
<strong>Explanation:</strong> It can be shown that it is not possible to sort the input array using any number of operations.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2<sup>8</sup></code></li>
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
