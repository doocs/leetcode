# [2744. 最大字符串配对数目](https://leetcode.cn/problems/find-maximum-number-of-string-pairs)

[English Version](/solution/2700-2799/2744.Find%20Maximum%20Number%20of%20String%20Pairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>words</code>&nbsp;，数组中包含 <strong>互不相同</strong>&nbsp;的字符串。</p>

<p>如果字符串&nbsp;<code>words[i]</code>&nbsp;与字符串 <code>words[j]</code>&nbsp;满足以下条件，我们称它们可以匹配：</p>

<ul>
	<li>字符串&nbsp;<code>words[i]</code>&nbsp;等于&nbsp;<code>words[j]</code>&nbsp;的反转字符串。</li>
	<li><code>0 &lt;= i &lt; j &lt; words.length</code></li>
</ul>

<p>请你返回数组 <code>words</code>&nbsp;中的&nbsp;<strong>最大</strong>&nbsp;匹配数目。</p>

<p>注意，每个字符串最多匹配一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>words = ["cd","ac","dc","ca","zz"]
<b>输出：</b>2
<strong>解释：</strong>在此示例中，我们可以通过以下方式匹配 2 对字符串：
- 我们将第 0 个字符串与第 2 个字符串匹配，因为 word[0] 的反转字符串是 "dc" 并且等于 words[2]。
- 我们将第 1 个字符串与第 3 个字符串匹配，因为 word[1] 的反转字符串是 "ca" 并且等于 words[3]。
可以证明最多匹配数目是 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>words = ["ab","ba","cc"]
<b>输出：</b>1
<b>解释：</b>在此示例中，我们可以通过以下方式匹配 1 对字符串：
- 我们将第 0 个字符串与第 1 个字符串匹配，因为 words[1] 的反转字符串 "ab" 与 words[0] 相等。
可以证明最多匹配数目是 1 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>words = ["aa","ab"]
<b>输出：</b>0
<strong>解释：</strong>这个例子中，无法匹配任何字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 50</code></li>
	<li><code>words[i].length == 2</code></li>
	<li><code>words</code>&nbsp;包含的字符串互不相同。</li>
	<li><code>words[i]</code>&nbsp;只包含小写英文字母。</li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
