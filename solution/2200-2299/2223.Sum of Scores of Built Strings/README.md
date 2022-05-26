# [2223. 构造字符串的总得分和](https://leetcode.cn/problems/sum-of-scores-of-built-strings)

[English Version](/solution/2200-2299/2223.Sum%20of%20Scores%20of%20Built%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你需要从空字符串开始&nbsp;<strong>构造</strong> 一个长度为 <code>n</code>&nbsp;的字符串 <code>s</code>&nbsp;，构造的过程为每次给当前字符串 <strong>前面</strong>&nbsp;添加 <strong>一个</strong> 字符。构造过程中得到的所有字符串编号为 <code>1</code>&nbsp;到 <code>n</code>&nbsp;，其中长度为 <code>i</code>&nbsp;的字符串编号为 <code>s<sub>i</sub></code>&nbsp;。</p>

<ul>
	<li>比方说，<code>s = "abaca"</code>&nbsp;，<code>s<sub>1</sub> == "a"</code>&nbsp;，<code>s<sub>2</sub> == "ca"</code>&nbsp;，<code>s<sub>3</sub> == "aca"</code>&nbsp;依次类推。</li>
</ul>

<p><code>s<sub>i</sub></code>&nbsp;的 <strong>得分</strong>&nbsp;为&nbsp;<code>s<sub>i</sub></code> 和&nbsp;<code>s<sub>n</sub></code>&nbsp;的 <strong>最长公共前缀</strong> 的长度（注意&nbsp;<code>s == s<sub>n</sub></code>&nbsp;）。</p>

<p>给你最终的字符串&nbsp;<code>s</code>&nbsp;，请你返回每一个<em>&nbsp;</em><code>s<sub>i</sub></code>&nbsp;的&nbsp;<strong>得分之和</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "babab"
<b>输出：</b>9
<b>解释：</b>
s<sub>1</sub> == "b" ，最长公共前缀是 "b" ，得分为 1 。
s<sub>2</sub> == "ab" ，没有公共前缀，得分为 0 。
s<sub>3</sub> == "bab" ，最长公共前缀为 "bab" ，得分为 3 。
s<sub>4</sub> == "abab" ，没有公共前缀，得分为 0 。
s<sub>5</sub> == "babab" ，最长公共前缀为 "babab" ，得分为 5 。
得分和为 1 + 0 + 3 + 0 + 5 = 9 ，所以我们返回 9 。</pre>

<p><strong>示例 2 ：</strong></p>

<pre>
<b>输入：</b>s = "azbazbzaz"
<b>输出：</b>14
<b>解释：</b>
s<sub>2</sub> == "az" ，最长公共前缀为 "az" ，得分为 2 。
s<sub>6</sub> == "azbzaz" ，最长公共前缀为 "azb" ，得分为 3 。
s<sub>9</sub> == "azbazbzaz" ，最长公共前缀为 "azbazbzaz" ，得分为 9 。
其他 s<sub>i</sub> 得分均为 0 。
得分和为 2 + 3 + 9 = 14 ，所以我们返回 14 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
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

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
