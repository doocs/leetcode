# [987. 二叉树的垂序遍历](https://leetcode-cn.com/problems/vertical-order-traversal-of-a-binary-tree)

[English Version](/solution/0900-0999/0987.Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定二叉树，按<em>垂序</em>遍历返回其结点值。</p>

<p>对位于&nbsp;<code>(X, Y)</code>&nbsp;的每个结点而言，其左右子结点分别位于&nbsp;<code>(X-1, Y-1)</code>&nbsp;和&nbsp;<code>(X+1, Y-1)</code>。</p>

<p>把一条垂线从&nbsp;<code>X = -infinity</code>&nbsp;移动到&nbsp;<code>X = +infinity</code>&nbsp;，每当该垂线与结点接触时，我们按从上到下的顺序报告结点的值（ <code>Y</code>&nbsp;坐标递减）。</p>

<p>如果两个结点位置相同，则首先报告的结点值较小。</p>

<p>按&nbsp;<code>X</code>&nbsp;坐标顺序返回非空报告的列表。每个报告都有一个结点值列表。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/02/1236_example_1.PNG" style="height: 186px; width: 201px;"></p>

<pre><strong>输入：</strong>[3,9,20,null,null,15,7]
<strong>输出：</strong>[[9],[3,15],[20],[7]]
<strong>解释： </strong>
在不丧失其普遍性的情况下，我们可以假设根结点位于 (0, 0)：
然后，值为 9 的结点出现在 (-1, -1)；
值为 3 和 15 的两个结点分别出现在 (0, 0) 和 (0, -2)；
值为 20 的结点出现在 (1, -1)；
值为 7 的结点出现在 (2, -2)。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/tree2.png" style="height: 150px; width: 236px;"></strong></p>

<pre><strong>输入：</strong>[1,2,3,4,5,6,7]
<strong>输出：</strong>[[4],[2],[1,5,6],[3],[7]]
<strong>解释：</strong>
根据给定的方案，值为 5 和 6 的两个结点出现在同一位置。
然而，在报告 &quot;[1,5,6]&quot; 中，结点值 5 排在前面，因为 5 小于 6。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>树的结点数介于 <code>1</code>&nbsp;和&nbsp;<code>1000</code>&nbsp;之间。</li>
	<li>每个结点值介于&nbsp;<code>0</code>&nbsp;和&nbsp;<code>1000</code>&nbsp;之间。</li>
</ol>



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