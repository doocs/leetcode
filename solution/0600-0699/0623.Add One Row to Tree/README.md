# [623. 在二叉树中增加一行](https://leetcode-cn.com/problems/add-one-row-to-tree)

[English Version](/solution/0600-0699/0623.Add%20One%20Row%20to%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，根节点为第1层，深度为 1。在其第&nbsp;<code>d</code>&nbsp;层追加一行值为&nbsp;<code>v</code>&nbsp;的节点。</p>

<p>添加规则：给定一个深度值 <code>d</code> （正整数），针对深度为 <code>d-1</code> 层的每一<strong>非空</strong>节点 <code>N</code>，为 <code>N</code> 创建两个值为&nbsp;<code>v</code>&nbsp;的左子树和右子树。</p>

<p>将&nbsp;<code>N</code> 原先的左子树，连接为新节点&nbsp;<code>v</code> 的左子树；将&nbsp;<code>N</code> 原先的右子树，连接为新节点&nbsp;<code>v</code> 的右子树。</p>

<p>如果 <code>d</code> 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 <code>v</code>，原先的整棵树将作为 <code>v</code> 的左子树。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
二叉树如下所示:
       4
     /   \
    2     6
   / \   / 
  3   1 5   

<strong>v = 1</strong>

<strong>d = 2</strong>

<strong>输出:</strong> 
       4
      / \
     1   1
    /     \
   2       6
  / \     / 
 3   1   5   

</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> 
二叉树如下所示:
      4
     /   
    2    
   / \   
  3   1    

<strong>v = 1</strong>

<strong>d = 3</strong>

<strong>输出:</strong> 
      4
     /   
    2
   / \    
  1   1
 /     \  
3       1
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li>输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。</li>
	<li>输入的二叉树至少有一个节点。</li>
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
