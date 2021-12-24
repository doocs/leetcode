# [1430. 判断给定的序列是否是二叉树从根到叶的路径](https://leetcode-cn.com/problems/check-if-a-string-is-a-valid-sequence-from-root-to-leaves-path-in-a-binary-tree)

[English Version](/solution/1400-1499/1430.Check%20If%20a%20String%20Is%20a%20Valid%20Sequence%20from%20Root%20to%20Leaves%20Path%20in%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，我们称从根节点到任意叶节点的任意路径中的节点值所构成的序列为该二叉树的一个 &ldquo;<strong>有效序列</strong>&rdquo; 。检查一个给定的序列是否是给定二叉树的一个 &ldquo;<strong>有效序列</strong>&rdquo; 。</p>

<p>我们以整数数组 <code>arr</code> 的形式给出这个序列。从根节点到任意叶节点的任意路径中的节点值所构成的序列都是这个二叉树的 &ldquo;<strong>有效序列</strong>&rdquo; 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1430.Check%20If%20a%20String%20Is%20a%20Valid%20Sequence%20from%20Root%20to%20Leaves%20Path%20in%20a%20Binary%20Tree/images/leetcode_testcase_1.png" style="height:250px; width:333px" /></strong></p>

<pre>
<strong>输入：</strong>root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,0,1]
<strong>输出：</strong>true
<strong>解释：
</strong>路径 0 -&gt; 1 -&gt; 0 -&gt; 1 是一个&ldquo;有效序列&rdquo;（图中的绿色节点）。
其他的&ldquo;有效序列&rdquo;是：
0 -&gt; 1 -&gt; 1 -&gt; 0 
0 -&gt; 0 -&gt; 0
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1430.Check%20If%20a%20String%20Is%20a%20Valid%20Sequence%20from%20Root%20to%20Leaves%20Path%20in%20a%20Binary%20Tree/images/leetcode_testcase_2.png" style="height:250px; width:333px" /></strong></p>

<pre>
<strong>输入：</strong>root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,0,1]
<strong>输出：</strong>false 
<strong>解释：</strong>路径 0 -&gt; 0 -&gt; 1 不存在，所以这不是一个&ldquo;序列&rdquo;。
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1430.Check%20If%20a%20String%20Is%20a%20Valid%20Sequence%20from%20Root%20to%20Leaves%20Path%20in%20a%20Binary%20Tree/images/leetcode_testcase_3.png" style="height:250px; width:333px" /></strong></p>

<pre>
<strong>输入：</strong>root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,1]
<strong>输出：</strong>false
<strong>解释：</strong>路径 0 -&gt; 1 -&gt; 1 是一个序列，但不是一个&ldquo;有效序列&rdquo;（译者注：因为序列的终点不是叶节点）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 5000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 9</code></li>
	<li>每个节点的值的取值范围是 <code>[0 - 9]</code></li>
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
