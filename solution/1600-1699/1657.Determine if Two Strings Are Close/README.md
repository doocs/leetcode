# [1657. 确定两个字符串是否接近](https://leetcode.cn/problems/determine-if-two-strings-are-close)

[English Version](/solution/1600-1699/1657.Determine%20if%20Two%20Strings%20Are%20Close/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 <strong>接近</strong> ：</p>

<ul>
	<li>操作 1：交换任意两个 <strong>现有</strong> 字符。
    <ul>
    	<li>例如，<code>a<strong>b</strong>cd<strong>e</strong> -> a<strong>e</strong>cd<strong>b</strong></code></li>
    </ul>
    </li>
    <li>操作 2：将一个 <strong>现有</strong> 字符的每次出现转换为另一个 <strong>现有</strong> 字符，并对另一个字符执行相同的操作。
    <ul>
    	<li>例如，<code><strong>aa</strong>c<strong>abb</strong> -> <strong>bb</strong>c<strong>baa</strong></code>（所有 <code>a</code> 转化为 <code>b</code> ，而所有的 <code>b</code> 转换为 <code>a</code> ）</li>
    </ul>
    </li>
</ul>

<p>你可以根据需要对任意一个字符串多次使用这两种操作。</p>

<p>给你两个字符串，<code>word1</code> 和 <code>word2</code> 。如果<em> </em><code>word1</code><em> </em>和<em> </em><code>word2</code><em> </em><strong>接近 </strong>，就返回 <code>true</code> ；否则，返回<em> </em><code>false</code><em> </em>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word1 = "abc", word2 = "bca"
<strong>输出：</strong>true
<strong>解释：</strong>2 次操作从 word1 获得 word2 。
执行操作 1："a<strong>bc</strong>" -> "a<strong>cb</strong>"
执行操作 1："<strong>a</strong>c<strong>b</strong>" -> "<strong>b</strong>c<strong>a</strong>"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word1 = "a", word2 = "aa"
<strong>输出：</strong>false
<strong>解释：</strong>不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>word1 = "cabbba", word2 = "abbccc"
<strong>输出：</strong>true
<strong>解释：</strong>3 次操作从 word1 获得 word2 。
执行操作 1："ca<strong>b</strong>bb<strong>a</strong>" -> "ca<strong>a</strong>bb<strong>b</strong>"
执行操作 2：<code>"</code><strong>c</strong>aa<strong>bbb</strong>" -> "<strong>b</strong>aa<strong>ccc</strong>"
执行操作 2："<strong>baa</strong>ccc" -> "<strong>abb</strong>ccc"
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>word1 = "cabbba", word2 = "aabbss"
<strong>输出：</strong>false
<strong>解释：</strong>不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= word1.length, word2.length <= 10<sup>5</sup></code></li>
	<li><code>word1</code> 和 <code>word2</code> 仅包含小写英文字母</li>
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
