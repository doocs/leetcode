# [2030. 含特定字母的最小子序列](https://leetcode.cn/problems/smallest-k-length-subsequence-with-occurrences-of-a-letter)

[English Version](/solution/2000-2099/2030.Smallest%20K-Length%20Subsequence%20With%20Occurrences%20of%20a%20Letter/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，一个整数 <code>k</code> ，一个字母 <code>letter</code> 以及另一个整数 <code>repetition</code> 。</p>

<p>返回 <code>s</code> 中长度为 <code>k</code> 且 <strong>字典序最小</strong> 的子序列，该子序列同时应满足字母 <code>letter</code> 出现<strong> 至少</strong> <code>repetition</code> 次。生成的测试用例满足 <code>letter</code> 在 <code>s</code> 中出现 <strong>至少</strong> <code>repetition</code> 次。</p>

<p><strong>子序列</strong> 是由原字符串删除一些（或不删除）字符且不改变剩余字符顺序得到的剩余字符串。</p>

<p>字符串 <code>a</code> 字典序比字符串 <code>b</code> 小的定义为：在 <code>a</code> 和 <code>b</code> 出现不同字符的第一个位置上，字符串 <code>a</code> 的字符在字母表中的顺序早于字符串 <code>b</code>&nbsp;的字符。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "leet", k = 3, letter = "e", repetition = 1
<strong>输出：</strong>"eet"
<strong>解释：</strong>存在 4 个长度为 3 ，且满足字母 'e' 出现至少 1 次的子序列：
- "lee"（"<em><strong>lee</strong></em>t"）
- "let"（"<em><strong>le</strong></em>e<em><strong>t</strong></em>"）
- "let"（"<em><strong>l</strong></em>e<em><strong>et</strong></em>"）
- "eet"（"l<em><strong>eet</strong></em>"）
其中字典序最小的子序列是 "eet" 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="example-2" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2030.Smallest%20K-Length%20Subsequence%20With%20Occurrences%20of%20a%20Letter/images/smallest-k-length-subsequence.png" style="width: 339px; height: 67px;" /></p>

<pre>
<strong>输入：</strong>s = "leetcode", k = 4, letter = "e", repetition = 2
<strong>输出：</strong>"ecde"
<strong>解释：</strong>"ecde" 是长度为 4 且满足字母 "e" 出现至少 2 次的字典序最小的子序列。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "bb", k = 2, letter = "b", repetition = 2
<strong>输出：</strong>"bb"
<strong>解释：</strong>"bb" 是唯一一个长度为 2 且满足字母 "b" 出现至少 2 次的子序列。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= repetition &lt;= k &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
	<li><code>letter</code> 是一个小写英文字母，在 <code>s</code>&nbsp;中至少出现 <code>repetition</code> 次</li>
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
