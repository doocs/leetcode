# [383. Ransom Note](https://leetcode.com/problems/ransom-note)

[中文文档](/solution/0300-0399/0383.Ransom%20Note/README.md)

## Description

<p>

Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom

note can be constructed from the magazines ; otherwise, it will return false.

</p>

<p>

Each letter in the magazine string can only be used once in your ransom note.

</p>

<p><b>Note:</b><br />

You may assume that both strings contain only lowercase letters.

</p>

<pre>

canConstruct("a", "b") -> false

canConstruct("aa", "ab") -> false

canConstruct("aa", "aab") -> true

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canConstruct(self, ransomNote: str, magazine: str) -> bool:
        chars = {}
        for i in magazine:
            chars[i] = chars.get(i, 0) + 1
        for i in ransomNote:
            if not chars.get(i):
                return False
            chars[i] -= 1
        return True
```

### **Java**

```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] chars = new int[26];
        for (int i = 0, n = magazine.length(); i < n; ++i) {
            int idx = magazine.charAt(i) - 'a';
            ++chars[idx];
        }
        for (int i = 0, n = ransomNote.length(); i < n; ++i) {
            int idx = ransomNote.charAt(i) - 'a';
            if (chars[idx] == 0) return false;
            --chars[idx];
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
