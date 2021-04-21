# [1612. 检查两棵二叉表达式树是否等价](https://leetcode-cn.com/problems/check-if-two-expression-trees-are-equivalent)

[English Version](/solution/1600-1699/1612.Check%20If%20Two%20Expression%20Trees%20are%20Equivalent/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong><a href="https://en.wikipedia.org/wiki/Binary_expression_tree" target="_blank">二叉表达式树</a></strong>是一种表达算术表达式的二叉树。二叉表达式树中的每一个节点都有零个或两个子节点。 叶节点（有 0 个子节点的节点）表示操作数，非叶节点（有 2 个子节点的节点）表示运算符。在本题中，我们只考虑 <code>'+'</code> 运算符（即加法）。</p>

<p>给定两棵二叉表达式树的根节点 <code>root1</code> 和 <code>root2</code> 。<em>如果两棵二叉表达式树等价</em>，返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>当两棵二叉搜索树中的变量取任意值，<strong>分别求得的值都相等</strong>时，我们称这两棵二叉表达式树是等价的。</p>

<p><b>进阶：</b>当你的答案需同时支持 <code>'-'</code> 运算符（减法）时，你该如何修改你的答案？</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<b>输入：</b> root1 = [x], root2 = [x]
<b>输出：</b> true
</pre>

<p><strong>示例 2:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/10/04/tree1.png" style="width: 211px; height: 131px;" /></strong></p>

<pre>
<b>输入：</b>root1 = [+,a,+,null,null,b,c], root2 = [+,+,a,b,c]
<b>输出：</b>true
<code><span style=""><b>解释：</b></span>a + (b + c) == (b + c) + a</code></pre>

<p><strong>示例 3:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/10/04/tree2.png" style="width: 211px; height: 131px;" /></strong></p>

<pre>
<b>输入：</b> root1 = [+,a,+,null,null,b,c], root2 = [+,+,a,b,d]
<b>输出：</b> false
<b>解释：</b> <code>a + (b + c) != (b + d) + a</code>
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>两棵树中的节点个数相等，且节点个数为范围 <code>[1, 4999]</code> 内的奇数。</li>
	<li><code>Node.val</code> 是 <code>'+'</code> 或小写英文字母。</li>
	<li>给定的树<strong>保证</strong>是有效的二叉表达式树。</li>
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
