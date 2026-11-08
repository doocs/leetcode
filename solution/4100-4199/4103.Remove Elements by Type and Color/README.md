---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4103. 按类型和颜色删除元素](https://leetcode.cn/problems/remove-elements-by-type-and-color)

[English Version](/solution/4100-4199/4103.Remove%20Elements%20by%20Type%20and%20Color/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你三个整数数组 <code>types</code>、<code>colors</code> 和 <code>groupSize</code>，其中 <code>types</code> 和 <code>colors</code> 的长度为 <code>n</code>，<code>groupSize</code> 的长度为 <code>m</code>。</p>

<ul>
	<li><code>types[i]</code> 表示下标 <code>i</code> 处元素的类型。</li>
	<li><code>colors[i]</code> 表示下标 <code>i</code> 处元素的颜色。</li>
	<li><code>groupSize[t]</code> 表示类型为 <code>t</code> 的删除组需要的元素数量。</li>
</ul>

<p>在一次操作中，如果 <strong>恰好</strong>&nbsp;<code>groupSize[t]</code> 个&nbsp;<strong>连续&nbsp;</strong>元素都具有类型 <code>t</code> 且颜色&nbsp;<strong>相同&nbsp;</strong>，你可以将它们移除。</p>

<p>在每次移除后，剩余的元素会重新&nbsp;<strong>相邻&nbsp;</strong>，同时保持它们的相对顺序。</p>

<p>如果可以通过一系列有效操作移除所有元素，返回 <code>true</code>。否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">types = [0,1,1,0], colors = [5,3,3,5] , groupSize = [2,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>首先，移除下标 1 和 2 处的连续元素。这两个元素的类型均为 1，颜色均为 3，且组大小等于 <code>groupSize[1] = 2</code>。</li>
	<li>剩余的序列为：<code>types = [0, 0]</code>，<code>colors = [5, 5]</code></li>
	<li>现在移除剩下的两个元素。它们的类型为 0，颜色为 5，且组大小等于 <code>groupSize[0] = 2</code>。</li>
	<li>所有元素已被移除，因此答案是 <code>true</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">types = [0,1,0], colors = [9,9,9], groupSize = [3,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>无法执行任何有效操作，因为没有一个连续组满足其所需的大小。</li>
	<li>因此，无法移除所有元素，所以答案是 <code>false</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">types = [2,0,1,2], colors = [7,5,4,7], groupSize = [3,1,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>移除下标 2 处的元素。它的类型为 1，颜色为 4，并形成一个大小为 <code>groupSize[1] = 1</code> 的连续组。</li>
	<li>剩余的序列为：<code>types = [2, 0, 2]</code>，<code>colors = [7, 5, 7]</code>。</li>
	<li>无法再执行进一步的操作，因此答案是 <code>false</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == types.length == colors.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m == groupSize.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= types[i] &lt;= m - 1</code></li>
	<li><code>1 &lt;= colors[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= groupSize[t] &lt;= n</code></li>
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
