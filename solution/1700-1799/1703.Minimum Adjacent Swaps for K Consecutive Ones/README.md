# [1703. 得到连续 K 个 1 的最少相邻交换次数](https://leetcode.cn/problems/minimum-adjacent-swaps-for-k-consecutive-ones)

[English Version](/solution/1700-1799/1703.Minimum%20Adjacent%20Swaps%20for%20K%20Consecutive%20Ones/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> 。 <code>nums</code> 仅包含 <code>0</code> 和 <code>1</code> 。每一次移动，你可以选择 <strong>相邻</strong> 两个数字并将它们交换。</p>

<p>请你返回使 <code>nums</code> 中包含 <code>k</code> 个 <strong>连续 </strong><code>1</code> 的 <strong>最少</strong> 交换次数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,0,0,1,0,1], k = 2
<b>输出：</b>1
<b>解释：</b>在第一次操作时，nums 可以变成 [1,0,0,0,<strong>1</strong>,<strong>1</strong>] 得到连续两个 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,0,0,0,0,0,1,1], k = 3
<b>输出：</b>5
<b>解释：</b>通过 5 次操作，最左边的 1 可以移到右边直到 nums 变为 [0,0,0,0,0,<strong>1</strong>,<strong>1</strong>,<strong>1</strong>] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [1,1,0,1], k = 2
<b>输出：</b>0
<b>解释：</b>nums 已经有连续 2 个 1 了。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> 要么是 <code>0</code> ，要么是 <code>1</code> 。</li>
	<li><code>1 &lt;= k &lt;= sum(nums)</code></li>
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
