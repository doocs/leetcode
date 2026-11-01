---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4097. 合并整数后的最小值](https://leetcode.cn/problems/minimum-value-of-merged-integers)

[English Version](/solution/4000-4099/4097.Minimum%20Value%20of%20Merged%20Integers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为偶数 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>你需要将 <code>nums</code> 中的元素两两<strong>合并</strong>：将 <code>nums[0]</code> 与 <code>nums[1]</code> 合并，将 <code>nums[2]</code> 与 <code>nums[3]</code> 合并，依此类推，直到将 <code>nums[n - 2]</code> 与 <code>nums[n - 1]</code> 合并。</p>

<p>返回所有合并后得到的整数中的<strong>最小值</strong>。</p>

<p><strong>合并</strong>两个整数，是指将第二个整数的所有十进制数位拼接在第一个整数的所有十进制数位之后。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [23,55,42,21]</span></p>

<p><strong>输出：</strong> <span class="example-io">2355</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将 <code>nums[0] = 23</code> 与 <code>nums[1] = 55</code> 合并，得到 2355。</li>
	<li>将 <code>nums[2] = 42</code> 与 <code>nums[3] = 21</code> 合并，得到 4221。</li>
	<li>合并后得到的整数中的最小值为 <code>min(2355, 4221) = 2355</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [34,7,11,11]</span></p>

<p><strong>输出：</strong> <span class="example-io">347</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将 <code>nums[0] = 34</code> 与 <code>nums[1] = 7</code> 合并，得到 347。</li>
	<li>将 <code>nums[2] = 11</code> 与 <code>nums[3] = 11</code> 合并，得到 1111。</li>
	<li>合并后得到的整数中的最小值为 <code>min(347, 1111) = 347</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [9,20]</span></p>

<p><strong>输出：</strong> <span class="example-io">920</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将 <code>nums[0] = 9</code> 与 <code>nums[1] = 20</code> 合并，得到 920。</li>
	<li>合并后只有一个整数，因此最小值为 920。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>n</code> 为偶数</li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
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
