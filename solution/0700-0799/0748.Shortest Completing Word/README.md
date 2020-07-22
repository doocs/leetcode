# [748. 最短完整词](https://leetcode-cn.com/problems/shortest-completing-word)

## 题目描述
<!-- 这里写题目描述 -->
<p>如果单词列表（<code>words</code>）中的一个单词包含牌照（<code>licensePlate</code>）中所有的字母，那么我们称之为完整词。在所有完整词中，最短的单词我们称之为最短完整词。</p>

<p>单词在匹配牌照中的字母时不区分大小写，比如牌照中的&nbsp;<code>&quot;P&quot;</code>&nbsp;依然可以匹配单词中的&nbsp;<code>&quot;p&quot;</code>&nbsp;字母。</p>

<p>我们保证一定存在一个最短完整词。当有多个单词都符合最短完整词的匹配条件时取单词列表中最靠前的一个。</p>

<p>牌照中可能包含多个相同的字符，比如说：对于牌照 <code>&quot;PP&quot;</code>，单词&nbsp;<code>&quot;pair&quot;</code>&nbsp;无法匹配，但是&nbsp;<code>&quot;supper&quot;</code>&nbsp;可以匹配。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>licensePlate = &quot;1s3 PSt&quot;, words = [&quot;step&quot;, &quot;steps&quot;, &quot;stripe&quot;, &quot;stepple&quot;]
<strong>输出：</strong>&quot;steps&quot;
<strong>说明：</strong>最短完整词应该包括 &quot;s&quot;、&quot;p&quot;、&quot;s&quot; 以及 &quot;t&quot;。对于 &quot;step&quot; 它只包含一个 &quot;s&quot; 所以它不符合条件。同时在匹配过程中我们忽略牌照中的大小写。</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>licensePlate = &quot;1s3 456&quot;, words = [&quot;looks&quot;, &quot;pest&quot;, &quot;stew&quot;, &quot;show&quot;]
<strong>输出：</strong>&quot;pest&quot;
<strong>说明：</strong>存在 3 个包含字母 &quot;s&quot; 且有着最短长度的完整词，但我们返回最先出现的完整词。
</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<ol>
	<li>牌照<code>（licensePlate）</code>的长度在区域<code>[1, 7]</code>中。</li>
	<li>牌照<code>（licensePlate）</code>将会包含数字、空格、或者字母（大写和小写）。</li>
	<li>单词列表<code>（words）</code>长度在区间&nbsp;<code>[10, 1000]</code>&nbsp;中。</li>
	<li>每一个单词&nbsp;<code>words[i]</code>&nbsp;都是小写，并且长度在区间&nbsp;<code>[1, 15]</code>&nbsp;中。</li>
</ol>

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