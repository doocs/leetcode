# [966. 元音拼写检查器](https://leetcode-cn.com/problems/vowel-spellchecker)

[English Version](/solution/0900-0999/0966.Vowel%20Spellchecker/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在给定单词列表&nbsp;<code>wordlist</code>&nbsp;的情况下，我们希望实现一个拼写检查器，将查询单词转换为正确的单词。</p>

<p>对于给定的查询单词&nbsp;<code>query</code>，拼写检查器将会处理两类拼写错误：</p>

<ul>
	<li>大小写：如果查询匹配单词列表中的某个单词（<strong>不区分大小写</strong>），则返回的正确单词与单词列表中的大小写相同。

    <ul>
    	<li>例如：<code>wordlist = [&quot;yellow&quot;]</code>, <code>query = &quot;YellOw&quot;</code>: <code>correct = &quot;yellow&quot;</code></li>
    	<li>例如：<code>wordlist = [&quot;Yellow&quot;]</code>, <code>query = &quot;yellow&quot;</code>: <code>correct = &quot;Yellow&quot;</code></li>
    	<li>例如：<code>wordlist = [&quot;yellow&quot;]</code>, <code>query = &quot;yellow&quot;</code>: <code>correct = &quot;yellow&quot;</code></li>
    </ul>
    </li>
    <li>元音错误：如果在将查询单词中的元音（&lsquo;a&rsquo;、&lsquo;e&rsquo;、&lsquo;i&rsquo;、&lsquo;o&rsquo;、&lsquo;u&rsquo;）分别替换为任何元音后，能与单词列表中的单词匹配（<strong>不区分大小写</strong>），则返回的正确单词与单词列表中的匹配项大小写相同。
    <ul>
    	<li>例如：<code>wordlist = [&quot;YellOw&quot;]</code>, <code>query = &quot;yollow&quot;</code>: <code>correct = &quot;YellOw&quot;</code></li>
    	<li>例如：<code>wordlist = [&quot;YellOw&quot;]</code>, <code>query = &quot;yeellow&quot;</code>: <code>correct = &quot;&quot;</code> （无匹配项）</li>
    	<li>例如：<code>wordlist = [&quot;YellOw&quot;]</code>, <code>query = &quot;yllw&quot;</code>: <code>correct = &quot;&quot;</code> （无匹配项）</li>
    </ul>
    </li>

</ul>

<p>此外，拼写检查器还按照以下优先级规则操作：</p>

<ul>
	<li>当查询完全匹配单词列表中的某个单词（<strong>区分大小写</strong>）时，应返回相同的单词。</li>
	<li>当查询匹配到大小写问题的单词时，您应该返回单词列表中的第一个这样的匹配项。</li>
	<li>当查询匹配到元音错误的单词时，您应该返回单词列表中的第一个这样的匹配项。</li>
	<li>如果该查询在单词列表中没有匹配项，则应返回空字符串。</li>
</ul>

<p>给出一些查询 <code>queries</code>，返回一个单词列表 <code>answer</code>，其中 <code>answer[i]</code> 是由查询 <code>query = queries[i]</code> 得到的正确单词。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>wordlist = [&quot;KiTe&quot;,&quot;kite&quot;,&quot;hare&quot;,&quot;Hare&quot;], queries = [&quot;kite&quot;,&quot;Kite&quot;,&quot;KiTe&quot;,&quot;Hare&quot;,&quot;HARE&quot;,&quot;Hear&quot;,&quot;hear&quot;,&quot;keti&quot;,&quot;keet&quot;,&quot;keto&quot;]
<strong>输出：</strong>[&quot;kite&quot;,&quot;KiTe&quot;,&quot;KiTe&quot;,&quot;Hare&quot;,&quot;hare&quot;,&quot;&quot;,&quot;&quot;,&quot;KiTe&quot;,&quot;&quot;,&quot;KiTe&quot;]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= wordlist.length &lt;= 5000</code></li>
	<li><code>1 &lt;= queries.length &lt;= 5000</code></li>
	<li><code>1 &lt;= wordlist[i].length &lt;= 7</code></li>
	<li><code>1 &lt;= queries[i].length &lt;= 7</code></li>
	<li><code>wordlist</code> 和&nbsp;<code>queries</code>&nbsp;中的所有字符串仅由<strong>英文</strong>字母组成。</li>
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
