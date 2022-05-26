# [604. 迭代压缩字符串](https://leetcode.cn/problems/design-compressed-string-iterator)

[English Version](/solution/0600-0699/0604.Design%20Compressed%20String%20Iterator/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计并实现一个迭代压缩字符串的数据结构。给定的压缩字符串的形式是，每个字母后面紧跟一个正整数，表示该字母在原始未压缩字符串中出现的次数。</p>

<p>设计一个数据结构，它支持如下两种操作：&nbsp;<code>next</code>&nbsp;和&nbsp;<code>hasNext</code>。</p>

<ul>
	<li><code>next()</code> - 如果原始字符串中仍有未压缩字符，则返回<strong>下一个字符</strong>，否则返回<strong>空格</strong>。</li>
	<li><code>hasNext()</code> - 如果原始字符串中存在未压缩的的字母，则返回true，否则返回<code>false</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["StringIterator", "next", "next", "next", "next", "next", "next", "hasNext", "next", "hasNext"]
[["L1e2t1C1o1d1e1"], [], [], [], [], [], [], [], [], []]
<b>输出：</b>
[null, "L", "e", "e", "t", "C", "o", true, "d", true]

<strong>解释：</strong>
StringIterator stringIterator = new StringIterator("L1e2t1C1o1d1e1");
stringIterator.next(); // 返回 "L"
stringIterator.next(); // 返回 "e"
stringIterator.next(); // 返回 "e"
stringIterator.next(); // 返回 "t"
stringIterator.next(); // 返回 "C"
stringIterator.next(); // 返回 "o"
stringIterator.hasNext(); // 返回 True
stringIterator.next(); // 返回 "d"
stringIterator.hasNext(); // 返回 True</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;compressedString.length &lt;= 1000</code></li>
	<li><code>compressedString</code>&nbsp;由小写字母、大写字母和数字组成。</li>
	<li>在&nbsp;<code>compressedString</code>&nbsp;中，单个字符的重复次数在&nbsp;<code>[1,10 ^9]</code>&nbsp;范围内。</li>
	<li><code>next</code>&nbsp;和&nbsp;<code>hasNext</code>&nbsp;的操作数最多为&nbsp;<code>100</code>&nbsp;。</li>
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
