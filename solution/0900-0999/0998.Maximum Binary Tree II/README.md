# [998. 最大二叉树 II](https://leetcode-cn.com/problems/maximum-binary-tree-ii)

[English Version](/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>最大树定义：一个树，其中每个节点的值都大于其子树中的任何其他值。</p>

<p>给出最大树的根节点 <code>root</code>。</p>

<p>就像<a href="https://leetcode-cn.com/problems/maximum-binary-tree/">之前的问题</a>那样，给定的树是从列表 <code>A</code>（<code>root = Construct(A)</code>）递归地使用下述 <code>Construct(A)</code> 例程构造的：</p>

<ul>
	<li>如果 <code>A</code> 为空，返回 <code>null</code></li>
	<li>否则，令 <code>A[i]</code> 作为 A 的最大元素。创建一个值为 <code>A[i]</code> 的根节点 <code>root</code></li>
	<li><code>root</code> 的左子树将被构建为 <code>Construct([A[0], A[1], ..., A[i-1]])</code></li>
	<li><code>root</code> 的右子树将被构建为 <code>Construct([A[i+1], A[i+2], ..., A[A.length - 1]])</code></li>
	<li>返回 <code>root</code></li>
</ul>

<p>请注意，我们没有直接给定 A，只有一个根节点 <code>root = Construct(A)</code>.</p>

<p>假设 <code>B</code> 是 <code>A</code> 的副本，并在末尾附加值 <code>val</code>。题目数据保证 <code>B</code> 中的值是不同的。</p>

<p>返回 <code>Construct(B)</code>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/maximum-binary-tree-1-1.png" style="height: 160px; width: 159px;" /><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/maximum-binary-tree-1-2.png" style="height: 160px; width: 169px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [4,1,3,null,null,2], val = 5
<strong>输出：</strong>[5,4,null,1,3,null,null,2]
<strong>解释：</strong>A = [1,4,2,3], B = [1,4,2,3,5]
</pre>

<p><strong>示例 2：<br />
<img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/maximum-binary-tree-2-1.png" style="height: 160px; width: 180px;" /><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/maximum-binary-tree-2-2.png" style="height: 160px; width: 214px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [5,2,4,null,1], val = 3
<strong>输出：</strong>[5,2,4,null,1,null,3]
<strong>解释：</strong>A = [2,1,5,4], B = [2,1,5,4,3]
</pre>

<p><strong>示例 3：<br />
<img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/maximum-binary-tree-3-1.png" style="height: 160px; width: 180px;" /><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/maximum-binary-tree-3-2.png" style="height: 160px; width: 201px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [5,2,3,null,1], val = 4
<strong>输出：</strong>[5,2,4,null,1,3]
<strong>解释：</strong>A = [2,1,5,3], B = [2,1,5,3,4]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= B.length <= 100</code></li>
</ul>

<p> </p>

<p> </p>


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
