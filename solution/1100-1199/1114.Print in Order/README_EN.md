# [1114. Print in Order](https://leetcode.com/problems/print-in-order)

[中文文档](/solution/1100-1199/1114.Print%20in%20Order/README.md)

## Description

<p>Suppose we have a class:</p>

<pre>
public class Foo {
  public void first() { print(&quot;first&quot;); }
  public void second() { print(&quot;second&quot;); }
  public void third() { print(&quot;third&quot;); }
}
</pre>

<p>The same instance of <code>Foo</code> will be passed to three different threads. Thread A will call <code>first()</code>, thread B will call <code>second()</code>, and thread C will call <code>third()</code>. Design a mechanism and modify the program to ensure that <code>second()</code> is executed after <code>first()</code>, and <code>third()</code> is executed after <code>second()</code>.</p>

<p><strong>Note:</strong></p>

<p>We do not know how the threads will be scheduled in the operating system, even though the numbers in the input seem to imply the ordering. The input format you see is mainly to ensure our tests&#39; comprehensiveness.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> &quot;firstsecondthird&quot;
<strong>Explanation:</strong> There are three threads being fired asynchronously. The input [1,2,3] means thread A calls first(), thread B calls second(), and thread C calls third(). &quot;firstsecondthird&quot; is the correct output.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,2]
<strong>Output:</strong> &quot;firstsecondthird&quot;
<strong>Explanation:</strong> The input [1,3,2] means thread A calls first(), thread B calls third(), and thread C calls second(). &quot;firstsecondthird&quot; is the correct output.
</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
