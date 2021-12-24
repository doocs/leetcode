# [1427. 字符串的左右移](https://leetcode-cn.com/problems/perform-string-shifts)

[English Version](/solution/1400-1499/1427.Perform%20String%20Shifts/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个包含小写英文字母的字符串&nbsp;<code>s</code>&nbsp;以及一个矩阵&nbsp;<code>shift</code>，其中&nbsp;<code>shift[i] = [direction, amount]</code>：</p>

<ul>
	<li><code>direction</code>&nbsp;可以为&nbsp;<code>0</code>&nbsp;（表示左移）或&nbsp;<code>1</code>&nbsp;（表示右移）。</li>
	<li><code>amount</code>&nbsp;表示&nbsp;<code>s</code>&nbsp;左右移的位数。</li>
	<li>左移 1 位表示移除&nbsp;<code>s</code>&nbsp;的第一个字符，并将该字符插入到 <code>s</code> 的结尾。</li>
	<li>类似地，右移 1 位表示移除&nbsp;<code>s</code>&nbsp;的最后一个字符，并将该字符插入到 <code>s</code> 的开头。</li>
</ul>

<p>对这个字符串进行所有操作后，返回最终结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;abc&quot;, shift = [[0,1],[1,2]]
<strong>输出：</strong>&quot;cab&quot;
<strong>解释：</strong>
[0,1] 表示左移 1 位。 &quot;abc&quot; -&gt; &quot;bca&quot;
[1,2] 表示右移 2 位。 &quot;bca&quot; -&gt; &quot;cab&quot;</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;abcdefg&quot;, shift = [[1,1],[1,1],[0,2],[1,3]]
<strong>输出：</strong>&quot;efgabcd&quot;
<strong>解释：</strong> 
[1,1] 表示右移 1 位。 &quot;abcdefg&quot; -&gt; &quot;gabcdef&quot;
[1,1] 表示右移 1 位。 &quot;gabcdef&quot; -&gt; &quot;fgabcde&quot;
[0,2] 表示左移 2 位。 &quot;fgabcde&quot; -&gt; &quot;abcdefg&quot;
[1,3] 表示右移 3 位。 &quot;abcdefg&quot; -&gt; &quot;efgabcd&quot;</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母</li>
	<li><code>1 &lt;= shift.length &lt;= 100</code></li>
	<li><code>shift[i].length == 2</code></li>
	<li><code>0 &lt;= shift[i][0] &lt;= 1</code></li>
	<li><code>0 &lt;= shift[i][1] &lt;= 100</code></li>
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
