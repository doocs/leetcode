---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4083. 使相邻元素按位异或非零的最少删除次数](https://leetcode.cn/problems/minimum-deletions-to-make-array-sharp)

[English Version](/solution/4000-4099/4083.Minimum%20Deletions%20to%20Make%20Array%20Sharp/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>你可以从 <code>nums</code> 中删除任意数量的元素。剩余元素保持原有的相对顺序。</p>

<p>如果结果数组 <code>a</code> 中每一对<strong>&nbsp;相邻</strong>&nbsp;元素的&nbsp;<strong>按位异或&nbsp;</strong>结果都&nbsp;<strong>不为 0</strong>，则称数组 <code>a</code> 是&nbsp;<strong>相邻异或非零数组&nbsp;</strong>。也就是说，对于每个满足 <code>0 &lt;= i &lt; a.length - 1</code> 的下标 <code>i</code>，都有 <code>a[i] XOR a[i + 1] != 0</code>。长度不超过 1 的数组也被视为相邻异或非零数组。</p>

<p>返回使 <code>nums</code> 变为相邻异或非零数组所需的&nbsp;<strong>最少&nbsp;</strong>删除次数。</p>

<p><strong>按位异或&nbsp;</strong>会逐位比较两个整数对应的二进制位。如果对应位不同，则结果中的该位为 1；否则为 0。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,1,2,2,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>下标对 <code>(0, 1)</code>、<code>(2, 3)</code> 和 <code>(3, 4)</code> 对应元素的异或结果均为 0。</li>
	<li>删除一个 1 和两个 2 后，得到 <code>[1, 2, 3]</code>，其中 <code>1 XOR 2 == 3</code> 且 <code>2 XOR 3 == 1</code>，两者都不为 0。因此，答案为 3。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,4,9,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>下标对 <code>(0, 1)</code> 对应元素的异或结果为 0。</li>
	<li>删除这两个 4 中的任意一个后，得到 <code>[4, 9, 4]</code>，其中 <code>4 XOR 9 == 13</code> 且 <code>9 XOR 4 == 13</code>，两者都不为 0。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
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
