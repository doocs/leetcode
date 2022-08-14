# [1666. 改变二叉树的根节点](https://leetcode.cn/problems/change-the-root-of-a-binary-tree)

[English Version](/solution/1600-1699/1666.Change%20the%20Root%20of%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一棵二叉树的根节点 <code>root</code> 和一个叶节点 <code>leaf</code> ，更改二叉树，使得 <code>leaf</code> 为新的根节点。</p>

<p>你可以按照下列步骤修改<strong>从</strong> <code>leaf</code> <strong>到</strong> <code>root</code> <strong>的路径中除</strong> <code>root</code> <strong>外的每个节点</strong> <code>cur</code> ：</p>

<ol>
	<li>如果 <code>cur</code> 有左子节点，则该子节点变为 <code>cur</code> 的右子节点。注意我们保证 <code>cur</code> 至多有一个子节点。</li>
	<li><code>cur</code> 的原父节点变为 <code>cur</code> 的左子节点。</li>
</ol>

<p>返回修改后新树的根节点。</p>

<p><b>注意：</b>确保你的答案在操作后正确地设定了 <code>Node.parent</code> （父节点）指针，否则会被判为错误答案。</p>

<p> </p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1666.Change%20the%20Root%20of%20a%20Binary%20Tree/images/fliptree.png" style="width: 400px; height: 298px;">
<pre><strong>输入:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], leaf = 7
<strong>输出:</strong> [7,2,null,5,4,3,6,null,null,null,1,null,null,0,8]
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], leaf = 0
<strong>输出:</strong> [0,1,null,3,8,5,null,null,null,6,2,null,null,7,4]
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li>树中节点的个数在范围 <code>[2, 100]</code> 内。</li>
	<li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li>所有的 <code>Node.val</code> 都是<strong>唯一</strong>的。</li>
	<li><code>leaf</code> 存在于树中。</li>
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
