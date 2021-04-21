# [10. 正则表达式匹配](https://leetcode-cn.com/problems/regular-expression-matching)

[English Version](/solution/0000-0099/0010.Regular%20Expression%20Matching/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 和一个字符规律 <code>p</code>，请你来实现一个支持 <code>'.'</code> 和 <code>'*'</code> 的正则表达式匹配。</p>

<ul>
	<li><code>'.'</code> 匹配任意单个字符</li>
	<li><code>'*'</code> 匹配零个或多个前面的那一个元素</li>
</ul>

<p>所谓匹配，是要涵盖 <strong>整个 </strong>字符串 <code>s</code>的，而不是部分字符串。</p>
 

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aa" p = "a"
<strong>输出：</strong>false
<strong>解释：</strong>"a" 无法匹配 "aa" 整个字符串。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong>s = "aa" p = "a*"
<strong>输出：</strong>true
<strong>解释：</strong>因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "ab" p = ".*"
<strong>输出：</strong>true
<strong>解释：</strong>".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = "aab" p = "c*a*b"
<strong>输出：</strong>true
<strong>解释：</strong>因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>s = "mississippi" p = "mis*is*p*."
<strong>输出：</strong>false</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= s.length <= 20</code></li>
	<li><code>0 <= p.length <= 30</code></li>
	<li><code>s</code> 可能为空，且只包含从 <code>a-z</code> 的小写字母。</li>
	<li><code>p</code> 可能为空，且只包含从 <code>a-z</code> 的小写字母，以及字符 <code>.</code> 和 <code>*</code>。</li>
	<li>保证每次出现字符 <code>*</code> 时，前面都匹配到有效的字符</li>
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
