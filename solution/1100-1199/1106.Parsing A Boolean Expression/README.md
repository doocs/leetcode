# [1106. 解析布尔表达式](https://leetcode.cn/problems/parsing-a-boolean-expression)

[English Version](/solution/1100-1199/1106.Parsing%20A%20Boolean%20Expression/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个以字符串形式表述的&nbsp;<a href="https://baike.baidu.com/item/%E5%B8%83%E5%B0%94%E8%A1%A8%E8%BE%BE%E5%BC%8F/1574380?fr=aladdin" target="_blank">布尔表达式</a>（boolean） <code>expression</code>，返回该式的运算结果。</p>

<p>有效的表达式需遵循以下约定：</p>

<ul>
	<li><code>&quot;t&quot;</code>，运算结果为 <code>True</code></li>
	<li><code>&quot;f&quot;</code>，运算结果为 <code>False</code></li>
	<li><code>&quot;!(expr)&quot;</code>，运算过程为对内部表达式 <code>expr</code> 进行逻辑 <strong>非的运算</strong>（NOT）</li>
	<li><code>&quot;&amp;(expr1,expr2,...)&quot;</code>，运算过程为对 2 个或以上内部表达式 <code>expr1, expr2, ...</code> 进行逻辑 <strong>与的运算</strong>（AND）</li>
	<li><code>&quot;|(expr1,expr2,...)&quot;</code>，运算过程为对 2 个或以上内部表达式 <code>expr1, expr2, ...</code> 进行逻辑 <strong>或的运算</strong>（OR）</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>expression = &quot;!(f)&quot;
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>expression = &quot;|(f,t)&quot;
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>expression = &quot;&amp;(t,f)&quot;
<strong>输出：</strong>false
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>expression = &quot;|(&amp;(t,f,t),!(t))&quot;
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 20000</code></li>
	<li><code>expression[i]</code> 由 <code>{&#39;(&#39;, &#39;)&#39;, &#39;&amp;&#39;, &#39;|&#39;, &#39;!&#39;, &#39;t&#39;, &#39;f&#39;, &#39;,&#39;}</code> 中的字符组成。</li>
	<li><code>expression</code> 是以上述形式给出的有效表达式，表示一个布尔值。</li>
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
