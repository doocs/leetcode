# [剑指 Offer II 055. 二叉搜索树迭代器](https://leetcode-cn.com/problems/kTOapQ)

## 题目描述

<!-- 这里写题目描述 -->

<p>实现一个二叉搜索树迭代器类<code>BSTIterator</code> ，表示一个按中序遍历二叉搜索树（BST）的迭代器：</p>

<div class="original__bRMd">
<div>
<ul>
	<li><code>BSTIterator(TreeNode root)</code> 初始化 <code>BSTIterator</code> 类的一个对象。BST 的根节点 <code>root</code> 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。</li>
	<li><code>boolean hasNext()</code> 如果向指针右侧遍历存在数字，则返回 <code>true</code> ；否则返回 <code>false</code> 。</li>
	<li><code>int next()</code>将指针向右移动，然后返回指针处的数字。</li>
</ul>

<p>注意，指针初始化为一个不存在于 BST 中的数字，所以对 <code>next()</code> 的首次调用将返回 BST 中的最小元素。</p>
</div>
</div>

<p>可以假设&nbsp;<code>next()</code>&nbsp;调用总是有效的，也就是说，当调用 <code>next()</code>&nbsp;时，BST 的中序遍历中至少存在一个下一个数字。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20055.%20%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E8%BF%AD%E4%BB%A3%E5%99%A8/images/bst-tree.png" style="width: 189px; height: 178px;" /></p>

<pre>
<strong>输入</strong>
inputs = [&quot;BSTIterator&quot;, &quot;next&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;next&quot;, &quot;hasNext&quot;]
inputs = [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
<strong>输出</strong>
[null, 3, 7, true, 9, true, 15, true, 20, false]

<strong>解释</strong>
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
bSTIterator.next();    // 返回 3
bSTIterator.next();    // 返回 7
bSTIterator.hasNext(); // 返回 True
bSTIterator.next();    // 返回 9
bSTIterator.hasNext(); // 返回 True
bSTIterator.next();    // 返回 15
bSTIterator.hasNext(); // 返回 True
bSTIterator.next();    // 返回 20
bSTIterator.hasNext(); // 返回 False
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目在范围 <code>[1, 10<sup>5</sup>]</code> 内</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>6</sup></code></li>
	<li>最多调用 <code>10<sup>5</sup></code> 次 <code>hasNext</code> 和 <code>next</code> 操作</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>你可以设计一个满足下述条件的解决方案吗？<code>next()</code> 和 <code>hasNext()</code> 操作均摊时间复杂度为 <code>O(1)</code> ，并使用 <code>O(h)</code> 内存。其中 <code>h</code> 是树的高度。</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 173&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/binary-search-tree-iterator/">https://leetcode-cn.com/problems/binary-search-tree-iterator/</a></p>


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
