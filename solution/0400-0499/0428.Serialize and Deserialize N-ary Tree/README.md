# [428. 序列化和反序列化 N 叉树](https://leetcode.cn/problems/serialize-and-deserialize-n-ary-tree)

[English Version](/solution/0400-0499/0428.Serialize%20and%20Deserialize%20N-ary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>序列化是指将一个数据结构转化为位序列的过程，因此可以将其存储在文件中或内存缓冲区中，以便稍后在相同或不同的计算机环境中恢复结构。</p>

<p>设计一个序列化和反序列化 N 叉树的算法。一个 N 叉树是指每个节点都有不超过 N 个孩子节点的有根树。序列化 / 反序列化算法的算法实现没有限制。你只需要保证 N 叉树可以被序列化为一个字符串并且该字符串可以被反序列化成原树结构即可。</p>

<p>例如，你需要序列化下面的 <code>3-叉</code> 树。</p>

<p>&nbsp;</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0428.Serialize%20and%20Deserialize%20N-ary%20Tree/images/narytreeexample.png" style="height: 321px; width: 500px;" /></p>

<p>&nbsp;</p>

<p>为&nbsp;<code>[1 [3[5 6] 2 4]]</code>。你不需要以这种形式完成，你可以自己创造和实现不同的方法。</p>

<p>或者，您可以遵循 LeetCode 的层序遍历序列化格式，其中每组孩子节点由空值分隔。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0428.Serialize%20and%20Deserialize%20N-ary%20Tree/images/sample_4_964.png" style="height: 454px; width: 500px;" /></p>

<p>例如，上面的树可以序列化为 <code>[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]</code></p>

<p>你不一定要遵循以上建议的格式，有很多不同的格式，所以请发挥创造力，想出不同的方法来完成本题。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>输出:</strong> [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> root = [1,null,3,2,4,null,5,6]
<strong>输出:</strong> [1,null,3,2,4,null,5,6]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> root = []
<strong>输出:</strong> []
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目的范围是 <code>[0,&nbsp;10<sup>4</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li>N 叉树的高度小于等于 <code>1000</code></li>
	<li>不要使用类成员 / 全局变量 / 静态变量来存储状态。你的序列化和反序列化算法应是无状态的。</li>
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
