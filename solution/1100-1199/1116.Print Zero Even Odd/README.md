# [1116. 打印零与奇偶数](https://leetcode.cn/problems/print-zero-even-odd)

[English Version](/solution/1100-1199/1116.Print%20Zero%20Even%20Odd/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现有函数 <code>printNumber</code> 可以用一个整数参数调用，并输出该整数到控制台。</p>

<ul>
	<li>例如，调用 <code>printNumber(7)</code> 将会输出 <code>7</code> 到控制台。</li>
</ul>

<p>给你类 <code>ZeroEvenOdd</code> 的一个实例，该类中有三个函数：<code>zero</code>、<code>even</code> 和 <code>odd</code> 。<code>ZeroEvenOdd</code> 的相同实例将会传递给三个不同线程：</p>

<ul>
	<li><strong>线程 A：</strong>调用 <code>zero()</code> ，只输出 <code>0</code></li>
	<li><strong>线程 B：</strong>调用 <code>even()</code> ，只输出偶数</li>
	<li><strong>线程 C：</strong>调用 <code>odd()</code> ，只输出奇数</li>
</ul>

<p>修改给出的类，以输出序列 <code>"010203040506..."</code> ，其中序列的长度必须为 <code>2n</code> 。</p>

<p>实现 <code>ZeroEvenOdd</code> 类：</p>

<ul>
	<li><code>ZeroEvenOdd(int n)</code> 用数字 <code>n</code> 初始化对象，表示需要输出的数。</li>
	<li><code>void zero(printNumber)</code> 调用 <code>printNumber</code> 以输出一个 0 。</li>
	<li><code>void even(printNumber)</code> 调用<code>printNumber</code> 以输出偶数。</li>
	<li><code>void odd(printNumber)</code> 调用 <code>printNumber</code> 以输出奇数。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>"0102"
<strong>解释：</strong>三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>"0102030405"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
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
