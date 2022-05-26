# [471. 编码最短长度的字符串](https://leetcode-cn.com/problems/encode-string-with-shortest-length)

[English Version](/solution/0400-0499/0471.Encode%20String%20with%20Shortest%20Length/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个 <strong>非空</strong> 字符串，将其编码为具有最短长度的字符串。</p>

<p>编码规则是：<code>k[encoded_string]</code>，其中在方括号 <em>encoded_string </em>中的内容重复 <em>k</em> 次。</p>

<p><strong>注：</strong></p>

<ol>
	<li><em>k</em> 为正整数且编码后的字符串不能为空或有额外的空格。</li>
	<li>你可以假定输入的字符串只包含小写的英文字母。字符串长度不超过 160。</li>
	<li>如果编码的过程不能使字符串缩短，则不要对其进行编码。如果有多种编码方式，返回任意一种即可。</li>
</ol>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>输入： "aaa"
输出： "aaa"
解释： 无法将其编码为更短的字符串，因此不进行编码。
</pre>

<p> </p>

<p><strong>示例 2：</strong></p>

<pre>输入： "aaaaa"
输出： "5[a]"
解释： "5[a]" 比 "aaaaa" 短 1 个字符。
</pre>

<p> </p>

<p><strong>示例 3：</strong></p>

<pre>输入： "aaaaaaaaaa"
输出： "10[a]"
解释： "a9[a]" 或 "9[a]a" 都是合法的编码，和 "10[a]" 一样长度都为 5。
</pre>

<p> </p>

<p><strong>示例 4：</strong></p>

<pre>输入： "aabcaabcd"
输出： "2[aabc]d"
解释： "aabc" 出现两次，因此一种答案可以是 "2[aabc]d"。
</pre>

<p> </p>

<p><strong>示例 5：</strong></p>

<pre>输入： "abbbabbbcabbbabbbc"
输出： "2[2[abbb]c]"
解释： "abbbabbbc" 出现两次，但是 "abbbabbbc" 可以编码为 "2[abbb]c"，因此一种答案可以是 "2[2[abbb]c]"。
</pre>

<p> </p>

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
