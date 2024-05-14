# [3082. 求出所有子序列的能量和](https://leetcode.cn/problems/find-the-sum-of-the-power-of-all-subsequences)

[English Version](/solution/3000-3099/3082.Find%20the%20Sum%20of%20the%20Power%20of%20All%20Subsequences/README_EN.md)

<!-- tags:数组,动态规划 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;和一个 <strong>正</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;。</p>

<p>一个整数数组的 <strong>能量</strong>&nbsp;定义为和 <strong>等于</strong>&nbsp;<code>k</code>&nbsp;的子序列的数目。</p>

<p>请你返回 <code>nums</code>&nbsp;中所有子序列的 <strong>能量和</strong>&nbsp;。</p>

<p>由于答案可能很大，请你将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> nums = [1,2,3], k = 3 </span></p>

<p><strong>输出：</strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 6 </span></p>

<p><strong>解释：</strong></p>

<p>总共有&nbsp;<code>5</code>&nbsp;个能量不为 0 的子序列：</p>

<ul>
	<li>子序列&nbsp;<code>[<u><em><strong>1</strong></em></u>,<u><em><strong>2</strong></em></u>,<u><em><strong>3</strong></em></u>]</code> 有&nbsp;<code>2</code>&nbsp;个和为&nbsp;<code>3</code>&nbsp;的子序列：<code>[1,2,<u><strong><em>3</em></strong></u>]</code> 和 <code>[<u><strong><em>1</em></strong></u>,<u><strong><em>2</em></strong></u>,3]</code>&nbsp;。</li>
	<li>子序列&nbsp;<code>[<u><em><strong>1</strong></em></u>,2,<u><em><strong>3</strong></em></u>]</code>&nbsp;有 <code>1</code>&nbsp;个和为&nbsp;<code>3</code>&nbsp;的子序列：<code>[1,2,<u><strong><em>3</em></strong></u>]</code>&nbsp;。</li>
	<li>子序列&nbsp;<code>[1,<u><em><strong>2</strong></em></u>,<u><em><strong>3</strong></em></u>]</code> 有&nbsp;<code>1</code>&nbsp;个和为&nbsp;<code>3</code>&nbsp;的子序列：<code>[1,2,<u><strong><em>3</em></strong></u>]</code>&nbsp;。</li>
	<li>子序列&nbsp;<code>[<u><em><strong>1</strong></em></u>,<u><em><strong>2</strong></em></u>,3]</code>&nbsp;有&nbsp;<code>1</code>&nbsp;个和为&nbsp;<code>3</code>&nbsp;的子序列：<code>[<u><strong><em>1</em></strong></u>,<u><strong><em>2</em></strong></u>,3]</code>&nbsp;。</li>
	<li>子序列&nbsp;<code>[1,2,<u><em><strong>3</strong></em></u>]</code>&nbsp;有&nbsp;<code>1</code>&nbsp;个和为&nbsp;<code>3</code>&nbsp;的子序列：<code>[1,2,<u><strong><em>3</em></strong></u>]</code>&nbsp;。</li>
</ul>

<p>所以答案为&nbsp;<code>2 + 1 + 1 + 1 + 1 = 6</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> nums = [2,3,3], k = 5 </span></p>

<p><strong>输出：</strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 4 </span></p>

<p><strong>解释：</strong></p>

<p>总共有&nbsp;<code>3</code>&nbsp;个能量不为 0 的子序列：</p>

<ul>
	<li>子序列&nbsp;<code>[<u><em><strong>2</strong></em></u>,<u><em><strong>3</strong></em></u>,<u><em><strong>3</strong></em></u>]</code>&nbsp;有 2 个子序列和为&nbsp;<code>5</code>&nbsp;：<code>[<u><strong><em>2</em></strong></u>,3,<u><strong><em>3</em></strong></u>]</code> 和&nbsp;<code>[<u><strong><em>2</em></strong></u>,<u><strong><em>3</em></strong></u>,3]</code>&nbsp;。</li>
	<li>子序列&nbsp;<code>[<u><em><strong>2</strong></em></u>,3,<u><em><strong>3</strong></em></u>]</code>&nbsp;有 1 个子序列和为&nbsp;<code>5</code>&nbsp;：<code>[<u><strong><em>2</em></strong></u>,3,<u><strong><em>3</em></strong></u>]</code>&nbsp;。</li>
	<li>子序列&nbsp;<code>[<u><em><strong>2</strong></em></u>,<u><em><strong>3</strong></em></u>,3]</code>&nbsp;有 1 个子序列和为 <code>5</code>&nbsp;：<code>[<u><strong><em>2</em></strong></u>,<u><strong><em>3</em></strong></u>,3]</code>&nbsp;。</li>
</ul>

<p>所以答案为&nbsp;<code>2 + 1 + 1 = 4</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> nums = [1,2,3], k = 7 </span></p>

<p><strong>输出：</strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 0 </span></p>

<p><strong>解释：</strong>不存在和为 <code>7</code>&nbsp;的子序列，所以 <code>nums</code>&nbsp;的能量和为&nbsp;<code>0</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
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
