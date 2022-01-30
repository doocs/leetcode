# [2157. 字符串分组](https://leetcode-cn.com/problems/groups-of-strings)

[English Version](/solution/2100-2199/2157.Groups%20of%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从&nbsp;<strong>0&nbsp;</strong>开始的字符串数组&nbsp;<code>words</code>&nbsp;。每个字符串都只包含 <strong>小写英文字母</strong>&nbsp;。<code>words</code>&nbsp;中任意一个子串中，每个字母都至多只出现一次。</p>

<p>如果通过以下操作之一，我们可以从 <code>s1</code>&nbsp;的字母集合得到 <code>s2</code>&nbsp;的字母集合，那么我们称这两个字符串为 <strong>关联的</strong>&nbsp;：</p>

<ul>
	<li>往&nbsp;<code>s1</code>&nbsp;的字母集合中添加一个字母。</li>
	<li>从&nbsp;<code>s1</code>&nbsp;的字母集合中删去一个字母。</li>
	<li>将 <code>s1</code>&nbsp;中的一个字母替换成另外任意一个字母（也可以替换为这个字母本身）。</li>
</ul>

<p>数组&nbsp;<code>words</code>&nbsp;可以分为一个或者多个无交集的 <strong>组</strong>&nbsp;。一个字符串与一个组如果满足以下 <strong>任一</strong>&nbsp;条件，它就属于这个组：</p>

<ul>
	<li>它与组内 <strong>至少</strong>&nbsp;一个其他字符串关联。</li>
	<li>它是这个组中 <strong>唯一</strong>&nbsp;的字符串。</li>
</ul>

<p>注意，你需要确保分好组后，一个组内的任一字符串与其他组的字符串都不关联。可以证明在这个条件下，分组方案是唯一的。</p>

<p>请你返回一个长度为 <code>2</code>&nbsp;的数组&nbsp;<code>ans</code>&nbsp;：</p>

<ul>
	<li><code>ans[0]</code>&nbsp;是&nbsp;<code>words</code>&nbsp;分组后的&nbsp;<strong>总组数</strong>&nbsp;。</li>
	<li><code>ans[1]</code>&nbsp;是字符串数目最多的组所包含的字符串数目。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>words = ["a","b","ab","cde"]
<b>输出：</b>[2,3]
<b>解释：</b>
- words[0] 可以得到 words[1] （将 'a' 替换为 'b'）和 words[2] （添加 'b'）。所以 words[0] 与 words[1] 和 words[2] 关联。
- words[1] 可以得到 words[0] （将 'b' 替换为 'a'）和 words[2] （添加 'a'）。所以 words[1] 与 words[0] 和 words[2] 关联。
- words[2] 可以得到 words[0] （删去 'b'）和 words[1] （删去 'a'）。所以 words[2] 与 words[0] 和 words[1] 关联。
- words[3] 与 words 中其他字符串都不关联。
所以，words 可以分成 2 个组 ["a","b","ab"] 和 ["cde"] 。最大的组大小为 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>words = ["a","ab","abc"]
<b>输出：</b>[1,3]
<strong>解释：</strong>
- words[0] 与 words[1] 关联。
- words[1] 与 words[0] 和 words[2] 关联。
- words[2] 与 words[1] 关联。
由于所有字符串与其他字符串都关联，所以它们全部在同一个组内。
所以最大的组大小为 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 26</code></li>
	<li><code>words[i]</code>&nbsp;只包含小写英文字母。</li>
	<li><code>words[i]</code> 中每个字母最多只出现一次。</li>
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
