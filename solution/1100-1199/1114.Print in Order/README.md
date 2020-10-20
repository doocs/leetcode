# [1114. 按序打印](https://leetcode-cn.com/problems/print-in-order)

[English Version](/solution/1100-1199/1114.Print%20in%20Order/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>我们提供了一个类：</p>

<pre>
public class Foo {
&nbsp; public void one() { print(&quot;one&quot;); }
&nbsp; public void two() { print(&quot;two&quot;); }
&nbsp; public void three() { print(&quot;three&quot;); }
}
</pre>

<p>三个不同的线程将会共用一个&nbsp;<code>Foo</code>&nbsp;实例。</p>

<ul>
	<li>线程 A 将会调用 <code>one()</code> 方法</li>
	<li>线程 B 将会调用&nbsp;<code>two()</code> 方法</li>
	<li>线程 C 将会调用 <code>three()</code> 方法</li>
</ul>

<p>请设计修改程序，以确保 <code>two()</code> 方法在 <code>one()</code> 方法之后被执行，<code>three()</code> 方法在 <code>two()</code> 方法之后被执行。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> [1,2,3]
<strong>输出:</strong> &quot;onetwothree&quot;
<strong>解释:</strong> 
有三个线程会被异步启动。
输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
正确的输出是 &quot;onetwothree&quot;。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> [1,3,2]
<strong>输出:</strong> &quot;onetwothree&quot;
<strong>解释:</strong> 
输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
正确的输出是 &quot;onetwothree&quot;。</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<p>尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。</p>

<p>你看到的输入格式主要是为了确保测试的全面性。</p>

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
