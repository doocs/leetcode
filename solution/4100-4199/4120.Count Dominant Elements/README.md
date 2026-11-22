---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4120. 统计优势元素的数量](https://leetcode.cn/problems/count-dominant-elements)

[English Version](/solution/4100-4199/4120.Count%20Dominant%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>如果 <code>nums</code> 中的某个元素 <strong>大于或等于</strong> 其相邻元素的平均值，则称它为 <strong>优势</strong> 元素。</p>

<p><code>nums[i]</code> 的相邻元素是 <code>nums[i - 1]</code> 和 <code>nums[i + 1]</code>，仅在对应下标存在时计入。</p>

<p>没有相邻元素的单个元素视为 <strong>优势</strong> 元素。</p>

<p>返回 <code>nums</code> 中 <strong>优势</strong> 元素的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,10,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对于 <code>nums[0] = 1</code>，它唯一的相邻元素是 10。由于 <code>1 &lt; 10</code>，它不是优势元素。</li>
	<li>对于 <code>nums[1] = 10</code>，它的相邻元素是 1 和 2，平均值为 <code>(1 + 2) / 2 = 1.5</code>。由于 <code>10 &gt; 1.5</code>，它是优势元素。</li>
	<li>对于 <code>nums[2] = 2</code>，它唯一的相邻元素是 10。由于 <code>2 &lt; 10</code>，它不是优势元素。</li>
</ul>

<p>因此，答案是 1。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [6,4,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对于 <code>nums[0] = 6</code>，它唯一的相邻元素是 4。由于 <code>6 &gt; 4</code>，它是优势元素。</li>
	<li>对于 <code>nums[1] = 4</code>，它的相邻元素是 6 和 2，平均值为 <code>(6 + 2) / 2 = 4</code>。由于 <code>4 = 4</code>，它是优势元素。</li>
	<li>对于 <code>nums[2] = 2</code>，它唯一的相邻元素是 4。由于 <code>2 &lt; 4</code>，它不是优势元素。</li>
</ul>

<p>因此，答案是 2。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [7]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对于 <code>nums[0] = 7</code>，它没有相邻元素，因此它是优势元素。</li>
</ul>

<p>因此，答案是 1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
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
