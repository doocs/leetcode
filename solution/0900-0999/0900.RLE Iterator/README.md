# [900. RLE 迭代器](https://leetcode-cn.com/problems/rle-iterator)

[English Version](/solution/0900-0999/0900.RLE%20Iterator/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个遍历游程编码序列的迭代器。</p>

<p>迭代器由 <code>RLEIterator(int[] A)</code> 初始化，其中&nbsp;<code>A</code>&nbsp;是某个序列的游程编码。更具体地，对于所有偶数 <code>i</code>，<code>A[i]</code> 告诉我们在序列中重复非负整数值 <code>A[i + 1]</code> 的次数。</p>

<p>迭代器支持一个函数：<code>next(int n)</code>，它耗尽接下来的&nbsp; <code>n</code> 个元素（<code>n &gt;= 1</code>）并返回以这种方式耗去的最后一个元素。如果没有剩余的元素可供耗尽，则&nbsp; <code>next</code>&nbsp;返回&nbsp;<code>-1</code> 。</p>

<p>例如，我们以&nbsp;<code>A = [3,8,0,9,2,5]</code>&nbsp;开始，这是序列&nbsp;<code>[8,8,8,5,5]</code>&nbsp;的游程编码。这是因为该序列可以读作 &ldquo;三个八，零个九，两个五&rdquo;。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>[&quot;RLEIterator&quot;,&quot;next&quot;,&quot;next&quot;,&quot;next&quot;,&quot;next&quot;], [[[3,8,0,9,2,5]],[2],[1],[1],[2]]
<strong>输出：</strong>[null,8,8,5,-1]
<strong>解释：</strong>
RLEIterator 由 RLEIterator([3,8,0,9,2,5]) 初始化。
这映射到序列 [8,8,8,5,5]。
然后调用 RLEIterator.next 4次。

.next(2) 耗去序列的 2 个项，返回 8。现在剩下的序列是 [8, 5, 5]。

.next(1) 耗去序列的 1 个项，返回 8。现在剩下的序列是 [5, 5]。

.next(1) 耗去序列的 1 个项，返回 5。现在剩下的序列是 [5]。

.next(2) 耗去序列的 2 个项，返回 -1。 这是由于第一个被耗去的项是 5，
但第二个项并不存在。由于最后一个要耗去的项不存在，我们返回 -1。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>0 &lt;= A.length &lt;= 1000</code></li>
	<li><code>A.length</code>&nbsp;是偶数。</li>
	<li><code>0 &lt;= A[i] &lt;= 10^9</code></li>
	<li>每个测试用例最多调用&nbsp;<code>1000</code>&nbsp;次&nbsp;<code>RLEIterator.next(int n)</code>。</li>
	<li>每次调用&nbsp;<code>RLEIterator.next(int n)</code>&nbsp;都有&nbsp;<code>1 &lt;= n &lt;= 10^9</code>&nbsp;。</li>
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
