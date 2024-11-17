---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3355.Zero%20Array%20Transformation%20I/README.md
---

<!-- problem:start -->

# [3355. 零数组变换 I](https://leetcode.cn/problems/zero-array-transformation-i)

[English Version](/solution/3300-3399/3355.Zero%20Array%20Transformation%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为 <code>n</code> 的整数数组 <code>nums</code> 和一个二维数组 <code>queries</code>，其中 <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code>。</p>

<p>对于每个查询&nbsp;<code>queries[i]</code>：</p>

<ul>
	<li>在&nbsp;<code>nums</code>&nbsp;的下标范围&nbsp;<code>[l<sub>i</sub>, r<sub>i</sub>]</code>&nbsp;内选择一个下标子集。</li>
	<li>将选中的每个下标对应的元素值减 1。</li>
</ul>

<p><strong>零数组&nbsp;</strong>是指所有元素都等于 0 的数组。</p>

<p>如果在按顺序处理所有查询后，可以将 <code>nums</code> 转换为&nbsp;<strong>零数组&nbsp;</strong>，则返回 <code>true</code>，否则返回 <code>false</code>。</p>

<p>数组的&nbsp;<strong>子集&nbsp;</strong>是对数组元素的选择（可能为空）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,0,1], queries = [[0,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>对于 i = 0：</strong>

    <ul>
    	<li>选择下标子集 <code>[0, 2]</code> 并将这些下标处的值减 1。</li>
    	<li>数组将变为 <code>[0, 0, 0]</code>，这是一个零数组。</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,3,2,1], queries = [[1,3],[0,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>对于 i = 0：</strong>&nbsp;

    <ul>
    	<li>选择下标子集 <code>[1, 2, 3]</code> 并将这些下标处的值减 1。</li>
    	<li>数组将变为 <code>[4, 2, 1, 0]</code>。</li>
    </ul>
    </li>
    <li><strong>对于 i = 1：</strong>
    <ul>
    	<li>选择下标子集 <code>[0, 1, 2]</code> 并将这些下标处的值减 1。</li>
    	<li>数组将变为 <code>[3, 1, 0, 0]</code>，这不是一个零数组。</li>
    </ul>
    </li>

</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; nums.length</code></li>
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
