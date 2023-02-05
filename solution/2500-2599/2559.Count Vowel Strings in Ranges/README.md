# [2559. 统计范围内的元音字符串数](https://leetcode.cn/problems/count-vowel-strings-in-ranges)

[English Version](/solution/2500-2599/2559.Count%20Vowel%20Strings%20in%20Ranges/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串数组 <code>words</code> 以及一个二维整数数组 <code>queries</code> 。</p>

<p>每个查询 <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> 会要求我们统计在 <code>words</code> 中下标在 <code>l<sub>i</sub></code> 到 <code>r<sub>i</sub></code> 范围内（<strong>包含</strong> 这两个值）并且以元音开头和结尾的字符串的数目。</p>

<p>返回一个整数数组，其中数组的第 <code>i</code> 个元素对应第 <code>i</code> 个查询的答案。</p>

<p><strong>注意：</strong>元音字母是 <code>'a'</code>、<code>'e'</code>、<code>'i'</code>、<code>'o'</code> 和 <code>'u'</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
<strong>输出：</strong>[2,3,0]
<strong>解释：</strong>以元音开头和结尾的字符串是 "aba"、"ece"、"aa" 和 "e" 。
查询 [0,2] 结果为 2（字符串 "aba" 和 "ece"）。
查询 [1,4] 结果为 3（字符串 "ece"、"aa"、"e"）。
查询 [1,1] 结果为 0 。
返回结果 [2,3,0] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
<strong>输出：</strong>[3,2,1]
<strong>解释：</strong>每个字符串都满足这一条件，所以返回 [3,2,1] 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 40</code></li>
	<li><code>words[i]</code> 仅由小写英文字母组成</li>
	<li><code>sum(words[i].length) &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= queries[j][0] &lt;= queries[j][1] &lt;&nbsp;words.length</code></li>
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
