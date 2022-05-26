# [1028. 从先序遍历还原二叉树](https://leetcode.cn/problems/recover-a-tree-from-preorder-traversal)

[English Version](/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们从二叉树的根节点 <code>root</code>&nbsp;开始进行深度优先搜索。</p>

<p>在遍历中的每个节点处，我们输出&nbsp;<code>D</code>&nbsp;条短划线（其中&nbsp;<code>D</code>&nbsp;是该节点的深度），然后输出该节点的值。（<em>如果节点的深度为 <code>D</code>，则其直接子节点的深度为 <code>D + 1</code>。根节点的深度为 <code>0</code>）。</em></p>

<p>如果节点只有一个子节点，那么保证该子节点为左子节点。</p>

<p>给出遍历输出&nbsp;<code>S</code>，还原树并返回其根节点&nbsp;<code>root</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/images/recover-a-tree-from-preorder-traversal.png" style="height: 200px; width: 320px;"></strong></p>

<pre><strong>输入：</strong>&quot;1-2--3--4-5--6--7&quot;
<strong>输出：</strong>[1,2,5,3,4,6,7]
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/images/screen-shot-2019-04-10-at-114101-pm.png" style="height: 250px; width: 256px;"></strong></p>

<pre><strong>输入：</strong>&quot;1-2--3---4-5--6---7&quot;
<strong>输出：</strong>[1,2,5,3,null,6,null,4,null,7]
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1028.Recover%20a%20Tree%20From%20Preorder%20Traversal/images/screen-shot-2019-04-10-at-114955-pm.png" style="height: 250px; width: 276px;"></p>

<pre><strong>输入：</strong>&quot;1-401--349---90--88&quot;
<strong>输出：</strong>[1,401,null,349,88,90]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>原始树中的节点数介于 <code>1</code> 和 <code>1000</code> 之间。</li>
	<li>每个节点的值介于 <code>1</code> 和 <code>10 ^ 9</code> 之间。</li>
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
