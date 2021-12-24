# [1032. 字符流](https://leetcode-cn.com/problems/stream-of-characters)

[English Version](/solution/1000-1099/1032.Stream%20of%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>按下述要求实现 <code>StreamChecker</code> 类：</p>

<ul>
	<li><code>StreamChecker(words)</code>：构造函数，用给定的字词初始化数据结构。</li>
	<li><code>query(letter)</code>：如果存在某些 <code>k &gt;= 1</code>，可以用查询的最后 <code>k</code>个字符（按从旧到新顺序，包括刚刚查询的字母）拼写出给定字词表中的某一字词时，返回 <code>true</code>。否则，返回 <code>false</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>StreamChecker streamChecker = new StreamChecker([&quot;cd&quot;,&quot;f&quot;,&quot;kl&quot;]); // 初始化字典
streamChecker.query(&#39;a&#39;);          // 返回 false
streamChecker.query(&#39;b&#39;);          // 返回 false
streamChecker.query(&#39;c&#39;);          // 返回 false
streamChecker.query(&#39;d&#39;);          // 返回 true，因为 &#39;cd&#39; 在字词表中
streamChecker.query(&#39;e&#39;);          // 返回 false
streamChecker.query(&#39;f&#39;);          // 返回 true，因为 &#39;f&#39; 在字词表中
streamChecker.query(&#39;g&#39;);          // 返回 false
streamChecker.query(&#39;h&#39;);          // 返回 false
streamChecker.query(&#39;i&#39;);          // 返回 false
streamChecker.query(&#39;j&#39;);          // 返回 false
streamChecker.query(&#39;k&#39;);          // 返回 false
streamChecker.query(&#39;l&#39;);          // 返回 true，因为 &#39;kl&#39; 在字词表中。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 2000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 2000</code></li>
	<li>字词只包含小写英文字母。</li>
	<li>待查项只包含小写英文字母。</li>
	<li>待查项最多 40000 个。</li>
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
