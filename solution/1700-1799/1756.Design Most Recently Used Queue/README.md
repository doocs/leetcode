# [1756. 设计最近使用（MRU）队列](https://leetcode-cn.com/problems/design-most-recently-used-queue)

[English Version](/solution/1700-1799/1756.Design%20Most%20Recently%20Used%20Queue/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一种类似队列的数据结构，该数据结构将最近使用的元素移到队列尾部。</p>

<p>实现 <code>MRUQueue</code> 类：</p>

<ul>
	<li><code>MRUQueue(int n)</code>  使用 <code>n</code> 个元素： <code>[1,2,3,...,n]</code> 构造 <code>MRUQueue</code> 。</li>
	<li><code>fetch(int k)</code> 将第 <code>k</code> 个元素<strong>（从 1 开始索引）</strong>移到队尾，并返回该元素。</li>
</ul>

<p> </p>

<p><b>示例 1：</b></p>

<pre>
<strong>输入：</strong>
["MRUQueue", "fetch", "fetch", "fetch", "fetch"]
[[8], [3], [5], [2], [8]]
<strong>输出：</strong>
[null, 3, 6, 2, 2]

<strong>解释：</strong>
MRUQueue mRUQueue = new MRUQueue(8); // 初始化队列为 [1,2,3,4,5,6,7,8]。
mRUQueue.fetch(3); // 将第 3 个元素 (3) 移到队尾，使队列变为 [1,2,4,5,6,7,8,3] 并返回该元素。
mRUQueue.fetch(5); // 将第 5 个元素 (6) 移到队尾，使队列变为 [1,2,4,5,7,8,3,6] 并返回该元素。
mRUQueue.fetch(2); // 将第 2 个元素 (2) 移到队尾，使队列变为 [1,4,5,7,8,3,6,2] 并返回该元素。
mRUQueue.fetch(8); // 第 8 个元素 (2) 已经在队列尾部了，所以直接返回该元素即可。
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 <= n <= 2000</code></li>
	<li><code>1 <= k <= n</code></li>
	<li>最多调用 <code>2000</code> 次 <code>fetch</code></li>
</ul>

<p> </p>
<b>进阶：</b>找到每次 <code>fetch</code> 的复杂度为 <code>O(n)</code> 的算法比较简单。你可以找到每次 <code>fetch</code> 的复杂度更佳的算法吗？

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
