# [345. Reverse Vowels of a String](https://leetcode.com/problems/reverse-vowels-of-a-string)

[中文文档](/solution/0300-0399/0345.Reverse%20Vowels%20of%20a%20String/README.md)

## Description

<p>Write a function that takes a string as input and reverse only the vowels of a string.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">&quot;hello&quot;</span>

<strong>Output: </strong><span id="example-output-1">&quot;holle&quot;</span>

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-2-1">&quot;leetcode&quot;</span>

<strong>Output: </strong><span id="example-output-2">&quot;leotcede&quot;</span></pre>

</div>

<p><b>Note:</b><br />

The vowels does not include the letter &quot;y&quot;.</p>

<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reverseVowels(self, s: str) -> str:
        if s is None:
            return s
        chars = list(s)
        p, q = 0, len(chars) - 1
        while p < q:
            if chars[p] not in 'aeiouAEIOU':
                p += 1
                continue
            if chars[q] not in 'aeiouAEIOU':
                q -= 1
                continue
            chars[p], chars[q] = chars[q], chars[p]
            p += 1
            q -= 1
        return ''.join(chars)
```

### **Java**

```java
class Solution {
    public String reverseVowels(String s) {
        if (s == null) {
            return s;
        }
        char[] chars = s.toCharArray();
        int p = 0, q = chars.length - 1;
        while (p < q) {
            if (!isVowel(chars[p])) {
                ++p;
                continue;
            }
            if (!isVowel(chars[q])) {
                --q;
                continue;
            }
            swap(chars, p++, q--);
        }
        return String.valueOf(chars);
    }

    private void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }

    private boolean isVowel(char c) {
        switch(c) {
        case 'a':
        case 'e':
        case 'i':
        case 'o':
        case 'u':
        case 'A':
        case 'E':
        case 'I':
        case 'O':
        case 'U':
            return true;
        default:
            return false;
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
