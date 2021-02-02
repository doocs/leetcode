# [392. Is Subsequence](https://leetcode.com/problems/is-subsequence)

[中文文档](/solution/0300-0399/0392.Is%20Subsequence/README.md)

## Description

<p>

Given a string <b>s</b> and a string <b>t</b>, check if <b>s</b> is subsequence of <b>t</b>.

</p>

<p>

You may assume that there is only lower case English letters in both <b>s</b> and <b>t</b>. <b>t</b> is potentially a very long (length ~= 500,000) string, and <b>s</b> is a short string (<=100).

</p>

<p>

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, <code>"ace"</code> is a subsequence of <code>"abcde"</code> while <code>"aec"</code> is not).

</p>

<p><b>Example 1:</b><br />

<b>s</b> = <code>"abc"</code>, <b>t</b> = <code>"ahbgdc"</code>

</p>

<p>

Return <code>true</code>.

</p>

<p><b>Example 2:</b><br />

<b>s</b> = <code>"axc"</code>, <b>t</b> = <code>"ahbgdc"</code>

</p>

<p>

Return <code>false</code>.

</p>

<p><b>Follow up:</b><br />

If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?</p>

<p><b>Credits:</b><br />Special thanks to <a href="https://leetcode.com/pbrother/">@pbrother</a> for adding this problem and creating all test cases.</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isSubsequence(self, s: str, t: str) -> bool:
        m, n = len(s), len(t)
        i = j = 0
        while i < m and j < n:
            if s[i] == t[j]:
                i += 1
            j += 1
        return i == m
```

### **Java**

```java
class Solution {
    public boolean isSubsequence(String s, String t) {
        int m = s.length(), n = t.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                ++i;
            }
            ++j;
        }
        return i == m;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
