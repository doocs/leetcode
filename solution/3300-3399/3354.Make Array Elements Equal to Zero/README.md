---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3354.Make%20Array%20Elements%20Equal%20to%20Zero/README.md
---

<!-- problem:start -->

# [3354. 使数组元素等于零](https://leetcode.cn/problems/make-array-elements-equal-to-zero)

[English Version](/solution/3300-3399/3354.Make%20Array%20Elements%20Equal%20to%20Zero/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code> 。</p>

<p>开始时，选择一个满足 <code>nums[curr] == 0</code> 的起始位置&nbsp;<code>curr</code>&nbsp;，并选择一个移动 <strong>方向</strong>&nbsp;：向左或者向右。</p>

<p>此后，你需要重复下面的过程：</p>

<ul>
	<li>如果&nbsp;<code>curr</code>&nbsp;超过范围&nbsp;<code>[0, n - 1]</code> ，过程结束。</li>
	<li>如果&nbsp;<code>nums[curr] == 0</code> ，沿当前方向继续移动：如果向右移，则 <strong>递增</strong>&nbsp;<code>curr</code>&nbsp;；如果向左移，则 <strong>递减</strong>&nbsp;<code>curr</code>&nbsp;。</li>
	<li>如果&nbsp;<code>nums[curr] &gt; 0</code>:
	<ul>
		<li>将&nbsp;<code>nums[curr]</code>&nbsp;减&nbsp;1 。</li>
		<li><strong>反转</strong>&nbsp;移动方向（向左变向右，反之亦然）。</li>
		<li>沿新方向移动一步。</li>
	</ul>
	</li>
</ul>

<p>如果在结束整个过程后，<code>nums</code>&nbsp;中的所有元素都变为 0 ，则认为选出的初始位置和移动方向 <strong>有效</strong>&nbsp;。</p>

<p>返回可能的有效选择方案数目。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,0,2,0,3]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><b>解释：</b></p>

<p>可能的有效选择方案如下：</p>

<ul>
	<li>选择&nbsp;<code>curr = 3</code>&nbsp;并向左移动。

    <ul>
    	<li><code>[1,0,2,<strong><u>0</u></strong>,3] -&gt; [1,0,<strong><u>2</u></strong>,0,3] -&gt; [1,0,1,<strong><u>0</u></strong>,3] -&gt; [1,0,1,0,<strong><u>3</u></strong>] -&gt; [1,0,1,<strong><u>0</u></strong>,2] -&gt; [1,0,<strong><u>1</u></strong>,0,2] -&gt; [1,0,0,<strong><u>0</u></strong>,2] -&gt; [1,0,0,0,<strong><u>2</u></strong>] -&gt; [1,0,0,<strong><u>0</u></strong>,1] -&gt; [1,0,<strong><u>0</u></strong>,0,1] -&gt; [1,<strong><u>0</u></strong>,0,0,1] -&gt; [<strong><u>1</u></strong>,0,0,0,1] -&gt; [0,<strong><u>0</u></strong>,0,0,1] -&gt; [0,0,<strong><u>0</u></strong>,0,1] -&gt; [0,0,0,<strong><u>0</u></strong>,1] -&gt; [0,0,0,0,<strong><u>1</u></strong>] -&gt; [0,0,0,0,0]</code>.</li>
    </ul>
    </li>
    <li>选择&nbsp;<code>curr = 3</code>&nbsp;并向右移动。
    <ul>
    	<li><code>[1,0,2,<strong><u>0</u></strong>,3] -&gt; [1,0,2,0,<strong><u>3</u></strong>] -&gt; [1,0,2,<strong><u>0</u></strong>,2] -&gt; [1,0,<strong><u>2</u></strong>,0,2] -&gt; [1,0,1,<strong><u>0</u></strong>,2] -&gt; [1,0,1,0,<strong><u>2</u></strong>] -&gt; [1,0,1,<strong><u>0</u></strong>,1] -&gt; [1,0,<strong><u>1</u></strong>,0,1] -&gt; [1,0,0,<strong><u>0</u></strong>,1] -&gt; [1,0,0,0,<strong><u>1</u></strong>] -&gt; [1,0,0,<strong><u>0</u></strong>,0] -&gt; [1,0,<strong><u>0</u></strong>,0,0] -&gt; [1,<strong><u>0</u></strong>,0,0,0] -&gt; [<strong><u>1</u></strong>,0,0,0,0] -&gt; [0,0,0,0,0].</code></li>
    </ul>
    </li>

</ul>
</div>

<p><b>示例 2：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2,3,4,0,4,1,0]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><b>解释：</b></p>

<p>不存在有效的选择方案。</p>
</div>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
	<li>至少存在一个元素&nbsp;<code>i</code>&nbsp;满足&nbsp;<code>nums[i] == 0</code> 。</li>
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
