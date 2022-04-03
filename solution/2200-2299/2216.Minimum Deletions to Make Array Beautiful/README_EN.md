# [2216. Minimum Deletions to Make Array Beautiful](https://leetcode.com/problems/minimum-deletions-to-make-array-beautiful)

[中文文档](/solution/2200-2299/2216.Minimum%20Deletions%20to%20Make%20Array%20Beautiful/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. The array <code>nums</code> is <strong>beautiful</strong> if:</p>

<ul>
	<li><code>nums.length</code> is even.</li>
	<li><code>nums[i] != nums[i + 1]</code> for all <code>i % 2 == 0</code>.</li>
</ul>

<p>Note that an empty array is considered beautiful.</p>

<p>You can delete any number of elements from <code>nums</code>. When you delete an element, all the elements to the right of the deleted element will be <strong>shifted one unit to the left</strong> to fill the gap created and all the elements to the left of the deleted element will remain <strong>unchanged</strong>.</p>

<p>Return <em>the <strong>minimum</strong> number of elements to delete from </em><code>nums</code><em> to make it </em><em>beautiful.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2,3,5]
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can delete either <code>nums[0]</code> or <code>nums[1]</code> to make <code>nums</code> = [1,2,3,5] which is beautiful. It can be proven you need at least 1 deletion to make <code>nums</code> beautiful.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2,2,3,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can delete <code>nums[0]</code> and <code>nums[5]</code> to make nums = [1,2,2,3] which is beautiful. It can be proven you need at least 2 deletions to make nums beautiful.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
