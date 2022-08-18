# [1966. 未排序数组中的可被二分搜索的数](https://leetcode.cn/problems/binary-searchable-numbers-in-an-unsorted-array)

[English Version](/solution/1900-1999/1966.Binary%20Searchable%20Numbers%20in%20an%20Unsorted%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个 <strong>类似</strong> <a href="https://leetcode.com/explore/learn/card/binary-search/" target="_blank">二分搜索</a>的方法。 这个方法有两个入参: <code>sequence</code> 是一个整数数组， <code>target</code> 是一个整数。 这个方法可以判断 <code>target</code> 是否存在 <code>sequence</code>中。</p>

<p>该方法的伪代码如下：</p>

<pre>
func(sequence, target)
  while sequence is not empty
    <strong>randomly</strong> choose an element from sequence as the pivot
    if pivot = target, return <strong>true</strong>
    else if pivot &lt; target, remove pivot and all elements to its left from the sequence
    else, remove pivot and all elements to its right from the sequence
  end while
  return <strong>false</strong></pre>

<p>当 <code>sequence</code> 是排好序时, 这个方法对 <strong>所有</strong> 值都可正常判断。如果&nbsp;<code>sequence</code>&nbsp;不是排好序的, 该方法并不是对所有值都可正常判断, 但对<strong>一些</strong> 值仍可正常判断。</p>

<p>给定一个仅包含<strong>不同</strong>数字的数组 <code>nums</code>表示 <code>sequence</code>， nums<strong>是否排序未知</strong>，对于 <strong>所有可能</strong>的选择, 返回通过这个方法<b>保证</b>能找到的值的数量。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> nums = [7]
<strong>输出:</strong> 1
<strong>解释</strong>: 
7 保证能被找到.
因为数组中只有一个数字, 7 一定会被选中. 因为选中的值等于target, 这个方法会返回 true.
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> nums = [-1,5,2]
<strong>输出:</strong> 1
<strong>解释</strong>: 
只有 -1 保证能被找到.
如果 -1 被选中, 这个方法就会返回 true.
如果 5 被选中, 5 和 2 会被移除。 在下一次循环时, 这个序列只有一个元素： -1 ，这个方法就会返回 true.
如果 2 被选中, 2 将会被移除。 在下次循环时, 这个序列里将会有 -1 和 5. 无论哪个数字被选中, 这个方法都会找到 -1 且返回 true.

5 不能保证被找到。
如果 2 被选中, -1, 5 和 2 将会被移除。 这个序列将会被清空且这个方法会返回 false。

2 不能保证被找到.
如果 5 被选中, 5 和 2 将会被移除。在下次循环时, 这个序列只会有一个元素： -1 且这个方法会返回 false。

因为只有-1 是保证能被找到的, 你应该返回 1.
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums</code>&nbsp;中所有值都&nbsp;<b>不同</b>.</li>
</ul>

<p>&nbsp;</p>

<p><strong>提升:</strong>&nbsp;如果&nbsp;<code>nums</code> 存在&nbsp;<strong>重复的值</strong>, 你会如何修改你的算法吗?&nbsp;</p>

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
