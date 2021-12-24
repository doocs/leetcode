# [1650. 二叉树的最近公共祖先 III](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree-iii)

[English Version](/solution/1600-1699/1650.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一棵二叉树中的两个节点 <code>p</code> 和 <code>q</code>，返回它们的最近公共祖先节点（LCA）。</p>

<p>每个节点都包含其父节点的引用（指针）。<code>Node</code> 的定义如下：</p>

<pre>class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
</pre>

<p>根据<a href="https://en.wikipedia.org/wiki/Lowest_common_ancestor">维基百科中对最近公共祖先节点的定义</a>：“两个节点 p 和 q 在二叉树 T 中的最近公共祖先节点是后代节点中既包括 p 又包括 q 的最深节点（我们允许<strong>一个节点为自身的一个后代节点</strong>）”。一个节点 x 的后代节点是节点 x 到某一叶节点间的路径中的节点 y。</p>

<p> </p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1650.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20III/images/binarytree.png" style="width: 200px; height: 190px;">
<pre><strong>输入:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
<strong>输出:</strong> 3
<strong>解释:</strong> 节点 5 和 1 的最近公共祖先是 3。
</pre>

<p><strong>示例 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1650.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20III/images/binarytree.png" style="width: 200px; height: 190px;">
<pre><strong>输入:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
<strong>输出:</strong> 5
<strong>解释:</strong> 节点 5 和 4 的最近公共祖先是 5，根据定义，一个节点可以是自身的最近公共祖先。
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> root = [1,2], p = 1, q = 2
<strong>输出:</strong> 1
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li>树中节点个数的范围是 <code>[2, 10<sup>5</sup>]</code>。</li>
	<li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li>所有的 <code>Node.val</code> 都是<strong>互不相同</strong>的。</li>
	<li><code>p != q</code></li>
	<li><code>p</code> 和 <code>q</code> 存在于树中。</li>
</ul>

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
