# [880. 索引处的解码字符串](https://leetcode.cn/problems/decoded-string-at-index)

[English Version](/solution/0800-0899/0880.Decoded%20String%20at%20Index/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个编码字符串 <code>S</code>。请你找出<em> </em><strong>解码字符串</strong> 并将其写入磁带。解码时，从编码字符串中<strong> 每次读取一个字符 </strong>，并采取以下步骤：</p>

<ul>
	<li>如果所读的字符是字母，则将该字母写在磁带上。</li>
	<li>如果所读的字符是数字（例如 <code>d</code>），则整个当前磁带总共会被重复写&nbsp;<code>d-1</code> 次。</li>
</ul>

<p>现在，对于给定的编码字符串 <code>S</code> 和索引 <code>K</code>，查找并返回解码字符串中的第&nbsp;<code>K</code>&nbsp;个字母。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>S = &quot;leet2code3&quot;, K = 10
<strong>输出：</strong>&quot;o&quot;
<strong>解释：</strong>
解码后的字符串为 &quot;leetleetcodeleetleetcodeleetleetcode&quot;。
字符串中的第 10 个字母是 &quot;o&quot;。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>S = &quot;ha22&quot;, K = 5
<strong>输出：</strong>&quot;h&quot;
<strong>解释：</strong>
解码后的字符串为 &quot;hahahaha&quot;。第 5 个字母是 &quot;h&quot;。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>S = &quot;a2345678999999999999999&quot;, K = 1
<strong>输出：</strong>&quot;a&quot;
<strong>解释：</strong>
解码后的字符串为 &quot;a&quot; 重复 8301530446056247680 次。第 1 个字母是 &quot;a&quot;。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= S.length &lt;= 100</code></li>
	<li><code>S</code>&nbsp;只包含小写字母与数字 <code>2</code> 到 <code>9</code> 。</li>
	<li><code>S</code>&nbsp;以字母开头。</li>
	<li><code>1 &lt;= K &lt;= 10^9</code></li>
	<li>题目保证 <code>K</code> 小于或等于解码字符串的长度。</li>
	<li>解码后的字符串保证少于&nbsp;<code>2^63</code>&nbsp;个字母。</li>
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
