# [955. 删列造序 II](https://leetcode-cn.com/problems/delete-columns-to-make-sorted-ii)

[English Version](/solution/0900-0999/0955.Delete%20Columns%20to%20Make%20Sorted%20II/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定由&nbsp;<code>N</code>&nbsp;个小写字母字符串组成的数组&nbsp;<code>A</code>，其中每个字符串长度相等。</p>

<p>选取一个删除索引序列，对于&nbsp;<code>A</code>&nbsp;中的每个字符串，删除对应每个索引处的字符。</p>

<p>比如，有&nbsp;<code>A = [&quot;abcdef&quot;, &quot;uvwxyz&quot;]</code>，删除索引序列&nbsp;<code>{0, 2, 3}</code>，删除后&nbsp;<code>A</code>&nbsp;为<code>[&quot;bef&quot;, &quot;vyz&quot;]</code>。</p>

<p>假设，我们选择了一组删除索引&nbsp;<code>D</code>，那么在执行删除操作之后，最终得到的数组的元素是按 <strong>字典序</strong>（<code>A[0] &lt;= A[1] &lt;= A[2] ... &lt;= A[A.length - 1]</code>）排列的，然后请你返回&nbsp;<code>D.length</code>&nbsp;的最小可能值。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[&quot;ca&quot;,&quot;bb&quot;,&quot;ac&quot;]
<strong>输出：</strong>1
<strong>解释： </strong>
删除第一列后，A = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]。
现在 A 中元素是按字典排列的 (即，A[0] &lt;= A[1] &lt;= A[2])。
我们至少需要进行 1 次删除，因为最初 A 不是按字典序排列的，所以答案是 1。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[&quot;xc&quot;,&quot;yb&quot;,&quot;za&quot;]
<strong>输出：</strong>0
<strong>解释：</strong>
A 的列已经是按字典序排列了，所以我们不需要删除任何东西。
注意 A 的行不需要按字典序排列。
也就是说，A[0][0] &lt;= A[0][1] &lt;= ... 不一定成立。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>[&quot;zyx&quot;,&quot;wvu&quot;,&quot;tsr&quot;]
<strong>输出：</strong>3
<strong>解释：</strong>
我们必须删掉每一列。
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