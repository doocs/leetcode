---
comments: true
difficulty: 困难
---

<!-- problem:start -->

# [4059. 字典序最大的答案数组](https://leetcode.cn/problems/lexicographically-largest-power-array)

[English Version](/solution/4000-4099/4059.Lexicographically%20Largest%20Power%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>。你可以重新排列其中的元素以形成任意 <strong>排列</strong> <code>perm</code>。</p>

<p>定义一个长度为 15 的数组 <code>power</code>。对于每个 <code>0 &lt;= i &lt; 15</code>，考查&nbsp;<code>perm</code> 的前 <code>j</code> 个元素的第 <code>(14 - i)</code>&nbsp;位，<code>power[i]</code>&nbsp;是满足这些位全为 <code>1</code>&nbsp;的最大整数 <code>j</code>（其中 <code>0 &lt;= j &lt;= n</code>）。</p>

<p>二进制位的位置从右向左编号，从第 <code>0</code>&nbsp;位开始。</p>

<p>返回可能得到的 <strong>字典序最大</strong> 的 <code>power</code> 数组。</p>

<p><strong>排列</strong> 是数组中所有元素的一种重新排列。</p>

<p><strong>置位</strong> 指的是数字在二进制表示中对应位的值为 1。</p>

<p>对于两个长度相同的数组，如果在它们不同的第一个下标处，数组 <code>a</code> 包含的元素大于数组 <code>b</code> 中的元素，则称数组 <code>a</code> 的 <strong>字典序大于</strong> 数组 <code>b</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [7,5]</span></p>

<p><strong>输出：</strong><span class="example-io">[0,0,0,0,0,0,0,0,0,0,0,0,2,1,2]</span></p>

<p><strong>解释：</strong></p>

<p>选择 <code>perm = [7, 5]</code>。</p>

<ul>
	<li>两个元素的第 2 位都置位了，因此 <code>power[12] = 2</code>。</li>
	<li>第一个元素的第 1 位置位了，但第二个元素没有，因此 <code>power[13] = 1</code>。</li>
	<li>两个元素的第 0 位都置位了，因此 <code>power[14] = 2</code>。</li>
</ul>

<p>第一个元素的所有更高位都未置位，因此其余项都为 0。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [3,1,7]</span></p>

<p><strong>输出：</strong><span class="example-io">[0,0,0,0,0,0,0,0,0,0,0,0,1,2,3]</span></p>

<p><strong>解释：</strong></p>

<p>选择 <code>perm = [7, 3, 1]</code>。</p>

<ul>
	<li>第一个元素的第 2 位置位了，但第二个元素没有，因此 <code>power[12] = 1</code>。</li>
	<li>前两个元素的第 1 位都置位了，但第三个元素没有，因此 <code>power[13] = 2</code>。</li>
	<li>所有三个元素的第 0 位都置位了，因此 <code>power[14] = 3</code>。</li>
</ul>

<p>第一个元素的所有更高位都未置位，因此其余项都为 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt; 2<sup>15</sup></code></li>
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
