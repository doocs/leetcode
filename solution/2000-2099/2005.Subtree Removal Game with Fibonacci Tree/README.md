# [2005. 斐波那契树的移除子树游戏](https://leetcode.cn/problems/subtree-removal-game-with-fibonacci-tree)

[English Version](/solution/2000-2099/2005.Subtree%20Removal%20Game%20with%20Fibonacci%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>斐波那契</strong>树是一种按这种规则函数&nbsp;<code>order(n)</code>&nbsp;创建的二叉树：</p>

<ul>
	<li><code>order(0)</code> 是空树。</li>
	<li><code>order(1)</code>&nbsp;是一棵<strong>只有一个节点</strong>的二叉树。</li>
	<li><code>order(n)</code>&nbsp;是一棵根节点的左子树为&nbsp;<code>order(n - 2)</code>&nbsp;、右子树为&nbsp;<code>order(n - 1)</code>&nbsp;的二叉树。</li>
</ul>

<p>Alice 和&nbsp;Bob 在玩一种关于<strong>斐波那契</strong>树的游戏，由 Alice 先手。在每个回合中，每个玩家选择一个节点，然后移除该节点<strong>及</strong>其子树。只能删除根节点&nbsp;<code>root</code>&nbsp;的玩家输掉这场游戏。</p>

<p>给定一个整数&nbsp;<code>n</code>，假定两名玩家都按最优策略进行游戏，若 Alice 赢得这场游戏，返回&nbsp;<code>true</code>&nbsp;。若 Bob 赢得这场游戏，返回&nbsp;<code>false</code>&nbsp;。</p>

<p>一棵二叉树的子树&nbsp;<code>tree</code> 是由&nbsp;<code>tree</code>&nbsp;中某个节点及其所有后代节点组成的树。树&nbsp;<code>tree</code>&nbsp;也可当作自身的子树。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2005.Subtree%20Removal%20Game%20with%20Fibonacci%20Tree/images/image-20210914173520-3.png" style="width: 200px; height: 184px;" /></p>

<pre>
<strong>输入:</strong> n = 3
<strong>输出:</strong> true
<strong>解释:</strong>
Alice 移除右子树中的节点 1。
Bob 要么移除左子树中的 1，要么移除右子树中的 2。
Alice 可以移除 Bob 未移除的任意节点。
Bob 只能删除根节点 3，所以 Bob 输了。
返回 true，因为 Alice 赢了。
</pre>

<p><strong>示例 2:</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2005.Subtree%20Removal%20Game%20with%20Fibonacci%20Tree/images/image-20210914173634-4.png" style="width: 75px; height: 75px;" /></p>

<pre>
<strong>输入:</strong> n = 1
<strong>输出:</strong> false
<strong>解释:</strong>
Alice 只能移除根节点 1, 所以 Alice 输了。
返回 false，因为 Alice 输了。
</pre>

<p><strong>示例 3:</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2005.Subtree%20Removal%20Game%20with%20Fibonacci%20Tree/images/image-20210914173425-1.png" style="width: 100px; height: 106px;" /></p>

<pre>
<strong>输入:</strong> n = 2
<strong>输出:</strong> true
<strong>解释:</strong>
Alice 删除节点 1.
Bob 只能删除根节点 2，所以 Bob 输了。
返回 true，因为 Alice 赢了。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
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
