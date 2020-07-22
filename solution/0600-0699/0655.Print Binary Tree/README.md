# [655. 输出二叉树](https://leetcode-cn.com/problems/print-binary-tree)

[English Version](/solution/0600-0699/0655.Print%20Binary%20Tree/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>在一个 m*n 的二维字符串数组中输出二叉树，并遵守以下规则：</p>

<ol>
	<li>行数&nbsp;<code>m</code>&nbsp;应当等于给定二叉树的高度。</li>
	<li>列数&nbsp;<code>n</code>&nbsp;应当总是奇数。</li>
	<li>根节点的值（以字符串格式给出）应当放在可放置的第一行正中间。根节点所在的行与列会将剩余空间划分为两部分（<strong>左下部分和右下部分</strong>）。你应该将左子树输出在左下部分，右子树输出在右下部分。左下和右下部分应当有相同的大小。即使一个子树为空而另一个非空，你不需要为空的子树输出任何东西，但仍需要为另一个子树留出足够的空间。然而，如果两个子树都为空则不需要为它们留出任何空间。</li>
	<li>每个未使用的空间应包含一个空的字符串<code>&quot;&quot;</code>。</li>
	<li>使用相同的规则输出子树。</li>
</ol>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong>
     1
    /
   2
<strong>输出:</strong>
[[&quot;&quot;, &quot;1&quot;, &quot;&quot;],
 [&quot;2&quot;, &quot;&quot;, &quot;&quot;]]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong>
     1
    / \
   2   3
    \
     4
<strong>输出:</strong>
[[&quot;&quot;, &quot;&quot;, &quot;&quot;, &quot;1&quot;, &quot;&quot;, &quot;&quot;, &quot;&quot;],
 [&quot;&quot;, &quot;2&quot;, &quot;&quot;, &quot;&quot;, &quot;&quot;, &quot;3&quot;, &quot;&quot;],
 [&quot;&quot;, &quot;&quot;, &quot;4&quot;, &quot;&quot;, &quot;&quot;, &quot;&quot;, &quot;&quot;]]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong>
      1
     / \
    2   5
   / 
  3 
 / 
4 
<strong>输出:</strong>
[[&quot;&quot;,  &quot;&quot;,  &quot;&quot;, &quot;&quot;,  &quot;&quot;, &quot;&quot;, &quot;&quot;, &quot;1&quot;, &quot;&quot;,  &quot;&quot;,  &quot;&quot;,  &quot;&quot;,  &quot;&quot;, &quot;&quot;, &quot;&quot;]
 [&quot;&quot;,  &quot;&quot;,  &quot;&quot;, &quot;2&quot;, &quot;&quot;, &quot;&quot;, &quot;&quot;, &quot;&quot;,  &quot;&quot;,  &quot;&quot;,  &quot;&quot;,  &quot;5&quot;, &quot;&quot;, &quot;&quot;, &quot;&quot;]
 [&quot;&quot;,  &quot;3&quot;, &quot;&quot;, &quot;&quot;,  &quot;&quot;, &quot;&quot;, &quot;&quot;, &quot;&quot;,  &quot;&quot;,  &quot;&quot;,  &quot;&quot;,  &quot;&quot;,  &quot;&quot;, &quot;&quot;, &quot;&quot;]
 [&quot;4&quot;, &quot;&quot;,  &quot;&quot;, &quot;&quot;,  &quot;&quot;, &quot;&quot;, &quot;&quot;, &quot;&quot;,  &quot;&quot;,  &quot;&quot;,  &quot;&quot;,  &quot;&quot;,  &quot;&quot;, &quot;&quot;, &quot;&quot;]]
</pre>

<p><strong>注意:</strong> 二叉树的高度在范围 [1, 10] 中。</p>



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