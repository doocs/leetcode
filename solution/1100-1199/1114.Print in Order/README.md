# [1114. 按序打印](https://leetcode-cn.com/problems/print-in-order)

[English Version](/solution/1100-1199/1114.Print%20in%20Order/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们提供了一个类：</p>

<pre>
public class Foo {
  public void first() { print("first"); }
  public void second() { print("second"); }
  public void third() { print("third"); }
}</pre>

<p>三个不同的线程 A、B、C 将会共用一个 <code>Foo</code> 实例。</p>

<ul>
	<li>一个将会调用 <code>first()</code> 方法</li>
	<li>一个将会调用 <code>second()</code> 方法</li>
	<li>还有一个将会调用 <code>third()</code> 方法</li>
</ul>

<p>请设计修改程序，以确保 <code>second()</code> 方法在 <code>first()</code> 方法之后被执行，<code>third()</code> 方法在 <code>second()</code> 方法之后被执行。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> [1,2,3]
<strong>输出:</strong> "firstsecondthird"
<strong>解释:</strong> 
有三个线程会被异步启动。
输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。
正确的输出是 "firstsecondthird"。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> [1,3,2]
<strong>输出:</strong> "firstsecondthird"
<strong>解释:</strong> 
输入 [1,3,2] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 third() 方法，线程 C 将会调用 second() 方法。
正确的输出是 "firstsecondthird"。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。</li>
	<li>你看到的输入格式主要是为了确保测试的全面性。</li>
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
