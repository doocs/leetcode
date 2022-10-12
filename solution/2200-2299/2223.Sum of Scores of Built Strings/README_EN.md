# [2223. Sum of Scores of Built Strings](https://leetcode.com/problems/sum-of-scores-of-built-strings)

[中文文档](/solution/2200-2299/2223.Sum%20of%20Scores%20of%20Built%20Strings/README.md)

## Description

<p>You are <strong>building</strong> a string <code>s</code> of length <code>n</code> <strong>one</strong> character at a time, <strong>prepending</strong> each new character to the <strong>front</strong> of the string. The strings are labeled from <code>1</code> to <code>n</code>, where the string with length <code>i</code> is labeled <code>s<sub>i</sub></code>.</p>

<ul>
	<li>For example, for <code>s = &quot;abaca&quot;</code>, <code>s<sub>1</sub> == &quot;a&quot;</code>, <code>s<sub>2</sub> == &quot;ca&quot;</code>, <code>s<sub>3</sub> == &quot;aca&quot;</code>, etc.</li>
</ul>

<p>The <strong>score</strong> of <code>s<sub>i</sub></code> is the length of the <strong>longest common prefix</strong> between <code>s<sub>i</sub></code> and <code>s<sub>n</sub></code> (Note that <code>s == s<sub>n</sub></code>).</p>

<p>Given the final string <code>s</code>, return<em> the <strong>sum</strong> of the <strong>score</strong> of every </em><code>s<sub>i</sub></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;babab&quot;
<strong>Output:</strong> 9
<strong>Explanation:</strong>
For s<sub>1</sub> == &quot;b&quot;, the longest common prefix is &quot;b&quot; which has a score of 1.
For s<sub>2</sub> == &quot;ab&quot;, there is no common prefix so the score is 0.
For s<sub>3</sub> == &quot;bab&quot;, the longest common prefix is &quot;bab&quot; which has a score of 3.
For s<sub>4</sub> == &quot;abab&quot;, there is no common prefix so the score is 0.
For s<sub>5</sub> == &quot;babab&quot;, the longest common prefix is &quot;babab&quot; which has a score of 5.
The sum of the scores is 1 + 0 + 3 + 0 + 5 = 9, so we return 9.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;azbazbzaz&quot;
<strong>Output:</strong> 14
<strong>Explanation:</strong> 
For s<sub>2</sub> == &quot;az&quot;, the longest common prefix is &quot;az&quot; which has a score of 2.
For s<sub>6</sub> == &quot;azbzaz&quot;, the longest common prefix is &quot;azb&quot; which has a score of 3.
For s<sub>9</sub> == &quot;azbazbzaz&quot;, the longest common prefix is &quot;azbazbzaz&quot; which has a score of 9.
For all other s<sub>i</sub>, the score is 0.
The sum of the scores is 2 + 3 + 9 = 14, so we return 14.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
