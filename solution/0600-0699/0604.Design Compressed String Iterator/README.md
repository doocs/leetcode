# [604. 迭代压缩字符串](https://leetcode-cn.com/problems/design-compressed-string-iterator)

[English Version](/solution/0600-0699/0604.Design%20Compressed%20String%20Iterator/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>对于一个压缩字符串，设计一个数据结构，它支持如下两种操作：&nbsp;<code>next</code>&nbsp;和&nbsp;<code>hasNext</code>。</p>

<p>给定的压缩字符串格式为：每个字母后面紧跟一个正整数，这个整数表示该字母在解压后的字符串里连续出现的次数。</p>

<p><code>next()</code> - 如果压缩字符串仍然有字母未被解压，则返回下一个字母，否则返回一个空格。<br>
<code>hasNext()</code> - 判断是否还有字母仍然没被解压。</p>

<p><strong>注意：</strong></p>

<p>请记得将你的类在 StringIterator 中&nbsp;<strong>初始化</strong>&nbsp;，因为静态变量或类变量在多组测试数据中不会被自动清空。更多细节请访问 <a href="http://leetcode.com/faq/#different-output">这里</a>&nbsp;。</p>

<p><strong>示例：</strong></p>

<pre>StringIterator iterator = new StringIterator(&quot;L1e2t1C1o1d1e1&quot;);

iterator.next(); // 返回 &#39;L&#39;
iterator.next(); // 返回 &#39;e&#39;
iterator.next(); // 返回 &#39;e&#39;
iterator.next(); // 返回 &#39;t&#39;
iterator.next(); // 返回 &#39;C&#39;
iterator.next(); // 返回 &#39;o&#39;
iterator.next(); // 返回 &#39;d&#39;
iterator.hasNext(); // 返回 true
iterator.next(); // 返回 &#39;e&#39;
iterator.hasNext(); // 返回 false
iterator.next(); // 返回 &#39; &#39;
</pre>

<p>&nbsp;</p>

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
