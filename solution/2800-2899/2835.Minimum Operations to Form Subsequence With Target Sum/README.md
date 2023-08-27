# [2835. 使子序列的和等于目标的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-form-subsequence-with-target-sum)

[English Version](/solution/2800-2899/2835.Minimum%20Operations%20to%20Form%20Subsequence%20With%20Target%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>nums</code>&nbsp;，它包含 <strong>非负</strong>&nbsp;整数，且全部为 <code>2</code>&nbsp;的幂，同时给你一个整数&nbsp;<code>target</code>&nbsp;。</p>

<p>一次操作中，你必须对数组做以下修改：</p>

<ul>
	<li>选择数组中一个元素&nbsp;<code>nums[i]</code>&nbsp;，满足&nbsp;<code>nums[i] &gt; 1</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[i]</code>&nbsp;从数组中删除。</li>
	<li>在 <code>nums</code>&nbsp;的 <strong>末尾</strong>&nbsp;添加 <strong>两个</strong>&nbsp;数，值都为&nbsp;<code>nums[i] / 2</code>&nbsp;。</li>
</ul>

<p>你的目标是让 <code>nums</code>&nbsp;的一个 <strong>子序列</strong>&nbsp;的元素和等于&nbsp;<code>target</code>&nbsp;，请你返回达成这一目标的 <strong>最少操作次数</strong>&nbsp;。如果无法得到这样的子序列，请你返回 <code>-1</code>&nbsp;。</p>

<p>数组中一个 <strong>子序列</strong>&nbsp;是通过删除原数组中一些元素，并且不改变剩余元素顺序得到的剩余数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,8], target = 7
<b>输出：</b>1
<b>解释：</b>第一次操作中，我们选择元素 nums[2] 。数组变为 nums = [1,2,4,4] 。
这时候，nums 包含子序列 [1,2,4] ，和为 7 。
无法通过更少的操作得到和为 7 的子序列。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,32,1,2], target = 12
<b>输出：</b>2
<b>解释：</b>第一次操作中，我们选择元素 nums[1] 。数组变为 nums = [1,1,2,16,16] 。
第二次操作中，我们选择元素 nums[3] 。数组变为 nums = [1,1,2,16,8,8] 。
这时候，nums 包含子序列 [1,1,2,8] ，和为 12 。
无法通过更少的操作得到和为 12 的子序列。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,32,1], target = 35
<b>输出：</b>-1
<b>解释：</b>无法得到和为 35 的子序列。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2<sup>30</sup></code></li>
	<li><code>nums</code>&nbsp;只包含非负整数，且均为 2 的幂。</li>
	<li><code>1 &lt;= target &lt; 2<sup>31</sup></code></li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
