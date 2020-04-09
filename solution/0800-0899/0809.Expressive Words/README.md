# [809. 情感丰富的文字](https://leetcode-cn.com/problems/expressive-words)

## 题目描述
<!-- 这里写题目描述 -->
<p>有时候人们会用重复写一些字母来表示额外的感受，比如 <code>&quot;hello&quot; -&gt; &quot;heeellooo&quot;</code>, <code>&quot;hi&quot; -&gt; &quot;hiii&quot;</code>。我们将相邻字母都相同的一串字符定义为相同字母组，例如：&quot;h&quot;, &quot;eee&quot;, &quot;ll&quot;, &quot;ooo&quot;。</p>

<p>对于一个给定的字符串 S ，如果另一个单词能够通过将一些字母组扩张从而使其和 S 相同，我们将这个单词定义为可扩张的（stretchy）。扩张操作定义如下：选择一个字母组（包含字母&nbsp;<code>c</code>&nbsp;），然后往其中添加相同的字母&nbsp;<code>c</code>&nbsp;使其长度达到 3 或以上。</p>

<p>例如，以&nbsp;&quot;hello&quot; 为例，我们可以对字母组&nbsp;&quot;o&quot; 扩张得到 &quot;hellooo&quot;，但是无法以同样的方法得到 &quot;helloo&quot; 因为字母组 &quot;oo&quot; 长度小于&nbsp;3。此外，我们可以进行另一种扩张 &quot;ll&quot; -&gt; &quot;lllll&quot; 以获得&nbsp;&quot;helllllooo&quot;。如果&nbsp;<code>S = &quot;helllllooo&quot;</code>，那么查询词&nbsp;&quot;hello&quot; 是可扩张的，因为可以对它执行这两种扩张操作使得&nbsp;<code>query = &quot;hello&quot; -&gt; &quot;hellooo&quot; -&gt;&nbsp;&quot;helllllooo&quot; = S</code>。</p>

<p>输入一组查询单词，输出其中可扩张的单词数量。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong> 
S = &quot;heeellooo&quot;
words = [&quot;hello&quot;, &quot;hi&quot;, &quot;helo&quot;]
<strong>输出：</strong>1
<strong>解释</strong>：
我们能通过扩张 &quot;hello&quot; 的 &quot;e&quot; 和 &quot;o&quot; 来得到 &quot;heeellooo&quot;。
我们不能通过扩张 &quot;helo&quot; 来得到 &quot;heeellooo&quot; 因为 &quot;ll&quot; 的长度小于 3 。
</pre>

<p>&nbsp;</p>

<p><strong>说明：</strong></p>

<ul>
	<li><code>0 &lt;= len(S) &lt;= 100</code>。</li>
	<li><code>0 &lt;= len(words) &lt;= 100</code>。</li>
	<li><code>0 &lt;= len(words[i]) &lt;= 100</code>。</li>
	<li><code>S</code>&nbsp;和所有在&nbsp;<code>words</code>&nbsp;中的单词都只由小写字母组成。</li>
</ul>



## 解法
<!-- 这里可写通用的实现逻辑 -->


### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### ...
```

```
