# [3113. 边界元素是最大值的子数组数目](https://leetcode.cn/problems/find-the-number-of-subarrays-where-boundary-elements-are-maximum)

[English Version](/solution/3100-3199/3113.Find%20the%20Number%20of%20Subarrays%20Where%20Boundary%20Elements%20Are%20Maximum/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>正</strong>&nbsp;整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>请你求出&nbsp;<code>nums</code>&nbsp;中有多少个子数组，满足子数组中&nbsp;<strong>第一个</strong>&nbsp;和 <strong>最后一个</strong>&nbsp;元素都是这个子数组中的 <strong>最大</strong>&nbsp;值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,4,3,3,2]</span></p>

<p><span class="example-io"><b>输出：</b>6</span></p>

<p><strong>解释：</strong></p>

<p>总共有 6 个子数组满足第一个元素和最后一个元素都是子数组中的最大值：</p>

<ul>
	<li>子数组&nbsp;<code>[<u><em><strong>1</strong></em></u>,4,3,3,2]</code>&nbsp;，最大元素为 1 ，第一个和最后一个元素都是 1 。</li>
	<li>子数组&nbsp;<code>[1,<u><em><strong>4</strong></em></u>,3,3,2]</code>&nbsp;，最大元素为 4 ，第一个和最后一个元素都是 4 。</li>
	<li>子数组&nbsp;<code>[1,4,<u><em><strong>3</strong></em></u>,3,2]</code>&nbsp;，最大元素为 3 ，第一个和最后一个元素都是 3 。</li>
	<li>子数组&nbsp;<code>[1,4,3,<u><em><strong>3</strong></em></u>,2]</code>&nbsp;，最大元素为 3 ，第一个和最后一个元素都是 3 。</li>
	<li>子数组&nbsp;<code>[1,4,3,3,<u><em><strong>2</strong></em></u>]</code>&nbsp;，最大元素为 2 ，第一个和最后一个元素都是 2 。</li>
	<li>子数组&nbsp;<code>[1,4,<u><em><strong>3,3</strong></em></u>,2]</code>&nbsp;，最大元素为 3 ，第一个和最后一个元素都是 3 。</li>
</ul>

<p>所以我们返回 6 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [3,3,3]</span></p>

<p><span class="example-io"><b>输出：</b>6</span></p>

<p><strong>解释：</strong></p>

<p>总共有 6 个子数组满足第一个元素和最后一个元素都是子数组中的最大值：</p>

<ul>
	<li>子数组 <code>[<u><em><strong>3</strong></em></u>,3,3]</code>&nbsp;，最大元素为 3&nbsp;，第一个和最后一个元素都是 3&nbsp;。</li>
	<li>子数组 <code>[3,<u><em><strong>3</strong></em></u>,3]</code>&nbsp;，最大元素为 3&nbsp;，第一个和最后一个元素都是 3&nbsp;。</li>
	<li>子数组 <code>[3,3,<u><em><strong>3</strong></em></u>]</code>&nbsp;，最大元素为 3&nbsp;，第一个和最后一个元素都是 3&nbsp;。</li>
	<li>子数组 <code>[<u><em><strong>3,3</strong></em></u>,3]</code>&nbsp;，最大元素为 3&nbsp;，第一个和最后一个元素都是 3&nbsp;。</li>
	<li>子数组 <code>[3,<u><em><strong>3,3</strong></em></u>]</code>&nbsp;，最大元素为 3&nbsp;，第一个和最后一个元素都是 3&nbsp;。</li>
	<li>子数组 <code>[<u><em><strong>3,3,3</strong></em></u>]</code>&nbsp;，最大元素为 3&nbsp;，第一个和最后一个元素都是 3&nbsp;。</li>
</ul>

<p>所以我们返回 6 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1]</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code>&nbsp;中只有一个子数组&nbsp;<code>[<em><strong>1</strong></em>]</code>&nbsp;，最大元素为 1 ，第一个和最后一个元素都是 1 。</p>

<p>所以我们返回 1 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
