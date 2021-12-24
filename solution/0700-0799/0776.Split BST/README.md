# [776. 拆分二叉搜索树](https://leetcode-cn.com/problems/split-bst)

[English Version](/solution/0700-0799/0776.Split%20BST/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉搜索树（BST）、它的根结点 <code>root</code> 以及目标值 <code>V</code>。</p>

<p>请将该树按要求拆分为两个子树：其中一个子树结点的值都必须小于等于给定的目标值 <code>V</code>；另一个子树结点的值都必须大于目标值 <code>V</code>；树中并非一定要存在值为 <code>V</code> 的结点。</p>

<p>除此之外，树中大部分结构都需要保留，也就是说原始树中父节点 P 的任意子节点 C，假如拆分后它们仍在同一个子树中，那么结点 P 应仍为 C 的父结点。</p>

<p>你需要返回拆分后两个子树的根结点 TreeNode，顺序随意。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>root = [4,2,6,1,3,5,7], V = 2
<strong>输出：</strong>[[2,1],[4,3,6,null,null,5,7]]
<strong>解释：
</strong>注意根结点 output[0] 和 output[1] 都是 TreeNode 对象，不是数组。

给定的树 [4,2,6,1,3,5,7] 可化为如下示意图：

          4
        /   \
      2      6
     / \    / \
    1   3  5   7

输出的示意图如下：

          4
        /   \
      3      6       和    2
            / \           /
           5   7         1</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ol>
	<li>二叉搜索树节点个数不超过 <code>50</code> </li>
	<li>二叉搜索树始终是有效的，并且每个节点的值都不相同</li>
</ol>

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
