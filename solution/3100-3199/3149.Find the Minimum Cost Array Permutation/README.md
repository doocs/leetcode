# [3149. 找出分数最低的排列](https://leetcode.cn/problems/find-the-minimum-cost-array-permutation)

[English Version](/solution/3100-3199/3149.Find%20the%20Minimum%20Cost%20Array%20Permutation/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组 <code>nums</code> ，它是 <code>[0, 1, 2, ..., n - 1]</code> 的一个<span data-keyword="permutation">排列</span> 。对于任意一个 <code>[0, 1, 2, ..., n - 1]</code> 的排列 <code>perm</code> ，其 <strong>分数 </strong>定义为：</p>

<p><code>score(perm) = |perm[0] - nums[perm[1]]| + |perm[1] - nums[perm[2]]| + ... + |perm[n - 1] - nums[perm[0]]|</code></p>

<p>返回具有<strong> 最低</strong> 分数的排列 <code>perm</code> 。如果存在多个满足题意且分数相等的排列，则返回其中<span data-keyword="lexicographically-smaller-array">字典序最小</span>的一个。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,0,2]</span></p>

<p><strong>输出：</strong><span class="example-io">[0,1,2]</span></p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3149.Find%20the%20Minimum%20Cost%20Array%20Permutation/images/example0gif.gif" style="width: 235px; height: 235px;" /></strong></p>

<p>字典序最小且分数最低的排列是 <code>[0,1,2]</code>。这个排列的分数是 <code>|0 - 0| + |1 - 2| + |2 - 1| = 2</code> 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [0,2,1]</span></p>

<p><strong>输出：</strong><span class="example-io">[0,2,1]</span></p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3149.Find%20the%20Minimum%20Cost%20Array%20Permutation/images/example1gif.gif" style="width: 235px; height: 235px;" /></strong></p>

<p>字典序最小且分数最低的排列是 <code>[0,2,1]</code>。这个排列的分数是 <code>|0 - 1| + |2 - 2| + |1 - 0| = 2</code> 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 14</code></li>
	<li><code>nums</code> 是 <code>[0, 1, 2, ..., n - 1]</code> 的一个排列。</li>
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
