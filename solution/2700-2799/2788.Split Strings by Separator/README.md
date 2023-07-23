# [2788. 按分隔符拆分字符串](https://leetcode.cn/problems/split-strings-by-separator)

[English Version](/solution/2700-2799/2788.Split%20Strings%20by%20Separator/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>words</code> 和一个字符 <code>separator</code> ，请你按 <code>separator</code> 拆分 <code>words</code> 中的每个字符串。</p>

<p>返回一个由拆分后的新字符串组成的字符串数组，<strong>不包括空字符串</strong> 。</p>

<p><strong>注意</strong></p>

<ul>
	<li><code>separator</code> 用于决定拆分发生的位置，但它不包含在结果字符串中。</li>
	<li>拆分可能形成两个以上的字符串。</li>
	<li>结果字符串必须保持初始相同的先后顺序。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["one.two.three","four.five","six"], separator = "."
<strong>输出：</strong>["one","two","three","four","five","six"]
<strong>解释：</strong>在本示例中，我们进行下述拆分：

"one.two.three" 拆分为 "one", "two", "three"
"four.five" 拆分为 "four", "five"
"six" 拆分为 "six" 

因此，结果数组为 ["one","two","three","four","five","six"] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["$easy$","$problem$"], separator = "$"
<strong>输出：</strong>["easy","problem"]
<strong>解释：</strong>在本示例中，我们进行下述拆分：

"$easy$" 拆分为 "easy"（不包括空字符串）
"$problem$" 拆分为 "problem"（不包括空字符串）

因此，结果数组为 ["easy","problem"] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = ["|||"], separator = "|"
<strong>输出：</strong>[]
<strong>解释：</strong>在本示例中，"|||" 的拆分结果将只包含一些空字符串，所以我们返回一个空数组 [] 。 </pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>words[i]</code> 中的字符要么是小写英文字母，要么就是字符串 <code>".,|$#@"</code> 中的字符（不包括引号）</li>
	<li><code>separator</code> 是字符串 <code>".,|$#@"</code> 中的某个字符（不包括引号）</li>
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
