# [2625. 扁平化嵌套数组](https://leetcode.cn/problems/flatten-deeply-nested-array)

[English Version](/solution/2600-2699/2625.Flatten%20Deeply%20Nested%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写一个函数，它接收一个&nbsp;<strong>多维数组&nbsp;</strong><code>arr</code> 和它的深度 <code>n</code> ，并返回该数组的&nbsp;<strong>扁平化&nbsp;</strong>后的结果。</p>

<p><strong>多维数组&nbsp;</strong>是一种包含整数或其他&nbsp;<strong>多维数组&nbsp;</strong>的递归数据结构。</p>

<p>数组 <strong>扁平化</strong> 是对数组的一种操作，定义是将原数组部分或全部子数组删除，并替换为该子数组中的实际元素。只有当嵌套的数组深度大于 <code>n</code> 时，才应该执行扁平化操作。第一层数组中元素的深度被认为是 0。</p>

<p>请在没有使用内置方法&nbsp;<code>Array.flat</code> 的前提下解决这个问题。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入</strong>
arr = [1, 2, 3, [4, 5, 6], [7, 8, [9, 10, 11], 12], [13, 14, 15]]
n = 0
<strong>输出</strong>
[1, 2, 3, [4, 5, 6], [7, 8, [9, 10, 11], 12], [13, 14, 15]]

<strong>解释</strong>
传递深度 n=0 的多维数组将始终得到原始数组。这是因为 子数组(0) 的最小可能的深度不小于 n=0 。因此，任何子数组都不应该被平面化。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入</strong>
arr = [1, 2, 3, [4, 5, 6], [7, 8, [9, 10, 11], 12], [13, 14, 15]]
n = 1
<strong>输出</strong>
[1, 2, 3, 4, 5, 6, 7, 8, [9, 10, 11], 12, 13, 14, 15]

<strong>解释</strong>
以 4 、7 和 13 开头的子数组都被扁平化了，这是因为它们的深度为 0 ， 而 0 小于 1 。然而 [9,10,11] 其深度为 1 ，所以未被扁平化。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入</strong>
arr = [[1, 2, 3], [4, 5, 6], [7, 8, [9, 10, 11], 12], [13, 14, 15]]
n = 2
<strong>输出</strong>
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]

<strong>解释</strong>
所有子数组的最大深度都为 1 。因此，它们都被扁平化了。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= arr 的元素个数&nbsp;&lt;=&nbsp;10<sup>5</sup></code></li>
	<li><code>0 &lt;= arr 的子数组个数&nbsp;&lt;=&nbsp;10<sup>5</sup></code></li>
	<li><code>maxDepth &lt;= 1000</code></li>
	<li><code>-1000 &lt;= each number &lt;= 1000</code></li>
	<li><code><font face="monospace">0 &lt;= n &lt;= 1000</font></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
