# [288. 单词的唯一缩写](https://leetcode-cn.com/problems/unique-word-abbreviation)

[English Version](/solution/0200-0299/0288.Unique%20Word%20Abbreviation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>一个单词的缩写需要遵循 <起始字母><中间字母数><结尾字母> 这样的格式。</p>

<p>以下是一些单词缩写的范例：</p>

<pre>a) it                      --> it    (没有缩写)

     1
     ↓
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
     ↓   ↓    ↓    ↓  ↓    
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
     ↓   ↓    ↓
d) l|ocalizatio|n          --> l10n
</pre>

<p>请你判断单词缩写在字典中是否唯一。当单词的缩写满足下面任何一个条件是，可以认为该单词缩写是唯一的：</p>

<ul>
	<li>字典 <code>dictionary</code> 中没有任何其他单词的缩写与该单词 <code>word</code> 的缩写相同。</li>
	<li>字典 <code>dictionary</code> 中的所有缩写与该单词 <code>word</code> 的缩写相同的单词都与 <code>word</code> 相同。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
["ValidWordAbbr","isUnique","isUnique","isUnique","isUnique"]
[[["deer","door","cake","card"]],["dear"],["cart"],["cane"],["make"]]
<strong>输出：</strong>
[null,false,true,false,true]

<strong>解释：</strong>
ValidWordAbbr validWordAbbr = new ValidWordAbbr(["deer", "door", "cake", "card"]);
validWordAbbr.isUnique("dear"); // return False
validWordAbbr.isUnique("cart"); // return True
validWordAbbr.isUnique("cane"); // return False
validWordAbbr.isUnique("make"); // return True
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>每个单词都只由小写字符组成</li>
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
