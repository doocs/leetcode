# [3139. 使数组中所有元素相等的最小开销](https://leetcode.cn/problems/minimum-cost-to-equalize-array)

[English Version](/solution/3100-3199/3139.Minimum%20Cost%20to%20Equalize%20Array/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和两个整数&nbsp;<code>cost1</code> 和&nbsp;<code>cost2</code>&nbsp;。你可以执行以下&nbsp;<strong>任一</strong>&nbsp;操作&nbsp;<strong>任意</strong>&nbsp;次：</p>

<ul>
	<li>从 <code>nums</code>&nbsp;中选择下标 <code>i</code>&nbsp;并且将 <code>nums[i]</code>&nbsp;<strong>增加</strong> <code>1</code>&nbsp;，开销为 <code>cost1</code>。</li>
	<li>选择 <code>nums</code>&nbsp;中两个 <strong>不同</strong>&nbsp;下标 <code>i</code>&nbsp;和 <code>j</code>&nbsp;，并且将 <code>nums[1]</code>&nbsp;和 <code>nums[2]</code>&nbsp;都&nbsp;<strong>增加</strong> <code>1</code>&nbsp;，开销为 <code>cost2</code>&nbsp;。</li>
</ul>

<p>你的目标是使数组中所有元素都 <strong>相等</strong>&nbsp;，请你返回需要的 <strong>最小开销</strong>&nbsp;之和。</p>

<p>由于答案可能会很大，请你将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [4,1], cost1 = 5, cost2 = 2</span></p>

<p><span class="example-io"><b>输出：</b>15</span></p>

<p><strong>解释：</strong></p>

<p>执行以下操作可以使数组中所有元素相等：</p>

<ul>
	<li>将&nbsp;<code>nums[1]</code>&nbsp;增加 1 ，开销为 5 ，<code>nums</code> 变为&nbsp;<code>[4,2]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[1]</code>&nbsp;增加 1 ，开销为 5 ，<code>nums</code> 变为&nbsp;<code>[4,3]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[1]</code>&nbsp;增加 1 ，开销为 5 ，<code>nums</code> 变为&nbsp;<code>[4,4]</code>&nbsp;。</li>
</ul>

<p>总开销为 15 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [2,3,3,3,5], cost1 = 2, cost2 = 1</span></p>

<p><span class="example-io"><b>输出：</b>6</span></p>

<p><b>解释：</b></p>

<p>执行以下操作可以使数组中所有元素相等：</p>

<ul>
	<li>将&nbsp;<code>nums[0]</code> 和&nbsp;<code>nums[1]</code>&nbsp;同时增加 1 ，开销为 1 ，<code>nums</code> 变为&nbsp;<code>[3,4,3,3,5]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[0]</code> 和&nbsp;<code>nums[2]</code>&nbsp;同时增加 1 ，开销为 1 ，<code>nums</code> 变为&nbsp;<code>[4,4,4,3,5]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[0]</code> 和&nbsp;<code>nums[3]</code>&nbsp;同时增加 1 ，开销为 1 ，<code>nums</code> 变为&nbsp;<code>[5,4,4,4,5]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[1]</code> 和&nbsp;<code>nums[2]</code>&nbsp;同时增加 1 ，开销为 1 ，<code>nums</code> 变为&nbsp;<code>[5,5,5,4,5]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[3]</code>&nbsp;增加 1 ，开销为 2 ，<code>nums</code> 变为&nbsp;<code>[5,5,5,5,5]</code>&nbsp;。</li>
</ul>

<p>总开销为 6 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [3,5,3], cost1 = 1, cost2 = 3</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>执行以下操作可以使数组中所有元素相等：</p>

<ul>
	<li>将&nbsp;<code>nums[0]</code>&nbsp;增加 1 ，开销为 1 ，<code>nums</code> 变为&nbsp;<code>[4,5,3]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[0]</code>&nbsp;增加 1 ，开销为 1 ，<code>nums</code> 变为&nbsp;<code>[5,5,3]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[2]</code>&nbsp;增加 1 ，开销为 1 ，<code>nums</code> 变为&nbsp;<code>[5,5,4]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[2]</code>&nbsp;增加 1 ，开销为 1 ，<code>nums</code> 变为&nbsp;<code>[5,5,5]</code>&nbsp;。</li>
</ul>

<p>总开销为 4 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= cost1 &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= cost2 &lt;= 10<sup>6</sup></code></li>
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
