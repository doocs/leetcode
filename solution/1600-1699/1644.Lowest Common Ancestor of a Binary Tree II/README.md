# [1644. 二叉树的最近公共祖先 II](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree-ii)

[English Version](/solution/1600-1699/1644.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一棵二叉树的根节点 <code>root</code>，返回给定节点 <code>p</code> 和 <code>q</code> 的最近公共祖先（LCA）节点。如果 <code>p</code> 或 <code>q</code> 之一<strong> 不存在</strong> 于该二叉树中，返回 <code>null</code>。树中的每个节点值都是互不相同的。</p>

<p>根据<a href="https://en.wikipedia.org/wiki/Lowest_common_ancestor" target="_blank">维基百科中对最近公共祖先节点的定义</a>：“两个节点 <code>p</code> 和 <code>q</code> 在二叉树 <code>T</code> 中的最近公共祖先节点是<strong> 后代节点 </strong>中既包括 <code>p</code>&nbsp;又包括&nbsp;<code>q</code>&nbsp;的最深节点（我们允许<strong> 一个节点为自身的一个后代节点 </strong>）”。一个节点 <code>x</code>&nbsp;的<strong> 后代节点 </strong>是节点&nbsp;<code>x</code> 到某一叶节点间的路径中的节点 <code>y</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1644.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20II/images/binarytree.png" />
<pre>
<b>输入：</b> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
<b>输出：</b> 3
<b>解释：</b> 节点 5 和 1 的共同祖先节点是 3。</pre>

<p><strong>示例 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1644.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20II/images/binarytree.png" /></p>

<pre>
<b>输入：</b> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
<b>输出：</b> 5
<b>解释：</b> 节点 5 和 4 的共同祖先节点是 5。根据共同祖先节点的定义，一个节点可以是自身的后代节点。</pre>

<p><strong>示例 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1644.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20II/images/binarytree.png" /></p>

<pre>
<strong>输入：</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
<b>输出：</b> null
<b>解释：</b> 节点 10 不存在于树中，所以返回 null。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>树中节点个数的范围是&nbsp;<code>[1, 10<sup>4</sup>]</code></li>
	<li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li>所有节点的值&nbsp;<code>Node.val</code> <strong>互不相同</strong></li>
	<li><code>p != q</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong> 在不检查节点是否存在的情况下，你可以遍历树找出最近公共祖先节点吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
