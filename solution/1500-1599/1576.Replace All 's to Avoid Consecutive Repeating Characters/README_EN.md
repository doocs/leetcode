# [1576. Replace All 's to Avoid Consecutive Repeating Characters](https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters)

[中文文档](/solution/1500-1599/1576.Replace%20All%20%27s%20to%20Avoid%20Consecutive%20Repeating%20Characters/README.md)

## Description

<p>Given a string&nbsp;<code>s</code><var>&nbsp;</var>containing only lower case English letters&nbsp;and the &#39;?&#39;&nbsp;character, convert <strong>all </strong>the &#39;?&#39; characters into lower case letters such that the final string does not contain any <strong>consecutive repeating&nbsp;</strong>characters.&nbsp;You <strong>cannot </strong>modify the non &#39;?&#39; characters.</p>

<p>It is <strong>guaranteed </strong>that there are no consecutive repeating characters in the given string <strong>except </strong>for &#39;?&#39;.</p>

<p>Return the final string after all the conversions (possibly zero) have been made. If there is more than one solution, return any of them.&nbsp;It can be shown that an answer is always possible with the given constraints.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;?zs&quot;
<strong>Output:</strong> &quot;azs&quot;
<strong>Explanation</strong>: There are 25 solutions for this problem. From &quot;azs&quot; to &quot;yzs&quot;, all are valid. Only &quot;z&quot; is an invalid modification as the string will consist of consecutive repeating characters in &quot;zzs&quot;.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ubv?w&quot;
<strong>Output:</strong> &quot;ubvaw&quot;
<strong>Explanation</strong>: There are 24 solutions for this problem. Only &quot;v&quot; and &quot;w&quot; are invalid modifications as the strings will consist of consecutive repeating characters in &quot;ubvvw&quot; and &quot;ubvww&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;j?qg??b&quot;
<strong>Output:</strong> &quot;jaqgacb&quot;
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;??yw?ipkj?&quot;
<strong>Output:</strong> &quot;acywaipkja&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length&nbsp;&lt;= 100</code></li>
	<li><code>s</code> contains&nbsp;only lower case English letters and <code>&#39;?&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def modifyString(self, s: str) -> str:
        s = list(s)
        for i in range(len(s)):
            if s[i] == '?':
                ahead = ' ' if i == 0 else s[i - 1]
                behind = ' ' if i == len(s) - 1 else s[i + 1]
                for c in string.ascii_lowercase:
                    if c != ahead and c != behind:
                        s[i] = c
                        break
        return "".join(s)
```

### **Java**

```java
class Solution {
    public String modifyString(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                // 前面的字符
                char ahead = i == 0 ? ' ' : chars[i - 1];
                // 后面的字符
                char behind = i == chars.length - 1 ? ' ' : chars[i + 1];
                char temp = 'a';
                while (temp == ahead || temp == behind) {
                    temp++;
                }
                chars[i] = temp;
            }
        }
        return new String(chars);
    }
}
```

### **Go**

```go
func modifyString(s string) string {
	data := []byte(" " + s + " ")
	for i, c := range data {
		if c == byte('?') {
			ahead, behind := data[i-1], data[i+1]
			for t := byte('a'); t <= byte('z'); t++ {
				if t != ahead && t != behind {
					data[i] = t
				}
			}
		}
	}
	return string(data[1 : len(data)-1])
}
```

<!-- tabs:end -->
