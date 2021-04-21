# [748. 最短补全词](https://leetcode-cn.com/problems/shortest-completing-word)

[English Version](/solution/0700-0799/0748.Shortest%20Completing%20Word/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串牌照 <code>licensePlate</code> 和一个字符串数组 <code>words</code> ，请你找出并返回 <code>words</code> 中的 <strong>最短补全词</strong> 。</p>

<p>如果单词列表（<code>words</code>）中的一个单词包含牌照（<code>licensePlate</code>）中所有的字母，那么我们称之为 <strong>补全词</strong> 。在所有完整词中，最短的单词我们称之为 <strong>最短补全词</strong> 。</p>

<p>单词在匹配牌照中的字母时要：</p>

<ul>
	<li>忽略牌照中的数字和空格。</li>
	<li>不区分大小写，比如牌照中的&nbsp;<code>&quot;P&quot;</code>&nbsp;依然可以匹配单词中的&nbsp;<code>&quot;p&quot;</code>&nbsp;字母。</li>
	<li>如果某个字母在牌照中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。</li>
</ul>

<p>例如：<code>licensePlate</code><code> = &quot;aBc 12c&quot;</code>，那么它由字母 <code>&#39;a&#39;</code>、<code>&#39;b&#39;</code> （忽略大写）和两个 <code>&#39;c&#39;</code> 。可能的 <strong>补全词</strong> 是 <code>&quot;abccdef&quot;</code>、<code>&quot;caaacab&quot;</code> 以及 <code>&quot;cbca&quot;</code> 。</p>

<p>题目数据保证一定存在一个最短补全词。当有多个单词都符合最短补全词的匹配条件时取单词列表中最靠前的一个。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>licensePlate = &quot;1s3 PSt&quot;, words = [&quot;step&quot;, &quot;steps&quot;, &quot;stripe&quot;, &quot;stepple&quot;]
<strong>输出：</strong>&quot;steps&quot;
<strong>说明：</strong>最短补全词应该包括 &quot;s&quot;、&quot;p&quot;、&quot;s&quot; 以及 &quot;t&quot;。在匹配过程中我们忽略牌照中的大小写。
&quot;step&quot; 包含 &quot;t&quot;、&quot;p&quot;，但只包含一个 &quot;s&quot;，所以它不符合条件。
&quot;steps&quot; 包含 &quot;t&quot;、&quot;p&quot; 和两个 &quot;s&quot;。
&quot;stripe&quot; 缺一个 &quot;s&quot;。
&quot;stepple&quot; 缺一个 &quot;s&quot;。
因此，&quot;steps&quot; 是唯一一个包含所有字母的单词，也是本样例的答案。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>licensePlate = &quot;1s3 456&quot;, words = [&quot;looks&quot;, &quot;pest&quot;, &quot;stew&quot;, &quot;show&quot;]
<strong>输出：</strong>&quot;pest&quot;
<strong>说明：</strong>存在 3 个包含字母 &quot;s&quot; 且有着最短长度的补全词，&quot;pest&quot;、&quot;stew&quot;、和 &quot;show&quot; 三者长度相同，但我们返回最先出现的补全词 &quot;pest&quot; 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>licensePlate = &quot;Ah71752&quot;, words = [&quot;suggest&quot;,&quot;letter&quot;,&quot;of&quot;,&quot;husband&quot;,&quot;easy&quot;,&quot;education&quot;,&quot;drug&quot;,&quot;prevent&quot;,&quot;writer&quot;,&quot;old&quot;]
<strong>输出：</strong>&quot;husband&quot;
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>licensePlate = &quot;OgEu755&quot;, words = [&quot;enough&quot;,&quot;these&quot;,&quot;play&quot;,&quot;wide&quot;,&quot;wonder&quot;,&quot;box&quot;,&quot;arrive&quot;,&quot;money&quot;,&quot;tax&quot;,&quot;thus&quot;]
<strong>输出：</strong>&quot;enough&quot;
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>licensePlate = &quot;iMSlpe4&quot;, words = [&quot;claim&quot;,&quot;consumer&quot;,&quot;student&quot;,&quot;camera&quot;,&quot;public&quot;,&quot;never&quot;,&quot;wonder&quot;,&quot;simple&quot;,&quot;thought&quot;,&quot;use&quot;]
<strong>输出：</strong>&quot;simple&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= licensePlate.length &lt;= 7</code></li>
	<li><code>licensePlate</code> 由数字、大小写字母或空格 <code>&#39; &#39;</code> 组成</li>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 15</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
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
