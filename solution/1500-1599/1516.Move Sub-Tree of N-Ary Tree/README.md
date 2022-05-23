# [1516. 移动 N 叉树的子树](https://leetcode.cn/problems/move-sub-tree-of-n-ary-tree)

[English Version](/solution/1500-1599/1516.Move%20Sub-Tree%20of%20N-Ary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一棵没有重复值的 <a href="http://leetcode.com/articles/introduction-to-n-ary-trees/">N 叉树</a>的根节点&nbsp;<code>root</code>&nbsp;，以及其中的两个节点&nbsp;<code>p</code> 和&nbsp;<code>q</code>。</p>

<p>移动节点&nbsp;<code>p</code>&nbsp;及其子树，使节点 <code>p</code>&nbsp;成为节点&nbsp;<code>q</code>&nbsp;的直接子节点。如果&nbsp;<code>p</code>&nbsp;已经是&nbsp;<code>q</code>&nbsp;的直接子节点，则请勿改动任何节点。节点&nbsp;<code>p</code> <strong>必须</strong>是节点&nbsp;<code>q</code>&nbsp;的子节点列表的最后一项。</p>

<p>返回改动后的<em>树的根节点</em>。</p>

<p>&nbsp;</p>

<p>节点&nbsp;<code>p</code>&nbsp;和&nbsp;<code>q</code>&nbsp;可能是下列三种情况之一：</p>

<ol>
	<li>节点&nbsp;<code>q</code>&nbsp;在节点&nbsp;<code>p</code>&nbsp;的子树中。</li>
	<li>节点&nbsp;<code>p</code>&nbsp;在节点&nbsp;<code>q</code>&nbsp;的子树中。</li>
	<li>节点&nbsp;<code>p</code>&nbsp;不在节点&nbsp;<code>q</code>&nbsp;的子树中，且节点&nbsp;<code>q</code> 也不在节点&nbsp;<code>p</code>&nbsp;的子树中。</li>
</ol>

<p>在第 2 种和第 3 种情况中，你只需要移动&nbsp;<code>p</code>&nbsp;（及其子树），使 <code>p</code>&nbsp;成为&nbsp;<code>q</code>&nbsp;的子节点。但是在第 1 种情况中，树的节点可能会断连，因此你还需要重新连接这些节点。<strong>请在解题前仔细阅读示例。</strong></p>

<p>&nbsp;</p>

<p><em>N 叉树的输入序列以层序遍历的形式给出，每组子节点用 null 分隔（见示例）。</em></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1516.Move%20Sub-Tree%20of%20N-Ary%20Tree/images/sample_4_964.png" style="height: 269px; width: 296px;" /></p>

<p>例如，上面的树会被序列化为&nbsp;[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1516.Move%20Sub-Tree%20of%20N-Ary%20Tree/images/move_e1.jpg" style="height: 188px; width: 450px;" /></p>

<pre>
<strong>输入:</strong> root = [1,null,2,3,null,4,5,null,6,null,7,8], p = 4, q = 1
<strong>输出:</strong> [1,null,2,3,4,null,5,null,6,null,7,8]
<strong>解释:</strong> 该示例属于第二种情况，节点 p 在节点 q 的子树中。我们可以移动节点 p 及其子树，使 p 成为节点 q 的直接子节点。
注意，节点 4 是节点 1 的最后一个子节点。</pre>

<p><strong>示例 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1516.Move%20Sub-Tree%20of%20N-Ary%20Tree/images/move_e2.jpg" style="height: 281px; width: 281px;" /></p>

<pre>
<strong>输入:</strong> root = [1,null,2,3,null,4,5,null,6,null,7,8], p = 7, q = 4
<strong>输出:</strong> [1,null,2,3,null,4,5,null,6,null,7,8]
<strong>解释:</strong> 节点 7 已经是节点 4 的直接子节点，因此我们不改动任何节点。
</pre>

<p><strong>示例 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1516.Move%20Sub-Tree%20of%20N-Ary%20Tree/images/move_e3.jpg" style="height: 331px; width: 450px;" /></p>

<pre>
<strong>输入:</strong> root = [1,null,2,3,null,4,5,null,6,null,7,8], p = 3, q = 8
<strong>输出:</strong> [1,null,2,null,4,5,null,7,8,null,null,null,3,null,6]
<strong>解释:</strong> 该示例属于第三种情况，节点 p 不在节点 q 的子树中，反之亦然。我们可以移动节点 3 及其子树，使之成为节点 8 的子节点。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>节点的总数在&nbsp;<code>[2,&nbsp;1000]</code>&nbsp;间。</li>
	<li>每个节点都有&nbsp;<strong>唯一&nbsp;</strong>的值。</li>
	<li><code>p != null</code></li>
	<li><code>q != null</code></li>
	<li><code>p</code>&nbsp;和&nbsp;<code>q</code>&nbsp;是两个不同的节点（即&nbsp;<code>p != q</code>&nbsp;）。</li>
</ul>

<p>&nbsp;</p>

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
