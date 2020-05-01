# [01.06. Compress String](https://leetcode-cn.com/problems/compress-string-lcci)

## Description
<p>Implement a method to perform basic string compression using the counts of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the &quot;compressed&quot; string would not become smaller than the original string, your method should return the original string. You can assume the string has only uppercase and lowercase letters (a - z).</p>



<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>&quot;aabcccccaaa&quot;

<strong>Output: </strong>&quot;a2b1c5a3&quot;

</pre>



<p><strong>Example 2:</strong></p>



<pre>

<strong>Input: </strong>&quot;abbccd&quot;

<strong>Output: </strong>&quot;abbccd&quot;

<strong>Explanation: </strong>

The compressed string is &quot;a1b2c2d1&quot;, which is longer than the original string.

</pre>

<p><strong>Note:</strong></p>

- `0 <= S.length <= 50000`



## Solutions


### Python3

```python
class Solution:
    def compressString(self, S: str) -> str:
        if len(S) < 2:
            return S
        p, q = 0, 1
        res = ''
        while q < len(S):
            if S[p] != S[q]:
                res += (S[p] + str(q - p))
                p = q
            q += 1
        res += (S[p] + str(q - p))
        return res if len(res) < len(S) else S
```

### Java

```java
class Solution {
    public String compressString(String S) {
        if (S == null || S.length() < 2) {
            return S;
        }
        char[] chars = S.toCharArray();
        int p = 0, q = 1, n = chars.length;
        StringBuilder sb = new StringBuilder();
        while (q < n) {
            if (chars[p] != chars[q]) {
                sb.append(chars[p]).append(q - p);
                p = q;
            }
            q += 1;
        }
        sb.append(chars[p]).append(q - p);
        String res = sb.toString();
        return res.length() < n ? res : S;
    }
}
```

### ...
```

```
