# [242. 有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram)

[English Version](/solution/0200-0299/0242.Valid%20Anagram/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串 <em>s</em> 和 <em>t</em> ，编写一个函数来判断 <em>t</em> 是否是 <em>s</em> 的字母异位词。</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> <em>s</em> = &quot;anagram&quot;, <em>t</em> = &quot;nagaram&quot;
<strong>输出:</strong> true
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> <em>s</em> = &quot;rat&quot;, <em>t</em> = &quot;car&quot;
<strong>输出: </strong>false</pre>

<p><strong>说明:</strong><br>
你可以假设字符串只包含小写字母。</p>

<p><strong>进阶:</strong><br>
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？</p>


## 解法

<!-- 这里可写通用的实现逻辑 -->

哈希表解决。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t):
            return False
        n = len(s)
        chars = [0] * 26
        for i in range(n):
            chars[ord(s[i]) - ord('a')] += 1
            chars[ord(t[i]) - ord('a')] -= 1
        for i in range(26):
            if chars[i] != 0:
                return False
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        int n;
        if ((n = s.length()) != t.length()) {
            return false;
        }
        int[] chars = new int[26];
        for (int i = 0; i < n; ++i) {
            ++chars[s.charAt(i) - 'a'];
            --chars[t.charAt(i) - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (chars[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
