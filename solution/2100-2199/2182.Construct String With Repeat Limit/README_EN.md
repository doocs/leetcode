# [2182. Construct String With Repeat Limit](https://leetcode.com/problems/construct-string-with-repeat-limit)

[中文文档](/solution/2100-2199/2182.Construct%20String%20With%20Repeat%20Limit/README.md)

## Description

<p>You are given a string <code>s</code> and an integer <code>repeatLimit</code>. Construct a new string <code>repeatLimitedString</code> using the characters of <code>s</code> such that no letter appears <strong>more than</strong> <code>repeatLimit</code> times <strong>in a row</strong>. You do <strong>not</strong> have to use all characters from <code>s</code>.</p>

<p>Return <em>the <strong>lexicographically largest</strong> </em><code>repeatLimitedString</code> <em>possible</em>.</p>

<p>A string <code>a</code> is <strong>lexicographically larger</strong> than a string <code>b</code> if in the first position where <code>a</code> and <code>b</code> differ, string <code>a</code> has a letter that appears later in the alphabet than the corresponding letter in <code>b</code>. If the first <code>min(a.length, b.length)</code> characters do not differ, then the longer string is the lexicographically larger one.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cczazcc&quot;, repeatLimit = 3
<strong>Output:</strong> &quot;zzcccac&quot;
<strong>Explanation:</strong> We use all of the characters from s to construct the repeatLimitedString &quot;zzcccac&quot;.
The letter &#39;a&#39; appears at most 1 time in a row.
The letter &#39;c&#39; appears at most 3 times in a row.
The letter &#39;z&#39; appears at most 2 times in a row.
Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
The string is the lexicographically largest repeatLimitedString possible so we return &quot;zzcccac&quot;.
Note that the string &quot;zzcccca&quot; is lexicographically larger but the letter &#39;c&#39; appears more than 3 times in a row, so it is not a valid repeatLimitedString.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aababab&quot;, repeatLimit = 2
<strong>Output:</strong> &quot;bbabaa&quot;
<strong>Explanation:</strong> We use only some of the characters from s to construct the repeatLimitedString &quot;bbabaa&quot;. 
The letter &#39;a&#39; appears at most 2 times in a row.
The letter &#39;b&#39; appears at most 2 times in a row.
Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
The string is the lexicographically largest repeatLimitedString possible so we return &quot;bbabaa&quot;.
Note that the string &quot;bbabaaa&quot; is lexicographically larger but the letter &#39;a&#39; appears more than 2 times in a row, so it is not a valid repeatLimitedString.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= repeatLimit &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def repeatLimitedString(self, s: str, repeatLimit: int) -> str:
        cnt = [0] * 26
        for c in s:
            cnt[ord(c) - ord('a')] += 1
        ans = []
        for i in range(25, -1, -1):
            j = i - 1
            while 1:
                for _ in range(min(repeatLimit, cnt[i])):
                    cnt[i] -= 1
                    ans.append(chr(ord('a') + i))
                if cnt[i] == 0:
                    break
                while j >= 0 and cnt[j] == 0:
                    j -= 1
                if j < 0:
                    break
                cnt[j] -= 1
                ans.append(chr(ord('a') + j))
        return ''.join(ans)
```

### **Java**

```java
class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 25; i >= 0; --i) {
            int j = i - 1;
            while (true) {
                for (int k = Math.min(repeatLimit, cnt[i]); k > 0; --k) {
                    cnt[i]--;
                    ans.append((char) ('a' + i));
                }
                if (cnt[i] == 0) {
                    break;
                }
                while (j >= 0 && cnt[j] == 0) {
                    --j;
                }
                if (j < 0) {
                    break;
                }
                cnt[j]--;
                ans.append((char) ('a' + j));
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string repeatLimitedString(string s, int repeatLimit) {
        vector<int> cnt(26);
        for (char& c : s) cnt[c - 'a']++;
        string ans;
        for (int i = 25; ~i; --i) {
            int j = i - 1;
            while (true) {
                for (int k = min(cnt[i], repeatLimit); k; --k) {
                    cnt[i]--;
                    ans.push_back('a' + i);
                }
                if (cnt[i] == 0) break;
                while (~j && cnt[j] == 0) --j;
                if (j < 0) break;
                cnt[j]--;
                ans.push_back('a' + j);
            }
        }
        return ans;
    }
};
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
