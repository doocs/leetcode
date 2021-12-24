# [742. 二叉树最近的叶节点](https://leetcode-cn.com/problems/closest-leaf-in-a-binary-tree)

[English Version](/solution/0700-0799/0742.Closest%20Leaf%20in%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <strong>每个结点的值互不相同</strong>&nbsp;的二叉树，和一个目标值 <code>k</code>，找出树中与目标值 <code>k</code> 最近的叶结点。&nbsp;</p>

<p>这里，与叶结点 <em>最近 </em>表示在二叉树中到达该叶节点需要行进的边数与到达其它叶结点相比最少。而且，当一个结点没有孩子结点时称其为叶结点。</p>

<p>在下面的例子中，输入的树以逐行的平铺形式表示。实际上的有根树 <code>root</code> 将以TreeNode对象的形式给出。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
root = [1, 3, 2], k = 1
二叉树图示：
          1
         / \
        3   2

<strong>输出：</strong> 2 (或 3)

<strong>解释：</strong> 2 和 3 都是距离目标 1 最近的叶节点。
</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>
root = [1], k = 1
<strong>输出：</strong>1

<strong>解释：</strong> 最近的叶节点是根结点自身。
</pre>

<p>&nbsp;</p>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>
root = [1,2,3,4,null,null,null,5,null,6], k = 2
二叉树图示：
             1
            / \
           2   3
          /
         4
        /
       5
      /
     6

<strong>输出：</strong>3
<strong>解释：</strong> 值为 3（而不是值为 6）的叶节点是距离结点 2 的最近结点。
</pre>

<p>&nbsp;</p>

<p><strong>注：</strong></p>

<ol>
	<li><code>root</code>&nbsp;表示的二叉树最少有&nbsp;<code>1</code> 个结点且最多有&nbsp;<code>1000</code> 个结点。</li>
	<li>每个结点都有一个唯一的&nbsp;<code>node.val</code>&nbsp;，范围为&nbsp;<code>[1, 1000]</code>。</li>
	<li>给定的二叉树中有某个结点使得&nbsp;<code>node.val == k</code>。</li>
</ol>

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
