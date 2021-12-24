# [1993. 树上的操作](https://leetcode-cn.com/problems/operations-on-tree)

[English Version](/solution/1900-1999/1993.Operations%20on%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵&nbsp;<code>n</code>&nbsp;个节点的树，编号从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;，以父节点数组&nbsp;<code>parent</code>&nbsp;的形式给出，其中&nbsp;<code>parent[i]</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;个节点的父节点。树的根节点为 <code>0</code>&nbsp;号节点，所以&nbsp;<code>parent[0] = -1</code>&nbsp;，因为它没有父节点。你想要设计一个数据结构实现树里面对节点的加锁，解锁和升级操作。</p>

<p>数据结构需要支持如下函数：</p>

<ul>
	<li><strong>Lock：</strong>指定用户给指定节点 <strong>上锁</strong>&nbsp;，上锁后其他用户将无法给同一节点上锁。只有当节点处于未上锁的状态下，才能进行上锁操作。</li>
	<li><strong>Unlock：</strong>指定用户给指定节点 <strong>解锁</strong>&nbsp;，只有当指定节点当前正被指定用户锁住时，才能执行该解锁操作。</li>
	<li><b>Upgrade：</b>指定用户给指定节点&nbsp;<strong>上锁</strong>&nbsp;，并且将该节点的所有子孙节点&nbsp;<strong>解锁</strong>&nbsp;。只有如下 3 个条件 <strong>全部</strong> 满足时才能执行升级操作：
	<ul>
		<li>指定节点当前状态为未上锁。</li>
		<li>指定节点至少有一个上锁状态的子孙节点（可以是 <strong>任意</strong>&nbsp;用户上锁的）。</li>
		<li>指定节点没有任何上锁的祖先节点。</li>
	</ul>
	</li>
</ul>

<p>请你实现&nbsp;<code>LockingTree</code>&nbsp;类：</p>

<ul>
	<li><code>LockingTree(int[] parent)</code>&nbsp;用父节点数组初始化数据结构。</li>
	<li><code>lock(int num, int user)</code> 如果&nbsp;id 为&nbsp;<code>user</code>&nbsp;的用户可以给节点&nbsp;<code>num</code>&nbsp;上锁，那么返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。如果可以执行此操作，节点&nbsp;<code>num</code>&nbsp;会被 id 为 <code>user</code>&nbsp;的用户 <strong>上锁</strong>&nbsp;。</li>
	<li><code>unlock(int num, int user)</code>&nbsp;如果 id 为 <code>user</code>&nbsp;的用户可以给节点 <code>num</code>&nbsp;解锁，那么返回&nbsp;<code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。如果可以执行此操作，节点 <code>num</code>&nbsp;变为 <strong>未上锁</strong>&nbsp;状态。</li>
	<li><code>upgrade(int num, int user)</code>&nbsp;如果 id 为 <code>user</code>&nbsp;的用户可以给节点 <code>num</code>&nbsp;升级，那么返回&nbsp;<code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。如果可以执行此操作，节点 <code>num</code>&nbsp;会被&nbsp;<strong>升级 </strong>。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1993.Operations%20on%20Tree/images/untitled.png" style="width: 375px; height: 246px;"></p>

<pre><strong>输入：</strong>
["LockingTree", "lock", "unlock", "unlock", "lock", "upgrade", "lock"]
[[[-1, 0, 0, 1, 1, 2, 2]], [2, 2], [2, 3], [2, 2], [4, 5], [0, 1], [0, 1]]
<strong>输出：</strong>
[null, true, false, true, true, true, false]

<strong>解释：</strong>
LockingTree lockingTree = new LockingTree([-1, 0, 0, 1, 1, 2, 2]);
lockingTree.lock(2, 2);    // 返回 true ，因为节点 2 未上锁。
                           // 节点 2 被用户 2 上锁。
lockingTree.unlock(2, 3);  // 返回 false ，因为用户 3 无法解锁被用户 2 上锁的节点。
lockingTree.unlock(2, 2);  // 返回 true ，因为节点 2 之前被用户 2 上锁。
                           // 节点 2 现在变为未上锁状态。
lockingTree.lock(4, 5);    // 返回 true ，因为节点 4 未上锁。
                           // 节点 4 被用户 5 上锁。
lockingTree.upgrade(0, 1); // 返回 true ，因为节点 0 未上锁且至少有一个被上锁的子孙节点（节点 4）。
                           // 节点 0 被用户 1 上锁，节点 4 变为未上锁。
lockingTree.lock(0, 1);    // 返回 false ，因为节点 0 已经被上锁了。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == parent.length</code></li>
	<li><code>2 &lt;= n &lt;= 2000</code></li>
	<li>对于&nbsp;<code>i != 0</code>&nbsp;，满足&nbsp;<code>0 &lt;= parent[i] &lt;= n - 1</code></li>
	<li><code>parent[0] == -1</code></li>
	<li><code>0 &lt;= num &lt;= n - 1</code></li>
	<li><code>1 &lt;= user &lt;= 10<sup>4</sup></code></li>
	<li><code>parent</code>&nbsp;表示一棵合法的树。</li>
	<li><code>lock</code>&nbsp;，<code>unlock</code>&nbsp;和&nbsp;<code>upgrade</code>&nbsp;的调用&nbsp;<strong>总共&nbsp;</strong>不超过&nbsp;<code>2000</code>&nbsp;次。</li>
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
