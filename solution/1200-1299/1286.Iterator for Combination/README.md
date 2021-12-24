# [1286. 字母组合迭代器](https://leetcode-cn.com/problems/iterator-for-combination)

[English Version](/solution/1200-1299/1286.Iterator%20for%20Combination/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你设计一个迭代器类，包括以下内容：</p>

<ul>
	<li>一个构造函数，输入参数包括：一个&nbsp;<strong>有序且字符唯一&nbsp;</strong>的字符串&nbsp;<code>characters</code>（该字符串只包含小写英文字母）和一个数字&nbsp;<code>combinationLength</code>&nbsp;。</li>
	<li>函数&nbsp;<em>next()&nbsp;</em>，按&nbsp;<strong>字典序&nbsp;</strong>返回长度为&nbsp;<code>combinationLength</code> 的下一个字母组合。</li>
	<li>函数&nbsp;<em>hasNext()&nbsp;</em>，只有存在长度为&nbsp;<code>combinationLength</code> 的下一个字母组合时，才返回&nbsp;<code>True</code>；否则，返回 <code>False</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>CombinationIterator iterator = new CombinationIterator(&quot;abc&quot;, 2); // 创建迭代器 iterator

iterator.next(); // 返回 &quot;ab&quot;
iterator.hasNext(); // 返回 true
iterator.next(); // 返回 &quot;ac&quot;
iterator.hasNext(); // 返回 true
iterator.next(); // 返回 &quot;bc&quot;
iterator.hasNext(); // 返回 false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= combinationLength &lt;=&nbsp;characters.length &lt;= 15</code></li>
	<li>每组测试数据最多包含&nbsp;<code>10^4</code>&nbsp;次函数调用。</li>
	<li>题目保证每次调用函数&nbsp;<code>next</code>&nbsp;时都存在下一个字母组合。</li>
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
