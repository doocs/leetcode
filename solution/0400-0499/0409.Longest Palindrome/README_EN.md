# [409. Longest Palindrome](https://leetcode.com/problems/longest-palindrome)

[中文文档](/solution/0400-0499/0409.Longest%20Palindrome/README.md)

## Description

<p>Given a string <code>s</code> which consists of lowercase or uppercase letters, return <em>the length of the <strong>longest palindrome</strong></em>&nbsp;that can be built with those letters.</p>

<p>Letters are <strong>case sensitive</strong>, for example,&nbsp;<code>&quot;Aa&quot;</code> is not considered a palindrome here.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abccccdd&quot;
<strong>Output:</strong> 7
<strong>Explanation:</strong>
One longest palindrome that can be built is &quot;dccaccd&quot;, whose length is 7.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a&quot;
<strong>Output:</strong> 1
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bb&quot;
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code> consists of lowercase <strong>and/or</strong> uppercase English&nbsp;letters only.</li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestPalindrome(self, s: str) -> int:
        res = [0] * 128
        for ch in s:
            res[ord(ch)] += 1
        odd_cnt, n = 0, len(s)
        for e in res:
            odd_cnt += (e % 2)
        return n if odd_cnt == 0 else n - odd_cnt + 1
```

### **Java**

```java
class Solution {
    public int longestPalindrome(String s) {
        int[] res = new int[128];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            res[s.charAt(i)]++;
        }
        int oddCnt = 0;
        for (int e : res) {
            oddCnt += (e % 2);
        }
        return oddCnt == 0 ? n : n - oddCnt + 1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
