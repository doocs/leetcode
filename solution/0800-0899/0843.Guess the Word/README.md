# [843. 猜猜这个单词](https://leetcode.cn/problems/guess-the-word)

[English Version](/solution/0800-0899/0843.Guess%20the%20Word/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>这是一个&nbsp;<strong><em>交互式问题 </em></strong>。</p>

<p>我们给出了一个由一些 <strong>不同的</strong> 单词组成的列表&nbsp;<code>wordlist</code>&nbsp;，对于每个&nbsp;<code>wordlist[i]</code>&nbsp;长度均为&nbsp;<code>6</code> ，这个列表中的一个单词将被选作&nbsp;<code>secret</code>&nbsp;。</p>

<p>你可以调用&nbsp;<code>Master.guess(word)</code>&nbsp;来猜单词。你所猜的单词应当是存在于原列表并且由 <code>6</code> 个小写字母组成的类型&nbsp;<code>string</code>&nbsp;。</p>

<p>此函数将会返回一个&nbsp;<code>integer</code>&nbsp;，表示你的猜测与<strong>秘密单词</strong>&nbsp;<code>secret</code>&nbsp;的准确匹配（值和位置同时匹配）的数目。此外，如果你的猜测不在给定的单词列表中，它将返回 <code>-1</code>。</p>

<p>对于每个测试用例，你有 <code>10</code> 次机会来猜出这个单词。当所有调用都结束时，如果您对 <code>Master.guess</code> 的调用在&nbsp;<code>10</code> 次以内，并且至少有一次猜到&nbsp;<code>secret</code>&nbsp;，将判定为通过该用例。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong>&nbsp;secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]
<strong>输出:</strong>&nbsp;You guessed the secret word correctly.
<strong>解释:</strong>
<code>master.guess("aaaaaa")</code> 返回 -1, 因为&nbsp;<code>"aaaaaa"</code>&nbsp;不在 wordlist 中.
<code>master.guess("acckzz") 返回</code> 6, 因为&nbsp;<code>"acckzz"</code> 就是<strong>秘密</strong>，6个字母完全匹配。
<code>master.guess("ccbazz")</code> 返回 3, 因为<code>&nbsp;"ccbazz"</code>&nbsp;有 3 个匹配项。
<code>master.guess("eiowzz")</code> 返回 2, 因为&nbsp;<code>"eiowzz"</code>&nbsp;有 2 个匹配项。
<code>master.guess("abcczz")</code> 返回 4, 因为&nbsp;<code>"abcczz"</code> 有 4 个匹配项。
我们调用了 5 次master.guess，其中一次猜到了<strong>秘密</strong>，所以我们通过了这个测试用例。
</pre>

<p><strong>&nbsp;示例 2:</strong></p>

<pre>
<strong>输入:</strong> secret = "hamada", wordlist = ["hamada","khaled"], numguesses = 10
<strong>输出:</strong> You guessed the secret word correctly.
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= wordlist.length &lt;= 100</code></li>
	<li><code>wordlist[i].length == 6</code></li>
	<li><code>wordlist[i]</code>&nbsp;只包含小写英文字母</li>
	<li><code>wordlist</code>&nbsp;中所有字符串都 <strong>不同</strong></li>
	<li><code>secret</code>&nbsp;在&nbsp;<code>wordlist</code>&nbsp;中</li>
	<li><code>numguesses == 10</code></li>
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
