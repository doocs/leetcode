# [3077. K 个不相交子数组的最大能量值](https://leetcode.cn/problems/maximum-strength-of-k-disjoint-subarrays)

[English Version](/solution/3000-3099/3077.Maximum%20Strength%20of%20K%20Disjoint%20Subarrays/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;和一个 <strong>正奇数</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;。</p>

<p><code>x</code> 个子数组的能量值定义为&nbsp;<code>strength = sum[1] * x - sum[2] * (x - 1) + sum[3] * (x - 2) - sum[4] * (x - 3) + ... + sum[x] * 1</code> ，其中&nbsp;<code>sum[i]</code>&nbsp;是第 <code>i</code>&nbsp;个子数组的和。更正式的，能量值是满足&nbsp;<code>1 &lt;= i &lt;= x</code>&nbsp;的所有&nbsp;<code>i</code>&nbsp;对应的&nbsp;<code>(-1)<sup>i+1</sup> * sum[i] * (x - i + 1)</code>&nbsp;之和。</p>

<p>你需要在 <code>nums</code>&nbsp;中选择 <code>k</code>&nbsp;个 <strong>不相交</strong><strong>子数组</strong>&nbsp;，使得&nbsp;<strong>能量值最大</strong>&nbsp;。</p>

<p>请你返回可以得到的 <strong>最大</strong><strong>能量值</strong>&nbsp;。</p>

<p><strong>注意</strong>，选出来的所有子数组&nbsp;<strong>不</strong>&nbsp;需要覆盖整个数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,-1,2], k = 3
<b>输出：</b>22
<b>解释：</b>选择 3 个子数组的最好方式是选择：nums[0..2] ，nums[3..3] 和 nums[4..4] 。能量值为 (1 + 2 + 3) * 3 - (-1) * 2 + 2 * 1 = 22 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [12,-2,-2,-2,-2], k = 5
<b>输出：</b>64
<b>解释：</b>唯一一种选 5 个不相交子数组的方案是：nums[0..0] ，nums[1..1] ，nums[2..2] ，nums[3..3] 和 nums[4..4] 。能量值为 12 * 5 - (-2) * 4 + (-2) * 3 - (-2) * 2 + (-2) * 1 = 64 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [-1,-2,-3], k = 1
<b>输出：</b>-1
<b>解释：</b>选择 1 个子数组的最优方案是：nums[0..0] 。能量值为 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>1 &lt;= n * k &lt;= 10<sup>6</sup></code></li>
	<li><code>k</code> 是奇数。</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
