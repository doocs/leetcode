# [1707. 与数组中元素的最大异或值](https://leetcode.cn/problems/maximum-xor-with-an-element-from-array)

[English Version](/solution/1700-1799/1707.Maximum%20XOR%20With%20an%20Element%20From%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由非负整数组成的数组 <code>nums</code> 。另有一个查询数组 <code>queries</code> ，其中 <code>queries[i] = [x<sub>i</sub>, m<sub>i</sub>]</code> 。</p>

<p>第 <code>i</code> 个查询的答案是 <code>x<sub>i</sub></code> 和任何 <code>nums</code> 数组中不超过 <code>m<sub>i</sub></code> 的元素按位异或（<code>XOR</code>）得到的最大值。换句话说，答案是 <code>max(nums[j] XOR x<sub>i</sub>)</code> ，其中所有 <code>j</code> 均满足 <code>nums[j] &lt;= m<sub>i</sub></code> 。如果 <code>nums</code> 中的所有元素都大于 <code>m<sub>i</sub></code>，最终答案就是 <code>-1</code> 。</p>

<p>返回一个整数数组<em> </em><code>answer</code><em> </em>作为查询的答案，其中<em> </em><code>answer.length == queries.length</code><em> </em>且<em> </em><code>answer[i]</code><em> </em>是第<em> </em><code>i</code><em> </em>个查询的答案。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
<strong>输出：</strong>[3,3,7]
<strong>解释：</strong>
1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
2) 1 XOR 2 = 3.
3) 5 XOR 2 = 7.
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
<strong>输出：</strong>[15,-1,5]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= nums[j], x<sub>i</sub>, m<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
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
