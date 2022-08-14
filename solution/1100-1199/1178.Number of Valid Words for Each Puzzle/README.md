# [1178. 猜字谜](https://leetcode.cn/problems/number-of-valid-words-for-each-puzzle)

[English Version](/solution/1100-1199/1178.Number%20of%20Valid%20Words%20for%20Each%20Puzzle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。</p>

<p>字谜的迷面 <code>puzzle</code> 按字符串形式给出，如果一个单词 <code>word</code> 符合下面两个条件，那么它就可以算作谜底：</p>

<ul>
	<li>单词 <code>word</code> 中包含谜面 <code>puzzle</code> 的第一个字母。</li>
	<li>单词 <code>word</code> 中的每一个字母都可以在谜面 <code>puzzle</code> 中找到。<br />
	例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）都不能作为谜底。</li>
</ul>

<p>返回一个答案数组 <code>answer</code>，数组中的每个元素 <code>answer[i]</code> 是在给出的单词列表 <code>words</code> 中可以作为字谜迷面 <code>puzzles[i]</code> 所对应的谜底的单词数目。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
words = ["aaaa","asas","able","ability","actt","actor","access"], 
puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
<strong>输出：</strong>[1,1,3,2,4,0]
<strong>解释：</strong>
1 个单词可以作为 "aboveyz" 的谜底 : "aaaa" 
1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= words.length <= 10^5</code></li>
	<li><code>4 <= words[i].length <= 50</code></li>
	<li><code>1 <= puzzles.length <= 10^4</code></li>
	<li><code>puzzles[i].length == 7</code></li>
	<li><code>words[i][j]</code>, <code>puzzles[i][j]</code> 都是小写英文字母。</li>
	<li>每个 <code>puzzles[i]</code> 所包含的字符都不重复。</li>
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
