# [712. Minimum ASCII Delete Sum for Two Strings](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings)

[中文文档](/solution/0700-0799/0712.Minimum%20ASCII%20Delete%20Sum%20for%20Two%20Strings/README.md)

## Description

<p>Given two strings <code>s1, s2</code>, find the lowest ASCII sum of deleted characters to make two strings equal.</p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> s1 = "sea", s2 = "eat"

<b>Output:</b> 231

<b>Explanation:</b> Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.

Deleting "t" from "eat" adds 116 to the sum.

At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.

</pre>

</p>

<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> s1 = "delete", s2 = "leet"

<b>Output:</b> 403

<b>Explanation:</b> Deleting "dee" from "delete" to turn the string into "let",

adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.

At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.

If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.

</pre>

</p>

<p><b>Note:</b>

<li><code>0 < s1.length, s2.length <= 1000</code>.</li>

<li>All elements of each string will have an ASCII value in <code>[97, 122]</code>.</li>

</p>

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
