# [2060. 同源字符串检测](https://leetcode-cn.com/problems/check-if-an-original-string-exists-given-two-encoded-strings)

[English Version](/solution/2000-2099/2060.Check%20if%20an%20Original%20String%20Exists%20Given%20Two%20Encoded%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>原字符串由小写字母组成，可以按下述步骤编码：</p>

<ul>
	<li>任意将其 <strong>分割</strong> 为由若干 <strong>非空</strong> 子字符串组成的一个 <strong>序列</strong> 。</li>
	<li>任意选择序列中的一些元素（也可能不选择），然后将这些元素替换为元素各自的长度（作为一个数字型的字符串）。</li>
	<li>重新 <strong>顺次连接</strong> 序列，得到编码后的字符串。</li>
</ul>

<p>例如，编码 <code>"abcdefghijklmnop"</code> 的一种方法可以描述为：</p>

<ul>
	<li>将原字符串分割得到一个序列：<code>["ab", "cdefghijklmn", "o", "p"]</code> 。</li>
	<li>选出其中第二个和第三个元素并分别替换为它们自身的长度。序列变为 <code>["ab", "12", "1", "p"]</code> 。</li>
	<li>重新顺次连接序列中的元素，得到编码后的字符串：<code>"ab121p"</code> 。</li>
</ul>

<p>给你两个编码后的字符串 <code>s1</code> 和 <code>s2</code> ，由小写英文字母和数字 <code>1-9</code> 组成。如果存在能够同时编码得到 <code>s1</code> 和 <code>s2</code> 原字符串，返回 <code>true</code> ；否则，返回 <code>false</code>。</p>

<p><strong>注意：</strong>生成的测试用例满足 <code>s1</code> 和 <code>s2</code> 中连续数字数不超过 <code>3</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s1 = "internationalization", s2 = "i18n"
<strong>输出：</strong>true
<strong>解释：</strong>"internationalization" 可以作为原字符串
- "internationalization" 
  -&gt; 分割：      ["internationalization"]
  -&gt; 不替换任何元素
  -&gt; 连接：      "internationalization"，得到 s1
- "internationalization"
  -&gt; 分割：      ["i", "nternationalizatio", "n"]
  -&gt; 替换：      ["i", "18",                 "n"]
  -&gt; 连接：      "i18n"，得到 s2
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s1 = "l123e", s2 = "44"
<strong>输出：</strong>true
<strong>解释：</strong>"leetcode" 可以作为原字符串
- "leetcode" 
  -&gt; 分割：       ["l", "e", "et", "cod", "e"]
  -&gt; 替换：       ["l", "1", "2",  "3",   "e"]
  -&gt; 连接：       "l123e"，得到 s1
- "leetcode" 
  -&gt; 分割：       ["leet", "code"]
  -&gt; 替换：       ["4",    "4"]
  -&gt; 连接：       "44"，得到 s2
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s1 = "a5b", s2 = "c5b"
<strong>输出：</strong>false
<strong>解释：</strong>不存在这样的原字符串
- 编码为 s1 的字符串必须以字母 'a' 开头
- 编码为 s2 的字符串必须以字母 'c' 开头
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s1 = "112s", s2 = "g841"
<strong>输出：</strong>true
<strong>解释：</strong>"gaaaaaaaaaaaas" 可以作为原字符串
- "gaaaaaaaaaaaas"
  -&gt; 分割：       ["g", "aaaaaaaaaaaa", "s"]
  -&gt; 替换：       ["1", "12",           "s"]
  -&gt; 连接：       "112s"，得到 s1
- "gaaaaaaaaaaaas"
  -&gt; 分割：       ["g", "aaaaaaaa", "aaaa", "s"]
  -&gt; 替换：       ["g", "8",        "4",    "1"]
  -&gt; 连接         "g841"，得到 s2
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>s1 = "ab", s2 = "a2"
<strong>输出：</strong>false
<strong>解释：</strong>不存在这样的原字符串
- 编码为 s1 的字符串由两个字母组成
- 编码为 s2 的字符串由三个字母组成
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 40</code></li>
	<li><code>s1</code> 和 <code>s2</code> 仅由数字 <code>1-9</code> 和小写英文字母组成</li>
	<li><code>s1</code> 和 <code>s2</code> 中连续数字数不超过 <code>3</code></li>
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
