# [1115. 交替打印 FooBar](https://leetcode-cn.com/problems/print-foobar-alternately)

[English Version](/solution/1100-1199/1115.Print%20FooBar%20Alternately/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们提供一个类：</p>

<pre>
class FooBar {
  public void foo() {
&nbsp; &nbsp; for (int i = 0; i &lt; n; i++) {
&nbsp; &nbsp; &nbsp; print(&quot;foo&quot;);
&nbsp;   }
  }

  public void bar() {
&nbsp; &nbsp; for (int i = 0; i &lt; n; i++) {
&nbsp; &nbsp; &nbsp; print(&quot;bar&quot;);
&nbsp; &nbsp; }
  }
}
</pre>

<p>两个不同的线程将会共用一个 <code>FooBar</code>&nbsp;实例。其中一个线程将会调用&nbsp;<code>foo()</code>&nbsp;方法，另一个线程将会调用&nbsp;<code>bar()</code>&nbsp;方法。</p>

<p>请设计修改程序，以确保 &quot;foobar&quot; 被输出 n 次。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> n = 1
<strong>输出:</strong> &quot;foobar&quot;
<strong>解释:</strong> 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，&quot;foobar&quot; 将被输出一次。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = 2
<strong>输出:</strong> &quot;foobarfoobar&quot;
<strong>解释:</strong> &quot;foobar&quot; 将被输出两次。
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
