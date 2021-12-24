# [555. Split Concatenated Strings](https://leetcode.com/problems/split-concatenated-strings)

[中文文档](/solution/0500-0599/0555.Split%20Concatenated%20Strings/README.md)

## Description

<p>Given a list of strings, you could concatenate these strings together into a loop, where for each string you could choose to reverse it or not. Among all the possible loops, you need to find the lexicographically biggest string after cutting the loop, which will make the looped string into a regular one.</p>

<p>Specifically, to find the lexicographically biggest string, you need to experience two phases:

<ol>

<li>Concatenate all the strings into a loop, where you can reverse some strings or not and connect them in the same order as given.</li>

<li>Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular one starting from the character at the cutpoint. </li>

</ol>

</p>

<p>And your job is to find the lexicographically biggest one among all the possible regular strings.</p>

<p><b>Example:</b><br />

<pre>

<b>Input:</b> "abc", "xyz"

<b>Output:</b> "zyxcba"

<b>Explanation:</b> You can get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-", <br/>where '-' represents the looped status. <br/>The answer string came from the fourth looped one, <br/>where you could cut from the middle character 'a' and get "zyxcba".

</pre>

</p>

<p><b>Note:</b><br>

<ol>

<li>The input strings will only contain lowercase letters.</li>

<li>The total length of all the strings will not over 1,000.</li>

</ol>

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
