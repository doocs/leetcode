# [156. 上下翻转二叉树](https://leetcode-cn.com/problems/binary-tree-upside-down)

[English Version](/solution/0100-0199/0156.Binary%20Tree%20Upside%20Down/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定一个二叉树，其中所有的右节点要么是具有兄弟节点（拥有相同父节点的左节点）的叶节点，要么为空，将此二叉树上下翻转并将它变成一棵树， 原来的右节点将转换成左叶节点。返回新的根。</p>

<p><strong>例子:</strong></p>

<pre><strong>输入: </strong>[1,2,3,4,5]

    1
   / \
  2   3
 / \
4   5

<strong>输出:</strong> 返回二叉树的根 [4,5,2,#,#,3,1]

   4
  / \
 5   2
    / \
   3   1  
</pre>

<p><strong>说明:</strong></p>

<p>对 <code>[4,5,2,#,#,3,1]</code> 感到困惑? 下面详细介绍请查看&nbsp;<a href="https://support.leetcode-cn.com/hc/kb/article/1194353/" target="_blank">二叉树是如何被序列化的</a>。</p>

<p>二叉树的序列化遵循层次遍历规则，当没有节点存在时，&#39;#&#39; 表示路径终止符。</p>

<p>这里有一个例子:</p>

<pre>   1
  / \
 2   3
    /
   4
    \
     5
</pre>

<p>上面的二叉树则被序列化为 <code>[1,2,3,#,#,4,#,#,5]</code>.</p>



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