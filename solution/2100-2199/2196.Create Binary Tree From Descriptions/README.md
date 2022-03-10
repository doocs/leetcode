# [2196. 根据描述创建二叉树](https://leetcode-cn.com/problems/create-binary-tree-from-descriptions)

[English Version](/solution/2100-2199/2196.Create%20Binary%20Tree%20From%20Descriptions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组 <code>descriptions</code> ，其中 <code>descriptions[i] = [parent<sub>i</sub>, child<sub>i</sub>, isLeft<sub>i</sub>]</code> 表示 <code>parent<sub>i</sub></code> 是 <code>child<sub>i</sub></code> 在 <strong>二叉树</strong> 中的 <strong>父节点</strong>，二叉树中各节点的值 <strong>互不相同</strong> 。此外：</p>

<ul>
	<li>如果 <code>isLeft<sub>i</sub> == 1</code> ，那么 <code>child<sub>i</sub></code> 就是 <code>parent<sub>i</sub></code> 的左子节点。</li>
	<li>如果 <code>isLeft<sub>i</sub> == 0</code> ，那么 <code>child<sub>i</sub></code> 就是 <code>parent<sub>i</sub></code> 的右子节点。</li>
</ul>

<p>请你根据 <code>descriptions</code> 的描述来构造二叉树并返回其 <strong>根节点</strong> 。</p>

<p>测试用例会保证可以构造出 <strong>有效</strong> 的二叉树。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2196.Create%20Binary%20Tree%20From%20Descriptions/images/example1drawio.png" style="width: 300px; height: 236px;" /></p>

<pre>
<strong>输入：</strong>descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
<strong>输出：</strong>[50,20,80,15,17,19]
<strong>解释：</strong>根节点是值为 50 的节点，因为它没有父节点。
结果二叉树如上图所示。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2196.Create%20Binary%20Tree%20From%20Descriptions/images/example2drawio.png" style="width: 131px; height: 300px;" /></p>

<pre>
<strong>输入：</strong>descriptions = [[1,2,1],[2,3,0],[3,4,1]]
<strong>输出：</strong>[1,2,null,null,3,4]
<strong>解释：</strong>根节点是值为 1 的节点，因为它没有父节点。 
结果二叉树如上图所示。 </pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= descriptions.length &lt;= 10<sup>4</sup></code></li>
	<li><code>descriptions[i].length == 3</code></li>
	<li><code>1 &lt;= parent<sub>i</sub>, child<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= isLeft<sub>i</sub> &lt;= 1</code></li>
	<li><code>descriptions</code> 所描述的二叉树是一棵有效二叉树</li>
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

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
