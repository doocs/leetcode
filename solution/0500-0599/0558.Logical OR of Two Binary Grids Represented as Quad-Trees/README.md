# [558. 四叉树交集](https://leetcode-cn.com/problems/logical-or-of-two-binary-grids-represented-as-quad-trees)

[English Version](/solution/0500-0599/0558.Logical%20OR%20of%20Two%20Binary%20Grids%20Represented%20as%20Quad-Trees/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>四叉树是一种树数据，其中每个结点恰好有四个子结点：<code>topLeft</code>、<code>topRight</code>、<code>bottomLeft</code>&nbsp;和&nbsp;<code>bottomRight</code>。四叉树通常被用来划分一个二维空间，递归地将其细分为四个象限或区域。</p>

<p>我们希望在四叉树中存储 True/False 信息。四叉树用来表示 <code>N * N</code> 的布尔网格。对于每个结点, 它将被等分成四个孩子结点<strong>直到这个区域内的值都是相同的</strong>。每个节点都有另外两个布尔属性：<code>isLeaf</code>&nbsp;和&nbsp;<code>val</code>。当这个节点是一个叶子结点时&nbsp;<code>isLeaf</code>&nbsp;为真。<code>val</code>&nbsp;变量储存叶子结点所代表的区域的值。</p>

<p>例如，下面是两个四叉树 A 和 B：</p>

<pre>A:
+-------+-------+   T: true
|       |       |   F: false
|   T   |   T   |
|       |       |
+-------+-------+
|       |       |
|   F   |   F   |
|       |       |
+-------+-------+
topLeft: T
topRight: T
bottomLeft: F
bottomRight: F

B:               
+-------+---+---+
|       | F | F |
|   T   +---+---+
|       | T | T |
+-------+---+---+
|       |       |
|   T   |   F   |
|       |       |
+-------+-------+
topLeft: T
topRight:
     topLeft: F
     topRight: F
     bottomLeft: T
     bottomRight: T
bottomLeft: T
bottomRight: F
</pre>

<p>&nbsp;</p>

<p>你的任务是实现一个函数，该函数根据两个四叉树返回表示这两个四叉树的逻辑或(或并)的四叉树。</p>

<pre>A:                 B:                 C (A or B):
+-------+-------+  +-------+---+---+  +-------+-------+
|       |       |  |       | F | F |  |       |       |
|   T   |   T   |  |   T   +---+---+  |   T   |   T   |
|       |       |  |       | T | T |  |       |       |
+-------+-------+  +-------+---+---+  +-------+-------+
|       |       |  |       |       |  |       |       |
|   F   |   F   |  |   T   |   F   |  |   T   |   F   |
|       |       |  |       |       |  |       |       |
+-------+-------+  +-------+-------+  +-------+-------+
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>A</code>&nbsp;和&nbsp;<code>B</code>&nbsp;都表示大小为&nbsp;<code>N * N</code>&nbsp;的网格。</li>
	<li><code>N</code>&nbsp;将确保是 2 的整次幂。</li>
	<li>如果你想了解更多关于四叉树的知识，你可以参考这个&nbsp;<a href="https://en.wikipedia.org/wiki/Quadtree">wiki</a>&nbsp;页面。</li>
	<li>逻辑或的定义如下：如果&nbsp;<code>A 为 True</code> ，或者&nbsp;<code>B 为 True</code> ，或者&nbsp;<code>A 和 B 都为 True</code>，则 &quot;A 或 B&quot; 为 True。</li>
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
