---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3936.Minimum%20Swaps%20to%20Move%20Zeros%20to%20End/README.md
---

<!-- problem:start -->

# [3936. 将 0 移到末尾的最少交换次数](https://leetcode.cn/problems/minimum-swaps-to-move-zeros-to-end)

[English Version](/solution/3900-3999/3936.Minimum%20Swaps%20to%20Move%20Zeros%20to%20End/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 。</p>

<p>在一步操作中，你可以选择任意两个 <strong>不同</strong> 的下标 <code>i</code> 和 <code>j</code> 并交换 <code>nums[i]</code> 和 <code>nums[j]</code> 。</p>

<p>返回将所有 0 移动到数组末尾所需的 <strong>最少</strong> 操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0,1,0,3,12]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>我们执行以下交换操作：</p>

<ul>
	<li>交换 <code>nums[0]</code> 和 <code>nums[3]</code> ，得到 <code>nums = [3, 1, 0, 0, 12]</code> 。</li>
	<li>交换 <code>nums[2]</code> 和 <code>nums[4]</code> ，得到 <code>nums = [3, 1, 12, 0, 0]</code> 。</li>
</ul>

<p>因此，答案是 2 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0,1,0,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>我们执行以下交换操作：</p>

<ul>
	<li>交换 <code>nums[0]</code> 和 <code>nums[3]</code> ，得到 <code>nums = [2, 1, 0, 0]</code> 。</li>
</ul>

<p>因此，答案是 1 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,0]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>数组已经满足条件。因此，不需要任何交换操作。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
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
