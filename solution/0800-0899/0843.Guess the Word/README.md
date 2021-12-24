# [843. 猜猜这个单词](https://leetcode-cn.com/problems/guess-the-word)

[English Version](/solution/0800-0899/0843.Guess%20the%20Word/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>这个问题是 LeetCode 平台新增的<strong><em>交互式问题 </em></strong>。</p>

<p>我们给出了一个由一些独特的单词组成的单词列表，每个单词都是 6 个字母长，并且这个列表中的一个单词将被选作<strong>秘密</strong>。</p>

<p>你可以调用 <code>master.guess(word)</code> 来猜单词。你所猜的单词应当是存在于原列表并且由 6 个小写字母组成的类型<code>字符串</code>。</p>

<p>此函数将会返回一个<code>整型数字</code>，表示你的猜测与<strong>秘密单词</strong>的准确匹配（值和位置同时匹配）的数目。此外，如果你的猜测不在给定的单词列表中，它将返回 <code>-1</code>。</p>

<p>对于每个测试用例，你有 10 次机会来猜出这个单词。当所有调用都结束时，如果您对 <code>master.guess</code> 的调用不超过 10 次，并且至少有一次猜到<strong>秘密</strong>，那么您将通过该测试用例。</p>

<p>除了下面示例给出的测试用例外，还会有 5 个额外的测试用例，每个单词列表中将会有 100 个单词。这些测试用例中的每个单词的字母都是从 <code>&#39;a&#39;</code> 到 <code>&#39;z&#39;</code>&nbsp;中随机选取的，并且保证给定单词列表中的每个单词都是唯一的。</p>

<pre><strong>示例 1:</strong>
<strong>输入:</strong>&nbsp;secret = &quot;acckzz&quot;, wordlist = [&quot;acckzz&quot;,&quot;ccbazz&quot;,&quot;eiowzz&quot;,&quot;abcczz&quot;]

<strong>解释:</strong>

<code>master.guess(&quot;aaaaaa&quot;)</code> 返回 -1, 因为&nbsp;<code>&quot;aaaaaa&quot;</code>&nbsp;不在 wordlist 中.
<code>master.guess(&quot;acckzz&quot;) 返回</code> 6, 因为&nbsp;<code>&quot;acckzz&quot;</code> 就是<strong>秘密</strong>，6个字母完全匹配。
<code>master.guess(&quot;ccbazz&quot;)</code> 返回 3, 因为<code>&nbsp;&quot;ccbazz&quot;</code>&nbsp;有 3 个匹配项。
<code>master.guess(&quot;eiowzz&quot;)</code> 返回 2, 因为&nbsp;<code>&quot;eiowzz&quot;</code>&nbsp;有 2 个匹配项。
<code>master.guess(&quot;abcczz&quot;)</code> 返回 4, 因为&nbsp;<code>&quot;abcczz&quot;</code> 有 4 个匹配项。

我们调用了 5 次master.guess，其中一次猜到了<strong>秘密</strong>，所以我们通过了这个测试用例。
</pre>

<p><strong>提示：</strong>任何试图绕过评判的解决方案都将导致比赛资格被取消。</p>

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
