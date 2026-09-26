---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4058. 一个子数组循环移动后的最大脉冲值](https://leetcode.cn/problems/maximum-pulse-value-after-one-subarray-rotation)

[English Version](/solution/4000-4099/4058.Maximum%20Pulse%20Value%20After%20One%20Subarray%20Rotation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>定义整数数组 <code>arr</code> 的 <strong>脉冲值&nbsp;</strong>为从下标 0 开始的 <strong>交替和&nbsp;</strong>：<code>pulse(arr) = arr[0] - arr[1] + arr[2] - arr[3] + ...</code></p>

<p>你可以对 <code>nums</code> 执行 <strong>至多一次</strong> 操作：</p>

<ul>
	<li>选择两个下标 <code>l</code> 和 <code>r</code>，满足 <code>0 &lt;= l &lt; r &lt;= n - 1</code>。</li>
	<li>将子数组 <code>nums[l..r]</code> <strong>循环左移</strong><strong>恰好</strong> 一个位置。例如，<code>[a, b, c, d]</code> 变为 <code>[b, c, d, a]</code>。</li>
</ul>

<p>返回执行 <strong>至多一次</strong> 该操作后可以获得的 <strong>最大脉冲值</strong>。</p>

<p><strong>子数组</strong> 是数组中连续且 <b>非空</b> 的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,5,2]</span></p>

<p><strong>输出：</strong><span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>原始脉冲值为 <code>1 - 5 + 2 = -2</code>。</li>
	<li>将子数组 <code>nums[0..1]</code> 从 <code>[1, 5]</code> 循环左移为 <code>[5, 1]</code>。</li>
	<li>得到的数组为 <code>[5, 1, 2]</code>，其脉冲值为 <code>5 - 1 + 2 = 6</code>，这是可能的最大值。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [6,4,3]</span></p>

<p><strong>输出：</strong><span class="example-io">7</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>原始脉冲值为 <code>6 - 4 + 3 = 5</code>。</li>
	<li>将子数组 <code>nums[1..2]</code> 从 <code>[4, 3]</code> 循环左移为 <code>[3, 4]</code>。</li>
	<li>得到的数组为 <code>[6, 3, 4]</code>，其脉冲值为 <code>6 - 3 + 4 = 7</code>，这是可能的最大值。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [9,7]</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>原始脉冲值为 <code>9 - 7 = 2</code>，已经是最大值。因此，不需要进行旋转操作。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
