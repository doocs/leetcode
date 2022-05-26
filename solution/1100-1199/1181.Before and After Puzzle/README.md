# [1181. 前后拼接](https://leetcode-cn.com/problems/before-and-after-puzzle)

[English Version](/solution/1100-1199/1181.Before%20and%20After%20Puzzle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给你一个「短语」列表 <code>phrases</code>，请你帮忙按规则生成拼接后的「新短语」列表。</p>

<p>「短语」（phrase）是仅由小写英文字母和空格组成的字符串。「短语」的开头和结尾都不会出现空格，「短语」中的空格不会连续出现。</p>

<p>「前后拼接」（Before and After puzzles）是合并两个「短语」形成「新短语」的方法。我们规定拼接时，<strong>第一个短语的最后一个单词</strong> 和 <strong>第二个短语的第一个单词</strong> 必须相同。</p>

<p>返回每两个「短语」 <code>phrases[i]</code> 和 <code>phrases[j]</code>（<code>i != j</code>）进行「前后拼接」得到的「新短语」。</p>

<p>注意，两个「短语」拼接时的顺序也很重要，我们需要同时考虑这两个「短语」。另外，同一个「短语」可以多次参与拼接，但「新短语」不能再参与拼接。</p>

<p>请你按字典序排列并返回「新短语」列表，列表中的字符串应该是 <strong>不重复的</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>phrases = ["writing code","code rocks"]
<strong>输出：</strong>["writing code rocks"]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>phrases = ["mission statement",
                "a quick bite to eat",
                "a chip off the old block",
                "chocolate bar",
                "mission impossible",
                "a man on a mission",
                "block party",
                "eat my words",
                "bar of soap"]
<strong>输出：</strong>["a chip off the old block party",
      "a man on a mission impossible",
      "a man on a mission statement",
      "a quick bite to eat my words",
      "chocolate bar of soap"]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>phrases = ["a","b","a"]
<strong>输出：</strong>["a"]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= phrases.length <= 100</code></li>
	<li><code>1 <= phrases[i].length <= 100</code></li>
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
