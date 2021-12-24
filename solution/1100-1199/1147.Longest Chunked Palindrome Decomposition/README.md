# [1147. 段式回文](https://leetcode-cn.com/problems/longest-chunked-palindrome-decomposition)

[English Version](/solution/1100-1199/1147.Longest%20Chunked%20Palindrome%20Decomposition/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>段式回文 其实与 一般回文 类似，只不过是最小的单位是 一段字符&nbsp;而不是 单个字母。</p>

<p>举个例子，对于一般回文 &quot;<code>abcba</code>&quot; 是回文，而 &quot;<code>volvo</code>&quot; 不是，但如果我们把&nbsp;&quot;<code>volvo</code>&quot; 分为 &quot;<code>vo</code>&quot;、&quot;<code>l</code>&quot;、&quot;<code>vo</code>&quot; 三段，则可以认为 &ldquo;<code>(vo)(l)(vo)</code>&rdquo; 是段式回文（分为 3 段）。</p>

<p>&nbsp;</p>

<p>给你一个字符串&nbsp;<code>text</code>，在确保它满足段式回文的前提下，请你返回 <strong>段</strong> 的&nbsp;<strong>最大数量</strong>&nbsp;<code>k</code>。</p>

<p>如果段的最大数量为&nbsp;<code>k</code>，那么存在满足以下条件的&nbsp;<code>a_1, a_2, ..., a_k</code>：</p>

<ul>
	<li>每个&nbsp;<code>a_i</code>&nbsp;都是一个非空字符串；</li>
	<li>将这些字符串首位相连的结果&nbsp;<code>a_1 + a_2 + ... + a_k</code>&nbsp;和原始字符串&nbsp;<code>text</code>&nbsp;相同；</li>
	<li>对于所有<code>1 &lt;= i &lt;= k</code>，都有&nbsp;<code>a_i = a_{k+1 - i}</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>text = &quot;ghiabcdefhelloadamhelloabcdefghi&quot;
<strong>输出：</strong>7
<strong>解释：</strong>我们可以把字符串拆分成 &quot;(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)&quot;。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>text = &quot;merchant&quot;
<strong>输出：</strong>1
<strong>解释：</strong>我们可以把字符串拆分成 &quot;(merchant)&quot;。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>text = &quot;antaprezatepzapreanta&quot;
<strong>输出：</strong>11
<strong>解释：</strong>我们可以把字符串拆分成 &quot;(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)&quot;。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>text = &quot;aaa&quot;
<strong>输出：</strong>3
<strong>解释：</strong>我们可以把字符串拆分成 &quot;(a)(a)(a)&quot;。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>text</code>&nbsp;仅由小写英文字符组成。</li>
	<li><code>1 &lt;= text.length &lt;= 1000</code></li>
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
