# [345. 反转字符串中的元音字母](https://leetcode-cn.com/problems/reverse-vowels-of-a-string)

[English Version](/solution/0300-0399/0345.Reverse%20Vowels%20of%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>编写一个函数，以字符串作为输入，反转该字符串中的元音字母。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入: </strong>&quot;hello&quot;
<strong>输出: </strong>&quot;holle&quot;
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入: </strong>&quot;leetcode&quot;
<strong>输出: </strong>&quot;leotcede&quot;</pre>

<p><strong>说明:</strong><br>
元音字母不包含字母&quot;y&quot;。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

将字符串转为字符数组（或列表），定义双指针 p、q，分别指向数组（列表）头部和尾部，当 p、q 指向的字符均为元音字母时，进行交换。

依次遍历，当 `p >= q` 时，遍历结束。将字符数组（列表）转为字符串返回即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
