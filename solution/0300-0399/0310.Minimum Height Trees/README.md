# [310. 最小高度树](https://leetcode-cn.com/problems/minimum-height-trees)

[English Version](/solution/0300-0399/0310.Minimum%20Height%20Trees/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>对于一个具有树特征的无向图，我们可选择任何一个节点作为根。图因此可以成为树，在所有可能的树中，具有最小高度的树被称为最小高度树。给出这样的一个图，写出一个函数找到所有的最小高度树并返回他们的根节点。</p>

<p><strong>格式</strong></p>

<p>该图包含&nbsp;<code>n</code>&nbsp;个节点，标记为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>。给定数字&nbsp;<code>n</code>&nbsp;和一个无向边&nbsp;<code>edges</code>&nbsp;列表（每一个边都是一对标签）。</p>

<p>你可以假设没有重复的边会出现在&nbsp;<code>edges</code>&nbsp;中。由于所有的边都是无向边， <code>[0, 1]</code>和&nbsp;<code>[1, 0]</code>&nbsp;是相同的，因此不会同时出现在&nbsp;<code>edges</code>&nbsp;里。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> <code>n = 4</code>, <code>edges = [[1, 0], [1, 2], [1, 3]]</code>

        0
        |
        1
       / \
      2   3 

<strong>输出:</strong> <code>[1]</code>
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> <code>n = 6</code>, <code>edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]</code>

     0  1  2
      \ | /
        3
        |
        4
        |
        5 

<strong>输出:</strong> <code>[3, 4]</code></pre>

<p><strong>说明</strong>:</p>

<ul>
	<li>&nbsp;根据<a href="https://baike.baidu.com/item/%E6%A0%91/2699484?fromtitle=%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84+%E6%A0%91&amp;fromid=12062173&amp;fr=aladdin" target="_blank">树的定义</a>，树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。</li>
	<li>树的高度是指根节点和叶子节点之间最长向下路径上边的数量。</li>
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
