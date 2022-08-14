# [2313. Minimum Flips in Binary Tree to Get Result](https://leetcode.cn/problems/minimum-flips-in-binary-tree-to-get-result)

[English Version](/solution/2300-2399/2313.Minimum%20Flips%20in%20Binary%20Tree%20to%20Get%20Result/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given the <code>root</code> of a <strong>binary tree</strong> with the following properties:</p>

<ul>
	<li><strong>Leaf nodes</strong> have either the value <code>0</code> or <code>1</code>, representing <code>false</code> and <code>true</code> respectively.</li>
	<li><strong>Non-leaf nodes</strong> have either the value <code>2</code>, <code>3</code>, <code>4</code>, or <code>5</code>, representing the boolean operations <code>OR</code>, <code>AND</code>, <code>XOR</code>, and <code>NOT</code>, respectively.</li>
</ul>

<p>You are also given a boolean <code>result</code>, which is the desired result of the <strong>evaluation</strong> of the <code>root</code> node.</p>

<p>The evaluation of a node is as follows:</p>

<ul>
	<li>If the node is a leaf node, the evaluation is the <strong>value</strong> of the node, i.e. <code>true</code> or <code>false</code>.</li>
	<li>Otherwise, <strong>evaluate</strong> the node&#39;s children and <strong>apply</strong> the boolean operation of its value with the children&#39;s evaluations.</li>
</ul>

<p>In one operation, you can <strong>flip</strong> a leaf node, which causes a <code>false</code> node to become <code>true</code>, and a <code>true</code> node to become <code>false</code>.</p>

<p>Return<em> the minimum number of operations that need to be performed such that the evaluation of </em><code>root</code><em> yields </em><code>result</code>. It can be shown that there is always a way to achieve <code>result</code>.</p>

<p>A <strong>leaf node</strong> is a node that has zero children.</p>

<p>Note: <code>NOT</code> nodes have either a left child or a right child, but other non-leaf nodes have both a left child and a right child.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2313.Minimum%20Flips%20in%20Binary%20Tree%20to%20Get%20Result/images/operationstree.png" style="width: 500px; height: 179px;" />
<pre>
<strong>Input:</strong> root = [3,5,4,2,null,1,1,1,0], result = true
<strong>Output:</strong> 2
<strong>Explanation:</strong>
It can be shown that a minimum of 2 nodes have to be flipped to make the root of the tree
evaluate to true. One way to achieve this is shown in the diagram above.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [0], result = false
<strong>Output:</strong> 0
<strong>Explanation:</strong>
The root of the tree already evaluates to false, so 0 nodes have to be flipped.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 5</code></li>
	<li><code>OR</code>, <code>AND</code>, and <code>XOR</code> nodes have <code>2</code> children.</li>
	<li><code>NOT</code> nodes have <code>1</code> child.</li>
	<li>Leaf nodes have a value of <code>0</code> or <code>1</code>.</li>
	<li>Non-leaf nodes have a value of <code>2</code>, <code>3</code>, <code>4</code>, or <code>5</code>.</li>
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
