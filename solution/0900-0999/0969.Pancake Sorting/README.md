# [969. 煎饼排序](https://leetcode-cn.com/problems/pancake-sorting)

[English Version](/solution/0900-0999/0969.Pancake%20Sorting/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定数组&nbsp;<code>A</code>，我们可以对其进行<em>煎饼翻转</em>：我们选择一些正整数&nbsp;<code><strong>k</strong>&nbsp;&lt;= A.length</code>，然后反转 <code>A</code> 的前 <strong>k</strong>&nbsp;个元素的顺序。我们要执行零次或多次煎饼翻转（按顺序一次接一次地进行）以完成对数组 <code>A</code> 的排序。</p>

<p>返回能使&nbsp;<code>A</code> 排序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在&nbsp;<code>10 * A.length</code> 范围内的有效答案都将被判断为正确。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[3,2,4,1]
<strong>输出：</strong>[4,2,4,3]
<strong>解释：</strong>
我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
初始状态 A = [3, 2, 4, 1]
第一次翻转后 (k=4): A = [1, 4, 2, 3]
第二次翻转后 (k=2): A = [4, 1, 2, 3]
第三次翻转后 (k=4): A = [3, 2, 1, 4]
第四次翻转后 (k=3): A = [1, 2, 3, 4]，此时已完成排序。 
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[1,2,3]
<strong>输出：</strong>[]
<strong>解释：
</strong>输入已经排序，因此不需要翻转任何内容。
请注意，其他可能的答案，如[3，3]，也将被接受。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 100</code></li>
	<li><code>A[i]</code> 是&nbsp;<code>[1, 2, ..., A.length]</code>&nbsp;的排列</li>
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
