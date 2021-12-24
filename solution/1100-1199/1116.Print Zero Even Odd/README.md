# [1116. 打印零与奇偶数](https://leetcode-cn.com/problems/print-zero-even-odd)

[English Version](/solution/1100-1199/1116.Print%20Zero%20Even%20Odd/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设有这么一个类：</p>

<pre>class ZeroEvenOdd {
&nbsp; public ZeroEvenOdd(int n) { ... }&nbsp;     // 构造函数
  public void zero(printNumber) { ... }  // 仅打印出 0
  public void even(printNumber) { ... }  // 仅打印出 偶数
  public void odd(printNumber) { ... }   // 仅打印出 奇数
}
</pre>

<p>相同的一个&nbsp;<code>ZeroEvenOdd</code>&nbsp;类实例将会传递给三个不同的线程：</p>

<ol>
	<li>线程 A 将调用&nbsp;<code>zero()</code>，它只输出 0 。</li>
	<li>线程 B 将调用&nbsp;<code>even()</code>，它只输出偶数。</li>
	<li>线程 C 将调用&nbsp;<code>odd()</code>，它只输出奇数。</li>
</ol>

<p>每个线程都有一个&nbsp;<code>printNumber</code> 方法来输出一个整数。请修改给出的代码以输出整数序列&nbsp;<code>010203040506</code>... ，其中序列的长度必须为 2<em>n</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 2
<strong>输出：</strong>&quot;0102&quot;
<strong>说明：</strong>三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 &quot;0102&quot;。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 5
<strong>输出：</strong>&quot;0102030405&quot;
</pre>

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
