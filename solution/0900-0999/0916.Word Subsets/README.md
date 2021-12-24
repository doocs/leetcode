# [916. 单词子集](https://leetcode-cn.com/problems/word-subsets)

[English Version](/solution/0900-0999/0916.Word%20Subsets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们给出两个单词数组 <code>A</code>&nbsp;和&nbsp;<code>B</code>。每个单词都是一串小写字母。</p>

<p>现在，如果&nbsp;<code>b</code> 中的每个字母都出现在 <code>a</code> 中，<strong>包括重复出现的字母</strong>，那么称单词 <code>b</code> 是单词 <code>a</code> 的子集。 例如，&ldquo;wrr&rdquo; 是 &ldquo;warrior&rdquo; 的子集，但不是 &ldquo;world&rdquo; 的子集。</p>

<p>如果对 <code>B</code> 中的每一个单词&nbsp;<code>b</code>，<code>b</code> 都是 <code>a</code> 的子集，那么我们称&nbsp;<code>A</code> 中的单词 <code>a</code> 是<em>通用的</em>。</p>

<p>你可以按任意顺序以列表形式返回&nbsp;<code>A</code> 中所有的通用单词。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>A = [&quot;amazon&quot;,&quot;apple&quot;,&quot;facebook&quot;,&quot;google&quot;,&quot;leetcode&quot;], B = [&quot;e&quot;,&quot;o&quot;]
<strong>输出：</strong>[&quot;facebook&quot;,&quot;google&quot;,&quot;leetcode&quot;]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>A = [&quot;amazon&quot;,&quot;apple&quot;,&quot;facebook&quot;,&quot;google&quot;,&quot;leetcode&quot;], B = [&quot;l&quot;,&quot;e&quot;]
<strong>输出：</strong>[&quot;apple&quot;,&quot;google&quot;,&quot;leetcode&quot;]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>A = [&quot;amazon&quot;,&quot;apple&quot;,&quot;facebook&quot;,&quot;google&quot;,&quot;leetcode&quot;], B = [&quot;e&quot;,&quot;oo&quot;]
<strong>输出：</strong>[&quot;facebook&quot;,&quot;google&quot;]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>A = [&quot;amazon&quot;,&quot;apple&quot;,&quot;facebook&quot;,&quot;google&quot;,&quot;leetcode&quot;], B = [&quot;lo&quot;,&quot;eo&quot;]
<strong>输出：</strong>[&quot;google&quot;,&quot;leetcode&quot;]
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>A = [&quot;amazon&quot;,&quot;apple&quot;,&quot;facebook&quot;,&quot;google&quot;,&quot;leetcode&quot;], B = [&quot;ec&quot;,&quot;oc&quot;,&quot;ceo&quot;]
<strong>输出：</strong>[&quot;facebook&quot;,&quot;leetcode&quot;]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= A.length, B.length &lt;= 10000</code></li>
	<li><code>1 &lt;= A[i].length, B[i].length&nbsp;&lt;= 10</code></li>
	<li><code>A[i]</code>&nbsp;和&nbsp;<code>B[i]</code>&nbsp;只由小写字母组成。</li>
	<li><code>A[i]</code>&nbsp;中所有的单词都是独一无二的，也就是说不存在&nbsp;<code>i != j</code>&nbsp;使得&nbsp;<code>A[i] == A[j]</code>。</li>
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
