# [1674. 使数组互补的最少操作次数](https://leetcode.cn/problems/minimum-moves-to-make-array-complementary)

[English Version](/solution/1600-1699/1674.Minimum%20Moves%20to%20Make%20Array%20Complementary/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为<strong> 偶数</strong> <code>n</code> 的整数数组 <code>nums</code> 和一个整数 <code>limit</code> 。每一次操作，你可以将 <code>nums</code> 中的任何整数替换为 <code>1</code> 到 <code>limit</code> 之间的另一个整数。</p>

<p>如果对于所有下标 <code>i</code>（<strong>下标从 </strong><code>0</code><strong> 开始</strong>），<code>nums[i] + nums[n - 1 - i]</code> 都等于同一个数，则数组 <code>nums</code> 是 <strong>互补的</strong> 。例如，数组 <code>[1,2,3,4]</code> 是互补的，因为对于所有下标 <code>i</code> ，<code>nums[i] + nums[n - 1 - i] = 5</code> 。</p>

<p>返回使数组 <strong>互补</strong> 的 <strong>最少</strong> 操作次数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,4,3], limit = 4
<strong>输出：</strong>1
<strong>解释：</strong>经过 1 次操作，你可以将数组 nums 变成 [1,2,<strong>2</strong>,3]（加粗元素是变更的数字）：
nums[0] + nums[3] = 1 + 3 = 4.
nums[1] + nums[2] = 2 + 2 = 4.
nums[2] + nums[1] = 2 + 2 = 4.
nums[3] + nums[0] = 3 + 1 = 4.
对于每个 i ，nums[i] + nums[n-1-i] = 4 ，所以 nums 是互补的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2,1], limit = 2
<strong>输出：</strong>2
<strong>解释：</strong>经过 2 次操作，你可以将数组 nums 变成 [<strong>2</strong>,2,2,<strong>2</strong>] 。你不能将任何数字变更为 3 ，因为 3 > limit 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,2], limit = 2
<strong>输出：</strong>0
<strong>解释：</strong>nums 已经是互补的。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 <= n <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= limit <= 10<sup>5</sup></code></li>
	<li><code>n</code> 是偶数。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
