# [2036. 最大交替子数组和](https://leetcode.cn/problems/maximum-alternating-subarray-sum)

[English Version](/solution/2000-2099/2036.Maximum%20Alternating%20Subarray%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>子数组</strong>是以<strong>0</strong>下标开始的数组的连续非空子序列，从 <code>i</code> 到 <code>j</code>（<code>0 &lt;= i &lt;= j &lt; nums.length</code>）的 <strong>子数组交替和</strong> 被定义为 <code>nums[i] - nums[i+1] + nums[i+2] - ... +/- nums[j]</code> 。</p>

<p>给定一个以<strong>0</strong>下标开始的整数数组<code>nums</code>，返回它所有可能的交替子数组和的最大值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,-1,1,2]
<strong>输出：</strong>5
<strong>解释：</strong>
子数组 [3,-1,1]有最大的交替子数组和3 - (-1) + 1 = 5.
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,2,2,2]
<strong>输出：</strong>2
<strong>解释：</strong>
子数组 [2], [2,2,2]和 [2,2,2,2,2]有相同的最大交替子数组和为2
[2]: 2.
[2,2,2]: 2 - 2 + 2 = 2.
[2,2,2,2,2]: 2 - 2 + 2 - 2 + 2 = 2.
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>1
<strong>解释：</strong>
仅有一个非空子数组，为 [1]，它的交替子数组和为 1
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
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
