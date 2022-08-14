# [2030. Smallest K-Length Subsequence With Occurrences of a Letter](https://leetcode.com/problems/smallest-k-length-subsequence-with-occurrences-of-a-letter)

[中文文档](/solution/2000-2099/2030.Smallest%20K-Length%20Subsequence%20With%20Occurrences%20of%20a%20Letter/README.md)

## Description

<p>You are given a string <code>s</code>, an integer <code>k</code>, a letter <code>letter</code>, and an integer <code>repetition</code>.</p>

<p>Return <em>the <strong>lexicographically smallest</strong> subsequence of</em> <code>s</code><em> of length</em> <code>k</code> <em>that has the letter</em> <code>letter</code> <em>appear <strong>at least</strong></em> <code>repetition</code> <em>times</em>. The test cases are generated so that the <code>letter</code> appears in <code>s</code> <strong>at least</strong> <code>repetition</code> times.</p>

<p>A <strong>subsequence</strong> is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.</p>

<p>A string <code>a</code> is <strong>lexicographically smaller</strong> than a string <code>b</code> if in the first position where <code>a</code> and <code>b</code> differ, string <code>a</code> has a letter that appears earlier in the alphabet than the corresponding letter in <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leet&quot;, k = 3, letter = &quot;e&quot;, repetition = 1
<strong>Output:</strong> &quot;eet&quot;
<strong>Explanation:</strong> There are four subsequences of length 3 that have the letter &#39;e&#39; appear at least 1 time:
- &quot;lee&quot; (from &quot;<strong><u>lee</u></strong>t&quot;)
- &quot;let&quot; (from &quot;<strong><u>le</u></strong>e<u><strong>t</strong></u>&quot;)
- &quot;let&quot; (from &quot;<u><strong>l</strong></u>e<u><strong>et</strong></u>&quot;)
- &quot;eet&quot; (from &quot;l<u><strong>eet</strong></u>&quot;)
The lexicographically smallest subsequence among them is &quot;eet&quot;.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="example-2" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2030.Smallest%20K-Length%20Subsequence%20With%20Occurrences%20of%20a%20Letter/images/smallest-k-length-subsequence.png" style="width: 339px; height: 67px;" />
<pre>
<strong>Input:</strong> s = &quot;leetcode&quot;, k = 4, letter = &quot;e&quot;, repetition = 2
<strong>Output:</strong> &quot;ecde&quot;
<strong>Explanation:</strong> &quot;ecde&quot; is the lexicographically smallest subsequence of length 4 that has the letter &quot;e&quot; appear at least 2 times.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bb&quot;, k = 2, letter = &quot;b&quot;, repetition = 2
<strong>Output:</strong> &quot;bb&quot;
<strong>Explanation:</strong> &quot;bb&quot; is the only subsequence of length 2 that has the letter &quot;b&quot; appear at least 2 times.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= repetition &lt;= k &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>letter</code> is a lowercase English letter, and appears in <code>s</code> at least <code>repetition</code> times.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
