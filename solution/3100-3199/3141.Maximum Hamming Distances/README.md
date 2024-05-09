# [3141. Maximum Hamming Distances 🔒](https://leetcode.cn/problems/maximum-hamming-distances)

[English Version](/solution/3100-3199/3141.Maximum%20Hamming%20Distances/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>Given an array <code>nums</code> and an integer <code>m</code>, with each element <code>nums[i]</code> satisfying <code>0 &lt;= nums[i] &lt; 2<sup>m</sup></code>, return an array <code>answer</code>. The <code>answer</code> array should be of the same length as <code>nums</code>, where each element <code>answer[i]</code> represents the <em>maximum</em> <strong>Hamming distance </strong>between <code>nums[i]</code> and any other element <code>nums[j]</code> in the array.</p>

<p>The <strong>Hamming distance</strong> between two binary integers is defined as the number of positions at which the corresponding bits differ (add leading zeroes if needed).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [9,12,9,11], m = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,3,2,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>The binary representation of <code>nums = [1001,1100,1001,1011]</code>.</p>

<p>The maximum hamming distances for each index are:</p>

<ul>
	<li><code>nums[0]</code>: 1001 and 1100 have a distance of 2.</li>
	<li><code>nums[1]</code>: 1100 and 1011 have a distance of 3.</li>
	<li><code>nums[2]</code>: 1001 and 1100 have a distance of 2.</li>
	<li><code>nums[3]</code>: 1011 and 1100 have a distance of 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,4,6,10], m = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,3,2,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>The binary representation of <code>nums = [0011,0100,0110,1010]</code>.</p>

<p>The maximum hamming distances for each index are:</p>

<ul>
	<li><code>nums[0]</code>: 0011 and 0100 have a distance of 3.</li>
	<li><code>nums[1]</code>: 0100 and 0011 have a distance of 3.</li>
	<li><code>nums[2]</code>: 0110 and 1010 have a distance of 2.</li>
	<li><code>nums[3]</code>: 1010 and 0100 have a distance of 3.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m &lt;= 17</code></li>
	<li><code>2 &lt;= nums.length &lt;= 2<sup>m</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt; 2<sup>m</sup></code></li>
</ul>

## 解法

### 方法一

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
