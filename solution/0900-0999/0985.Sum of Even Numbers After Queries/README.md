# [985. 查询后的偶数和](https://leetcode-cn.com/problems/sum-of-even-numbers-after-queries)

[English Version](/solution/0900-0999/0985.Sum%20of%20Even%20Numbers%20After%20Queries/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给出一个整数数组&nbsp;<code>A</code>&nbsp;和一个查询数组&nbsp;<code>queries</code>。</p>

<p>对于第&nbsp;<code>i</code>&nbsp;次查询，有&nbsp;<code>val =&nbsp;queries[i][0], index&nbsp;= queries[i][1]</code>，我们会把&nbsp;<code>val</code>&nbsp;加到&nbsp;<code>A[index]</code>&nbsp;上。然后，第&nbsp;<code>i</code>&nbsp;次查询的答案是 <code>A</code> 中偶数值的和。</p>

<p><em>（此处给定的&nbsp;<code>index = queries[i][1]</code>&nbsp;是从 0 开始的索引，每次查询都会永久修改数组&nbsp;<code>A</code>。）</em></p>

<p>返回所有查询的答案。你的答案应当以数组&nbsp;<code>answer</code>&nbsp;给出，<code>answer[i]</code>&nbsp;为第&nbsp;<code>i</code>&nbsp;次查询的答案。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
<strong>输出：</strong>[8,6,2,4]
<strong>解释：</strong>
开始时，数组为 [1,2,3,4]。
将 1 加到 A[0] 上之后，数组为 [2,2,3,4]，偶数值之和为 2 + 2 + 4 = 8。
将 -3 加到 A[1] 上之后，数组为 [2,-1,3,4]，偶数值之和为 2 + 4 = 6。
将 -4 加到 A[0] 上之后，数组为 [-2,-1,3,4]，偶数值之和为 -2 + 4 = 2。
将 2 加到 A[3] 上之后，数组为 [-2,-1,3,6]，偶数值之和为 -2 + 6 = 4。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 10000</code></li>
	<li><code>-10000 &lt;= A[i] &lt;= 10000</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10000</code></li>
	<li><code>-10000 &lt;= queries[i][0] &lt;= 10000</code></li>
	<li><code>0 &lt;= queries[i][1] &lt; A.length</code></li>
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