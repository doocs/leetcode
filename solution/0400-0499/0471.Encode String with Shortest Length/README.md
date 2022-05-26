# [471. 编码最短长度的字符串](https://leetcode.cn/problems/encode-string-with-shortest-length)

[English Version](/solution/0400-0499/0471.Encode%20String%20with%20Shortest%20Length/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <strong>非空</strong> 字符串，将其编码为具有最短长度的字符串。</p>

<p>编码规则是：<code>k[encoded_string]</code>，其中在方括号 <code>encoded_string</code><em> </em>中的内容重复 <code>k</code> 次。</p>

<p><strong>注：</strong></p>

<ul>
	<li><em>k</em> 为正整数</li>
	<li>如果编码的过程不能使字符串缩短，则不要对其进行编码。如果有多种编码方式，返回 <strong>任意一种</strong> 即可</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aaa"
<strong>输出：</strong>"aaa"
<strong>解释：</strong>无法将其编码为更短的字符串，因此不进行编码。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aaaaa"
<strong>输出：</strong>"5[a]"
<strong>解释：</strong>"5[a]" 比 "aaaaa" 短 1 个字符。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "aaaaaaaaaa"
<strong>输出：</strong>"10[a]"
<strong>解释：</strong>"a9[a]" 或 "9[a]a" 都是合法的编码，和 "10[a]" 一样长度都为 5。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = "aabcaabcd"
<strong>输出：</strong>"2[aabc]d"
<strong>解释：</strong>"aabc" 出现两次，因此一种答案可以是 "2[aabc]d"。
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>s = "abbbabbbcabbbabbbc"
<strong>输出：</strong>"2[2[abbb]c]"
<strong>解释：</strong>"abbbabbbc" 出现两次，但是 "abbbabbbc" 可以编码为 "2[abbb]c"，因此一种答案可以是 "2[2[abbb]c]"。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 150</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
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
