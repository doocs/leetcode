## 反转字符串
### 题目描述

编写一个函数，其作用是将输入的字符串反转过来。

示例 1:
```
输入: "hello"
输出: "olleh"
```

示例 2:
```
输入: "A man, a plan, a canal: Panama"
输出: "amanaP :lanac a ,nalp a ,nam A"
```

### 解法
本题利用双指针解决。

```java
class Solution {
    public String reverseString(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        char[] chars = s.toCharArray();
        int p = 0;
        int q = chars.length - 1;
        while (p < q) {
            char tmp = chars[p];
            chars[p] = chars[q];
            chars[q] = tmp;
            ++p;
            --q;
        }
        return String.valueOf(chars);
    }
    
}
```

# [题目](这里是题目链接，如：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

## 题目描述
<!-- 这里写题目描述 -->


## 解法
<!-- 这里可写通用的实现逻辑 -->


### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### ...
```

```
