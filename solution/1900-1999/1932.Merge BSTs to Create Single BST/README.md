# [1932. 合并多棵二叉搜索树](https://leetcode.cn/problems/merge-bsts-to-create-single-bst)

[English Version](/solution/1900-1999/1932.Merge%20BSTs%20to%20Create%20Single%20BST/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你 <code>n</code> 个 <strong>二叉搜索树的根节点</strong> ，存储在数组 <code>trees</code> 中（<strong>下标从 0 开始</strong>），对应 <code>n</code> 棵不同的二叉搜索树。<code>trees</code> 中的每棵二叉搜索树 <strong>最多有 3 个节点</strong> ，且不存在值相同的两个根节点。在一步操作中，将会完成下述步骤：</p>

<ul>
	<li>选择两个 <strong>不同的</strong> 下标 <code>i</code> 和 <code>j</code> ，要求满足在&nbsp;<code>trees[i]</code> 中的某个 <strong>叶节点</strong> 的值等于&nbsp;<code>trees[j]</code> 的 <strong>根节点的值</strong> 。</li>
	<li>用&nbsp;<code>trees[j]</code> 替换 <code>trees[i]</code> 中的那个叶节点。</li>
	<li>从 <code>trees</code> 中移除 <code>trees[j]</code> 。</li>
</ul>

<p>如果在执行 <code>n - 1</code> 次操作后，能形成一棵有效的二叉搜索树，则返回结果二叉树的 <strong>根节点</strong> ；如果无法构造一棵有效的二叉搜索树<em>，</em>返回<em> </em><code>null</code> 。</p>

<p>二叉搜索树是一种二叉树，且树中每个节点均满足下述属性：</p>

<ul>
	<li>任意节点的左子树中的值都 <strong>严格小于</strong>&nbsp;此节点的值。</li>
	<li>任意节点的右子树中的值都 <strong>严格大于</strong>&nbsp;此节点的值。</li>
</ul>

<p>叶节点是不含子节点的节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1932.Merge%20BSTs%20to%20Create%20Single%20BST/images/d1.png" />
<pre>
<strong>输入：</strong>trees = [[2,1],[3,2,5],[5,4]]
<strong>输出：</strong>[3,2,5,1,null,4]
<strong>解释：</strong>
第一步操作中，选出 i=1 和 j=0 ，并将 trees[0] 合并到 trees[1] 中。
删除 trees[0] ，trees = [[3,2,5,1],[5,4]] 。
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1932.Merge%20BSTs%20to%20Create%20Single%20BST/images/diagram.png" />
在第二步操作中，选出 i=0 和 j=1 ，将 trees[1] 合并到 trees[0] 中。
删除 trees[1] ，trees = [[3,2,5,1,null,4]] 。
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1932.Merge%20BSTs%20to%20Create%20Single%20BST/images/diagram-2.png" />
结果树如上图所示，为一棵有效的二叉搜索树，所以返回该树的根节点。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1932.Merge%20BSTs%20to%20Create%20Single%20BST/images/d2.png" />
<pre>
<strong>输入：</strong>trees = [[5,3,8],[3,2,6]]
<strong>输出：</strong>[]
<strong>解释：</strong>
选出 i=0 和 j=1 ，然后将 trees[1] 合并到 trees[0] 中。
删除 trees[1] ，trees = [[5,3,8,2,6]] 。
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1932.Merge%20BSTs%20to%20Create%20Single%20BST/images/diagram-3.png" />
结果树如上图所示。仅能执行一次有效的操作，但结果树不是一棵有效的二叉搜索树，所以返回 null 。
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1932.Merge%20BSTs%20to%20Create%20Single%20BST/images/d3.png" />
<pre>
<strong>输入：</strong>trees = [[5,4],[3]]
<strong>输出：</strong>[]
<strong>解释：</strong>无法执行任何操作。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == trees.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li>每棵树中节点数目在范围 <code>[1, 3]</code> 内。</li>
	<li>输入数据的每个节点可能有子节点但不存在子节点的子节点</li>
	<li><code>trees</code> 中不存在两棵树根节点值相同的情况。</li>
	<li>输入中的所有树都是 <strong>有效的二叉树搜索树</strong> 。</li>
	<li><code>1 &lt;= TreeNode.val &lt;= 5 * 10<sup>4</sup></code>.</li>
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
