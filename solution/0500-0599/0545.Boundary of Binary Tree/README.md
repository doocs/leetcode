# [545. 二叉树的边界](https://leetcode-cn.com/problems/boundary-of-binary-tree)

[English Version](/solution/0500-0599/0545.Boundary%20of%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>二叉树的 <strong>边界</strong> 是由 <strong>根节点 </strong>、<strong>左边界</strong> 、按从左到右顺序的<strong> 叶节点</strong> 和 <strong>逆序的右边界</strong> ，按顺序依次连接组成。</p>

<p><strong>左边界 </strong>是满足下述定义的节点集合：</p>

<ul>
	<li>根节点的左子节点在左边界中。如果根节点不含左子节点，那么左边界就为 <strong>空</strong> 。</li>
	<li>如果一个节点在左边界中，并且该节点有左子节点，那么它的左子节点也在左边界中。</li>
	<li>如果一个节点在左边界中，并且该节点 <strong>不含</strong> 左子节点，那么它的右子节点就在左边界中。</li>
	<li>最左侧的叶节点 <strong>不在</strong> 左边界中。</li>
</ul>

<p><strong>右边界</strong> 定义方式与 <strong>左边界</strong> 相同，只是将左替换成右。即，右边界是根节点右子树的右侧部分；叶节点 <strong>不是</strong> 右边界的组成部分；如果根节点不含右子节点，那么右边界为 <strong>空</strong> 。</p>

<p><strong>叶节点</strong> 是没有任何子节点的节点。对于此问题，根节点 <strong>不是</strong> 叶节点。</p>

<p>给你一棵二叉树的根节点 <code>root</code> ，按顺序返回组成二叉树 <strong>边界</strong> 的这些值。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/11/boundary1.jpg" style="width: 299px; height: 290px;" />
<pre>
<strong>输入：</strong>root = [1,null,2,3,4]
<strong>输出：</strong>[1,3,4,2]
<b>解释：</b>
- 左边界为空，因为二叉树不含左子节点。
- 右边界是 [2] 。从根节点的右子节点开始的路径为 2 -> 4 ，但 4 是叶节点，所以右边界只有 2 。
- 叶节点从左到右是 [3,4] 。
按题目要求依序连接得到结果 [1] + [] + [3,4] + [2] = [1,3,4,2] 。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/11/boundary2.jpg" style="width: 599px; height: 411px;" />
<pre>
<strong>输入：</strong>root = [1,2,3,4,5,6,null,null,null,7,8,9,10]
<strong>输出：</strong>[1,2,4,7,8,9,10,6,3]
<b>解释：</b>
- 左边界为 [2] 。从根节点的左子节点开始的路径为 2 -> 4 ，但 4 是叶节点，所以左边界只有 2 。
- 右边界是 [3,6] ，逆序为 [6,3] 。从根节点的右子节点开始的路径为 3 -> 6 -> 10 ，但 10 是叶节点。
- 叶节点从左到右是 [4,7,8,9,10]
按题目要求依序连接得到结果 [1] + [2] + [4,7,8,9,10] + [6,3] = [1,2,4,7,8,9,10,6,3] 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目在范围 <code>[1, 10<sup>4</sup>]</code> 内</li>
	<li><code>-1000 <= Node.val <= 1000</code></li>
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
