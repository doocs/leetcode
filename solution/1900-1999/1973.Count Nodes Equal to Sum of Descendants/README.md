# [1973. 值等于子节点值之和的节点数量](https://leetcode.cn/problems/count-nodes-equal-to-sum-of-descendants)

[English Version](/solution/1900-1999/1973.Count%20Nodes%20Equal%20to%20Sum%20of%20Descendants/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一颗二叉树的根节点&nbsp;<code>root</code>&nbsp;，返回满足条件：节点的值等于该节点所有子节点的值之和&nbsp;<em>的节点的数量。</em></p>

<p>一个节点&nbsp;<code>x</code>&nbsp;的&nbsp;<strong>子节点</strong>&nbsp;是指从节点&nbsp;<code>x</code>&nbsp;出发，到所有叶子节点路径上的节点。没有子节点的节点的子节点和视为&nbsp;<code>0</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1973.Count%20Nodes%20Equal%20to%20Sum%20of%20Descendants/images/screenshot-2021-08-17-at-17-16-50-diagram-drawio-diagrams-net.png" style="width: 250px; height: 207px;" />
<pre>
<strong>输入:</strong> root = [10,3,4,2,1]
<strong>输出:</strong> 2
<strong>解释:</strong>
对于值为10的节点: 其子节点之和为： 3+4+2+1 = 10。
对于值为3的节点：其子节点之和为： 2+1 = 3。
</pre>

<p><strong>示例&nbsp;2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1973.Count%20Nodes%20Equal%20to%20Sum%20of%20Descendants/images/screenshot-2021-08-17-at-17-25-21-diagram-drawio-diagrams-net.png" style="height: 196px; width: 200px;" />
<pre>
<strong>输入:</strong> root = [2,3,null,2,null]
<strong>输出:</strong> 0
<strong>解释:</strong>
没有节点满足其值等于子节点之和。
</pre>

<p><strong>示例&nbsp;3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1973.Count%20Nodes%20Equal%20to%20Sum%20of%20Descendants/images/screenshot-2021-08-17-at-17-23-53-diagram-drawio-diagrams-net.png" style="width: 50px; height: 50px;" />
<pre>
<strong>输入:</strong> root = [0]
<strong>输出:</strong> 1
<strong>解释:</strong>
对于值为0的节点：因为它没有子节点，所以自己点之和为0。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数量范围：&nbsp;<code>[1, 10<sup>5</sup>]</code></li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
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
