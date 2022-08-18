# [1586. 二叉搜索树迭代器 II](https://leetcode.cn/problems/binary-search-tree-iterator-ii)

[English Version](/solution/1500-1599/1586.Binary%20Search%20Tree%20Iterator%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>实现二叉搜索树（BST）的<a href="https://baike.baidu.com/item/中序遍历/757281?fr=aladdin">中序遍历</a>迭代器&nbsp;<code>BSTIterator</code>&nbsp;类：</p>

<ul>
	<li><code>BSTIterator(TreeNode root)</code>&nbsp;初始化&nbsp;<code>BSTIterator</code>&nbsp;类的实例。二叉搜索树的根节点&nbsp;<code>root</code>&nbsp;作为构造函数的参数传入。内部指针使用一个不存在于树中且小于树中任意值的数值来初始化。</li>
	<li><code>boolean hasNext()</code>&nbsp;如果当前指针在中序遍历序列中，存在右侧数值，返回&nbsp;<code>true</code> ，否则返回&nbsp;<code>false</code>&nbsp;。</li>
	<li><code>int next()</code>&nbsp;将指针在中序遍历序列中向右移动，然后返回移动后指针所指数值。</li>
	<li><code>boolean hasPrev()</code>&nbsp;如果当前指针在中序遍历序列中，存在左侧数值，返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。</li>
	<li><code>int prev()</code>&nbsp;将指针在中序遍历序列中向左移动，然后返回移动后指针所指数值。</li>
</ul>

<p>注意，虽然我们使用树中不存在的最小值来初始化内部指针，第一次调用&nbsp;<code>next()</code>&nbsp;需要返回二叉搜索树中最小的元素。</p>

<p>你可以假设&nbsp;<code>next()</code>&nbsp;和&nbsp;<code>prev()</code>&nbsp;的调用总是有效的。即，当&nbsp;<code>next()</code>/<code>prev()</code>&nbsp;被调用的时候，在中序遍历序列中一定存在下一个/上一个元素。</p>

<p><strong>进阶：</strong>你可以不提前遍历树中的值来解决问题吗？</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1586.Binary%20Search%20Tree%20Iterator%20II/images/untitled-diagram-1.png" style="height: 201px; width: 201px;"></strong></p>

<pre><strong>输入</strong>
[&quot;BSTIterator&quot;, &quot;next&quot;, &quot;next&quot;, &quot;prev&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;hasPrev&quot;, &quot;prev&quot;, &quot;prev&quot;]
[[[7, 3, 15, null, null, 9, 20]], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null]]
<strong>输出</strong>
[null, 3, 7, 3, 7, true, 9, 15, 20, false, true, 15, 9]

<strong>解释</strong>
// 划线的元素表示指针当前的位置。
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]); // 当前状态为 &lt;u&gt; &lt;/u&gt; [3, 7, 9, 15, 20]
bSTIterator.next(); // 状态变为 [&lt;u&gt;3&lt;/u&gt;, 7, 9, 15, 20], 返回 3
bSTIterator.next(); // 状态变为 [3, &lt;u&gt;7&lt;/u&gt;, 9, 15, 20], 返回 7
bSTIterator.prev(); // 状态变为 [&lt;u&gt;3&lt;/u&gt;, 7, 9, 15, 20], 返回 3
bSTIterator.next(); // 状态变为 [3, &lt;u&gt;7&lt;/u&gt;, 9, 15, 20], 返回 7
bSTIterator.hasNext(); // 返回 true
bSTIterator.next(); // 状态变为 [3, 7, &lt;u&gt;9&lt;/u&gt;, 15, 20], 返回 9
bSTIterator.next(); // 状态变为 [3, 7, 9, &lt;u&gt;15&lt;/u&gt;, 20], 返回 15
bSTIterator.next(); // 状态变为 [3, 7, 9, 15, &lt;u&gt;20&lt;/u&gt;], 返回 20
bSTIterator.hasNext(); // 返回 false
bSTIterator.hasPrev(); // 返回 true
bSTIterator.prev(); // 状态变为 [3, 7, 9, &lt;u&gt;15&lt;/u&gt;, 20], 返回 15
bSTIterator.prev(); // 状态变为 [3, 7, &lt;u&gt;9&lt;/u&gt;, 15, 20], 返回 9
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>树中节点个数的范围是&nbsp;<code>[1, 10<sup>5</sup>]</code>&nbsp;。</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>6</sup></code></li>
	<li>最多调用&nbsp;10<sup>5</sup>&nbsp;次&nbsp;<code>hasNext</code>、&nbsp;<code>next</code>、&nbsp;<code>hasPrev</code>&nbsp;和&nbsp;<code>prev</code>&nbsp;。</li>
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
