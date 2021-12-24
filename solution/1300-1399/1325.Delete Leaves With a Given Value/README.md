# [1325. 删除给定值的叶子节点](https://leetcode-cn.com/problems/delete-leaves-with-a-given-value)

[English Version](/solution/1300-1399/1325.Delete%20Leaves%20With%20a%20Given%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵以&nbsp;<code>root</code>&nbsp;为根的二叉树和一个整数&nbsp;<code>target</code>&nbsp;，请你删除所有值为&nbsp;<code>target</code> 的&nbsp;<strong>叶子节点</strong> 。</p>

<p>注意，一旦删除值为&nbsp;<code>target</code>&nbsp;的叶子节点，它的父节点就可能变成叶子节点；如果新叶子节点的值恰好也是&nbsp;<code>target</code> ，那么这个节点也应该被删除。</p>

<p>也就是说，你需要重复此过程直到不能继续删除。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1325.Delete%20Leaves%20With%20a%20Given%20Value/images/sample_1_1684.png" style="height: 120px; width: 550px;"></strong></p>

<pre><strong>输入：</strong>root = [1,2,3,2,null,2,4], target = 2
<strong>输出：</strong>[1,null,3,null,4]
<strong>解释：
</strong>上面左边的图中，绿色节点为叶子节点，且它们的值与 target 相同（同为 2 ），它们会被删除，得到中间的图。
有一个新的节点变成了叶子节点且它的值与 target 相同，所以将再次进行删除，从而得到最右边的图。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1325.Delete%20Leaves%20With%20a%20Given%20Value/images/sample_2_1684.png" style="height: 120px; width: 300px;"></strong></p>

<pre><strong>输入：</strong>root = [1,3,3,3,2], target = 3
<strong>输出：</strong>[1,3,null,null,2]
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1325.Delete%20Leaves%20With%20a%20Given%20Value/images/sample_3_1684.png" style="width: 450px;"></strong></p>

<pre><strong>输入：</strong>root = [1,2,null,2,null,2], target = 2
<strong>输出：</strong>[1]
<strong>解释：</strong>每一步都删除一个绿色的叶子节点（值为 2）。</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>root = [1,1,1], target = 1
<strong>输出：</strong>[]
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>root = [1,2,3], target = 1
<strong>输出：</strong>[1,2,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target&nbsp;&lt;= 1000</code></li>
	<li>每一棵树最多有 <code>3000</code> 个节点。</li>
	<li>每一个节点值的范围是&nbsp;<code>[1, 1000]</code>&nbsp;。</li>
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
