---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0431.Encode%20N-ary%20Tree%20to%20Binary%20Tree/README.md
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 设计
    - 二叉树
---

<!-- problem:start -->

# [431. 将 N 叉树编码为二叉树 🔒](https://leetcode.cn/problems/encode-n-ary-tree-to-binary-tree)

[English Version](/solution/0400-0499/0431.Encode%20N-ary%20Tree%20to%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>设计一个算法，可以将 N 叉树编码为二叉树，并能将该二叉树解码为原 N 叉树。一个 N 叉树是指每个节点都有不超过 N 个孩子节点的有根树。类似地，一个二叉树是指每个节点都有不超过 2 个孩子节点的有根树。你的编码 / 解码的算法的实现没有限制，你只需要保证一个 N 叉树可以编码为二叉树且该二叉树可以解码回原始 N 叉树即可。</p>

<p>例如，你可以将下面的 <code>3-叉</code> 树以该种方式编码：</p>

<p>&nbsp;</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0431.Encode%20N-ary%20Tree%20to%20Binary%20Tree/images/narytreebinarytreeexample.png" style="width: 500px;" /></p>

<p>&nbsp;</p>

<pre>
<strong>输入：</strong>root = [1,null,3,2,4,null,5,6]
</pre>

<p>注意，上面的方法仅仅是一个例子，可能可行也可能不可行。你没有必要遵循这种形式转化，你可以自己创造和实现不同的方法。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>root = [1,null,3,2,4,null,5,6]
<strong>输出：</strong>[1,null,3,2,4,null,5,6]
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>输出：</strong>[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>N</code>&nbsp;的范围在 <code>[1, 10<sup>4</sup>]</code></li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li>N 叉树的高度小于等于&nbsp;<code>1000</code>。</li>
	<li>不要使用类成员 / 全局变量 / 静态变量来存储状态。你的编码和解码算法应是无状态的。</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
