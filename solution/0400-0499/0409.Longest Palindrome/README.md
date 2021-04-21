# [409. 最长回文串](https://leetcode-cn.com/problems/longest-palindrome)

[English Version](/solution/0400-0499/0409.Longest%20Palindrome/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。</p>

<p>在构造过程中，请注意区分大小写。比如&nbsp;<code>&quot;Aa&quot;</code>&nbsp;不能当做一个回文字符串。</p>

<p><strong>注意:</strong><br />
假设字符串的长度不会超过 1010。</p>

<p><strong>示例 1: </strong></p>

<pre>
输入:
&quot;abccccdd&quot;

输出:
7

解释:
我们可以构造的最长的回文串是&quot;dccaccd&quot;, 它的长度是 7。
</pre>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
