# [953. 验证外星语词典](https://leetcode-cn.com/problems/verifying-an-alien-dictionary)

[English Version](/solution/0900-0999/0953.Verifying%20an%20Alien%20Dictionary/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>某种外星语也使用英文小写字母，但可能顺序 <code>order</code> 不同。字母表的顺序（<code>order</code>）是一些小写字母的排列。</p>

<p>给定一组用外星语书写的单词 <code>words</code>，以及其字母表的顺序 <code>order</code>，只有当给定的单词在这种外星语中按字典序排列时，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>words = [&quot;hello&quot;,&quot;leetcode&quot;], order = &quot;hlabcdefgijkmnopqrstuvwxyz&quot;
<strong>输出：</strong>true
<strong>解释：</strong>在该语言的字母表中，&#39;h&#39; 位于 &#39;l&#39; 之前，所以单词序列是按字典序排列的。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>words = [&quot;word&quot;,&quot;world&quot;,&quot;row&quot;], order = &quot;worldabcefghijkmnpqstuvxyz&quot;
<strong>输出：</strong>false
<strong>解释：</strong>在该语言的字母表中，&#39;d&#39; 位于 &#39;l&#39; 之后，那么 words[0] &gt; words[1]，因此单词序列不是按字典序排列的。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>words = [&quot;apple&quot;,&quot;app&quot;], order = &quot;abcdefghijklmnopqrstuvwxyz&quot;
<strong>输出：</strong>false
<strong>解释：</strong>当前三个字符 &quot;app&quot; 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 &quot;apple&quot; &gt; &quot;app&quot;，因为 &#39;l&#39; &gt; &#39;&empty;&#39;，其中 &#39;&empty;&#39; 是空白字符，定义为比任何其他字符都小（<a href="https://baike.baidu.com/item/%E5%AD%97%E5%85%B8%E5%BA%8F" target="_blank">更多信息</a>）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>order.length == 26</code></li>
	<li>在&nbsp;<code>words[i]</code>&nbsp;和&nbsp;<code>order</code>&nbsp;中的所有字符都是英文小写字母。</li>
</ol>



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