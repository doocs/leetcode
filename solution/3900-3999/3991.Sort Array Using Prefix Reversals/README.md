---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3991.Sort%20Array%20Using%20Prefix%20Reversals/README.md
---

<!-- problem:start -->

# [3991. 使用前缀反转对数组进行排序 🔒](https://leetcode.cn/problems/sort-array-using-prefix-reversals)

[English Version](/solution/3900-3999/3991.Sort%20Array%20Using%20Prefix%20Reversals/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>，其中 <code>nums</code> 是区间 <code>[0, n - 1]</code> 内整数的一个&nbsp;<span data-keyword="permutation-array">排列</span>。</p>

<p>另给你一个整数数组 <code>pre</code>，其中每个 <code>pre[i]</code> 都是一个合法的&nbsp;<span data-keyword="array-prefix">前缀&nbsp;</span>长度。</p>

<p>一次操作中，你可以从 <code>pre</code> 中选择任意一个长度 <code>x</code>，并将 <code>nums</code> 的前 <code>x</code> 个元素翻转。</p>

<p>例如，对数组 <code>[4, 1, 2, 3]</code> 执行一次长度为 <code>3</code> 的前缀翻转后，结果为 <code>[2, 1, 4, 3]</code>。</p>

<p>返回将 <code>nums</code> 按升序排序所需的最少操作次数。如果无法完成排序，则返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,0,1], pre = [2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>先翻转前 <code>pre[1] = 3</code> 个元素，得到 <code>nums = [1, 0, 2]</code>。</li>
	<li>然后翻转前 <code>pre[0] = 2</code> 个元素，得到 <code>nums = [0, 1, 2]</code>。</li>
	<li>因此，将数组排序所需的最少前缀翻转次数为 2。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,0,2], pre = [1,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>无法仅使用给定的前缀长度对数组进行排序，因此答案为 <code>-1</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0,1], pre = [2]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>由于 <code>nums</code> 已经按升序排列，因此无需进行任何前缀翻转操作，答案为 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>约束条件：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 8</code></li>
	<li><code>0 &lt;= nums[i] &lt;= n - 1</code></li>
	<li><code>1 &lt;= pre.length &lt;= n</code></li>
	<li><code>1 &lt;= pre[i] &lt;= n</code></li>
	<li><code>nums</code> 是由 <code>0</code> 到 <code>n - 1</code> 所有整数组成的一个排列。</li>
	<li><code>pre</code> 中的所有整数&nbsp;<strong>互不相同</strong>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
