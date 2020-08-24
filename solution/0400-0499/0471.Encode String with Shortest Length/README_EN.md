# [471. Encode String with Shortest Length](https://leetcode.com/problems/encode-string-with-shortest-length)

[中文文档](/solution/0400-0499/0471.Encode%20String%20with%20Shortest%20Length/README.md)

## Description
<p>Given a <b>non-empty</b> string, encode the string such that its encoded length is the shortest.</p>

<p>The encoding rule is: <code>k[encoded_string]</code>, where the <i>encoded_string</i> inside the square brackets is being repeated exactly <i>k</i> times.</p>

<p><b>Note:</b></p>

<ol>
	<li><i>k</i> will be a positive integer and encoded string will not be empty or have extra space.</li>
	<li>You may assume that the input string contains only lowercase English letters. The string's length is at most 160.</li>
	<li>If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.</li>
</ol>

<p> </p>

<p><b>Example 1:</b></p>

<pre>
Input: "aaa"
Output: "aaa"
Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
</pre>

<p> </p>

<p><b>Example 2:</b></p>

<pre>
Input: "aaaaa"
Output: "5[a]"
Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
</pre>

<p> </p>

<p><b>Example 3:</b></p>

<pre>
Input: "aaaaaaaaaa"
Output: "10[a]"
Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".
</pre>

<p> </p>

<p><b>Example 4:</b></p>

<pre>
Input: "aabcaabcd"
Output: "2[aabc]d"
Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".
</pre>

<p> </p>

<p><b>Example 5:</b></p>

<pre>
Input: "abbbabbbcabbbabbbc"
Output: "2[2[abbb]c]"
Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".
</pre>

<p> </p>



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