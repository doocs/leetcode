# [960. 删列造序 III](https://leetcode-cn.com/problems/delete-columns-to-make-sorted-iii)

[English Version](/solution/0900-0999/0960.Delete%20Columns%20to%20Make%20Sorted%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定由&nbsp;<code>N</code>&nbsp;个小写字母字符串组成的数组&nbsp;<code>A</code>，其中每个字符串长度相等。</p>

<p>选取一个删除索引序列，对于&nbsp;<code>A</code>&nbsp;中的每个字符串，删除对应每个索引处的字符。</p>

<p>比如，有&nbsp;<code>A = [&quot;babca&quot;,&quot;bbazb&quot;]</code>，删除索引序列&nbsp;<code>{0, 1, 4}</code>，删除后&nbsp;<code>A</code>&nbsp;为<code>[&quot;bc&quot;,&quot;az&quot;]</code>。</p>

<p>假设，我们选择了一组删除索引&nbsp;<code>D</code>，那么在执行删除操作之后，最终得到的数组的行中的每个元素都是按<strong>字典序</strong>排列的。</p>

<p>清楚起见，<code>A[0]</code>&nbsp;是按字典序排列的（即，<code>A[0][0] &lt;= A[0][1] &lt;= ... &lt;= A[0][A[0].length - 1]</code>），<code>A[1]</code>&nbsp;是按字典序排列的（即，<code>A[1][0] &lt;= A[1][1] &lt;= ... &lt;= A[1][A[1].length - 1]</code>），依此类推。</p>

<p>请你返回&nbsp;<code>D.length</code>&nbsp;的最小可能值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[&quot;babca&quot;,&quot;bbazb&quot;]
<strong>输出：</strong>3
<strong>解释：
</strong>删除 0、1 和 4 这三列后，最终得到的数组是 A = [&quot;bc&quot;, &quot;az&quot;]。
这两行是分别按字典序排列的（即，A[0][0] &lt;= A[0][1] 且 A[1][0] &lt;= A[1][1]）。
注意，A[0] &gt; A[1] &mdash;&mdash; 数组 A 不一定是按字典序排列的。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[&quot;edcba&quot;]
<strong>输出：</strong>4
<strong>解释：</strong>如果删除的列少于 4 列，则剩下的行都不会按字典序排列。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>[&quot;ghi&quot;,&quot;def&quot;,&quot;abc&quot;]
<strong>输出：</strong>0
<strong>解释：</strong>所有行都已按字典序排列。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 100</code></li>
	<li><code>1 &lt;= A[i].length &lt;= 100</code></li>
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
