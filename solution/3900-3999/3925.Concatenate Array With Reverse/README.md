---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3925.Concatenate%20Array%20With%20Reverse/README.md
---

<!-- problem:start -->

# [3925. 连接逆序数组](https://leetcode.cn/problems/concatenate-array-with-reverse)

[English Version](/solution/3900-3999/3925.Concatenate%20Array%20With%20Reverse/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>构造一个新的长度为 <code>2 * n</code> 的数组 <code>ans</code>，其中前 <code>n</code> 个元素与 <code>nums</code> 相同，后 <code>n</code> 个元素为 <code>nums</code> 的逆序。</p>

<p>具体而言，对于 <code>0 &lt;= i &lt;= n - 1</code>：</p>

<ul>
	<li><code>ans[i] = nums[i]</code></li>
	<li><code>ans[i + n] = nums[n - i - 1]</code></li>
</ul>

<p>返回整数数组 <code>ans</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,2,3,3,2,1]</span></p>

<p><strong>解释：</strong></p>

<p><code>ans</code> 的前 <code>n</code> 个元素与 <code>nums</code> 相同。</p>

<p>接下来的 <code>n = 3</code> 个元素按照 <code>nums</code> 的逆序填入：</p>

<ul>
	<li><code>ans[3] = nums[2] = 3</code></li>
	<li><code>ans[4] = nums[1] = 2</code></li>
	<li><code>ans[5] = nums[0] = 1</code></li>
</ul>

<p>因此，<code>ans = [1, 2, 3, 3, 2, 1]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,1]</span></p>

<p><strong>解释：</strong></p>

<p>数组逆序后保持不变。因此，<code>ans = [1, 1]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
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
