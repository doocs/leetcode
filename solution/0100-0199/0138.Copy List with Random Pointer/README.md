# [138. 复制带随机指针的链表](https://leetcode-cn.com/problems/copy-list-with-random-pointer)

[English Version](/solution/0100-0199/0138.Copy%20List%20with%20Random%20Pointer/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。</p>

<p>要求返回这个链表的&nbsp;<strong><a href="https://baike.baidu.com/item/深拷贝/22785317?fr=aladdin" target="_blank">深拷贝</a></strong>。&nbsp;</p>

<p>我们用一个由&nbsp;<code>n</code>&nbsp;个节点组成的链表来表示输入/输出中的链表。每个节点用一个&nbsp;<code>[val, random_index]</code>&nbsp;表示：</p>

<ul>
	<li><code>val</code>：一个表示&nbsp;<code>Node.val</code>&nbsp;的整数。</li>
	<li><code>random_index</code>：随机指针指向的节点索引（范围从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n-1</code>）；如果不指向任何节点，则为&nbsp;&nbsp;<code>null</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e1.png" style="height: 138px; width: 680px;"></p>

<pre><strong>输入：</strong>head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
<strong>输出：</strong>[[7,null],[13,0],[11,4],[10,2],[1,0]]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e2.png" style="height: 111px; width: 680px;"></p>

<pre><strong>输入：</strong>head = [[1,1],[2,1]]
<strong>输出：</strong>[[1,1],[2,1]]
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e3.png" style="height: 119px; width: 680px;"></strong></p>

<pre><strong>输入：</strong>head = [[3,null],[3,0],[3,null]]
<strong>输出：</strong>[[3,null],[3,0],[3,null]]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>head = []
<strong>输出：</strong>[]
<strong>解释：</strong>给定的链表为空（空指针），因此返回 null。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-10000 &lt;= Node.val &lt;= 10000</code></li>
	<li><code>Node.random</code>&nbsp;为空（null）或指向链表中的节点。</li>
	<li>节点数目不超过 1000 。</li>
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