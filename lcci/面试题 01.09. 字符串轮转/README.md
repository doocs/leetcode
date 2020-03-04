# [面试题 01.09. 字符串轮转](https://leetcode-cn.com/problems/flipped-string-lcci/)

## 题目描述
字符串轮转。给定两个字符串 `s1` 和 `s2`，请编写代码检查 `s2` 是否为 `s1` 旋转而成（比如，`waterbottle` 是 `erbottlewat` 旋转后的字符串）。

**示例1:**

```
 输入：s1 = "waterbottle", s2 = "erbottlewat"
 输出：True
```

**示例2:**
```
 输入：s1 = "aa", "aba"
 输出：False
```

**提示：**

1. 字符串长度在 `[0, 100000]` 范围内。

**说明:**

1. 你能只调用一次检查子串的方法吗？

## 解法
### Python3
```python

```

### Java
```java
class Solution {
    public boolean isFlipedString(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 != len2) {
            return false;
        }
        if ((len1 == 0 && len2 == 0) || (s1.equals(s2))) {
            return true;
        }

        for (int i = 0; i < len1; ++i) {
            s1 = flip(s1);
            if (s1.equals(s2)) {
                return true;
            }
        }
        return false;


    }

    private String flip(String s) {
        return s.substring(1) + s.charAt(0);
    }
}
```

### ...
```

```