# [面试题 01.03. URL化](https://leetcode-cn.com/problems/string-to-url-lcci/)

## 题目描述
URL化。编写一种方法，将字符串中的空格全部替换为 `%20`。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用 `Java` 实现的话，请使用字符数组实现，以便直接在数组上操作。）

**示例1:**

```
 输入："Mr John Smith    ", 13
 输出："Mr%20John%20Smith"
```

**示例2:**

```
 输入："               ", 5
 输出："%20%20%20%20%20"
```

**提示：**

1. 字符串长度在[0, 500000]范围内。

## 解法
### Python3
```python
class Solution:
    def replaceSpaces(self, S: str, length: int) -> str:
        S = S[:length] if length < len(S) else S
        return S.replace(' ', '%20')
```

### Java
```java

```

### ...
```

```
