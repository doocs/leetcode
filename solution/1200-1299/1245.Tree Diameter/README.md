# [1245. 树的直径](https://leetcode-cn.com/problems/tree-diameter)

[English Version](/solution/1200-1299/1245.Tree%20Diameter/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给你这棵「无向树」，请你测算并返回它的「直径」：这棵树上最长简单路径的 <strong>边数</strong>。</p>

<p>我们用一个由所有「边」组成的数组 <code>edges</code> 来表示一棵无向树，其中 <code>edges[i] = [u, v]</code> 表示节点 <code>u</code> 和 <code>v</code> 之间的双向边。</p>

<p>树上的节点都已经用 <code>{0, 1, ..., edges.length}</code> 中的数做了标记，每个节点上的标记都是独一无二的。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

![](./images/1397_example_1.png)

<pre><strong>输入：</strong>edges = [[0,1],[0,2]]
<strong>输出：</strong>2
<strong>解释：</strong>
这棵树上最长的路径是 1 - 0 - 2，边数为 2。
</pre>

<p><strong>示例 2：</strong></p>

![](./images/1397_example_2.png)

<pre><strong>输入：</strong>edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
<strong>输出：</strong>4
<strong>解释： </strong>
这棵树上最长的路径是 3 - 2 - 1 - 4 - 5，边数为 4。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= edges.length < 10^4</code></li>
	<li><code>edges[i][0] != edges[i][1]</code></li>
	<li><code>0 <= edges[i][j] <= edges.length</code></li>
	<li><code>edges</code> 会形成一棵无向树</li>
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