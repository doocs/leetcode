# [1178. 猜字谜](https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle)

## 题目描述
<!-- 这里写题目描述 -->
<p>外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。</p>

<p>字谜的迷面&nbsp;<code>puzzle</code> 按字符串形式给出，如果一个单词&nbsp;<code>word</code>&nbsp;符合下面两个条件，那么它就可以算作谜底：</p>

<ul>
	<li>单词&nbsp;<code>word</code>&nbsp;中包含谜面&nbsp;<code>puzzle</code>&nbsp;的第一个字母。</li>
	<li>单词&nbsp;<code>word</code>&nbsp;中的每一个字母都可以在谜面&nbsp;<code>puzzle</code>&nbsp;中找到。<br>
	例如，如果字谜的谜面是 &quot;abcdefg&quot;，那么可以作为谜底的单词有 &quot;faced&quot;, &quot;cabbage&quot;, 和 &quot;baggage&quot;；而 &quot;beefed&quot;（不含字母 &quot;a&quot;）以及&nbsp;&quot;based&quot;（其中的 &quot;s&quot; 没有出现在谜面中）。</li>
</ul>

<p>返回一个答案数组&nbsp;<code>answer</code>，数组中的每个元素&nbsp;<code>answer[i]</code>&nbsp;是在给出的单词列表 <code>words</code> 中可以作为字谜迷面&nbsp;<code>puzzles[i]</code>&nbsp;所对应的谜底的单词数目。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>
words = [&quot;aaaa&quot;,&quot;asas&quot;,&quot;able&quot;,&quot;ability&quot;,&quot;actt&quot;,&quot;actor&quot;,&quot;access&quot;], 
puzzles = [&quot;aboveyz&quot;,&quot;abrodyz&quot;,&quot;abslute&quot;,&quot;absoryz&quot;,&quot;actresz&quot;,&quot;gaswxyz&quot;]
<strong>输出：</strong>[1,1,3,2,4,0]
<strong>解释：</strong>
1 个单词可以作为 &quot;aboveyz&quot; 的谜底 : &quot;aaaa&quot; 
1 个单词可以作为 &quot;abrodyz&quot; 的谜底 : &quot;aaaa&quot;
3 个单词可以作为 &quot;abslute&quot; 的谜底 : &quot;aaaa&quot;, &quot;asas&quot;, &quot;able&quot;
2 个单词可以作为&nbsp;&quot;absoryz&quot; 的谜底 : &quot;aaaa&quot;, &quot;asas&quot;
4 个单词可以作为&nbsp;&quot;actresz&quot; 的谜底 : &quot;aaaa&quot;, &quot;asas&quot;, &quot;actt&quot;, &quot;access&quot;
没有单词可以作为&nbsp;&quot;gaswxyz&quot; 的谜底，因为列表中的单词都不含字母 &#39;g&#39;。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10^5</code></li>
	<li><code>4 &lt;= words[i].length &lt;= 50</code></li>
	<li><code>1 &lt;= puzzles.length &lt;= 10^4</code></li>
	<li><code>puzzles[i].length == 7</code></li>
	<li><code>words[i][j]</code>, <code>puzzles[i][j]</code>&nbsp;都是小写英文字母。</li>
	<li>每个&nbsp;<code>puzzles[i]</code>&nbsp;所包含的字符都不重复。</li>
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