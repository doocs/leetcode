# [1123. 最深叶节点的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves)

[English Version](/solution/1100-1199/1123.Lowest%20Common%20Ancestor%20of%20Deepest%20Leaves/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个有根节点的二叉树，找到它最深的叶节点的最近公共祖先。</p>

<p>回想一下：</p>

<ul>
	<li><strong>叶节点</strong> 是二叉树中没有子节点的节点</li>
	<li>树的根节点的 <strong>深度 </strong>为 <code>0</code>，如果某一节点的深度为 <code>d</code>，那它的子节点的深度就是 <code>d+1</code></li>
	<li>如果我们假定 <code>A</code> 是一组节点 <code>S</code> 的 <strong>最近公共祖先</strong>，<code>S</code> 中的每个节点都在以 <code>A</code> 为根节点的子树中，且 <code>A</code> 的深度达到此条件下可能的最大值。</li>
</ul>

<p> </p>

<p>注意：本题与力扣 865 重复：<a href="https://leetcode-cn.com/problems/smallest-subtree-with-all-the-deepest-nodes/">https://leetcode-cn.com/problems/smallest-subtree-with-all-the-deepest-nodes/</a></p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1123.Lowest%20Common%20Ancestor%20of%20Deepest%20Leaves/images/sketch1.png" style="width: 600px; height: 510px;" />
<pre>
<strong>输入：</strong>root = [3,5,1,6,2,0,8,null,null,7,4]
<strong>输出：</strong>[2,7,4]
<strong>解释：</strong>
我们返回值为 2 的节点，在图中用黄色标记。
在图中用蓝色标记的是树的最深的节点。
注意，节点 6、0 和 8 也是叶节点，但是它们的深度是 2 ，而节点 7 和 4 的深度是 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [1]
<strong>输出：</strong>[1]
<strong>解释：</strong>根节点是树中最深的节点，它是它本身的最近公共祖先。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [0,1,3,null,2]
<strong>输出：</strong>[2]
<strong>解释：</strong>树中最深的叶节点是 2 ，最近公共祖先是它自己。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>给你的树中将有 1 到 1000 个节点。</li>
	<li>树中每个节点的值都在 1 到 1000 之间。</li>
	<li>每个节点的值都是独一无二的。</li>
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
