# [409. Longest Palindrome](https://leetcode.com/problems/longest-palindrome)

[中文文档](/solution/0400-0499/0409.Longest%20Palindrome/README.md)

## Description

<p>Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.</p>

<p>This is case sensitive, for example <code>"Aa"</code> is not considered a palindrome here.</p>

<p><b>Note:</b><br />

Assume the length of given string will not exceed 1,010.

</p>

<p><b>Example: </b>

<pre>

Input:

"abccccdd"



Output:

7



Explanation:

One longest palindrome that can be built is "dccaccd", whose length is 7.

</pre>

</p>

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
